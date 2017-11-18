package test;

import sistemaeducativo.Facultad;
import sistemaeducativo.MailInvalidoException;

public class TestFixture2
{ 
    Facultad facultad=new Facultad();
    public TestFixture2()
    {
    }

    public void setUp()
    {
        this.facultad.agregarAsignatura("Analisis 1");
        try
        {
            this.facultad.agregarAlumno("Pico", "Juan", "Falucho 3433", "boca@gmail.com");
        } catch (MailInvalidoException e)
        {
        }
        try
        {
            this.facultad.agregarProfesor("Laxurri", "Guillermo", "Costa 1234", "guille@gamil.com", "15523432234");
        } catch (MailInvalidoException e)
        {
        }
        
    }
    
    public void tearDown()
    {
    }
}
