package edu.upc.dsa.Classes;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seguimiento {

    private String descripcion;
    private LocalDate date = LocalDate.now();

    public Seguimiento( String descripcion) {
        this.descripcion = descripcion;
    }
    public Seguimiento() {
    }

    public LocalDate getFecha() {
        return date;
    }

    public void setFecha(LocalDate fecha) {
        this.date = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String toString() {
        return "Seguimiento [fecha= "+date+ " descripcion = " + descripcion + "]";
    }

}
