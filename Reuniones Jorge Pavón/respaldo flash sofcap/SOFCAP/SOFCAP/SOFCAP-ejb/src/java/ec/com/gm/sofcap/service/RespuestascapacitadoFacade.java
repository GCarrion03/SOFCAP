/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.Respuestascapacitado;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;

/**
 *
 * @author Guz
 */
@Stateless
public class RespuestascapacitadoFacade extends AbstractFacade<Respuestascapacitado> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RespuestascapacitadoFacade() {
        super(Respuestascapacitado.class);
    }
    public Collection<Respuestascapacitado> obtenerRespuestasCapacitadoPlantilla(Respuestascapacitado respuestasBusqueda)
    {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
         Session session = hem.getSession();
         //cursoBusqueda

         Criteria criterioBusqueda =session.createCriteria(Respuestascapacitado.class);
                 criterioBusqueda.add(Restrictions.eq("respuestascapacitadoPK.idCurso", respuestasBusqueda.getRespuestascapacitadoPK().getIdCurso()) ).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

//                 criterioBusqueda.createAlias("curso.asistenciaCollection", "asistencia", Criteria.INNER_JOIN);
//                 criterioBusqueda.createAlias("curso.asistenciaCollection.capacitado", "capacitado", Criteria.INNER_JOIN);
//                 criterioBusqueda.createAlias("capacitado.areas", "area", Criteria.INNER_JOIN);
//                 criterioBusqueda.createAlias("asistencia.areas", "area", Criteria.INNER_JOIN);
         List results =  criterioBusqueda.list();
         //.add(Restrictions.in("respuestascapacitadoPK.codprg", new Integer[] {1,2,3,4,5,6,7,8}) )
         return (results);
    }

}
