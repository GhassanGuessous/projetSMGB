/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.ContrainteItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Owner
 */
@Stateless
public class ContrainteItemFacade extends AbstractFacade<ContrainteItem> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContrainteItemFacade() {
        super(ContrainteItem.class);
    }
    
    public int verify(List<ContrainteItem> cis, ContrainteItem ci1) {
        for (ContrainteItem ci2 : cis) {
            if ((ci1.getAttribut().equals(ci2.getAttribut())) && (ci1.getCritere().equals(ci2.getCritere())) && (ci1.getValeurCritique().equals(ci2.getValeurCritique())) && (ci1.getContrainte().equals(ci2.getContrainte()) && (ci1.getStep().equals(ci2.getStep())))) {
                return -1;
            }
        }
        return 1;

    }

/*public int findByAttribut(ContrainteItem ci1){
    List<ContrainteItem> cis = em.createQuery("SELECT ci2 FROM ContrainteItem ci2 WHERE ci2.contrainte.id = " + ci1.getContrainte().getId()+ " AND ci2.critere = '" +ci1.getCritere()+ "'AND ci2.attribut = '" +ci1.getAttribut()+  "' AND ci2.valeurCritique = '" + ci1.getValeurCritique()+  "' AND ci2.step = '" +ci1.getStep()+  "' AND ci2.contrainte = '" +ci1.getContrainte()+"'").getResultList();
           
    if(cis.isEmpty()){
        return 1;
    }
    return -1;
}*/
   public void clone(ContrainteItem contrainteItemSource,ContrainteItem contrainteItemDestination) {
    contrainteItemDestination.setId(contrainteItemSource.getId());
       contrainteItemDestination.setAttribut(contrainteItemSource.getAttribut());
       contrainteItemDestination.setCritere(contrainteItemSource.getCritere());
       contrainteItemDestination.setContrainte(contrainteItemSource.getContrainte());
       contrainteItemDestination.setStep(contrainteItemSource.getStep());
       contrainteItemDestination.setValeurCritique(contrainteItemSource.getValeurCritique());
    }

    public ContrainteItem clone(ContrainteItem contrainteItem) {
       ContrainteItem cloned = new ContrainteItem();
        clone(contrainteItem, cloned);
        return cloned;

    }
}
