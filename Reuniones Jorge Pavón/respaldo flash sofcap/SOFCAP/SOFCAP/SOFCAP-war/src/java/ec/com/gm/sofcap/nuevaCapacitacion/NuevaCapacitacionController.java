/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.nuevaCapacitacion;

import com.customerapp.ejb.pruebita;
import ec.com.gm.sofcap.Menu.MenuDataManager;
import ec.com.gm.sofcap.dto.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ec.com.gm.sofcap.ejb.instances.EJBInstances;
import ec.com.gm.sofcap.service.AbstractFacade;
import ec.com.gm.sofcap.service.AreasFacade;
import ec.com.gm.sofcap.service.PlaasisFacade;
import ec.com.gm.sofcap.util.MessageController;
import ec.com.gm.sofcap.views.ReporteAsistencia;
import ec.com.gm.sofcap.views.ReporteCIndividual;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Guz
 */
@ManagedBean(name = "nuevaCapacitacionController")
@ViewScoped
public class NuevaCapacitacionController implements Serializable {

    @ManagedProperty(value = "#{nuevaCapacitacionDataManager}")
    private NuevaCapacitacionDataManager nuevaCapacitacionDataManager;
    @ManagedProperty(value="#{menuDataManager}")
    private MenuDataManager menuDataManager;
    @ManagedProperty(value = "#{ejbInstances}")
    private EJBInstances ejbInstances;
    Collection<Asistencia> asistentes = new ArrayList<Asistencia>();
    private int tamanio;
    PlantillaCurso plantillaCurso = new PlantillaCurso();
    Curso curso;
    Instructor plantillaInstructor;
    AreasFacade areasLocator;
    private Integer capacitadoSeleccionado = 0;
    private Plaasis asistenteEvaluado;
    private HashMap evaluaciones;

//    @PostConstruct
    public void initialize() {
        nuevaCapacitacionDataManager.setPlanificacionCurso(new PlantillaCurso());
        nuevaCapacitacionDataManager.setCurso(new Curso());
//        nuevaCapacitacionDataManager.getCurso().setTema(new Tema());
//        nuevaCapacitacionDataManager.getCurso().setInstructor(new Instructor());
//        nuevaCapacitacionDataManager.getCurso().getInstructor().setCodinstr(1);
//        nuevaCapacitacionDataManager.getCurso().setIdCurso(1);
        nuevaCapacitacionDataManager.setListaCmbAreas(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaCmbAreas().add(new SelectItem(0, "Seleccione un Área"));
        for (Areas area : ejbInstances.getAreasLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaCmbAreas().add(new SelectItem((area.getCodarea()), area.getDesarea()));
        }

        nuevaCapacitacionDataManager.setListaTemas(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem(0, "Seleccione un principio"));
        nuevaCapacitacionDataManager.setListaInstructores(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaInstructores().add(new SelectItem(0, "Seleccione un principio"));
        nuevaCapacitacionDataManager.setListaPrincipios(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaPrincipios().add(new SelectItem(0, "Seleccione un principio"));
        for (PrincipiosGms principiosGms : ejbInstances.getPrincipiosGmsLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaPrincipios().add(new SelectItem((principiosGms.getCodprinci()), principiosGms.getDesprinci()));
        }
        nuevaCapacitacionDataManager.setListaTipoCurso(new ArrayList<SelectItem>());
        for (Tipodecapacitacion tipodeCurso : ejbInstances.getTipodecapacitacionLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaTipoCurso().add(new SelectItem((tipodeCurso.getCodtipcap()), tipodeCurso.getNomtipcap()));
        }
        nuevaCapacitacionDataManager.setListaTurnos(new ArrayList<SelectItem>());
        for (Turno turno : ejbInstances.getTurnoLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaTurnos().add(new SelectItem(turno.getCodturno(), turno.getDesturno()));

        }
//        nuevaCapacitacionDataManager.setListaInstructores(new ArrayList<SelectItem>());
//        for (Instructor intructor: ejbInstances.getInstructorLocator().findAll())
//        {
//            nuevaCapacitacionDataManager.getListaInstructores().add(new SelectItem(intructor.getCodinstr() , intructor. getNominstr()));
//        }
//        nuevaCapacitacionDataManager.setListaAreas(ejbInstances.getAreasLocator().findAll());
        nuevaCapacitacionDataManager.setListaCentroEntren(new ArrayList<SelectItem>());
        for (Centroentrenamiento centroEntrenamiento : ejbInstances.getCentroentrenamientoLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaCentroEntren().add(new SelectItem(centroEntrenamiento.getCodcenent(), centroEntrenamiento.getDescenent()));
        }
    }
    
    //funcion que setea la bandera de la vista para contraer los registros sanitarios
    public void expandirAsistentes(ReporteAsistencia reporteAsistencia){
	reporteAsistencia.setSelected(Boolean.TRUE);
        System.out.println("Expandiendo");
        reporteAsistencia.setAsistentesPlanificacionCol(ejbInstances.getPlaasisLocator().buscarAsistentesPlantilla(reporteAsistencia));
        
    }
    //funcion que setea la bandera de la vista para expandir los registros sanitarios
    public void contraerAsistentes(ReporteAsistencia reporteAsistencia){
    	reporteAsistencia.setSelected(Boolean.FALSE);
        System.out.println("Contrayendo");
        
    }

    public void cargarCapacitadores() {
        nuevaCapacitacionDataManager.setListaInstructorVista(ejbInstances.getInstructorLocator().findAll());
    }

    public void anadirCapacitadoresPlanificacion() {
        Plains plains;
        for (Instructor instructor : nuevaCapacitacionDataManager.getListaInstructorVista()) {
            if (instructor.getSeleccionado()) {
                plains = new Plains();
                plains.setPlainsPK(new PlainsPK());
                plains.getPlainsPK().setCodinstr(instructor.getCodinstr());
                System.out.println("Código Instructor: " + instructor.getCodinstr() + "Nombre Instructor" + instructor.getNominstr());
                nuevaCapacitacionDataManager.getPlanificacionCurso().getPlainsCollection().add(plains);
            }
        }
    }

    public void cargarTemas(AjaxBehaviorEvent event) {
        //Obtiene lista de ciudades por pais
        System.out.println("valor de la variable: " + nuevaCapacitacionDataManager.getCodigoPrincipio());
        nuevaCapacitacionDataManager.setListaTemas(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem(null, "Seleccione"));
        for (Tema tema : ejbInstances.getTemaLocator().obtenerTemasPrincipio(nuevaCapacitacionDataManager.getCodigoPrincipio())) {
            nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem((tema.getCodtema()), tema.getNomtema()));
        }
    }

    public void generarReporteCartillaIndividualFiltrosCompuestos() {
        try {
            System.out.println(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().getCodgm());
            nuevaCapacitacionDataManager.setColReporteCIndividual(ejbInstances.getReporteCIndividualLocator().generarReporteCIndividual(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte()));
            Capacitado plantillaCapacitado = new Capacitado();
            plantillaCapacitado.setCodgm(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().getCodgm());
            Collection<Capacitado> capacitados = ejbInstances.getCapacitadoLocator().simpleTemplateSearch(plantillaCapacitado);
            if (capacitados.size() > 0) {
                nuevaCapacitacionDataManager.setCapacitadoPln(capacitados.iterator().next());
            }

        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarFiltros(AjaxBehaviorEvent e) {
        nuevaCapacitacionDataManager.setPlanificacionAsistenciaReporte(new ReporteCIndividual());
    }

    public void generarReporteCartillaIndividual() {
        try {
            System.out.println(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().getCodgm());
            nuevaCapacitacionDataManager.setColReporteCIndividual(ejbInstances.getReporteCIndividualLocator().generarReporteCIndividualCodGM(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte()));
            Capacitado plantillaCapacitado = new Capacitado();
            plantillaCapacitado.setCodgm(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().getCodgm());
            Collection<Capacitado> capacitados = ejbInstances.getCapacitadoLocator().simpleTemplateSearch(plantillaCapacitado);
            if (capacitados.size() > 0) {
                nuevaCapacitacionDataManager.setCapacitadoPln(capacitados.iterator().next());
            }

        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarInstructores(AjaxBehaviorEvent event) {
        //Obtiene lista de ciudades por pais
    }

    public String cargarTiposReportes() {
//        nuevaCapacitacionDataManager.setListaAreas(ejbInstances.getAreasLocator().contarAsistentes());
        return "/Capacitacion/opcionesReporte?faces-redirect=true";
    }

    public void generarReportePlanificadoAsistencia() {
        try {
            nuevaCapacitacionDataManager.setCollReporteAsistencia(ejbInstances.getReporteAsistenciaLocator().generarReporteAsistencia(nuevaCapacitacionDataManager.getPlantillaReporteAsistencia()));
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String redireccionarReportePlanificadoAsistencia() {
        try {
            Calendar fechaInicio = Calendar.getInstance(), fechaFin = Calendar.getInstance();
            nuevaCapacitacionDataManager.setPlantillaReporteAsistencia(new ReporteAsistencia());
            fechaInicio.set(Calendar.DAY_OF_MONTH, 1);
            fechaInicio.set(Calendar.HOUR_OF_DAY, 0);
            fechaInicio.set(Calendar.MINUTE, 00);
            fechaFin.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
            fechaFin.set(Calendar.HOUR_OF_DAY, 24);
            fechaFin.set(Calendar.MINUTE, 00);
            nuevaCapacitacionDataManager.getPlantillaReporteAsistencia().setFechaInicio(fechaInicio.getTime());
            nuevaCapacitacionDataManager.getPlantillaReporteAsistencia().setFechaFin(fechaFin.getTime());
//        plantillaCurso.setFechaInicio(fechaInicio.getTime());
//        plantillaCurso.setFechaFin(fechaFin.getTime());
//            nuevaCapacitacionDataManager.setCollReporteAsistencia(ejbInstances.getReporteAsistenciaLocator().simpleTemplateSearch(new ReporteAsistencia()));
            nuevaCapacitacionDataManager.setListaTemas(new ArrayList<SelectItem>());
            nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem(null, "Seleccione"));
            for (Tema tema : ejbInstances.getTemaLocator().obtenerTemasPrincipio(null)) {
                nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem((tema.getCodtema()), tema.getNomtema()));
            }
            nuevaCapacitacionDataManager.setListaCecos(new ArrayList<SelectItem>());
            nuevaCapacitacionDataManager.getListaCecos().add(new SelectItem(0, "Todos"));
            for (Ceco ceco : ejbInstances.getCecoLocator().findAll()) {
                nuevaCapacitacionDataManager.getListaCecos().add(new SelectItem(ceco.getCodceco(), ceco.getCodceco()));
            }
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return "/Capacitacion/reportePlanificadosAsistentes?faces-redirect=true";
    }

    public String redireccionarCIndividual() {
        nuevaCapacitacionDataManager.setListaTemas(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem(null, "Seleccione"));
        for (Tema tema : ejbInstances.getTemaLocator().obtenerTemasPrincipio(null)) {
            nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem((tema.getCodtema()), tema.getNomtema()));
        }
        nuevaCapacitacionDataManager.setListaCecos(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaCecos().add(new SelectItem(null, "Todos"));
        for (Ceco ceco : ejbInstances.getCecoLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaCecos().add(new SelectItem(ceco.getCodceco(), ceco.getCodceco()));
        }
        nuevaCapacitacionDataManager.setPlanificacionAsistenciaReporte(new ReporteCIndividual());
        return "/Capacitacion/reporteCartillaIndividual?faces-redirect=true";
    }

    public String redireccionarReporteAsistencia() {
        nuevaCapacitacionDataManager.setCurso(new Curso());
        plantillaCurso = new PlantillaCurso();
        return "/Capacitacion/reporteAsistentes?faces-redirect=true";
    }

    public String redireccionarReporteInstructor() {
        nuevaCapacitacionDataManager.setCurso(new Curso());
        return "/Capacitacion/reporteInstructores?faces-redirect=true";
    }

    public void reporteInstructor() {

        nuevaCapacitacionDataManager.setListaInstructorVista(ejbInstances.getInstructorLocator().generarReporteInstructor(nuevaCapacitacionDataManager.getCurso()));

    }

    public void reporteAsistencia() {
        nuevaCapacitacionDataManager.setListaAreas(ejbInstances.getAreasLocator().contarAsistentes(nuevaCapacitacionDataManager.getCurso()));
    }

    public void cargarDatosCapacitacion(AjaxBehaviorEvent event) {
        System.out.println("Se muestran los datos de la capacitación, variable de plantilla: " + nuevaCapacitacionDataManager.getCurso().getId_plantilla());
        
        nuevaCapacitacionDataManager.setFiltroBusquedaCap(100D);
        PlantillaCurso plantillaCursoBusq = new PlantillaCurso();
        plantillaCursoBusq.setIdPlantilla(nuevaCapacitacionDataManager.getCurso().getId_plantilla());
        nuevaCapacitacionDataManager.getCurso().setCodcenent(6);
        Calendar fechCalendar = Calendar.getInstance();
        nuevaCapacitacionDataManager.getCurso().setFechaInicio(fechCalendar.getTime());
        fechCalendar.set(Calendar.HOUR_OF_DAY, fechCalendar.get(Calendar.HOUR_OF_DAY) + 1);
        nuevaCapacitacionDataManager.getCurso().setFechaFin(fechCalendar.getTime());
        System.out.println("--" + nuevaCapacitacionDataManager.getCurso().getCodcenent());
        System.out.println("--" + nuevaCapacitacionDataManager.getCurso().getFechaInicio());
        System.out.println("--" + nuevaCapacitacionDataManager.getCurso().getFechaFin());
        nuevaCapacitacionDataManager.setPlanificacionCurso((PlantillaCurso) ejbInstances.getPlantillaCursoLocator().buscarPlanificacionConInstructor(plantillaCursoBusq));

        System.out.println("valor de la variable del tema: " + nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema());

        nuevaCapacitacionDataManager.setListaInstructores(new ArrayList<SelectItem>());
        for (Plains plaIns : nuevaCapacitacionDataManager.getPlanificacionCurso().getPlainsCollection()) {
            nuevaCapacitacionDataManager.getListaInstructores().add(new SelectItem((plaIns.getInstructor().getCodinstr()), plaIns.getInstructor().getNominstr()));
        }
    }

    public String guardarPlantillaCurso() {
        try {
            System.out.println("Va a crear la plantilla curso");
            if (nuevaCapacitacionDataManager.getPlanificacionCurso().getPlainsCollection().size() > 0) {
//        ejbInstances.getTemaLocator().find(nuevaCapacitacionDataManager..getIdCurso()getTema());
                ejbInstances.getPlantillaCursoLocator().guardarPlanificacionCurso(nuevaCapacitacionDataManager.getPlanificacionCurso());
            } else {
                MessageController.addError(null, "Error: debe seleccionar los instructores primero.");
                return "";
            }
            System.out.println("Creo curso id " + nuevaCapacitacionDataManager.getPlanificacionCurso().getIdPlantilla());
            return "/Menuprincipal?faces-redirect=true";
        } catch (Exception e) {
            MessageController.addError("", "Error al crear el curso");
            System.out.println("Error al crear un curso nuevo");
        }
        return "";

    }

    public String guardarPostulantes() {
     
        
        Collection<Plaasis> postulantesAsistentes = new ArrayList<Plaasis>();
         for (ReporteCIndividual capacitado : nuevaCapacitacionDataManager.getColReporteCIndividual()) {
            if (capacitado.getSeleccionado()) {
                    Plaasis plaasis = new Plaasis();
                    plaasis.setPlaasisPK(new PlaasisPK());
                    plaasis.getPlaasisPK().setCedcapac(capacitado.getCedcapac());
                    plaasis.getPlaasisPK().setIdPlantilla(nuevaCapacitacionDataManager.getPlanificacionCurso().getIdPlantilla());
                    postulantesAsistentes.add(plaasis);
                }
            }
            if (postulantesAsistentes.size() > 0) {
            ejbInstances.getPlaasisLocator().createAll(postulantesAsistentes);
            MessageController.addInfo(null, "Los asistentes se añadieron correctamente");
            return "/Menuprincipal?faces-redirect=true";
        } else {
            MessageController.addError(null, "Error: debe planificar asistentes para este curso.");
            return "";
        }


    }

    public String guardarCurso() {
        try {
            System.out.println("Va a tomar lista asistentes");
            if (nuevaCapacitacionDataManager.getListaAsistentes().size()>0){
//        ejbInstances.getTemaLocator().find(nuevaCapacitacionDataManager..getIdCurso()getTema());
            ejbInstances.getCursoLocator().actualizarCurso(nuevaCapacitacionDataManager.getCurso(), nuevaCapacitacionDataManager.getListaAsistentes());
//        ejbInstances.getAsistenciaLocator().createAll(nuevaCapacitacionDataManager.getCurso().getAsistenciaCollection());
            System.out.println("Creo curso id " + nuevaCapacitacionDataManager.getCurso().getIdCurso());
            return "/Menuprincipal?faces-redirect=true";
            }else{
                MessageController.addError(null, "Error: Por favor, seleccione asistentes antes de guardar");
                return "";
            }
        } catch (Exception e) {
            MessageController.addError("", "Error al crear el curso");
            System.out.println("Error al crear un curso nuevo");
        }
        return "";

    }

    public String actualizarCurso() {
        try {
            System.out.println("Va a actualizar curso");
//        ejbInstances.getTemaLocator().find(nuevaCapacitacionDataManager..getIdCurso()getTema());
            //ejbInstances.getCursoLocator().actualizarCurso(nuevaCapacitacionDataManager.getCurso());
            System.out.println("Actualizo curso id " + nuevaCapacitacionDataManager.getCurso().getIdCurso());
            return "/Menuprincipal?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Error al actualizar un curso ");
        }
        return "";

    }

    public void buscarCapacitaciones() {
//        nuevaCapacitacionDataManager.getCurso().setAsistenciaCollection(new ArrayList<Asistencia>());
//        nuevaCapacitacionDataManager.getCurso().getAsistenciaCollection().add(new Asistencia());
        nuevaCapacitacionDataManager.getCurso().setCursosevaluacionCollection(new ArrayList<CursosEvaluacion>());
        nuevaCapacitacionDataManager.setCursos(ejbInstances.getCursoLocator().obtenerInstructorTemasPlantilla(nuevaCapacitacionDataManager.getCurso()));

        if (nuevaCapacitacionDataManager.getCursos().isEmpty()) {
            MessageController.addError(null, "No se encontraron capacitaciones que cumplan con los criterios de búsqueda");
        }
    }

    public String getForm() {
        if (this.getNuevaCapacitacionDataManager() != null) {
            if (!this.getNuevaCapacitacionDataManager().getEstaInicializado()) {
                System.out.println("Entro a método de obtener el form");
                this.initialize();
                this.getNuevaCapacitacionDataManager().setEstaInicializado(Boolean.TRUE);
            }
        }
        return "frmNuevaCap";
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public String volverMenu() {
        System.out.println("Retornando al menú principal");
//        nuevaCapacitacionDataManager.setPasoFlujo("");
        return "/Menuprincipal?faces-redirect=true";
    }

    public void obtenerReporteN1() {
        evaluaciones = new HashMap<String, Double>();
        Respuestascapacitado respuestasTabular = new Respuestascapacitado();
        respuestasTabular.setRespuestascapacitadoPK(new RespuestascapacitadoPK());
        respuestasTabular.getRespuestascapacitadoPK().setIdCurso(nuevaCapacitacionDataManager.getCurso().getIdCurso());
        Collection<Respuestascapacitado> respuestasN3 = ejbInstances.getRespuestascapacitadoLocator().obtenerRespuestasCapacitadoPlantilla(respuestasTabular);
        for (int i = 0; i < 12; i++) {
            evaluaciones.put("" + i, 0.00);
            System.out.println(i + "  Valor anterior:" + evaluaciones.get(i));
        }
        for (Respuestascapacitado respuestasCapacitado : respuestasN3) {
            System.out.println("Valor anterior:" + evaluaciones.get(respuestasCapacitado.getRespuesta().getPregunta().getCodprg()));
            System.out.println("CodPRG:" + respuestasCapacitado.getRespuesta().getPregunta().getCodprg());
            System.out.println("Valor:" + respuestasCapacitado.getRespuesta().getDesres());
            evaluaciones.put(respuestasCapacitado.getRespuesta().getPregunta().getCodprg(), Double.parseDouble(evaluaciones.get(respuestasCapacitado.getRespuesta().getPregunta().getCodprg()) + respuestasCapacitado.getRespuesta().getDesres()));
            if (respuestasCapacitado.getRespuestascapacitadoPK().getCodprg() == 1
                    || respuestasCapacitado.getRespuestascapacitadoPK().getCodprg() == 2) {
                evaluaciones.put("9", Double.parseDouble(evaluaciones.get(9) + respuestasCapacitado.getRespuesta().getDesres()));
            }
            if (respuestasCapacitado.getRespuestascapacitadoPK().getCodprg() == 3
                    || respuestasCapacitado.getRespuestascapacitadoPK().getCodprg() == 4) {
                evaluaciones.put("10", Double.parseDouble(evaluaciones.get(10) + respuestasCapacitado.getRespuesta().getDesres()));
            }
            if (respuestasCapacitado.getRespuestascapacitadoPK().getCodprg() == 5
                    || respuestasCapacitado.getRespuestascapacitadoPK().getCodprg() == 6
                    || respuestasCapacitado.getRespuestascapacitadoPK().getCodprg() == 7
                    || respuestasCapacitado.getRespuestascapacitadoPK().getCodprg() == 8) {
                evaluaciones.put("11", Double.parseDouble(evaluaciones.get(11) + respuestasCapacitado.getRespuesta().getDesres()));
            }
        }
        System.out.println("9:" + evaluaciones.get(9) + " 10:" + evaluaciones.get(10) + " 11:" + evaluaciones.get(11));
    }

    public String visualizarCapacitacion() {
        nuevaCapacitacionDataManager.setPaso("editar");
        nuevaCapacitacionDataManager.setPasoCapacitacion("n1");
        nuevaCapacitacionDataManager.setCapacitados(new ArrayList<Capacitado>());
        System.out.println("Id del curso: " + nuevaCapacitacionDataManager.getCurso().getIdCurso());
        System.out.println(nuevaCapacitacionDataManager.getCurso().getCursosevaluacionCollection().iterator().next().getCursosEvaluacionPK().getcodEva());
        //seteamos los valores para editar el instructor
        try {
            System.out.println("se encontraron curso: " + nuevaCapacitacionDataManager.getCursos().size());
            nuevaCapacitacionDataManager.setPlanificacionCurso((PlantillaCurso) ejbInstances.getPlantillaCursoLocator().buscarPlanificacionConInstructor(nuevaCapacitacionDataManager.getCurso().getPlantillaCurso()));
            Plaasis plantillaPlaasis = new Plaasis();
            plantillaPlaasis.setPlaasisPK(new PlaasisPK());
            plantillaPlaasis.getPlaasisPK().setIdPlantilla(nuevaCapacitacionDataManager.getPlanificacionCurso().getIdPlantilla());
            nuevaCapacitacionDataManager.getCurso().getPlantillaCurso().setPlaasisCollection(ejbInstances.getPlaasisLocator().buscarAsistentes(plantillaPlaasis));
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("valor de la variable del tema: " + nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema());
        nuevaCapacitacionDataManager.setListaInstructores(new ArrayList<SelectItem>());
        for (Plains plaIns : nuevaCapacitacionDataManager.getPlanificacionCurso().getPlainsCollection()) {
            nuevaCapacitacionDataManager.getListaInstructores().add(new SelectItem((plaIns.getInstructor().getCodinstr()), plaIns.getInstructor().getNominstr()));
        }


        return "/Capacitacion/nuevaCapacitacion?faces-redirect=true";
    }

    public void nuevoCapacitado() {
        Capacitado capacitado = new Capacitado();
        nuevaCapacitacionDataManager.setListaCapacitados(ejbInstances.getCapacitadoLocator().buscarCapacitadoCurso(nuevaCapacitacionDataManager.getPlanificacionCurso()));
        System.out.println("Se inicializó la plantilla de búsqueda");
        //nuevaCapacitacionDataManager.getCapacitados().add(new Capacitado());
    }

    public void anadirCapacitados() { 
        nuevaCapacitacionDataManager.setListaAsistentes(new ArrayList<Asistencia>());
        Asistencia asistenteTMP;
        for (Capacitado capacitado : nuevaCapacitacionDataManager.getListaCapacitados()) {
            if (capacitado.getSeleccionado()) {
                asistenteTMP = new Asistencia();
                asistenteTMP.setAsistenciaPK(new AsistenciaPK());
                asistenteTMP.getAsistenciaPK().setCedcapac(capacitado.getCedcapac());
                asistenteTMP.getAsistenciaPK().setIdPlantilla(nuevaCapacitacionDataManager.getCurso().getId_plantilla());
                nuevaCapacitacionDataManager.getListaAsistentes().add(asistenteTMP);
            }
        }
    }

    public void asignarPreguntasNivel1() {
        try {
            System.out.println("Tamano de las preguntas" + nuevaCapacitacionDataManager.getCurso().getCursosevaluacionCollection().iterator().next().getPlantillaEvaluacion().getPreguntaCollection().size());
            nuevaCapacitacionDataManager.setPreguntas(nuevaCapacitacionDataManager.getCurso().getCursosevaluacionCollection().iterator().next().getPlantillaEvaluacion().getPreguntaCollection());
            System.out.println("Tamano de las respuestas 1: " + nuevaCapacitacionDataManager.getPreguntas().iterator().next().getRespuestaCollection().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Se asignaron las preguntas de " + nuevaCapacitacionDataManager.getCurso().getCursosevaluacionCollection().iterator().next().getPlantillaEvaluacion().getDespla());
        //nuevaCapacitacionDataManager.getCapacitados().add(new Capacitado());
    }

    public void generarReporteNivel1() {
//          nuevaCapacitacionDataManager.setPreguntas(new ArrayList<Pregunta>());
        try {
            System.out.println("Generando reporte nivel 1");
            Respuestascapacitado respuestasPlantilla = new Respuestascapacitado();
            respuestasPlantilla.setRespuestascapacitadoPK(new RespuestascapacitadoPK());
            respuestasPlantilla.getRespuestascapacitadoPK().setIdCurso(nuevaCapacitacionDataManager.getCurso().getIdCurso());
            System.out.println("Curso: " + nuevaCapacitacionDataManager.getCurso().getIdCurso());
            Collection<Respuestascapacitado> listaRespuestas = ejbInstances.getRespuestascapacitadoLocator().obtenerRespuestasCapacitadoPlantilla(respuestasPlantilla);

            nuevaCapacitacionDataManager.setPreguntas(ejbInstances.getPreguntaLocator().simpleTemplateSearch(new Pregunta()));
            for (Pregunta pregunta : nuevaCapacitacionDataManager.getPreguntas()) {
                pregunta.setMedia(0D);
                pregunta.setPorcentaje(0D);
                int max = 0;
                int i = 0;
                for (Respuestascapacitado RCap : listaRespuestas) {
                    if (pregunta.getCodprg() == RCap.getRespuestascapacitadoPK().getCodprg()) {
                        pregunta.setMedia(pregunta.getMedia() + Double.parseDouble(RCap.getRespuesta().getDesres()));
                        pregunta.setPorcentaje(pregunta.getPorcentaje() + Double.parseDouble(RCap.getRespuesta().getDesres()));
                        max++;
                        System.out.println(i + "****" + pregunta.getMedia() + "***valores de la respuesta: " + RCap.getRespuestascapacitadoPK().getCodprg() + "valores de la respuesta: " + RCap.getRespuesta().getPregunta().getCabprg() + "-----" + RCap.getRespuesta().getDesres() + "----Curso: " + RCap.getRespuestascapacitadoPK().getIdCurso());
                    }
                    i++;

                }
                pregunta.setMedia(pregunta.getMedia() / (max));
                System.out.println("Dividiendo:" + pregunta.getMedia() + "/" + max);
                pregunta.setPorcentaje(pregunta.getPorcentaje() * 100 / (4 * max));
                System.out.println("Porcentaje:" + pregunta.getPorcentaje() * 100 + "/" + 4 * max);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Se asignaron las preguntas de " + nuevaCapacitacionDataManager.getCurso().getCursosevaluacionCollection().iterator().next().getPlantillaEvaluacion().getDespla());
        System.out.println("Curso: " + nuevaCapacitacionDataManager.getCurso().getIdCurso());
        //nuevaCapacitacionDataManager.getCapacitados().add(new Capacitado());
    }

    public void guardarRespuestasNivel1() {

        Respuestascapacitado respuestasSeleccionadas = new Respuestascapacitado();
        ArrayList<Asistencia> capacitadoActual = new ArrayList<Asistencia>();
//          nuevaCapacitacionDataManager.getCurso().getAsistenciaCollection()
        if (capacitadoSeleccionado < capacitadoActual.size()) {
            Collection< Respuestascapacitado> respuestasCapacitados = new ArrayList<Respuestascapacitado>();
            for (Pregunta pregunta : nuevaCapacitacionDataManager.getPreguntas()) {
                respuestasSeleccionadas = new Respuestascapacitado();
                respuestasSeleccionadas.setRespuestascapacitadoPK(new RespuestascapacitadoPK());
                respuestasSeleccionadas.getRespuestascapacitadoPK().setCodres(pregunta.getRespuestaAux());
                respuestasSeleccionadas.getRespuestascapacitadoPK().setCodprg(pregunta.getCodprg());
                respuestasSeleccionadas.getRespuestascapacitadoPK().setIdCurso(nuevaCapacitacionDataManager.getCurso().getIdCurso());
                respuestasSeleccionadas.getRespuestascapacitadoPK().setCedcapac(capacitadoActual.get(capacitadoSeleccionado).getAsistenciaPK().getCedcapac());
                respuestasCapacitados.add(respuestasSeleccionadas);
            }
            MessageController.addInfo(null, "Se añadio una nueva evaluación nivel 1");
            ejbInstances.getRespuestascapacitadoLocator().createAll(respuestasCapacitados);
            capacitadoSeleccionado++;
        } else {
            MessageController.addError("", "Error ha sobrepasado la cantidad de evaluaciones a cargar en relación a la cantidad de asistentes a este curso");
        }
    }

    public void anadirCapacitado() {
        try {
            Capacitado capacitado = ejbInstances.getCapacitadoLocator().simpleTemplateSearch(nuevaCapacitacionDataManager.getCapacitadoPln()).iterator().next();
            if (capacitado != null) {
                Asistencia asistencia = new Asistencia();
//                asistencia .setCedcapac(capacitado.getCedcapac());
//                asistencia.setId_curso(nuevaCapacitacionDataManager.getCurso().getIdCurso());
                System.out.println("ced" + capacitado.getCedcapac() + "idcurso" + nuevaCapacitacionDataManager.getCurso().getIdCurso());
//                asistencia.setCapacitado(capacitado);
//                nuevaCapacitacionDataManager.getCurso().getAsistenciaCollection().add(asistencia);
            } else {
                System.out.println("no se encontró asistente");
                MessageController.addError(null, "Error no se ha encontrado ningún capacitado");
            }
            //nuevaCapacitacionDataManager.setCapacitadoPln(new Capacitado());
            //nuevaCapacitacionDataManager.getCapacitados().add(new Capacitado());
            //nuevaCapacitacionDataManager.setCapacitadoPln(new Capacitado());
            //nuevaCapacitacionDataManager.getCapacitados().add(new Capacitado());
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
            MessageController.addError(null, "Error no se ha encontrado ningún empleado con este código.");
        }
    }

    /**
     * Creates a new instance of nuevaCapacitacionController
     */
    public NuevaCapacitacionController() {
        try {
            System.out.println("Se inicializa el controlador");

//        System.out.println(getEjbInstances().alo());


            //System.out.println(pruebita.alo());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        //System.out.println(tamanio);
    }

    public String nuevaCapacitacion() {
        Calendar fechaInicio = Calendar.getInstance(), fechaFin = Calendar.getInstance();
        nuevaCapacitacionDataManager.setPaso("editar");
        nuevaCapacitacionDataManager.setPasoCapacitacion("nuevo");
        nuevaCapacitacionDataManager.setCurso(new Curso());
        nuevaCapacitacionDataManager.setPasoFlujo("nueCap");
        nuevaCapacitacionDataManager.setListaAsistentes(new ArrayList<Asistencia>());
        //inicializamos los asistentes
//        nuevaCapacitacionDataManager.getCurso().setAsistenciaCollection(new ArrayList<Asistencia>());
        nuevaCapacitacionDataManager.setPlanificacionCurso(null);
        System.out.println("redireccionando a nueva capacitación");
        PlantillaCurso plantillaCurso = new PlantillaCurso();
        fechaInicio.set(Calendar.DAY_OF_MONTH, 1);
        fechaFin.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        plantillaCurso.setFechaInicio(fechaInicio.getTime());
        plantillaCurso.setFechaFin(fechaFin.getTime());
//        plantillaCurso.setCodtema();
        //System.out.println();
        System.out.println(plantillaCurso.getFechaInicio());
        System.out.println(plantillaCurso.getFechaFin());
        System.out.println(plantillaCurso.getCodtema());
        System.out.println(plantillaCurso.getCodtipcap());
        nuevaCapacitacionDataManager.setListaPlanificacionCursos(new ArrayList<SelectItem>());
        for (PlantillaCurso planificaciones : ejbInstances.getPlantillaCursoLocator().obtenerPlanificacionCursoPlantilla(plantillaCurso)) {
            nuevaCapacitacionDataManager.getListaPlanificacionCursos().add(new SelectItem(planificaciones.getIdPlantilla(), planificaciones.getTema().getNomtema()));
        }
        return "Capacitacion/nuevaCapacitacion?faces-redirect=true";
    }

    public String seleccionarAsistentes() {
        Calendar fechaInicio = Calendar.getInstance(), fechaFin = Calendar.getInstance();
        nuevaCapacitacionDataManager.setPaso("editar");
        nuevaCapacitacionDataManager.setPasoCapacitacion("nuevo");
        nuevaCapacitacionDataManager.setCurso(new Curso());
        nuevaCapacitacionDataManager.setPasoFlujo("carCap");
        //inicializamos los asistentes
//        nuevaCapacitacionDataManager.getCurso().setAsistenciaCollection(new ArrayList<Asistencia>());
        nuevaCapacitacionDataManager.setPlanificacionCurso(null);
        System.out.println("redireccionando a nueva capacitación");
        PlantillaCurso plantillaCurso = new PlantillaCurso();
        fechaInicio.set(Calendar.DAY_OF_MONTH, 1);
        fechaFin.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        plantillaCurso.setFechaInicio(fechaInicio.getTime());
        plantillaCurso.setFechaFin(fechaFin.getTime());
//        plantillaCurso.setCodtema();
        //System.out.println();
        System.out.println(plantillaCurso.getFechaInicio());
        System.out.println(plantillaCurso.getFechaFin());
        System.out.println(plantillaCurso.getCodtema());
        System.out.println(plantillaCurso.getCodtipcap());
        nuevaCapacitacionDataManager.setListaCapacitados(new ArrayList<Capacitado>());
        nuevaCapacitacionDataManager.setListaPlanificacionCursos(new ArrayList<SelectItem>());
        for (PlantillaCurso planificaciones : ejbInstances.getPlantillaCursoLocator().obtenerPlanificacionCursoPlantilla(plantillaCurso)) {
            nuevaCapacitacionDataManager.getListaPlanificacionCursos().add(new SelectItem(planificaciones.getIdPlantilla(), planificaciones.getTema().getNomtema()));
        }
        return "Capacitacion/nuevaCapacitacion?faces-redirect=true";
    }
    //metodo que carga las capacitaciones en un combo en un determinado periodo

    public void cargarCapacitacionesPerido() {
//        plantillaCurso.setCodtema();
        //System.out.println();
        System.out.println(plantillaCurso.getFechaInicio());
        System.out.println(plantillaCurso.getFechaFin());
        nuevaCapacitacionDataManager.setListaPlanificacionCursos(new ArrayList<SelectItem>());
        for (PlantillaCurso planificaciones : ejbInstances.getPlantillaCursoLocator().obtenerPlanificacionCursoPlantilla(plantillaCurso)) {
            nuevaCapacitacionDataManager.getListaPlanificacionCursos().add(new SelectItem(planificaciones.getIdPlantilla(), planificaciones.getTema().getNomtema()));
        }
    }

    public void cargarCapacitadosPuntaje() {
       nuevaCapacitacionDataManager.setPlanificacionAsistenciaReporte(new ReporteCIndividual());
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColCeco(new Integer [] {null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTurno(new Integer [] {null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTema(new Integer [] {null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTema(new Integer [] {nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema()}); 
        try {
            nuevaCapacitacionDataManager.setColReporteCIndividual(ejbInstances.getReporteCIndividualLocator().generarReporteCIndividualRangos(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte(), nuevaCapacitacionDataManager.getFiltroBusquedaCap(),nuevaCapacitacionDataManager.getCurso().getId_plantilla()));

        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarCapacitados() {
         try {
            
            nuevaCapacitacionDataManager.setPlanificacionAsistenciaReporte(new ReporteCIndividual());
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColCeco(new Integer [] {null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTurno(new Integer [] {null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTema(new Integer [] {null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTema(new Integer [] {nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema()}); 
            nuevaCapacitacionDataManager.setColReporteCIndividual(ejbInstances.getReporteCIndividualLocator().generarReporteCIndividualRangos(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte(), nuevaCapacitacionDataManager.getFiltroBusquedaCap(),nuevaCapacitacionDataManager.getCurso().getId_plantilla()));
            

        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

    public PlantillaCurso getPlantillaCurso() {
        return plantillaCurso;
    }

    public void setPlantillaCurso(PlantillaCurso plantillaCurso) {
        this.plantillaCurso = plantillaCurso;
    }

    public String nuevaPlanificacionCapacitacion() {
        nuevaCapacitacionDataManager.setPaso("nuevo");
        nuevaCapacitacionDataManager.setPasoFlujo("nueCap");
        nuevaCapacitacionDataManager.setPlanificacionCurso(new PlantillaCurso());
        //seteamos fechas defecto
        Calendar fechaAUX = Calendar.getInstance();
        fechaAUX.set(Calendar.DAY_OF_MONTH, 1);
        nuevaCapacitacionDataManager.getPlanificacionCurso().setFechaInicio(fechaAUX.getTime());
        fechaAUX = Calendar.getInstance();
        fechaAUX.set(Calendar.DAY_OF_MONTH, fechaAUX.getActualMaximum(Calendar.DAY_OF_MONTH));
        nuevaCapacitacionDataManager.getPlanificacionCurso().setFechaFin(fechaAUX.getTime());

        nuevaCapacitacionDataManager.getPlanificacionCurso().setPlainsCollection(new ArrayList<Plains>());
        System.out.println("redireccionando a nueva planificación capacitación");
        return "Capacitacion/nuevaPlanificacion?faces-redirect=true";
    }

    public String editarCapacitacion() {
        nuevaCapacitacionDataManager.setCurso(new Curso());
        Calendar fechaAUX = Calendar.getInstance();
        fechaAUX.set(Calendar.DAY_OF_MONTH, 1);
        nuevaCapacitacionDataManager.getCurso().setFechaInicio(fechaAUX.getTime());
        fechaAUX = Calendar.getInstance();
        fechaAUX.set(Calendar.DAY_OF_MONTH, fechaAUX.getActualMaximum(Calendar.DAY_OF_MONTH));
        nuevaCapacitacionDataManager.getCurso().setFechaFin(fechaAUX.getTime());
        nuevaCapacitacionDataManager.setCursos(new ArrayList<Curso>());
        nuevaCapacitacionDataManager.setCodigoPrincipio(null);
        cargarTemas(null);
        System.out.println("redireccionando a editar capacitación");
        return "Capacitacion/buscarCapacitacion?faces-redirect=true";
    }

    public void guardarEvaluacionN3() {
        System.out.println("guardando la nivel 3");
        ejbInstances.getAsistenciaLocator().edit(asistenteEvaluado.getAsistencia());

    }

    public NuevaCapacitacionDataManager getNuevaCapacitacionDataManager() {
        return nuevaCapacitacionDataManager;
    }

    public void setNuevaCapacitacionDataManager(NuevaCapacitacionDataManager nuevaCapacitacionDataManager) {
        this.nuevaCapacitacionDataManager = nuevaCapacitacionDataManager;
    }

    public EJBInstances getEjbInstances() {
        return ejbInstances;
    }

    public void setEjbInstances(EJBInstances ejbInstances) {
        this.ejbInstances = ejbInstances;
    }

    public Integer getCapacitadoSeleccionado() {
        return capacitadoSeleccionado;
    }

    public void setCapacitadoSeleccionado(Integer capacitadoSeleccionado) {
        this.capacitadoSeleccionado = capacitadoSeleccionado;
    }

    public Plaasis getAsistenteEvaluado() {
        return asistenteEvaluado;
    }

    public void setAsistenteEvaluado(Plaasis asistenteEvaluado) {
        this.asistenteEvaluado = asistenteEvaluado;
    }

    public Instructor getPlantillaInstructor() {
        return plantillaInstructor;
    }

    public void setPlantillaInstructor(Instructor plantillaInstructor) {
        this.plantillaInstructor = plantillaInstructor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public MenuDataManager getMenuDataManager() {
        return menuDataManager;
    }

    public void setMenuDataManager(MenuDataManager menuDataManager) {
        this.menuDataManager = menuDataManager;
    }
    
}
