package logica;

import java.util.Date;

public abstract class Usuario {

	private String nickname;
    private String nombre;
    private String apellido;
    private String mail;
    private Date nacimiento;
    private String password;
    private byte[] imagen;

    public Usuario(String nickname, String nombre, String apellido, String mail, Date nacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.mail = mail;
        this.nacimiento = nacimiento;
        password = "1234";
        imagen = null;
    }
   
    public Usuario(String nickname, String nombre, String apellido, String mail, Date nacimiento, String pass, byte[] imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.mail = mail;
        this.nacimiento = nacimiento;
        this.password = pass;
        this.imagen = imagen;
    }
    public Usuario(String nickname, String nombre, String apellido, String mail, Date nacimiento, String pass) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.mail = mail;
        this.nacimiento = nacimiento;
        password = pass;
        imagen = null;
    }
    
    /* Getters */
    public abstract DataUsuario getDataUsuario();
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNickname() {
        return nickname;
    }
    
    public String getMail() {
        return mail;
    }
    
    public Date getNacimiento() {
        return nacimiento;
    }    
    
    /* Setters */
    
    public void setNombre(String nom) {
        nombre = nom;
    }

    public void setApellido(String apellidoU) {
        apellido = apellidoU;
    }

    public void setNickname(String nick) {
    	nickname = nick;
    }

    public void setMail(String mailU) {
    	mail = mailU;
    }

    public void setNacimiento(Date nac) {
        nacimiento = nac;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public abstract DataUsuario getDataUsuarioComp();
}
