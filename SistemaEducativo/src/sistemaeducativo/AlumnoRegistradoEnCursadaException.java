package sistemaeducativo;

public class AlumnoRegistradoEnCursadaException extends Exception{
    private Alumno a;
    private Cursada c;
    
    public AlumnoRegistradoEnCursadaException(Alumno a, Cursada c) {
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
