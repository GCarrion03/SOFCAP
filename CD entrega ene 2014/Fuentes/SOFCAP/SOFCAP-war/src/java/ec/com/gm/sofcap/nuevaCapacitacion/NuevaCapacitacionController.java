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
import ec.com.gm.sofcap.views.ReporteAsistenciaNoLider;
import ec.com.gm.sofcap.views.ReporteCIndividual;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

/**
 *
 * @author Guz
 */
@ManagedBean(name = "nuevaCapacitacionController")
@ViewScoped
public class NuevaCapacitacionController implements Serializable {

    @ManagedProperty(value = "#{nuevaCapacitacionDataManager}")
    private NuevaCapacitacionDataManager nuevaCapacitacionDataManager;
    @ManagedProperty(value = "#{menuDataManager}")
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

    //funcion que setea la bandera de la vista para contraer los asistentes
    public void expandirAsistentes(ReporteAsistencia reporteAsistencia) {
        reporteAsistencia.setSelected(Boolean.TRUE);
        System.out.println("Expandiendo");
        reporteAsistencia.setAsistentesPlanificacionCol(ejbInstances.getPlaasisLocator().buscarAsistentesPlantilla(reporteAsistencia));

    }
    
    public void expandirAsistentesNoLider(ReporteAsistenciaNoLider reporteAsistencia) {
        reporteAsistencia.setSelected(Boolean.TRUE);
        System.out.println("Expandiendo");
        reporteAsistencia.setAsistentesPlanificacionCol(ejbInstances.getPlaasisLocator().buscarAsistentesPlantillaNoLider(reporteAsistencia));

    }
    
    

