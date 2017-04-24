/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Domaine;
import com.smgb.projetsmgb.bean.DomaineAssocie;
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
public class DomaineFacade extends AbstractFacade<Domaine> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
  
    @EJB
    DomaineAssocieFacade domaineAssocieFacade;
    public DomaineFacade() {
        super(Domaine.class);
    }
    public int save(List<DomaineAssocie> domaineAssocies){
        for (DomaineAssocie domaineAssocy : domaineAssocies) {
            /*int i = domaineAssocieFacade.findByDomaineAndNom(domaineAssocy);
            if(i > 0){*/
                domaineAssocieFacade.create(domaineAssocy);
             
            }
           return 1;
       
    }
    public int saveDomaine (Domaine domaine){
   List<Domaine> ds= em.createQuery("SELECT d FROM Domaine d WHERE d.nom ='"+domaine.getNom()+"'").getResultList();
         
       if(ds.isEmpty()){
           create(domaine);
           return 1;
       }
     
        return -1;
      
}
     public List<DomaineAssocie> findByDomaine (Domaine domaine) {
        return em.createQuery("SELECT ds FROM DomaineAssocie ds WHERE ds.domaine.id='" + domaine.getId() + "'").getResultList();
    }
}