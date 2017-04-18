/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smgb.projetsmgb.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ghassan
 */
@Entity
public class ContrainteItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attribut;
    private String critere;
    @ManyToOne
    private Contrainte contrainte;
    @ManyToOne
    private Step step;
    @OneToOne(mappedBy = "contrainteItem")
    private ValeurCritique valeurCritique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribut() {
        return attribut;
    }

    public void setAttribut(String attribut) {
        this.attribut = attribut;
    }

    public String getCritere() {
        return critere;
    }

    public void setCritere(String critere) {
        this.critere = critere;
    }

    public Contrainte getContrainte() {
        return contrainte;
    }

    public void setContrainte(Contrainte contrainte) {
        this.contrainte = contrainte;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public ValeurCritique getValeurCritique() {
        return valeurCritique;
    }

    public void setValeurCritique(ValeurCritique valeurCritique) {
        this.valeurCritique = valeurCritique;
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
        if (!(object instanceof ContrainteItem)) {
            return false;
        }
        ContrainteItem other = (ContrainteItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smgb.projetsmgb.bean.ContrainteItem[ id=" + id + " ]";
    }
    
}
