package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opencsv.CSVReader;

import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.DepartamentoNoExisteException;
import excepciones.DepartamentoYaExisteExeption;
import excepciones.ExcedeTuristas;
import excepciones.FechaAltaSalidaAnteriorActividad;
import excepciones.FechaAltaSalidaInvalida;
import excepciones.InscFechaDespSalida;
import excepciones.InscFechaInconsistente;
import excepciones.NoHayCuposException;
import excepciones.PaqueteNoExisteException;
import excepciones.PaqueteRepetidoException;
import excepciones.ProveedorNoNacidoException;
import excepciones.SalidaYaExisteExeption;
import excepciones.TuristaConSalida;
import excepciones.TuristaNoHaNacido;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.DataDepartamento;
import logica.DataUsuario;
import logica.Fabrica;
import logica.IControladorAlta;
import logica.IControladorConsulta;
import logica.IControladorInsc;

/**
 * Servlet implementation
 */
@WebServlet (urlPatterns={"/iniciarSesion", "/cerrarSesion", "/sesionCerrada", "/sesionIniciada", "/home"})
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
 * inicializa la sesi??n si no estaba creada 
 * @param request 
 */
public void initSession(HttpServletRequest request) {
	HttpSession session = request.getSession();
	if (session.getAttribute("estado_sesion") == null) {
		session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
    	Fabrica fab = Fabrica.getInstance();
        IControladorAlta conAlta = fab.getIControladorAlta();
        try {
            conAlta.cargarCategorias();
            // Categs listas
            ServletContext servletContext = request.getServletContext();
            InputStream input = servletContext.getResourceAsStream("/WEB-INF/data/Departamentos.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(input));
            conAlta.cargarDptos(reader);
            // dptos listos
            input = servletContext.getResourceAsStream("/WEB-INF/data/Usuarios.csv");
            reader = new CSVReader(new InputStreamReader(input));
            Map<String, byte[]> imagenes = new HashMap<String, byte[]>();
            for (int i=1; i<=13; i++ ) {
                BufferedImage img = ImageIO.read(servletContext.getResourceAsStream("/WEB-INF/data/Users/u"+Integer.toString(i)+".jpg"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", baos);
                byte[] imgBytes = baos.toByteArray();
                imagenes.put(Integer.toString(i), imgBytes);
            }
            conAlta.cargarUsuarios(reader, imagenes);
            // usus listos
            input = servletContext.getResourceAsStream("/WEB-INF/data/Actividades.csv");
            reader = new CSVReader(new InputStreamReader(input));
            imagenes.clear();
            for (int i=1; i<=10; i++ ) {
                BufferedImage img = ImageIO.read(servletContext.getResourceAsStream("/WEB-INF/data/Actvs/a"+Integer.toString(i)+".jpg"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", baos);
                byte[] imgBytes = baos.toByteArray();
                imagenes.put(Integer.toString(i), imgBytes);
            }
            conAlta.cargarActs(reader, imagenes);
            // acts listas
            input = servletContext.getResourceAsStream("/WEB-INF/data/Paquetes.csv");
            reader = new CSVReader(new InputStreamReader(input));
            imagenes.clear();
            for (int i=1; i<=3; i++ ) {
                BufferedImage img = ImageIO.read(servletContext.getResourceAsStream("/WEB-INF/data/Paqs/p"+Integer.toString(i)+".jpg"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", baos);
                byte[] imgBytes = baos.toByteArray();
                imagenes.put(Integer.toString(i), imgBytes);
            }
            conAlta.cargarPaquetes(reader, imagenes);
            //Paqs listos
            input = servletContext.getResourceAsStream("/WEB-INF/data/Salidas.csv");
            reader = new CSVReader(new InputStreamReader(input));
            imagenes.clear();
            for (int i=1; i<=18; i++ ) {
                InputStream instm = servletContext.getResourceAsStream("/WEB-INF/data/Salidas/s"+Integer.toString(i)+".jpg");
                byte[] imgBytes = null;
                if (instm != null) {
                    BufferedImage img = ImageIO.read(instm);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(img, "jpg", baos);
                    imgBytes = baos.toByteArray();
                }
                else {
                    BufferedImage img = ImageIO.read(servletContext.getResourceAsStream("/WEB-INF/data/default_imagen.jpg"));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(img, "jpg", baos);
                    imgBytes = baos.toByteArray();
                }
                imagenes.put(Integer.toString(i), imgBytes);
                imgBytes = null;
            }
            conAlta.cargarSalidas(reader, imagenes);
            IControladorInsc cins = fab.getIControladorInsc();
            input = servletContext.getResourceAsStream("/WEB-INF/data/ActividadesPaquetes.csv");
            reader = new CSVReader(new InputStreamReader(input));
            cins.cargarActsPaqs(reader);
            input = servletContext.getResourceAsStream("/WEB-INF/data/ComprasPaquetes.csv");
            reader = new CSVReader(new InputStreamReader(input));
            conAlta.cargarCompPaq(reader);
            input = servletContext.getResourceAsStream("/WEB-INF/data/Inscripciones.csv");
            reader = new CSVReader(new InputStreamReader(input));
            cins.cargarInsc(reader);
        } catch (NumberFormatException | IOException | UsuarioRepetidoException | ParseException
                | DepartamentoYaExisteExeption | ActividadRepetidaException | UsuarioNoExisteException
                | ProveedorNoNacidoException | SalidaYaExisteExeption | PaqueteRepetidoException
                | FechaAltaSalidaInvalida | FechaAltaSalidaAnteriorActividad | TuristaConSalida
                | ExcedeTuristas | InscFechaInconsistente | ActividadNoExisteException | InscFechaDespSalida
                | TuristaNoHaNacido | NoHayCuposException | PaqueteNoExisteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println(e1.toString());
        }
	}
    
}

/**
 * Devuelve el estado de la sesi??n
 * @param request
 * @return 
 */
public static EstadoSesion getEstado(HttpServletRequest request){
	return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
}

public void selecDep(HttpServletRequest req, HttpServletResponse resp, String nomDpto) {
    HttpSession session = req.getSession();
    DataDepartamento[] aux = (DataDepartamento[]) session.getAttribute("dptos");
    for (DataDepartamento it : aux) {
        if (it.getNombre().equals(nomDpto)) {
            session.setAttribute("DTDConsultaActividad", it);
        }
    }
    try {
        resp.sendRedirect("/tarea2p2/ConsultaActividad");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void selecCat(HttpServletRequest req, HttpServletResponse resp, String nomCat) {
    HttpSession session = req.getSession();
    session.setAttribute("CatConsultaActividad", nomCat);
    try {
        resp.sendRedirect("/tarea2p2/ConsultaActividad");
    } catch (IOException e) {
        e.printStackTrace();
    }
}


private void processRequest(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException, DepartamentoYaExisteExeption, UsuarioNoExisteException {
	initSession(req);
	String solicitud = req.getServletPath();
	Fabrica fab = Fabrica.getInstance();
	IControladorAlta conAlta = fab.getIControladorAlta();
	IControladorConsulta conCons = fab.getIControladorConsulta();
	HttpSession ses = req.getSession();
    String nomDpto = req.getParameter("DTDConsultaActividad");
    String nomCat = req.getParameter("CatConsultaActividad");
    if (nomDpto != null) {
        selecDep(req, resp, nomDpto);
    } else if (nomCat != null) {
        selecCat(req, resp, nomCat);
    } else {
    	switch (solicitud) {
    		case "/home":
    		    String act = (String) req.getParameter("actividad");
    		    if (act != null) {
    		        ses.setAttribute("actividad_inicio", act);
    		        resp.sendRedirect("/tarea2p2/ConsultaActividad");
    		    }
    		    else {
    		        DataDepartamento[] aux = null;
        			try {
        				aux = conCons.obtenerDataDepartamentos();
        			} catch (DepartamentoNoExisteException e) {
        				System.out.println("no hay deptos");
        			}
        			ses.setAttribute("dptos", aux);
        			Set<String> cats = conCons.obtenerNombreCategorias();
        			ses.setAttribute("categorias", cats);
        			req.getRequestDispatcher("/WEB-INF/home/iniciar.jsp").forward(req, resp);
    		    }
    			break;
    		case "/iniciarSesion":
    			req.getRequestDispatcher("/WEB-INF/home/iniciarSesion.jsp").forward(req, resp);
    			break;
    		case "/cerrarSesion":
    			ses.setAttribute("usuario", null);
    			ses.setAttribute("imagenUsuario", null);
    			ses.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
    			req.getRequestDispatcher("/WEB-INF/home/cerrarSesion.jsp").forward(req, resp);
    			break;
    		case "/sesionIniciada":
    			DataUsuario[] ususSistema = conAlta.getUsuariosComp();
    			String nickOrEmail = (String) req.getParameter("emailnick_inicioSesion");
    			for (DataUsuario it : ususSistema) {
    				if (it.getMail().equals(nickOrEmail)) {
    					String password = (String) req.getParameter("pass_iniSesion");
    					if (it.getPassword().equals(password)) { 
    						//Sesion iniciada correctamente
    						ses.setAttribute("usuario", it);
    						// Setear imagen
    						String imageAsBase64 = null;
    						if (it.getImagen()!=null) {
    						    InputStream inSt = new ByteArrayInputStream(it.getImagen());
        				        BufferedImage buffIM = ImageIO.read(inSt);
        				        ByteArrayOutputStream output = new ByteArrayOutputStream();
        						ImageIO.write(buffIM, "jpg", output);
        						imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
    						}
    				        ses.setAttribute("imagenUsuario", imageAsBase64);
    				        //
    				        ses.setAttribute("estado_sesion", EstadoSesion.LOGIN_CORRECTO);
    						req.getRequestDispatcher("/WEB-INF/home/iniciar.jsp").forward(req, resp);
    						return;
    					}
    					else {
    						req.setAttribute("error_contrasena", "error");
    						req.getRequestDispatcher("/WEB-INF/home/iniciarSesion.jsp").forward(req, resp);
    						return;
    					}
    				}
    				else if (it.getNick().equals(nickOrEmail)) {
    					String password = (String) req.getParameter("pass_iniSesion");
    					if (it.getPassword().equals(password)) { 
    						//Sesion iniciada correctamente
    						ses.setAttribute("usuario", it);
    						// Setear imagen
    						String imageAsBase64 = null;
                            if (it.getImagen()!=null) {
                                InputStream inSt = new ByteArrayInputStream(it.getImagen());
                                BufferedImage buffIm = ImageIO.read(inSt );
                                ByteArrayOutputStream output = new ByteArrayOutputStream();
                                ImageIO.write(buffIm, "jpg", output);
                                imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
                            }
                            ses.setAttribute("imagenUsuario", imageAsBase64);
    				        //
    						ses.setAttribute("estado_sesion", EstadoSesion.LOGIN_CORRECTO);
    						resp.sendRedirect("/tarea2p2/home");
    						return;
    					}
    					else {
    						req.setAttribute("error_contrasena", "error");
    						req.getRequestDispatcher("/WEB-INF/home/iniciarSesion.jsp").forward(req, resp);
    						return;
    					}
    				}
    			}
    			req.setAttribute("error_emailnick", "error");
    			req.getRequestDispatcher("/WEB-INF/home/iniciarSesion.jsp").forward(req, resp);
    			break;
    		case "/sesionCerrada":
    			break;
    	}
    }
}

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		processRequest(request, response);
	} catch (DepartamentoYaExisteExeption e) {
		System.out.println("no hay dptos get");
	} catch (UsuarioNoExisteException e) {
        e.printStackTrace();
	} 
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		processRequest(request, response);
	} catch (DepartamentoYaExisteExeption e) {
		System.out.println("no hay depntos post");
		} catch (UsuarioNoExisteException e) {
            e.printStackTrace();
		}
	}

}