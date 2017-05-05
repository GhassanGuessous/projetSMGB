/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Resultat;
import com.smgb.projetsmgb.bean.ResultatItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ACER
 */
@Stateless
public class ResultatFacade extends AbstractFacade<Resultat> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @EJB
    ResultatItemFacade resultatItemFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultatFacade() {
        super(Resultat.class);
    }
    
    public int save(List<ResultatItem> LrIs, Resultat resultat) {
        create(resultat);
        for (ResultatItem LrI : LrIs) {
            resultatItemFacade.create(LrI);
        }
        return 1;
    }
    
    public int findByAction(Resultat resultat){
        List<Resultat> resultats = em.createQuery("SELECT r FROM Resultat r WHERE r.nom = '" + resultat.getNom() 
                + "' AND r.action.id = " + resultat.getAction().getId()).getResultList();
        if(resultats.isEmpty()){
            return -1;
        }else{
            return 1;
        }
    }
}
