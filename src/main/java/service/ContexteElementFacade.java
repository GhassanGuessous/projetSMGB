/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Contexte;
import bean.ContexteElement;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class ContexteElementFacade extends AbstractFacade<ContexteElement> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Object[] findByNomAndContexte(Contexte contexte, ContexteElement contexteElement) {
        String requete = "SELECT ce FROM ContexteElement ce WHERE ce.contexte.id =" + contexte.getId() + " AND ce.nom = '" + contexteElement.getNom() + "'";
        List<ContexteElement> contexteElements = em.createQuery(requete).getResultList();
        if (!contexteElements.isEmpty()) {
            return new Object[]{1, contexteElements.get(0)};
        } else {
            return new Object[]{0, contexteElement};
        }
    }
    
    public List<ContexteElement> findByContexte(Contexte contexte) {
        String requete = "";
        if (contexte != null) {
            requete += "SELECT ce FROM ContexteElement ce WHERE ce.contexte.id=" + contexte.getId();
        }
        return em.createQuery(requete).getResultList();
    }


    public ContexteElementFacade() {
        super(ContexteElement.class);
    }
    
}
