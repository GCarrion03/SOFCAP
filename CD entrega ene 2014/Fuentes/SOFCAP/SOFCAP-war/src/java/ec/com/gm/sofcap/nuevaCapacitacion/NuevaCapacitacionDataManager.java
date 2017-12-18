/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.nuevaCapacitacion;

import ec.com.gm.sofcap.dto.*;
import ec.com.gm.sofcap.views.ReporteAsistencia;
import ec.com.gm.sofcap.views.ReporteAsistenciaNoLider;
import ec.com.gm.sofcap.views.ReporteCIndividual;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Guz
 */
@ManagedBean(name="nuevaCapacitacionDataManager")
@SessionScoped
public class NuevaCapacitacionDataManager implements Serializable{

   

    private Collection<ReporteCIndividual> colReporteCIndividual;
    private ReporteCIndividual planificacionAsistenciaReporte;
    private Collection<ReporteAsistencia> collReporteAsistencia;
    private Collection<ReporteAsistenciaNoLider> collReporteAsistenciaNoLider;
    private PlantillaCurso planificacionCurso;
    private Collection<Areas> listaAreas;
    private Collection<Instructor> listaInstructorVista;
    private Collection <Centroentrenamiento> centroentrenamiento;
    private List<SelectItem> listaPrincipios,listaTipoCurso,listaTemas,listaTurnos,listaInstructores,listaCentroEntren,listaPlanificacionCursos,listaCmbAreas,listaCecos,listaLider;
    private Collection<Capacitado> listaCapacitados;
    private Double filtroBusquedaCap;
    private Collection<Asistencia> listaAsistentes;
    private Collection<Plaasis> asistentesPlanificacionCol;
    private Tema temaEdicion;
    private Collection<Tema> temasCol;
    
    //archivo con el headcount
    byte[] headcountExcel;
    
    private Collection<GrupoCap> grupoCapAsigCol;
    private Collection<GrupoCap> grupoCapDispCol;
    
    //private collection listaUsuarios para edicion de usuarios
    private Collection<Usuario> usuariosCol; 
    
    //private collection listaLideres para edicion de lideres
    private Collection<Lider> lideresCol; 
    
    //collection con las personas para edicion de headcount
    private Collection<Capacitado> capacitadoCol; 
    
    //Lista de roles
    private List<SelectItem> listaPermisos;
    
    private Usuario usuarioEdicion;
    
     private Lider liderEdicion;
    
    private Double totalPromedioReporteCIndividual;

    private ReporteAsistencia plantillaReporteAsistencia;
    private String pasoFlujo;
    private Integer codigoTema;
    private String radioSeleccionadoReporte;
    private Integer codigoPrincipio;
    private Curso curso;
    private Collection<Curso> cursos;

    private Collection<Pregunta> preguntas;

    public List<SelectItem> getListaCmbAreas() {
        return listaCmbAreas;
    }

    public Collection<GrupoCap> getGrupoCapAsigCol() {
        return grupoCapAsigCol;
    }

    public void setGrupoCapAsigCol(Collection<GrupoCap> grupoCapAsigCol) {
        this.grupoCapAsigCol = grupoCapAsigCol;
    }

    public Collection<GrupoCap> getGrupoCapDispCol() {
        return grupoCapDispCol;
    }

    public void setGrupoCapDispCol(Collection<GrupoCap> grupoCapDispCol) {
        this.grupoCapDispCol = grupoCapDispCol;
    }

    public Collection<Tema> getTemasCol() {
        return temasCol;
    }

    public void setTemasCol(Collection<Tema> temasCol) {
        this.temasCol = temasCol;
    }

    public void setListaCmbAreas(List<SelectItem> listaCmbAreas) {
        this.listaCmbAreas = listaCmbAreas;
    }

    public List<SelectItem> getListaCecos() {
        return listaCecos;
    }

    public String getRadioSeleccionadoReporte() {
        return radioSeleccionadoReporte;
    }

    public Double getTotalPromedioReporteCIndividual() {         
            Double ret = 0.0;         
            Iterator<ReporteCIndividual> it = colReporteCIndividual.iterator();        
            while (it.hasNext()) {             
                ReporteCIndividual item = (ReporteCIndividual) it.next();             
                ret += Double.parseDouble(item.getPromedio().toString());         
            }
            ret=ret/colReporteCIndividual.size();
            return ret;     
    }

