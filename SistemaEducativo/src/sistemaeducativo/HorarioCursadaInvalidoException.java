package sistemaeducativo;

import java.time.LocalTime;

public class HorarioCursadaInvalidoException extends Exception{
    
    LocalTime horaInicio, horaFin;
    public HorarioCursadaInvalidoException(LocalTime horaInicio, LocalTime horaFin) {
        this.horaFin=horaFin;
        this.horaInicio=horaInicio;
    }


    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
}
