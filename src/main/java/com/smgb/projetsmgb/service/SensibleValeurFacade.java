/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.SensibleParam;
import com.smgb.projetsmgb.bean.SensibleValeur;
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
    
     public Object[] findByNomAndSensibleParam2(SensibleParam sensibleParam) {
        String requete = "SELECT se FROM SensibleValeur se WHERE se.sensibleParam.nom =" + sensibleParam.getNom();
        List<SensibleValeur> sensibleValeurs = em.createQuery(requete).getResultList();
        if (sensibleValeurs.get(0) != null) {
            return new Object[]{1, sensibleValeurs.get(0)};
        } else {
            return new Object[]{-1, sensibleParam.getSensibleValeur()};
        }
    }
    
    public int findBySensibleParam2(SensibleParam sensibleParam){
        String requete = "SELECT se FROM SensibleValeur se WHERE se.sensibleParam.nom = '" + sensibleParam.getNom() + "' AND se.nom = '" + sensibleParam.getSensibleValeur().getNom() + "'";
        List<SensibleValeur> sensibleValeurs = em.createQuery(requete).getResultList();
        if(sensibleValeurs.isEmpty()){
            return 1;
        }else{
            return -1;
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
      
      public List<SensibleValeur> distinctSensibleParam(){
         String rq = "SELECT DISTINCT sv FROM SensibleValeur sv";
         return em.createQuery(rq).getResultList();
     }
      
    public void clone(SensibleValeur sensibleValeurSource, SensibleValeur sensibleValeurDestination) {
        sensibleValeurDestination.setNom(sensibleValeurSource.getNom());
        sensibleValeurDestination.setSensibleParam(sensibleValeurSource.getSensibleParam());
    }

    public SensibleValeur clone(SensibleValeur sensibleValeur) {
        SensibleValeur cloned = new SensibleValeur();
        clone(sensibleValeur, cloned);
        return cloned;
    }

    public int findSensibleValeur(List<SensibleValeur> itemsValeur, SensibleValeur sensibleValeur) {
         
        for(SensibleValeur item : itemsValeur){
            if(item.getSensibleParam().getNom().equals(sensibleValeur.getSensibleParam().getNom()) && item.getSensibleParam().getContexteElement().getNom().equals(sensibleValeur.getSensibleParam().getContexteElement().getNom())){
               
                    return -1;
                
            }
        }
        return 1;
    
    }
    
}
