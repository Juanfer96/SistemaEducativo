package sistemaeducativo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Esta clase es la encargada de comunicar el modelo con la interfaz.
 * Es responsable de asignar el legajo/id a las diferentes entidades instanciadas.
 */
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
     * @param mail mail!=null && mail!=""
     * @return : Retorna el alumno agregado
     * post: Si el mail ingresado posee un formato valido, se autoincrementa el legajo, se instancia un objeto de la clase alumno , y se agrega a la coleccion alumnos,
     * en caso contrario se lanza MailInvalidoException.
     */
    public Alumno agregarAlumno(String apellido, String nombre, String domicilio, String mail) throws MailInvalidoException{
        this.verificarMail(mail);
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
     * @param mail mail!=null && mail!=""
     * @param telefono : telefono!=null && telefono!=""
     * @return : Retorna la instancia de Profesor.
     * post: Si el mail ingresado posee un formato valido, se autoincrementa el legajo ,se instancia un objeto de la clase profesor , y se agrega a la coleccion profesores.
     * en caso contrario se lanza MailInvalidoException.,
     */
    public Profesor agregarProfesor(String apellido, String nombre, String domicilio, String mail,String telefono) throws MailInvalidoException {
        this.verificarMail(mail);
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
     * @param asignatura asignatura!=null
     * @param periodo periodo valido , periodo!=null && periodo !=""
     * @return Retorna la cursada agregada
     * post: si la asignatura se encuentra registrada en la coleccion de asignaturas y el periodo tiene un formato valido, se autoincrementa el legajo, se crea una nueva cursada para dicha asignatura
     * y se agrega a la coleccioncursadas.
     * Si el periodo es invalido se lanza PeriodoInvalidoException
     * Si la asignatura no se encuentra registrada en la coleccion de asignaturas se lanza NoExisteEntidadException
     */
    public Cursada agregarCursada(Asignatura asignatura,  String periodo) throws PeriodoInvalidoException,
                                                                                NoExisteEntidadException {
        if(!this.asignaturas.containsKey(asignatura.getId())){
            throw new NoExisteEntidadException(asignatura);
        }
        this.verificaCursadaPeriodo(periodo);
        String s = String.format("%04d", ++PROXIDCURSADA);
        String id = "CUR" + s;
        Cursada a = new Cursada(id, asignatura, periodo);
        this.getCursadas().put(a.getId(), a);
        return a;
    }

    /**
     * 
     * @param c !=null
     * @param asignatura 
     * @param periodo
     * @throws PeriodoInvalidoException
     * post: Si la asignatura no se encuentra registrada en la coleccion de asignaturas o la cursada no se encuentra en la coleccion de cursadas, se lanza NoExisteEntidadException,
     * si el periodo recibido como parametro tiene un formato invalido se lanza PeriodoInvalidoException,
     * en caso contrario se procede a modificar la cursada c
     */
    public void modificarCursada(Cursada c, Asignatura asignatura,  String periodo) throws PeriodoInvalidoException,
                                                                                          NoExisteEntidadException {
        if(!this.asignaturas.containsKey(asignatura.getId()))
            throw new NoExisteEntidadException(asignatura);
        if(!this.cursadas.containsKey(c.getId()))
            throw new NoExisteEntidadException(c);
        this.verificaCursadaPeriodo(periodo);
        c.modificar(asignatura, periodo);
    }
    
    
    //Bajas de las entidades
    
    /**
     * @param cursada Se considera que cursada pertenece a una cursada existente.
     * post: Si la cursada no se encuentra registrada en la coleccion de cursadas se lanzaNoExisteEntidadException,
     * en caso contrario, la cursada se elimina de la coleccion de cursadas
     */
    public void bajaCursada(Cursada cursada) throws NoExisteEntidadException {
        if(!this.cursadas.containsKey(cursada.getId()))
            throw new NoExisteEntidadException(cursada);
        this.getCursadas().remove(cursada.getId());
    }


    /**
     * 
     * @param a la asignatura existe , a!=null 
     * post: Si la asignatura a no se encuentra registrada en la coleccion de asignaturas se lanza NoExisteEntidadException,
     * en caso contrario, ademas de dar de baja en la coleccion correspondiente la asignatura, da de baja a las cursadas de esa asignatura, 
     * se elimina dicha asignatura de las correlativas en las que se encuentre y de la competencia de los profesores.
     * Tener en cuenta de que en caso de que algun alumno posea dicha asignatura como aprobada en su historia academica, se tomo la decision de que la conserve.
     */
    public void bajaAsignatura(Asignatura a) throws NoExisteEntidadException {
        if(!this.asignaturas.containsKey(a.getId())){
            throw new NoExisteEntidadException(a);
        }
        ArrayList<Cursada> cursadas=this.cursadasDeAsignatura(a);
        Iterator it=cursadas.iterator();
        Cursada c;
        while(it.hasNext()) {
            c=(Cursada)it.next();
            try {
                this.bajaCursada(c);
            } catch (NoExisteEntidadException e) {
            }
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
     * @param a a!=null
     * @return El metodo devuelve en un ArrayList todas las cursadas de la asignatura pasada como parametro
     * post: Si la asignatura a no se encuentra registrada en la coleccion de asignaturas se lanza NoExisteEntidadException,
     * en caso contrario se devuelve un arrayList con las cursadas de la asignatura pasada como parametro. En caso de que no tenga ninguna cursada activa,
     * el arrayList devuelto estara vacio.
     */
    private ArrayList<Cursada> cursadasDeAsignatura(Asignatura a) throws NoExisteEntidadException {
        if(!this.asignaturas.containsKey(a.getId())){
            throw new NoExisteEntidadException(a);
        }
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
     * @param legajo legajo!=null && legajo!=""
     * post: Si el legajo pasado como parametro no pertenece a un alumno contenido en la coleccion de alumnos,
     * se lanza NoExisteEntidadException,
     * en caso contrario, el alumno cuyo legajo es el pasado como parametro, se elimina de todas las cursadas y de la coleccion de alumnos
     */
    public void bajaAlumno(String legajo) throws NoExisteEntidadException {
        if(!this.alumnos.containsKey(legajo)){
            throw new NoExisteEntidadException(legajo);
        }
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
     * post: Si el legajo pasado como parametro no pertenece a un profesor contenido en la coleccion de profesores,
     * se lanza NoExisteEntidadException,
     * el profesor se elimina de todas las cursadas y de la coleccion de profesores
     */
    public void bajaProfesor(String legajo) throws NoExisteEntidadException {
        if(!this.profesores.containsKey(legajo)){
            throw new NoExisteEntidadException(legajo);
        }
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
     * @return Retorna un arraylist de alumnos que tengan ese nombre y ese apellido , buscando en la coleccion de alumnos.
     * En caso de que no se encuentre ningun alumno con dichos atributos, se devuelve un arrayList vacio.
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
     * En caso de que no se encuentre ningun profesor con dichos atributos, se devuelve un arrayList vacio.
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
     * En caso de que no se encuentre ninguna cursada con dicho atributo, se devuelve un arrayList vacio.
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
     * @param c c!=null 
     * @return un ArrayList de Fechas (horarios) que posee la cursada c
     * post: En caso de que la cursada c no se encuentre registrada en la coleccion de cursadas, se lanza NoExisteEntidadException,
     * en caso contrario se retorna el arrayList de horarios de la cursada.
     */
    public ArrayList<Fecha> buscarHorariosDeCursada(Cursada c) throws NoExisteEntidadException {
        if(!this.cursadas.containsKey(c.getId())){
            throw new NoExisteEntidadException(c);
        }
        return c.getHorario();
    }
    
    /**
     *
     * @param c c!=null c
     * @param f f!=null
     * post: En caso de que la cursada no se encuentre registrada en la coleccion de cursadas,
     * o que la fecha f no se encuentre registrada en la coleccion de fechas de la cursada c, se lanza NoExisteEntidadException,
     * en caso contrario se procede a eliminar la fecha f de la cursada c
     */
    public void eliminarHorarioCursada(Cursada c, Fecha f) throws NoExisteEntidadException {
        if(!this.cursadas.containsKey(c.getId())){
            throw new NoExisteEntidadException(c);
        }
        if(!c.getHorario().contains(f))
            throw new NoExisteEntidadException(f);
        c.eliminarHorario(f);
    }
    
    /**
     *
     * @param c c!=null c debe existir en la coleccion de cursadas
     * @param a a!=null a debe existir en la coleccion de alumnos
     * post: En caso de que la cursada no se encuentre registrada en la coleccion de cursadas, o
     * que el alumno no se encuentre registrado en la coleccion de alumnos se lanza NoExisteEntidadException.
     * Si no se cumple ninguna de estas situaciones, se procede a eliminar el alumno a de la cursada c, si es que el mismo se encuentra inscripto en la misma,
     * en caso contrario no se realiza ninguna accion.
     */
    public void eliminarAlumnoCursada(Cursada c, Alumno a) throws NoExisteEntidadException {
        if(!this.cursadas.containsKey(c.getId())){
            throw new NoExisteEntidadException(c);
        }
        if(!this.alumnos.containsKey(a.getLegajo()))
            throw new NoExisteEntidadException(a);
        c.eliminarAlumno(a.getLegajo());
    }
    
    /**
     *
     * @param c c!=null c debe existir en la colecciond e cursadas
     * @param p p!=null p debe existir en la coleccion de profesores
     * post: En caso de que la cursada no se encuentre registrada en la coleccion de cursadas, o
     * que el profesor no se encuentre registrado en la coleccion de profesores se lanza NoExisteEntidadException.
     * Si no se cumple ninguna de estas situaciones, se procede a eliminar el profesor p de la cursada c, si es que el mismo se encuentra inscripto en la misma,
     * en caso contrario no se realiza ninguna accion.
     */
    public void eliminarProfesorCursada(Cursada c, Profesor p) throws NoExisteEntidadException {
        if(!this.cursadas.containsKey(c.getId())){
            throw new NoExisteEntidadException(c);
        }
        if(!this.profesores.containsKey(p.getLegajo()))
            throw new NoExisteEntidadException(p);
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
     * post: Si el alumno a o la cursada c no se encuentran registrados en sus respectivas colecciones, se lanza  NoExisteEntidadException,
     * si alumno ya esta registrado en la cursada se lanza AlumnoRegistradoEnCursadaException,
     * Si el alumno esta inhabilitado, es decir que no tiene las correlativas,se lanza AlumnoInhabilitadoException,
     * Si el alumno no tiene disponibilidad horario se lanza AlumnoOcupadoException,
     * De no cumplirse ninguna de estas el alumno es agregado a la cursada c
     */
    public void agregarAlumnoEnCursada(Alumno a, Cursada c) throws AlumnoRegistradoEnCursadaException, AlumnoInhabilitadoException, AlumnoOcupadoParaCursadaException,
                                                                   NoExisteEntidadException {
        if(!this.alumnos.containsKey(a.getLegajo())) {
            throw new NoExisteEntidadException(a);
        }
        if(!this.cursadas.containsKey(c.getId())){
            throw new NoExisteEntidadException(c);
        }
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
     * post: Si el alumno a no se encuentra registrado en la coleccion de alumnos, se lanza NoExisteEntidadException,
     * en caso contrario se devuelve un arrayList con las cursadas actuales del alumno,
     * en caso de que el alumno no posea cursadas, se procede a retornar un arratList vacio.
     */
    public ArrayList<Cursada> cursadasDeAlumno(Alumno a) throws NoExisteEntidadException {
        if(!this.alumnos.containsKey(a.getLegajo())) {
            throw new NoExisteEntidadException(a);
        }
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
     * @param a!=null
     * @param c!=null
     * @return true/false
     * post: Si el alumno a o la cursada c no se encuentran registrados en las colecciones respectivas, se procede a lanzar NoExisteEntidadException,
     * en caso contrario retorna true si el alumno tiene disponibilidad horario para cursar la cursada c, false en caso contrario
     */
    private boolean alumnoOcupadoParaCursada(Alumno a, Cursada c) throws NoExisteEntidadException {
        if(!this.alumnos.containsKey(a.getLegajo())) {
            throw new NoExisteEntidadException(a);
        }
        if(!this.cursadas.containsKey(c.getId())) {
            throw new NoExisteEntidadException(c);
        }
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
     * @param a a !=null
     * @param c c!=null
     * @return true/false
     * post: Si el alumno a o la cursada c no se encuentran registrados en las colecciones respectivas, se procede a lanzar NoExisteEntidadException,
     * sino, retorna true si el alumno tiene las asignaturas aprobadas para cursar la cursada c,false en caso contrario
     */
    private boolean alumnoHabilitadoParaCursada(Alumno a, Cursada c) throws NoExisteEntidadException {
        if(!this.alumnos.containsKey(a.getLegajo())) {
            throw new NoExisteEntidadException(a);
        }
        if(!this.cursadas.containsKey(c.getId())) {
            throw new NoExisteEntidadException(c);
        }
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
     * post: Si el profesor o la cursada no se encuentran registradas en sus respectivas colecciones se procede a lanzar NoExisteEntidadException,
     * si el profesor ya se encuentra registrado en esa cursada se lanza ProfesorRegistradoEnCursadaException,
     * Si el profesor no tiene la asignatura de la cursada c en sus competencia se lanza ProfesorInhabilitadoExecption,
     * Si el profesor no tiene disponibilidad horario se lanza ProfesorOcupadoException
     * Si no se cumple ninguna de estas el profesor se agrega a la coleccion de profesores de la cursada
     */
    public void agregarProfesorEnCursada(Profesor p, Cursada c) throws ProfesorRegistradoEnCursadaException,ProfesorInhabilitadoException,ProfesorOcupadoParaCursadaException,
                                                                       NoExisteEntidadException {
        if(!this.profesores.containsKey(p.getLegajo())) {
            throw new NoExisteEntidadException(p);
        }
        if(!this.cursadas.containsKey(c.getId())) {
            throw new NoExisteEntidadException(c);
        }
        
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
     * @param p p!=null
     * @param c c!=null
     * @return true/false
     * post: Si el profesor o la cursada no se encuentran registradas en sus respectivas colecciones se procede a lanzar NoExisteEntidadException,
     * sino, retorna true si el profesor tiene disponibilidad horario para cursar la cursada c,false en caso contrario
     */
    private boolean profesorOcupadoParaCursada(Profesor p, Cursada c) throws NoExisteEntidadException {
        if(!this.profesores.containsKey(p.getLegajo())) {
            throw new NoExisteEntidadException(p);
        }
        if(!this.cursadas.containsKey(c.getId())) {
            throw new NoExisteEntidadException(c);
        }
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
     * @param p p!=null
     * @param c c!=null
     * @return true/false
     * post: post: Si el profesor o la cursada no se encuentran registradas en sus respectivas colecciones se procede a lanzar NoExisteEntidadException,
     * sino, retorna true si el profesor tiene la asignatura de la cursada c entre sus competencias,false en caso contrario
     */
    public boolean profesorHabilitadoParaCursada(Profesor p, Cursada c) throws NoExisteEntidadException {
        if(!this.profesores.containsKey(p.getLegajo())) {
            throw new NoExisteEntidadException(p);
        }
        if(!this.cursadas.containsKey(c.getId())) {
            throw new NoExisteEntidadException(c);
        }
        boolean rta=true;
        if(p.getCompetencia().get(c.getAsignatura().getId())==null) {
            rta=false;
        }
        return rta;
    }
    
    /**
     * 
     * @param c c!=null. c debe existir en la coleccion de cursadas
     * @param horaInicio
     * @param minInicio 
     * @param horaFin 
     * @param minFin 
     * @param dia: entero que representa un dia de la semana (segun las constantes de clase definidas en la clase Fecha)
     * @return el objeto Fecha creado
     * @throws HorarioCursadaSuperpuestaException
     * @throws HorarioCursadaInvalidoException
     * post: Si la cursada c no se encuentra registrada en la coleccion de cursadas se procede a lanzar  NoExisteEntidadException,
     * si el horario de fin es menor o igual al horario de inicio se lanza HorarioCursadaInvalidoException,
     * si la cursada c ya tiene un horario que se superpone con el horario a agregar se lanza HorarioCursadaSuperpuestaException
     * si horaInicio u horaFin no poseen un formato valido (entero entre 0 y 23) o si minInicio o minFin no poseen un formato valido (entero entre 0 y 59) o si ellanza FormatoHoraException
     * en caso contrario se agrega el horario con el formato horainicio:mininicio,  horafin:minfin a la cursada c
     */
    public Fecha agregarHorarioCursada(Cursada c, int horaInicio, int minInicio, int horaFin, int minFin, int dia) throws HorarioCursadaSuperpuestaException, HorarioCursadaInvalidoException, FormatoHoraException,
                                                       NoExisteEntidadException {
        if(!this.cursadas.containsKey(c.getId())) {
            throw new NoExisteEntidadException(c);
        }
        this.verificarHorario(horaInicio, minInicio, horaFin, minFin, dia);
        return c.agregarHorario(dia, horaInicio, minInicio, horaFin, minFin);
    }

    /**
     * @param p p!=null
     * @return ArrayList de cursadas.
     * post: Si el profesor p no se encuentra registrada en la coleccion de profesores se procede a lanzar  NoExisteEntidadException,
     * en caso contrario, se retorna un arraylist de cursadas donde el profesor se encuentra registrado
     */
    public ArrayList<Cursada> cursadasDeProfesor(Profesor p) throws NoExisteEntidadException {
        if(!this.profesores.containsKey(p.getLegajo())) {
            throw new NoExisteEntidadException(p);
        }
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
     * @param a1 a1!=null
     * @param a2 a2!=null 
     * @throws CorrelativaRegistradaException
     * post: Si la asignatura a2 ya se encuentra registrada en la coleccion de correlativas de la asignatura a1, se lanza CorrelativaRegistradaException,
     * si alguna de las asignaturas no estan registradas en la coleccion de asignaturas se lanza NoExisteEntidadException
     * en caso contrario se procede a agregar la asignatura a2 en la coleccion de correlativas de la asignatura a1.
     */
    public void agregarCorrelativaAsignatura(Asignatura a1,Asignatura a2) throws CorrelativaRegistradaException,
                                                                                  NoExisteEntidadException {
        if(!this.asignaturas.containsKey(a1.getId()))
            throw new NoExisteEntidadException(a1);
        if(!this.asignaturas.containsKey(a2.getId()))
            throw new NoExisteEntidadException(a2);
        a1.agregarCorrelatividad(a2);
    }
    
    //Se llama al metodo eliminarCorrelatividad de la clase Asignatura.Contrato del metodo en la clase Asignatura eliminarCorrelatividad
    /**
     * 
     * @param a1 a1!=null, a1 debe existir en la coleccion de asignaturas
     * @param a2 a2!=null, a2 debe existir en la coleccion de asignaturas
     * post: Si la asignatura a2 pertenece a una asignatura registrada entre las correlativas de la asignatura a1, se elimina a2 de las correlativas de a1
     * en caso de que a1 o a2 no esten registradas en la coleccion de asignaturas se lanza NoExisteEntidadException 
     * en caso de que a2 no pertenezca a una asignatura registrada entre las correlativas de la asignatura a1 no se produce ningun efecto.
     */
    public void eliminarCorrelativaAsignatura(Asignatura a1,Asignatura a2) throws NoExisteEntidadException {
        if(!this.asignaturas.containsKey(a1.getId()))
            throw new NoExisteEntidadException(a1);
        if(!this.asignaturas.containsKey(a2.getId()))
            throw new NoExisteEntidadException(a2);
        a1.eliminarCorrelatividad(a2.getId());
    }
    //Alumno-Profesor
    
    /**
     *  
     * @param a a!=null
     * @param apellido != null && apellido !=""
     * @param nombre != null && nombre !=""
     * @param domicilio != null && domicilio !=""
     * @param mail != null && mail !=""
     * post: si el alumno a  no se encuentra registrado en la coleccion de alumnos de la facultad se lanza NoExisteEntidadException,
     * si el String mail no posee un formato valido se lanza MailInvalidoException,
     * en caso contrario se procede a modificar los atributos del Alumno a segun cada parametro de entrada.
     */
    public void modificarAlumno(Alumno a, String apellido, String nombre, String domicilio, String mail) throws MailInvalidoException, NoExisteEntidadException {
        if(!this.alumnos.containsKey(a.getLegajo())){
            throw new NoExisteEntidadException(a);
        }
        this.verificarMail(mail);
        a.modificarAlumno(apellido, nombre, domicilio, mail);
    }
    //Requerimiento a considerar: un alumno puede aprobar una asignatura si y solo si se encuentra cursandola.
    /**
     * 
     * @param alumno!=null y el alumno debe estar cursando esa asignatura
     * @param asignatura asignatura !=null
     * @throws AsignaturaAprobadaYaRegistradaException
     * post: si el Alumno alumno o la Asignatura asignatura no se encuentran registrados en la coleccion de alumnos o en la coleccion de asignaturas, respectivamente, de la facultad se lanza NoExisteEntidadException,
     * si el Alumno alumno no se encuentra cursando una cursada de la Asignatura asignatura se lanza AsignaturaNoAprobableException,
     * si en la historia del alumno ya se encuentra esa Asignatura lanza AsignaturaAprobadaYaRegistradaException,
     * si no se llama al metodo aprobarAsignatura de alumno en donde se pone la asignatura en la historia del alumno.
     */
    public void aprobarAlumnoAsignatura(Alumno alumno, Asignatura asignatura) throws AsignaturaAprobadaYaRegistradaException,
                                                                      NoExisteEntidadException,
                                                                      AsignaturaNoAprobableException {
        Cursada c=this.verificarAsignaturaAprobable(asignatura, alumno);
        if(!this.alumnos.containsKey(alumno.getLegajo())){
            throw new NoExisteEntidadException(alumno);
        }
        if(!this.asignaturas.containsKey(asignatura.getId())){
            throw new NoExisteEntidadException(asignatura);
        }
        if(alumno.getHistoria().containsKey(asignatura.getId())) {
            throw new AsignaturaAprobadaYaRegistradaException(alumno,asignatura);
            //Ya se encuentra aprobada esa asignatura
        }
        alumno.aprobarAsignatura(asignatura);
        c.eliminarAlumno(alumno.getLegajo());
    }
    
    /**
     * @param asignatura
     * @param alumno
     * @throws AsignaturaNoAprobableException: se lanza en caso de que el alumno no este cursando la asignatura pasada como parametro, impidiendo que el mismo la pueda aprobar.
     * post: Si la asignatura o el alumno no se encuentran registrados en sus respectivas colecciones, se lanza NoExisteEntidadException,
     * El metodo verifica que el Alumno alumno, pueda aprobar la Asignatura asignatura; es decir, que el alumno este cursando una cursada de asignatura.
     */
    public Cursada verificarAsignaturaAprobable(Asignatura asignatura, Alumno alumno) throws AsignaturaNoAprobableException,
                                                                   NoExisteEntidadException {
        if(!this.alumnos.containsKey(alumno.getLegajo())){
            throw new NoExisteEntidadException(alumno);
        }
        if(!this.asignaturas.containsKey(asignatura.getId())){
            throw new NoExisteEntidadException(asignatura);
        }
        Iterator it=this.cursadasDeAlumno(alumno).iterator();
        Cursada c=null;
        boolean rta=false;
        while(it.hasNext() && !rta){
            c=(Cursada)it.next();
            if(c.getAsignatura()==asignatura){
                rta=true;
            }
        }
        if(rta==false) {
            throw new AsignaturaNoAprobableException(asignatura,alumno);
        }
        return c;
    }
    
    /**
     * pre: la asignatura se encuentra dentro de las materias aprobadas por alumno.
     * @param alumno debe existir, alumno!=null
     * @param asignatura debe exisit, asignatura!=null
     * post: Si el alumno no se encuentra registrado en la coleccion de alumnos o la asignatura no se encuentra registrada en la coleccion de asignaturas, se lanza NoExisteEntidadException,
     * en caso contrario, se elimina la asignatura de la historia del alumno 
     */
    public void eliminarAlumnoAsignatura(Alumno alumno, Asignatura asignatura) throws NoExisteEntidadException {
        if(!this.alumnos.containsKey(alumno.getLegajo())){
            throw new NoExisteEntidadException(alumno);
        }
        if(!this.asignaturas.containsKey(asignatura.getId())){
            throw new NoExisteEntidadException(asignatura);
        }
        alumno.eliminarAsignatura(asignatura);
    }


    /**
     * pre: 
     * @param p p!=null
     * @param apellido
     * @param nombre
     * @param domicilio
     * @param mail
     * @param telefono
     * post: si el profesor p no se encuentra registrado en la coleccion de profesores de la facultad se lanza NoExisteEntidadException,
     * si el String mail no posee un formato valido se lanza MailInvalidoException,
     * en caso contrario se procede a modificar los atributos del Profesor p segun cada parametro de entrada.
     */
    public void modificarProfesor(Profesor p,String apellido, String nombre, String domicilio, String mail,String telefono) throws MailInvalidoException, NoExisteEntidadException {
        if(!this.profesores.containsKey(p.getLegajo())){
            throw new NoExisteEntidadException(p);
        }
        this.verificarMail(mail);
        p.modificarProfesor(apellido, nombre, domicilio, mail, telefono);
    }


    /** 
     * @param p p!=null
     * @param a a!=null
     * @throws AsignaturaYaRegistradaEnProfesorException
     * post: Si el profesor o la asignatura no se encuentran registrados en sus respectivas colecciones se procede a lanzar NoExisteEntidadException
     * si la asignatura ya se encuentra registrada en la coleccion de competencias del profesor se lanza AsignaturaYaRegistradaEnProfesorException,
     * en caso contrario la asignatura se agrega a la coleccion de competencias del profesor
     */
    public void agregarCompetenciaProfesor(Profesor p, Asignatura a) throws AsignaturaYaRegistradaEnProfesorException,
                                                                            NoExisteEntidadException {
        if(!this.profesores.containsKey(p.getLegajo())){
            throw new NoExisteEntidadException(p);
        }
        if(!this.asignaturas.containsKey(a.getId())){
            throw new NoExisteEntidadException(a);
        }
        if(p.getCompetencia().containsKey(a.getId())) {
            throw new AsignaturaYaRegistradaEnProfesorException(a,p);
        }
        else{
            p.agregarCompetencia(a);
        }
    }
    
    /**
     * pre: La asignatura a debe encontrarse en la coleccion de competencias del profesor p 
     * @param p p!=null
     * @param a a!=null
     * post: Si el profesor o la asignatura no se encuentran registrados en sus respectivas colecciones se procede a lanzar NoExisteEntidadException,
     * en caso contrario, se elimina la asignatura a de la coleccion de competencias del profesor
     */
    public void eliminarCompetenciaProfesor(Profesor p, Asignatura a) throws NoExisteEntidadException {
        if(!this.profesores.containsKey(p.getLegajo())){
            throw new NoExisteEntidadException(p);
        }
        if(!this.asignaturas.containsKey(a.getId())){
            throw new NoExisteEntidadException(a);
        }
        p.eliminarCompetencia(a);
    }
    
    public void verificarMail(String mail) throws MailInvalidoException{
        if(!(mail.contains("@")&& (mail.indexOf("@") <(mail.length()-1)) && (mail.indexOf("@")>0)))
            throw new MailInvalidoException(mail);
    }
    
    public void verificaCursadaPeriodo(String periodo) throws PeriodoInvalidoException
    {
        boolean ret = false;
        String cad;    
        if(periodo.length() == 7 && periodo.indexOf("-") == 2)
        {
            cad = periodo.substring(0,2);
            if(cad.equals("01") || cad.equals("02"))
            {
                cad = periodo.substring(3);
                ret = cad.compareTo("2017") >= 0 && cad.compareTo("2100") <= 0;
            }
        }
        if (!ret)
            throw new PeriodoInvalidoException(periodo);
    }
    
    public void verificarHorario(int horaInicio, int minInicio, int horaFin, int minFin, int dia) throws FormatoHoraException {
        if(horaInicio<0||horaInicio>23||horaFin<0||horaFin>23||minInicio<0||minInicio>59||minFin<0||minInicio>59||dia<Fecha.LUNES||dia>Fecha.DOMINGO) {
            throw new FormatoHoraException(horaInicio,minInicio,horaFin,minFin);
        }
    }
}
