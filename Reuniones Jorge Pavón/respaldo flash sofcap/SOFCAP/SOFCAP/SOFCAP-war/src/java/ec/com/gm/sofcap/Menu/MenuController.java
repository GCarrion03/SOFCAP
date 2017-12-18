/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.Menu;

import ec.com.gm.sofcap.dto.Usuario;
import ec.com.gm.sofcap.ejb.instances.EJBInstances;
import ec.com.gm.sofcap.nuevaCapacitacion.NuevaCapacitacionController;
import ec.com.gm.sofcap.nuevaCapacitacion.NuevaCapacitacionDataManager;
import ec.com.gm.sofcap.util.MessageController;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guz
 */
@ManagedBean(name="menuController")
@ViewScoped
public class MenuController implements Serializable{
    @ManagedProperty(value="#{menuDataManager}")
    private MenuDataManager menuDataManager;

     @ManagedProperty(value="#{ejbInstances}")
    private EJBInstances ejbInstances;

    public void initialize(){
        System.out.println("se setea el usuario");
        menuDataManager.setFiltroUsuario(new Usuario());
    }

    public String getForm() {
        if(this.getMenuDataManager()!= null){
            if (!this.getMenuDataManager().getEstaInicializado())
            {
                System.out.println("Entro a método de obtener el form");
                this.initialize();
                this.getMenuDataManager().setEstaInicializado(Boolean.TRUE);
            }
        }
        return "frmMenuCap";
    }

    public String ingresar()
    {
        try {
            Collection<Usuario> usuarios=ejbInstances.getUsuarioLocator().simpleTemplateSearch(getMenuDataManager().getFiltroUsuario());
            if (usuarios.isEmpty()) {
                MessageController.addError(null, "El usuario o la contraseña son incorrectos");
                return "";
            } else {
                menuDataManager.setUsuarioSesion(ejbInstances.getUsuarioLocator().find(usuarios.iterator().next().getIdusuario()));
                System.out.println("Logueado con el usuario: "+menuDataManager.getUsuarioSesion().getNomusr());
                return "/Menuprincipal?faces-redirect=true";
            }
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public MenuDataManager getMenuDataManager() {
        return menuDataManager;
    }

    public void setMenuDataManager(MenuDataManager menuDataManager) {
        this.menuDataManager = menuDataManager;
    }

    public EJBInstances getEjbInstances() {
        return ejbInstances;
    }

    public void setEjbInstances(EJBInstances ejbInstances) {
        this.ejbInstances = ejbInstances;
    }
    
}
