package sistemaeducativo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Persistencia
{
    private static final String SERIALIZED_FILE_NAME = "SistemaPGA.xml";
    
    public Persistencia()
    {
        super();
    }
    
    public static Facultad leerArchivo()
    {
        Facultad facultad;
        
        if (new File(SERIALIZED_FILE_NAME).exists())
        {
            XMLDecoder decoder = null;
            try
            {
                decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
            }
            catch(Exception e)
            {
                System.out.println("Error al intentar leer el archivo.");
            }
            Fecha.setLUNES((Integer) decoder.readObject());
            Fecha.setMARTES((Integer) decoder.readObject());
            Fecha.setMIERCOLES((Integer) decoder.readObject());
            Fecha.setJUEVES((Integer) decoder.readObject());
            Fecha.setVIERNES((Integer) decoder.readObject());
            Fecha.setSABADO((Integer) decoder.readObject());
            Fecha.setDOMINGO((Integer) decoder.readObject());
            Facultad.setPROXLEGAJOALUM((Integer) decoder.readObject());
            Facultad.setPROXLEGAJOPROF((Integer) decoder.readObject());
            Facultad.setPROXIDCURSADA((Integer) decoder.readObject());
            Facultad.setPROXIDASIGNATURA((Integer) decoder.readObject());
            facultad = (Facultad)decoder.readObject();
            facultad.setAlumnos((TreeMap<String, Alumno>)decoder.readObject());
            facultad.setProfesores((TreeMap<String, Profesor>)decoder.readObject());
            facultad.setAsignaturas((TreeMap<String, Asignatura>)decoder.readObject());
            facultad.setCursadas((TreeMap<String, Cursada>)decoder.readObject());
            decoder.close();
        }
        else // Si el archivo no existe es por ser la primer corrida del sistema. Se debe por lo tanto crear una Facultad
        {
            facultad = Facultad.getInstancia();
        }
        return facultad;
    }
    
    public static void guardarArchivo(Facultad facultad)
    {
        XMLEncoder encoder = null;
        try
        {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
        }
        catch(Exception e)
        {
            System.out.println("Error al intentar guardar el archivo.");
        }
        encoder.writeObject(Fecha.getLUNES());
        encoder.writeObject(Fecha.getMARTES());
        encoder.writeObject(Fecha.getMIERCOLES());
        encoder.writeObject(Fecha.getJUEVES());
        encoder.writeObject(Fecha.getVIERNES());
        encoder.writeObject(Fecha.getSABADO());
        encoder.writeObject(Fecha.getDOMINGO());
        encoder.writeObject(Facultad.getPROXLEGAJOALUM());
        encoder.writeObject(Facultad.getPROXLEGAJOPROF());
        encoder.writeObject(Facultad.getPROXIDCURSADA());
        encoder.writeObject(Facultad.getPROXIDASIGNATURA());
        encoder.writeObject(facultad);
        encoder.writeObject(facultad.getAlumnos());
        encoder.writeObject(facultad.getProfesores());
        encoder.writeObject(facultad.getAsignaturas());
        encoder.writeObject(facultad.getCursadas());
        encoder.close();
    }
}