package sistemaeducativo;

public class PeriodoInvalidoException extends Exception{
    private String periodo;
    public PeriodoInvalidoException(String periodo) {
        this.periodo=periodo;
    }

    public String getPeriodo() {
        return periodo;
    }
}
