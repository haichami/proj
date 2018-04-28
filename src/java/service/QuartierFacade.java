/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Quartier;
import bean.Rue;
import bean.Secteur;
import controller.util.SearchUtil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ANASS
 */
@Stateless
public class QuartierFacade extends AbstractFacade<Quartier> {

    @PersistenceContext(unitName = "ProjetPU")
    private EntityManager em;

    
      public List<Rue> findBySecteur(Secteur secteur){
     String query = "SELECT q FROM Quartier q WHERE 1=1";
        query+=SearchUtil.addConstraint("q", "secteur.id", "=", secteur.getId());
        return em.createQuery(query).getResultList();
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuartierFacade() {
        super(Quartier.class);
    }
    
}
