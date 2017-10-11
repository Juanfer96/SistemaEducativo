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
    
    public void agregarCompetencia(Asignatura a)
    {
        if(this.getHistoria().containsKey(a.getId()))
        {
            //exceptcion a hacer si la correlatividad ya existe 
        }else
        {
            this.getHistoria().put(a.getId(), a);
        }
    }
    public void eliminarCompetencia(String id)
    {
        if(this.getHistoria().remove(id)==null)
        {
            //excepcion de que quiero eliminar una correlatividad q no existe(no econtro la clave)
        }   
    }
}