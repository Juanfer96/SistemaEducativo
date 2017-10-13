package sistemaeducativo;

import java.util.ArrayList;
import java.util.Iterator;

public class Prueba {
    public Prueba() {
        super();
    }

    public static void main(String[] args) {
        Facultad f=new Facultad();
        f.agregarAlumno("ALU123", "Ponce", "Emanuel", "Tejefor", "manumanu");
        f.agregarAlumno("ALU321", "Alvarez", "Javier", "Tejefor", "manumanu");
        f.agregarAlumno("ALU111", "Medina", "Francisco", "Tejefor", "manumanu");
        f.agregarAlumno("ALU111", "pepe", "Francisco", "Tejefor", "manumanu");
        ArrayList a=f.buscarAlumnoPorNombre("Francisco", "Medina");
        Iterator it = a.iterator();
        Alumno alum;
        while(it.hasNext()) {
            alum=(Alumno)it.next();
            System.out.println("Nombre: "+alum.getNombre()+" Apellido : "+alum.getApellido()+" Legajo: "+alum.getLegajo());
        }
        
        
    }
}
