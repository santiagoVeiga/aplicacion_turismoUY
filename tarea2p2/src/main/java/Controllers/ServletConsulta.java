package Controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.DataActividad;
import logica.DataDepartamento;
import logica.DataPaquete;
import logica.DataUsuario;
import logica.Fabrica;
import logica.IControladorConsulta;

/**
 * Servlet implementation class Home
 */
@WebServlet (urlPatterns={"/ConsultaUsuario","/ConsultaActividad","/ConsultaSalida","/ConsultaPaquete"})

public class ServletConsulta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Fabrica fab = Fabrica.getInstance();;
	private IControladorConsulta conCons;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsulta() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		switch(req.getServletPath()){
			case "/ConsultaUsuario":
				String usuario = (String) req.getParameter("usuario");
				conCons = fab.getIControladorConsulta();
				if(usuario == null) {
					DataUsuario[] usuarios = conCons.listarUsuarios();
					req.setAttribute("ArregloUsuarios", usuarios);
					req.getRequestDispatcher("/WEB-INF/ListaUsuario.jsp").forward(req,resp);
				} else {
					DataUsuario du = conCons.ingresarDatos(usuario);
					req.setAttribute("UsuarioElegido", du);
					req.getRequestDispatcher("/WEB-INF/ConsultaUsuario.jsp").forward(req,resp);
				}				
				break;
			case "/ConsultaActividad":
				HttpSession session = req.getSession();
				String actividad = (String) req.getParameter("actividad"); 
				DataActividad[] actividades = null;
				if(session.getAttribute("DTDConsultaActividad")!= null) {
					actividades = ((DataDepartamento) session.getAttribute("DTDConsultaActividad")).getColAct().toArray(new DataActividad[0]);
				} else {
					String categoria = (String) session.getAttribute("CatConsultaActividad");
					if(categoria != null)
					    actividades = conCons.obtenerActividadCategoria(categoria);
				}
				if(actividad == null) {
					req.setAttribute("ArregloActividades", actividades);
					req.getRequestDispatcher("/WEB-INF/ConsultaActividad/ListaActividad.jsp").forward(req,resp);
				} else {
				    int index = 0;
					int i = 0;
					for(i=0;i<actividades.length;i++) {
					    if(actividades[i].getNombre()!=actividad) {
					        index=i;
					    }
					}
					
				    req.setAttribute("ActividadElegida", actividades[index]);
                    req.getRequestDispatcher("/WEB-INF/ConsultaActividad/DetalleActividad.jsp").forward(req,resp);
				}
				break;
			case "/ConsultaSalida":
				req.getRequestDispatcher("/WEB-INF/ConsultaActividad.jsp").forward(req,resp); //Ver si entregar el set de salidas o no, por ahora se devuelve el DataSalida que viene desde la lista.
				break;
			case "/ConsultaPaquete":
				String paquete = (String) req.getParameter("paquete");
				if(paquete == null) {
					conCons = fab.getIControladorConsulta();
					String[] nombresPaq = conCons.listarPaquetes();
					DataPaquete[] dps = new DataPaquete[nombresPaq.length];
					for(int i=0;i<nombresPaq.length;i++) {
						dps[i] = conCons.obtenerDataPaquete(nombresPaq[i]);
					}
					req.setAttribute("ArregloPaquetes", dps);
					req.getRequestDispatcher("/WEB-INF/ListaPaquetes.jsp").forward(req,resp);
				} else {
					DataPaquete dp = conCons.obtenerDataPaquete(paquete);
					req.setAttribute("PaqueteElegido", dp);
					req.getRequestDispatcher("/WEB-INF/ConsultaPaquete.jsp").forward(req,resp);
				}
				break;
			case "/DetallePaquete":
				break;
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
