package sistemaeducativo;

import java.util.Hashtable;

public class Alumno extends Persona
{
    private Hashtable <String, Asignatura> historia=new Hashtable<String,Asignatura>();
    
    public Alumno(String legajo, String apellido, String nombre, String domicilio, String mail)
    {
        super(legajo, apellido, nombre, domicilio, mail);
    }

    public Alumno()
    {
        super();
    }


    public void setHistoria(Hashtable<String, Asignatura> historia)
    {
        this.historia = historia;
    }

    public Hashtable<String, Asignatura> getHistoria()
    {
        return historia;
    }
    
    public void agregarHistoria(Asignatura a) throws AsignaturaRegistradaEnAlumnoException {
        if(this.getHistoria().containsKey(a.getId()))
        {
            throw new AsignaturaRegistradaEnAlumnoException(this,a);
            //exceptcion a hacer si la asignatura aprobada ya existe 
        }else
        {
            this.getHistoria().put(a.getId(), a);
        }
    }

    @Override
    public String toString()
    {
        // TODO Implement this method
        return "Nombre: "+this.getNombre()+"   Apellido: "+this.getApellido()+"   Legajo: "+this.getLegajo() ;
    }

    public void eliminarHistoria(String id)
    {
        this.getHistoria().remove(id);
    }
}