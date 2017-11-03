package sistemaeducativo;

public class HoraInvalidaException extends Exception{
    private String hora;
    public HoraInvalidaException(String hora) {
        this.hora=hora;
    }


    public String getHora() {
        return hora;
    }
}
