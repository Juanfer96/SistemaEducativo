package sistemaeducativo;

public class AsignaturaAprobadaYaRegistradaException extends Exception{
    private Alumno alumno;
    private Asignatura asignatura;


    public AsignaturaAprobadaYaRegistradaException(Alumno alumno, Asignatura asignatura) {
        this.alumno = alumno;
        this.asignatura = asignatura;
    }


    public Alumno getAlumno() {
        return alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }
}
