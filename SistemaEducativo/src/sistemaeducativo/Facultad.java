package sistemaeducativo;

import java.util.TreeMap;

public class Facultad
{
    //askdmksdmk
    TreeMap <String,Alumno> alumnos=new TreeMap<>();
    TreeMap <String,Profesor> profesores=new TreeMap<>();
    TreeMap <String,Asignatura> asignaturas=new TreeMap<>();
    TreeMap <String,Cursada> cursadas=new TreeMap<>();
    
    public Facultad()
    {
        super();
    }

    public TreeMap<String, Alumno> getAlumnos()
    {
        return alumnos;
    }

    public TreeMap<String, Profesor> getProfesores()
    {
        return profesores;
    }

    public TreeMap<String, Asignatura> getAsignaturas()
    {
        return asignaturas;
    }

    public TreeMap<String, Cursada> getCursadas()
    {
        return cursadas;
    }
    
    public void agregarAlumno(String legajo, String apellido, String nombre, String domicilio, String mail)
    {
        if(this.getAlumnos().containsKey(legajo))
        {
            //exception a hacer si el alumno(segun legajo) ya existe 
        }else
        {
            Alumno a = new Alumno(legajo,apellido,nombre,domicilio,mail);
            this.getAlumnos().put(a.getLegajo(), a);
        }
    }
    
    public void agregarProfesor(String legajo, String apellido, String nombre, String domicilio, String mail)
    {
        if(this.getProfesores().containsKey(legajo))
        {
            //exception a hacer si el alumno(segun legajo) ya existe 
        }else
        {
            Profesor a = new Profesor(legajo,apellido,nombre,domicilio,mail);
            this.getProfesores().put(a.getLegajo(), a);
        }
    }
    
    public void agregarAsignatura(String id, String nombre)
    {
        if(this.getAsignaturas().containsKey(id))
        {
            //exception a hacer si el alumno(segun legajo) ya existe 
        }else
        {
            Asignatura a = new Asignatura(id,nombre);
            this.getAsignaturas().put(a.getId(), a);
        }
    }
    
    public void agregarCursada(String id, Asignatura asignatura, String dia, String hora, String periodo)
    {
        if(this.getCursadas().containsKey(id))
        {
            //exception a hacer si el alumno(segun legajo) ya existe 
        }else
        {
            Cursada a = new Cursada(id, asignatura, dia, hora, periodo);
            this.getCursadas().put(a.getId(), a);
        }
    }
    
    /**
     * pre: Se considera que el id es de un alumno existente.
     * @param id
     */
    public void bajaAlumno(String legajo) {
        this.getAlumnos().remove(legajo);
        
    }
}
