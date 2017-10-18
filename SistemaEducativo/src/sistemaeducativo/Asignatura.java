package sistemaeducativo;

import java.util.Hashtable;

public class Asignatura
{
    private String id;
    private String nombre;
    private Hashtable<String,Asignatura> correlatividades=new Hashtable<String,Asignatura>();

    public Asignatura(String id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Asignatura(){
        super();
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setCorrelatividades(Hashtable<String, Asignatura> correlatividades)
    {
        this.correlatividades = correlatividades;
    }

    public Hashtable<String, Asignatura> getCorrelatividades()
    {
        return correlatividades;
    }
    public void agregarCorrelatividad(Asignatura a) throws CorrelativaRegistradaException
    {
        if(this.getCorrelatividades().containsKey(a.getId()))
        {
            throw new CorrelativaRegistradaException(a,this);
        }else
        {
            this.getCorrelatividades().put(a.getId(), a);
        }
    }
    public void eliminarCorrelatividad(String id)
    {
        this.getCorrelatividades().remove(id);
    }

    @Override
    public String toString()
    {
        // TODO Implement this method
        return "Nombre: "+this.getNombre()+" ID: "+this.getId();
    }
}
