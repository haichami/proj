/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Taxe;
import bean.TauxTaxeBoison;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ANASS
 */
@Stateless
public class TaxeFacade extends AbstractFacade<Taxe> {

    @PersistenceContext(unitName = "ProjetPU")
    private EntityManager em;

    @EJB
    TauxTaxeBoisonFacade tauxTaxeBoisonFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaxeFacade() {
        super(Taxe.class);
    }

    public int save(Taxe taxe) {
        TauxTaxeBoison tauxTaxeBoison = tauxTaxeBoisonFacade.findByCategorie(taxe.getLocal().getCategorie());
        if (tauxTaxeBoison == null) {
            return -1;
        } else {
            calculerMontantBase(taxe, tauxTaxeBoison);
            calculerMontantPremierMois(taxe, tauxTaxeBoison);
            calculerMontantAutreMois(taxe, tauxTaxeBoison);
            CalculeTotal(taxe);
            return 1;
        }
    }

    private void calculerMontantBase(Taxe taxe, TauxTaxeBoison tauxTaxeBoison) {
        double montantBase = taxe.getGain() * tauxTaxeBoison.getTauxTaxeBase() / 100;
        taxe.setMontantTaxeBase(montantBase);
    }

    public void calculerMontantPremierMois(Taxe taxe, TauxTaxeBoison tauxTaxeBoison) {
        double montantPremierMois = taxe.getGain() * tauxTaxeBoison.getTauxRetardPremierMois() / 100;
        taxe.setMontantTaxePremierMois(montantPremierMois);
    }

    public void calculerMontantAutreMois(Taxe taxe, TauxTaxeBoison tauxTaxeBoison) {
        double montantAutreMois = taxe.getGain() * tauxTaxeBoison.getTauxRetardAutreMois() / 100;
        taxe.setMontantTaxeAutreMois(montantAutreMois);
    }

    public void CalculeTotal(Taxe taxe) {
        double total = taxe.getMontantTaxeBase()+taxe.getMontantTaxePremierMois()+taxe.getMontantTaxeAutreMois();
        taxe.setMontantTaxeTotal(total);
        
    }

}
