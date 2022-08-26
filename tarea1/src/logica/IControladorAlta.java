package logica;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import excepciones.ActividadRepetidaException;
import excepciones.DepartamentoNoExisteException;
import excepciones.DepartamentoYaExisteExeption;
import excepciones.SalidaYaExisteExeption;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

/**
 * @author TProg2017
 *
 */
public interface IControladorAlta {
    
    /**
     * Registra al usuario en el sistema.
     * @param n Nombre del usuario.
     * @param ap Apellido del usuario.
     * @param ci Cédula del usuario.
     * @throws UsuarioRepetidoException Si la cédula del usuario se encuentra registrada en el sistema.
     */
    public abstract void confirmarAltaTurista(String nick, String nom , String ap, String mail ,Date nacimiento ,String nacionalidad) throws UsuarioRepetidoException;

    public abstract void confirmarAltaProveedor(String nick, String nom , String ap, String mail ,Date nacimiento ,String descripcion, String link, boolean hayLink) throws UsuarioRepetidoException;
    
    public abstract DataDepartamento[] obtenerDataDepartamentos() throws DepartamentoNoExisteException;
    
    public abstract void registrarActividad(String dep, String nom , String desc,int dur, int costo, String ciudad ,Date f,String proveedor) throws ActividadRepetidaException;

    /**
     * Retorna la información de un usuario con la cédula indicada.
     * @param ci Cédula del usuario.
     * @return Información del usuario.
     * @throws UsuarioNoExisteException Si la cédula del usuario no está registrada en el sistema.
     */
    public abstract DataUsuario verInfoUsuario(String ci) throws UsuarioNoExisteException;

    /**
     * Retorna la información de todos los usuarios registrados en el sistema.
     * @return Información de los usuarios del sistema.
     * @throws UsuarioNoExisteException Si no existen usuarios registrados en el sistema.
     */
    public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;

	public abstract void cargarDptos() throws IOException, DepartamentoYaExisteExeption;

	public abstract void cargarUsuarios() throws IOException, UsuarioRepetidoException, ParseException ;
    public abstract void confirmarAltaSalida(String nombreActividad, String nombreSalida, Date fecha, Date hora, String lugar, int maxCantTuristas, Date fechaAlta) throws SalidaYaExisteExeption ;

	public abstract void cargarActs() throws IOException, DepartamentoYaExisteExeption, NumberFormatException, ActividadRepetidaException, ParseException;

	public abstract void cargarSalidas() throws NumberFormatException, IOException, ParseException, SalidaYaExisteExeption;
   
}
