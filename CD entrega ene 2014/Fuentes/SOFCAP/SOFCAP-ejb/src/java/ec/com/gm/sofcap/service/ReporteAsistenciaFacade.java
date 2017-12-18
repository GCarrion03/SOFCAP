/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.views.ReporteAsistencia;
import ec.com.gm.sofcap.views.ReporteAsistenciaNoLider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;

/**
 *
 * @author GUZ
 */
@Stateless
public class ReporteAsistenciaFacade extends AbstractFacade<ReporteAsistencia> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteAsistenciaFacade() {
        super(ReporteAsistencia.class);
    }
    
    public Collection<ReporteAsistencia> generarReporteAsistencia(ReporteAsistencia reporteAsistencia)
    {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
        Criteria filtroReporte=sesion.createCriteria(ReporteAsistencia.class);
        if (! Arrays.equals(reporteAsistencia.getColCeco(), new String[] {null})){
            String[] arrTmpCeco=new String[reporteAsistencia.getColCeco().length];
            int i=0;
            for (Integer tmpCeco:reporteAsistencia.getColCeco()){
                arrTmpCeco[i]=tmpCeco.toString();
                i++;
            }
             
        filtroReporte.add(Restrictions.in("ceco", arrTmpCeco));
        }
        if (! Arrays.equals(reporteAsistencia.getColTurno(), new String[] {null})){
        filtroReporte.add(Restrictions.in("turno", reporteAsistencia.getColTurno()));
        }
        if (! Arrays.equals(reporteAsistencia.getColTema(), new String[] {null})){
        filtroReporte.add(Restrictions.in("codtema", reporteAsistencia.getColTema()));
        }
        if (! Arrays.equals(reporteAsistencia.getColIdLider(), new Integer[] {null})){
        filtroReporte.add(Restrictions.in("idLider", reporteAsistencia.getColIdLider()));
        }
        filtroReporte.add(Restrictions.ge("fechaInicio",reporteAsistencia.getFechaInicio()));
        filtroReporte.add(Restrictions.le("fechaInicio",reporteAsistencia.getFechaFin()));
        return (filtroReporte.list());
    }
    public Collection<ReporteAsistenciaNoLider> generarReporteAsistenciaNoLider(ReporteAsistencia reporteAsistencia)
    {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
        Criteria filtroReporte=sesion.createCriteria(ReporteAsistenciaNoLider.class);
        if (! Arrays.equals(reporteAsistencia.getColCeco(), new String[] {null})){
            String[] arrTmpCeco=new String[reporteAsistencia.getColCeco().length];
            int i=0;
            for (Integer tmpCeco:reporteAsistencia.getColCeco()){
                arrTmpCeco[i]=tmpCeco.toString();
                i++;
            }
             
        filtroReporte.add(Restrictions.in("ceco", arrTmpCeco));
        }
        if (! Arrays.equals(reporteAsistencia.getColTurno(), new String[] {null})){
        filtroReporte.add(Restrictions.in("turno", reporteAsistencia.getColTurno()));
        }
        if (! Arrays.equals(reporteAsistencia.getColTema(), new String[] {null})){
        filtroReporte.add(Restrictions.in("codtema", reporteAsistencia.getColTema()));
        }
        filtroReporte.add(Restrictions.ge("fechaInicio",reporteAsistencia.getFechaInicio()));
        filtroReporte.add(Restrictions.le("fechaInicio",reporteAsistencia.getFechaFin()));
        return (filtroReporte.list());
    }
}
