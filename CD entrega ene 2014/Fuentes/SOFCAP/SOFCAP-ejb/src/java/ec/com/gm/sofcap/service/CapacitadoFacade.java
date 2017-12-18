/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.JoinType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.ejb.HibernateEntityManager;

/**
 *
 * @author Guz
 */
@Stateless
public class CapacitadoFacade extends AbstractFacade<Capacitado> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacitadoFacade() {
        super(Capacitado.class);
    }

    public Collection<Capacitado> buscarCapacitadoCurso(PlantillaCurso plantillaCurso){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = (Session) hem.getSession();
        List<Asistencia> aExcluir =(ArrayList<Asistencia>) session.createCriteria(Asistencia .class)
                    .createAlias("plaasis", "plaasis", Criteria.INNER_JOIN)
        .add(Restrictions.eq("plaasis.plaasisPK.idPlantilla", plantillaCurso.getIdPlantilla())).list();;
        ArrayList<Integer> cedCapacs= new ArrayList<Integer>();
        for (Asistencia capAct:aExcluir){
            cedCapacs.add(capAct.getAsistenciaPK().getCedcapac());
        }
        
        Criteria cBusqueda= session.createCriteria(Capacitado.class)
                    .createAlias("plaasisCollection", "plaasis", Criteria.LEFT_JOIN)
        .add(Restrictions.eq("plaasis.plaasisPK.idPlantilla", plantillaCurso.getIdPlantilla()));
        if(cedCapacs.size()>0){
        cBusqueda.add(Restrictions.not(Restrictions.in("cedcapac", cedCapacs))).list();
        }
        List results=cBusqueda.list();
        
        
        
        return results;
    }

    public Collection<Capacitado> buscarPuntajesCapacitadoTema(PlantillaCurso plantillaCurso){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = (Session) hem.getSession();
        Criteria criterioBusqueda =session.createCriteria(Asistencia.class);
        criterioBusqueda.createAlias("plaasis", "plaasis",Criteria.LEFT_JOIN);
        criterioBusqueda.createAlias("plaasis.plantillaCurso", "plantillaCurso",Criteria.LEFT_JOIN);
        criterioBusqueda.add(Restrictions.eq("plantillaCurso.codtema", plantillaCurso.getCodtema()));
        criterioBusqueda.setProjection( Projections.projectionList()
                .add(Projections.max("resN3"),"resN3")
                .add(Projections.groupProperty("asistenciaPK.cedcapac")));
//        criterioBusqueda.createAlias("plaasisCollection.asistencia", "asistencia");
        Iterator ite=criterioBusqueda.list().iterator();
        ArrayList<Integer> ids= new ArrayList<Integer>();
        while (ite.hasNext()) {
                    Object[] tuple = (Object[]) ite.next();
                    ids.add((Integer) tuple[1]);
                    System.out.println(""+ tuple[0]+"--"+ tuple[1]);
                }
        Criteria criterioBusquedaCapacitados= session.createCriteria(Capacitado.class);
//        criterioBusquedaCapacitados.add(Restrictions.in("cedcapac", ids));
        
        Collection<Capacitado> capacitadoCol=criterioBusquedaCapacitados.list();
        for (Capacitado capacitadoActual:capacitadoCol)
        {
            ite=criterioBusqueda.list().iterator();
            while (ite.hasNext()) {
                    Object[] tuple = (Object[]) ite.next();
                    if (((Integer) (tuple[1])).equals(capacitadoActual.getCedcapac()))
                    {
                        capacitadoActual.setEvaluacionN3((Double) tuple[0]);
                    }
                    System.out.println(""+ capacitadoActual.getApecapac()+"--"+ tuple[1]);
                }
        }
        return capacitadoCol;
    }
    public Collection<Capacitado> buscarPuntajesCapacitadoTema(PlantillaCurso plantillaCurso,Double lesserVal){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = (Session) hem.getSession();
        Criteria criterioBusqueda =session.createCriteria(Asistencia.class);
        criterioBusqueda.createAlias("plaasis", "plaasis",Criteria.LEFT_JOIN);
        criterioBusqueda.createAlias("plaasis.plantillaCurso", "plantillaCurso",Criteria.LEFT_JOIN);
        criterioBusqueda.add(Restrictions.eq("plantillaCurso.codtema", plantillaCurso.getCodtema()));
        criterioBusqueda.setProjection( Projections.projectionList()
                .add(Projections.max("resN3"),"resN3")
                .add(Projections.groupProperty("asistenciaPK.cedcapac")));
        criterioBusqueda.add(Restrictions.ge("resN3", lesserVal));
//        criterioBusqueda.createAlias("plaasisCollection.asistencia", "asistencia");
        Iterator ite=criterioBusqueda.list().iterator();
        ArrayList<Integer> ids= new ArrayList<Integer>();
        while (ite.hasNext()) {
                    Object[] tuple = (Object[]) ite.next();
                    ids.add((Integer) tuple[1]);
                    System.out.println(""+ tuple[0]+"--"+ tuple[1]);
                }
        Criteria criterioBusquedaCapacitados= session.createCriteria(Capacitado.class);
        criterioBusquedaCapacitados.add(Restrictions.not(Restrictions.in("cedcapac", ids)));

        Collection<Capacitado> capacitadoCol=criterioBusquedaCapacitados.list();

        criterioBusqueda =session.createCriteria(Asistencia.class);
        criterioBusqueda.createAlias("plaasis", "plaasis",Criteria.LEFT_JOIN);
        criterioBusqueda.createAlias("plaasis.plantillaCurso", "plantillaCurso",Criteria.LEFT_JOIN);
        criterioBusqueda.add(Restrictions.eq("plantillaCurso.codtema", plantillaCurso.getCodtema()));
        criterioBusqueda.setProjection( Projections.projectionList()
                .add(Projections.max("resN3"),"resN3")
                .add(Projections.groupProperty("asistenciaPK.cedcapac")));

        List capacitados= criterioBusqueda.list();
        for (Capacitado capacitadoActual:capacitadoCol)
        {
            ite=capacitados.iterator();
            while (ite.hasNext()) {
                    Object[] tuple = (Object[]) ite.next();
                    if (((Integer) (tuple[1])).equals(capacitadoActual.getCedcapac()))
                    {
                        capacitadoActual.setEvaluacionN3((Double) tuple[0]);
                    }
                    System.out.println(""+ capacitadoActual.getApecapac()+"--"+ tuple[1]);
                }
        }
        return capacitadoCol;
    }
    
    public void eliminarCapacitado(Capacitado capacitadoEliminar){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
         
        Asistencia asistencia=new Asistencia();
        asistencia.setAsistenciaPK(new AsistenciaPK());
        asistencia.getAsistenciaPK().setCedcapac(capacitadoEliminar.getCedcapac());
        Criteria criterioBusqueda =session.createCriteria(asistencia.getClass()).add(
                 Example.create(asistencia));
        criterioBusqueda.add(Restrictions.eq("asistenciaPK.cedcapac",capacitadoEliminar.getCedcapac()));
        List<Asistencia> results =  criterioBusqueda.list();
        for (Asistencia asistenciaCapacitado:results)
        {
            session.delete(asistenciaCapacitado);
        }
        Plaasis plaAsistencia=new Plaasis();
        plaAsistencia.setPlaasisPK(new PlaasisPK());
        plaAsistencia.getPlaasisPK().setCedcapac(capacitadoEliminar.getCedcapac());
        criterioBusqueda =session.createCriteria(plaAsistencia.getClass()).add(
                 Example.create(plaAsistencia));
        criterioBusqueda.add(Restrictions.eq("plaasisPK.cedcapac",capacitadoEliminar.getCedcapac()));
        List<Plaasis> resultsPl =  criterioBusqueda.list();
        for (Plaasis asistenciaCapacitado:resultsPl)
        {
            session.delete(asistenciaCapacitado);
        }
        criterioBusqueda =session.createCriteria(capacitadoEliminar.getClass()).add(
                 Example.create(capacitadoEliminar));
        List<Capacitado> resultsCap =  criterioBusqueda.list();
        for (Capacitado asistenciaCapacitado:resultsCap)
        {
            session.delete(asistenciaCapacitado);
        }
    }
}