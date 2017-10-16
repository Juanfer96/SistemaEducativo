package sistemaeducativo;

public class ProfesorInhabilitadoParaCursadaException extends Exception{
    private Profesor p;
    private Cursada c;
    
    public ProfesorInhabilitadoParaCursadaException(Profesor p, Cursada c) {
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
