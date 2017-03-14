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
public class ComposantFacade extends AbstractFacade<Composant> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @EJB
    ProvideInterfaceItemFacade provideInterfaceItemFacade;
    @EJB
    ProvideInterfaceFacade provideInterfaceFacade;

    @EJB
    InputFacade inputFacade;

    @EJB
    OutputFacade outputFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComposantFacade() {
        super(Composant.class);
    }

    //cherche si le domaine a deja ce composant
    public Object[] findByDomaineAndNom(Composant composant) {
        String requete = "SELECT c FROM Composant c WHERE c.domaineAssocie.id = " + composant.getDomaineAssocie().getId() + " AND c.nom = '" + composant.getNom() + "'";
        List<Composant> composants = em.createQuery(requete).getResultList();
        if (!composants.isEmpty()) {
            return new Object[]{1, composants.get(0)};
        } else {
            return new Object[]{-1, composant};
        }
    }

    public int save(Composant composant, ProvideInterfaceItem provideInterfaceItem, Input input, Output output) {
        int retour = 0;
        Object[] res1 = findByDomaineAndNom(composant);
        int res1_1 = (int) res1[0];
        Composant composant1 = (Composant) res1[1];
        //composant existe deja pour le subDomaine selectionné
        if (res1_1 > 0) {
            Object[] res2 = provideInterfaceItemFacade.findProvideInterfaceItemByProvideInterfaceAndNom(composant1.getProvideInterface(), provideInterfaceItem);
            int res2_1 = (int) res2[0];
            provideInterfaceItem = (ProvideInterfaceItem) res2[1];
            //provideInterfaceItem existe deja pour ce composant
            if (res2_1 > 0) {
                Object[] res3 = inputFacade.findInputByProvideInterfaceItemAndNomAndType(provideInterfaceItem, input);
                int res3_1 = (int) res3[0];
                Input input1 = (Input) res3[1];
                //input existe deja pour cette provideInterfaceItem
                if (res3_1 > 0) {
                    return -1;
                } else {//sinon on ajoute l'input
                    inputFacade.create(input1);
                    return 3;
                }
            } else {
                provideInterfaceItem.setProvideInterface(composant1.getProvideInterface());
                retour = 2;
            }
        } else {
            ProvideInterface provideInterface = new ProvideInterface();
            Long i = provideInterfaceFacade.generateId("ProvideInterface", "id");
            provideInterface.setNom(composant1.getNom());
            provideInterface.setComposant(composant1);
            provideInterfaceItem.setProvideInterface(provideInterface);
            composant1.setProvideInterface(provideInterface);
            create(composant1);
            provideInterfaceFacade.create(provideInterface);
            retour = 1;
        }
        input.setProvideInterfaceItem(provideInterfaceItem);
        output.setProvideInterfaceItem(provideInterfaceItem);
        provideInterfaceItemFacade.create(provideInterfaceItem);
        inputFacade.create(input);
        outputFacade.create(output);
        return retour;
    }
}
