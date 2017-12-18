/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "USUARIO")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByNomusr", query = "SELECT u FROM Usuario u WHERE u.nomusr = :nomusr"),
    @NamedQuery(name = "Usuario.findByPasusr", query = "SELECT u FROM Usuario u WHERE u.pasusr = :pasusr")})
public class Usuario implements Serializable {
    @JoinColumn(name = "IDROL", referencedColumnName = "IDROL",insertable=false,updatable=false)
    @ManyToOne
    private Rol rol;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
    @Column(name = "IDROL")
    private Integer idRol;
    @Column(name = "NOMUSR")
    private String nomusr;
    @Column(name = "PASUSR")
    private String pasusr;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNomusr() {
        return nomusr;
    }

    public void setNomusr(String nomusr) {
        this.nomusr = nomusr;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getPasusr() {
        return pasusr;
    }

    public void setPasusr(String pasusr) {
        this.pasusr = pasusr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Usuario[idusuario=" + idusuario + "]";
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


}
