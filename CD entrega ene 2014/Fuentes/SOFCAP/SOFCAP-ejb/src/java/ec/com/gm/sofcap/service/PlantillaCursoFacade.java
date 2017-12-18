/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.*;
import ec.com.gm.sofcap.views.ReporteCIndividual;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
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
            if (fechaInicio!=null && fechaFin!=null){
            criterioBusqueda.add(Restrictions.
                    le("fechaInicio",new Date()));
            criterioBusqueda.add(Restrictions.
                    ge("fechaFin",new Date()));
            }
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
    
    public void eliminarPlanificacionCurso(PlantillaCurso plantillaCurso) throws Exception{
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
         
         //Obtenemos y eliminamos los intructores
        CursosEvaluacion cursosEvaluacion= new CursosEvaluacion();
         //cursosEvaluacion.getCursosEvaluacionPK().setIdPlantilla(plantillaCurso.getIdPlantilla());
        DetachedCriteria subCriteria= DetachedCriteria.forClass(Curso.class); 
        subCriteria.add(Property.forName("id_plantilla").eq(plantillaCurso.getIdPlantilla()));
        subCriteria.setProjection(Projections.property("idCurso")); 
         
        DetachedCriteria filtroReporte=DetachedCriteria.forClass(CursosEvaluacion.class);
        
        filtroReporte.add(Property.forName("cursosEvaluacionPK.id_Curso").in(subCriteria)); 
         
        Collection<CursosEvaluacion> results =  filtroReporte.getExecutableCriteria(session).list();
        for (CursosEvaluacion cursosEvaluacionElim:results)
        {
            session.delete(cursosEvaluacionElim);
        }
         //Eliminamos los cursos
        subCriteria= DetachedCriteria.forClass(Curso.class); 
        subCriteria.add(Property.forName("id_plantilla").eq(plantillaCurso.getIdPlantilla()));
        for (Curso cursoeliminar: (ArrayList<Curso>) subCriteria.getExecutableCriteria(session).list()){
            session.delete(cursoeliminar);
        }
        
        //Obtenemos y eliminamos los intructores
         Plains plantillaInstructor= new Plains();
         plantillaInstructor.getPlainsPK().setIdPlantilla(plantillaCurso.getIdPlantilla());
         Criteria criterioBusqueda =session.createCriteria(Plains.class).add(
                 Example.create(plantillaInstructor));
         criterioBusqueda.add(Restrictions.eq("plainsPK.idPlantilla",plantillaCurso.getIdPlantilla()));
         Collection<Plains> resultsPlains =  criterioBusqueda.list();
         for (Plains plantillaInstructorElim:resultsPlains)
         {
             session.delete(plantillaInstructorElim);
         }
        
         //Eliminamos los asistencias
        subCriteria= DetachedCriteria.forClass(Asistencia.class); 
        subCriteria.add(Property.forName("asistenciaPK.idPlantilla").eq(plantillaCurso.getIdPlantilla()));
        for (Asistencia asistenciaEliminar: (ArrayList<Asistencia>) subCriteria.getExecutableCriteria(session).list()){
            session.delete(asistenciaEliminar);
        }
        
        //Eliminamos las planificaciones
        subCriteria= DetachedCriteria.forClass(Plaasis.class); 
        subCriteria.add(Property.forName("plaasisPK.idPlantilla").eq(plantillaCurso.getIdPlantilla()));
        for (Plaasis plaAsisEliminar: (ArrayList<Plaasis>) subCriteria.getExecutableCriteria(session).list()){
            session.delete(plaAsisEliminar);
        }
        
        //Eliminamos las plantillas curso
        subCriteria= DetachedCriteria.forClass(PlantillaCurso.class); 
        subCriteria.add(Property.forName("idPlantilla").eq(plantillaCurso.getIdPlantilla()));
        for (PlantillaCurso plantillaCursoEliminar: (ArrayList<PlantillaCurso>) subCriteria.getExecutableCriteria(session).list()){
            plantillaCursoEliminar.setCursoCollection(null);
            plantillaCursoEliminar.setPlaasisCollection(null); 
            plantillaCursoEliminar.setPlainsCollection(null); 
            plantillaCursoEliminar.setTema(null);
            plantillaCursoEliminar.setRespuestascapacitadoCollection(null);
            plantillaCursoEliminar.setTipodecapacitacion(null);
            session.delete(plantillaCursoEliminar);
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
