/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Local;
import bean.Quartier;
import bean.Redevable;
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
public class LocalFacade extends AbstractFacade<Local> {

    @PersistenceContext(unitName = "ProjetPU")
    private EntityManager em;

    public List<Local> findByRedevable(Redevable redevable) {
        String query = "SELECT lo FROM Local lo WHERE 1=1";
        query += SearchUtil.addConstraint("lo", "Redevable.id", "=", redevable.getId());
        return em.createQuery(query).getResultList();
    }
//    public List<Local> findByRue(Rue rue){
//     String query = "SELECT lo FROM Local lo WHERE 1=1";
//        query+=SearchUtil.addConstraint("lo", "Rue.id", "=", rue.getId());
//        return em.createQuery(query).getResultList();
//    }

    public List<Local> findByCriteria(Rue rue, Quartier quartier, Secteur secteur) {
        String query = contructQueryByCriteria(rue, quartier, secteur);
        return em.createQuery(query).getResultList();
    }

    private String contructQueryByCriteria(Rue rue, Quartier quartier, Secteur secteur) {
        System.out.println("hahia ");
        String query = "SELECT lo FROM Local lo WHERE 1=1";
        if (rue != null) {
            query += SearchUtil.addConstraint("lo", "rue.id", "=", rue.getId());
            System.out.println("ha rue");
        }
        if (quartier != null) {
            query += SearchUtil.addConstraint("lo", "rue.quartier.id", "=", quartier.getId());
            System.out.println("ha quartier");
        }
        if (secteur != null) {
            query += SearchUtil.addConstraint("lo", "rue.quartier.secteur.id", "=", secteur.getId());
            System.out.println("ha secteur");
        }
        return query;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalFacade() {
        super(Local.class);
    }

}
