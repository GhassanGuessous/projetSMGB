/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;
import com.smgb.projetsmgb.bean.Contexte;
import com.smgb.projetsmgb.bean.ContexteElement;
import com.smgb.projetsmgb.bean.SensibleParam;
import com.smgb.projetsmgb.bean.SensibleValeur;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class ContexteElementFacade extends AbstractFacade<ContexteElement> {

    @EJB
    private com.smgb.projetsmgb.service.SensibleParamFacade sensibleParamFacade;
    @EJB
    private com.smgb.projetsmgb.service.ContexteFacade contexteFacade;
    @EJB
    private com.smgb.projetsmgb.service.SensibleValeurFacade sensibleValeurFacade;

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Object[] findByNomAndContexte(ContexteElement contexteElement) {
        String requete = "SELECT ce FROM ContexteElement ce WHERE ce.contexte.id =" + contexteElement.getContexte().getId() + " AND ce.nom = '" + contexteElement.getNom() + "'";
        List<ContexteElement> contexteElements = em.createQuery(requete).getResultList();
        if (!contexteElements.isEmpty()) {
            return new Object[]{1, contexteElements.get(0)};
        } else {
            return new Object[]{-1, contexteElement};
        }
    }

    public List<ContexteElement> findByContexte(Contexte contexte) {
        String requete = "";
        if (contexte != null) {
            requete += "SELECT ce FROM ContexteElement ce WHERE ce.contexte.id=" + contexte.getId();
        }
        return em.createQuery(requete).getResultList();
    }

    public void clone(ContexteElement contexteElementSource, ContexteElement contexteElementDestination) {
        contexteElementDestination.setNom(contexteElementSource.getNom());
        contexteElementDestination.setContexte(contexteElementSource.getContexte());
    }

    public ContexteElement clone(ContexteElement contexteElement) {
        ContexteElement cloned = new ContexteElement();
        clone(contexteElement, cloned);
        return cloned;
    }

    public ContexteElementFacade() {
        super(ContexteElement.class);
    }

    public int findContexteElement(List<ContexteElement> itemsElement, ContexteElement contexteElement) {

        for (ContexteElement item : itemsElement) {
            if (item.getNom().equals(contexteElement.getNom()) && item.getId() == contexteElement.getId()) {
                return -1;
            }
        }
        return 1;
    }

    public void save(List<ContexteElement> itemsElement, List<SensibleParam> itemsParam, List<SensibleValeur> itemsValeur) {

        List<SensibleParam> params = new ArrayList();
        SensibleValeur sensibleValeur = new SensibleValeur();
      
        for (ContexteElement item : itemsElement) {

            for (SensibleParam sensibleParam : itemsParam) {
                if (sensibleParam.getContexteElement().getNom().equals(item.getNom())) {
                    params.add(sensibleParam);
                }
            }

            Object[] res = findByNomAndContexte(item);
            int res1 = (int) res[0];
            if (res1 < 0) {
                Long i = generateId("ContexteElement", "id");
                item.setId(i);
                create(item);
                
                for (SensibleParam param : params) {
                    
                    for(SensibleValeur valeur : itemsValeur){
                        if(valeur.getSensibleParam().getNom().equals(param.getNom())){
                            sensibleValeur = valeur;
                        }
                    }
                    param.setContexteElement(item);
                    Long j = sensibleParamFacade.generateId("SensibleParam", "id");
                    param.setId(j);
                    sensibleParamFacade.create(param);
                    sensibleValeur.setSensibleParam(param);
                    sensibleValeurFacade.create(sensibleValeur);

                }

                params = new ArrayList();
                sensibleValeur = new SensibleValeur();

            } else {

                ContexteElement contexteElement = (ContexteElement) res[1];

                for (SensibleParam param : params) {
                    
                    Object[] resParam = sensibleParamFacade.findByNomAndContexteElement(param, contexteElement);

                    int resParam1 = (int) resParam[0];

                    if (resParam1 < 0) {
                        
                        for (SensibleValeur valeur : itemsValeur) {
                            if (valeur.getSensibleParam().getNom().equals(param.getNom())) {
                                sensibleValeur = valeur;
                            }
                        }

                        Long i = sensibleParamFacade.generateId("SensibleParam", "id");
                        param.setId(i);
                        sensibleParamFacade.create(param);
                        sensibleValeur.setSensibleParam(param);
                        sensibleValeurFacade.create(sensibleValeur);
                        sensibleValeur = new SensibleValeur();
                    }
                }
                params = new ArrayList();
            }
        }
    }

}
