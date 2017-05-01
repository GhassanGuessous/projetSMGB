package com.smgb.projetsmgb.controller;

import com.smgb.projetsmgb.bean.User;
import com.smgb.projetsmgb.controller.util.DeviceUtil;
import com.smgb.projetsmgb.controller.util.HashageUtil;
import com.smgb.projetsmgb.controller.util.SessionUtil;
import com.smgb.projetsmgb.service.UserFacade;
import com.smgb.projetsmgb.controller.util.JsfUtil;
import com.smgb.projetsmgb.controller.util.JsfUtil.PersistAction;
import java.io.IOException;

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

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private com.smgb.projetsmgb.service.UserFacade ejbFacade;
    private List<User> items = null;
    private User selected;
    
    private String changePassword;
    private String changeRepetePassword;

    public UserController() {
    }

    public User getSelected() {
        if(selected == null){
            selected = new User();
            return selected;
        }
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }
    
    public User getCurrentUser(){
        return SessionUtil.getConnectedUser();
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public User prepareCreate() {
        selected = new User();
        initializeEmbeddableKey();
        return selected;
    }
    
    public String seConnecter() throws IOException {
        System.out.println(""+selected);
        int res = getFacade().seConnecter(selected, DeviceUtil.getDevice());
        if (res == -1) {
            return "/verification?faces-redirect=true";
        } else if (res > 0) {
            if (SessionUtil.getConnectedUser().isMdpChanged()==false) {
                return "/index?faces-redirect=true";
            } else {
                return "/user/ChangePassword?faces-redirect=true";
                //SessionUtil.redirect("/projetSMGB/faces/user/ChangePassword");
                
            }
        }
        messageMeothde(res);
        return null;
    }
    
    public void messageMeothde(int res){
        if(res == -2){
            JsfUtil.addErrorMessage("bloqué");
        }else if(res == -3){
             JsfUtil.addErrorMessage("mdp incorrect");
        }else if(res == -4){
             JsfUtil.addErrorMessage("introuvable");
        }
    }
    
    public String changePassword() {
        if (changePassword.equals(changeRepetePassword) && !changePassword.equals("") && changePassword != null) {
            User user = ejbFacade.find(selected.getLogin());
            user.setPassword(HashageUtil.sha256(changePassword));
            user.setMdpChanged(true);
            ejbFacade.edit(user);
            return seDeConnnecter();
        }
        return "";
    }
    
    public String seDeConnnecter(){
        ejbFacade.seDeconnecter();
        return "/Authentification.xhtml?faces-redirect=true";
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items.add(getFacade().clone(selected));    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<User> getItems() {
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
//                    int res = ejbFacade.addUser(selected);
                    selected.setPassword(HashageUtil.sha256(selected.getPassword()));
                    getFacade().edit(selected);
//                    if(res<0){
//                        JsfUtil.addErrorMessage("entrer convenablement votre login");
//                    }
                
                } 
                else {
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

    public User getUser(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<User> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<User> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key; 
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getLogin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }

    }

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }

    public String getChangeRepetePassword() {
        return changeRepetePassword;
    }

    public void setChangeRepetePassword(String changeRepetePassword) {
        this.changeRepetePassword = changeRepetePassword;
    }
    
    

}
