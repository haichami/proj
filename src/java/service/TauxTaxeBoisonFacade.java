/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.TauxTaxeBoison;
import controller.util.SearchUtil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ANASS
 */
@Stateless
public class TauxTaxeBoisonFacade extends AbstractFacade<TauxTaxeBoison> {

    @PersistenceContext(unitName = "ProjetPU")
    private EntityManager em;

    public TauxTaxeBoison findByCategorie(Categorie categorie){
        String query="SELECT t FROM TauxTaxeBoison t WHERE 1=1";
        query+=SearchUtil.addConstraint("t", "categorie.id", "=", categorie.getId());
        return (TauxTaxeBoison) em.createQuery(query).getSingleResult();
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TauxTaxeBoisonFacade() {
        super(TauxTaxeBoison.class);
    }
    
}
