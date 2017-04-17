package com.smgb.projetsmgb.controller;

import com.smgb.projetsmgb.bean.Contexte;
import com.smgb.projetsmgb.bean.ContexteElement;
import com.smgb.projetsmgb.bean.SensibleParam;
import com.smgb.projetsmgb.bean.SensibleValeur;
import com.smgb.projetsmgb.service.ContexteFacade;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import com.smgb.projetsmgb.service.ContexteFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("contexteController")
@SessionScoped
public class ContexteController implements Serializable {
    
    @EJB
    private com.smgb.projetsmgb.service.ContexteElementFacade contexteElementFacade;
    @EJB
    private com.smgb.projetsmgb.service.SensibleParamFacade sensibleParamFacade;
    @EJB
    private com.smgb.projetsmgb.service.ContexteFacade ejbFacade;
    @EJB
    private com.smgb.projetsmgb.service.SensibleValeurFacade sensibleValeurFacade;
    private List<Contexte> items = null;
    private List<SensibleParam> itemsParam = new ArrayList();
    private List<ContexteElement> itemsElement = new ArrayList();
    private List<SensibleValeur> itemsValeur = new ArrayList();
    private Contexte selected = new Contexte();
    private ContexteElement selectedElement = new ContexteElement();
    private ContexteElement selectedElement2 = new ContexteElement();
    private ContexteElement contexteElement;
    private SensibleParam   selectedSensibleParam = new SensibleParam();
    private SensibleParam   sensibleParam;
    private SensibleValeur  selectedsensibleValeur = new SensibleValeur();
    private SensibleValeur  sensibleValeur;
    
    
    
    
    public void save(){
        contexteElementFacade.save(itemsElement, itemsParam, itemsValeur);
        itemsElement = new ArrayList<>();
        itemsParam = new ArrayList<>();
        itemsValeur = new ArrayList<>();
        Message("Tout est bien crée", "");
    }
    
    public void saveContexte(){
        Object[] res = ejbFacade.findByNom(selected);
        int res1 = (int) res[0];
        selected = (Contexte) res[1];
        if (res1 < 0) {
            Message("Contexte", "Contexte a été crée avec succes");
        } else {
            Message("Contexte", "Contexte existant. Ajoutez d'autres contexte elements !");
        }
    }
    
    public void saveContexteElement(){
        contexteElement.setContexte(selected);
        int res = contexteElementFacade.findContexteElement(itemsElement, contexteElement);
        if (res > 0) {
            getItemsElement().add(contexteElementFacade.clone(contexteElement));
        } else {
            Message("Contexte Element deja ajoutée a la liste", "");
        }
    }
    
    public void addSensibleParamValeur(){
         
     
           sensibleParam.setContexteElement(selectedElement);
           sensibleValeur.setSensibleParam(sensibleParam);
           int res = sensibleParamFacade.findSensibleParam(itemsParam, sensibleParam);
           int res1 = sensibleValeurFacade.findSensibleValeur(itemsValeur, sensibleValeur);
    
         if (res > 0 && res1>0) {

                 getItemsParam().add(sensibleParamFacade.clone(sensibleParam));
                 getItemsValeur().add(sensibleValeurFacade.clone(sensibleValeur));
                 sensibleValeur = new SensibleValeur();
                 sensibleParam = new SensibleParam();
                     
        } else {
            
                
                 Message("Cette sensible param existe déjà dans la liste", "");
            
            
        }
    }
    public void findBySensibleParam(SensibleParam sensibleParam){
        
        if(sensibleParam != null){
            selectedSensibleParam.setSensibleValeur(sensibleValeurFacade.findBySensibleParam(sensibleParam));
        }
    }
    

     public void findByContexteElement(ContexteElement contexteElement){
        
        if(contexteElement != null){
            selectedElement2.setSensibleParams(sensibleParamFacade.findByContexteElement(contexteElement));
        }
    }

     public void findByContexte(Contexte contexte){
        if(contexte != null){
        selected.setContexteElements(contexteElementFacade.findByContexte(contexte));
        }
    }
     
