package sistemaeducativo;

import java.time.LocalTime;

public class Fecha {
    public static int LUNES=0;
    public static int MARTES=1;
    public static int MIERCOLES=2;
    public static int JUEVES=3;
    public static int VIERNES=4;
    public static int SABADO=5;
    public static int DOMINGO=6;
    
    private int dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    
    public Fecha(int dia, int horaInicio, int minInicio, int horaFin, int minFin) {
        this.dia=dia;
        this.horaInicio=LocalTime.of(horaInicio, minInicio);
        this.horaFin=LocalTime.of(horaFin, minFin);
    }


    public static int getLUNES() {
        return LUNES;
    }

    public static int getMARTES() {
        return MARTES;
    }

    public static int getMIERCOLES() {
        return MIERCOLES;
    }

    public static int getJUEVES() {
        return JUEVES;
    }

    public static int getVIERNES() {
        return VIERNES;
    }

    public static int getSABADO() {
        return SABADO;
    }

    public static int getDOMINGO() {
        return DOMINGO;
    }

    public int getDia() {
        return dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
}
