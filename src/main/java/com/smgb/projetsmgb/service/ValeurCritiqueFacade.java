/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.ValeurCritique;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Owner
 */
@Stateless
public class ValeurCritiqueFacade extends AbstractFacade<ValeurCritique> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValeurCritiqueFacade() {
        super(ValeurCritique.class);
    }
    public void saveValeurCritique(ValeurCritique valeurCritique){
         create(valeurCritique);
     }
      // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
