/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.Menu;

import ec.com.gm.sofcap.dto.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Guz
 */
@ManagedBean(name="menuDataManager")
@SessionScoped
public class MenuDataManager implements Serializable {
     private Usuario filtroUsuario,usuarioSesion;

     private Boolean estaInicializado = Boolean.FALSE;

     
    public Boolean getEstaInicializado() {
        return estaInicializado;
    }

    public void setEstaInicializado(Boolean estaInicializado) {
        this.estaInicializado = estaInicializado;
    }

    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public Usuario getFiltroUsuario() {
        return filtroUsuario;
    }

    public void setFiltroUsuario(Usuario filtroUsuario) {
        this.filtroUsuario = filtroUsuario;
    }

}
