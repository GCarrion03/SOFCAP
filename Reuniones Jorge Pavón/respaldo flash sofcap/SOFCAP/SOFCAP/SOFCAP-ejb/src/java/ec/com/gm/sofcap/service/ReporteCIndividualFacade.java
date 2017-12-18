/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.Plaasis;
import ec.com.gm.sofcap.views.ReporteAsistencia;
import ec.com.gm.sofcap.views.ReporteCIndividual;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;

/**
 *
 * @author GUZ
 */
@Stateless
public class ReporteCIndividualFacade extends AbstractFacade<ReporteCIndividual> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteCIndividualFacade() {
        super(ReporteCIndividual.class);
    }
    
    public Collection<ReporteCIndividual> generarReporteCIndividual(ReporteCIndividual reporteCIndividual)
    {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
        Criteria filtroReporte=sesion.createCriteria(ReporteCIndividual.class);
        if (! Arrays.equals(reporteCIndividual.getColCeco(), new String[] {null})){
        filtroReporte.add(Restrictions.in("ceco", reporteCIndividual.getColCeco()));
        }
        if (! Arrays.equals(reporteCIndividual.getColTurno(), new String[] {null})){
        filtroReporte.add(Restrictions.in("turno", reporteCIndividual.getColTurno()));
        }
        if (! Arrays.equals(reporteCIndividual.getColTema(), new Integer[] {null})){
        filtroReporte.add(Restrictions.in("codtema", reporteCIndividual.getColTema()));
        }
        return (filtroReporte.list());
    }
    
     public Collection<ReporteCIndividual> generarReporteCIndividualRangos(ReporteCIndividual reporteCIndividual,Double lesserVal,Integer idCurso)
    {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
        
        DetachedCriteria subCriteria= DetachedCriteria.forClass(Plaasis.class); 
        subCriteria.add(Property.forName("plaasisPK.idPlantilla").eq(idCurso));
        subCriteria.setProjection(Projections.property("plaasisPK.cedcapac")); 
 
//        subCriteria.getExecutableCriteria(sesion).list();
         
        DetachedCriteria filtroReporte=DetachedCriteria.forClass(ReporteCIndividual.class);
        
        filtroReporte.add(Property.forName("cedcapac").notIn(subCriteria)); 
        
        
        filtroReporte.add(Restrictions.lt("promedio", BigDecimal.valueOf(lesserVal)));
        if (! Arrays.equals(reporteCIndividual.getColCeco(), new String[] {null})){
        filtroReporte.add(Restrictions.in("ceco", reporteCIndividual.getColCeco()));
        }
        if (! Arrays.equals(reporteCIndividual.getColTurno(), new String[] {null})){
        filtroReporte.add(Restrictions.in("turno", reporteCIndividual.getColTurno()));
        }
        if (! Arrays.equals(reporteCIndividual.getColTema(), new Integer[] {null})){
        filtroReporte.add(Restrictions.in("codtema", reporteCIndividual.getColTema()));
        }
//        DetachedCriteria avgWeightForSex = DetachedCriteria.forClass(ReporteCIndividual.class, "Excluir")
//        .add( Property.forName("cat2.sex").eq("cat.sex") );
//        session.createCriteria(Cat.class, "cat")
//        .add( Property.forName("weight").gt(avgWeightForSex) )
//        .list();
        
        return (filtroReporte.getExecutableCriteria(sesion).list());
    }
    
     public Collection<ReporteCIndividual> generarReporteCIndividualCodGM(ReporteCIndividual reporteCIndividual)
    {
        HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
        Session sesion = hem.getSession();
        Criteria filtroReporte=sesion.createCriteria(ReporteCIndividual.class);
        filtroReporte.add(Restrictions.eq("codgm", reporteCIndividual.getCodgm()));
        return filtroReporte.list();
    }
    
}
