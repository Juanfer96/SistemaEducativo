package sistemaeducativo;

import java.time.LocalTime;

public class Fecha {
    public static final int LUNES=0;
    public static final int MARTES=1;
    public static final int MIERCOLES=2;
    public static final int JUEVES=3;
    public static final int VIERNES=4;
    public static final int SABADO=5;
    public static final int DOMINGO=6;
    
    private int dia;
    private String horaInicio;
    private String horaFin;
    
    public Fecha(int dia, int horaInicio, int minInicio, int horaFin, int minFin) {
        this.dia=dia;
        this.horaInicio = String.format("%02d", horaInicio)+":"+String.format("%02d", minInicio);
        this.horaFin= String.format("%02d", horaFin)+":"+String.format("%02d", minFin);
    }
    
    public Fecha(){
        super();
    }


   

    public int getDia() {
        return dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }


   

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    /**
     *
     * @param fecha
     * @return true si se superpone this con fecha. false en caso contrario
     */
    public boolean superpone(Fecha fecha) {
        if(this.horaInicio.compareTo(fecha.getHoraInicio())==0 || this.horaInicio.compareTo(fecha.getHoraInicio())>0 && this.horaInicio.compareTo(fecha.getHoraFin())<0 || this.horaFin.compareTo(fecha.getHoraFin())<0 && this.horaFin.compareTo(fecha.getHoraInicio())>0 || this.horaInicio.compareTo(fecha.getHoraInicio())<0 && this.horaFin.compareTo(fecha.getHoraFin())>0 ) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        String dia;
        switch(this.dia){
        case LUNES: dia="Lunes";
            break;
        case MARTES: dia="Martes";
            break;
        case MIERCOLES: dia="Miercoles";
            break;
        case JUEVES: dia="Jueves";
            break;
        case VIERNES: dia="Viernes";
            break;
        case SABADO: dia="Sabado";
            break;
        case DOMINGO: dia="Domingo";
            break;
        default: dia=null;
        }
        return dia+" de "+this.getHoraInicio()+" a "+this.getHoraFin();
    }
}
