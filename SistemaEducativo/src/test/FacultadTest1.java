package test;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import sistemaeducativo.Asignatura;
import sistemaeducativo.MailInvalidoException;

public class FacultadTest1
{
    private TestFixture1 fixture1=new TestFixture1();
    public FacultadTest1()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture1.setUp();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture1.tearDown();
    }

    @Test
    public void altaAsignaturaTest1()
    {
        this.fixture1.facutltad.agregarAsignatura("Programacion3");
        assertTrue("Error al agregar asignatura",this.fixture1.facutltad.buscarAsignaturaPorNombre("Programacion3").size()==1);
    }
    @Test
    public void altaAlumnoTest1()
    {
        try
        {
            this.fixture1
                .facutltad
                .agregarAlumno("Pico", "Juan", "Falucho3433", "cabj@gmail.com");
            assertTrue("Error al agregar alumno",this.fixture1.facutltad.buscarAlumnoPorNombre("Juan", "Pico").size()==1);
        } catch (MailInvalidoException e)
        {
            fail("EL mail es correcto no deberia tirar esta excepcion");
        }

    }
    @Test
    public void altaAlumnoTest2()
    {
        try
        {
            this.fixture1
                .facutltad
                .agregarAlumno("Pico", "Juan", "Falucho3433", "cabjgmail.com");
            fail("Error al agregar alumno con mail invalido ,no dispara exception");
        } catch (MailInvalidoException e)
        {
            assertTrue("No se genero correctamente la excepcion del mail",e.getMail().equals("cabjgmail.com"));
        }

    }
    @Test
    public void altaAlumnoTest3()
    {
        try
        {
            this.fixture1
                .facutltad
                .agregarAlumno("Pico", "Juan", "Falucho3433", "@gmail.com");
            fail("Error al agregar alumno con mail invalido ,no dispara exception");
        } catch (MailInvalidoException e)
        {
            assertTrue("No se genero correctamente la excepcion del mail",e.getMail().equals("@gmail.com"));
        }

    }
    @Test
    public void altaAlumnoTest4()
    {
        try
        {
            this.fixture1
                .facutltad
                .agregarAlumno("Pico", "Juan", "Falucho3433", "cabj@");
            fail("Error al agregar alumno con mail invalido ,no dispara exception");
        } catch (MailInvalidoException e)
        {
            assertTrue("No se genero correctamente la excepcion del mail",e.getMail().equals("cabj@"));
        }

    }
    @Test
    public void altaProfesorTest1()
    {
        try
        {
            this.fixture1
                .facutltad
                .agregarProfesor("Guccione", "Leonel","Espacio 111","leonel@gmail.com","12345678");
            assertTrue("Error al agregar profesor",this.fixture1.facutltad.buscarProfesorPorNombre("Leonel", "Guccione").size()==1);
        } catch (MailInvalidoException e)
        {
            fail("EL mail es correcto no deberia tirar esta excepcion");
        }
    }
    @Test
    public void altaProfesorTest2()
    {
        try
        {
            this.fixture1
                .facutltad
                .agregarProfesor("Guccione", "Leonel","Espacio 111","@gmail.com","12345678");
            fail("Error al agregar profesor, no lanza exception");
        } catch (MailInvalidoException e)
        {
            assertTrue("Error en la excepcion no coinciden los mail",e.getMail().equals("@gmail.com"));
        }
    }
    @Test
    public void altaProfesorTest3()
    {
        try
        {
            this.fixture1
                .facutltad
                .agregarProfesor("Guccione", "Leonel","Espacio 111","leonelgmail.com","12345678");
            fail("Error al agregar profesor, no lanza exception");
        } catch (MailInvalidoException e)
        {
            assertTrue("Error en la excepcion no coinciden los mail",e.getMail().equals("leonelgmail.com"));
        }
    }
    @Test
    public void altaProfesorTest4()
    {
        try
        {
            this.fixture1
                .facutltad
                .agregarProfesor("Guccione", "Leonel","Espacio 111","leonel@","12345678");
            fail("Error al agregar profesor, no lanza exception");
        } catch (MailInvalidoException e)
        {
            assertTrue("Error en la excepcion no coinciden los mail",e.getMail().equals("leonel@"));
        }
    }
    
}
