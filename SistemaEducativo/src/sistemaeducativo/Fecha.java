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

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }


    public static void setLUNES(int LUNES) {
        Fecha.LUNES = LUNES;
    }

    public static void setMARTES(int MARTES) {
        Fecha.MARTES = MARTES;
    }

    public static void setMIERCOLES(int MIERCOLES) {
        Fecha.MIERCOLES = MIERCOLES;
    }

    public static void setJUEVES(int JUEVES) {
        Fecha.JUEVES = JUEVES;
    }

    public static void setVIERNES(int VIERNES) {
        Fecha.VIERNES = VIERNES;
    }

    public static void setSABADO(int SABADO) {
        Fecha.SABADO = SABADO;
    }

    public static void setDOMINGO(int DOMINGO) {
        Fecha.DOMINGO = DOMINGO;
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
        case 0: dia="Lunes";
            break;
        case 1: dia="Martes";
            break;
        case 2: dia="Miercoles";
            break;
        case 3: dia="Jueves";
            break;
        case 4: dia="Viernes";
            break;
        case 5: dia="Sabado";
            break;
        case 6: dia="Domingo";
            break;
        default: dia=null;
        }
        return dia+" de "+this.getHoraInicio()+" a "+this.getHoraFin();
    }
}
