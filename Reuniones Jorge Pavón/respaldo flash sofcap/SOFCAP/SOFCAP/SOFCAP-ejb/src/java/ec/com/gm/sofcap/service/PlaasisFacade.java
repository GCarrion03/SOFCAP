/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.*;
import ec.com.gm.sofcap.views.ReporteAsistencia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javassist.convert.Transformer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Guz
 */
@Stateless
public class PlaasisFacade extends AbstractFacade<Plaasis> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaasisFacade() {
        super(Plaasis.class);
    }
    
    public Collection generarReportePlanificadoCapacitado(Plaasis plaasis){
         HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
         plaasis.getPlantillaCurso().getCodtema();
                Session sesion = hem.getSession();
                System.out.println("Contando asistente vs planificados.");
                Criteria crit = sesion.createCriteria(Plaasis.class,"plaasis").
                        createCriteria("capacitado","capacitado").createCriteria("asistencia", "asistencia").
                        createCriteria("plantillaCurso", "plantillaCurso");
                        //.createAlias("capacitadoCollection","Capacitados").createAlias("asistenciaCollection", "Asistencia").createAlias("curso", "Curso");
                crit.setProjection(Projections.projectionList().
                        add(Projections.groupProperty("capacitado.ceco"), "ceco").
                        add(Projections.groupProperty("plaasis.idPlantilla"),"idPlantilla").
                        add(Projections.groupProperty("plantillaCurso.codtema"),"codtema").
                        add(Projections.rowCount()));
        return crit.list();
    }
    public Collection<Plaasis> buscarAsistentes(Plaasis plaasis){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
                Session sesion = hem.getSession();
                System.out.println("Busqueda asistencia.");
                Criteria crit = sesion.createCriteria(Plaasis.class,"plaasis").createCriteria("capacitado", "capacitado");
                        crit.createCriteria("plaasis.asistencia", "asistencia");
                        crit.add(Restrictions.eq("plaasis.plaasisPK.idPlantilla", plaasis.getPlaasisPK().getIdPlantilla()));
                return crit.list();
    }
    public Collection<Plaasis> buscarAsistentesPlantilla(ReporteAsistencia reporteAsistencia){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
                System.out.println("Busqueda asistencia.");
        Criteria crit = sesion.createCriteria(Plaasis.class,"plaasis")
                        .createCriteria("capacitado", "capacitado");
                        crit.createCriteria("plaasis.asistencia", "asistencia",Criteria.INNER_JOIN);
                        crit.add(Restrictions.eq("plaasis.plaasisPK.idPlantilla", reporteAsistencia.getIdPlantilla()));
                        crit.add(Restrictions.eq("capacitado.ceco", reporteAsistencia.getCeco()));
                        crit.add(Restrictions.eq("capacitado.turno", (Integer)reporteAsistencia.getTurno()));
//                        crit.setResultTransformer(Transformers.aliasToBean(Capacitado.class));
                ArrayList<Plaasis> capacitados=(ArrayList<Plaasis>) crit.list();
                ArrayList<Integer> cedCapacs= new ArrayList<Integer>();
//                Plaasis a= new Plaasis();
//                a.getPlaasisPK().getCedcapac();
//                a.getCapacitado() .get getCeco();
//                        plaasis
                for (Plaasis capac: capacitados)
                {
                    capac.getCapacitado().setSeleccionado(Boolean.TRUE);
                    cedCapacs.add(capac.getPlaasisPK().getCedcapac());
                }
                crit = sesion.createCriteria(Plaasis.class,"plaasis")
                        .createCriteria("capacitado", "capacitado");
                        crit.add(Restrictions.eq("plaasis.plaasisPK.idPlantilla", reporteAsistencia.getIdPlantilla()));
                        
                        crit.add(Restrictions.eq("capacitado.ceco", reporteAsistencia.getCeco()));
                        crit.add(Restrictions.eq("capacitado.turno", (Integer)reporteAsistencia.getTurno()));
                        if (cedCapacs.size()>0)
                        crit.add(Restrictions.not(Restrictions.in("plaasis.plaasisPK.cedcapac", cedCapacs)));
//                        crit.setResultTransformer(Transformers.aliasToBean(Capacitado.class));
                capacitados.addAll((ArrayList<Plaasis>) crit.list());
                return capacitados;
        
    }

}
