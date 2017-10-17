package sistemaeducativo;

public class HorarioCursadaSuperpuestaException extends Exception{
    private Fecha fecha;
    private Cursada cursada;
    
    public HorarioCursadaSuperpuestaException(Fecha fecha, Cursada cursada) {
        this.fecha=fecha;
        this.cursada=cursada;
    }


    public Fecha getFecha() {
        return fecha;
    }

    public Cursada getCursada() {
        return cursada;
    }
}
