package sistemaeducativo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Facultad {
    public static int PROXLEGAJOALUM;
    public static int PROXLEGAJOPROF;
    public static int PROXIDCURSADA;
    public static int PROXIDASIGNATURA;
    private static Facultad instancia = null;
    private TreeMap<String, Alumno> alumnos = new TreeMap<>();
    private TreeMap<String, Profesor> profesores = new TreeMap<>();
    private TreeMap<String, Asignatura> asignaturas = new TreeMap<>();
    private TreeMap<String, Cursada> cursadas = new TreeMap<>();

    public Facultad() {
        super();
        PROXLEGAJOALUM = 0;
        PROXLEGAJOPROF = 0;
        PROXIDASIGNATURA = 0;
        PROXIDCURSADA = 0;
    }
    
    
    public static Facultad getInstancia()
    {
        if (instancia == null)
            instancia = new Facultad();
        return instancia;
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


    public void setAlumnos(TreeMap<String, Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void setProfesores(TreeMap<String, Profesor> profesores) {
        this.profesores = profesores;
    }

    public void setAsignaturas(TreeMap<String, Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void setCursadas(TreeMap<String, Cursada> cursadas) {
        this.cursadas = cursadas;
    }


    public static void setPROXLEGAJOALUM(int PROXLEGAJOALUM) {
        Facultad.PROXLEGAJOALUM = PROXLEGAJOALUM;
    }

    public static void setPROXLEGAJOPROF(int PROXLEGAJOPROF) {
        Facultad.PROXLEGAJOPROF = PROXLEGAJOPROF;
    }

    public static void setPROXIDCURSADA(int PROXIDCURSADA) {
        Facultad.PROXIDCURSADA = PROXIDCURSADA;
    }

    public static void setPROXIDASIGNATURA(int PROXIDASIGNATURA) {
        Facultad.PROXIDASIGNATURA = PROXIDASIGNATURA;
    }


    public static int getPROXLEGAJOALUM() {
        return PROXLEGAJOALUM;
    }

    public static int getPROXLEGAJOPROF() {
        return PROXLEGAJOPROF;
    }

    public static int getPROXIDCURSADA() {
        return PROXIDCURSADA;
    }

    public static int getPROXIDASIGNATURA() {
        return PROXIDASIGNATURA;
    }

    //Agregar entidades a las colecciones
    
    public Alumno agregarAlumno(String apellido, String nombre, String domicilio, String mail) {
        String s = String.format("%04d", ++PROXLEGAJOALUM);
        String leg = "ALU" + s;
        Alumno a = new Alumno(leg, apellido, nombre, domicilio, mail);
        this.getAlumnos().put(a.getLegajo(), a);
        return a;
    }

    public Profesor agregarProfesor(String apellido, String nombre, String domicilio, String mail) {
        String s = String.format("%04d", ++PROXLEGAJOPROF);
        String leg = "PRO" + s;
        Profesor a = new Profesor(leg, apellido, nombre, domicilio, mail);
        this.getProfesores().put(a.getLegajo(), a);
        return a;
    }

    public Asignatura agregarAsignatura(String nombre) {
        String s = String.format("%04d", ++PROXIDASIGNATURA);
        String id = "ASI" + s;
        Asignatura a = new Asignatura(id, nombre);
        this.getAsignaturas().put(a.getId(), a);
        return a;
    }

    public Cursada agregarCursada(Asignatura asignatura,  String periodo) {
        String s = String.format("%04d", ++PROXIDCURSADA);
        String id = "ASI" + s;
        Cursada a = new Cursada(id, asignatura, periodo);
        this.getCursadas().put(a.getId(), a);
        return a;
    }
    
    //Bajas de las entidades
    
    /**
     * pre: Se considera que cursada pertenece a una cursada existente.
     * @param cursada
     */
    public void bajaCursada(Cursada cursada) {
        this.getCursadas().remove(cursada.getId());
    }


    /**
     * El metodo ademas de dar de baja en la coleccion correspondiente la asignatura, da de baja a las cursadas de esa asignatura.
     * @param a
     */
    public void bajaAsignatura(Asignatura a) {
        ArrayList<Cursada> cursadas=this.cursadasDeAsignatura(a);
        Iterator it=cursadas.iterator();
        Cursada c;
        while(it.hasNext()) {
            c=(Cursada)it.next();
            this.bajaCursada(c);
        }
        this.getAsignaturas().remove(a.getId());
    }
    
    /**
     * El metodo devuelve en un ArrayList todas las cursadas de la asignatura pasada como parametro
     * @param a
     * @return
     */
    private ArrayList<Cursada> cursadasDeAsignatura(Asignatura a){
        Iterator it=this.getCursadas().entrySet().iterator();
        Cursada c;
        ArrayList<Cursada> cursadasReturn=new ArrayList<>();
        while(it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            if(c.getAsignatura()==a)
                cursadasReturn.add(c);
        }
        return cursadasReturn;
    }
        
    /**
     * pre: Se considera que el legajo es de un alumno existente.
     * @param id
     */
    public void bajaAlumno(String legajo) {
        this.getAlumnos().remove(legajo);
        Cursada c;
        Iterator it = this.getCursadas().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            c.eliminarAlumno(legajo);
        }
    }

    public void bajaProfesor(String legajo) {
        this.getProfesores().remove(legajo);
        Cursada c;
        Iterator it = this.getCursadas()
                          .entrySet()
                          .iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            c.eliminarProfesor(legajo);
        }
    }

    //Consultas de las entidades

    public ArrayList<Alumno> buscarAlumnoPorNombre(String nombre, String apellido) {
        ArrayList<Alumno> alumnosReturn = new ArrayList<>();
        Alumno a;
        Iterator it = this.getAlumnos()
                          .entrySet()
                          .iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            a = (Alumno) m.getValue();
            if (a.getNombre().equals(nombre) && a.getApellido().equals(apellido)) {
                alumnosReturn.add(a);
            }
        }
        return alumnosReturn;
    }
    
    public ArrayList<Profesor> buscarProfesorPorNombre(String nombre, String apellido) {
        ArrayList<Profesor> profesoresReturn = new ArrayList<>();
        Profesor p;
        Iterator it = this.getProfesores()
                          .entrySet()
                          .iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            p = (Profesor) m.getValue();
            if (p.getNombre().equals(nombre) && p.getApellido().equals(apellido)) {
                profesoresReturn.add(p);
            }
        }
        return profesoresReturn;
    }

    public ArrayList<Cursada> buscarCursadaPorNombre(String asignatura) {
        ArrayList<Cursada> cursadasReturn = new ArrayList<>();
        Cursada c;
        Iterator it = this.getCursadas()
                          .entrySet()
                          .iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            if (c.getAsignatura().equals(asignatura)) {
                cursadasReturn.add(c);
            }
        }
        return cursadasReturn;
    }
    
    public ArrayList<Asignatura> buscarAsignaturaPorNombre(String asignatura) {
        ArrayList<Asignatura> asignaturasReturn = new ArrayList<Asignatura>();
        Asignatura a;
        Iterator it = this.getAsignaturas()
                          .entrySet()
                          .iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            a = (Asignatura) m.getValue();
            if (a.getNombre().equals(asignatura)) {
                asignaturasReturn.add(a);
            }
        }
        return asignaturasReturn;
    }
    
    //Metodos de Cursada
    
    public void agregarAlumnoEnCursada(Alumno a, Cursada c) throws AlumnoRegistradoEnCursadaException, AlumnoInhabilitadoException, AlumnoOcupadoParaCursadaException {
        if(c.getAlumnos().containsKey(a.getLegajo())) {
            throw new AlumnoRegistradoEnCursadaException(a,c);
        }
        else{
            if(!alumnoHabilitadoParaCursada(a, c)) {
                throw new AlumnoInhabilitadoException(a, c);
            }
            else{
                if(this.alumnoOcupadoParaCursada(a, c)) {
                    throw new AlumnoOcupadoParaCursadaException(a,c);
                }
                else {
                    c.agregarAlumno(a);
                }
            }
        }
    }
    
    public ArrayList<Cursada> cursadasDeAlumno(Alumno a) {
        ArrayList<Cursada> cursadasReturn=new ArrayList<Cursada>();
        Iterator it = this.getCursadas().entrySet().iterator();
        Cursada c;
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            if (c.contieneAlumno(a)) {
                cursadasReturn.add(c);
            }
        }
        return cursadasReturn;
    }
    
    private boolean alumnoOcupadoParaCursada(Alumno a, Cursada c) {
        ArrayList<Cursada>cursadasAlumno=this.cursadasDeAlumno(a);
        if(cursadasAlumno.size()>0) {
            Iterator itCursadas=cursadasAlumno.iterator();
            Iterator itHorarioCursada=c.getHorario().iterator();
            Iterator itHorariosAlumno;
            Fecha horarioAlumno,horarioCursada;
            Cursada cAux;
            while(itHorarioCursada.hasNext()) {
                horarioCursada=(Fecha)itHorarioCursada.next();
                while(itCursadas.hasNext()) {
                    cAux=(Cursada)itCursadas.next();
                    itHorariosAlumno=cAux.getHorario().iterator();
                    while(itHorariosAlumno.hasNext()) {
                        horarioAlumno=(Fecha)itHorariosAlumno.next();
                        if(horarioCursada.superpone(horarioAlumno)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean alumnoHabilitadoParaCursada(Alumno a, Cursada c) {
        boolean rta=true;
        String asignaturaId;
        Iterator it = c.getAsignatura().getCorrelatividades().entrySet().iterator();
        while (it.hasNext() && rta==true) {
            Map.Entry m = (Map.Entry) it.next();
            asignaturaId = (String) m.getKey();
            if(!a.getHistoria().containsKey(asignaturaId)) {
                rta = false;
            }
        }
        return rta;
    }
    
    public void agregarProfesorEnCursada(Profesor p, Cursada c) throws ProfesorRegistradoEnCursadaException,ProfesorInhabilitadoException,ProfesorOcupadoParaCursadaException {
        if(c.getProfesores().containsKey(p.getLegajo())) {
            throw new ProfesorRegistradoEnCursadaException(p,c);
        }
        else{
            if(!profesorHabilitadoParaCursada(p, c)) {
                throw new ProfesorInhabilitadoException(p, c);
            }
            else{
                if(this.profesorOcupadoParaCursada(p, c)) {
                    throw new ProfesorOcupadoParaCursadaException(p,c);
                }
                else {
                    c.agregarProfesor(p);
                }
            }
        }
    }
    
    private boolean profesorOcupadoParaCursada(Profesor p, Cursada c) {
        ArrayList<Cursada>cursadasProfesor=this.cursadasDeProfesor(p);
        if(cursadasProfesor.size()>0) {
            Iterator itCursadas=cursadasProfesor.iterator();
            Iterator itHorarioCursada=c.getHorario().iterator();
            Iterator itHorariosProfesor;
            Fecha horarioProfesor,horarioCursada;
            Cursada cAux;
            while(itHorarioCursada.hasNext()) {
                horarioCursada=(Fecha)itHorarioCursada.next();
                while(itCursadas.hasNext()) {
                    cAux=(Cursada)itCursadas.next();
                    itHorariosProfesor=cAux.getHorario().iterator();
                    while(itHorariosProfesor.hasNext()) {
                        horarioProfesor=(Fecha)itHorariosProfesor.next();
                        if(horarioCursada.superpone(horarioProfesor)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean profesorHabilitadoParaCursada(Profesor p, Cursada c) {
        boolean rta=true;
        if(p.getCompetencia().get(c.getAsignatura().getId())==null) {
            rta=false;
        }
        return rta;
    }
    
    public ArrayList<Cursada> cursadasDeProfesor(Profesor p) {
        ArrayList<Cursada> cursadasReturn=new ArrayList<Cursada>();
        Iterator it = this.getCursadas().entrySet().iterator();
        Cursada c;
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            if (c.contieneProfesor(p)) {
                cursadasReturn.add(c);
            }
        }
        return cursadasReturn;
    }
    
    //Alumno-Profesor
    
    public void modificarAlumno(Alumno a, String apellido, String nombre, String domicilio, String mail) {
        a.modificarAlumno(apellido, nombre, domicilio, mail);
    }
    
    //Ver si es necesaria esa excepcion.
    public void aprobarAlumnoAsignatura(Alumno alumno, Asignatura asignatura) throws AsignaturaAprobadaYaRegistradaException{
        if(alumno.getHistoria().containsKey(asignatura.getId())) {
            throw new AsignaturaAprobadaYaRegistradaException(alumno,asignatura);
            //Ya se encuentra aprobada esa asignatura
        }
        else{
            alumno.aprobarAsignatura(asignatura);
        }
    }
    
    /**
     * pre: la asignatura se encuentra dentro de las materias aprobadas por alumno.
     * @param alumno
     * @param asignatura
     */
    public void eliminarAlumnoAsignatura(Alumno alumno, Asignatura asignatura) {
        alumno.eliminarAsignatura(asignatura);
    }

    
    public void modificarProfesor(Profesor p,String apellido, String nombre, String domicilio, String mail) {
        p.modificarProfesor(apellido, nombre, domicilio, mail);
    }
    
    /**
     * pre: Se considera que la asignatura es una existente.
     * @param p
     * @param a
     */
    public void agregarCompetenciaProfesor(Profesor p, Asignatura a) throws AsignaturaYaRegistradaEnProfesorException {
        if(p.getCompetencia().containsKey(a.getId())) {
            throw new AsignaturaYaRegistradaEnProfesorException(a,p);
        }
        else{
            p.agregarCompetencia(a);
        }
    }
    
    public void eliminarCompetenciaProfesor(Profesor p, Asignatura a) {
        p.eliminarCompetencia(a);
    }
}