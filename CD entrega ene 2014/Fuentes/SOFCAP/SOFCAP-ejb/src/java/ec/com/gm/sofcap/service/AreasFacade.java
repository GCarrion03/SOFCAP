/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.Areas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.ejb.HibernateEntityManager;
import ec.com.gm.sofcap.dto.Curso;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Guz
 */
@Stateless
public class AreasFacade extends AbstractFacade<Areas> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AreasFacade() {
        super(Areas.class);
    }
    public String alo(){
        return "alo";
    }

    public void persist(Object object) {
        em.persist(object);
    }
    public Collection<Areas> contarAsistentes(Curso cursoBuscado){
        ArrayList <Areas> areas=new ArrayList<Areas>();
            try{
                HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
                Session sesion = hem.getSession();
                System.out.println("Contando asistentes");
                Criteria crit = sesion.createCriteria(Areas.class,"Area").createCriteria("capacitadoCollection","Capacitados").createCriteria("asistenciaCollection", "Asistencia").createCriteria("curso", "Curso").add(Restrictions.eq("id_plantilla", cursoBuscado.getId_plantilla()));;
                        //.createAlias("capacitadoCollection","Capacitados").createAlias("asistenciaCollection", "Asistencia").createAlias("curso", "Curso");
                crit.setProjection(Projections.projectionList().add(Projections.groupProperty("Area.codarea"), "codarea").add(Projections.groupProperty("Area.desarea"),"desarea").add(Projections.rowCount()));
                Iterator results = crit.list().iterator();
                while (results.hasNext()) {
                    Areas areaTmp= new Areas();
                    Object[] tuple = (Object[]) results.next();
                    areaTmp.setCodarea((Integer) tuple[0]);
                    areaTmp.setDesarea((String) tuple[1]);
                    areaTmp.setAsistentes( (Long) tuple[2]);
                    System.out.println(""+ tuple[0]+"--"+ tuple[1]+"--"+ tuple[2]);
                    areas.add(areaTmp);
                }
                }catch(Exception e){
                    System.out.println("Error");
                    e.printStackTrace();
                }
            return (areas);
     }
}