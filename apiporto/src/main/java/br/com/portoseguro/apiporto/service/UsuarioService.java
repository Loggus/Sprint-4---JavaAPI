package br.com.portoseguro.apiporto.service;


import java.sql.SQLException;
import java.util.List;


import br.com.portoseguro.apiporto.data.UsuarioDao;
import br.com.portoseguro.apiporto.model.Usuario;

public class UsuarioService {
	UsuarioDao dao = new UsuarioDao();
	
	
	public List<Usuario> getAllUsuarios () throws SQLException {
		return dao.getAllUsuarios();
	}
	
	public Usuario findById(Long id) throws SQLException {
		return dao.findById(id);
	}
	
	public boolean create(Usuario usuario) throws SQLException {
		if (!validar(usuario)) return false;
		dao.create(usuario); 
		return true;
	}
	
	  public boolean validar(Usuario usuario) {
	        if (usuario == null)  return false; 
	        if (usuario.getNome() == null || usuario.getNome().isEmpty()) return false;
	        if (usuario.getCelular() <= 0) return false; 
	        if (usuario.getEmail() == null || !usuario.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) return false; 
	        if (usuario.getCpf() <= 0) return false; 
	        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) return false; 
	        
	        return true;
	    }

	public boolean update(Usuario usuario) throws SQLException {
		if (!validar(usuario)) return false;
		dao.update(usuario);		
		return true;
		
	}
	
	public boolean delete(Usuario usuario) throws SQLException {
		if (!validar(usuario)) return false;
		dao.delete(usuario);		
		return true;
	}

}
