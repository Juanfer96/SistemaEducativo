package test;

import java.util.ArrayList;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import sistemaeducativo.Alumno;
import sistemaeducativo.Asignatura;
import sistemaeducativo.AsignaturaYaRegistradaEnProfesorException;
import sistemaeducativo.MailInvalidoException;
import sistemaeducativo.NoExisteEntidadException;
import sistemaeducativo.PeriodoInvalidoException;
import sistemaeducativo.Profesor;

public class FacultadTest2
{
    TestFixture2 fixture2=new TestFixture2();
    public FacultadTest2()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture2.setUp();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture2.tearDown();
    }
    
    //Se testea el ingreso de una asignatura existiendo otra con disnto nombre
    @Test
    
    public void altaAsignaturaTest1()
    {
        this.fixture2.facultad.agregarAsignatura("Programacion3");
        assertTrue("Error al agregar asignatura",this.fixture2.facultad.buscarAsignaturaPorNombre("Programacion3").size()==1);
    }
    //Se testea el ingreso de una asignatura existiendo otra con el mismo nombre
    @Test
    public void altaAsignaturaTest2()
    {
        this.fixture2.facultad.agregarAsignatura("Analisis 1");
        ArrayList<Asignatura> asignaturas=this.fixture2.facultad.buscarAsignaturaPorNombre("Analisis 1");
        assertTrue("Error al agregar asignatura",asignaturas.size()==2);
    }
    //Se testea el ingreso de un alumno existiendo otro con distinto nombre
    @Test
    public void altaAlumnoTest1()
    {
        try
        {
            this.fixture2
                .facultad
                .agregarAlumno("Ponce", "Emanuel", "Falucho3433", "cabj@gmail.com");
            Alumno a=this.fixture2.facultad.buscarAlumnoPorNombre("Emanuel", "Ponce").get(0);
            assertTrue("Error al agregar alumno",this.fixture2.facultad.getAlumnos().containsValue(a));
        } catch (MailInvalidoException e)
        {
            fail("EL mail es correcto no deberia tirar esta excepcion");
        }

    }
    //Se testea el ingreso de un alumno existiendo otro con el mismo  nombre
    @Test
    public void altaAlumnoTest2()
    {
        try
        {
            this.fixture2
                .facultad
                .agregarAlumno("Pico", "Juan", "Falucho3433", "cabj@gmail.com");
            assertTrue("Error al agregar alumno",this.fixture2.facultad.buscarAlumnoPorNombre("Juan", "Pico").size()==2);
        } catch (MailInvalidoException e)
        {
            fail("EL mail es correcto no deberia tirar esta excepcion");
        }

    }
    //se testea el alta de un profesor existiendo otro con distinto nombre
    @Test
    public void altaProfesorTest1()
    {
        try
        {
            this.fixture2
                .facultad
                .agregarProfesor("Guccione", "Leonel","Espacio 111","leonel@gmail.com","12345678");
            assertTrue("Error al agregar profesor",this.fixture2.facultad.buscarProfesorPorNombre("Leonel", "Guccione").size()==1);
        } catch (MailInvalidoException e)
        {
            fail("EL mail es correcto no deberia tirar esta excepcion");
        }
    }
    //se testea el alta de un profesor existiendo otro con el mismo nombre
    @Test
    public void altaProfesorTest2()
    {
        try
        {
            this.fixture2
                .facultad
                .agregarProfesor("Laxurri", "Guillermo","Espacio 111","leonel@gmail.com","12345678");
            assertTrue("Error al agregar profesor",this.fixture2.facultad.buscarProfesorPorNombre("Guillermo", "Laxurri").size()==2);
        } catch (MailInvalidoException e)
        {
            fail("EL mail es correcto no deberia tirar esta excepcion");
        }
    }
    
    //Se testea el alta de una cursada a la facultad, a partir de una asignatura existente
    @Test
    public void agregarCursadaTest1()
    {
        try
        {
            this.fixture2
                .facultad
                .agregarCursada(this.fixture2
                                    .facultad
                                    .buscarAsignaturaPorNombre("Analisis 1")
                                    .get(0), "01-2017");
            assertTrue("Se deberia haber agregado la cursada a la facultad",this.fixture2.facultad.buscarCursadaPorNombre("Analisis 1").size()==1);
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar esta excepcion ya que la asignatura existe");
        } catch (PeriodoInvalidoException e)
        {
            fail("No se deberia lanzar esta excepcion ya que el periodo ingresado es valido");
        }
    }
    
    //Se testea el alta de una cursada a la facultad, a partir de una asignatura inexistente
    @Test
    public void agregarCursadaTest2()
    {
        //Se crea una asignatura que no esta registrada en la facultad
        Asignatura a=new Asignatura("ASI0002","Programacion 3");
        try
        {
            this.fixture2
                .facultad
                .agregarCursada(a, "01-2017");
            fail("Deberia haber lanzado la exception NoExisteEntidadException ya que la asignatura no se encuentra registrada en la facultad");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("La exception lanzada no corresponde al mensaje esperado",e.getEntidad().equals(a));
        } catch (PeriodoInvalidoException e)
        {
            fail("No se deberia lanzar esta excepcion ya que el periodo ingresado es valido");
        }
    }
    //Se testea el alta de una cursada a la facultad, a partir de una asignatura existente, pero con la longitud del periodo erroneo
    @Test
    public void agregarCursadaTest3()
    {
        String periodo="01-20170";
        try
        {
            this.fixture2
                .facultad
                .agregarCursada(this.fixture2
                                    .facultad
                                    .buscarAsignaturaPorNombre("Analisis 1")
                                    .get(0), periodo);
            fail("Deberia haber lanzado la exception PeriodoInvalidoException ya que el periodo tiene una longitud erronea");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar esta excepcion ya que la asignatura existe");
        } catch (PeriodoInvalidoException e)
        {
            assertTrue("Error dentro de la exception PeriodoInvalidoException",e.getPeriodo().equals(periodo));
        }
    }
    //Se testea el alta de una cursada a la facultad, a partir de una asignatura existente, pero con el prefijo del periodo erroneo
    @Test
    public void agregarCursadaTest4()
    {
        String periodo="03-2017";
        try
        {
            this.fixture2
                .facultad
                .agregarCursada(this.fixture2
                                    .facultad
                                    .buscarAsignaturaPorNombre("Analisis 1")
                                    .get(0), periodo);
            fail("Deberia haber lanzado la exception PeriodoInvalidoException ya que el periodo tiene una longitud erronea");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar esta excepcion ya que la asignatura existe");
        } catch (PeriodoInvalidoException e)
        {
            assertTrue("Error dentro de la exception PeriodoInvalidoException",e.getPeriodo().equals(periodo));
        }
    }
    
    //Se testea el alta de una cursada a la facultad, a partir de una asignatura existente, pero con el sufijo del periodo erroneo
    @Test
    public void agregarCursadaTest5()
    {
        String periodo="01-AG70";
        try
        {
            this.fixture2
                .facultad
                .agregarCursada(this.fixture2
                                    .facultad
                                    .buscarAsignaturaPorNombre("Analisis 1")
                                    .get(0), periodo);
            fail("Deberia haber lanzado la exception PeriodoInvalidoException ya que el periodo tiene una longitud erronea");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar esta excepcion ya que la asignatura existe");
        } catch (PeriodoInvalidoException e)
        {
            assertTrue("Error dentro de la exception PeriodoInvalidoException",e.getPeriodo().equals(periodo));
        }
    }
    
    //Se testea la busqueda de un alumno existente segun nombre y apellido
    @Test
    public void buscarAlumnoPorNombreTest1()
    {
        assertTrue("Se deberia haber encontrado al alumno",this.fixture2.facultad.buscarAlumnoPorNombre("Juan", "Pico").size()==1);
    }
    
    //Se testea la busqueda de un alumno inexistente segun nombre y apellido
    @Test
    public void buscarAlumnoPorNombreTest2()
    {
        assertTrue("No se deberia haber encontrado al alumno",this.fixture2.facultad.buscarAlumnoPorNombre("Emanuel", "Ponce").size()==0);
    }
    //Se testea la busqueda de un alumno existente segun nombre e inexsistente segun apellido
    @Test
    public void buscarAlumnoPorNombreTest3CB()
    {
        assertTrue("No se deberia haber encontrado al alumno",this.fixture2.facultad.buscarAlumnoPorNombre("Juan", "Ponce").size()==0);
    }
    
    
    //Se testea la busqueda de un profesor existente segun nombre y apellido
    @Test
    public void buscarProfesorPorNombreTest1()
    {
        assertTrue("Se deberia haber encontrado al profesor",this.fixture2.facultad.buscarProfesorPorNombre("Guillermo", "Laxurri").size()==1);
    }
    
    //Se testea la busqueda de un profesor inexistente segun nombre y apellido
    @Test
    public void buscarProfesorPorNombreTest2()
    {
        assertTrue("No se deberia haber encontrado al profesor",this.fixture2.facultad.buscarProfesorPorNombre("Leonel", "Guccione").size()==0);
    }
    //Se testea la busqueda de un profesor existente segun nombre e inexsistente segun apellido
    @Test
    public void buscarProfesorPorNombreTest3CB()
    {
        assertTrue("No se deberia haber encontrado al profesor",this.fixture2.facultad.buscarProfesorPorNombre("Guillermo", "Ponce").size()==0);
    }
    
    //Se testea la busqueda de una asignatura existente segun nombre 
    @Test
    public void buscarAsignaturaPorNombreTest1()
    {
        assertTrue("Se deberia haber encontrado a la asignatura",this.fixture2.facultad.buscarAsignaturaPorNombre("Analisis 1").size()==1);
    }
    
    //Se testea la busqueda de una asignatura inexistente segun nombre
    @Test
    public void buscarAsignaturaPorNombreTest2()
    {
        assertTrue("No se deberia haber encontrado a la asignatura",this.fixture2.facultad.buscarAsignaturaPorNombre("Programacion 1").size()==0);
    }
    
    //Se testea la baja de un alumno existente segun legajo
    @Test
    public void bajaAlumnoTest1()
    {
        try
        {
            this.fixture2
                .facultad
                .bajaAlumno("ALU0001");
            assertTrue("Se deberia haber eliminado al alumno",this.fixture2.facultad.getAlumnos()
                       .size()==0);
        } catch (NoExisteEntidadException e)
        {
            fail("El legajo del alumno a eliminar deberia existir");
        }
    }
    
    //Se testea la baja de un alumno inexistente segun legajo
    @Test
    public void bajaAlumnoTest2()
    {
        Alumno a=new Alumno("ALU0002","Ponce","Emanuel","ASD123","ASD@GMAIL.COM");
        try
        {
            this.fixture2
                .facultad
                .bajaAlumno(a.getLegajo());
            fail("No se deberia haber eliminado al alumno, se deberia haber lanzado la exception NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(a.getLegajo()));
        }
    }
    //Se testea la baja de un profesor existente segun legajo
    @Test
    public void bajaProfesorTest1()
    {
        try
        {
            this.fixture2
                .facultad
                .bajaProfesor("PRO0001");
            assertTrue("Se deberia haber eliminado al profesor",this.fixture2.facultad.getProfesores()
                       .size()==0);
        } catch (NoExisteEntidadException e)
        {
            fail("El legajo del profesor a eliminar deberia existir");
        }
    }
    
    //Se testea la baja de un profesor inexistente segun legajo
    @Test
    public void bajaProfesorTest2()
    {
        Profesor p=new Profesor("PRO0002","Guccione","Leonel","assdd","mamss@gmail","4344333");
        try
        {
            this.fixture2
                .facultad
                .bajaProfesor(p.getLegajo());
            fail("No se deberia haber eliminado al profesor, se deberia haber lanzado la exception NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(p.getLegajo()));
        }
    }
    
    //Se testea la baja de una asignatura existente segun id
    @Test
    public void bajaAsignaturaTest1()
    {
        try
        {
            this.fixture2
                .facultad
                .bajaAsignatura(this.fixture2.facultad.buscarAsignaturaPorNombre("Analisis 1").get(0));
            assertTrue("Se deberia haber eliminado a la asignatura",this.fixture2.facultad.getAsignaturas()
                       .size()==0);
        } catch (NoExisteEntidadException e)
        {
            fail("La identificacion de la asignatura a eliminar deberia existir");
        }
    }
    
    //Se testea la baja de una asignatura inexistente segun id
    @Test
    public void bajaAsignaturaTest2()
    {
        Asignatura a=new Asignatura("ASI0002","Programacion 3");
        try
        {
            this.fixture2
                .facultad
                .bajaAsignatura(a);
            fail("No se deberia haber eliminado a la asignatura, se deberia haber lanzado la exception NoExisteEntidadException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(a));
        }
    }
    
    //Se testea la modificacion de un alumno existente con los datos de modificacion validos
    @Test
    public void modificarAlumnoTest1()
    {
        Alumno anterior=this.fixture2.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        String nombreNuevo="Emanuel", apellidoNuevo="Ponce",direNueva="Tejedor 123",mailNuevo="manu@hotmail.com";
        try
        {
            this.fixture2
                .facultad
                .modificarAlumno(this.fixture2
                                     .facultad
                                     .buscarAlumnoPorNombre(anterior.getNombre(),anterior.getApellido())
                                     .get(0), apellidoNuevo, nombreNuevo, direNueva, mailNuevo);
            
            assertTrue("Deberian coincidir los atributos modificados",anterior.getApellido().equals(apellidoNuevo)&&anterior.getNombre().equals(nombreNuevo)&&anterior.getDomicilio().equals(direNueva)&&anterior.getMail().equals(mailNuevo));
        } catch (MailInvalidoException e)
        {
            fail("No se deberia lanzar esta excepcion ya que el mail modificado es valido");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar esta excepcion ya que la entidad a modificar existe");
        }
    }
    
    //Se testea la modificacion de un alumno inexistente con los datos de modificacion validos
    @Test
    public void modificarAlumnoTest2()
    {
        Alumno anterior=new Alumno("ALU0002","Ponce","Emanuel","falucho","mamm@live");
        String nombreNuevo="Emanuel", apellidoNuevo="Ponce",direNueva="Tejedor 123",mailNuevo="manu@hotmail.com";
        try
        {
            this.fixture2
                .facultad
                .modificarAlumno(anterior, apellidoNuevo, nombreNuevo, direNueva, mailNuevo);
            fail("Se deberia haber lanzado la exception NoExisteEntidadException");
        } catch (MailInvalidoException e)
        {
            fail("No se deberia lanzar esta excepcion ya que el mail modificado es valido");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(anterior));
        }
    }
    //Se testea la modificacion de un alumno existente con los datos de modificacion invalidos
    @Test
    public void modificarAlumnoTest3()
    {
        Alumno anterior=this.fixture2.facultad.buscarAlumnoPorNombre("Juan", "Pico").get(0);
        String nombreNuevo="Emanuel", apellidoNuevo="Ponce",direNueva="Tejedor 123",mailNuevo="manuhotmail.com";
        try
        {
            this.fixture2
                .facultad
                .modificarAlumno(this.fixture2
                                     .facultad
                                     .buscarAlumnoPorNombre(anterior.getNombre(),anterior.getApellido())
                                     .get(0), apellidoNuevo, nombreNuevo, direNueva, mailNuevo);
            fail("Se deberia lanzar MailInvalidoException");
        } catch (MailInvalidoException e)
        {
            assertTrue("Error en la exception MailInvalidoException",e.getMail().equals(mailNuevo));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar esta excepcion ya que la entidad a modificar existe");
        }
    }
    
    //Se testea la modificacion de un profesor existente con los datos de modificacion validos
    @Test
    public void modificarProfesorTest1()
    {
        Profesor anterior=this.fixture2.facultad.buscarProfesorPorNombre("Guillermo", "Laxurri").get(0);
        String nombreNuevo="Emanuel", apellidoNuevo="Ponce",direNueva="Tejedor 123",mailNuevo="manu@hotmail.com", telNuevo="223332";
        try
        {
            this.fixture2
                .facultad
                .modificarProfesor(this.fixture2
                                     .facultad
                                     .buscarProfesorPorNombre(anterior.getNombre(),anterior.getApellido())
                                     .get(0), apellidoNuevo, nombreNuevo, direNueva, mailNuevo,telNuevo);
            
            assertTrue("Deberian coincidir los atributos modificados",anterior.getApellido().equals(apellidoNuevo)&&anterior.getNombre().equals(nombreNuevo)&&anterior.getDomicilio().equals(direNueva)&&anterior.getMail().equals(mailNuevo));
        } catch (MailInvalidoException e)
        {
            fail("No se deberia lanzar esta excepcion ya que el mail modificado es valido");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar esta excepcion ya que la entidad a modificar existe");
        }
    }
    
    //Se testea la modificacion de un profesor inexistente con los datos de modificacion validos
    @Test
    public void modificarProfesorTest2()
    {
        Profesor anterior=new Profesor("PRO0002","Ponce","Emanuel","falucho","mamm@live","223323");
        String nombreNuevo="Emanuel", apellidoNuevo="Ponce",direNueva="Tejedor 123",mailNuevo="manu@hotmail.com",telNuevo="44434";
        try
        {
            this.fixture2
                .facultad
                .modificarProfesor(anterior, apellidoNuevo, nombreNuevo, direNueva, mailNuevo, telNuevo);
            fail("Se deberia haber lanzado la exception NoExisteEntidadException");
        } catch (MailInvalidoException e)
        {
            fail("No se deberia lanzar esta excepcion ya que el mail modificado es valido");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException",e.getEntidad().equals(anterior));
        }
    }
    //Se testea la modificacion de un profesor existente con los datos de modificacion invalidos
    @Test
    public void modificarProfesorTest3()
    {
        Profesor anterior=this.fixture2.facultad.buscarProfesorPorNombre("Guillermo", "Laxurri").get(0);
        String nombreNuevo="Emanuel", apellidoNuevo="Ponce",direNueva="Tejedor 123",mailNuevo="manuhotmail.com", telNuevo="232323";
        try
        {
            this.fixture2
                .facultad
                .modificarProfesor(this.fixture2
                                     .facultad
                                     .buscarProfesorPorNombre(anterior.getNombre(),anterior.getApellido())
                                     .get(0), apellidoNuevo, nombreNuevo, direNueva, mailNuevo, telNuevo);
            fail("Se deberia lanzar MailInvalidoException");
        } catch (MailInvalidoException e)
        {
            assertTrue("Error en la exception MailInvalidoException",e.getMail().equals(mailNuevo));
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar esta excepcion ya que la entidad a modificar existe");
        }
    }
    
    //Se testea agregar una competencia valida a un profesor valido
    @Test
    public void agregarCompetenciaProfesorTest1()
    {
        try
        {
            Profesor p=this.fixture2.facultad.buscarProfesorPorNombre("Guillermo", "Laxurri").get(0);
            Asignatura a=this.fixture2.facultad.buscarAsignaturaPorNombre("Analisis 1").get(0);
            this.fixture2
                .facultad
                .agregarCompetenciaProfesor(p, a);
            assertTrue("No se agregó la competencia al profesor correctamente",p.getCompetencia().containsKey(a.getId()));
        } catch (AsignaturaYaRegistradaEnProfesorException e)
        {
            fail("No se deberia lanzar la exception AsignaturaYaRegistradaEnProfesorException");
        } catch (NoExisteEntidadException e)
        {
            fail("No se deberia lanzar la exception NoExisteEntidadException");
        }
    }
    
    
    //Se testea agregar una competencia invalida (inexistente) a un profesor valido
    @Test
    public void agregarCompetenciaProfesorTest2()
    {
        Profesor p=this.fixture2.facultad.buscarProfesorPorNombre("Guillermo", "Laxurri").get(0);
        Asignatura a=new Asignatura ("ASI0002","Programacion 3");
        try
        {
            this.fixture2
                .facultad
                .agregarCompetenciaProfesor(p, a);
            fail("Se deberia lanzar la exception NoExisteEntidadException");
        } catch (AsignaturaYaRegistradaEnProfesorException e)
        {
            fail("No se deberia lanzar la exception AsignaturaYaRegistradaEnProfesorException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException", e.getEntidad().equals(a));
        }
    }  
    
    //Se testea agregar una competencia valida a un profesor inexistente
    @Test
    public void agregarCompetenciaProfesorTest3CB()
    {
        Profesor p=new Profesor("PRO0999","Fernandez","Mariano","Tejedor 1223","fernandez@gmail","22333223");
        Asignatura a=this.fixture2.facultad.buscarAsignaturaPorNombre("Analisis 1").get(0);
        try
        {
            this.fixture2
                .facultad
                .agregarCompetenciaProfesor(p, a);
            fail("Se deberia lanzar la exception NoExisteEntidadException");
        } catch (AsignaturaYaRegistradaEnProfesorException e)
        {
            fail("No se deberia lanzar la exception AsignaturaYaRegistradaEnProfesorException");
        } catch (NoExisteEntidadException e)
        {
            assertTrue("Error en la exception NoExisteEntidadException", e.getEntidad().equals(p));
        }
    } 
}
