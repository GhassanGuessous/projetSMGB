/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Contrainte;
import com.smgb.projetsmgb.bean.ContrainteItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Owner
 */
@Stateless
public class ContrainteFacade extends AbstractFacade<Contrainte> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @EJB
    ContrainteItemFacade contrainteItemFacade;
    
    @EJB
    ValeurCritiqueFacade valeurCritiqueFacade;

    public ContrainteFacade() {
        super(Contrainte.class);
    }

    public int saveContrainte(Contrainte contrainte) {
        List<Contrainte> cs = em.createQuery("SELECT c FROM Contrainte c WHERE c.action.id = " + contrainte.getAction().getId() + " AND c.nom='" + contrainte.getNom() + "'").getResultList();
        if (cs.isEmpty()) {
            create(contrainte);
            return 1;
        }
        return -1;
    }

    public int save(List<ContrainteItem> cis) {
        for (ContrainteItem ci : cis) {
           /* int i = contrainteItemFacade.findByAttribut(ci);
            if (i > 0) {*/
                contrainteItemFacade.create(ci);
                valeurCritiqueFacade.create(ci.getValeurCritique());
                
            }
       /* }
        return -1;*/
        // Add business logic below. (Right-click in editor and choose
        // "Insert Code > Add Business Method")
      return 1;
    }
}
