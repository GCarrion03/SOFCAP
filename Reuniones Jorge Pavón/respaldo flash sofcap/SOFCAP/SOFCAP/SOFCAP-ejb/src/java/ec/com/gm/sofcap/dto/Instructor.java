/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "INSTRUCTOR")
@NamedQueries({
    @NamedQuery(name = "Instructor.findAll", query = "SELECT i FROM Instructor i"),
    @NamedQuery(name = "Instructor.findByCodinstr", query = "SELECT i FROM Instructor i WHERE i.codinstr = :codinstr"),
    @NamedQuery(name = "Instructor.findByNominstr", query = "SELECT i FROM Instructor i WHERE i.nominstr = :nominstr"),
    @NamedQuery(name = "Instructor.findByCodtema", query = "SELECT i FROM Instructor i WHERE i.codtema = :codtema")})
public class Instructor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODINSTR")
    private Integer codinstr;
    @Column(name = "NOMINSTR")
    private String nominstr;
    @Column(name = "CODTEMA")
    private Integer codtema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")
    private Collection<Curso> cursoCollection;
    @JoinColumn(name = "CODTEMA", referencedColumnName = "CODTEMA", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Tema tema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")
    private Collection<Plains> plainsCollection;

    @Transient
    private Long conteo;
    @Transient
    private Boolean seleccionado=Boolean.FALSE;

    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Instructor() {
    }

    public Tema getTema() {
        return tema;
    }

    public Long getConteo() {
        return conteo;
    }

    public void setConteo(Long conteo) {
        this.conteo = conteo;
    }



    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Instructor(Integer codinstr) {
        this.codinstr = codinstr;
    }

    public Integer getCodinstr() {
        return codinstr;
    }

    public void setCodinstr(Integer codinstr) {
        this.codinstr = codinstr;
    }

    public String getNominstr() {
        return nominstr;
    }

    public void setNominstr(String nominstr) {
        this.nominstr = nominstr;
    }

    public Integer getCodtema() {
        return codtema;
    }

    public void setCodtema(Integer codtema) {
        this.codtema = codtema;
    }

    public Collection<Curso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<Curso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codinstr != null ? codinstr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) object;
        if ((this.codinstr == null && other.codinstr != null) || (this.codinstr != null && !this.codinstr.equals(other.codinstr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Instructor[codinstr=" + codinstr + "]";
    }
    public Collection<Plains> getPlainsCollection() {
        return plainsCollection;
    }

    public void setPlainsCollection(Collection<Plains> plainsCollection) {
        this.plainsCollection = plainsCollection;
    }
}