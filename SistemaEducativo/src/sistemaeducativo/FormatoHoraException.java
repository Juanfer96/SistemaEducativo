package sistemaeducativo;

public class FormatoHoraException extends Exception{
    private int horaInicio,horaFin,minInicio,minFin;

    public FormatoHoraException(int horaInicio, int horaFin, int minInicio, int minFin) {
        super();
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.minInicio = minInicio;
        this.minFin = minFin;
    }


    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public int getMinInicio() {
        return minInicio;
    }

    public int getMinFin() {
        return minFin;
    }


}
