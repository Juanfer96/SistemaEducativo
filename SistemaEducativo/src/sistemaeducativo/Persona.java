package sistemaeducativo;

public abstract class Persona
{
    private String legajo;
    private String apellido;
    private String nombre;
    private String domicilio;
    private String mail;
    public Persona()
    {
        
    }

    public Persona(String legajo, String apellido, String nombre, String domicilio, String mail)
    {
        this.legajo = legajo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.mail = mail;
    }

    public void setLegajo(String legajo)
    {
        this.legajo = legajo;
    }

    public String getLegajo()
    {
        return legajo;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setDomicilio(String domicilio)
    {
        this.domicilio = domicilio;
    }

    public String getDomicilio()
    {
        return domicilio;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getMail()
    {
        return mail;
    }
}
