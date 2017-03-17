/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.SensibleParam;
import bean.SensibleValeur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class SensibleValeurFacade extends AbstractFacade<SensibleValeur> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SensibleValeurFacade() {
        super(SensibleValeur.class);
    }
    
    
    
    public Object[] findByNomAndSensibleParam(SensibleParam sensibleParam, SensibleValeur sensibleValeur) {
        String requete = "SELECT se FROM SensibleValeur se WHERE se.sensibleParam.id =" + sensibleParam.getId() + " AND se.nom = '" + sensibleValeur.getNom() + "'";
        List<SensibleValeur> sensibleValeurs = em.createQuery(requete).getResultList();
        if (sensibleValeurs.get(0) != null) {
            return new Object[]{1, sensibleValeurs.get(0)};
        } else {
            return new Object[]{-1, sensibleValeur};
        }
    }
      public SensibleValeur findBySensibleParam(SensibleParam sensibleParam) {

        String requete = "";

        if (sensibleParam != null) {
            requete += "SELECT sv FROM SensibleValeur sv WHERE sv.sensibleParam.id=" + sensibleParam.getId();
        }

        List<SensibleValeur> sensibleValeurs = em.createQuery(requete).getResultList();

        return sensibleValeurs.get(0);
    }
    
}
