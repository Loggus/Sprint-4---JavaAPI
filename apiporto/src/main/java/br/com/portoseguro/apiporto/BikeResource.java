package br.com.portoseguro.apiporto;

import java.sql.SQLException;
import java.util.List;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import br.com.portoseguro.apiporto.model.Bicicleta;
import br.com.portoseguro.apiporto.service.BicicletaService;


@Path("usuarios") // Alterei o path para "bikes" para melhorar a semântica da API REST
public class BikeResource {

    private final BicicletaService service = new BicicletaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bicicleta> getAllBikes() throws SQLException {
        return service.getAllBikes();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikeById(@PathParam("id") Long id) throws SQLException {
        Bicicleta bike = service.findById(id);

        if (bike == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(bike).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBike(@PathParam("id") Long id) throws SQLException {
        Bicicleta bike = service.findById(id);
        try {
            bike = service.findById(id);

            if (bike == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (!service.delete(bike)) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ou logue a exceção adequadamente
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        // Se chegou até aqui, a exclusão foi bem-sucedida
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBike(Bicicleta bike) {
        try { 
            if (service.create(bike)) {
                return Response.ok(bike).build();
            } else {
                String errorMessage = "Falha ao criar a bicicleta. Verifique os dados e tente novamente.";
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity(errorMessage)
                               .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "Erro interno do servidor ao criar a bicicleta.";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(errorMessage)
                           .build();
        }
    }

    

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBike(@PathParam("id") Long id, Bicicleta updatedBike) throws SQLException {
        Bicicleta bikeEncontrada = service.findById(id);

        if (bikeEncontrada == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        bikeEncontrada.setMarca(updatedBike.getMarca());
        bikeEncontrada.setModelo(updatedBike.getModelo());
        bikeEncontrada.setValor(updatedBike.getValor());
        bikeEncontrada.setCor(updatedBike.getCor());

        if (!service.update(bikeEncontrada)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(bikeEncontrada).build();
    }
}
