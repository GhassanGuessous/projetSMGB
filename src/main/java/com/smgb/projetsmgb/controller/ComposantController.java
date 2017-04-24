
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

@Named("composantController")
@SessionScoped
public class ComposantController implements Serializable {

    @EJB
    private com.smgb.projetsmgb.service.ComposantFacade ejbFacade;
    @EJB
    private com.smgb.projetsmgb.service.ProvideInterfaceItemFacade provideInterfaceItemFacade;
    @EJB
    private com.smgb.projetsmgb.service.InputFacade inputFacade;
    @EJB
    private com.smgb.projetsmgb.service.OutputFacade outputFacade;
    private List<Composant> items = null;
    private Composant selected;
    private ProvideInterface provideInterface;
    private ProvideInterfaceItem provideInterfaceItem;
    private ProvideInterfaceItem selectedProvideInterfaceItem;
    private List<ProvideInterfaceItem> provideInterfaceItems = new ArrayList();
    private Input input;
    private List<Input> inputs = new ArrayList();
    private Output output = new Output();

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

    public void saveComposant() {
        Object[] res = ejbFacade.findByDomaineAndNom(selected);
        int res1 = (int) res[0];
        selected = (Composant) res[1];
        if (res1 < 0) {
            Message("Composant", "Composant a ete cree avec success");
        } else {
            Message("Composant", "Composant existant. Voulez vous ajouter d'autre pii ?");
        }
    }

    public void addProvideInterfaceItem() {
        provideInterfaceItem.setProvideInterface(selected.getProvideInterface());
        provideInterfaceItem.setOutput(outputFacade.clone(output));
        int res = provideInterfaceItemFacade.findProvideInterfaceItem(provideInterfaceItems, provideInterfaceItem);
        if (res > 0) {
            getProvideInterfaceItems().add(provideInterfaceItemFacade.clone(provideInterfaceItem));
        } else {
            Message("ProvideInterfaceItem deja ajoutée a la liste", "");
        }
    }

    public void addInput() {
        input.setProvideInterfaceItem(selectedProvideInterfaceItem);
        int res = inputFacade.findInput(inputs, input);
        if (res > 0) {
            getInputs().add(inputFacade.clone(input));
        }else{
            Message("Input deja ajouté a la liste", "");
        }
    }

    public void save() {
        provideInterfaceItemFacade.save(provideInterfaceItems, inputs);
        provideInterfaceItems = new ArrayList<>();
        inputs = new ArrayList<>();
        Message("Success", "");
    }

    public void findInputsByProvideInterfaceItem(ProvideInterfaceItem provideInterfaceItem) {
        selectedProvideInterfaceItem = provideInterfaceItem;
        selectedProvideInterfaceItem.setInputs(inputFacade.findInputsByProvideInterfaceItem(provideInterfaceItem));
    }

    public void findProvideInterfaceItemByComposant(Composant composant) {
        selected = composant;
        selected.getProvideInterface().setProvideInterfaceItems(provideInterfaceItemFacade.findProvideInterfaceItemByComposant(selected));
    }
    
    public String add(Composant composant){
        selected = composant;
        provideInterfaceItem = new ProvideInterfaceItem();
        output = new Output();
        return "List";
    }
    
    public String add2(ProvideInterfaceItem pii){
        provideInterfaceItem = pii;
        provideInterface = provideInterfaceItem.getProvideInterface();
        selected = provideInterface.getComposant();
        output = provideInterfaceItem.getOutput();
        return "List";
    }
    
    public void refresh1(){
        selected = new Composant();
        refresh2();
    }
    
    public void refresh2(){
        provideInterfaceItem = new ProvideInterfaceItem();
        output = new Output();
    }

    public void Message(String msg1, String msg2) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg1, msg2));
    }

    public Composant prepareCreate() {
        selected = new Composant();
        initializeEmbeddableKey();
        return selected;
    }
    
    public Input prepareCreateInput(ProvideInterfaceItem provideInterfaceItem) {
        selectedProvideInterfaceItem = provideInterfaceItem;
        input = new Input();
        initializeEmbeddableKey();
        return input;
    }
    
    public String versList1(){
        selected = new Composant();
        provideInterface = new ProvideInterface();
        provideInterfaceItem = new ProvideInterfaceItem();
        output = new Output();
        return "List";
    }
    public String versList2(){
        selected = new Composant();
        return "List2";
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

    public List<ProvideInterfaceItem> getProvideInterfaceItems() {
        return provideInterfaceItems;
    }

    public void setProvideInterfaceItems(List<ProvideInterfaceItem> provideInterfaceItems) {
        this.provideInterfaceItems = provideInterfaceItems;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public ProvideInterfaceItem getSelectedProvideInterfaceItem() {
        return selectedProvideInterfaceItem;
    }

    public void setSelectedProvideInterfaceItem(ProvideInterfaceItem selectedProvideInterfaceItem) {
        this.selectedProvideInterfaceItem = selectedProvideInterfaceItem;
    }

    public ProvideInterface getProvideInterface() {
        return provideInterface;
    }

    public void setProvideInterface(ProvideInterface provideInterface) {
        this.provideInterface = provideInterface;
    }

}
