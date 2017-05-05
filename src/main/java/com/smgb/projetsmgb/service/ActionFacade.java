/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Action;
import com.smgb.projetsmgb.bean.Goal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class ActionFacade extends AbstractFacade<Action> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActionFacade() {
        super(Action.class);
    }
    
    public Object[] findActionByGoal(Goal goal){
        List<Action> actions = em.createQuery("SELECT a FROM Action a WHERE a.goal.nom = '" + goal.getNom() + "'").getResultList();
        if(!actions.isEmpty()){
            return new Object[]{-1, actions.get(0)};
        }else{
            return new Object[]{1, null};
        }
    }
}
