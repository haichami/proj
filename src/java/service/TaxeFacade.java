/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Local;
import bean.Taxe;
import bean.TauxTaxeBoison;
import controller.util.SearchUtil;
import java.rmi.server.Operation;
import java.util.Date;
import java.util.List;
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
    
    public Taxe findByLocal(Local local){
        String query="SELECT t FROM Taxe t WHERE 1=1";
        query+=SearchUtil.addConstraint("t", "Local.id", "=", local.getNom());
        return (Taxe) em.createQuery(query).getSingleResult();
    }

    public int save(Taxe taxe) {
        TauxTaxeBoison tauxTaxeBoison = tauxTaxeBoisonFacade.findByCategorie(taxe.getLocal().getCategorie());
        if (tauxTaxeBoison == null) {
            return -1;
        } else {
            calculerMontantBase(taxe, tauxTaxeBoison);
            calculerMontantPremierMois(taxe, tauxTaxeBoison);
            calculerMontantAutreMois(taxe, tauxTaxeBoison);
            calculeTotal(taxe);
            create(taxe);
            return 1;
        }
    }

    private void calculerMontantBase(Taxe taxe, TauxTaxeBoison tauxTaxeBoison) {
        double montantBase = taxe.getGain() * tauxTaxeBoison.getTauxTaxeBase() / 100;
        taxe.setMontantTaxeBase(montantBase);
    }

    private void calculerMontantPremierMois(Taxe taxe, TauxTaxeBoison tauxTaxeBoison) {
        double montantPremierMois = taxe.getGain() * tauxTaxeBoison.getTauxRetardPremierMois() / 100;
        taxe.setMontantTaxePremierMois(montantPremierMois);
    }

    private void calculerMontantAutreMois(Taxe taxe, TauxTaxeBoison tauxTaxeBoison) {
        double montantAutreMois = taxe.getGain() * tauxTaxeBoison.getTauxRetardAutreMois() / 100;
        taxe.setMontantTaxeAutreMois(montantAutreMois);
    }

    private void calculeTotal(Taxe taxe) {
        double total = taxe.getMontantTaxeBase()+taxe.getMontantTaxePremierMois()+taxe.getMontantTaxeAutreMois();
        taxe.setMontantTaxeTotal(total);
        
    }
    
    
}
