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
    
    public void agregarAlumno(Alumno a) throws AlumnoInhabilitadoException, AlumnoRegistradoEnCursadaException {
        if(this.getAlumnos().containsKey(a.getLegajo()))
        {
            throw new AlumnoRegistradoEnCursadaException(a,this);
            //Exception: alumno a ya se encuentra registrado en la cursada.
        }
        else
        {
            if(alumnoHabilitadoParaCursada(a))
            {
                this.getAlumnos().put(a.getLegajo(), a);   
            }
            else {
                throw new AlumnoInhabilitadoException(a, this);
                //Exception para alumno que no puede anotarse a la cursada por no tener las correlativas necesarias.
            }
        }
    }
    
    public boolean alumnoHabilitadoParaCursada(Alumno a) {
        boolean rta=true;
        String asignaturaId;
        Iterator it = this.getAsignatura().getCorrelatividades().entrySet().iterator();
        while (it.hasNext() && rta==true) {
            Map.Entry m = (Map.Entry) it.next();
            asignaturaId = (String) m.getKey();
            if(!a.getHistoria().containsKey(asignaturaId)) {
                rta = false;
            }
        }
        return rta;
    }
    
    public void agregarProfesor(Profesor p) throws ProfesorRegistradoEnCursadaException, ProfesorInhabilitadoParaCursadaException {
        if(this.getProfesores().containsKey(p.getLegajo()))
        {
            throw new ProfesorRegistradoEnCursadaException(p,this);
        }
        else
        {
            if(!profesorHabilitadoParaCursada(p)) {
                throw new ProfesorInhabilitadoParaCursadaException(p,this);
            }
            else{
                this.getProfesores().put(p.getLegajo(), p);   
            }
        }
    }
    
    public boolean profesorHabilitadoParaCursada(Profesor p) {
        boolean rta=true;
        if(p.getCompetencia().get(this.getAsignatura().getId())==null) {
            rta=false;
        }
        return rta;
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
    
    
    /**
     * pre: Se considera que los datos ingresados son horas validas (segun su formato).
     * post: Se agrega el horario ingresado al arrayList de horarios en caso de que no se superponga con otro horario.
     * @param dia
     * @param horaInicio
     * @param minInicio
     * @param horaFin
     * @param minFin
     */
    
    public void agregarHorario(int dia, int horaInicio, int minInicio, int horaFin, int minFin) {
        if(this.getHorario().isEmpty()) {
            //No habra cursadas superpuestas de esa asignatura, ya que será la primera que se inserta.
            Fecha f= new Fecha(dia,horaInicio,minInicio,horaFin,minFin);
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
                        
                    }
                }
            }
        }
    }
}
