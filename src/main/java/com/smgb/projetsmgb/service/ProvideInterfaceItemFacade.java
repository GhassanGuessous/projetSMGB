/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Composant;
import com.smgb.projetsmgb.bean.Input;
import com.smgb.projetsmgb.bean.Output;
import com.smgb.projetsmgb.bean.ProvideInterface;
import com.smgb.projetsmgb.bean.ProvideInterfaceItem;
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
public class ProvideInterfaceItemFacade extends AbstractFacade<ProvideInterfaceItem> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private InputFacade inputFacade;
    @EJB
    private OutputFacade outputFacade;
    
    public ProvideInterfaceItemFacade() {
        super(ProvideInterfaceItem.class);
    }
    
    public void clone(ProvideInterfaceItem provideInterfaceItemSource, ProvideInterfaceItem provideInterfaceItemDestination) {
        provideInterfaceItemDestination.setNom(provideInterfaceItemSource.getNom());
        provideInterfaceItemDestination.setOutput(provideInterfaceItemSource.getOutput());
        provideInterfaceItemDestination.setProvideInterface(provideInterfaceItemSource.getProvideInterface());
    }

    public ProvideInterfaceItem clone(ProvideInterfaceItem provideInterfaceItem) {
        ProvideInterfaceItem cloned = new ProvideInterfaceItem();
        clone(provideInterfaceItem, cloned);
        return cloned;
    }
    //BD
    public Object[] findProvideInterfaceItemByProvideInterfaceAndNom(ProvideInterfaceItem provideInterfaceItem) {
        String requete = "SELECT p FROM ProvideInterfaceItem p WHERE p.nom = '" + provideInterfaceItem.getNom() + "'"
                + " AND p.provideInterface.id = " + provideInterfaceItem.getProvideInterface().getId();
        List<ProvideInterfaceItem> provideInterfaceItems = em.createQuery(requete).getResultList();
        if (!provideInterfaceItems.isEmpty()) {
            return new Object[]{1, provideInterfaceItems.get(0)};
        }else{
            return new Object[]{-1, provideInterfaceItem};
        }
    }
    //LIST
    public int findProvideInterfaceItem(List<ProvideInterfaceItem> provideInterfaceItems, ProvideInterfaceItem provideInterfaceItem) {
        for (ProvideInterfaceItem provideInterfaceItem1 : provideInterfaceItems) {
            if(provideInterfaceItem.getNom().equals(provideInterfaceItem1.getNom()) 
                    && provideInterfaceItem.getProvideInterface().getId() == provideInterfaceItem1.getProvideInterface().getId()){
                return -1;
            }
        }
        return 1;
    }
    
    public List<ProvideInterfaceItem> findProvideInterfaceItemByComposant(Composant composant) {
        List<ProvideInterfaceItem> provideInterfaceItems = em.createQuery("SELECT p FROM ProvideInterfaceItem p WHERE p.provideInterface.composant.id = " + composant.getId()).getResultList();
        return provideInterfaceItems;
    }
    
    public void save(List<ProvideInterfaceItem> provideInterfaceItems, List<Input> inputs){
        List<Input> inputs1 = new ArrayList();
        for (ProvideInterfaceItem provideInterfaceItem : provideInterfaceItems) {
            for (Input input : inputs) {
                if (input.getProvideInterfaceItem().getNom().equals(provideInterfaceItem.getNom())) {
                    inputs1.add(input);
                }
            }
            Object[] res = findProvideInterfaceItemByProvideInterfaceAndNom(provideInterfaceItem);
            int res1 = (int) res[0];
            if (res1 < 0) {
                Long i = generateId("ProvideInterfaceItem", "id");
                for (Input input : inputs1) {
                    inputFacade.create(input);
                }
                inputs1 = new ArrayList();
                create(provideInterfaceItem);
                Output output = provideInterfaceItem.getOutput();
                output.setProvideInterfaceItem(provideInterfaceItem);
                outputFacade.create(output);
            } else {
                ProvideInterfaceItem provideInterfaceItem1 = (ProvideInterfaceItem) res[1];
                for (Input input : inputs1) {
                    Object[] resInput = inputFacade.findInputByProvideInterfaceItemAndNomAndType(provideInterfaceItem1, input);
                    int resInput1 = (int) resInput[0];
                    if (resInput1 > 0) {
                        Input input1 = (Input) resInput[1];
                        inputFacade.create(input1);
                    }
                }
            }
        }
    }
}
