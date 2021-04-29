package edu.upc.dsa;

import edu.upc.dsa.Classes.MarcaVacunas;
import edu.upc.dsa.Classes.Seguimiento;
import edu.upc.dsa.Classes.UserCovid;
import edu.upc.dsa.Classes.Vacuna;

import java.util.List;

public interface Covid19Manager {
    public void PonerVacuna(Vacuna v);
    public List<Vacuna> ListaVacunas();
    public List<MarcaVacunas> ListaMarcaVacunasNum();
    public void AñadirSeguimiento(String user, Seguimiento s);
    public List<Seguimiento> SegUser(String user);
    public void clear();
    public void dirty();
    public boolean isDirty();
    public void addUser(UserCovid user);
    public void addMarca(MarcaVacunas m);

}