    public void Message(String msg1, String msg2) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg1, msg2));
    }

    public ContexteController() {
    }

    public Contexte getSelected() {
        if(selected ==null){
            selected = new Contexte();
        }
        return selected;
    }

    public void setSelected(Contexte selected) {
        this.selected = selected;
    }

    public SensibleValeur getSelectedsensibleValeur() {
        
        if(selectedsensibleValeur == null){
            selectedsensibleValeur = new SensibleValeur();
        }
        return selectedsensibleValeur;
    }

    public void setSelectedsensibleValeur(SensibleValeur selectedsensibleValeur) {
        this.selectedsensibleValeur = selectedsensibleValeur;
    }

    public ContexteElement getSelectedElement2() {
        
        if(selectedElement2 == null){
            selectedElement2 = new ContexteElement();
        }
        return selectedElement2;
    }

    public void setSelectedElement2(ContexteElement selectedElement2) {
        this.selectedElement2 = selectedElement2;
    }
    
    

    public SensibleValeur getSensibleValeur() {
        if(sensibleValeur == null){
            sensibleValeur = new SensibleValeur();
        }
        return sensibleValeur;
    }

    public void setSensibleValeur(SensibleValeur sensibleValeur) {
        this.sensibleValeur = sensibleValeur;
    }
    
    public SensibleParam getSelectedSensibleParam() {
        
        if(selectedSensibleParam == null){
            selectedSensibleParam = new SensibleParam();
        }
        return selectedSensibleParam;
    }

    public void setSelectedSensibleParam(SensibleParam selectedSensibleParam) {
        this.selectedSensibleParam = selectedSensibleParam;
    }

    public ContexteElement getContexteElement() {
        if(contexteElement == null){
            contexteElement = new ContexteElement();
        }
        return contexteElement;
    }

    public void setContexteElement(ContexteElement contexteElement) {
        this.contexteElement = contexteElement;
    }
    
    

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContexteFacade getFacade() {
        return ejbFacade;
    }

    public Contexte prepareCreate() {
        selected = new Contexte();
        initializeEmbeddableKey();
        return selected;
    }
    

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContexteCreated"));
        if (!JsfUtil.isValidationFailed()) {
           items.add(selected);   // Invalidate list of items to trigger re-query
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContexteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContexteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ContexteElement> getItemsElement() {
        if (itemsElement == null) {
            itemsElement = new ArrayList();
        }
        return itemsElement;
    }

    public void setItemsElement(List<ContexteElement> itemsElement) {
        this.itemsElement = itemsElement;
    }

    public List<Contexte> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<SensibleParam> getItemsParam() {
      if (itemsParam == null) {
            itemsParam = new ArrayList<>();
        }
        return itemsParam;
    }

    public void setItemsParam(List<SensibleParam> itemsParam) {
        this.itemsParam = itemsParam;
    }

    public List<SensibleValeur> getItemsValeur() {
         if (itemsValeur.isEmpty()) {
            itemsValeur = new ArrayList<>();
        }
        return itemsValeur;
    }

    public void setItemsValeur(List<SensibleValeur> itemsValeur) {
        this.itemsValeur = itemsValeur;
    }
    
    

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
                
                
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Contexte getContexte(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Contexte> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Contexte> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public ContexteElement getSelectedElement() {
        
        if(selectedElement == null){
            selectedElement = new ContexteElement();
        }
        return selectedElement;
    }

    public SensibleParam getSensibleParam() {
        if(sensibleParam == null){
            sensibleParam = new SensibleParam();
        }
        return sensibleParam;
    }

    public void setSensibleParam(SensibleParam sensibleParam) {
        this.sensibleParam = sensibleParam;
    }
    

    public void setSelectedElement(ContexteElement selectedElement) {
        this.selectedElement = selectedElement;
    }

    @FacesConverter(forClass = Contexte.class)
    public static class ContexteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContexteController controller = (ContexteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contexteController");
            return controller.getContexte(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Contexte) {
                Contexte o = (Contexte) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contexte.class.getName()});
                return null;
            }
        }

    }
    

}
