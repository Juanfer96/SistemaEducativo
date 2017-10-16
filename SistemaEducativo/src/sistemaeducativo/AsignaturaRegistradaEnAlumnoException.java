package sistemaeducativo;

public class AsignaturaRegistradaEnAlumnoException extends Exception{
    Alumno a;
    Asignatura asig;
    
    public AsignaturaRegistradaEnAlumnoException(Alumno a, Asignatura asig) {
        this.a=a;
        this.asig=asig;
    }


    public Alumno getA() {
        return a;
    }

    public Asignatura getAsig() {
        return asig;
    }
}
