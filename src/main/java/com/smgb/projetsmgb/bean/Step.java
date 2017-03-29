/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ghassan
 */
@Entity
public class Step implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToOne
    private Processus processus;
    @OneToOne
    private ProvideInterfaceItem provideInterfaceItem;
    @OneToMany(mappedBy = "step")
    private List<ContrainteItem> contrainteItems;
    @OneToOne(mappedBy = "step")
    private ResultatItem resultatItem;
    private StepEnum stepEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Processus getProcessus() {
        return processus;
    }

    public void setProcessus(Processus processus) {
        this.processus = processus;
    }

    public ProvideInterfaceItem getProvideInterfaceItem() {
        return provideInterfaceItem;
    }

    public void setProvideInterfaceItem(ProvideInterfaceItem provideInterfaceItem) {
        this.provideInterfaceItem = provideInterfaceItem;
    }

    public List<ContrainteItem> getContrainteItems() {
        return contrainteItems;
    }

    public void setContrainteItems(List<ContrainteItem> contrainteItems) {
        this.contrainteItems = contrainteItems;
    }

    public ResultatItem getResultatItem() {
        return resultatItem;
    }

    public void setResultatItem(ResultatItem resultatItem) {
        this.resultatItem = resultatItem;
    }

    public StepEnum getStepEnum() {
        return stepEnum;
    }

    public void setStepEnum(StepEnum stepEnum) {
        this.stepEnum = stepEnum;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Step)) {
            return false;
        }
        Step other = (Step) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smgb.projetsmgb.bean.Step[ id=" + id + " ]";
    }
    
}
