/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ContexteElement;
import bean.SensibleParam;
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
            return new Object[]{-1, sensibleParam};
        }
    }
    
     public List<SensibleParam> findByContexteElement(ContexteElement contexteElement){
        String requete = "";
        if(contexteElement !=null){
        requete  +=  "SELECT sp FROM SensibleParam sp WHERE sp.contexteElement.id="+contexteElement.getId();
        }
        return em.createQuery(requete).getResultList();
        }
    
}
