/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.ejb.instances;
import ec.com.gm.sofcap.dto.Areas;
import ec.com.gm.sofcap.dto.Centroentrenamiento;
import ec.com.gm.sofcap.dto.GrupoCap;
import ec.com.gm.sofcap.service.AreasFacade;
import ec.com.gm.sofcap.service.AsistenciaFacade;
import ec.com.gm.sofcap.service.CapacitadoFacade;
import ec.com.gm.sofcap.service.CecoFacade;
import ec.com.gm.sofcap.service.CentroentrenamientoFacade;
import ec.com.gm.sofcap.service.CursoFacade;
import ec.com.gm.sofcap.service.GrupoCapFacade;
import ec.com.gm.sofcap.service.InstructorFacade;
import ec.com.gm.sofcap.service.LiderFacade;
import ec.com.gm.sofcap.service.PlaasisFacade;
import ec.com.gm.sofcap.service.PlantillaCursoFacade;
import ec.com.gm.sofcap.service.PlantillaevaluacionFacade;
import ec.com.gm.sofcap.service.PreguntaFacade;
import ec.com.gm.sofcap.service.PrincipiosGmsFacade;
import ec.com.gm.sofcap.service.ReporteAsistenciaFacade;
import ec.com.gm.sofcap.service.ReporteCIndividualFacade;
import ec.com.gm.sofcap.service.RespuestaFacade;
import ec.com.gm.sofcap.service.RespuestascapacitadoFacade;
import ec.com.gm.sofcap.service.RolFacade;
import ec.com.gm.sofcap.service.TemaFacade;
import ec.com.gm.sofcap.service.TemaGrupoCapFacade;
import ec.com.gm.sofcap.service.TipodecapacitacionFacade;
import ec.com.gm.sofcap.service.TipoevaluacionFacade;
import ec.com.gm.sofcap.service.TurnoFacade;
import ec.com.gm.sofcap.service.UsuarioFacade;
import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author Guz
 */