    public Collection<Plaasis> getAsistentesPlanificacionCol() {
        return asistentesPlanificacionCol;
    }

    public void setAsistentesPlanificacionCol(Collection<Plaasis> asistentesPlanificacionCol) {
        this.asistentesPlanificacionCol = asistentesPlanificacionCol;
    }

    public byte[] getHeadcountExcel() {
        return headcountExcel;
    }

    public void setHeadcountExcel(byte[] headcountExcel) {
        this.headcountExcel = headcountExcel;
    }



    public void setTotalPromedioReporteCIndividual(Double totalPromedioReporteCIndividual) {
        this.totalPromedioReporteCIndividual = totalPromedioReporteCIndividual;
    }

    public void setRadioSeleccionadoReporte(String radioSeleccionadoReporte) {
        this.radioSeleccionadoReporte = radioSeleccionadoReporte;
    }

    public void setListaCecos(List<SelectItem> listaCecos) {
        this.listaCecos = listaCecos;
    }

    public Collection<ReporteAsistencia> getCollReporteAsistencia() {
        return collReporteAsistencia;
    }

    public ReporteAsistencia getPlantillaReporteAsistencia() {
        return plantillaReporteAsistencia;
    }

    public void setPlantillaReporteAsistencia(ReporteAsistencia plantillaReporte) {
        this.plantillaReporteAsistencia = plantillaReporte;
    }

    public void setCollReporteAsistencia(Collection<ReporteAsistencia> collReporteAsistencia) {
        this.collReporteAsistencia = collReporteAsistencia;
    }

    public ReporteCIndividual getPlanificacionAsistenciaReporte() {
        return planificacionAsistenciaReporte;
    }

    public void setPlanificacionAsistenciaReporte(ReporteCIndividual planificacionAsistenciaReporte) {
        this.planificacionAsistenciaReporte = planificacionAsistenciaReporte;
    }


    public Collection<Asistencia> getListaAsistentes() {
        return listaAsistentes;
    }

    public void setListaAsistentes(Collection<Asistencia> listaAsistentes) {
        this.listaAsistentes = listaAsistentes;
    }

    public Collection<ReporteCIndividual> getColReporteCIndividual() {
        return colReporteCIndividual;
    }

    public void setColReporteCIndividual(Collection<ReporteCIndividual> colReporteCIndividual) {
        this.colReporteCIndividual = colReporteCIndividual;
    }

    public Collection<Capacitado> getListaCapacitados() {
        return listaCapacitados;
    }

    public Double getFiltroBusquedaCap() {
        return filtroBusquedaCap;
    }

    public void setFiltroBusquedaCap(Double filtroBusquedaCap) {
        this.filtroBusquedaCap = filtroBusquedaCap;
    }

    public String getPasoFlujo() {
        return pasoFlujo;
    }

    public void setPasoFlujo(String pasoFlujo) {
        this.pasoFlujo = pasoFlujo;
    }



    public void setListaCapacitados(Collection<Capacitado> listaCapacitados) {
        this.listaCapacitados = listaCapacitados;
    }

    private Collection<Capacitado> capacitados;

    private Capacitado capacitadoPln;

    private String paso, pasoCapacitacion;

    private Boolean estaInicializado = Boolean.FALSE;

    public Capacitado getCapacitadoPln() {
        return capacitadoPln;
    }

    public void setCapacitadoPln(Capacitado capacitadoPln) {
        this.capacitadoPln = capacitadoPln;
    }

    public String getPasoCapacitacion() {
        return pasoCapacitacion;
    }

    public Collection<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Collection<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void setPasoCapacitacion(String pasoCapacitacion) {
        this.pasoCapacitacion = pasoCapacitacion;
    }

    public Collection<Capacitado> getCapacitados() {
        return capacitados;
    }

    public Integer getCodigoPrincipio() {
        return codigoPrincipio;
    }

    public Collection<Instructor> getListaInstructorVista() {
        return listaInstructorVista;
    }

    public void setListaInstructorVista(Collection<Instructor> listaInstructorVista) {
        this.listaInstructorVista = listaInstructorVista;
    }

    public List<SelectItem> getListaPlanificacionCursos() {
        return listaPlanificacionCursos;
    }

    public void setListaPlanificacionCursos(List<SelectItem> listaPlanificacionCursos) {
        this.listaPlanificacionCursos = listaPlanificacionCursos;
    }

    

