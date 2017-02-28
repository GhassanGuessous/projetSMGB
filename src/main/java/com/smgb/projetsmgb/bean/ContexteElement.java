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

/**
 *
 * @author Ghassan
 */
@Entity
public class ContexteElement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    @ManyToOne
    private Contexte contexte;
    @OneToMany(mappedBy = "contexteElement")
    private List<SensibleParam> sensibleParams;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SensibleParam> getSensibleParams() {
        return sensibleParams;
    }

    public void setSensibleParams(List<SensibleParam> sensibleParams) {
        this.sensibleParams = sensibleParams;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Contexte getContexte() {
        return contexte;
    }

    public void setContexte(Contexte contexte) {
        this.contexte = contexte;
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
        if (!(object instanceof ContexteElement)) {
            return false;
        }
        ContexteElement other = (ContexteElement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smgb.projetsmgb.bean.ContexteElement[ id=" + id + " ]";
    }
    
}