@ManagedBean(name="ejbInstances",eager = true)
@ApplicationScoped
public class EJBInstances<T> implements Serializable {
    private static String contexto="java:global/SOFCAP/SOFCAP-ejb/";
    private AreasFacade areasLocator;
    private PrincipiosGmsFacade principiosLocator;
    private CapacitadoFacade capacitadoLocator;
    private CursoFacade CursoLocator;
    private InstructorFacade instructorLocator;
    private PlantillaevaluacionFacade plantillaevaluacionLocator;
    private PreguntaFacade preguntaLocator;
    private PrincipiosGmsFacade principiosGmsLocator;
    private RespuestaFacade respuestaLocator;
    private RespuestascapacitadoFacade respuestascapacitadoLocator;
    private TemaFacade temaLocator;
    private TipodecapacitacionFacade tipodecapacitacionLocator;
    private TipoevaluacionFacade tipoevaluacionLocator;
    private TurnoFacade  turnoLocator;
    private CentroentrenamientoFacade centroentrenamientoLocator;
    private UsuarioFacade usuarioLocator;
    private PlantillaCursoFacade plantillaCursoLocator;
    private AsistenciaFacade asistenciaLocator;
    private PlaasisFacade plaasisLocator;
    private ReporteAsistenciaFacade reporteAsistenciaLocator;
    private ReporteCIndividualFacade reporteCIndividualLocator;
    private CecoFacade cecoLocator;
    private GrupoCapFacade grupoLocator;
    private TemaGrupoCapFacade temaGrupoCapLocator;
    private LiderFacade liderLocator;
    private RolFacade rolLocator;
    public EJBInstances() throws NamingException{
        System.out.println("Se inicializaron las instancias de los beans");
        
       //INFO: Portable JNDI names for EJB PrimerEJB : [java:global/cursoEJB/PrimerEJB!vhsg.ejb.PrimerEJBRemote, java:global/cursoEJB/PrimerEJB!vhsg.ejb.PrimerEJB]
       // INFO: Glassfish-specific (Non-portable) JNDI names for EJB PrimerEJB : [vhsg.ejb.PrimerEJBRemote#vhsg.ejb.PrimerEJBRemote, vhsg.ejb.PrimerEJBRemote]
        
        areasLocator = (AreasFacade) obtenerEJB((T) new AreasFacade());
        principiosLocator=(PrincipiosGmsFacade) obtenerEJB((T) new PrincipiosGmsFacade());
        capacitadoLocator=(CapacitadoFacade) obtenerEJB((T) new CapacitadoFacade());
        instructorLocator =(InstructorFacade) obtenerEJB((T) new InstructorFacade());
        plantillaevaluacionLocator =( PlantillaevaluacionFacade) obtenerEJB((T) new  PlantillaevaluacionFacade());
        preguntaLocator =(PreguntaFacade) obtenerEJB((T) new PreguntaFacade());
        principiosGmsLocator  =(PrincipiosGmsFacade) obtenerEJB((T) new PrincipiosGmsFacade());
        respuestaLocator  =(RespuestaFacade) obtenerEJB((T) new RespuestaFacade());
        respuestascapacitadoLocator  =(RespuestascapacitadoFacade) obtenerEJB((T) new RespuestascapacitadoFacade());
        temaLocator =(TemaFacade) obtenerEJB((T) new TemaFacade());
        tipodecapacitacionLocator =(TipodecapacitacionFacade) obtenerEJB((T) new TipodecapacitacionFacade());
        tipoevaluacionLocator =(TipoevaluacionFacade) obtenerEJB((T) new TipoevaluacionFacade());
        turnoLocator =(TurnoFacade) obtenerEJB((T) new TurnoFacade());
        centroentrenamientoLocator = (CentroentrenamientoFacade) obtenerEJB((T) new CentroentrenamientoFacade());
        CursoLocator=(CursoFacade)obtenerEJB((T) new CursoFacade());
        usuarioLocator=(UsuarioFacade)obtenerEJB((T) new UsuarioFacade());
        plantillaCursoLocator=(PlantillaCursoFacade) obtenerEJB((T)new PlantillaCursoFacade());
        asistenciaLocator= (AsistenciaFacade) obtenerEJB((T) new AsistenciaFacade());
        plaasisLocator= (PlaasisFacade) obtenerEJB((T) new PlaasisFacade());
        reporteAsistenciaLocator= (ReporteAsistenciaFacade) obtenerEJB((T) new ReporteAsistenciaFacade());
        reporteCIndividualLocator= (ReporteCIndividualFacade) obtenerEJB((T) new ReporteCIndividualFacade());
        cecoLocator=(CecoFacade) obtenerEJB((T) new CecoFacade());
        grupoLocator=(GrupoCapFacade) obtenerEJB((T) new GrupoCapFacade());
        temaGrupoCapLocator=(TemaGrupoCapFacade) obtenerEJB((T) new TemaGrupoCapFacade());
        liderLocator=(LiderFacade) obtenerEJB((T) new LiderFacade());
        rolLocator=(RolFacade) obtenerEJB((T) new RolFacade());
//        Areas area = new Areas();
//        System.out.println(alo());
//        area = areasLocator.find(1);
//        System.out.println(area.getDesarea());
    }

    public CursoFacade getCursoLocator() {
        return CursoLocator;
    }

    public AsistenciaFacade getAsistenciaLocator() {
        return asistenciaLocator;
    }

    public ReporteCIndividualFacade getReporteCIndividualLocator() {
        return reporteCIndividualLocator;
    }

    public void setReporteCIndividualLocator(ReporteCIndividualFacade reporteCIndividualLocator) {
        this.reporteCIndividualLocator = reporteCIndividualLocator;
    }

    public CecoFacade getCecoLocator() {
        return cecoLocator;
    }

    public void setCecoLocator(CecoFacade cecoLocator) {
        this.cecoLocator = cecoLocator;
    }


    public ReporteAsistenciaFacade getReporteAsistenciaLocator() {
        return reporteAsistenciaLocator;
    }

