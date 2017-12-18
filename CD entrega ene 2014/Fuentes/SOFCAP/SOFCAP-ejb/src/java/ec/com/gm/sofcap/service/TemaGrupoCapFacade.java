/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.GrupoCap;
import ec.com.gm.sofcap.dto.TemaGrupoCap;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.transform.Transformers;

/**
 *
 * @author GUZ
 */
@Stateless
public class TemaGrupoCapFacade extends AbstractFacade<TemaGrupoCap> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TemaGrupoCapFacade() {
        super(TemaGrupoCap.class);
    }
    public Collection<GrupoCap> buscarGruposAsignado(TemaGrupoCap temaGrupoCap){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
                System.out.println("Busqueda Grupos asignado.");
        Criteria crit = sesion.createCriteria(GrupoCap.class,"grupoCap")
                        . createAlias("grupoCap.temaGrupoCapCol", "temaGrupoCap",Criteria.INNER_JOIN);
                        crit.add(Restrictions.eq("temaGrupoCap.temaGrupoCapPK.codTema", temaGrupoCap.getTemaGrupoCapPK().getCodTema()));
        return crit.list();
    }
    
}
