package sistemaeducativo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Facultad
{
    //cambio 2
    public static int PROXLEGAJOALUM;
    public static int PROXLEGAJOPROF;
    public static int PROXIDCURSADA;
    public static int PROXIDASIGNATURA;
    private TreeMap<String, Alumno> alumnos = new TreeMap<>();
    private TreeMap<String, Profesor> profesores = new TreeMap<>();
    private TreeMap<String, Asignatura> asignaturas = new TreeMap<>();
    private TreeMap<String, Cursada> cursadas = new TreeMap<>();

    public Facultad()
    {
        super();
        PROXLEGAJOALUM = 0;
        PROXLEGAJOPROF = 0;
        PROXIDASIGNATURA = 0;
        PROXIDCURSADA = 0;
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

    public void agregarAlumno(String apellido, String nombre, String domicilio, String mail)
    {
        String s = String.format("%04d", PROXLEGAJOALUM);
        String leg = "ALU" + s;
        Alumno a = new Alumno(leg, apellido, nombre, domicilio, mail);
        this.getAlumnos().put(a.getLegajo(), a);
    }

    public void agregarProfesor(String apellido, String nombre, String domicilio, String mail)
    {
        String s = String.format("%04d", ++PROXLEGAJOPROF);
        String leg = "PRO" + s;
        Profesor a = new Profesor(leg, apellido, nombre, domicilio, mail);
        this.getProfesores().put(a.getLegajo(), a);
    }

    public void agregarAsignatura(String nombre)
    {
        String s = String.format("%04d", ++PROXIDASIGNATURA);
        String id = "ASI" + s;
        Asignatura a = new Asignatura(id, nombre);
        this.getAsignaturas().put(a.getId(), a);
    }

    public void agregarCursada(Asignatura asignatura, String dia, String hora, String periodo)
    {
        String s = String.format("%04d", ++PROXIDCURSADA);
        String id = "ASI" + s;
        Cursada a = new Cursada(id, asignatura, dia, hora, periodo);
        this.getCursadas().put(a.getId(), a);
    }

    //Preguntar si cada alumno no tiene una coleccion de cursadas actuales. Idem profesores.

    /**
     * pre: Se considera que el legajo es de un alumno existente.
     * @param id
     */
    public void bajaAlumno(String legajo)
    {
        this.getAlumnos().remove(legajo);
        Cursada c;
        Iterator it = this.getCursadas()
                          .entrySet()
                          .iterator();
        while (it.hasNext())
        {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            c.eliminarAlumno(legajo);
        }
    }

    public void bajaProfesor(String legajo)
    {
        this.getProfesores().remove(legajo);
        Cursada c;
        Iterator it = this.getCursadas()
                          .entrySet()
                          .iterator();
        while (it.hasNext())
        {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            c.eliminarProfesor(legajo);
        }
    }

    public ArrayList<Alumno> buscarAlumnoPorNombre(String nombre, String apellido)
    {
        ArrayList<Alumno> alumnosReturn = new ArrayList<>();
        Alumno a;
        Iterator it = this.getAlumnos().entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry m = (Map.Entry) it.next();
            a = (Alumno) m.getValue();
            if (a.getNombre().equals(nombre) && a.getApellido().equals(apellido))
            {
                alumnosReturn.add(a);
            }
        }
        return alumnosReturn;
    }
    
    public ArrayList<Cursada> buscarCursadaPorNombre(String asignatura)
    {
        ArrayList<Cursada> cursadasReturn = new ArrayList<>();
        Cursada c;
        Iterator it = this.getCursadas().entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            if (c.getAsignatura().equals(asignatura))
            {
                cursadasReturn.add(c);
            }
        }
        return cursadasReturn;
    }
}