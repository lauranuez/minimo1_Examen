package edu.upc.dsa.services;

import edu.upc.dsa.Classes.*;
import edu.upc.dsa.Covid19Manager;
import edu.upc.dsa.Covid19ManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/covid19", description = "Endpoint to Covid19 Service")
@Path("/covid19")
public class Covid19Service {
    private Covid19Manager cm;

    public Covid19Service() {

        this.cm = Covid19ManagerImpl.getInstance();
        if (!this.cm.isDirty() ) {
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
            UserCovid u5 = new UserCovid("Pep");

            cm.addUser(u1);
            cm.addUser(u2);
            cm.addUser(u3);
            cm.addUser(u4);
            cm.addUser(u5);

            Vacuna v1= new Vacuna("Sara", "Moderna",1);
            Vacuna v2= new Vacuna("Maria", "Astrazeneca",1);
            Vacuna v3= new Vacuna("Juan", "Pfizer",1);
            Vacuna v4= new Vacuna("Fer", "Moderna",2);

            cm.PonerVacuna(v4);
            cm.PonerVacuna(v2);
            cm.PonerVacuna(v1);
            cm.PonerVacuna(v3);

            cm.dirty();
        }
    }
    @POST
    @ApiOperation(value = "Aplicar vacuna", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Vacuna.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/vacuna")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevaVacuna(Vacuna v) {
        if (v.getIdUser()==null)  return Response.status(500).entity(v).build();
        this.cm.PonerVacuna(v);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Listado de vacunaciones ordenadas por marca", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vacuna.class, responseContainer="List"),
    })
    @Path("/listVacunacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listVac() {

        List<Vacuna> listVacunas = this.cm.ListaVacunas();

        GenericEntity<List<Vacuna>> entity = new GenericEntity<List<Vacuna>>(listVacunas) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Listado de marcas ordenado descendentemente por numero de vacunas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = MarcaVacunas.class, responseContainer="List"),
    })
    @Path("/listMarcas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMarca() {

        List<MarcaVacunas> listMarca = this.cm.ListaMarcaVacunasNum();

        GenericEntity<List<MarcaVacunas>> entity = new GenericEntity<List<MarcaVacunas>>(listMarca) {};
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "añadir seguimiento", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= UserCovid.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/añadirSeg/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObject(@PathParam("name") String id, Seguimiento s) {
        if (s.getDescripcion()==null)  return Response.status(500).entity(s).build();
        this.cm.AñadirSeguimiento(id,s);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Listado de seguimientos de una persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserCovid.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/segUser/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderByUser(@PathParam("name") String id) {
        List<Seguimiento> listSeg = cm.SegUser(id);

        if (listSeg.size()==0) return Response.status(404).build();
        else {
            GenericEntity<List<Seguimiento>> entity = new GenericEntity<List<Seguimiento>>(listSeg) {};
            return Response.status(201).entity(entity).build();
        }
    }
}
