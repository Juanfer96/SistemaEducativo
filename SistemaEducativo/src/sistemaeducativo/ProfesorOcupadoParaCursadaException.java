package sistemaeducativo;

public class ProfesorOcupadoParaCursadaException extends Exception{
    private Profesor p;
    private Cursada c;
    
    public ProfesorOcupadoParaCursadaException(Profesor p, Cursada c) {
        this.c=c;
        this.p=p;
    }


    public Profesor getP() {
        return p;
    }

    public Cursada getC() {
        return c;
    }
}
