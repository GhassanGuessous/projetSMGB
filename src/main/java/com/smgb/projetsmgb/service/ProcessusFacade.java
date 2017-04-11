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

    public void clone(Processus processusSource, Processus processusDestination) {
        processusDestination.setNom(processusSource.getNom());
        processusDestination.setAction(processusSource.getAction());
    }

    public Processus clone(Processus processus) {
        Processus cloned = new Processus();
        clone(processus, cloned);
        return cloned;
    }

    public int verifierListeProcessus(List<Processus> processuses, Processus processus) {
        for (Processus processuse : processuses) {
            if (processus.getNom().equals(processuse.getNom())) {
                return -1;
            }
        }
        return 1;
    }

    public void save(List<Processus> processuses, List<Step> steps) {
        List<Step> steps1 = new ArrayList();
        for (Processus processus : processuses) {
            for (Step step : steps) {
                if (step.getProcessus().getNom().equals(processus.getNom())) {
                    steps1.add(step);
                }
            }
            Object[] res = findProcessusByAction(processus);
            int res1 = (int) res[0];
            if (res1 > 0) {
                Long i = generateId("Processus", "id");
                for (Step step : steps1) {
                    stepFacade.create(step);
                }
                create(processus);
            } else {
                Processus processus1 = (Processus) res[1];
                for (Step step : steps1) {
                    Object[] resStep = stepFacade.findStepByProcessus(processus1, step);
                    int resStep1 = (int) resStep[0];
                    if(resStep1 > 0){
                        Step step1 = (Step) resStep[1];
                        stepFacade.create(step1);
                    }
                }
            }
        }
    }

    public Object[] findProcessusByAction(Processus processus) {
        String requete = "SELECT p FROM Processus p WHERE p.nom = '" + processus.getNom() + "' AND p.action.id = " + processus.getAction().getId();
        List<Processus> processuses = em.createQuery(requete).getResultList();
        if (!processuses.isEmpty()) {
            return new Object[]{-1, processuses.get(0)};
        } else {
            return new Object[]{1, processus};
        }
    }
}
