/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.customerapp.ejb;

import ec.com.gm.sofcap.service.AreasFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Guz
 */
@Stateless
@LocalBean
public class pruebita {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<AreasFacade> businessMethod() {
        Query query = em.createNamedQuery("AreasFacade.findAll");
        return query.getResultList();
    }
     public String alo(){
        return "alo";
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 public void probarReporte1(){
     
 }
}

