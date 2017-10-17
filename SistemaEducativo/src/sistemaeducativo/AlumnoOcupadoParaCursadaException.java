package sistemaeducativo;

public class AlumnoOcupadoParaCursadaException extends Exception{
    private Alumno a;
    private Cursada c;
    
    public AlumnoOcupadoParaCursadaException(Alumno a, Cursada c) {
        this.a=a;
        this.c=c;
    }


    public Alumno getA() {
        return a;
    }

    public Cursada getC() {
        return c;
    }
}
