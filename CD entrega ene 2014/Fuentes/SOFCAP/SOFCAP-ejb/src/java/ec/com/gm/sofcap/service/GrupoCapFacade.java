/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.GrupoCap;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;

/**
 *
 * @author GUZ
 */
@Stateless
public class GrupoCapFacade extends AbstractFacade<GrupoCap> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoCapFacade() {
        super(GrupoCap.class);
    }
    
    public Collection<GrupoCap> buscarGruposExcluyendo(Collection<Integer> codGrupos){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
                System.out.println("Busqueda Grupos.");
        Criteria crit = sesion.createCriteria(GrupoCap.class,"grupoCap");
        if (codGrupos.size()>0)                
        crit.add(Restrictions.not(Restrictions.in("grupoCap.idGrupo", codGrupos))); 
        return crit.list();
    }
}
