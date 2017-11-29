package test;

import java.time.LocalTime;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import sistemaeducativo.Alumno;
import sistemaeducativo.Asignatura;
import sistemaeducativo.CorrelativaRegistradaException;
import sistemaeducativo.Cursada;
import sistemaeducativo.Fecha;
import sistemaeducativo.FormatoHoraException;
import sistemaeducativo.HorarioCursadaInvalidoException;
import sistemaeducativo.HorarioCursadaSuperpuestaException;
import sistemaeducativo.NoExisteEntidadException;
import sistemaeducativo.Profesor;

public class FacultadTest3
{
    TestFixture3 fixture3=new TestFixture3();
    
    public FacultadTest3()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture3.setUp();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture3.tearDown();
    }
    
    //Se testea la busqueda de una cursada existente 
    @Test
    public void buscarCursadaPorNombreTest1()
    {
        assertTrue("Se deberia haber encontrado a la cursada",this.fixture3.facultad.buscarCursadaPorNombre("mateA").size()==1);
    }
    
    //Se testea la busqueda de una cursada inexistente 
    @Test
    public void buscarCursadaPorNombreTest2()
    {
        assertTrue("No Se deberia haber encontrado a la cursada",this.fixture3.facultad.buscarCursadaPorNombre("mateD").size()==0);
    }
    //Se testea que se agregue una correlativa valida a una asignatura
    @Test
    public void agregarCorrelativaAsignaturaTest1()
    {
        Asignatura a1=this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        Asignatura a2=this.fixture3.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        try
        {
            this.fixture3
                .facultad
                .agregarCorrelativaAsignatura(a1, a2);
            assertTrue("La asignatura no se agrego a las correlatividades ",this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0).getCorrelatividades().size()==1);
        } catch (CorrelativaRegistradaException e)
        {
            fail("no se deberia haber lanzado CorrelativaRegistradaException ");
        } catch (NoExisteEntidadException e)
        {
            fail("no se deberia haber lanzado NoExisteEntidadException ya que la asignatura existe ");
        }
    }
    //Se testea que se agregue una correlativa que ya esta agregada
    @Test
    public void agregarCorrelativaAsignaturaTest2()
    {
        Asignatura a1=this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        Asignatura a2=this.fixture3.facultad.buscarAsignaturaPorNombre("mateB").get(0);
        try
        {
            this.fixture3
                .facultad
                .agregarCorrelativaAsignatura(a1, a2);
        } catch (CorrelativaRegistradaException | NoExisteEntidadException e)
        {
        }
        try
        {
            this.fixture3
                .facultad
                .agregarCorrelativaAsignatura(a1, a2);
            fail("La asignatura ya estaba agregada como correlativa , se debio lanzarCorrelativaRegistradaException ");
            
        } catch (CorrelativaRegistradaException e)
        {
            assertTrue("error en la exception CorrelativaRegistradaException ",e.getCorrelativa().equals(a2));
        } catch (NoExisteEntidadException e)
        {
            fail("no se deberia haber lanzado NoExisteEntidadException ya que la asignatura existe ");
        }
        
    }
    //Se testea que se agregue una correlativa inexsitente a una asignatura
    @Test
    public void agregarCorrelativaAsignaturaTest3()
    {
        Asignatura a1=this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        Asignatura a2=new Asignatura("ASI0010","mateD");
        try
        {
            this.fixture3
                .facultad
                .agregarCorrelativaAsignatura(a1, a2);
            fail("La correlativa no existe se debio lanzar NoExisteEntidadException");
            
        } catch (CorrelativaRegistradaException e)
        {
            fail("no se deberia haber lanzado CorrelativaRegistradaException ");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en  NoExisteEntidadException",e.getEntidad().equals(a2));
        }
    }
    //Se testea que se agregue una correlativa exsitente a una asignatura inexistente
    @Test
    public void agregarCorrelativaAsignaturaTest4CB()
    {
        Asignatura a1=this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0);
        Asignatura a2=new Asignatura("ASI0010","mateD");
        try
        {
            this.fixture3
                .facultad
                .agregarCorrelativaAsignatura(a2, a1);
            fail("La correlativa no existe se debio lanzar NoExisteEntidadException");
            
        } catch (CorrelativaRegistradaException e)
        {
            fail("no se deberia haber lanzado CorrelativaRegistradaException ");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en  NoExisteEntidadException",e.getEntidad().equals(a2));
        }
    }
    //Se testea la baja de una cursada existente 
    @Test
    public void bajaCursadaTest1()
    {
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture3
                .facultad
                .bajaCursada(c);
            assertTrue("Se deberia haber eliminado a la cursada",this.fixture3.facultad.getCursadas().size()==2);
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar NoExisteEntidadException, la cursada existe ");
        }
    }
    //Se testea la baja de una cursada inexistente 
    @Test
    public void bajaCursadaTest2()
    {
        Cursada c=new Cursada("CUR0010",this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture3
                .facultad
                .bajaCursada(c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException ",e.getEntidad().equals(c));
        }
    }
    //Se testea el agregado valido de un horario a una cursada
    @Test
    public void agregarHorarioCursadaTest1()
    {
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture3
                .facultad
                .agregarHorarioCursada(c, 14, 0, 16, 0, Fecha.MARTES);
            assertTrue("El horario no se agrego correctamente",c.getHorario().size()==2);
        } catch (FormatoHoraException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (HorarioCursadaInvalidoException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (HorarioCursadaSuperpuestaException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (NoExisteEntidadException e)
        {
            fail("No se debe lanzar esta exception");
        }
    }
    //Se testea el agregado valido de un horario superpuesto a una cursada
    @Test
    public void agregarHorarioCursadaTest2()
    {
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        
        try
        {
                this.fixture3
                .facultad
                .agregarHorarioCursada(c, 8, 0, 10, 0, Fecha.LUNES);
           fail("Se debe lanzar HorarioCursadaSuperpuestaException");
        } catch (FormatoHoraException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (HorarioCursadaInvalidoException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (HorarioCursadaSuperpuestaException e)
        {
            assertTrue("No se debe lanzar esta exception",e.getCursada().equals(c));
        } catch (NoExisteEntidadException e)
        {
            fail("No se debe lanzar esta exception");
        }
    }
    //Se testea el agregado  de un horario invalido a una cursada
    @Test
    public void agregarHorarioCursadaTest3()
    {
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        
        try
        {
                this.fixture3
                .facultad
                .agregarHorarioCursada(c, 11, 0, 10, 0, Fecha.MARTES);
           fail("Se debe lanzar HorarioCursadaInvalidoException");
        } catch (FormatoHoraException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (HorarioCursadaInvalidoException e)
        {
            LocalTime auxInicio=LocalTime.of(11, 0);
            LocalTime auxFin=LocalTime.of(10, 0);
            assertTrue("Error en HorarioCursadaInvalidoException",e.getHoraFin().equals(auxFin)&&e.getHoraInicio().equals(auxInicio));
        } catch (HorarioCursadaSuperpuestaException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (NoExisteEntidadException e)
        {
            fail("No se debe lanzar esta exception");
        }
    }
    //Se testea el agregado valido de un horario con formato invalido a una cursada
    @Test
    public void agregarHorarioCursadaTest4()
    {
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        
        try
        {
                this.fixture3
                .facultad
                .agregarHorarioCursada(c, 52, 23, 10, 24, Fecha.MIERCOLES);
           fail("Se debe lanzar FormatoHoraException");
        } catch (FormatoHoraException e)
        {
            assertTrue("Error en FormatoHoraException ",e.getHoraInicio()==52);
        } catch (HorarioCursadaInvalidoException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (HorarioCursadaSuperpuestaException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (NoExisteEntidadException e)
        {
            fail("No se debe lanzar esta exception");
        }
    }
    //Se testea el agregado valido de un horario a una cursada inexsistente
    @Test
    public void agregarHorarioCursadaTest5()
    {
        Cursada c=new Cursada("CUR0025",this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        
        try
        {
                this.fixture3
                .facultad
                .agregarHorarioCursada(c, 8, 0, 10, 0, Fecha.VIERNES);
           fail("Se debe lanzar NoExisteEntidadException");
        } catch (FormatoHoraException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (HorarioCursadaInvalidoException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (HorarioCursadaSuperpuestaException e)
        {
            fail("No se debe lanzar esta exception");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en NoExisteEntidadException",e.getEntidad().equals(c));
        }
    }
    //Se testea la eliminacion valida de un horario de una cursada existente
    @Test
    public void eliminarHorarioCursada1()
    {
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        Fecha f=c.getHorario().get(0);
        try
        {
            this.fixture3
                .facultad
                .eliminarHorarioCursada(c, f);
            assertTrue("Se deberia haber eliminado el horario",!c.getHorario().contains(f));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar NoExisteEntidadException , ya que el horario existe ");
        }
    }
    //Se testea la eliminacion valida de un horario existente de una cursada inexistente
    @Test
    public void eliminarHorarioCursada2()
    {
        Cursada c=new Cursada("CUR0025",this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            c.agregarHorario(Fecha.JUEVES, 12, 0, 14, 0);
            Fecha f=c.getHorario().get(0);
            this.fixture3
                .facultad
                .eliminarHorarioCursada(c, f);
            fail("Se deberia lanzar NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException", e.getEntidad().equals(c));
        }
        catch (HorarioCursadaInvalidoException | HorarioCursadaSuperpuestaException e)
        {
        }
    }
    
    //Se testea la eliminacion valida de un horario inexistente de una cursada existente
    @Test
    public void eliminarHorarioCursada3()
    {
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        Fecha f=new Fecha(Fecha.JUEVES,12,0,15,0);
        try
        {
            this.fixture3
                .facultad
                .eliminarHorarioCursada(c, f);
            fail("Se deberia lanzar NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException", e.getEntidad().equals(f));
        }
    }
    //Se testea la verificacion de un alumno inexistente y una cursada inexistente
    @Test
    public void alumnoOcupadoParaCursada1CB()
    {
        Alumno a=new Alumno("ALU0056","Canonaco","Seba","lalalal","adada@adada");
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture3
                .facultad
                .alumnoOcupadoParaCursada(a, c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(a));
        }
    }
    //Se testea la verificacion de un alumno existente y una cursada inexistente
    @Test
    public void alumnoOcupadoParaCursada2CB()
    {
        Alumno a =this.fixture3.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Cursada c=new Cursada("CUR0234",this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture3
                .facultad
                .alumnoOcupadoParaCursada(a, c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(c));
        }
    }
    //Se testea la verificacion de un profesor inexistente y una cursada existente
    @Test
    public void profesorOcupadoParaCursada1CB()
    {
        Profesor a=new Profesor("PRO0056","Canonaco","Seba","lalalal","adada@adada","2235336336");
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture3
                .facultad
                .profesorOcupadoParaCursada(a, c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(a));
        }
    }
    //Se testea la verificacion de un profesor existente y una cursada inexistente
    @Test
    public void profesorOcupadoParaCursada2CB()
    {
        Profesor a=this.fixture3.facultad.buscarProfesorPorNombre("Jorge", "Rico").get(0);
        Cursada c=new Cursada("CUR0234",this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture3
                .facultad
                .profesorOcupadoParaCursada(a, c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(c));
        }
    }
    //Se testea la verificacion de un profesor inexstitente Habilitado para una cursada existente
    @Test
    public void profesorHabilitadoParaCursada1CB()
    {
        Profesor a=new Profesor("PRO0056","Canonaco","Seba","lalalal","adada@adada","2235336336");
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture3
                .facultad
                .profesorHabilitadoParaCursada(a, c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Erro en NoExisteEntidadException",e.getEntidad().equals(a));
        }

    }
    //Se testea la verificacion de un profesor exstitente Habilitado para una cursada inexistente
    @Test
    public void profesorHabilitadoParaCursada2CB()
    {
        Profesor a=this.fixture3.facultad.buscarProfesorPorNombre("Jorge", "Rico").get(0);
        Cursada c=new Cursada("CUR0234",this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture3
                .facultad
                .profesorHabilitadoParaCursada(a, c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Erro en NoExisteEntidadException",e.getEntidad().equals(c));
        }

    }
    //Se testea la verificacion de un alumno inexstitente Habilitado para una cursada existente
    @Test
    public void alumnoHabilitadoParaCursada1CB()
    {
        Alumno a=new Alumno("ALU0056","Canonaco","Seba","lalalal","adada@adada");
        Cursada c=this.fixture3.facultad.buscarCursadaPorNombre("mateA").get(0);
        try
        {
            this.fixture3
                .facultad
                .alumnoHabilitadoParaCursada(a, c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Erro en NoExisteEntidadException",e.getEntidad().equals(a));
        }

    }
    //Se testea la verificacion de un alumno exstitente Habilitado para una cursada inexistente
    @Test
    public void alumnoHabilitadoParaCursada2CB()
    {
        Alumno a =this.fixture3.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        Cursada c=new Cursada("CUR0234",this.fixture3.facultad.buscarAsignaturaPorNombre("mateA").get(0),"01-2017");
        try
        {
            this.fixture3
                .facultad
                .alumnoHabilitadoParaCursada(a, c);
            fail("Se deberia haber lanzado NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Erro en NoExisteEntidadException",e.getEntidad().equals(c));
        }

    }
    
}
