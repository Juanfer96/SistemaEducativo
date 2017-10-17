package sistemaeducativo;

public class AsignaturaYaRegistradaEnProfesorException extends Exception{
    private Asignatura a;
    private Profesor p;


    public AsignaturaYaRegistradaEnProfesorException(Asignatura a, Profesor p) {
        super();
        this.a = a;
        this.p = p;
    }


    public Asignatura getA() {
        return a;
    }

    public Profesor getP() {
        return p;
    }
}