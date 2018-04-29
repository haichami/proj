/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Local;
import bean.Redevable;
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
public class LocalFacade extends AbstractFacade<Local> {

    @PersistenceContext(unitName = "ProjetPU")
    private EntityManager em;

    public List<Local> findByRedevable(Redevable redevable) {
        String query="SELECT lo FROM Local lo WHERE 1=1";
        query+=SearchUtil.addConstraint("lo", "Redevable.id", "=", redevable.getId());
        return em.createQuery(query).getResultList();
    }
 
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalFacade() {
        super(Local.class);
    }
    
}
