package sistemaeducativo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Facultad {

    TreeMap<String, Alumno> alumnos = new TreeMap<>();
    TreeMap<String, Profesor> profesores = new TreeMap<>();
    TreeMap<String, Asignatura> asignaturas = new TreeMap<>();
    TreeMap<String, Cursada> cursadas = new TreeMap<>();

    public Facultad() {
        super();
    }

    public TreeMap<String, Alumno> getAlumnos() {
        return alumnos;
    }

    public TreeMap<String, Profesor> getProfesores() {
        return profesores;
    }

    public TreeMap<String, Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public TreeMap<String, Cursada> getCursadas() {
        return cursadas;
    }

    public void agregarAlumno(String legajo, String apellido, String nombre, String domicilio, String mail) {
        if (this.getAlumnos().containsKey(legajo)) {
            //exception a hacer si el alumno(segun legajo) ya existe

        } else {
            Alumno a = new Alumno(legajo, apellido, nombre, domicilio, mail);
            this.getAlumnos().put(a.getLegajo(), a);
        }
    }

    public void agregarProfesor(String legajo, String apellido, String nombre, String domicilio, String mail) {
        if (this.getProfesores().containsKey(legajo)) {
            //exception a hacer si el alumno(segun legajo) ya existe

        } else {
            Profesor a = new Profesor(legajo, apellido, nombre, domicilio, mail);
            this.getProfesores().put(a.getLegajo(), a);
        }
    }

    public void agregarAsignatura(String id, String nombre) {
        if (this.getAsignaturas().containsKey(id)) {
            //exception a hacer si el alumno(segun legajo) ya existe

        } else {
            Asignatura a = new Asignatura(id, nombre);
            this.getAsignaturas().put(a.getId(), a);
        }
    }

    public void agregarCursada(String id, Asignatura asignatura, String dia, String hora, String periodo) {
        if (this.getCursadas().containsKey(id)) {
            //exception a hacer si el alumno(segun legajo) ya existe

        } else {
            Cursada a = new Cursada(id, asignatura, dia, hora, periodo);
            this.getCursadas().put(a.getId(), a);
        }
    }

    //Preguntar si cada alumno no tiene una coleccion de cursadas actuales. Idem profesores.
    /**
     * pre: Se considera que el legajo es de un alumno existente.
     * @param id
     */
    public void bajaAlumno(String legajo) {
        this.getAlumnos().remove(legajo);
        Cursada c;
        Iterator it = this.getCursadas().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry m=(Map.Entry)it.next();
            c = (Cursada) m.getValue();
            c.eliminarAlumno(legajo);
        }
    }
    
    public void bajaProfesor(String legajo) {
        this.getProfesores().remove(legajo);
        Cursada c;
        Iterator it = this.getCursadas().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry m=(Map.Entry)it.next();
            c = (Cursada) m.getValue();
            c.eliminarProfesor(legajo);
        }
    }
    
    public ArrayList<Alumno> buscarAlumnoPorNombre(String nombre,String apellido) {
        ArrayList<Alumno> alumnosReturn = new ArrayList<>();
        Alumno a;
        Iterator it = this.getAlumnos().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry m=(Map.Entry)it.next();
            a = (Alumno) m.getValue();
            if(a.getNombre().equals(nombre) && a.getApellido().equals(apellido)) {
                alumnosReturn.add(a);
            }
        }
        return alumnosReturn;
    }
}
