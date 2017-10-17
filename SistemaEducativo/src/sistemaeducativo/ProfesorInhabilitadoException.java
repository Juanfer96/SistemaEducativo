package sistemaeducativo;

public class ProfesorInhabilitadoException extends Exception{
    private Profesor p;
    private Cursada c;
    
    public ProfesorInhabilitadoException(Profesor p, Cursada c) {
        this.p=p;
        this.c=c;
    }


    public Profesor getP() {
        return p;
    }

    public Cursada getC() {
        return c;
    }
}
