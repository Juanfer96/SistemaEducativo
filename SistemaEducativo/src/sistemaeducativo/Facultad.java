package sistemaeducativo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Facultad {
    public  int PROXLEGAJOALUM;
    public  int PROXLEGAJOPROF;
    public  int PROXIDCURSADA;
    public  int PROXIDASIGNATURA;
    private static Facultad instancia = null;
    private TreeMap<String, Alumno> alumnos = new TreeMap<>();
    private TreeMap<String, Profesor> profesores = new TreeMap<>();
    private TreeMap<String, Asignatura> asignaturas = new TreeMap<>();
    private TreeMap<String, Cursada> cursadas = new TreeMap<>();

    /**
     * Se inicializan los contadores de legajos en 0
     */
    public Facultad() {
        super();
        PROXLEGAJOALUM = 0;
        PROXLEGAJOPROF = 0;
        PROXIDASIGNATURA = 0;
        PROXIDCURSADA = 0;
    }


    /**Se aplica el patron singleton
     * @return retorna una instancia de facultad
     */
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


    public  void setPROXLEGAJOALUM(int PROXLEGAJOALUM) {
        PROXLEGAJOALUM = PROXLEGAJOALUM;
    }

    public  void setPROXLEGAJOPROF(int PROXLEGAJOPROF) {
        PROXLEGAJOPROF = PROXLEGAJOPROF;
    }

    public  void setPROXIDCURSADA(int PROXIDCURSADA) {
        PROXIDCURSADA = PROXIDCURSADA;
    }

    public  void setPROXIDASIGNATURA(int PROXIDASIGNATURA) {
        PROXIDASIGNATURA = PROXIDASIGNATURA;
    }


    public  int getPROXLEGAJOALUM() {
        return PROXLEGAJOALUM;
    }

    public  int getPROXLEGAJOPROF() {
        return PROXLEGAJOPROF;
    }

    public  int getPROXIDCURSADA() {
        return PROXIDCURSADA;
    }

    public  int getPROXIDASIGNATURA() {
        return PROXIDASIGNATURA;
    }

    //Agregar entidades a las colecciones

    /**
     * @param apellido apellido!=null && apellido!=""
     * @param nombre nombre!=null && nombre!=""
     * @param domicilio domicilio!=null && domicilio !=""
     * @param mail :se espera un mail valido , se verifica la validez en la interfaz, mail!=null && mail!=""
     * @return : se autoincrementa el legajo y se crea un nuevo alumno , y se agrega a la coleccion alumnos.Retorna el alumno agregado
     */
    public Alumno agregarAlumno(String apellido, String nombre, String domicilio, String mail) {
        String s = String.format("%04d", ++PROXLEGAJOALUM);
        String leg = "ALU" + s;
        Alumno a = new Alumno(leg, apellido, nombre, domicilio, mail);
        this.getAlumnos().put(a.getLegajo(), a);
        return a;
    }
    /**
     * @param apellido apellido!=null && apellido!=""
     * @param nombre nombre!=null && nombre!=""
     * @param domicilio domicilio!=null && domicilio !=""
     * @param mail :se espera un mail valido , se verifica la validez en la interfaz, mail!=null && mail!=""
     * @param telefono : telefono!=null && telefono!=""
     * @return : se autoincrementa el legajo y se crea un nuevo profesor , y se agrega a la coleccion profesores.Retorna el profesor agregado
     */
    public Profesor agregarProfesor(String apellido, String nombre, String domicilio, String mail,String telefono) {
        String s = String.format("%04d", ++PROXLEGAJOPROF);
        String leg = "PRO" + s;
        Profesor a = new Profesor(leg, apellido, nombre, domicilio, mail,telefono);
        this.getProfesores().put(a.getLegajo(), a);
        return a;
    }

    /**
     * @param nombre nombre!=null && nombre!=""
     * @return se autoincrementa el legajo y se crea una nueva asignatura , y se agrega a la coleccion asignaturas.Retorna la asignatura agregada
     */
    public Asignatura agregarAsignatura(String nombre) {
        String s = String.format("%04d", ++PROXIDASIGNATURA);
        String id = "ASI" + s;
        Asignatura a = new Asignatura(id, nombre);
        this.getAsignaturas().put(a.getId(), a);
        return a;
    }

    /**
     * @param asignatura asignatura!=null y asignatura existente en la coleccion asignaturas 
     * @param periodo periodo valido , periodo!=null && periodo !=""
     * @return se autoincrementa el legajo y se crea una nueva cursada , y se agrega a la coleccioncursadas.Retorna la cursada agregada
     */
    public Cursada agregarCursada(Asignatura asignatura,  String periodo) {
        String s = String.format("%04d", ++PROXIDCURSADA);
        String id = "CUR" + s;
        Cursada a = new Cursada(id, asignatura, periodo);
        this.getCursadas().put(a.getId(), a);
        return a;
    }
    
    //Bajas de las entidades
    
    /**
     * @param cursada Se considera que cursada pertenece a una cursada existente.
     * post: la cursada se elimina de la coleccion de cursadas
     */
    public void bajaCursada(Cursada cursada) {
        this.getCursadas().remove(cursada.getId());
    }


    /**
     * El metodo ademas de dar de baja en la coleccion correspondiente la asignatura, da de baja a las cursadas de esa asignatura, se elimina dicha asignatura de las correlativas en las que se encuentre y de la competencia de los profesores
     * @param a la asignatura existe , a!=null 
     */
    public void bajaAsignatura(Asignatura a) {
        ArrayList<Cursada> cursadas=this.cursadasDeAsignatura(a);
        Iterator it=cursadas.iterator();
        Cursada c;
        while(it.hasNext()) {
            c=(Cursada)it.next();
            this.bajaCursada(c);
        }
        Iterator it1 = this.getProfesores()
                          .values()
                          .iterator();
        Profesor p;
        while(it1.hasNext())
        {
            p=(Profesor)it1.next();
            p.getCompetencia().remove(a.getId());
        }
        Iterator it2 = this.getAsignaturas()
                          .values()
                          .iterator();
        Asignatura asig;
        while(it2.hasNext())
        {
            asig=(Asignatura)it2.next();
            asig.getCorrelatividades().remove(a.getId());
        }
        this.getAsignaturas().remove(a.getId());
    }
    
    /**
     * @param a la asignatura debe existir , a!=null
     * @return El metodo devuelve en un ArrayList todas las cursadas de la asignatura pasada como parametro
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
     *  pre: se considera que el legajo es de un alumno existente
     * @param legajo legajo!=null && legajo!=""
     * post: el alumno se elimina de todas las cursadas y de la coleccion de alumnos
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
    /**
     *  pre: se considera que el legajo es de un profesor existente
     * @param legajo legajo!=null && legajo!=""
     * post: el profesor se elimina de todas las cursadas y de la coleccion de profesores
     */
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

    /**
     * @param nombre nombre!=null && nombre!=""
     * @param apellido apellido!=null & apellido!=""
     * @return Retorna un arraylist de alumnos que tengan ese nombre y ese apellido , buscando en la coleccion de alumnos
     */
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
    /**
     * @param nombre nombre!=null && nombre!=""
     * @param apellido apellido!=null & apellido!=""
     * @return Retorna un arraylist de profesores que tengan ese nombre y ese apellido , buscando en la coleccion de profesores
     */
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

    /**
     * @param asignatura asignatura!=null && asignautra!=""
     * @return retorna un arraylist de cursadas con el mismo nombre de la asignatura pasada como parametro, buscando en la coleccion de cursadas
     */
    public ArrayList<Cursada> buscarCursadaPorNombre(String asignatura) {
        ArrayList<Cursada> cursadasReturn = new ArrayList<>();
        Cursada c;
        Iterator it = this.getCursadas()
                          .entrySet()
                          .iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            c = (Cursada) m.getValue();
            if (c.getAsignatura().getNombre().equals(asignatura)) {
                cursadasReturn.add(c);
            }
        }
        return cursadasReturn;
    }
    
    /**
     *
     * @param c c!=null c debe existir en la coleccion de cursadas
     * @return un ArrayList de Fechas (horarios) que posee la cursada c
     */
    public ArrayList<Fecha> buscarHorariosDeCursada(Cursada c) {
        return c.getHorario();
    }
    
    /**
     *
     * @param c c!=null c debe existir en la coleccion de cursadas
     * @param f f!=null la fecha debe estar registrada en la coleccion de horarios de la cursada
     * post: se procede a eliminar la fecha f de la cursada c
     */
    public void eliminarHorarioCursada(Cursada c, Fecha f) {
        c.eliminarHorario(f);
    }
    
    /**
     *
     * @param c c!=null c debe existir en la coleccion de cursadas
     * @param a a!=null a debe existir en la coleccion de alumnos
     * post: Se procede a eliminar el alumno a de la cursada c si es que se encuentra inscripto en la misma,
     * en caso contrario no se realiza ninguna accion.
     */
    public void eliminarAlumnoCursada(Cursada c, Alumno a) {
        c.eliminarAlumno(a.getLegajo());
    }
    
    /**
     *
     * @param c c!=null c debe existir en la colecciond e cursadas
     * @param p p!=null p debe existir en la coleccion de profesores
     * post: Se procede a eliminar el profesor p de la cursada c si es que se encuentra inscripto en la misma,
     * en caso contrario no se realiza ninguna accion.
     */
    public void eliminarProfesorCursada(Cursada c, Profesor p) {
        c.eliminarProfesor(p.getLegajo());
    }
    /**
     * @param asignatura asignatura!=null && asignautra!=""
     * @return retorna un arraylist de asignaturas con el mismo nombre de la asignatura pasada como parametro, buscando en la coleccion de asignaturas
     */
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

    /**
     * @param a el alumno debe existir , a!=null
     * @param c la cursada debe exitir, c!=null
     * @throws AlumnoRegistradoEnCursadaException
     * @throws AlumnoInhabilitadoException
     * @throws AlumnoOcupadoParaCursadaException
     * post: Si alumno ya esta registrado en la cursada se lanza AlumnoRegistradoEnCursadaException,
     * Si el alumno esta inhabilitado, es decir que no tiene las correlativas,se lanza AlumnoInhabilitadoException,
     * Si el alumno no tiene disponibilidad horario se lanza AlumnoOcupadoException,
     * De no cumplirse ninguna de estas el alumno es agregado a la cursada c
     */
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

    /**
     * @param a el alumno debe existir, a!=null
     * @return retorna un Arraylist de cursadas donde el alumno a se encuentra inscripto
     */
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

    /**
     * @param a el alumno debe existir , a!=null
     * @param c la cursada debe existir, c!=null
     * @return retorna true si el alumno tiene disponibilidad horario para cursar la cursada c,false en caso contrario
     */
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
                        if(c.getPeriodo().equals(cAux.getPeriodo()) && horarioCursada.superpone(horarioAlumno)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param a alumno debe existir ,a !=null
     * @param c cursada debe existir, c!=null
     * @return Retorna true si el alumno tiene las asignaturas aprobadas para cursar la cursada c,false en caso contrario
     */
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

    /**
     * @param p el profesor debe existir, p!=null
     * @param c la cursada debe existir, c!=null
     * @throws ProfesorRegistradoEnCursadaException
     * @throws ProfesorInhabilitadoException
     * @throws ProfesorOcupadoParaCursadaException
     * post: si el profesor ya se encuentra registrado en esa cursada se lanza ProfesorRegistradoEnCursadaException,
     * Si el profesor no tiene la asignatura de la cursada c en sus competencia se lanza ProfesorInhabilitadoExecption,
     * Si el profesor no tiene disponibilidad horario se lanza ProfesorOcupadoException
     * Si no se cumple ninguna de estas el profesor se agrega a la coleccion de profesores de la cursada
     */
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
    /**
     * @param p el profesor debe existir , p!=null
     * @param c la cursada debe existir, c!=null
     * @return retorna true si el profesor tiene disponibilidad horario para cursar la cursada c,false en caso contrario
     */
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
                        if(c.getPeriodo().equals(cAux.getPeriodo()) && horarioCursada.superpone(horarioProfesor)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * @param p profesor debe existir , p!=null
     * @param c cursada debe existir, c!=null
     * @return Retorna true si el profesor tiene la asignatura de la cursada c entre sus competencias,false en caso contrario
     */
    public boolean profesorHabilitadoParaCursada(Profesor p, Cursada c) {
        boolean rta=true;
        if(p.getCompetencia().get(c.getAsignatura().getId())==null) {
            rta=false;
        }
        return rta;
    }
    
    /**
     * 
     * @param c c!=null. c debe existir en la coleccion de cursadas
     * @param horaInicio debe ser una hora valida, es decir un entero entre 0 y 23
     * @param minInicio debe ser un minuto valido, es decir, un entero entre 0 y 59
     * @param horaFin debe ser una hora valida, es decir un entero entre 0 y 23
     * @param minFin debe ser un minuto valido, es decir, un entero entre 0 y 59
     * @param dia debe ser un dia valido, es decir un entero entre 0 y 6, especificados como constantes de clase en la clase Fecha
     * @return
     * @throws HorarioCursadaSuperpuestaException
     * @throws HorarioCursadaInvalidoException
     * post: si El horario de fin es menor o igual al horario de inicio se lanza HorarioCursadaInvalidoException,
     * si la cursada c ya tiene un horario que se superpone con el horario a agregar se lanza HorarioCursadaSuperpuestaException
     * en caso contrario se agrega el horario con el formato horainicio:mininicio,  horafin:minfin a la cursada c
     */
    public Fecha agregarHorarioCursada(Cursada c, int horaInicio, int minInicio, int horaFin, int minFin, int dia) throws HorarioCursadaSuperpuestaException, HorarioCursadaInvalidoException {
        return c.agregarHorario(dia, horaInicio, minInicio, horaFin, minFin);
    }

    /**
     * @param p el profesor debe existir , p!=null
     * @return retorna un arraylist de cursadas donde el profesor se encuentra registrado
     */
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
    
    //Se llama al metodo agregarCorrelatividad de la clase asignatura.Contrato del metodo especificado en la clase Asignatura agregarCorrelatividad
    /**
     * 
     * @param a1 a1!=null, a1 debe existir en la coleccion de asignaturas
     * @param a2 a2!=null, a2 debe existir en la coleccion de asignaturas
     * @throws CorrelativaRegistradaException
     * post: Si la asignatura a2 ya se encuentra registrada en la coleccion de correlativas de la asignatura a1, se lanza CorrelativaRegistradaException,
     * en caso contrario se procede a agregar la asignatura a2 en la coleccion de correlativas de la asignatura a1.
     */
    public void agregarCorrelativaAsignatura(Asignatura a1,Asignatura a2) throws CorrelativaRegistradaException
    {
        a1.agregarCorrelatividad(a2);
    }
    
    //Se llama al metodo eliminarCorrelatividad de la clase Asignatura.Contrato del metodo en la clase Asignatura eliminarCorrelatividad
    /**
     * 
     * @param a1 a1!=null, a1 debe existir en la coleccion de asignaturas
     * @param a2 a2!=null, a2 debe existir en la coleccion de asignaturas
     * post: Si la asignatura a2 pertenece a una asignatura registrada entre las correlativas de la asignatura a1, se elimina a2 de las correlativas de a1
     * En caso contrario no se produce ningun efecto.
     */
    public void eliminarCorrelativaAsignatura(Asignatura a1,Asignatura a2)
    {
        a1.eliminarCorrelatividad(a2.getId());
    }
    //Alumno-Profesor
    
    /**
     * pre: el alumno a debe existir + idem requisitos para dar de alta un alumno.
     * @param a
     * @param apellido
     * @param nombre
     * @param domicilio
     * @param mail
     */
    public void modificarAlumno(Alumno a, String apellido, String nombre, String domicilio, String mail) {
        a.modificarAlumno(apellido, nombre, domicilio, mail);
    }

    /**
     * @param alumno alumno debe existir, alumno!=null y el alumno debe estar cursando esa asignatura
     * @param asignatura asignatura debe existir ,asignatura !=null
     * @throws AsignaturaAprobadaYaRegistradaException
     * post: Si en la historia del alumno ya se encuentra esa Asignatura lanza AsignaturaAprobadaYaRegistradaException,
     * Si no se llama al metodo aprobarAsignatura de alumno en donde se pone la asignatura en la historia del alumno
     */
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
     * @param alumno debe existir, alumno!=null
     * @param asignatura debe exisit, asignatura!=null
     * post: se elimina la asignatura de la historia del alumno 
     */
    public void eliminarAlumnoAsignatura(Alumno alumno, Asignatura asignatura) {
        alumno.eliminarAsignatura(asignatura);
    }


    /**
     * pre: El profesor p debe existir + mismos req que el alta de profesor
     * @param p debe existir, p!=null
     * @param apellido
     * @param nombre
     * @param domicilio
     * @param mail
     * @param telefono
     */
    public void modificarProfesor(Profesor p,String apellido, String nombre, String domicilio, String mail,String telefono) {
        p.modificarProfesor(apellido, nombre, domicilio, mail,telefono);
    }


    /** 
     * @param p p debe existir, p!=null
     * @param a a debe existir, a!=null
     * @throws AsignaturaYaRegistradaEnProfesorException
     * post: Si la asignatura ya se encuentra registrada en la coleccion de competencias del profesor se lanza AsignaturaYaRegistradaEnProfesorException,
     * en caso contrario la asignatura se agrega a la coleccion de competencias del profesor
     */
    public void agregarCompetenciaProfesor(Profesor p, Asignatura a) throws AsignaturaYaRegistradaEnProfesorException {
        if(p.getCompetencia().containsKey(a.getId())) {
            throw new AsignaturaYaRegistradaEnProfesorException(a,p);
        }
        else{
            p.agregarCompetencia(a);
        }
    }
    
    /**
     * pre: La asignatura a debe encontrarse en la coleccion de competencias del profesor p 
     * @param p p debe existir, p!=null
     * @param a a debe existir, a!=null
     * post: Se elimina la asignatura a de la coleccion de competencias del profesor
     */
    public void eliminarCompetenciaProfesor(Profesor p, Asignatura a) {
        p.eliminarCompetencia(a);
    }
}
