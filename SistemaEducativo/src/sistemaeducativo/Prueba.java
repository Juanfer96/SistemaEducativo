package sistemaeducativo;

import java.time.DateTimeException;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Iterator;

public class Prueba {
    public Prueba() {
        super();
    }

    public static void main(String[] args) {
        Facultad f=new Facultad();
        f.agregarAlumno("Ponce", "Emanuel", "Tejefor", "manumanu");
        f.agregarAlumno("Alvarez", "Javier", "Tejefor", "manumanu");
        f.agregarAlumno("Fernandez", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("pepeh", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("pepeg", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("pepef", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("peper", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("pepe", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("pepwe", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("pepe", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("pepe", "Francisco", "Tejefor", "manumanu");
        ArrayList a=f.buscarAlumnoPorNombre("Francisco", "pepe");
        Iterator it = a.iterator();
        Alumno alum;
        while(it.hasNext()) {
            alum=(Alumno)it.next();
            System.out.println("Nombre: "+alum.getNombre()+" Apellido : "+alum.getApellido()+" Legajo: "+alum.getLegajo());
        }
        try{
           LocalTime hora=LocalTime.of(28, 30);
            System.out.printf("En este momento son las %d horas con %d minutos y %d segundos\n", hora.getHour(),  
                   hora.getMinute(), hora.getSecond());
        } 
        catch(DateTimeException e) {
            System.out.print("Hora erronea");
        }
        
        String prueba="abs@djas";
        if(prueba.contains("@") && prueba.indexOf("@")<prueba.length()-1 && prueba.indexOf("@")>0) {
            System.out.println("Correcto");
        }
        else{
            System.out.println("Incorrecto");
        }
    }
}
