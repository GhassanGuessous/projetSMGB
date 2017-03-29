/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.controller;

import com.smgb.projetsmgb.bean.Composant;
import com.smgb.projetsmgb.bean.Domaine;
import com.smgb.projetsmgb.bean.DomaineAssocie;
import com.smgb.projetsmgb.bean.ProvideInterfaceItem;
import com.smgb.projetsmgb.service.ComposantFacade;
import com.smgb.projetsmgb.service.DomaineAssocieFacade;
import com.smgb.projetsmgb.service.DomaineFacade;
import com.smgb.projetsmgb.service.ProvideInterfaceItemFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Ghassan
 */
@Named(value = "clientView1Controller")
@SessionScoped
public class ClientView1Controller implements Serializable {

    @EJB
    private DomaineFacade domaineFacade;
    @EJB
    private DomaineAssocieFacade domaineAssocieFacade;
    @EJB
    private ComposantFacade composantFacade;
    @EJB
    private ProvideInterfaceItemFacade provideInterfaceItemFacade;

    private Domaine domaineSelected;
    private DomaineAssocie subDomaineSelected;
    private Composant composantSelected;
    private ProvideInterfaceItem provideInterfaceItemSelected;

    private List<Domaine> domaines;
    private List<DomaineAssocie> subdoamaines;
    private List<Composant> composants;
    private List<ProvideInterfaceItem> provideInterfaceItems;
    private List<ProvideInterfaceItem> droppedProvideInterfaceItems;
    
    private TreeNode root;
    private TreeNode selectedNode1;

    public void findSubDomainesByDomaine() {
        subdoamaines = domaineAssocieFacade.findSubDomainesByDomaine(domaineSelected);
    }

    public void findComposantsBySubDomaine() {
        composants = composantFacade.findComposantsBySubDomaine(subDomaineSelected);
    }

    public void findProvideInterfaceItemsByComposant() {
//        provideInterfaceItems = provideInterfaceItemFacade.findProvideInterfaceItemByComposant(composantSelected);
        root = composantFacade.createComposant(subDomaineSelected);
    }

    public void onProvideInterfaceItemDrop(DragDropEvent ddEvent) {
        ProvideInterfaceItem provideInterfaceItem = ((ProvideInterfaceItem) ddEvent.getData());
        if (!droppedProvideInterfaceItems.contains(provideInterfaceItem)) {
            droppedProvideInterfaceItems.add(provideInterfaceItem);
            provideInterfaceItems.remove(provideInterfaceItem);
        }
    }
    
    public void onDragDrop(TreeDragDropEvent event) {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();
        int dropIndex = event.getDropIndex();
    }

    /**
     * Creates a new instance of ClientView1
     */
    public ClientView1Controller() {
    }

    @PostConstruct
    public void init() {
        droppedProvideInterfaceItems = new ArrayList<ProvideInterfaceItem>();
    }

    public Domaine getDomaineSelected() {
        return domaineSelected;
    }

    public void setDomaineSelected(Domaine domaineSelected) {
        this.domaineSelected = domaineSelected;
    }

    public DomaineAssocie getSubDomaineSelected() {
        return subDomaineSelected;
    }

    public void setSubDomaineSelected(DomaineAssocie subDomaineSelected) {
        this.subDomaineSelected = subDomaineSelected;
    }

    public Composant getComposantSelected() {
        return composantSelected;
    }

    public void setComposantSelected(Composant composantSelected) {
        this.composantSelected = composantSelected;
    }

    public List<Domaine> getDomaines() {
        return domaineFacade.findAll();
    }

    public void setDomaines(List<Domaine> domaines) {
        this.domaines = domaines;
    }

    public List<DomaineAssocie> getSubdoamaines() {
        return subdoamaines;
    }

    public void setSubdoamaines(List<DomaineAssocie> subdoamaines) {
        this.subdoamaines = subdoamaines;
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }

    public List<ProvideInterfaceItem> getProvideInterfaceItems() {
        return provideInterfaceItems;
    }

    public void setProvideInterfaceItems(List<ProvideInterfaceItem> provideInterfaceItems) {
        this.provideInterfaceItems = provideInterfaceItems;
    }

    public ProvideInterfaceItem getProvideInterfaceItemSelected() {
        return provideInterfaceItemSelected;
    }

    public void setProvideInterfaceItemSelected(ProvideInterfaceItem provideInterfaceItemSelected) {
        this.provideInterfaceItemSelected = provideInterfaceItemSelected;
    }

    public List<ProvideInterfaceItem> getDroppedProvideInterfaceItems() {
//        if(droppedProvideInterfaceItems == null){
//            droppedProvideInterfaceItems = new ArrayList<ProvideInterfaceItem>();
//        }
        return droppedProvideInterfaceItems;
    }

    public void setDroppedProvideInterfaceItems(List<ProvideInterfaceItem> droppedProvideInterfaceItems) {
        this.droppedProvideInterfaceItems = droppedProvideInterfaceItems;
    }

    public TreeNode getRoot() {
        if(root == null){
            root = new DefaultTreeNode(new Composant(), null);
        }
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode1() {
        if(selectedNode1 == null){
            selectedNode1 = new DefaultTreeNode(new Composant(), null);
        }
        return selectedNode1;
    }

    public void setSelectedNode1(TreeNode selectedNode1) {
        this.selectedNode1 = selectedNode1;
    }
    
}
