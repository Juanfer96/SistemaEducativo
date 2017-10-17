package sistemaeducativo;

import java.util.Hashtable;

public class Profesor extends Persona
{
    private Hashtable <String, Asignatura> competencia=new Hashtable<String,Asignatura>();
       
    public Profesor(String legajo, String apellido, String nombre, String domicilio, String mail)
    {
        super(legajo, apellido, nombre, domicilio, mail);
    }

    public Profesor()
    {
        super();
    }

    public void setCompetencia(Hashtable<String, Asignatura> competencia)
    {
        this.competencia = competencia;
    }

    public Hashtable<String, Asignatura> getCompetencia()
    {
        return competencia;
    }
    
    public void agregarCompetencia(Asignatura a)
    {
        if(this.getCompetencia().containsKey(a.getId()))
        {
            //exceptcion a hacer si la correlatividad ya existe 
        }else
        {
            this.getCompetencia().put(a.getId(), a);
        }
    }
    public void eliminarCompetencia(String id)
    {
        if(this.getCompetencia().remove(id)==null)
        {
            //excepcion de que quiero eliminar una correlatividad q no existe(no econtro la clave)
        }   
    }
    @Override
    public String toString()
    {
        // TODO Implement this method
        return "Nombre: "+this.getNombre()+"   Apellido: "+this.getApellido()+"   Legajo: "+this.getLegajo() ;
    }
}
