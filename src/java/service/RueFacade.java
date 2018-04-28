/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Quartier;
import bean.Rue;
import bean.Quartier;
import static bean.Rue_.nom;
import static bean.Rue_.quartier;
import controller.util.SearchUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ANASS
 */
@Stateless
public class RueFacade extends AbstractFacade<Rue> {

    @PersistenceContext(unitName = "ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RueFacade() {
        super(Rue.class);
    }
   
    
    public List<Rue> findByQuartier(Quartier quartier){
     String query = "SELECT ru FROM Rue ru WHERE 1=1";
        query+=SearchUtil.addConstraint("ru", "quartier.id", "=", quartier.getId());
        return em.createQuery(query).getResultList();
    }
    
   
}
