package controller;

import bean.Contexte;
import bean.ContexteElement;
import bean.SensibleParam;
import bean.SensibleValeur;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.ContexteFacade;

import java.io.Serializable;
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
    private service.ContexteElementFacade contexteElementFacade;
    @EJB
    private service.SensibleParamFacade sensibleParamFacade;
    @EJB
    private service.ContexteFacade ejbFacade;
    @EJB
    private service.SensibleValeurFacade sensibleValeurFacade;
    private List<Contexte> items = null;
    private Contexte selected = new Contexte();
    private ContexteElement selectedElement = new ContexteElement();
    private SensibleParam selectedSensibleParam = new SensibleParam();
    private SensibleValeur selectedsensibleValeur = new SensibleValeur();
    private String contextElementNom;
    private String sensibleParamNom;
    private String sensibleValeurNom;
    
    
    public void save() {

        selectedElement.setNom(contextElementNom);
        selectedSensibleParam.setNom(sensibleParamNom);
        selectedsensibleValeur.setNom(sensibleValeurNom);
        
        int res = ejbFacade.save(selected, selectedElement, selectedSensibleParam, selectedsensibleValeur);
        items = ejbFacade.findAll();
        if(res < 0){

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!","Cette sensible valeur existe déjà pour ce contexte element !"));
       
        }else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", " Le contexte a été créé avec succes"));
        
        }
    }
    
    public void findBySensibleParam(SensibleParam sensibleParam){
        
        if(sensibleParam != null){
            selectedSensibleParam.setSensibleValeur(sensibleValeurFacade.findBySensibleParam(sensibleParam));
        }
    }
    

     public void findByContexteElement(ContexteElement contexteElement){
        
        if(contexteElement != null){
            selectedElement.setSensibleParams(sensibleParamFacade.findByContexteElement(contexteElement));
        }
    }

     public void findByContexte(Contexte contexte){
        if(contexte != null){
        selected.setContexteElements(contexteElementFacade.findByContexte(contexte));
        }
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

    public String getContextElementNom() {
        return contextElementNom;
    }

    public void setContextElementNom(String contextElementNom) {
        this.contextElementNom = contextElementNom;
    }

    public String getSensibleParamNom() {
        return sensibleParamNom;
    }

    public void setSensibleParamNom(String sensibleParamNom) {
        this.sensibleParamNom = sensibleParamNom;
    }

    public String getSensibleValeurNom() {
        return sensibleValeurNom;
    }

    public void setSensibleValeurNom(String sensibleValeurNom) {
        this.sensibleValeurNom = sensibleValeurNom;
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

    public SensibleValeur getSelectedsensibleValeur() {
        if(selectedsensibleValeur == null){
            selectedsensibleValeur = new SensibleValeur();
        }
        return selectedsensibleValeur;
    }

    public void setSelectedsensibleValeur(SensibleValeur selectedsensibleValeur) {
        this.selectedsensibleValeur = selectedsensibleValeur;
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

    public List<Contexte> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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
