package test;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;

import sistemaeducativo.Facultad;
import sistemaeducativo.Fecha;
import sistemaeducativo.FormatoHoraException;
import sistemaeducativo.HorarioCursadaInvalidoException;
import sistemaeducativo.HorarioCursadaSuperpuestaException;
import sistemaeducativo.MailInvalidoException;
import sistemaeducativo.NoExisteEntidadException;
import sistemaeducativo.PeriodoInvalidoException;

public class TestFixture3
{
    Facultad facultad;
    public TestFixture3()
    {
    }

    public void setUp()
    {
        this.facultad=new Facultad();
        this.facultad.agregarAsignatura("mateA");
        this.facultad.agregarAsignatura("mateB");
        this.facultad.agregarAsignatura("mateC");
        try
        {
            this.facultad.agregarAlumno("Pico", "Juan", "falucho 3433", "boca@gmail.com");
            this.facultad.agregarAlumno("Ponce", "Emanuel", "falucho 2222", "ponce@gmail.com");
            this.facultad.agregarAlumno("Nucci", "Manu", "Colon 2222", "manu@gmail.com");
            
            this.facultad.agregarProfesor("Guccione", "Leonel", "adada", "leonel@gmail.com", "222222");
            this.facultad.agregarProfesor("Evans", "Felipe", "adadada", "adadad@ada", "2323232332");
            this.facultad.agregarProfesor("Rico", "Jorge", "dadada", "adada@adada", "333333");
            
            this.facultad.agregarCursada(this.facultad
                                             .buscarAsignaturaPorNombre("mateA")
                                             .get(0), "01-2017");
            this.facultad.agregarCursada(this.facultad
                                             .buscarAsignaturaPorNombre("mateB")
                                             .get(0), "02-2017");
            this.facultad.agregarCursada(this.facultad
                                             .buscarAsignaturaPorNombre("mateC")
                                             .get(0), "01-2017");
            this.facultad.agregarHorarioCursada(this.facultad
                                                    .buscarCursadaPorNombre("mateA")
                                                    .get(0), 8, 0, 10, 0,Fecha.LUNES);
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
        }
    }

    public void tearDown()
    {
    }
}
