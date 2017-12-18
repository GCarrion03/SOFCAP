/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.*;
import ec.com.gm.sofcap.views.ReporteAsistencia;
import ec.com.gm.sofcap.views.ReporteAsistenciaNoLider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javassist.convert.Transformer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
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
                        crit.add(Restrictions.eq("capacitado.idlider", (Integer)reporteAsistencia.getIdLider()));
                        if (reporteAsistencia.getTurno()!=null)
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
                        crit.add(Restrictions.eq("capacitado.idlider", (Integer)reporteAsistencia.getIdLider()));
                        if (reporteAsistencia.getTurno()!=null)
                        crit.add(Restrictions.eq("capacitado.turno", (Integer)reporteAsistencia.getTurno()));
                        if (cedCapacs.size()>0)
                        crit.add(Restrictions.not(Restrictions.in("plaasis.plaasisPK.cedcapac", cedCapacs)));
//                        crit.setResultTransformer(Transformers.aliasToBean(Capacitado.class));
                        ArrayList<Plaasis> plArrayList=(ArrayList<Plaasis>) crit.list();
                        for (Plaasis noAsistente: plArrayList){
                            noAsistente.setAsistencia(null);
                        }
                capacitados.addAll(plArrayList);
                return capacitados;
        
    }
    public Collection<Plaasis> buscarAsistentesPlantillaNoLider(ReporteAsistenciaNoLider reporteAsistencia){
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
                System.out.println("Busqueda asistencia.");
        Criteria crit = sesion.createCriteria(Plaasis.class,"plaasis")
                        .createCriteria("capacitado", "capacitado");
                        crit.createCriteria("plaasis.asistencia", "asistencia",Criteria.INNER_JOIN);
                        crit.add(Restrictions.eq("plaasis.plaasisPK.idPlantilla", reporteAsistencia.getIdPlantilla()));
                        crit.add(Restrictions.eq("capacitado.ceco", reporteAsistencia.getCeco()));
                        //crit.add(Restrictions.eq("capacitado.idlider", (Integer)reporteAsistencia.getIdLider()));
                        if (reporteAsistencia.getTurno()!=null)
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
                        //crit.add(Restrictions.eq("capacitado.idlider", (Integer)reporteAsistencia.getIdLider()));
                        if (reporteAsistencia.getTurno()!=null)
                        crit.add(Restrictions.eq("capacitado.turno", (Integer)reporteAsistencia.getTurno()));
                        if (cedCapacs.size()>0)
                        crit.add(Restrictions.not(Restrictions.in("plaasis.plaasisPK.cedcapac", cedCapacs)));
//                        crit.setResultTransformer(Transformers.aliasToBean(Capacitado.class));
                        ArrayList<Plaasis> plArrayList=(ArrayList<Plaasis>) crit.list();
                        for (Plaasis noAsistente: plArrayList){
                            noAsistente.setAsistencia(null);
                        }
                capacitados.addAll(plArrayList);
                return capacitados;
        
    }
     public void crearHistoricoCursos(PlantillaCurso plantillaCurso, Collection<TemaGrupoCap> temaGrupoCapInsert, Collection<TemaGrupoCap> temaGrupoCapElim) throws Exception{
        try{
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
         Session session = hem.getSession();
         //Buscamos el codigo de la plantilla de carga inicial
         Criteria criterioBusqueda =session.createCriteria(PlantillaCurso.class);
                  criterioBusqueda.add(Restrictions.like("objetivo", plantillaCurso.getObjetivo(), MatchMode.ANYWHERE));
                  criterioBusqueda.add(Restrictions.eq("codtema", plantillaCurso.getCodtema()));
         PlantillaCurso plantillaCursoResultado=(PlantillaCurso) criterioBusqueda.uniqueResult();
         ArrayList<Integer> idsGroup= new ArrayList<Integer>();
         for (TemaGrupoCap temaGrupoCap: temaGrupoCapInsert)
         {
             idsGroup.add(temaGrupoCap.getTemaGrupoCapPK().getId_Grupo());
          }
         Criteria criterioBusquedaCapacitado =session.createCriteria(Capacitado.class);
              criterioBusquedaCapacitado.add(Restrictions.in("id_Grupo", idsGroup));
         Collection<Capacitado> capacitadoCol=criterioBusquedaCapacitado.list();
         Collection<Plaasis> planificadosDefecto =  new ArrayList<Plaasis>(); 
         for (Capacitado capacitadoAux: capacitadoCol)
         {
             Plaasis plaasis= new Plaasis();
             plaasis.setPlaasisPK(new PlaasisPK());
             plaasis.getPlaasisPK().setCedcapac(capacitadoAux.getCedcapac());
             plaasis.getPlaasisPK().setIdPlantilla(plantillaCursoResultado.getIdPlantilla());
             planificadosDefecto.add(plaasis);
             this.editAll(planificadosDefecto);
             System.out.println("Persistiendo "+plaasis.getPlaasisPK().getCedcapac()+"---"+plaasis.getPlaasisPK().getIdPlantilla());
         }
        }catch (Exception e){
             e.printStackTrace();
             throw new Exception(e.getMessage());
         }
    }
     public void actualizarAsistentesPlanificados(PlantillaCurso plantillaCurso,Collection<Plaasis> plaasisEliminar ,Collection<Plaasis> plaasisInsertar){
         for (Plaasis planificacionAsistencia: plaasisEliminar){
             Asistencia asistencia= new Asistencia();
             asistencia.setAsistenciaPK(new AsistenciaPK());
             asistencia.getAsistenciaPK().setCedcapac(planificacionAsistencia.getPlaasisPK().getCedcapac());
             asistencia.getAsistenciaPK().setIdPlantilla(planificacionAsistencia.getPlaasisPK().getIdPlantilla());
             asistencia=em.find(Asistencia.class, asistencia.getAsistenciaPK());          
             if (asistencia!=null){
                em.remove(asistencia);
             }
             planificacionAsistencia=em.find(Plaasis.class, planificacionAsistencia.getPlaasisPK());          
             em.remove(planificacionAsistencia);
         }
//         em.r .removeAll(plaasisEliminar);
         this.createAll(plaasisInsertar);      
         
         //actualizamos la plantilla curso en caso de que sea necesario
         PlantillaCurso plantillaCursoTmp=new PlantillaCurso();
         //plantillaCursoTmp.setIdPlantilla(plantillaCurso.getIdPlantilla());
         HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
        Criteria crit=sesion.createCriteria(PlantillaCurso.class).add(
                 Restrictions.eq("idPlantilla",plantillaCurso.getIdPlantilla()));
        plantillaCursoTmp=(PlantillaCurso)crit.list().iterator().next();
        plantillaCursoTmp.setFechaInicio(plantillaCurso.getFechaInicio());
        plantillaCursoTmp.setFechaFin(plantillaCurso.getFechaFin());
        em.persist(plantillaCursoTmp);
     }

}
