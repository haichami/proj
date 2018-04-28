/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ANASS
 */
@Entity
public class Taxe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double gain;
    @OneToOne
    private Local local;
    private Double montantTaxeBase;
    private Double montantTaxePremierMois;
    private Double montantTaxeAutreMois;
    private Double montantTaxeTotal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePresentation;
    private int trimestre;
    private int annee;
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Double getMontantTaxeBase() {
        return montantTaxeBase;
    }

    public void setMontantTaxeBase(Double montantTaxeBase) {
        this.montantTaxeBase = montantTaxeBase;
    }

    public Double getMontantTaxePremierMois() {
        return montantTaxePremierMois;
    }

    public void setMontantTaxePremierMois(Double montantTaxePremierMois) {
        this.montantTaxePremierMois = montantTaxePremierMois;
    }

    public Double getMontantTaxeAutreMois() {
        return montantTaxeAutreMois;
    }

    public void setMontantTaxeAutreMois(Double montantTaxeAutreMois) {
        this.montantTaxeAutreMois = montantTaxeAutreMois;
    }

    public Double getMontantTaxeTotal() {
        return montantTaxeTotal;
    }

    public void setMontantTaxeTotal(Double montantTaxeTotal) {
        this.montantTaxeTotal = montantTaxeTotal;
    }

    public Date getDatePresentation() {
        return datePresentation;
    }

    public void setDatePresentation(Date datePresentation) {
        this.datePresentation = datePresentation;
    }

    public int getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taxe)) {
            return false;
        }
        Taxe other = (Taxe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Taxe[ id=" + id + " ]";
    }

}
