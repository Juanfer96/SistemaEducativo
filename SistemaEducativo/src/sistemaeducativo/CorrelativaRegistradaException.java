package sistemaeducativo;

public class CorrelativaRegistradaException extends Exception
{
    Asignatura correlativa;
    Asignatura original;
    
    public CorrelativaRegistradaException(Asignatura correlativa, Asignatura original)
    {
        this.correlativa=correlativa;
        this.original=original;
    }


    public Asignatura getCorrelativa()
    {
        return correlativa;
    }

    public Asignatura getOriginal()
    {
        return original;
    }

}
