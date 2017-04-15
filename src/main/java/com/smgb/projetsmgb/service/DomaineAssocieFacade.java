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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DomaineAssocieFacade() {
        super(DomaineAssocie.class);
    }
    
    public List<DomaineAssocie> findListSubDomaines(){
        return em.createQuery("SELECT sd FROM DomaineAssocie sd WHERE sd.type = 2").getResultList();
    }
    
    public List<DomaineAssocie> findSubDomainesByDomaine(Domaine domaine){
        return em.createQuery("SELECT sd FROM DomaineAssocie sd WHERE sd.domaine.id = " + domaine.getId() + " AND sd.type = 2").getResultList();
    }
     public void clone(DomaineAssocie domaineAssocieSource,DomaineAssocie domaineAssocieDestination) {
      domaineAssocieDestination.setId(domaineAssocieSource.getId());
        domaineAssocieDestination.setNom(domaineAssocieSource.getNom());
       domaineAssocieDestination.setType(domaineAssocieSource.getType());
       domaineAssocieDestination.setDomaine(domaineAssocieSource.getDomaine());
    }

    public DomaineAssocie clone(DomaineAssocie domaineAssocie) {
       DomaineAssocie cloned = new DomaineAssocie();
        clone(domaineAssocie, cloned);
        return cloned;

    }
public int verify(List<DomaineAssocie>das,DomaineAssocie domaineAssocie){
    for (DomaineAssocie da : das) {
        if(da.getNom().equals(domaineAssocie.getNom())&& da.getType()==domaineAssocie.getType()&& da.getDomaine().equals(domaineAssocie.getDomaine())){
            return -1;
        }
        
    }
    return 1;
}

public int findByDomaineAndNom(DomaineAssocie domaineAssocie){
    List<DomaineAssocie> domaineAssocies = em.createQuery("SELECT da FROM DomaineAssocie da WHERE da.domaine.id = " + domaineAssocie.getDomaine().getId() + 
            " AND da.nom = '" + domaineAssocie.getNom() + "' AND da.type = '" + domaineAssocie.getType() + "'").getResultList();
    if(domaineAssocies.isEmpty()){
        return 1;
    }
    return -1;
}
}
