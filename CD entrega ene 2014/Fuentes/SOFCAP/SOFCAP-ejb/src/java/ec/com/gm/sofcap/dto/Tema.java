/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "TEMA", catalog = "SOFCAP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Tema.findAll", query = "SELECT t FROM Tema t"),
    @NamedQuery(name = "Tema.findByCodtema", query = "SELECT t FROM Tema t WHERE t.codtema = :codtema"),
    @NamedQuery(name = "Tema.findByNomtema", query = "SELECT t FROM Tema t WHERE t.nomtema = :nomtema")})
public class Tema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "CODTEMA", nullable = false)
    private Integer codtema;
    @Column(name = "CODPRINCI", nullable = false)
    private Integer codPrinci;
    @Basic(optional = false)
    @Column(name = "NOMTEMA", nullable = false, length = 200)
    private String nomtema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tema")
    private Collection<PlantillaCurso> cursoCollection;
    @JoinColumn(name = "CODPRINCI", referencedColumnName = "CODPRINCI",insertable=false, updatable=false)
    @ManyToOne
    private PrincipiosGms principiosGms;
    @OneToMany(mappedBy = "tema")
    private Collection<Instructor> instructorCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tema")
    private Collection<TemaGrupoCap> temaGrupoCapCol;

    public Tema() {
    }

    public Tema(Integer codtema) {
        this.codtema = codtema;
    }

    public Tema(Integer codtema, String nomtema) {
        this.codtema = codtema;
        this.nomtema = nomtema;
    }

    public Collection<Instructor> getInstructorCollection() {
        return instructorCollection;
    }

    public void setInstructorCollection(Collection<Instructor> instructorCollection) {
        this.instructorCollection = instructorCollection;
    }

    public Integer getCodPrinci() {
        return codPrinci;
    }

    public void setCodPrinci(Integer codPrinci) {
        this.codPrinci = codPrinci;
    }

    
    public Integer getCodtema() {
        return codtema;
    }

    public void setCodtema(Integer codtema) {
        this.codtema = codtema;
    }

    public String getNomtema() {
        return nomtema;
    }

    public void setNomtema(String nomtema) {
        this.nomtema = nomtema;
    }

    public Collection<PlantillaCurso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<PlantillaCurso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }



    public PrincipiosGms getPrincipiosGms() {
        return principiosGms;
    }

    public void setPrincipiosGms(PrincipiosGms principiosGms) {
        this.principiosGms = principiosGms;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtema != null ? codtema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tema)) {
            return false;
        }
        Tema other = (Tema) object;
        if ((this.codtema == null && other.codtema != null) || (this.codtema != null && !this.codtema.equals(other.codtema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Tema[codtema=" + codtema + "]";
    }

    public Collection<TemaGrupoCap> getTemaGrupoCapCol() {
        return temaGrupoCapCol;
    }

    public void setTemaGrupoCapCol(Collection<TemaGrupoCap> temaGrupoCapCol) {
        this.temaGrupoCapCol = temaGrupoCapCol;
    }

    
}