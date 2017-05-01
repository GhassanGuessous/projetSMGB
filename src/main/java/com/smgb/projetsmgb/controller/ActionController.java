/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.controller;

import com.smgb.projetsmgb.bean.Action;
import com.smgb.projetsmgb.bean.Domaine;
import com.smgb.projetsmgb.bean.Goal;
import com.smgb.projetsmgb.bean.Processus;
import com.smgb.projetsmgb.bean.ProvideInterfaceItem;
import com.smgb.projetsmgb.bean.Step;
import com.smgb.projetsmgb.service.ActionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
    @EJB
    private com.smgb.projetsmgb.service.GoalFacade goalFacade;
    @EJB
    private com.smgb.projetsmgb.service.ProcessusFacade processusFacade;
    @EJB
    private com.smgb.projetsmgb.service.StepFacade stepFacade;
    @EJB
    private com.smgb.projetsmgb.service.ProvideInterfaceItemFacade provideInterfaceItemFacade;
    @EJB
    private com.smgb.projetsmgb.service.DomaineFacade domaineFacade;
    private List<Action> items = null;
    private Action selected;

    private Goal goal = new Goal();
    private Domaine domaine;
    private Processus processus;
    private Processus selectedProcessus; //list
    private Step step;
    private Step selectedStep;

    private List<Processus> processuses = new ArrayList();
    private List<Step> steps = new ArrayList();
    private List<Domaine> domaines;
    private List<ProvideInterfaceItem> provideInterfaceItems;
    
    /**
     * Creates a new instance of ActionController
     */
    public ActionController() {
    }

    public List<Action> getItems() {
        return items;
    }

    public void setItems(List<Action> items) {
        this.items = items;
    }

    public Action getSelected() {
        if (selected == null) {
            selected = new Action();
        }
        return selected;
    }

    public void setSelected(Action selected) {
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

    public void saveGoal() {
        selected.setGoal(goal);
        int i = goalFacade.saveGoal(goal, selected);
        if (i < 0) {
            message("", "Existent Deja ! voulez-vous ajouter des processus?");
        } else {
            message("Success", "continuez !");
        }
    }

    public void addProcessus() {
        processus.setAction(selected);
        int res = processusFacade.verifierListeProcessus(processuses, processus);
        if (res > 0) {
            processuses.add(processusFacade.clone(processus));
        } else {
            message("Processus deja ajouté a la liste", "");
        }
    }

    public void addStep() {
        step.setProcessus(selectedProcessus);
        int res = stepFacade.verifierListeStep(steps, step);
        if (res == -1) {
            message("Step deja ajoutée a la liste !", "");
        } else if (res == -2 || res == -3) {
            message("ProvideInterfaceItem deja prise !", "");
        } else {
            steps.add(stepFacade.clone(step));
        }

    }

    public void versContrainte() {
        if (!steps.isEmpty()) {
            processusFacade.save(processuses, steps);
            processuses = new ArrayList<>();
            steps = new ArrayList<>();
            message("Success", "");
        }else{
            message("La liste des Steps est vide !", "");
        }
    }
    
    public List<Domaine> findDomaines() {
        domaines = domaineFacade.findAll();
        return domaines;
    }

    public Step prepareCreate(Processus processus) {
        selectedProcessus = processus;
        step = new Step();
        initializeEmbeddableKey();
        return step;
    }

    public void message(String msg1, String msg2) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg1, msg2));
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

    public Goal getGoal() {
        if (goal == null) {
            goal = new Goal();
        }
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Domaine getDomaine() {
        if (domaine == null) {
            domaine = new Domaine();
        }
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public Processus getProcessus() {
        if (processus == null) {
            processus = new Processus();
        }
        return processus;
    }

    public void setProcessus(Processus processus) {
        this.processus = processus;
    }

    public Step getStep() {
        if (step == null) {
            step = new Step();
        }
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public List<Processus> getProcessuses() {
        return processuses;
    }

    public void setProcessuses(List<Processus> processuses) {
        this.processuses = processuses;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Domaine> getDomaines() {
        return domaines;
    }

    public void setDomaines(List<Domaine> domaines) {
        this.domaines = domaines;
    }

    public Processus getSelectedProcessus() {
        return selectedProcessus;
    }

    public void setSelectedProcessus(Processus selectedProcessus) {
        this.selectedProcessus = selectedProcessus;
    }

    public Step getSelectedStep() {
        return selectedStep;
    }

    public void setSelectedStep(Step selectedStep) {
        this.selectedStep = selectedStep;
    }

    public List<ProvideInterfaceItem> getProvideInterfaceItems() {
        provideInterfaceItems = provideInterfaceItemFacade.findAll();
        return provideInterfaceItems;
    }

    public void setProvideInterfaceItems(List<ProvideInterfaceItem> provideInterfaceItems) {
        this.provideInterfaceItems = provideInterfaceItems;
    }

}
