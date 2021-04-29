package edu.upc.dsa.Classes;

public class MarcaVacunas {
    private String name;
    private int numVac=0;

    public MarcaVacunas(String name) {
        this.name = name;
    }
    public MarcaVacunas() {
    }

    public void AumentarNum()
    {
        this.numVac++;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumVac() {
        return numVac;
    }

    public void setNumVac(int numVac) {
        this.numVac = numVac;
    }
    public String toString() {
        return "User [Name= "+name+ " numero de vacunas puestas = "+ numVac+" ]";
    }
}
