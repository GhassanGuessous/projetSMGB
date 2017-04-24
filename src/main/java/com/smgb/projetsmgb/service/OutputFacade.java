/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;
import com.smgb.projetsmgb.bean.Output;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class OutputFacade extends AbstractFacade<Output> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OutputFacade() {
        super(Output.class);
    }
    
     public void clone(Output outputSource, Output outputDestination){
        outputDestination.setNom(outputSource.getNom());
        outputDestination.setType(outputSource.getType());
        outputDestination.setProvideInterfaceItem(outputSource.getProvideInterfaceItem());
    }
    
    public Output clone(Output output){
        Output cloned = new Output();
        clone(output, cloned);
        return cloned;
    }
    
}
