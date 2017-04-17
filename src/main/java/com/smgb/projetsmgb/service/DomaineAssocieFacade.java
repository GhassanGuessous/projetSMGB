/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;
import com.smgb.projetsmgb.bean.Domaine;
import com.smgb.projetsmgb.bean.DomaineAssocie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class DomaineAssocieFacade extends AbstractFacade<DomaineAssocie> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public List<DomaineAssocie> findListSubDomaines() {
        return em.createQuery("SELECT sd FROM DomaineAssocie sd WHERE sd.type = 2").getResultList();
    }

    public List<DomaineAssocie> findSubDomainesByDomaine(Domaine domaine) {
        return em.createQuery("SELECT sd FROM DomaineAssocie sd WHERE sd.domaine.id = " + domaine.getId() + " AND sd.type = 2").getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DomaineAssocieFacade() {
        super(DomaineAssocie.class);
    }
    
}
