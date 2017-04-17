/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;

import com.smgb.projetsmgb.bean.Contexte;
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
public class ContexteFacade extends AbstractFacade<Contexte> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    private com.smgb.projetsmgb.service.ContexteElementFacade contexteElementFacade;
    @EJB
    private com.smgb.projetsmgb.service.SensibleParamFacade sensibleParamFacade;
    @EJB
    private com.smgb.projetsmgb.service.SensibleValeurFacade sensibleValeurFacade;
    
    
    public Object[] findByNom(Contexte contexte) {

        String requete = "SELECT c FROM Contexte c WHERE c.nom= '" + contexte.getNom() + "'";
        List<Contexte> contextes = em.createQuery(requete).getResultList();

        if (!contextes.isEmpty()) {
            return new Object[]{1, contextes.get(0)};
        } else {
            create(contexte);
            return new Object[]{-1, contexte};
        }
    }
    
    
  /*  public int save(Contexte contexte, ContexteElement contexteElement, SensibleParam sensibleParam, SensibleValeur sensibleValeur) {

        int retour;
       
        Object[] res1 = findByNom(contexte);

        int res1_1 = (int) res1[0];

        Contexte contexte1 = (Contexte) res1[1];

        if (res1_1 > 0) {
            Object[] res2 = contexteElementFacade.findByNomAndContexte(contexte1, contexteElement);

            int res2_1 = (int) res2[0];
            ContexteElement contexteElement1 = (ContexteElement) res2[1];

            if (res2_1 > 0){
                Object[] res3 = sensibleParamFacade.findByNomAndContexteElement(sensibleParam, contexteElement1);
                
                int res3_1 = (int) res3[0];
                SensibleParam sensibleParam1 = (SensibleParam) res3[1];
                if(res3_1>0){
                    Object[] res4 = sensibleValeurFacade.findByNomAndSensibleParam(sensibleParam1, sensibleValeur);
                    
                    int res4_1 = (int) res4[0];
                    SensibleValeur sensibleValeur1 = (SensibleValeur) res4[1];
                    
                    if(res4_1>0){
                        return -1;
                    } else {
                        sensibleParamFacade.create(sensibleParam);
                        sensibleValeur.setSensibleParam(sensibleParam1);
                        sensibleValeurFacade.create(sensibleValeur1);
                        return 4;
                    }
                }else{
                    sensibleParam.setContexteElement(contexteElement1);
                    sensibleParamFacade.create(sensibleParam);
                    sensibleValeur.setSensibleParam(sensibleParam);
                    sensibleValeurFacade.create(sensibleValeur);
                    retour = 3;
                }
            } else {
                contexteElement.setContexte(contexte1);
                contexteElementFacade.create(contexteElement1);
                sensibleParam.setContexteElement(contexteElement1);
                sensibleParamFacade.create(sensibleParam);
                sensibleValeur.setSensibleParam(sensibleParam);
                sensibleValeurFacade.create(sensibleValeur);
                retour = 2;
            }

        } else {
            create(contexte1);
            contexteElement.setContexte(contexte1);
            contexteElementFacade.create(contexteElement);
            sensibleParam.setContexteElement(contexteElement);
            sensibleParamFacade.create(sensibleParam);
            sensibleValeur.setSensibleParam(sensibleParam);
            sensibleValeurFacade.create(sensibleValeur);
            retour = 1;
        }

        return retour;

    }*/
        
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContexteFacade() {
        super(Contexte.class);
    }
    
}