    public void setCodigoPrincipio(Integer codigoPrincipio) {
        this.codigoPrincipio = codigoPrincipio;
    }

    public void setCapacitados(Collection<Capacitado> capacitados) {
        this.capacitados = capacitados;
    }

    public String getPaso() {
        return paso;
    }

    public void setPaso(String paso) {
        this.paso = paso;
    }

    public PlantillaCurso getPlanificacionCurso() {
        return planificacionCurso;
    }

    public void setPlanificacionCurso(PlantillaCurso planificacionCurso) {
        this.planificacionCurso = planificacionCurso;
    }

    public Boolean getEstaInicializado() {
        return estaInicializado;
    }

    public void setEstaInicializado(Boolean estaInicializado) {
        this.estaInicializado = estaInicializado;
    }

    public Collection<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public List<SelectItem> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<SelectItem> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public Integer getCodigoTema() {
        return codigoTema;
    }

    public void setCodigoTema(Integer codigoTema) {
        this.codigoTema = codigoTema;
    }

    public Collection<Centroentrenamiento> getCentroentrenamiento() {
        return centroentrenamiento;
    }

    public void setCentroentrenamiento(Collection<Centroentrenamiento> centroentrenamiento) {
        this.centroentrenamiento = centroentrenamiento;
    }

    public List<SelectItem> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(List<SelectItem> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public List<SelectItem> getListaTipoCurso() {
        return listaTipoCurso;
    }

    public void setListaTipoCurso(List<SelectItem> listaTipoCurso) {
        this.listaTipoCurso = listaTipoCurso;
    }

    public List<SelectItem> getListaPrincipios() {
        return listaPrincipios;
    }

    public void setListaPrincipios(List<SelectItem> listaPrincipios) {
        this.listaPrincipios = listaPrincipios;
    }

   

    public Collection<Areas> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(Collection<Areas> listaAreas) {
        this.listaAreas = listaAreas;
    }

    
    /** Creates a new instance of nuevaCapacitacionDataManager */
	public String getIdDataManager() {
		return "nuevaCapacitacionDataManager";
	}

    public List<SelectItem> getListaInstructores() {
        return listaInstructores;
    }

    public void setListaInstructores(List<SelectItem> listaInstructores) {
        this.listaInstructores = listaInstructores;
    }

    public List<SelectItem> getListaCentroEntren() {
        return listaCentroEntren;
    }

    public void setListaCentroEntren(List<SelectItem> listaCentroEntren) {
        this.listaCentroEntren = listaCentroEntren;
    }

    public Tema getTemaEdicion() {
        return temaEdicion;
    }

    public void setTemaEdicion(Tema temaEdicion) {
        this.temaEdicion = temaEdicion;
    }

    public List<SelectItem> getListaLider() {
        return listaLider;
    }

    public void setListaLider(List<SelectItem> listaLider) {
        this.listaLider = listaLider;
    }

    public Collection<Usuario> getUsuariosCol() {
        return usuariosCol;
    }

    public void setUsuariosCol(Collection<Usuario> usuariosCol) {
        this.usuariosCol = usuariosCol;
    }

    public Usuario getUsuarioEdicion() {
        return usuarioEdicion;
    }

    public void setUsuarioEdicion(Usuario usuarioEdicion) {
        this.usuarioEdicion = usuarioEdicion;
    }

    public List<SelectItem> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<SelectItem> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public Collection<Capacitado> getCapacitadoCol() {
        return capacitadoCol;
    }

    public void setCapacitadoCol(Collection<Capacitado> capacitadoCol) {
        this.capacitadoCol = capacitadoCol;
    }

    public Collection<Lider> getLideresCol() {
        return lideresCol;
    }

    public void setLideresCol(Collection<Lider> lideresCol) {
        this.lideresCol = lideresCol;
    }

    public Lider getLiderEdicion() {
        return liderEdicion;
    }

    public void setLiderEdicion(Lider liderEdicion) {
        this.liderEdicion = liderEdicion;
    }

    public Collection<ReporteAsistenciaNoLider> getCollReporteAsistenciaNoLider() {
        return collReporteAsistenciaNoLider;
    }

    public void setCollReporteAsistenciaNoLider(Collection<ReporteAsistenciaNoLider> collReporteAsistenciaNoLider) {
        this.collReporteAsistenciaNoLider = collReporteAsistenciaNoLider;
    }
    
    
}
