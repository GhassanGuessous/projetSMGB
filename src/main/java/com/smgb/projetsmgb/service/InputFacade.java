/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Input;
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
public class InputFacade extends AbstractFacade<Input> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InputFacade() {
        super(Input.class);
    }
    
    public void clone(Input inputSource, Input inputDestination){
        inputDestination.setNom(inputSource.getNom());
        inputDestination.setType(inputSource.getType());
        inputDestination.setProvideInterfaceItem(inputSource.getProvideInterfaceItem());
    }
    
    public Input clone(Input input){
        Input cloned = new Input();
        clone(input, cloned);
        return cloned;
    }

    public Object[] findInputByProvideInterfaceItemAndNomAndType(ProvideInterfaceItem provideInterfaceItem, Input input) {
        String requete = "SELECT i FROM Input i WHERE i.provideInterfaceItem.nom = '" + provideInterfaceItem.getNom() + "'";
        requete += " AND i.nom = '" + input.getNom() + "'";
        requete += " AND i.type = '" + input.getType() + "'";
        List<Input> inputs = em.createQuery(requete).getResultList();
        if (!inputs.isEmpty()) {
            return new Object[]{-1, inputs.get(0)};
        } else {
            input.setProvideInterfaceItem(provideInterfaceItem);
            return new Object[]{1, input};
        }
    }
    
    public int findInput(List<Input> inputs, Input input) {
        for (Input input1 : inputs) {
            if(input.getNom().equals(input1.getNom()) && input.getProvideInterfaceItem().getNom().equals(input1.getProvideInterfaceItem().getNom())){
                return -1;
            }
        }
        return 1;
    }
    
    public List<Input> findInputsByProvideInterfaceItem(ProvideInterfaceItem provideInterfaceItem){
        return em.createQuery("SELECT i FROM Input i WHERE i.provideInterfaceItem.id = " + provideInterfaceItem.getId()).getResultList();
    }
}
