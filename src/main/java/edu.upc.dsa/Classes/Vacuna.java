package edu.upc.dsa.Classes;

public class Vacuna {
    private String idUser;
    private String idVac;
    private int date;

    public Vacuna(String idUser, String idVac, int date) {
        this.idUser = idUser;
        this.idVac = idVac;
        this.date = date;
    }
    public Vacuna() {

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdVac() {
        return idVac;
    }

    public void setIdVac(String idVac) {
        this.idVac = idVac;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String toString() {
        return "Vacuna [Marca= "+idVac+ " name = " + idUser + " fecha = "+ date+"]";
    }
}
