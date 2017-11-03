package sistemaeducativo;

public class NoExisteEntidadException extends Exception{
    private Object entidad;
    public NoExisteEntidadException(Object entidad) {
        this.entidad=entidad;
    }


    public Object getEntidad() {
        return entidad;
    }
}
