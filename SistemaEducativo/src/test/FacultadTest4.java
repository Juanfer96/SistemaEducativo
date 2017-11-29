package test;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import sistemaeducativo.Alumno;
import sistemaeducativo.AlumnoInhabilitadoException;
import sistemaeducativo.AlumnoOcupadoParaCursadaException;
import sistemaeducativo.AlumnoRegistradoEnCursadaException;
import sistemaeducativo.Asignatura;
import sistemaeducativo.AsignaturaAprobadaYaRegistradaException;
import sistemaeducativo.AsignaturaNoAprobableException;
import sistemaeducativo.AsignaturaYaRegistradaEnProfesorException;
import sistemaeducativo.Cursada;
import sistemaeducativo.NoExisteEntidadException;
import sistemaeducativo.PeriodoInvalidoException;
import sistemaeducativo.Profesor;
import sistemaeducativo.ProfesorInhabilitadoException;
import sistemaeducativo.ProfesorOcupadoParaCursadaException;
import sistemaeducativo.ProfesorRegistradoEnCursadaException;

public class FacultadTest4
{
    TestFixture4 fixture4=new TestFixture4();
    public FacultadTest4()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        this.fixture4.setUp();
    }

    @After
    public void tearDown() throws Exception
    {
        this.fixture4.tearDown();
    }
    
    //Se testea agregar un Alumno existente en una cursada existente (sin que la curse el alumno) con disponibilidad horaria
    @Test
    public void agregarAlumnoEnCursadaTest1()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Emanuel", "Ponce").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarAlumnoEnCursada(a, c);
            assertTrue("Error al agregar el alumno en la cursada", c.contieneAlumno(a));
        } catch (AlumnoInhabilitadoException e)
        {
            fail("No se deberia lanzar la excepcion");
        } catch (AlumnoOcupadoParaCursadaException e)
        {
            fail("No se deberia lanzar la excepcion");
        } catch (AlumnoRegistradoEnCursadaException e)
        {
            fail("No se deberia lanzar la excepcion");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar la excepcion");
        }
    }
    
    //Se testea agregar un Alumno existente en una cursada existente y que el alumno ya se encuentre cursando
    @Test
    public void agregarAlumnoEnCursadaTest2()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarAlumnoEnCursada(a, c);
            fail("Se debe lanzar AlumnoRegistradoEnCursadaException");
        } catch (AlumnoInhabilitadoException e)
        {
            fail("No se deberia lanzar la excepcion .");
        } catch (AlumnoOcupadoParaCursadaException e)
        {
            fail("No se deberia lanzar la excepcion 0");
        } catch (AlumnoRegistradoEnCursadaException e)
        {
            assertTrue("Error en la excepcion",e.getA().equals(a)&&e.getC().equals(c));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar la excepcion");
        }
    }
    
    //Se testea agregar un Alumno existente en una cursada existente con disponibilidad horaria pero que el alumno no tenga las correlativas necesarias para cursarla la asignatura
    @Test
    public void agregarAlumnoEnCursadaTest3()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Emanuel", "Ponce").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarAlumnoEnCursada(a, c);
            fail("Se debe lanzar AlumnoInhabilitadoException");
        } catch (AlumnoInhabilitadoException e)
        {
            assertTrue("Error en la excepcion",e.getA().equals(a)&&e.getC().equals(c));
        } catch (AlumnoOcupadoParaCursadaException e)
        {
            fail("No se deberia lanzar la excepcion");
        } catch (AlumnoRegistradoEnCursadaException e)
        {
            fail("No se deberia lanzar la excepcion");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar la excepcion");
        }
    }
    
    //Se testea agregar un Alumno existente en una cursada existente sin disponibilidad horaria 
    @Test
    public void agregarAlumnoEnCursadaTest4()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateC").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarAlumnoEnCursada(a, c);
            fail("Se debe lanzar AlumnoOcupadoParaCursadaException");
        } catch (AlumnoInhabilitadoException e)
        {
            fail("No se deberia lanzar la excepcion .");
        } catch (AlumnoOcupadoParaCursadaException e)
        {
            assertTrue("Error en la excepcion",e.getA().equals(a)&&e.getC().equals(c));
        } catch (AlumnoRegistradoEnCursadaException e)
        {
            fail("No se deberia lanzar la excepcion");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar la excepcion");
        }
    }
    //Se testea agregar un Alumno inexistente en una cursada existente 
    @Test
    public void agregarAlumnoEnCursadaTest5CB()
    {
        Alumno a=new Alumno("ALU0056","Canonaco","Seba","lalalal","adada@adada");
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateC").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarAlumnoEnCursada(a, c);
            fail("Se debe lanzar NoExisteEntidadException");
        } catch (AlumnoInhabilitadoException e)
        {
            fail("No se deberia lanzar la excepcion .");
        } catch (AlumnoOcupadoParaCursadaException e)
        {
            fail("No se deberia lanzar la excepcion AlumnoOcupadoParaCursadaException.");
        } catch (AlumnoRegistradoEnCursadaException e)
        {
            fail("No se deberia lanzar la excepcion");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la excepcion",e.getEntidad().equals(a));
        }
    }
    //Se testea agregar un Alumno existente en una cursada inexistente 
    @Test
    public void agregarAlumnoEnCursadaTest6CB()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Cursada c=new Cursada("CUR0274",this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture4
                .facultad
                .agregarAlumnoEnCursada(a, c);
            fail("Se debe lanzar NoExisteEntidadException");
        } catch (AlumnoInhabilitadoException e)
        {
            fail("No se deberia lanzar la excepcion .");
        } catch (AlumnoOcupadoParaCursadaException e)
        {
            fail("No se deberia lanzar la excepcion AlumnoOcupadoParaCursadaException.");
        } catch (AlumnoRegistradoEnCursadaException e)
        {
            fail("No se deberia lanzar la excepcion");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la excepcion",e.getEntidad().equals(c));
        }
    }
    
    //Se testea eliminar a un alumno existente en una cursada existente, a la que está cursando
    @Test
    public void eliminarAlumnoCursada1()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarAlumnoCursada(c, a);
            assertTrue("El alumno se elimino de la cursada",!c.contieneAlumno(a));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar la excepcion");
        }
    }
    
    //Se testea eliminar a un alumno inexistente en una cursada existente
    @Test
    public void eliminarAlumnoCursada2()
    {
        Alumno a=new Alumno("ALU0012","Fernandez","Jose","asdd","maskm@mcm");
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarAlumnoCursada(c, a);
            fail("Se debe lanzar NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la excepcion",e.getEntidad().equals(a));
        }
    }
    //Se testea eliminar a un alumno existente en una cursada inexistente
    @Test
    public void eliminarAlumnoCursada3CB()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Cursada c=new Cursada("CUR0237",this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture4
                .facultad
                .eliminarAlumnoCursada(c, a);
            fail("Se deberia lanzar NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(c));
        }
    }
    
    //Se testea agregar un profesor existente en una cursada existente con validez en la competencia y en la disponibilidad horaria
    @Test
    public void agregarProfesorEnCursada1()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Felipe", "Evans").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarProfesorEnCursada(p, c);
            assertTrue("error al agregar profesor en cursada",c.contieneProfesor(p));
        } catch (NoExisteEntidadException e)
        {
            fail("No debe lanzar esta excepcion 1 ");
        } catch (ProfesorInhabilitadoException e)
        {
            fail("No debe lanzar esta excepcion 2");
        } catch (ProfesorOcupadoParaCursadaException e)
        {
            fail("No debe lanzar esta excepcion 3");
        } catch (ProfesorRegistradoEnCursadaException e)
        {
            fail("No debe lanzar esta excepcion 4");
        }
    }
    
    //Se testea agregar un profesor existente en una cursada existente con validez en la competencia y en la disponibilidad horaria pero que ya se encuentre registrado
    @Test
    public void agregarProfesorEnCursada2()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarProfesorEnCursada(p, c);
            fail("Se debe lanzar ProfesorRegistradoEnCursadaException");
        } catch (NoExisteEntidadException e)
        {
            fail("No debe lanzar esta excepcion");
        } catch (ProfesorInhabilitadoException e)
        {
            fail("No debe lanzar esta excepcion .");
        } catch (ProfesorOcupadoParaCursadaException e)
        {
            fail("No debe lanzar esta excepcion");
        } catch (ProfesorRegistradoEnCursadaException e)
        {
            assertTrue("Error en la excepcion",e.getP().equals(p));
        }
    }
    
    //Se testea agregar un profesor existente en una cursada existente con invalidez en la competencia y en la disponibilidad horaria
    @Test
    public void agregarProfesorEnCursada3()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarProfesorEnCursada(p, c);
            fail("Se debe lanzar ProfesorInhabilitadoException");
        } catch (NoExisteEntidadException e)
        {
            fail("No debe lanzar esta excepcion");
        } catch (ProfesorInhabilitadoException e)
        {
            assertTrue("Error en la excepcion",e.getP().equals(p));
        } catch (ProfesorOcupadoParaCursadaException e)
        {
            fail("No debe lanzar esta excepcion");
        } catch (ProfesorRegistradoEnCursadaException e)
        {
            fail("No debe lanzar esta excepcion");
        }
    }
    
    //Se testea agregar un profesor existente en una cursada existente con validez en la competencia y sin disponibilidad horaria
    @Test
    public void agregarProfesorEnCursada4()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateC").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarProfesorEnCursada(p, c);
            fail("Se debe lanzar ProfesorOcupadoParaCursadaException");
        } catch (NoExisteEntidadException e)
        {
            fail("No debe lanzar esta excepcion");
        } catch (ProfesorInhabilitadoException e)
        {
            fail("No debe lanzar esta excepcion .");
        } catch (ProfesorOcupadoParaCursadaException e)
        {
            assertTrue("Error en la excepcion",e.getP().equals(p));
        } catch (ProfesorRegistradoEnCursadaException e)
        {
            fail("No debe lanzar esta excepcion");
        }
    }
    //Se testea agregar un profesor inexistente en una cursada existente 
    @Test
    public void agregarProfesorEnCursada5CB()
    {
        Profesor p=new Profesor("PRO5000","lala","lolo","adada","ada@adad","2342342");
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarProfesorEnCursada(p, c);
            fail(" debe lanzar esta NoExisteEntidadException ");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(p));
        } catch (ProfesorInhabilitadoException e)
        {
            fail("No debe lanzar esta excepcion 2");
        } catch (ProfesorOcupadoParaCursadaException e)
        {
            fail("No debe lanzar esta excepcion 3");
        } catch (ProfesorRegistradoEnCursadaException e)
        {
            fail("No debe lanzar esta excepcion 4");
        }
    }
    //Se testea agregar un profesor existente en una cursada inexistente 
    @Test
    public void agregarProfesorEnCursada6CB()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Cursada c=new Cursada("CUR0234",this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture4
                .facultad
                .agregarProfesorEnCursada(p, c);
            fail(" debe lanzar esta NoExisteEntidadException ");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(c));
        } catch (ProfesorInhabilitadoException e)
        {
            fail("No debe lanzar esta excepcion 2");
        } catch (ProfesorOcupadoParaCursadaException e)
        {
            fail("No debe lanzar esta excepcion 3");
        } catch (ProfesorRegistradoEnCursadaException e)
        {
            fail("No debe lanzar esta excepcion 4");
        }
    }
    
    //Se testea eliminar a un profesor existente de una cursada existente en la cual este registrado
    @Test
    public void eliminarProfesorCursada1()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarProfesorCursada(c, p);
            assertTrue("El alumno se elimino de la cursada",!c.contieneProfesor(p));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar la excepcion");
        }
    }
    
    //Se testea eliminar a un profesor existente de una cursada existente en la cual este registrado
    @Test
    public void eliminarProfesorCursada2()
    {
        Profesor p=new Profesor("PRO2000","Fernandez","Matias","asds","smd@ss","12121");
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarProfesorCursada(c, p);
            fail("Se debio lanzar NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la excepcion",e.getEntidad().equals(p));
        }
    }
    //Se testea eliminar a un profesor existente de una cursada inexistente 
    @Test
    public void eliminarProfesorCursada3CB()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Cursada c=new Cursada("CUR0234",this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture4
                .facultad
                .eliminarProfesorCursada(c, p);
            fail("Se deberia lanzar NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(c));
        }
    }
    //Se testea la baja de un alumno existente y su correspondiente baja de las cursadas en las que se encuentra inscripto
    @Test
    public void bajaAlumnoCB()
    {
            try
            {
                //cursadas en la que esta inscripto el alumno
                Cursada c1=this.fixture4.facultad.buscarCursadaPorNombre("mateA").get(0);
                Cursada c2=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
                Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
                this.fixture4
                    .facultad
                    .bajaAlumno("ALU0001");
                assertTrue("Se deberia haber eliminado al alumno",this.fixture4.facultad.getAlumnos()
                           .size()==2);
                //me fijo que no este mas en las cursadas
                assertTrue("Se deberia haber eliminado el  alumno de las cursadas",!c1.contieneAlumno(a)&&!c2.contieneAlumno(a));
            } catch (NoExisteEntidadException e)
            {
                fail("El legajo del alumno a eliminar deberia existir y se deberia dar de baja correctamente");
            }
    }
    //Se testea la baja de un profesor existente y su correspondiente baja de las cursadas en las que se encuentra inscripto
    @Test
    public void bajaProfesorCB1()
    {
            try
            {
                //cursadas en la que esta inscripto el profesor
                Cursada c1=this.fixture4.facultad.buscarCursadaPorNombre("mateB").get(0);
                Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
                this.fixture4
                    .facultad
                    .bajaProfesor("PRO0001");
                assertTrue("Se deberia haber eliminado al profesor",this.fixture4.facultad.getProfesores()
                    .size()==2);
                //me fijo que no este mas en las cursadas
                assertTrue("Se deberia haber eliminado el  profesor de las cursadas",!c1.contieneProfesor(p));
            } catch (NoExisteEntidadException e)
            {
                fail("El legajo del profesor a eliminar deberia existir y se deberia dar de baja correctamente");
            }
    }
    //Se testea la baja de una asignatura existente segun id que contiene cursada
    @Test
    public void bajaAsignaturaCB1()
    {
        try
        {
            this.fixture4
                .facultad
                .bajaAsignatura(this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0));
            assertTrue("Se deberia haber eliminado a la asignatura",this.fixture4.facultad.getAsignaturas()
                       .size()==2);
            assertTrue("Se deberia haber eliminado la cursada",this.fixture4.facultad.getCursadas()
                       .size()==2);
        } catch (NoExisteEntidadException e)
        {
            fail("La identificacion de la asignatura a eliminar deberia existir");
        }
    }
    //Se testea la aprobacion de un alumno inexistente en una asignatura existente
    @Test
    public void aprobarAlumnoAsignaturaCB1()
    {
        Alumno a=new Alumno("ALU0012","Fernandez","Jose","asdd","maskm@mcm");
        Asignatura asi=this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .aprobarAlumnoAsignatura(a, asi);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (AsignaturaAprobadaYaRegistradaException e)
        {
            fail("No deberia lanzarse AsignaturaAprobadaYaRegistradaException");
        } catch (AsignaturaNoAprobableException e)
        {
            fail("No deberia lanzarse AsignaturaNoAprobableException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(a));
        }
    }
    //Se testea la aprobacion de un alumno existente en una asignatura inexistente
    @Test
    public void aprobarAlumnoAsignaturaCB2()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Asignatura asi=new Asignatura("ASI2221","lala");
        try
        {
            this.fixture4
                .facultad
                .aprobarAlumnoAsignatura(a, asi);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (AsignaturaAprobadaYaRegistradaException e)
        {
            fail("No deberia lanzarse AsignaturaAprobadaYaRegistradaException");
        } catch (AsignaturaNoAprobableException e)
        {
            fail("No deberia lanzarse AsignaturaNoAprobableException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(asi));
        }
    }
    //Se testea la aprobacion de un alumno existente en una asignatura existente y que la tiene aprobada
    @Test
    public void aprobarAlumnoAsignaturaCB3()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Asignatura asi=this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .aprobarAlumnoAsignatura(a, asi);
            fail("Se deberia haber lanzado AsignaturaAprobadaYaRegistradaException");
        } catch (AsignaturaAprobadaYaRegistradaException e)
        {
            assertTrue("Error en AsignaturaAprobadaYaRegistradaException",e.getAlumno().equals(a));
        } catch (AsignaturaNoAprobableException e)
        {
            fail("No deberia lanzarse AsignaturaNoAprobableException");
        } catch (NoExisteEntidadException e)
        {
            fail("No deberia lanzarse NoExisteEntidadException");
        }
    }
    //Se testea la eliminacion de una asignatura existente de la historia de un alumno existente
    @Test
    public void eliminarAlumnoAsignaturaCB1()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Asignatura asi=this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarAlumnoAsignatura(a, asi);
            assertTrue("Se deberia haber eliminado la asignatura de la historia",!a.getHistoria().contains(asi));
        } catch (NoExisteEntidadException e)
        {
            fail("no se deberia lanzar NoExisteEntidadException");
        }
    }
    //Se testea la eliminacion de una asignatura inexistente de la historia de un alumno existente
    @Test
    public void eliminarAlumnoAsignaturaCB2()
    {
        Alumno a=this.fixture4.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Asignatura asi=new Asignatura("ASI2221","lala");
        try
        {
            this.fixture4
                .facultad
                .eliminarAlumnoAsignatura(a, asi);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(asi));
        }
    }
    //Se testea la eliminacion de una asignatura existente de la historia de un alumno inexistente
    @Test
    public void eliminarAlumnoAsignaturaCB3()
    {
        Alumno a=new Alumno("ALU0012","Fernandez","Jose","asdd","maskm@mcm");
        Asignatura asi=this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarAlumnoAsignatura(a, asi);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(a));
        }
    }
    
    //Se testea agregar una competencia valida a un profesor existente quien ya tiene agregada la asignatura a su competencia
    @Test
    public void agregarCompetenciaProfesorTestCB()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Asignatura asi=this.fixture4.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .agregarCompetenciaProfesor(p, asi);
            fail("Se deberia lanzar la exception AsignaturaYaRegistradaEnProfesorException");
        } catch (AsignaturaYaRegistradaEnProfesorException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException", e.getA().equals(asi));
            
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar la exception NoExisteEntidadException");
        }
    } 
    //Se testea la eliminacion de una asignatura existente de la competencia de un profesor existente
    @Test
    public void eliminarCompetenciaProfesorCB1()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Asignatura asi=this.fixture4.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarCompetenciaProfesor(p, asi);
            assertTrue("Se deberia haber eliminado la competencia del profesor",!p.getCompetencia().contains(asi));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia haber lanzado NoExisteEntidadException");
        }
    }
    //Se testea la eliminacion de una asignatura inexistente de la competencia de un profesor existente
    @Test
    public void eliminarCompetenciaProfesorCB2()
    {
        Profesor p=this.fixture4.facultad.buscarProfesorPorNombre("Leonel", "Guccione").get(0);
        Asignatura asi=new Asignatura("ASI2221","lala");
        try
        {
            this.fixture4
                .facultad
                .eliminarCompetenciaProfesor(p, asi);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException", e.getEntidad().equals(asi));
        }
    }
    
    //Se testea la eliminacion de una asignatura existente de la competencia de un profesor inexistente
    @Test
    public void eliminarCompetenciaProfesorCB3()
    {
        Profesor p=new Profesor("PRO2000","Fernandez","Matias","asds","smd@ss","12121");
        Asignatura asi=this.fixture4.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarCompetenciaProfesor(p, asi);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException", e.getEntidad().equals(p));
        }
    }
    //Se testea la eliminacion de una asignatura existente como correlativa de otra asignatura existente
    @Test
    public void eliminarCorrelativaAsignaturaCB1()
    {
        Asignatura a1=this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        Asignatura a2=this.fixture4.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarCorrelativaAsignatura(a2, a1);
            assertTrue("Se deberia haber eliminado la correlativa",!a2.getCorrelatividades().contains(a1));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia haber lanzado NoExisteEntidadException");
        }
    }
    //Se testea la eliminacion de una asignatura inexistente como correlativa de otra asignatura existente
    @Test
    public void eliminarCorrelativaAsignaturaCB2()
    {
        Asignatura a1=new Asignatura("ASI8888","adad");
        Asignatura a2=this.fixture4.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .eliminarCorrelativaAsignatura(a2, a1);
            fail("se deberia lanzar NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(a1));
        }
    }
    //Se testea la eliminacion de una asignatura existente como correlativa de otra asignatura inexistente
    @Test
    public void eliminarCorrelativaAsignaturaCB3()
    {
        Asignatura a1=this.fixture4.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        Asignatura a2=new Asignatura("ASI8888","adad");
        try
        {
            this.fixture4
                .facultad
                .eliminarCorrelativaAsignatura(a2, a1);
            fail("se deberia lanzar NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(a2));
        }
    }
    //Se testea la modificacion de una cursada existente
    @Test
    public void modificarCursadaCB1()
    {
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateA").get(0);
        Asignatura a=this.fixture4.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        try
        {
            this.fixture4
                .facultad
                .modificarCursada(c, a, "01-2017");
            assertTrue("Se deberia haber modificado la cursada",c.getAsignatura().equals(a)&&c.getPeriodo().equals("01-2017"));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia haber lanzadado NoExisteEntidadException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No se deberia haber lanzadado PeriodoInvalidoException");
        }

    }
    //Se testea la modificacion de una cursada inexistente
    @Test
    public void modificarCursadaCB2()
    {
        
        Asignatura a=this.fixture4.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        Cursada c=new Cursada("CUR3232",a,"01-2017");
        try
        {
            this.fixture4
                .facultad
                .modificarCursada(c, a, "01-2017");
            fail("Deberia haberse lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(c));
        } catch (PeriodoInvalidoException e)
        {
            fail("No se deberia haber lanzadado PeriodoInvalidoException");
        }

    }
    //Se testea la modificacion de una cursada existente con una asignatura inexistente
    @Test
    public void modificarCursadaCB3()
    {
        
        Asignatura a=new Asignatura("ASI4343","adada");
        Cursada c=this.fixture4.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture4
                .facultad
                .modificarCursada(c, a, "01-2017");
            fail("Deberia haberse lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(a));
        } catch (PeriodoInvalidoException e)
        {
            fail("No se deberia haber lanzadado PeriodoInvalidoException");
        }

    }
    
    
}