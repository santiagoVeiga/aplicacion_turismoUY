package manejadores;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import excepciones.ActividadNoExisteException;
import excepciones.SalidaYaExisteExeption;
import excepciones.SalidasNoExisteException;
import logica.Actividad;
import logica.DataActividad;
 
public class ManejadorActividad {

	// Atributos

	private static ManejadorActividad instancia = null;

	private Map<String, Actividad> colAct;
	
	// Constructor
    
	private ManejadorActividad() {
    	colAct = new HashMap<String, Actividad>();
    }

    public static ManejadorActividad getInstance() {
        if (instancia == null) {
            instancia = new ManejadorActividad();
        }
        return instancia;
    }

    //Operaciones
    public void addActividad(Actividad act) {
        String nombre = act.getNombre();
        colAct.put(nombre, act);
    }

    public Set<DataActividad> getDAct() {
    	Set<DataActividad> resultado = new HashSet<DataActividad>();
    	Set<Entry<String, Actividad>> aux = colAct.entrySet();
    	Iterator<Entry<String, Actividad>> iter = aux.iterator();
    	while (iter.hasNext()){
    		resultado.add(iter.next().getValue().getDataAT());
    	}
    	return resultado;
    }

    public Map<String, Actividad> getColAct() {
    	return colAct ;
    }

    public Actividad getActividad(String nom) throws ActividadNoExisteException {
    	return colAct.get(nom);
    }
    
	public void verificarSalida(String nombreSalida) throws SalidaYaExisteExeption {
		Set<Entry<String, Actividad>> aux = colAct.entrySet();
    	Iterator<Entry<String, Actividad>> iter = aux.iterator();
    	while (iter.hasNext()){
    		if (iter.next().getValue().perteneceSalida(nombreSalida)) {
    			throw new SalidaYaExisteExeption("Ya existe una salida registrada con el nombre: " + nombreSalida);
    		}
    	}
	}
	
	public String obtenerNomActvidiadDeSalida(String salida) throws SalidasNoExisteException {
		String res =  null;
		for (Actividad it : colAct.values()) {
			if (it.perteneceSalida(salida)) {
				res = it.getNombre();
			}
		}
		if (res == null) {
			throw new SalidasNoExisteException("No existe una salida con nombre" + salida);
		}
		return res;
	}
	
}