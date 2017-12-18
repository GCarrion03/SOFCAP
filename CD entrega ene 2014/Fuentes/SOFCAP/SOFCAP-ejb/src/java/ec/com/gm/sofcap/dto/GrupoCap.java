/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author GUZ
 */
@Entity
@Table(name = "GRUPO_CAP")
@NamedQueries({
    @NamedQuery(name = "GrupoCap.findAll", query = "SELECT g FROM GrupoCap g"),
    @NamedQuery(name = "GrupoCap.findByIdGrupo", query = "SELECT g FROM GrupoCap g WHERE g.idGrupo = :idGrupo"),
    @NamedQuery(name = "GrupoCap.findByDesgrupo", query = "SELECT g FROM GrupoCap g WHERE g.desgrupo = :desgrupo")})
public class GrupoCap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GRUPO")
    private Integer idGrupo;
    @Size(max = 50)
    @Column(name = "DESGRUPO")
    private String desgrupo;

    @Transient
    private Boolean selected=Boolean.FALSE;
    
    public GrupoCap() {
    }

    public GrupoCap(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    
    

    public String getDesgrupo() {
        return desgrupo;
    }

    public void setDesgrupo(String desgrupo) {
        this.desgrupo = desgrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoCap)) {
            return false;
        }
        GrupoCap other = (GrupoCap) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.GrupoCap[ idGrupo=" + idGrupo + " ]";
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoCap")
    private Collection<Capacitado> CapacitadoCol;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoCap")
    private Collection<TemaGrupoCap> temaGrupoCapCol;
    
    
    
    public Collection<Capacitado> getCapacitadoCol() {
        return CapacitadoCol;
    }

    public void setCapacitadoCol(Collection<Capacitado> CapacitadoCol) {
        this.CapacitadoCol = CapacitadoCol;
    }

    public Collection<TemaGrupoCap> getTemaGrupoCapCol() {
        return temaGrupoCapCol;
    }

    public void setTemaGrupoCapCol(Collection<TemaGrupoCap> temaGrupoCapCol) {
        this.temaGrupoCapCol = temaGrupoCapCol;
    }

    
    
}
