/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author ANASS
 */
@Entity
public class TauxTaxeBoison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Categorie categorie;
    private Double tauxTaxeBase;
    private Double tauxRetardPremierMois;
    private Double tauxRetardAutreMois;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTauxTaxeBase() {
        return tauxTaxeBase;
    }

    public void setTauxTaxeBase(Double tauxTaxeBase) {
        this.tauxTaxeBase = tauxTaxeBase;
    }

    public Double getTauxRetardPremierMois() {
        return tauxRetardPremierMois;
    }

    public void setTauxRetardPremierMois(Double tauxRetardPremierMois) {
        this.tauxRetardPremierMois = tauxRetardPremierMois;
    }

    public Double getTauxRetardAutreMois() {
        return tauxRetardAutreMois;
    }

    public void setTauxRetardAutreMois(Double tauxRetardAutreMois) {
        this.tauxRetardAutreMois = tauxRetardAutreMois;
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TauxTaxeBoison)) {
            return false;
        }
        TauxTaxeBoison other = (TauxTaxeBoison) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.TauxTaxeBoison[ id=" + id + " ]";
    }

}
