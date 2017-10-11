package sistemaeducativo;

import java.util.TreeMap;

public class Facultad
{
    //Actualizaciooooooooooon 123456
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
            //exceptcion a hacer si el alumno(segun legajo) ya existe 
        }else
        {
            Alumno a = new Alumno(legajo,apellido,nombre,domicilio,mail);
            this.getAlumnos().put(a.getLegajo(), a);
        }
    }
}
