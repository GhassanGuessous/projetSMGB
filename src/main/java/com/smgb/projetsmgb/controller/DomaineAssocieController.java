package com.smgb.projetsmgb.controller;

import com.smgb.projetsmgb.bean.DomaineAssocie;
import com.smgb.projetsmgb.controller.util.JsfUtil;
import com.smgb.projetsmgb.controller.util.JsfUtil.PersistAction;
import com.smgb.projetsmgb.service.DomaineAssocieFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("domaineAssocieController")
@SessionScoped
public class DomaineAssocieController implements Serializable {

    @EJB
    private com.smgb.projetsmgb.service.DomaineAssocieFacade ejbFacade;
    private List<DomaineAssocie> items = null;
    private DomaineAssocie selected;

    public DomaineAssocieController() {
    }

    public DomaineAssocie getSelected() {
        return selected;
    }

    public void setSelected(DomaineAssocie selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DomaineAssocieFacade getFacade() {
        return ejbFacade;
    }

    public DomaineAssocie prepareCreate() {
        selected = new DomaineAssocie();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DomaineAssocieCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DomaineAssocieUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DomaineAssocieDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DomaineAssocie> getItems() {
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

    public DomaineAssocie getDomaineAssocie(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DomaineAssocie> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DomaineAssocie> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DomaineAssocie.class)
    public static class DomaineAssocieControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DomaineAssocieController controller = (DomaineAssocieController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "domaineAssocieController");
            return controller.getDomaineAssocie(getKey(value));
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
            if (object instanceof DomaineAssocie) {
                DomaineAssocie o = (DomaineAssocie) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DomaineAssocie.class.getName()});
                return null;
            }
        }

    }

}
