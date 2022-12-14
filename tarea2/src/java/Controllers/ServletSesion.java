package java.Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excepciones.DepartamentoYaExisteExeption;
import excepciones.UsuarioNoExisteException;
import logica.DataUsuario;
import logica.Fabrica;
import logica.IControladorAlta;

/**
 * Servlet implementation class Home
 */
@WebServlet (urlPatterns={"/iniciarSesion","/cerrarSesion","/sesionCerrada","/sesionIniciada","/home"})
public class ServletSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * inicializa la sesión si no estaba creada 
	 * @param request 
	 */
	public static void initSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("estado_sesion") == null) {
			session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
		}
	}
	
	/**
	 * Devuelve el estado de la sesión
	 * @param request
	 * @return 
	 */
	public static EstadoSesion getEstado(HttpServletRequest request)
	{
		return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, DepartamentoYaExisteExeption, UsuarioNoExisteException {
		initSession(req);
		String solicitud = req.getServletPath();
		
		switch(solicitud) {
			case "/home":
				// hace que se ejecute el jsp sin cambiar la url
				req.getRequestDispatcher("/WEB-INF/home/iniciar.jsp").forward(req, resp);
				break;
			case "/iniciarSesion":
				Fabrica f = Fabrica.getInstance();
				IControladorAlta ca = f.getIControladorAlta();
				DataUsuario[] ususSistema = ca.getUsuarios();
				HttpSession ses = req.getSession();
				String nickOrEmail = (String) ses.getAttribute("emailnick_inicioSesion");
				for (DataUsuario it : ususSistema) {
					if(it.getMail()==nickOrEmail) {
						String password = (String) ses.getAttribute("pass_iniSesion");
						if (password == it.getNombre()) { //CAMBIARRRRRRRRR
							//Sesion iniciada correctamente
							ses.setAttribute("usuario", it);
							resp.sendRedirect("/turismo.uy/home");
							break;
						}
						else {
							req.setAttribute("error_contrasena","error");
							resp.sendRedirect("/turismo.uy/iniciarSesion");
							break;
						}
					}
				}
				req.setAttribute("error_emailnick","error");
				resp.sendRedirect("/turismo.uy/iniciarSesion");
				break;
			case "/cerrarSesion":
				break;
			case "/sesionIniciada":
				break;
			case "/sesionCerrada":
				break;
		}
		
		
		switch(getEstado(req)){
			case NO_LOGIN:
				// hace que se ejecute el jsp sin cambiar la url
				req.getRequestDispatcher("/WEB-INF/home/iniciar.jsp").
						forward(req, resp);
				break;
			case LOGIN_INCORRECTO:
				// hace que se ejecute el jsp sin cambiar la url
				req.getRequestDispatcher("/WEB-INF/home/inicioErroneo.jsp").
						forward(req, resp);
				break;
			case LOGIN_CORRECTO:
				// manda una redirección a otra URL (cambia la URL)
				resp.sendRedirect("/gamebook/perfil");
				break;
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (DepartamentoYaExisteExeption e) {
			
		} catch (UsuarioNoExisteException e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (DepartamentoYaExisteExeption e) {
			
		} catch (UsuarioNoExisteException e) {
			
		}
	}

}