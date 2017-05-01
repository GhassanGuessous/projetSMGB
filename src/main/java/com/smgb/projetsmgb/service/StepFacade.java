/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Action;
import com.smgb.projetsmgb.bean.Processus;
import com.smgb.projetsmgb.bean.Step;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class StepFacade extends AbstractFacade<Step> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StepFacade() {
        super(Step.class);
    }
    
    public void clone(Step stepSource, Step stepDestination){
        stepDestination.setNom(stepSource.getNom());
        stepDestination.setProcessus(stepSource.getProcessus());
        stepDestination.setProvideInterfaceItem(stepSource.getProvideInterfaceItem());
    }
    
    public Step clone(Step step){
        Step cloned = new Step();
        clone(step, cloned);
        return cloned;
    }

    public int verifierListeStep(List<Step> steps, Step step){
        List<Step> stepsBD = findAll();
        for (Step step1 : steps) {
            if(step.getNom().equals(step1.getNom()) && step.getProcessus().getNom().equals(step1.getProcessus().getNom())){
                return -1;
            }else if(step.getProvideInterfaceItem().getId() == step1.getProvideInterfaceItem().getId()){
                return -2;
            }
        }
        for (Step step1 : stepsBD) {
            if(step.getProvideInterfaceItem().getId() == step1.getProvideInterfaceItem().getId()){
                return -3;
            }
        }
        return 1;
    }
    
    public Object[] findStepByProcessus(Processus processus, Step step){
        String requete = "SELECT s FROM Step s WHERE s.nom = '" + step.getNom() + "' AND s.processus.nom = '" + processus.getNom() + "'";
        List<Step> steps = em.createQuery(requete).getResultList();
        if(!steps.isEmpty()){
            return new Object[]{-1, steps.get(0)};
        }else{
            step.setProcessus(processus);
            return new Object[]{1, step};
        }
    }
    
    public List<Step> findByAction(Action action){
        List<Step> steps = new ArrayList();
        List<Step> steps1 = new ArrayList();
        List<Processus> processuses = em.createQuery("SELECT p FROM Processus p WHERE p.action.id = " + action.getId()).getResultList();
        for (Processus processuse : processuses) {
            steps1 = em.createQuery("SELECT s FROM Step s WHERE s.processus.id = " + processuse.getId()).getResultList();
            steps.addAll(steps1);
            steps1 = new ArrayList();
        }
        return steps;
    }
}
