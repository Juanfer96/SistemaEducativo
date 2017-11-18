package test;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import sistemaeducativo.Alumno;
import sistemaeducativo.AlumnoInhabilitadoException;
import sistemaeducativo.AlumnoOcupadoParaCursadaException;
import sistemaeducativo.AlumnoRegistradoEnCursadaException;
import sistemaeducativo.Cursada;
import sistemaeducativo.NoExisteEntidadException;
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
}
