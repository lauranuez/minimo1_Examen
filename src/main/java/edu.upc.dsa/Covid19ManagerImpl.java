package edu.upc.dsa;
import edu.upc.dsa.Classes.*;
import org.apache.log4j.Logger;

import java.util.*;

public class Covid19ManagerImpl implements Covid19Manager{

    private HashMap<String, UserCovid> hmUsers;
    private List<Vacuna> listVacunacion;
    private ArrayList<MarcaVacunas> listMarcas;
    private int idUser = 0;

    final static Logger logger = Logger.getLogger(Covid19ManagerImpl.class);
    private boolean dirty=false;

    private static Covid19Manager instance;

    private Covid19ManagerImpl() {
        listVacunacion = new ArrayList<>();
        hmUsers = new HashMap<String,UserCovid>();
        listMarcas = new ArrayList<>();
    }
    public static Covid19Manager getInstance() {
        if (instance==null)
        {
            instance = new Covid19ManagerImpl();
        }
        return instance;
    }
    @Override
    public void PonerVacuna(Vacuna v) {
        int error = 0;
        logger.info("Poner Vacuna a " + v.getIdUser());
        listVacunacion.add(v);
        logger.info("Vacuna puesta correctamente ");
        for (int i=0; i<listMarcas.size(); i++)
        {
            if (listMarcas.get(i).getName().equals(v.getIdVac()))
            {
                listMarcas.get(i).AumentarNum();
                logger.info("Añadido una vacuna a la marca " + v.getIdVac());
                error = 1;
            }
        }
        if (error==0)
            logger.warn("No existe la marca de la vacuna");
    }

    @Override
    public List<Vacuna> ListaVacunas() {
        logger.info("ListaVacunas() ");
        if (listVacunacion.size()==0)
        {
            logger.warn("No se ha aplicado ninguna vacuna");
        }
        else {
            Collections.sort(listVacunacion, new Comparator<Vacuna>() {
                @Override
                public int compare(Vacuna v1, Vacuna v2) {
                    return v1.getDate()-v2.getDate();
                }
            });
            Collections.sort(listVacunacion, new Comparator<Vacuna>() {
                @Override
                public int compare(Vacuna v1, Vacuna v2) {
                    return v1.getIdVac().compareTo(v2.getIdVac());
                }

            });
            logger.info("La primera vacuna de la marca " + listVacunacion.get(0).getIdVac() + " se ha puesto a " + listVacunacion.get(0).getIdUser());
        }
        return listVacunacion;
    }
    @Override
    public List<MarcaVacunas> ListaMarcaVacunasNum() {
        logger.info("Lista de marcas ordenado desc. por el numero de vacunas");
        if (listMarcas.size()==0)
        {
            logger.warn("No hay ninguna marca registrada");
        }
        else {
            Collections.sort(listMarcas, new Comparator<MarcaVacunas>() {
                @Override
                public int compare(MarcaVacunas m1, MarcaVacunas m2) {
                    return m2.getNumVac() - m1.getNumVac();
                }
            });
            logger.info("La marca con menor numero es " + listMarcas.get(0).getName());
        }
        return listMarcas;
    }
    @Override
    public void AñadirSeguimiento(String user, Seguimiento s) {
        logger.info("añadir seguimiento a " + user);
        if (hmUsers.get(user)==null)
        {
            logger.warn("No existe ese usuario");
        }
        else {
            hmUsers.get(user).addSeg(s);
            logger.info("Seguimiento añadido");
            logger.info("fecha: " + s.getFecha());
        }
    }
    @Override
    public List<Seguimiento> SegUser(String user) {
        logger.info("Seguimiento del usuario " + user);
        if (hmUsers.get(user).getListSeg().size() == 0)
            logger.warn("No hay seguimiento para ese usuario");
        else
            logger.info("la descripcion del primer seguimiento del usuario es " + hmUsers.get(user).getListSeg().get(0).getDescripcion());
        return hmUsers.get(user).getListSeg();

    }
    public void addMarca(MarcaVacunas m){
        logger.info("nueva marca " + m.getName());
        listMarcas.add(m);
        logger.info("marca añadida a la lista");
    }

    public void addUser(UserCovid user){
        logger.info("nuevo usuario " + user.getName());
        user.setId(idUser);
        idUser++;
        hmUsers.put(user.getName(),user);
        logger.info("usuario añadido con id = " + user.getId());
    }
    public void dirty() {
        this.dirty = true;
    }
    public boolean isDirty(){
        return dirty;
    }
    public void clear(){
        listMarcas.clear();
        hmUsers.clear();
        listVacunacion.clear();
    }
}
