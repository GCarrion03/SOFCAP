/* Realiza la validacion de entrada de texto para q sea solo numerico */
function mascaraWebInputNumeric(item,evento){
	   var nom = navigator.appName;
	   if (nom == "Microsoft Internet Explorer"){
		   //asignacion de tecla en IE
		   var tecla = evento.keyCode;}	   
	   else{
		   //asignacion de tecla en Firefox
		   var tecla = evento.which;
	   }
	   //Validacion ingreso numeros
	   if ( ((tecla >= 48 && tecla <= 57)) ){
		   return true;
	   }else if (	(tecla == 9) 
			|| 	(tecla == 8) 
			|| 	(tecla == 46) 
			|| 	(tecla == 0)){
  		   return(true);}
	   else {
		   return(false);
	  }   

	}
/* Convierte a mayusculas los valores ingresados */
function validarMayuscula(field,mensaje){	  
	try {
		if (typeof(field) == 'NodeList') {
			for(var i=0; i < field.length; i++) {
				validarMayuscula(field[i], mensaje);
            }
		} else {
	    	field.value = field.value.toUpperCase();
		}
		return true;
    } catch (e) {
    	return true;
    }
}
function validarMayusculaInput(field,mensaje){	  
	try {
		if (typeof(field) == 'NodeList') {
			for(var i=0; i < field.length; i++) {
				validarMayuscula(field[i], mensaje);
            }
		} else {
	    	field.value = field.value.toUpperCase();
		}
		return true;
    } catch (e) {
    	return true;
    }
}
/**
 * @param objeto
 * @param prefijo
 */
function seleccionarUnico(objeto,prefijo){
	jQuery('input[id*='+prefijo+']').each(function(){this.checked = false;});
	objeto.checked=true;
}
/**
 * 
 * @param objeto
 * @param prefijoChecks
 * @param cantidadChecks
 * @return
 */
/*function seleccionarTodos(objeto, prefijoChecks, cantidadChecks){
	var valorBoleano = false;
	//se verifica el valor del checkbox cabezera
	if(objeto != null && objeto.checked){
		valorBoleano = true;
	}
	//se recorre el arreglo de checkboxs
	for(var i=0;i<cantidadChecks;i++){
		var check = jQuery('input#' + prefijoChecks + i);
		if(check[0] != null){
			check[0].checked = valorBoleano;
		}else{
			break;
		}
	}
}*/

function seleccionarTodos(objeto, prefijoChecks){
	var valorBoleano = false;
	if(objeto != null && objeto.checked){
		valorBoleano = true;
	}
	var checks =  jQuery('input.' + prefijoChecks);
	for(var i=0;i<checks.length;i++){
		checks[i].checked = valorBoleano;
	}
}

/**
* Funcion que muestra y oculta secciones de codigo cuando se hace click en un radio button.
* Instancia del radio button que lanza el evento o el nombre del radio button que debe lanzar el evento.
* Muestra el conjunto de secciones indicadas en seccionesMostrar.
* Oculta el conjunto de secciones indicadas en seccionesOcultar.
* Limpia los cuadros de texto indicados en textosResetear.
* Muestra el foco en el cuadro de texto indicado en nombreTextoFoco.
* Si el radio button es seleccionado haciendo click en un span o div, el indice indica cual de los
* radio button que tienen un nombre debe seleccionarse.
*/
function mostrarUOcultarTextosConRadioButton(radioButton,seccionesMostrar,seccionesOcultar,textosResetear,nombreTextoFoco,formulario,indice){			
	if(typeof radioButton == "string"){	
		radio = document.getElementsByName(radioButton)[indice];		
		radio.checked=true;		
	}
	for(var i=0; i<seccionesMostrar.length;i++){
		document.getElementById(formulario+':'+seccionesMostrar[i]).style.display="block";
		document.getElementById(formulario+':'+seccionesMostrar[i]).style.visibility="visible";
	}
	for(var i=0; i<seccionesOcultar.length;i++){	
		document.getElementById(formulario+':'+seccionesOcultar[i]).style.display="none";
		document.getElementById(formulario+':'+seccionesOcultar[i]).style.visibility="hidden";			
		
	}
	if(formulario != "" && formulario!= ''){
		for(var i=0; i<textosResetear.length;i++){	
			textos = document.getElementById(formulario+':'+textosResetear[i]);
			textos.value="";
		}
		if(nombreTextoFoco != null && nombreTextoFoco != ''){
			document.getElementById(formulario+':'+nombreTextoFoco).focus();
		}
	}
}
/**
 * Desactivar Radio Boton
 * @param objeto - Los objetos (radio) que se desactivará
 */
function desactivarRadios(radiosDesactivar,formulario){
	for(var i=0; i<radiosDesactivar.length;i++){	
		document.getElementById(formulario+':'+radiosDesactivar[i]+':0').checked=false;
	}
}
/**
* Selecciona el item por defecto del combo
* @param objeto - Los objetos (combos) que se desactivará
*/
function resetearCombos(combosResetear,formulario){
	for(var i=0; i<combosResetear.length;i++){
		document.getElementById(formulario+':'+combosResetear[i]).selectedIndex=0;
	}	
}
/**
 * Funcion que permite ingresar solo numeros
 */
function solo_numeros(){
	var key=window.event.keyCode;
	if (key < 48 || key > 57){
		window.event.keyCode=0;
	}
}
/**
 * Concatenar los nombres y apellidos en una sola cadena de texto
 */
function concatenarNombreCompleto(NombreCompleto,txtPrimerApellido,txtSegundoApellido,txtPrimerNombre,txtSegundoNombre){
var nombreCompleto  = document.getElementById(txtPrimerApellido).value + " " +document.getElementById(txtSegundoApellido).value + " " + document.getElementById(txtPrimerNombre).value + " " + document.getElementById(txtSegundoNombre).value; 
document.getElementById(NombreCompleto).value  = nombreCompleto.toUpperCase();
}

function sincronizarScroll(scrollOrigen, scrollsDestino)
{
	objOrigen = document.getElementById(scrollOrigen);
	for(var i=0; i<scrollsDestino.length; i++){
		objDestino=document.getElementById(scrollsDestino[i].id);
		if(scrollsDestino[i].moverScrollHorizontal){
			objDestino.scrollLeft=objOrigen.scrollLeft;
		}
		if(scrollsDestino[i].moverScrollVertical){
			objDestino.scrollTop=objOrigen.scrollTop;
		}
		
	}
}