/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.controller;

import com.smgb.projetsmgb.bean.Action;
import com.smgb.projetsmgb.bean.Contexte;
import com.smgb.projetsmgb.service.ActionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ghassan
 */
@Named(value = "actionController")
@SessionScoped
public class ActionController implements Serializable {

    @EJB
    private com.smgb.projetsmgb.service.ActionFacade ejbFacade;
    private List<Contexte> items = null;
    private Contexte selected;

    /**
     * Creates a new instance of ActionController
     */
    public ActionController() {
    }

    public ActionFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ActionFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Contexte> getItems() {
        return items;
    }

    public void setItems(List<Contexte> items) {
        this.items = items;
    }

    public Contexte getSelected() {
        return selected;
    }

    public void setSelected(Contexte selected) {
        this.selected = selected;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ActionFacade getFacade() {
        return ejbFacade;
    }
    
    public Action getAction(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Action> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Action> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Action.class)
    public static class ActionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ActionController controller = (ActionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "actionController");
            return controller.getAction(getKey(value));
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
            if (object instanceof Action) {
                Action o = (Action) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Action.class.getName()});
                return null;
            }
        }

    }

}
