package logica;
import java.util.Date;
import java.util.Map;


public class DataCompraPaquete{
	
	private DataPaquete paq;
	private int cantidad;
	private float costo; 
	private Date fecha;
	private Date vencimiento;
	private Map<String, Integer> restAct;
	
	public DataCompraPaquete(Date fecha, int cant, float costo, Date vencimiento, DataPaquete paquete, Map<String, Integer> restAct) {
		this.setCantidad(cant);
		this.setCosto(costo);
		this.setFecha(fecha);
		this.setVencimiento(vencimiento);
		this.setPaq(paquete);
		this.setRestAct(restAct);
	}
	
	int obtenerCuposAct(String act) {
		return this.restAct.get(act);
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
	

    public DataPaquete getPaq() {
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

	public void setPaq(DataPaquete paq) {
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
    
}