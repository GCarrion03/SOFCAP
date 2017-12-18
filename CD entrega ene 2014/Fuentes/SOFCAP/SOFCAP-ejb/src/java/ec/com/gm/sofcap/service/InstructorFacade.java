/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.Curso;
import ec.com.gm.sofcap.dto.Instructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;

/**
 *
 * @author Guz
 */
@Stateless
public class InstructorFacade extends AbstractFacade<Instructor> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public InstructorFacade() {
        super(Instructor.class);
    }
     public Collection<Instructor> obtenerInstructorTemas (Integer codTema)
    {
                HashMap<String, Object> parametros = null;
		StringBuilder select = null;
		Query query = null;
//		HashMap<String, Object> parametros = null;
			select = new StringBuilder();

			//GENERA SENTENCIA SQL
			this.generarConsulta(new Instructor(), select);

			//CREA LA CONSULTA
                        select.append(" where Instructor.tema.codtema = :pcodtema");
//           		parametros.put("pcodtema", codPrincipio);

			query = em.createQuery(select.toString());
                        query.setParameter("pcodtema", codTema);
			//PARAMETROS
			return query.getResultList();

    }
      private void generarConsulta(Instructor instructor, StringBuilder select){
		select.append("select distinct Instructor from ");
		select.append(Instructor.class.getName()).append(" Instructor");

	}
      public Collection<Instructor> generarReporteInstructor(Curso plantillaCurso)
        {
           ArrayList <Instructor> instructores=new ArrayList<Instructor>();
           
           HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
                Session sesion = hem.getSession();
                System.out.println("Contando asistentes");
                Criteria crit = sesion.createCriteria(Instructor.class,"Instructor").createCriteria("cursoCollection","Cursos").add(Restrictions.between("fechaFin", plantillaCurso.getFechaInicio(),plantillaCurso.getFechaFin()));
                        //.createAlias("capacitadoCollection","Capacitados").createAlias("asistenciaCollection", "Asistencia").createAlias("curso", "Curso");
                crit.setProjection(Projections.projectionList().add(Projections.groupProperty("Instructor.codinstr"), "codinstr").add(Projections.groupProperty("Instructor.nominstr"),"nominstr").add(Projections.rowCount()));
                Iterator results = crit.list().iterator();
                while (results.hasNext()) {
                    Instructor instructorTmp= new Instructor();
                    Object[] tuple = (Object[]) results.next();
                    instructorTmp.setCodinstr((Integer) tuple[0]);
                    instructorTmp.setNominstr((String) tuple[1]);
                    instructorTmp.setConteo((Long) tuple[2]);
                    System.out.println(""+ tuple[0]+"--"+ tuple[1]+"--"+ tuple[2]);
                    instructores.add(instructorTmp);
                }
            return instructores;
      }
}
