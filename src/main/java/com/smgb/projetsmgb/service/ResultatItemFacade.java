/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.ResultatItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ACER
 */
@Stateless
public class ResultatItemFacade extends AbstractFacade<ResultatItem> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public int save(ResultatItem resultatItem){
        if(resultatItem!=null){
            create(resultatItem);
            return 1;
        }
        return -1;
    }
    
    public int verifierListeResultatItem(List<ResultatItem> resultatItems, ResultatItem resultatItem) {
        for (ResultatItem item : resultatItems) {
            if (item.getValeur().equals(resultatItem.getValeur())) {
                return -1;
            }
        }
        return 1;
    }
    
    public void clone(ResultatItem resultatItemSource,ResultatItem resultatItemDestination) {
        resultatItemDestination.setId(resultatItemSource.getId());
        resultatItemDestination.setStep(resultatItemSource.getStep());
        resultatItemDestination.setValeur(resultatItemSource.getValeur());
        resultatItemDestination.setResultat(resultatItemSource.getResultat());
    }

    public ResultatItem clone(ResultatItem resultatItem) {
        ResultatItem cloned = new ResultatItem();
        clone(resultatItem, cloned);
        return cloned;
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultatItemFacade() {
        super(ResultatItem.class);
    }
    
}
