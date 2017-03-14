/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Composant;
import com.smgb.projetsmgb.bean.ProvideInterface;
import com.smgb.projetsmgb.bean.ProvideInterfaceItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class ProvideInterfaceItemFacade extends AbstractFacade<ProvideInterfaceItem> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProvideInterfaceItemFacade() {
        super(ProvideInterfaceItem.class);
    }

    public Object[] findProvideInterfaceItemByProvideInterfaceAndNom(ProvideInterface provideInterface, ProvideInterfaceItem provideInterfaceItem) {
        String requete = "SELECT p FROM ProvideInterfaceItem p WHERE p.nom = '" + provideInterfaceItem.getNom() + "'"
                + " AND p.provideInterface.id = " + provideInterface.getId();
        List<ProvideInterfaceItem> provideInterfaceItems = em.createQuery(requete).getResultList();
        if (!provideInterfaceItems.isEmpty()) {
            return new Object[]{1, provideInterfaceItems.get(0)};
        }else{
            return new Object[]{-1, provideInterfaceItem};
        }
    }

    public List<ProvideInterfaceItem> findProvideInterfaceItemByComposant(String nom) {
        List<ProvideInterfaceItem> provideInterfaceItems = em.createQuery("SELECT p FROM ProvideInterfaceItem p WHERE p.provideInterface.composant.nom = '" + nom + "'").getResultList();
        if(!provideInterfaceItems.isEmpty()){
            return provideInterfaceItems;
        }else{
            return null;
        }
    }
}
