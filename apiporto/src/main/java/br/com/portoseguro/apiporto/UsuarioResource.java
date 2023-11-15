package br.com.portoseguro.apiporto;

import java.sql.SQLException;
import java.util.List;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import br.com.portoseguro.apiporto.model.Usuario;
import br.com.portoseguro.apiporto.service.UsuarioService;


@Path("usuarios") 
public class UsuarioResource {

    private final UsuarioService service = new UsuarioService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAllusuarios() throws SQLException {
        return service.getAllUsuarios();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikeById(@PathParam("id") Long id) throws SQLException {
        Usuario usuario = service.findById(id);

        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(usuario).build();
    }

    
    @DELETE
    @Path("{id}")
    public Response deleteBike(@PathParam("id") Long id) throws SQLException {
        Usuario usuario = service.findById(id);
        try {
            usuario = service.findById(id);

            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (!service.delete(usuario)) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) {
        try { 
            if (service.create(usuario)) {
                return Response.ok(usuario).build();
            } else {
                String errorMessage = "Falha ao criar usuario. Verifique os dados e tente novamente.";
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
    public Response updateBike(@PathParam("id") Long id, Usuario updatedUsuario) throws SQLException {
        Usuario UsuarioEncontrado = service.findById(id);

        if (UsuarioEncontrado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        UsuarioEncontrado.setNome(updatedUsuario.getNome());
        UsuarioEncontrado.setCelular(updatedUsuario.getCelular());
        UsuarioEncontrado.setEmail(updatedUsuario.getEmail());
        UsuarioEncontrado.setCpf(updatedUsuario.getCpf());
        UsuarioEncontrado.setSenha(updatedUsuario.getSenha());

        if (!service.update(UsuarioEncontrado)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(UsuarioEncontrado).build();
    }
}