    public void setReporteAsistenciaLocator(ReporteAsistenciaFacade reporteAsistenciaLocator) {
        this.reporteAsistenciaLocator = reporteAsistenciaLocator;
    }

    public void setAsistenciaLocator(AsistenciaFacade asistenciaLocator) {
        this.asistenciaLocator = asistenciaLocator;
    }

    public PlaasisFacade getPlaasisLocator() {
        return plaasisLocator;
    }

    public void setPlaasisLocator(PlaasisFacade plaasisLocator) {
        this.plaasisLocator = plaasisLocator;
    }

    public void setCursoLocator(CursoFacade CursoLocator) {
        this.CursoLocator = CursoLocator;
    }
    public AreasFacade getAreasLocator() {
        return areasLocator;
    }
    public T obtenerEJB(T clase) throws NamingException
    {
        Properties properties = new Properties();
        Context context = new InitialContext(properties);
        return (T) context.lookup(contexto+clase.getClass().getSimpleName());
    }
    public String alo(){
        return "aaaa";
    }

    public CapacitadoFacade getCapacitadoLocator() {
        return capacitadoLocator;
    }

    public InstructorFacade getInstructorLocator() {
        return instructorLocator;
    }

    public PlantillaevaluacionFacade getPlantillaevaluacionLocator() {
        return plantillaevaluacionLocator;
    }

    public PreguntaFacade getPreguntaLocator() {
        return preguntaLocator;
    }

    public PrincipiosGmsFacade getPrincipiosGmsLocator() {
        return principiosGmsLocator;
    }

    public UsuarioFacade getUsuarioLocator() {
        return usuarioLocator;
    }

    public void setUsuarioLocator(UsuarioFacade usuarioLocator) {
        this.usuarioLocator = usuarioLocator;
    }

    public PrincipiosGmsFacade getPrincipiosLocator() {
        return principiosLocator;
    }

    public CentroentrenamientoFacade getCentroentrenamientoLocator() {
        return centroentrenamientoLocator;
    }

    public void setCentroentrenamientoLocator(CentroentrenamientoFacade centroentrenamientoLocator) {
        this.centroentrenamientoLocator = centroentrenamientoLocator;
    }

    public RespuestaFacade getRespuestaLocator() {
        return respuestaLocator;
    }

    public RespuestascapacitadoFacade getRespuestascapacitadoLocator() {
        return respuestascapacitadoLocator;
    }

    public TemaFacade getTemaLocator() {
        return temaLocator;
    }

    public TipodecapacitacionFacade getTipodecapacitacionLocator() {
        return tipodecapacitacionLocator;
    }

    public TipoevaluacionFacade getTipoevaluacionLocator() {
        return tipoevaluacionLocator;
    }

    public TurnoFacade getTurnoLocator() {
        return turnoLocator;
    }

    public PlantillaCursoFacade getPlantillaCursoLocator() {
        return plantillaCursoLocator;
    }

    public void setPlantillaCursoLocator(PlantillaCursoFacade plantillaCursoLocator) {
        this.plantillaCursoLocator = plantillaCursoLocator;
    }

    public GrupoCapFacade getGrupoLocator() {
        return grupoLocator;
    }

    public void setGrupoLocator(GrupoCapFacade grupoLocator) {
        this.grupoLocator = grupoLocator;
    }

    public TemaGrupoCapFacade getTemaGrupoCapLocator() {
        return temaGrupoCapLocator;
    }

    public void setTemaGrupoCapLocator(TemaGrupoCapFacade temaGrupoCapLocator) {
        this.temaGrupoCapLocator = temaGrupoCapLocator;
    }

    public LiderFacade getLiderLocator() {
        return liderLocator;
    }

    public void setLiderLocator(LiderFacade liderLocator) {
        this.liderLocator = liderLocator;
    }

    public RolFacade getRolLocator() {
        return rolLocator;
    }

    public void setRolLocator(RolFacade rolLocator) {
        this.rolLocator = rolLocator;
    }

    
    
    
    
}
