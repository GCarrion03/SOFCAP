/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.Asistencia;
import ec.com.gm.sofcap.dto.Curso;
import ec.com.gm.sofcap.dto.CursosEvaluacion;
import ec.com.gm.sofcap.dto.CursosEvaluacionPK;
import ec.com.gm.sofcap.dto.Instructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


/**
 *
 * @author Guz
 */
@Stateless
public class CursoFacade extends AbstractFacade<Curso> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }
    public void actualizarCurso(Curso cursoActualizar,Collection<Asistencia> asistentes)
    {
        try{
        em.persist(cursoActualizar);
        CursosEvaluacion cursoEvaluacion=new CursosEvaluacion();
        cursoEvaluacion.setCursosEvaluacionPK(new CursosEvaluacionPK());
        cursoEvaluacion.getCursosEvaluacionPK().setid_Curso(cursoActualizar.getIdCurso());
        cursoEvaluacion.getCursosEvaluacionPK().setcodEva(1);
        em.persist(cursoEvaluacion);
        for (Asistencia asistencia: asistentes)
        {
           em.persist(asistencia);
        }
//        for (Asistencia asistentes: cursoActualizar.getAsistenciaCollection())
//        {
//            asistentes.setId_curso(cursoActualizar.getIdCurso());
//            System.out.println("cedula de asistente:"+asistentes.getCedcapac());
//            em.persist(asistentes);
//        }
//        for (Asistencia asistencia : colAsistentes)
//        {
//            System.out.println(asistencia.getCedcapac());
//            System.out.println(asistencia.getId_curso());
//            em.persist(asistencia);
//        }
//        System.out.println("SALIO DEL FOR");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
     public Collection<Curso> obtenerInstructorTemasPlantilla(Curso cursoBusqueda)
     {
         HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
         Session session = hem.getSession();
         //cursoBusqueda
         Date fechaInicio=cursoBusqueda.getFechaInicio();
         Date fechaFin= cursoBusqueda.getFechaFin();
         System.out.println(cursoBusqueda.getFechaInicio().toString());
         System.out.println(cursoBusqueda.getFechaFin().toString());
         cursoBusqueda.setFechaInicio(null);
         cursoBusqueda.setFechaFin(null);
         Criteria criterioBusqueda =session.createCriteria(Curso.class).add(
                 Example.create(cursoBusqueda));
//         Curso cur;
//         cur.getPlantillaCurso().getPlaasisCollection()
         criterioBusqueda.createAlias("plantillaCurso", "plantillaCurso");
//         criterioBusqueda.createAlias("plantillaCurso.plaasisCollection", "plaasisCollection", Criteria.LEFT_JOIN);
//         criterioBusqueda.createAlias("plaasisCollection.asistencia", "asistencia");
//         criterioBusqueda.createAlias("plaasisCollection.capacitado", "capacitado");
         criterioBusqueda.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
         if (fechaInicio!=null&& fechaFin!=null)
         {
            criterioBusqueda.add(Restrictions.
                    between("fechaInicio", fechaInicio,fechaFin));
         }
         List results =  criterioBusqueda.list();
         return (results);
     }
     public Collection<Curso> obtenerInstructorTemas (Integer codTema, Date fechaInicio,Date fechaFin)
    {

                HashMap<String, Object> parametros = null;
		StringBuilder select = null;
		Query query = null;
//		HashMap<String, Object> parametros = null;
			select = new StringBuilder();

			//GENERA SENTENCIA SQL
			this.generarConsulta(new Curso(), select);

			//CREA LA CONSULTA
                        select.append(" where Curso.fechaInicio between :pfechaInicio and :pfechaFin");
//           		parametros.put("pcodtema", codPrincipio);
                        if (codTema!=null){
                        select.append(" and Curso.codtema = :pcodtema ");
        }
			query = em.createQuery(select.toString());
                        if (codTema!=null){
                        query.setParameter("pcodtema", codTema);
                        }
                        query.setParameter("pfechaInicio", fechaInicio);
                        query.setParameter("pfechaFin", fechaFin);
			//PARAMETROS
                        System.out.println(fechaInicio.toString());
                        System.out.println(fechaFin.toString());
                        System.out.println(codTema);
			return query.getResultList();

    }
    private void generarConsulta(Curso curso, StringBuilder select){
		select.append("select distinct Curso from ");
		select.append(Curso.class.getName()).append(" Curso");

	}

}
