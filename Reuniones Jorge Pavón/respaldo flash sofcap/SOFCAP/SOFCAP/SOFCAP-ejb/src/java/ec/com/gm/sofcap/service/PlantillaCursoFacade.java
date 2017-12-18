/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.Plains;
import ec.com.gm.sofcap.dto.PlantillaCurso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;

/**
 *
 * @author Guz
 */
@Stateless
public class PlantillaCursoFacade extends AbstractFacade<PlantillaCurso> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PlantillaCursoFacade() {
        super(PlantillaCurso.class);
    }

    public Collection<PlantillaCurso> obtenerPlanificacionCursoPlantilla(PlantillaCurso planificacionCurso)
     {
         HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
         Session session = hem.getSession();
         //cursoBusqueda
         Date fechaInicio=planificacionCurso.getFechaInicio();
         Date fechaFin= planificacionCurso.getFechaFin();
         planificacionCurso.setFechaInicio(null);
         planificacionCurso.setFechaFin(null);
         Criteria criterioBusqueda =session.createCriteria(PlantillaCurso.class).add(
                 Example.create(planificacionCurso));
            criterioBusqueda.add(Restrictions.
                    between("fechaFin", fechaInicio,fechaFin));
         List results =  criterioBusqueda.list();
         return (results);
     }
    public void guardarPlanificacionCurso(PlantillaCurso plantillaCurso){
        ArrayList<Plains> plantillaInstructor=(ArrayList<Plains>) plantillaCurso.getPlainsCollection();
        plantillaCurso.setPlainsCollection(null);
        em.persist(plantillaCurso);
        for (Plains plains: plantillaInstructor){
            plains.getPlainsPK().setIdPlantilla(plantillaCurso.getIdPlantilla());
            System.out.println("id plains: "+plains.getPlainsPK().getCodinstr()+"id codcurso"+plains.getPlainsPK().getIdPlantilla());
            em.persist(plains);
        }
    }
    public Object buscarPlanificacionConInstructor(PlantillaCurso plantillaCurso){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
         Session session = hem.getSession();
         Criteria criterioBusqueda =session.createCriteria(PlantillaCurso.class);
         criterioBusqueda.createAlias("plainsCollection", "plainsCollection",Criteria.LEFT_JOIN);
         criterioBusqueda.createAlias("plainsCollection.instructor", "instructor",Criteria.LEFT_JOIN);
         
         criterioBusqueda.add(Restrictions.eq("idPlantilla", plantillaCurso.getIdPlantilla()));
         criterioBusqueda.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
         return criterioBusqueda.uniqueResult();
    }
}
