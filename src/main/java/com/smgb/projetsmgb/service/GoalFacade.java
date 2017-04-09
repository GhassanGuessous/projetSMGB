/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Action;
import com.smgb.projetsmgb.bean.Goal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class GoalFacade extends AbstractFacade<Goal> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    private ActionFacade actionFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GoalFacade() {
        super(Goal.class);
    }

    public int saveGoal(Goal goal, Action action) {
        int res = 0;
        String requete = "SELECT g FROM Goal g WHERE g.nom = '" + goal.getNom() + "'";
        requete += " AND g.domaine.id = " + goal.getDomaine().getId();
        List<Goal> goals = em.createQuery(requete).getResultList();
        if (goals.isEmpty()) {
            create(goal);
            res = 1;
        }else{
            int i = actionFacade.findActionByGoal(goal);
            if(i < 0){
                return -1; //goal et action existent
            }
        }
        actionFacade.create(action);
        return res;

    }
}
