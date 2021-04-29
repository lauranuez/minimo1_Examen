package edu.upc.dsa;

import edu.upc.dsa.Classes.MarcaVacunas;
import edu.upc.dsa.Classes.Seguimiento;
import edu.upc.dsa.Classes.UserCovid;
import edu.upc.dsa.Classes.Vacuna;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Covid19ManagerTest {

    Covid19Manager cm = Covid19ManagerImpl.getInstance();
    Vacuna v1;
    Vacuna v2;
    Vacuna v3;
    Vacuna v4;

    @Before
    public void init(){
        MarcaVacunas m1 = new MarcaVacunas("Pfizer");
        MarcaVacunas m2 = new MarcaVacunas("Moderna");
        MarcaVacunas m3 = new MarcaVacunas("Astrazeneca");

        cm.addMarca(m1);
        cm.addMarca(m2);
        cm.addMarca(m3);

        UserCovid u1 = new UserCovid("Juan");
        UserCovid u2 = new UserCovid("Maria");
        UserCovid u3 = new UserCovid("Fer");
        UserCovid u4 = new UserCovid("Sara");

        cm.addUser(u1);
        cm.addUser(u2);
        cm.addUser(u3);
        cm.addUser(u4);

        v1= new Vacuna("Sara", "Moderna",1);
        v2= new Vacuna("Maria", "Astrazeneca",1);
        v3= new Vacuna("Juan", "Pfizer",1);
        v4= new Vacuna("Fer", "Moderna",2);

        cm.PonerVacuna(v1);
        cm.PonerVacuna(v4);
        cm.PonerVacuna(v3);

    }

    @After
    public void reset(){
        cm.clear();
    }

    @Test
    public void test1(){
        //test para aplicar una vacuna sobre un usuario
        cm.PonerVacuna(v2);
        Assert.assertEquals(cm.ListaVacunas().get(0).getIdUser(), "Maria" );
    }
    @Test
    public void test2(){
        // test para añadir un seguimiento sobre un usuario
        Seguimiento s = new Seguimiento ( "el paciente ha tenido fiebre");
        cm.AñadirSeguimiento("Juan",s);
        List<Seguimiento> listSeg = cm.SegUser("Juan");
        Assert.assertEquals(listSeg.get(0).getDescripcion(), "el paciente ha tenido fiebre" );
    }

}
