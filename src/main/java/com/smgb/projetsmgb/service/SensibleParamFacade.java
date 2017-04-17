/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.service;


import com.smgb.projetsmgb.bean.ContexteElement;
import com.smgb.projetsmgb.bean.SensibleParam;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ghassan
 */
@Stateless
public class SensibleParamFacade extends AbstractFacade<SensibleParam> {

    @PersistenceContext(unitName = "com.SMGB_projetSMGB_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SensibleParamFacade() {
        super(SensibleParam.class);
    }
    
    public Object[] findByNomAndContexteElement(SensibleParam sensibleParam, ContexteElement contexteElement) {
        String requete = "SELECT sp FROM SensibleParam sp WHERE sp.contexteElement.id =" + contexteElement.getId() + " AND sp.nom = '" + sensibleParam.getNom() + "'";
        List<ContexteElement> sensibleParams = em.createQuery(requete).getResultList();
        if (!sensibleParams.isEmpty()) {
            return new Object[]{1, sensibleParams.get(0)};
        } else {
            sensibleParam.setContexteElement(contexteElement);
            return new Object[]{-1, sensibleParam};
        }
    }
    
    public void clone(SensibleParam sensibleParamSource, SensibleParam sensibleParamDestination) {
        
        sensibleParamDestination.setContexteElement(sensibleParamSource.getContexteElement());
        sensibleParamDestination.setNom(sensibleParamSource.getNom());
        
    }

    public SensibleParam clone(SensibleParam sensibleParam) {
        SensibleParam cloned = new SensibleParam();
        clone(sensibleParam, cloned);
        return cloned;
    }
    
     public List<SensibleParam> findByContexteElement(ContexteElement contexteElement){
        String requete = "";
        if(contexteElement !=null){
        requete  +=  "SELECT sp FROM SensibleParam sp WHERE sp.contexteElement.id="+contexteElement.getId();
        }
        return em.createQuery(requete).getResultList();
        }
    
     public List<SensibleParam> distinctSensibleParam(){
         String rq = "SELECT DISTINCT sp FROM SensibleParam sp";
         return em.createQuery(rq).getResultList();
     }

    public int findSensibleParam(List<SensibleParam> itemsParam, SensibleParam selectedSensibleParam) {
        
        for(SensibleParam item : itemsParam){
            if(item.getNom().equals(selectedSensibleParam.getNom()) && item.getContexteElement().getNom().equals(selectedSensibleParam.getContexteElement().getNom())){
                    return -1;
               
            }
        }
        return 1;
    }
}
