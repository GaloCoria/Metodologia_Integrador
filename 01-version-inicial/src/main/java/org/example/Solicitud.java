package org.example;

public class Solicitud {
    public int id;
    public Estudiante est;
    public Responsable resp;
    public String t; 
    public int p;
    public String e;
    public String desc;


    public boolean puedeAsignarseA(Responsable r) {
        if (r != null && r.activo == true) {
            if (this.t.equals("1") && r.area.equals("Alumnos")) return true;
            if (this.t.equals("2") && r.area.equals("Academica")) return true;
            if (this.t.equals("3") && r.area.equals("Academica")) return true;
        }
        return false;
    }
}
