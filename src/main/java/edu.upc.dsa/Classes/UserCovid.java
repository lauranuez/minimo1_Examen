package edu.upc.dsa.Classes;

import java.util.ArrayList;
import java.util.List;

public class UserCovid {
    private String name;
    private int id;
    private List<Seguimiento> listSeg = new ArrayList<>();

    public UserCovid(String name) {
        this.name = name;
    }
    public UserCovid() {
    }

    public void addSeg(Seguimiento s)
    {
        listSeg.add(s);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Seguimiento> getListSeg() {
        return listSeg;
    }

    public void setListSeg(List<Seguimiento> listSeg) {
        this.listSeg = listSeg;
    }

    public String toString() {
        return "User [Name= "+name+ " id = "+ id+"]";
    }
}
