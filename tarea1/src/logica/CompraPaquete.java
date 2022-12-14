package logica;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import excepciones.ActividadNoExisteException;
import excepciones.NoHayCuposException;


public class CompraPaquete{
	
	private Paquete paq;
	private int cantidad;
	private float costo; 
	private Date fecha;
	private Date vencimiento;
	private Map<String, Integer> restAct;
	
	public CompraPaquete(Date fecha, int cant, Paquete paquete) {
		setCantidad(cant);
		setFecha(fecha);
		setPaq(paquete);
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		calendario.add(Calendar.DAY_OF_MONTH, paquete.getValidez());
		setVencimiento(calendario.getTime());
		Collection<Actividad> acts = paquete.getColAct().values();
		setRestAct(new HashMap<String, Integer>());
		float cost = 0;
		for (Actividad aux: acts) {
			this.restAct.put(aux.getNombre(), cant);
			cost = cost + aux.getCosto();
		}
		float aux = 100 - paquete.getDescuento();
		cost = cost*(aux/100)*cant;
		setCosto(cost);
		if (!this.paq.isComprado()) {
			getPaq().setComprado(true);
		}
	}
	
	int obtenerCuposAct(String act) {
		return getRestAct().get(act);
	}
	
	void reducirCuposAct(String act, int cant) throws NoHayCuposException, ActividadNoExisteException {
		if (restAct.get(act) == null) {
			throw new ActividadNoExisteException("El paquete " + getPaq().getNombre() + " no contiene a la actividad " + act);
		}
		if (cant > obtenerCuposAct(act))
			throw new NoHayCuposException("No hay cupos suficientes en la compra paquete para la actividad:" + act);
		this.restAct.replace(act, this.obtenerCuposAct(act)-cant);
	}
	
    
	public int getCantidad() {
        return cantidad;
    }
	
	public Date getFecha() {
        return fecha;
    }
	
	public float getCosto() {
        return costo;
    }
	

    public Paquete getPaq() {
		return paq;
	}
    
    public int getDescuento() {
    	return paq.getDescuento();
    }

	public Date getVencimiento() {
		return vencimiento;
	}

	public Map<String, Integer> getRestAct() {
		return restAct;
	}

	public void setPaq(Paquete paq) {
		this.paq = paq;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public void setRestAct(Map<String, Integer> restAct) {
		this.restAct = restAct;
	}

	public void setCantidad(int cant) {
    	cantidad = cant;
    }

    public void setFecha(Date fechaC) {
    	fecha = fechaC;
    }

    public void setCosto(float cost) {
    	costo = cost;
    }
    
    public DataCompraPaquete getDataCompraPaquete() {
    	return new DataCompraPaquete(this.getFecha(), this.getCantidad(), this.getCosto(), this.getVencimiento(), this.getPaq().getDataP(), this.getRestAct());
    }
    
}