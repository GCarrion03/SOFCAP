/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Guz
 */
public class MessageController {
	/**
	 * Agrega un mensaje informativo
	 * @param keyMessage	- Id del mensaje, este puede ser nulo
	 * @param message		- Contenido del mensaje
	 */
	public static void addInfo(String keyMessage, String message){
    	FacesContext.getCurrentInstance().addMessage(keyMessage, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
	/**
	 * Agrega un mensaje de advertencia
	 * @param keyMessage	- Id del mensaje, este puede ser nulo
	 * @param message		- Contenido del mensaje
	 */
    public static void addWarn(String keyMessage, String message){
        FacesContext.getCurrentInstance().addMessage(keyMessage, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
    }
    /**
     * Agrega un mensaje de error
     * @param keyMessage	- Id del mensaje, este puede ser nulo
     * @param message		- Contenido del mensaje
     */
    public static void addError(String keyMessage, String message){
        FacesContext.getCurrentInstance().addMessage(keyMessage, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
    /**
     * Agrega un mensaje de error grave
     * @param keyMessage	- Id del mensaje, este puede ser nulo
     * @param message		- Contenido del mensaje
     */
    public static void addFatal(String keyMessage, String message){
        FacesContext.getCurrentInstance().addMessage(keyMessage, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, message));
    }
}
