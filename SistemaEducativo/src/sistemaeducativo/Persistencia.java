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
            facultad = (Facultad)decoder.readObject();
            facultad.setPROXLEGAJOALUM((Integer) decoder.readObject());
            facultad.setPROXLEGAJOPROF((Integer) decoder.readObject());
            facultad.setPROXIDCURSADA((Integer) decoder.readObject());
            facultad.setPROXIDASIGNATURA((Integer) decoder.readObject());
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
        encoder.writeObject(facultad);
        encoder.writeObject(facultad.getPROXLEGAJOALUM());
        encoder.writeObject(facultad.getPROXLEGAJOPROF());
        encoder.writeObject(facultad.getPROXIDCURSADA());
        encoder.writeObject(facultad.getPROXIDASIGNATURA());
        encoder.writeObject(facultad.getAlumnos());
        encoder.writeObject(facultad.getProfesores());
        encoder.writeObject(facultad.getAsignaturas());
        encoder.writeObject(facultad.getCursadas());
        encoder.close();
    }
}