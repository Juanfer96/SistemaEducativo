package sistemaeducativo;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.time.*;

import java.util.ArrayList;

public class Cursada
{
    private String id;
    private Asignatura asignatura;
    private String periodo;
    private ArrayList<Fecha> horario=new ArrayList<>();
    private Hashtable<String,Profesor> profesores=new Hashtable<String, Profesor>();
    private Hashtable<String,Alumno> alumnos=new Hashtable<String,Alumno>();
    
    public Cursada()
    {
        super();
    }


    public Cursada(String id, Asignatura asignatura, String periodo)
    {
        this.id = id;
        this.asignatura = asignatura;
        this.periodo = periodo;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setAsignatura(Asignatura asignatura)
    {
        this.asignatura = asignatura;
    }

    public Asignatura getAsignatura()
    {
        return asignatura;
    }


    public void setPeriodo(String periodo)
    {
        this.periodo = periodo;
    }

    public String getPeriodo()
    {
        return periodo;
    }


    public void setHorario(ArrayList<Fecha> horario) {
        this.horario = horario;
    }

    public ArrayList<Fecha> getHorario() {
        return horario;
    }

    public void setProfesores(Hashtable<String,Profesor> profesores)
    {
        this.profesores = profesores;
    }

    public Hashtable<String,Profesor> getProfesores()
    {
        return profesores;
    }

    public void setAlumnos(Hashtable<String,Alumno> alumnos)
    {
        this.alumnos = alumnos;
    }

    public Hashtable<String,Alumno> getAlumnos()
    {
        return alumnos;
    }
    
    //Implementar en Facultad las verificaciones: alumnoOcupado, alumnoHabilitado idem profesor.!!
    //Falta implementar si el alumno esta ocupado en ese horario.
    
    /**
     * pre: Se considera que el alumno a inscribirse cumple con todas las restricciones.
     * @param a
     * @throws AlumnoInhabilitadoException
     * @throws AlumnoRegistradoEnCursadaException
     */
    public void agregarAlumno(Alumno a) throws AlumnoInhabilitadoException, AlumnoRegistradoEnCursadaException {
        this.getAlumnos().put(a.getLegajo(), a); 
    }
    
    /**
     * pre: Se considera que el profesor a inscribirse cumple con todas las restricciones.
     * @param p
     * @throws ProfesorRegistradoEnCursadaException
     * @throws ProfesorInhabilitadoException
     */
    public void agregarProfesor(Profesor p) throws ProfesorRegistradoEnCursadaException, ProfesorInhabilitadoException {
        this.getProfesores().put(p.getLegajo(), p);
    }
    
    
    
    //Retorna el legajo del alumno eliminado (si dicho alumno se encuentra en la cursada)
    //Retorna null si no lo encontro (obviamente no lo elimina).
    public Object eliminarAlumno(String legajo)
    {
        /*if(this.getAlumnos().remove(legajo)==null)
        {
            
            //excepcion de que quiero eliminar una alumno q no existe(no econtro la clave)
        }*/
        return this.getAlumnos().remove(legajo);
    }
    
    //Retorna el legajo del profesor eliminado (si dicho alumno se encuentra en la cursada)
    //Retorna null si no lo encontro (obviamente no lo elimina).
    public Object eliminarProfesor(String legajo)
    {
        /*if(this.getProfesores().remove(legajo)==null)
        {
            //excepcion de que quiero eliminar una correlatividad q no existe(no econtro la clave)
        }*/
        return this.getProfesores().remove(legajo);
    }
    
    public void eliminarHorario(Fecha f){
        this.getHorario().remove(f);
    }
    
    
    /**
     * pre: Se considera que los datos ingresados son horas validas (segun su formato).
     * post: Se agrega el horario ingresado al arrayList de horarios en caso de que no se superponga con otro horario.
     * @param dia
     * @param horaInicio
     * @param minInicio
     * @param horaFin
     * @param minFin
     */
    //Considere horario superpuesto solo entre la misma cursada
    public Fecha agregarHorario(int dia, int horaInicio, int minInicio, int horaFin, int minFin) throws HorarioCursadaSuperpuestaException, HorarioCursadaInvalidoException {
        LocalTime auxInicio=LocalTime.of(horaInicio, minInicio);
        LocalTime auxFin=LocalTime.of(horaFin, minFin);
        Fecha f=null;
        if(!auxFin.isAfter(auxInicio))//El horario de fin es menor o igual al horario de inicio
        {
            throw new HorarioCursadaInvalidoException(auxInicio,auxFin);
        }else{
            if(this.getHorario().isEmpty()) {
                //No habra cursadas superpuestas de esa asignatura, ya que será la primera que se inserta.
                f= new Fecha(dia,horaInicio,minInicio,horaFin,minFin);
                this.getHorario().add(f);
            }
            else {
                Fecha fechaEstablecida;
                Fecha fechaEntrada=new Fecha(dia,horaInicio,minInicio,horaFin,minFin);
                Iterator it=this.getHorario().iterator();
                while(it.hasNext()) {
                    fechaEstablecida=(Fecha)it.next();
                    if(fechaEstablecida.getDia()==fechaEntrada.getDia()) {
                        if(fechaEstablecida.superpone(fechaEntrada)) {
                            throw new HorarioCursadaSuperpuestaException(fechaEntrada,this);
                        }
                    }
                }
                f= new Fecha(dia,horaInicio,minInicio,horaFin,minFin);
                this.getHorario().add(f);
            }   
        }
        return f;
    }
    
    public boolean contieneAlumno(Alumno a) {
       if(this.getAlumnos().containsKey(a.getLegajo())) {
           return true;
       }
       return false;
    }
    
    public boolean contieneProfesor(Profesor p) {
        if(this.getProfesores().containsKey(p.getLegajo())) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Asignatura: "+this.getAsignatura().getNombre()+"\tID: "+this.getId()+"\tPeríodo: "+this.getPeriodo();
    }
}