    public void loadXlsPrime(org.primefaces.event.FileUploadEvent event) throws Exception {
        try {
            org.primefaces.model.UploadedFile item = event.getFile();
            System.out.println(item.getFileName());
            String[] extension = item.getContentType().split("/");
            nuevaCapacitacionDataManager.setHeadcountExcel(item.getContents());
            InputStream inp = new ByteArrayInputStream(nuevaCapacitacionDataManager.getHeadcountExcel());
            // limpiamos los valores del archivo cargado
            // comparacionPreciosDataManager.setComparacionPreciosExcel(null);
            Workbook wb;
            wb = WorkbookFactory.create(inp);

            Sheet sheet = wb.getSheetAt(0);
            int i = 1;
            // Bandera que indica el fin de archivo
            Boolean eof = Boolean.FALSE;
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(1);
            Collection<Capacitado> capacitados= new ArrayList<Capacitado>();
//
            while (!eof) {
                // El momento que exista una excepcion el
                // archivo se habra acado de leer el archivo
                try {
                    Capacitado capacitado = new Capacitado();
                    // Celdas a ser utilizadas o validadas
                    Cell celdaCedula = row.getCell(0);
                    celdaCedula.setCellType(Cell.CELL_TYPE_STRING);

                    Cell celdaNombres = row.getCell(1);
                    celdaNombres.setCellType(Cell.CELL_TYPE_STRING);
                    Cell celdaApellidos = row.getCell(2);
                    celdaApellidos.setCellType(Cell.CELL_TYPE_STRING);
                    Cell celdaCECO = row.getCell(3);
                    celdaCECO.setCellType(Cell.CELL_TYPE_STRING);
                    Cell celdaCodigo = row.getCell(4);
                    celdaCodigo.setCellType(Cell.CELL_TYPE_STRING);
//                                                System.out.println(celdaCodigo.getStringCellValue());
                    Cell celdaTurno = row.getCell(5);
                    celdaTurno.setCellType(Cell.CELL_TYPE_STRING);
                    Cell celdaNivel = row.getCell(6);
                    celdaNivel.setCellType(Cell.CELL_TYPE_STRING);
                    Cell celdaLider = row.getCell(7);
                    celdaLider.setCellType(Cell.CELL_TYPE_STRING);
                    System.out.println("Cedula " + celdaCedula.getStringCellValue() + " nombres " + celdaNombres.getStringCellValue() + " Apellidos " + celdaApellidos.getStringCellValue() + " CECO " + celdaCECO.getStringCellValue()
                            + " Codigo " + celdaCodigo.getStringCellValue() + " Turno " + celdaTurno.getStringCellValue() + " Nivel " + celdaNivel.getStringCellValue()
                            + " Lider " + celdaLider.getStringCellValue());
                    capacitado.setCodArea("1");
                    capacitado.setCedcapac(Integer.parseInt(celdaCedula.getStringCellValue()));
                    capacitado.setNomcapac(celdaNombres.getStringCellValue());
                    capacitado.setApecapac(celdaApellidos.getStringCellValue());
                    capacitado.setCeco(celdaCECO.getStringCellValue());
                    capacitado.setCodgm(celdaCodigo.getStringCellValue());
                    capacitado.setTurno(Integer.parseInt(celdaTurno.getStringCellValue()));
                    capacitado.setId_Grupo(Integer.parseInt(celdaNivel.getStringCellValue()));
                    capacitado.setIdlider(Integer.parseInt(celdaLider.getStringCellValue()));

                    capacitados.add(capacitado);
                    i++;
                    row = sheet.getRow(i);
                    // cell = row.getCell(1);
                } catch (NullPointerException e) {
                    // e.printStackTrace();
                    System.out.println("Fin de archivo");
                    eof = Boolean.TRUE;
                }
            }
            ejbInstances.getCapacitadoLocator().editAll(capacitados);
            System.out.println("Fin proceso");
//                        SystemOut.getLogger().info("Tipo de archivo {} nombre {}, va a entrar a subir", item.getContentType(), item.getName());
            // buscarArticulos();
//			compararPreciosExcel=Boolean.TRUE;
//			FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().put("scroll", "1");
//			log.info("Ponemos el parametro en sesión", FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("scroll"));
            org.apache.commons.fileupload.servlet.ServletFileUpload a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        editarHeadcount();
    }

    //Trae los lideres
    public void traerLideres() {
        try {
            ejbInstances.getLiderLocator().simpleTemplateSearch(new Lider());
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //funcion que elimina los capacitados
    public void eliminarCapacitado(Capacitado capacitado) {
        ejbInstances.getCapacitadoLocator().eliminarCapacitado(capacitado);
        nuevaCapacitacionDataManager.setCapacitadoCol(ejbInstances.getCapacitadoLocator().findAll());
    }

    //funcion que setea la bandera de la vista para expandir los registros sanitarios
    public void contraerAsistentes(ReporteAsistencia reporteAsistencia) {
        reporteAsistencia.setSelected(Boolean.FALSE);
        System.out.println("Contrayendo");

    }
    
    public void contraerAsistentesNoLider(ReporteAsistenciaNoLider reporteAsistencia) {
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
        nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem(null, "Seleccione un tema"));
        nuevaCapacitacionDataManager.setTemasCol(ejbInstances.getTemaLocator().obtenerTemasPrincipio(nuevaCapacitacionDataManager.getCodigoPrincipio()));
        for (Tema tema : nuevaCapacitacionDataManager.getTemasCol()) {
            nuevaCapacitacionDataManager.getListaTemas().add(new SelectItem((tema.getCodtema()), tema.getNomtema()));
        }
    }

    public void cargarGruposAsistentes(AjaxBehaviorEvent event) {
        TemaGrupoCap temaGrupoCap = new TemaGrupoCap();
        temaGrupoCap.setTemaGrupoCapPK(new TemaGrupoCapPK());
        temaGrupoCap.getTemaGrupoCapPK().setCodTema(nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema());
        try {
            Collection<GrupoCap> temaGrupoCapCol = ejbInstances.getTemaGrupoCapLocator().buscarGruposAsignado(temaGrupoCap);
            ArrayList<Integer> arrayIdGrupo = new ArrayList<Integer>();
            for (GrupoCap temaGrupoCapTmp : temaGrupoCapCol) {
                arrayIdGrupo.add(temaGrupoCapTmp.getIdGrupo());
            }
            nuevaCapacitacionDataManager.setGrupoCapDispCol(ejbInstances.getGrupoLocator().buscarGruposExcluyendo(arrayIdGrupo));
            nuevaCapacitacionDataManager.setGrupoCapAsigCol(temaGrupoCapCol);
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //nuevaCapacitacionDataManager.setGrupoCapDispCol(null);
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

    public String gestionarTemas() {
//        nuevaCapacitacionDataManager.setListaAreas(ejbInstances.getAreasLocator().contarAsistentes());
        nuevaCapacitacionDataManager.setCodigoPrincipio(0);
        nuevaCapacitacionDataManager.setGrupoCapAsigCol(null);
        nuevaCapacitacionDataManager.setGrupoCapDispCol(null);
        nuevaCapacitacionDataManager.setPlanificacionCurso(new PlantillaCurso());
        nuevaCapacitacionDataManager.setPaso("visualizar");
        return "/Capacitacion/editarTema?faces-redirect=true";
    }

    public String gestionarUsuarios() {
//        nuevaCapacitacionDataManager.setListaAreas(ejbInstances.getAreasLocator().contarAsistentes());
        nuevaCapacitacionDataManager.setCodigoPrincipio(0);
        nuevaCapacitacionDataManager.setUsuariosCol(ejbInstances.getUsuarioLocator().findAll());
        //nuevaCapacitacionDataManager.setPlanificacionCurso(new PlantillaCurso());
        nuevaCapacitacionDataManager.setPaso("visualizar");
        return "/Capacitacion/Usuario/buscarUsuarios?faces-redirect=true";
    }
    
    public String gestionarLideres() {
//        nuevaCapacitacionDataManager.setListaAreas(ejbInstances.getAreasLocator().contarAsistentes());
        nuevaCapacitacionDataManager.setCodigoPrincipio(0);
        nuevaCapacitacionDataManager.setLideresCol(ejbInstances.getLiderLocator().findAll());
        //nuevaCapacitacionDataManager.setPlanificacionCurso(new PlantillaCurso());
        nuevaCapacitacionDataManager.setPaso("visualizar");
        return "/Capacitacion/Lideres/buscarLideres?faces-redirect=true";
    }
    
    public String editarHeadcount() {
        System.out.println("Editando headcount.");
//        nuevaCapacitacionDataManager.setListaAreas(ejbInstances.getAreasLocator().contarAsistentes());
        nuevaCapacitacionDataManager.setCodigoPrincipio(0);
        nuevaCapacitacionDataManager.setCapacitadoCol(ejbInstances.getCapacitadoLocator().findAll());
        //nuevaCapacitacionDataManager.setPlanificacionCurso(new PlantillaCurso());
        nuevaCapacitacionDataManager.setPaso("visualizar");
        return "/Capacitacion/editarHeadcount?faces-redirect=true";
    }

    public String editarUsuario() {
//        nuevaCapacitacionDataManager.setListaAreas(ejbInstances.getAreasLocator().contarAsistentes());
//        nuevaCapacitacionDataManager.setCodigoPrincipio(0);
        nuevaCapacitacionDataManager.setListaPermisos(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaPermisos().add(new SelectItem(null, "Seleccione"));
        for (Rol rol : ejbInstances.getRolLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaPermisos().add(new SelectItem(rol.getIdrol(), rol.getNomrol()));
        }

        //nuevaCapacitacionDataManager.setPlanificacionCurso(new PlantillaCurso());
        nuevaCapacitacionDataManager.setPaso("visualizar");
        return "/Capacitacion/Usuario/editarUsuario?faces-redirect=true";
    }
    
    public String editarLider() {
        //nuevaCapacitacionDataManager.setPlanificacionCurso(new PlantillaCurso());
        nuevaCapacitacionDataManager.setPaso("visualizar");
        return "/Capacitacion/Lideres/editarLideres?faces-redirect=true";
    }

    public void generarReportePlanificadoAsistencia() {
        try {
            nuevaCapacitacionDataManager.setCollReporteAsistencia(ejbInstances.getReporteAsistenciaLocator().generarReporteAsistencia(nuevaCapacitacionDataManager.getPlantillaReporteAsistencia()));
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generarReportePlanificadoAsistenciaNoLider() {
        try {
            nuevaCapacitacionDataManager.setCollReporteAsistenciaNoLider(ejbInstances.getReporteAsistenciaLocator().generarReporteAsistenciaNoLider(nuevaCapacitacionDataManager.getPlantillaReporteAsistencia()));
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String redireccionarReportePlanificadoAsistencia() {
        try {
            Calendar fechaInicio = Calendar.getInstance(), fechaFin = Calendar.getInstance();
            nuevaCapacitacionDataManager.setPlantillaReporteAsistencia(new ReporteAsistencia());
            nuevaCapacitacionDataManager.getPlantillaReporteAsistencia().setColIdLider(new Integer[]{null});
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
            nuevaCapacitacionDataManager.setCollReporteAsistencia(null);
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
            nuevaCapacitacionDataManager.setListaLider(new ArrayList<SelectItem>());
            nuevaCapacitacionDataManager.getListaLider().add(new SelectItem(null, "Todos"));
            for (Lider lider : ejbInstances.getLiderLocator().findAll()) {
                nuevaCapacitacionDataManager.getListaLider().add(new SelectItem(lider.getIdlider(), lider.getCargo()));
            }
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return "/Capacitacion/reportePlanificadosAsistentes?faces-redirect=true";
    }
    
    public String redireccionarReportePlanificadoAsistenciaGerencial() {
        try {
            Calendar fechaInicio = Calendar.getInstance(), fechaFin = Calendar.getInstance();
            nuevaCapacitacionDataManager.setPlantillaReporteAsistencia(new ReporteAsistencia());
            nuevaCapacitacionDataManager.getPlantillaReporteAsistencia().setColIdLider(new Integer[]{null});
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
            nuevaCapacitacionDataManager.setCollReporteAsistenciaNoLider(null);
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
            nuevaCapacitacionDataManager.setListaLider(new ArrayList<SelectItem>());
            nuevaCapacitacionDataManager.getListaLider().add(new SelectItem(null, "Todos"));
            for (Lider lider : ejbInstances.getLiderLocator().findAll()) {
                nuevaCapacitacionDataManager.getListaLider().add(new SelectItem(lider.getIdlider(), lider.getCargo()));
            }
        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return "/Capacitacion/reportePlanificadosAsistentesGerencial?faces-redirect=true";
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
        nuevaCapacitacionDataManager.setListaLider(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.getListaLider().add(new SelectItem(null, "Todos"));
        for (Lider lider : ejbInstances.getLiderLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaLider().add(new SelectItem(lider.getIdlider(), lider.getCargo()));
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

    public String eliminarPlantillaCurso() {

        try {
            ejbInstances.getPlantillaCursoLocator().eliminarPlanificacionCurso(nuevaCapacitacionDataManager.getPlanificacionCurso());
            seleccionarAsistentes();
            MessageController.addInfo(null, "Información: la planificación se elimino correctamente");
        } catch (Exception e) {
            MessageController.addError(null, "Error: la planificación no se elimino correctamente.");
        }
        return null;


    }

    public String nuevoUsuario() {
        System.out.println("Va a crear nuevo usuario");
        nuevaCapacitacionDataManager.setListaPermisos(new ArrayList<SelectItem>());
        for (Rol rol : ejbInstances.getRolLocator().findAll()) {
            nuevaCapacitacionDataManager.getListaPermisos().add(new SelectItem(rol.getIdrol(), rol.getNomrol()));
        }
        nuevaCapacitacionDataManager.setPaso("nuevo");
        nuevaCapacitacionDataManager.setUsuarioEdicion(new Usuario());
        return "/Capacitacion/Usuario/editarUsuario?faces-redirect=true";
    }
    
    public String nuevoLider() {
        System.out.println("Va a crear nuevo lider");
        nuevaCapacitacionDataManager.setListaPermisos(new ArrayList<SelectItem>());
        nuevaCapacitacionDataManager.setPaso("nuevo");
        nuevaCapacitacionDataManager.setLiderEdicion(new Lider());
        return "/Capacitacion/Lideres/editarLideres?faces-redirect=true";
    }

    public String guardarUsuario(Usuario usuario) {
        try {
            System.out.println("Va a guardar usuario");
            ejbInstances.getUsuarioLocator().edit(usuario);
            return volverListaUsuarios();
        } catch (Exception e) {
            MessageController.addError("", "Error al guardar el usuario");
            e.printStackTrace();
        }
        return "";
    }
    
    public String guardarLider(Lider lider) {
        try {
            System.out.println("Va a guardar lider");
            ejbInstances.getLiderLocator().edit(lider);
            return volverListaLideres();
        } catch (Exception e) {
            MessageController.addError("", "Error al guardar el lider");
            e.printStackTrace();
        }
        return "";
    }

    public String guardarUsuarioNuevo(Usuario usuario) {
        try {
            System.out.println("Va a guardar usuario");
            ejbInstances.getUsuarioLocator().create(usuario);
            nuevaCapacitacionDataManager.setUsuariosCol(ejbInstances.getUsuarioLocator().findAll());
            return volverListaUsuarios();
        } catch (Exception e) {
            MessageController.addError("", "Error al guardar el usuario");
            e.printStackTrace();
        }
        return "";
    }
    
    public String guardarLiderNuevo(Lider lider) {
        try {
            System.out.println("Va a guardar lider");
            ejbInstances.getLiderLocator().create(lider);
            nuevaCapacitacionDataManager.setLideresCol(ejbInstances.getLiderLocator().findAll());
            return volverListaLideres();
        } catch (Exception e) {
            MessageController.addError("", "Error al guardar el lider");
            e.printStackTrace();
        }
        return "";
    }

    public String guardarPlantillaCurso() {
        if (nuevaCapacitacionDataManager.getListaInstructorVista() != null) {
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
        } else {
            MessageController.addError(null, "Error: debe seleccionar instructores para el curso.");
        }
        return "";

    }

    public String guardarPostulantes() {
        try {
            System.out.println("Guardando postulantes");
            Collection<Plaasis> postulantesAsistentes = new ArrayList<Plaasis>();
            Collection<Plaasis> postulantesNoAsistentes = new ArrayList<Plaasis>();
            if (nuevaCapacitacionDataManager.getColReporteCIndividual() != null) {
                for (ReporteCIndividual capacitado : nuevaCapacitacionDataManager.getColReporteCIndividual()) {
                    if (!capacitado.getSeleccionado() && capacitado.getEsAnteriormentePlanificado()) {
                        Plaasis plaasis = new Plaasis();
                        plaasis.setPlaasisPK(new PlaasisPK());
                        plaasis.getPlaasisPK().setCedcapac(capacitado.getCedcapac());
                        plaasis.getPlaasisPK().setIdPlantilla(nuevaCapacitacionDataManager.getPlanificacionCurso().getIdPlantilla());
                        postulantesNoAsistentes.add(plaasis);
                    }

                    if (capacitado.getSeleccionado() && !capacitado.getEsAnteriormentePlanificado()) {
                        Plaasis plaasis = new Plaasis();
                        plaasis.setPlaasisPK(new PlaasisPK());
                        plaasis.getPlaasisPK().setCedcapac(capacitado.getCedcapac());
                        plaasis.getPlaasisPK().setIdPlantilla(nuevaCapacitacionDataManager.getPlanificacionCurso().getIdPlantilla());
                        postulantesAsistentes.add(plaasis);
                    }
                }
            }
            //if (postulantesAsistentes.size() > 0||postulantesNoAsistentes.size() > 0) {
            ejbInstances.getPlaasisLocator().actualizarAsistentesPlanificados(nuevaCapacitacionDataManager.getPlanificacionCurso(), postulantesNoAsistentes, postulantesAsistentes);
            MessageController.addInfo(null, "Los asistentes se añadieron correctamente");
            return "/Menuprincipal?faces-redirect=true";
//        } else {
//            MessageController.addError(null, "Error: debe planificar asistentes para este curso.");
//            return "";
//        }
        } catch (Exception e) {
            e.printStackTrace();
            MessageController.addError(null, "Error: Ha ocurrido un error al intentar guardar la planificación.");
            return "";
        }

    }

    public String guardarCursoN1() {
        try {
            System.out.println("Va a guardar curso nivel 1");
            ejbInstances.getCursoLocator().actualizarCursoN1(nuevaCapacitacionDataManager.getCurso(), nuevaCapacitacionDataManager.getListaAsistentes());
            System.out.println("Actualiza curso id " + nuevaCapacitacionDataManager.getCurso().getIdCurso());
            return "/Menuprincipal?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            MessageController.addError("", "Error al actualizar el curso");
            System.out.println("Error al crear un curso nuevo");
        }
        return "";
    }

    public String guardarCurso() {
        try {
            System.out.println("Va a tomar lista asistentes");
            if (nuevaCapacitacionDataManager.getListaAsistentes().size() > 0) {
//        ejbInstances.getTemaLocator().find(nuevaCapacitacionDataManager..getIdCurso()getTema());
                ejbInstances.getCursoLocator().actualizarCurso(nuevaCapacitacionDataManager.getCurso(), nuevaCapacitacionDataManager.getListaAsistentes());
//        ejbInstances.getAsistenciaLocator().createAll(nuevaCapacitacionDataManager.getCurso().getAsistenciaCollection());
                System.out.println("Creo curso id " + nuevaCapacitacionDataManager.getCurso().getIdCurso());
                return "/Menuprincipal?faces-redirect=true";
            } else {
                MessageController.addError(null, "Error: Por favor, seleccione asistentes antes de guardar");
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public String volverListaUsuarios() {
        System.out.println("Retornando a la lista de usuarios");
//        nuevaCapacitacionDataManager.setPasoFlujo("");
        return "/Capacitacion/Usuario/buscarUsuarios?faces-redirect=true";
    }
    
    public String volverListaLideres() {
        System.out.println("Retornando a la lista de lideres");
//        nuevaCapacitacionDataManager.setPasoFlujo("");
        return "/Capacitacion/Lideres/buscarLideres?faces-redirect=true";
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
        System.out.println("Anadiendo asistentes.");
        nuevaCapacitacionDataManager.setListaAsistentes(new ArrayList<Asistencia>());
        Asistencia asistenteTMP;
        for (Capacitado capacitado : nuevaCapacitacionDataManager.getListaCapacitados()) {
            if (capacitado.getSeleccionado()) {
                asistenteTMP = new Asistencia();
                asistenteTMP.setAsistenciaPK(new AsistenciaPK());
                asistenteTMP.getAsistenciaPK().setCedcapac(capacitado.getCedcapac());
                asistenteTMP.getAsistenciaPK().setIdPlantilla(nuevaCapacitacionDataManager.getCurso().getId_plantilla());
                asistenteTMP.setResN3(capacitado.getEvaluacionN3());
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
            System.out.println("instanciando el controlador");
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
        nuevaCapacitacionDataManager.setPlanificacionCurso(null);
        System.out.println("redireccionando a seleccionar asistentes");
        PlantillaCurso plantillaCurso = new PlantillaCurso();
        fechaInicio.set(Calendar.DAY_OF_MONTH, 1);
        fechaFin.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        if (menuDataManager.getUsuarioSesion().getIdRol() != 1) {
            plantillaCurso.setFechaInicio(fechaInicio.getTime());
            plantillaCurso.setFechaFin(fechaFin.getTime());
        }
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

    public void cargarCapacitadosPuntaje(AjaxBehaviorEvent e) {
        System.out.println("Buscando por puntaje");
        nuevaCapacitacionDataManager.setPlanificacionAsistenciaReporte(new ReporteCIndividual());
        nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColCeco(new Integer[]{null});
        nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTurno(new Integer[]{null});
        nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTema(new Integer[]{null});
        nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTema(new Integer[]{nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema()});
        try {
            nuevaCapacitacionDataManager.setColReporteCIndividual(ejbInstances.getReporteCIndividualLocator().generarReporteCIndividualRangos(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte(), nuevaCapacitacionDataManager.getFiltroBusquedaCap(), nuevaCapacitacionDataManager.getCurso().getId_plantilla()));

        } catch (Exception ex) {
            Logger.getLogger(NuevaCapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarCapacitados() {
        try {

            nuevaCapacitacionDataManager.setPlanificacionAsistenciaReporte(new ReporteCIndividual());
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColCeco(new Integer[]{null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTurno(new Integer[]{null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTema(new Integer[]{null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColIdLider(new Integer[]{null});
            nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte().setColTema(new Integer[]{nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema()});
            nuevaCapacitacionDataManager.setColReporteCIndividual(ejbInstances.getReporteCIndividualLocator().generarReporteCIndividualRangos(nuevaCapacitacionDataManager.getPlanificacionAsistenciaReporte(), nuevaCapacitacionDataManager.getFiltroBusquedaCap(), nuevaCapacitacionDataManager.getCurso().getId_plantilla()));
            //ponemos solo los capacitados permitidos a editar en la vista
            for (ReporteCIndividual reporteCIndividual : nuevaCapacitacionDataManager.getColReporteCIndividual()) {
                reporteCIndividual.setPermitirSeleccion(Boolean.FALSE);
            }


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
        nuevaCapacitacionDataManager.setCodigoPrincipio(0);
        //seteamos fechas defecto
        Calendar fechaAUX = Calendar.getInstance();
        fechaAUX.set(Calendar.DAY_OF_MONTH, 1);
        nuevaCapacitacionDataManager.getPlanificacionCurso().setFechaInicio(fechaAUX.getTime());
        fechaAUX = Calendar.getInstance();
        fechaAUX.set(Calendar.DAY_OF_MONTH, fechaAUX.getActualMaximum(Calendar.DAY_OF_MONTH));
        nuevaCapacitacionDataManager.getPlanificacionCurso().setFechaFin(fechaAUX.getTime());

        nuevaCapacitacionDataManager.getPlanificacionCurso().setPlainsCollection(new ArrayList<Plains>());
        nuevaCapacitacionDataManager.setListaInstructorVista(null);
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

    public void editarTemas() {
        System.out.println("Editando temas");
        for (Tema temaTMP : nuevaCapacitacionDataManager.getTemasCol()) {
            System.out.println("recorre:" + temaTMP.getCodtema() + "---" + nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema());
            if (temaTMP.getCodtema().equals(nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema())) {
                System.out.println("se editara el tema:" + nuevaCapacitacionDataManager.getPlanificacionCurso().getCodtema());
                nuevaCapacitacionDataManager.setTemaEdicion(temaTMP);
                nuevaCapacitacionDataManager.setPaso("editar");
            }
        }
    }

    public void anadirTodosGrupos() {
        nuevaCapacitacionDataManager.getGrupoCapAsigCol().addAll(nuevaCapacitacionDataManager.getGrupoCapDispCol());
        nuevaCapacitacionDataManager.setGrupoCapDispCol(new ArrayList<GrupoCap>());
    }

    public void removerTodosGrupos() {
        nuevaCapacitacionDataManager.getGrupoCapDispCol().addAll(nuevaCapacitacionDataManager.getGrupoCapAsigCol());
        nuevaCapacitacionDataManager.setGrupoCapAsigCol(new ArrayList<GrupoCap>());
    }

    public void removerGrupos() {
        ArrayList<GrupoCap> grupoCapCol = new ArrayList<GrupoCap>();
        for (GrupoCap grupoCap : nuevaCapacitacionDataManager.getGrupoCapAsigCol()) {
            if (grupoCap.getSelected()) {
                grupoCap.setSelected(Boolean.FALSE);
                grupoCapCol.add(grupoCap);

            }
        }
        nuevaCapacitacionDataManager.getGrupoCapDispCol().addAll(grupoCapCol);
        nuevaCapacitacionDataManager.getGrupoCapAsigCol().removeAll(grupoCapCol);
    }

    public void anadirGrupos() {
        ArrayList<GrupoCap> grupoCapCol = new ArrayList<GrupoCap>();
        for (GrupoCap grupoCap : nuevaCapacitacionDataManager.getGrupoCapDispCol()) {
            if (grupoCap.getSelected()) {
                grupoCap.setSelected(Boolean.FALSE);
                grupoCapCol.add(grupoCap);

            }
        }
        nuevaCapacitacionDataManager.getGrupoCapAsigCol().addAll(grupoCapCol);
        nuevaCapacitacionDataManager.getGrupoCapDispCol().removeAll(grupoCapCol);
    }

    public void anadirTemas() {
        nuevaCapacitacionDataManager.setTemaEdicion(new Tema());
        nuevaCapacitacionDataManager.setPaso("nuevo");
    }

    public void guardarAnadirTemas() {
        try {
            nuevaCapacitacionDataManager.getTemaEdicion().setCodPrinci(nuevaCapacitacionDataManager.getCodigoPrincipio());
            ejbInstances.getTemaLocator().create(nuevaCapacitacionDataManager.getTemaEdicion());
            System.out.println("Anadiendo--" + nuevaCapacitacionDataManager.getTemaEdicion().getCodtema());

            SelectItem sTemaTMP = new SelectItem(nuevaCapacitacionDataManager.getTemaEdicion().getCodtema(), nuevaCapacitacionDataManager.getTemaEdicion().getNomtema());
            nuevaCapacitacionDataManager.getListaTemas().add(sTemaTMP);
            nuevaCapacitacionDataManager.getTemasCol().add(nuevaCapacitacionDataManager.getTemaEdicion());
            cancelarEditarTemas();
            MessageController.addInfo(null, "El tema se almacenó correctamente.");
        } catch (Exception e) {
            MessageController.addError(null, "Error al guardar el tema");
        }
    }

    public void guardarEditarTemas() {
        try {
            ejbInstances.getTemaLocator().edit(nuevaCapacitacionDataManager.getTemaEdicion());
            for (SelectItem sTemaTMP : nuevaCapacitacionDataManager.getListaTemas()) {
                System.out.println("Recorro tema " + sTemaTMP.getValue() + "--" + nuevaCapacitacionDataManager.getTemaEdicion().getCodtema().toString());
                if (sTemaTMP.getValue() == nuevaCapacitacionDataManager.getTemaEdicion().getCodtema()) {
                    sTemaTMP.setLabel(nuevaCapacitacionDataManager.getTemaEdicion().getNomtema());
                    System.out.println("cambiando tema " + nuevaCapacitacionDataManager.getTemaEdicion().getNomtema());
                }
            }

            Collection<TemaGrupoCap> temaGrupoCapCol = new ArrayList<TemaGrupoCap>();
            for (GrupoCap grupoCap : nuevaCapacitacionDataManager.getGrupoCapAsigCol()) {
                TemaGrupoCap temaGrupoCap = new TemaGrupoCap();
                temaGrupoCap.setTemaGrupoCapPK(new TemaGrupoCapPK());
                temaGrupoCap.getTemaGrupoCapPK().setCodTema(nuevaCapacitacionDataManager.getTemaEdicion().getCodtema());
                temaGrupoCap.getTemaGrupoCapPK().setId_Grupo(grupoCap.getIdGrupo());
                temaGrupoCapCol.add(temaGrupoCap);
            }
            Collection<TemaGrupoCap> temaGrupoCapColElim = new ArrayList<TemaGrupoCap>();
            for (GrupoCap grupoCap : nuevaCapacitacionDataManager.getGrupoCapDispCol()) {
                TemaGrupoCap temaGrupoCap = new TemaGrupoCap();
                temaGrupoCap.setTemaGrupoCapPK(new TemaGrupoCapPK());
                temaGrupoCap.getTemaGrupoCapPK().setCodTema(nuevaCapacitacionDataManager.getTemaEdicion().getCodtema());
                temaGrupoCap.getTemaGrupoCapPK().setId_Grupo(grupoCap.getIdGrupo());
                temaGrupoCapColElim.add(temaGrupoCap);
            }
            ejbInstances.getTemaGrupoCapLocator().removeAll(temaGrupoCapColElim);
            ejbInstances.getTemaGrupoCapLocator().editAll(temaGrupoCapCol);
            //Parte para asignar los capacitados a uno de los temas predefinidos
            Capacitado capacitado = new Capacitado();
            PlantillaCurso plantillaCurso = new PlantillaCurso();
            plantillaCurso.setCodtema(nuevaCapacitacionDataManager.getTemaEdicion().getCodtema());
            plantillaCurso.setObjetivo("Carga inicial");
            //Función bajo pruebas aún
            //ejbInstances.getPlaasisLocator().crearHistoricoCursos(plantillaCurso, temaGrupoCapCol, temaGrupoCapColElim);

            cancelarEditarTemas();
            MessageController.addInfo(null, "El tema se almacenó correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            MessageController.addError(null, "Error al guardar el tema");
        }
    }

    public void cancelarEditarTemas() {
        nuevaCapacitacionDataManager.setTemaEdicion(null);
        nuevaCapacitacionDataManager.setPaso("visualizar");
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
