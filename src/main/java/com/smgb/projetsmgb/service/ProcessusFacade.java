/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Processus;
import com.smgb.projetsmgb.bean.Step;
import java.util.ArrayList;
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
public class ProcessusFacade extends AbstractFacade<Processus> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    private StepFacade stepFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcessusFacade() {
        super(Processus.class);
    }
    
    public void clone(Processus processusSource, Processus processusDestination){
        processusDestination.setNom(processusSource.getNom());
        processusDestination.setAction(processusSource.getAction());
    }
    
    public Processus clone(Processus processus){
        Processus cloned = new Processus();
        clone(processus, cloned);
        return cloned;
    }

    public int verifierListeProcessus(List<Processus> processuses, Processus processus){
        for (Processus processuse : processuses) {
            if(processus.getNom().equals(processuse.getNom())){
                return -1;
            }
        }
        return 1;
    }

    public void save(List<Processus> processuses, List<Step> steps) {
        List<Step> steps1 = new ArrayList();
         for (Processus processus : processuses) {
            Long i = generateId("Processus", "id");
            for (Step step : steps) {
                if(step.getProcessus().getNom().equals(processus.getNom())){
                    steps1.add(step);
                }
            }
            for (Step step : steps1) {
                stepFacade.create(step);
            }
            create(processus);
        }
    }
}
