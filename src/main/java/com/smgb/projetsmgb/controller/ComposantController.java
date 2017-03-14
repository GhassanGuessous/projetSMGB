package com.smgb.projetsmgb.controller;

import com.smgb.projetsmgb.bean.Composant;
import com.smgb.projetsmgb.bean.Input;
import com.smgb.projetsmgb.bean.Output;
import com.smgb.projetsmgb.bean.ProvideInterface;
import com.smgb.projetsmgb.bean.ProvideInterfaceItem;
import com.smgb.projetsmgb.controller.util.JsfUtil;
import com.smgb.projetsmgb.controller.util.JsfUtil.PersistAction;
import com.smgb.projetsmgb.service.ComposantFacade;

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

@Named("composantController")
@SessionScoped
public class ComposantController implements Serializable {

    @EJB
    private com.smgb.projetsmgb.service.ComposantFacade ejbFacade;
    @EJB
    private com.smgb.projetsmgb.service.ProvideInterfaceItemFacade provideInterfaceItemFacade;
    @EJB
    private com.smgb.projetsmgb.service.InputFacade inputFacade;
    private List<Composant> items = null;
    private Composant selected;

    private ProvideInterfaceItem provideInterfaceItem;
    private String provideInterfaceItemNom;
    private Input input;
    private Output output = new Output();
    private String inputNom;
    private String outputNom;
    private String inputType;
    private String outputType;
    private String nom;
    

    public ComposantController() {
    }

    public Composant getSelected() {
        if (selected == null) {
            selected = new Composant();
        }
        return selected;
    }

    public void setSelected(Composant selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ComposantFacade getFacade() {
        return ejbFacade;
    }

    public void save() {

        provideInterfaceItem.setNom(provideInterfaceItemNom);
        input.setNom(inputNom);
        input.setType(inputType);
        output.setNom(outputNom);
        output.setType(outputType);
        int res = ejbFacade.save(selected, provideInterfaceItem, input, output);
        if (res < 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cet input existe deja pour cette provideIntefaceItem !"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'input a été créé avec succes."));
        }
    }

    public void findProvideInterfaceItems() {
        ProvideInterface provideInterface = new ProvideInterface();
        selected.setProvideInterface(provideInterface);
        List<ProvideInterfaceItem> provideInterfaceItems = provideInterfaceItemFacade.findProvideInterfaceItemByComposant(nom);
        if (provideInterfaceItems != null) {
            selected.getProvideInterface().setProvideInterfaceItems(provideInterfaceItems);
        }
//        else{
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucune privideInterfaceItem pour ce composant !"));
//        }
    }

    public void findInputsByProvideInterfaceItem(ProvideInterfaceItem provideInterfaceItem) {
        provideInterfaceItem.setInputs(inputFacade.findInputsByProvideInterfaceItem(provideInterfaceItem));
    }

    public Composant prepareCreate() {
        selected = new Composant();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ComposantCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ComposantUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ComposantDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Composant> getItems() {
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

    public Composant getComposant(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Composant> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Composant> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Composant.class)
    public static class ComposantControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ComposantController controller = (ComposantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "composantController");
            return controller.getComposant(getKey(value));
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
            if (object instanceof Composant) {
                Composant o = (Composant) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Composant.class.getName()});
                return null;
            }
        }

    }

    public ProvideInterfaceItem getProvideInterfaceItem() {
        if (provideInterfaceItem == null) {
            provideInterfaceItem = new ProvideInterfaceItem();
        }
        return provideInterfaceItem;
    }

    public void setProvideInterfaceItem(ProvideInterfaceItem provideInterfaceItem) {
        this.provideInterfaceItem = provideInterfaceItem;
    }

    public String getInputNom() {
        return inputNom;
    }

    public void setInputNom(String inputNom) {
        this.inputNom = inputNom;
    }

    public String getOutputNom() {
        return outputNom;
    }

    public void setOutputNom(String outputNom) {
        this.outputNom = outputNom;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getProvideInterfaceItemNom() {
        return provideInterfaceItemNom;
    }

    public void setProvideInterfaceItemNom(String provideInterfaceItemNom) {
        this.provideInterfaceItemNom = provideInterfaceItemNom;
    }

    public Input getInput() {
        if (input == null) {
            input = new Input();
        }
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public Output getOutput() {
//        if(output == null){
//            output = new Output();
//        }
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

}
