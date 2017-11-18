package test;

import sistemaeducativo.Alumno;
import sistemaeducativo.AlumnoInhabilitadoException;
import sistemaeducativo.AlumnoOcupadoParaCursadaException;
import sistemaeducativo.AlumnoRegistradoEnCursadaException;
import sistemaeducativo.Asignatura;
import sistemaeducativo.AsignaturaAprobadaYaRegistradaException;
import sistemaeducativo.AsignaturaNoAprobableException;
import sistemaeducativo.AsignaturaYaRegistradaEnProfesorException;
import sistemaeducativo.CorrelativaRegistradaException;
import sistemaeducativo.Cursada;
import sistemaeducativo.Facultad;
import sistemaeducativo.Fecha;
import sistemaeducativo.FormatoHoraException;
import sistemaeducativo.HorarioCursadaInvalidoException;
import sistemaeducativo.HorarioCursadaSuperpuestaException;
import sistemaeducativo.MailInvalidoException;
import sistemaeducativo.NoExisteEntidadException;
import sistemaeducativo.PeriodoInvalidoException;
import sistemaeducativo.Profesor;
import sistemaeducativo.ProfesorInhabilitadoException;
import sistemaeducativo.ProfesorOcupadoParaCursadaException;
import sistemaeducativo.ProfesorRegistradoEnCursadaException;

public class TestFixture4
{
    Facultad facultad;
    public TestFixture4()
    {
    }

    public void setUp()
    {
        
        try
        {
            this.facultad=new Facultad();
            Asignatura a1=this.facultad.agregarAsignatura("mateA");
            Asignatura a2=this.facultad.agregarAsignatura("mateB");
            Asignatura a3=this.facultad.agregarAsignatura("mateC");
            Alumno alum1 =this.facultad.agregarAlumno("Pico", "Juan", "falucho 3433", "boca@gmail.com");
            Alumno alum2=this.facultad.agregarAlumno("Ponce", "Emanuel", "falucho 2222", "ponce@gmail.com");
            Alumno alum3=this.facultad.agregarAlumno("Nucci", "Manu", "Colon 2222", "manu@gmail.com");
            
            Profesor p1=this.facultad.agregarProfesor("Guccione", "Leonel", "adada", "leonel@gmail.com", "222222");
            Profesor p2=this.facultad.agregarProfesor("Evans", "Felipe", "adadada", "adadad@ada", "2323232332");
            Profesor p3=this.facultad.agregarProfesor("Rico", "Jorge", "dadada", "adada@adada", "333333");
            
            Cursada c1=this.facultad.agregarCursada(a1, "01-2017");
            Cursada c2=this.facultad.agregarCursada(a2, "01-2017");
            Cursada c3=this.facultad.agregarCursada(a3, "01-2017");
            this.facultad.agregarHorarioCursada(c1, 8, 0, 10, 0,Fecha.LUNES);
            this.facultad.agregarHorarioCursada(c2, 8, 0, 10, 0,Fecha.MARTES);
            this.facultad.agregarHorarioCursada(c3, 8, 0, 10, 0,Fecha.MARTES);
            
            this.facultad.agregarCorrelativaAsignatura(a2, a1);
            this.facultad.agregarAlumnoEnCursada(alum1, c1);
            this.facultad.aprobarAlumnoAsignatura(alum1, a1);
            this.facultad.agregarAlumnoEnCursada(alum1, c2);
            
            this.facultad.agregarCompetenciaProfesor(p1, a2);
            this.facultad.agregarCompetenciaProfesor(p1, a3);
            this.facultad.agregarProfesorEnCursada(p1, c2);
            
            this.facultad.agregarCompetenciaProfesor(p2, a1);
        } catch (MailInvalidoException e)
        {
        }
        catch (NoExisteEntidadException e)
        {
        } catch (PeriodoInvalidoException e)
        {
        }
        catch (FormatoHoraException | HorarioCursadaInvalidoException | HorarioCursadaSuperpuestaException e)
        {
        } catch (CorrelativaRegistradaException e)
        {
        } catch (AlumnoInhabilitadoException | AlumnoOcupadoParaCursadaException | AlumnoRegistradoEnCursadaException e)
        {
        } catch (AsignaturaAprobadaYaRegistradaException | AsignaturaNoAprobableException e)
        {
        } catch (AsignaturaYaRegistradaEnProfesorException e)
        {
        } catch (ProfesorInhabilitadoException | ProfesorOcupadoParaCursadaException |
                 ProfesorRegistradoEnCursadaException e)
        {
        }
    }

    public void tearDown()
    {
    }
}
