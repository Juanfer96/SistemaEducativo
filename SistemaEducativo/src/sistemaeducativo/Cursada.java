package sistemaeducativo;

import java.util.HashSet;
import java.util.Hashtable;

public class Cursada
{
    private String id;
    private Asignatura asignatura;
    private String dia, hora, periodo;
    private Hashtable<String,Profesor> profesores=new Hashtable<String, Profesor>();
    private Hashtable<String,Alumno> alumnos=new Hashtable<String,Alumno>();
    
    public Cursada()
    {
        super();
    }


    public Cursada(String id, Asignatura asignatura, String dia, String hora, String periodo)
    {
        this.id = id;
        this.asignatura = asignatura;
        this.dia = dia;
        this.hora = hora;
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

    public void setDia(String dia)
    {
        this.dia = dia;
    }

    public String getDia()
    {
        return dia;
    }

    public void setHora(String hora)
    {
        this.hora = hora;
    }

    public String getHora()
    {
        return hora;
    }

    public void setPeriodo(String periodo)
    {
        this.periodo = periodo;
    }

    public String getPeriodo()
    {
        return periodo;
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
    
    public void agregarAlumno(Alumno a)
    {
        if(this.getAlumnos().containsKey(a.getLegajo()))
        {
            //Exception: alumno a ya se encuentra registrado.
        }
        else
        {
            this.getAlumnos().put(a.getLegajo(), a);
        }
    }
    
    public void agregarProfesor(Profesor p)
    {
        if(this.getProfesores().containsKey(p.getLegajo()))
        {
            //Exception: alumno a ya se encuentra registrado.
        }
        else
        {
            this.getProfesores().put(p.getLegajo(), p);
        }
    }
    
    //Ver como implementar.
    public Object eliminarAlumno(String legajo)
    {
        /*if(this.getAlumnos().remove(legajo)==null)
        {
            
            //excepcion de que quiero eliminar una alumno q no existe(no econtro la clave)
        }*/
        return this.getProfesores().remove(legajo);
    }
    
    //Ver como implementar
    public Object eliminarProfesor(String legajo)
    {
        /*if(this.getProfesores().remove(legajo)==null)
        {
            //excepcion de que quiero eliminar una correlatividad q no existe(no econtro la clave)
        }*/
        return this.getProfesores().remove(legajo);
    }
}
