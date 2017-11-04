package sistemaeducativo;

public class AsignaturaNoAprobableException extends Exception{
    private Asignatura asignatura;
    private Alumno alumno;


    public AsignaturaNoAprobableException(Asignatura asignatura, Alumno alumno) {
        super();
        this.asignatura = asignatura;
        this.alumno = alumno;
    }


    public Asignatura getAsignatura() {
        return asignatura;
    }

    public Alumno getAlumno() {
        return alumno;
    }
}
