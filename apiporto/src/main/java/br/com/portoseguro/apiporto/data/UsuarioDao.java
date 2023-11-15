package br.com.portoseguro.apiporto.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portoseguro.apiporto.model.Usuario;
import br.com.portoseguro.connectionfactory.ConnectionFactory;

public class UsuarioDao {
	 private Connection conexao;

	    public UsuarioDao() {
	        try {
	            conexao = ConnectionFactory.createConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    }

	    public List<Usuario> getAllUsuarios() throws SQLException {
	        List<Usuario> lista = new ArrayList<Usuario>();

	        try {
	            PreparedStatement comando = conexao.prepareStatement("SELECT * FROM tbl_usuario");
	            ResultSet resultado = comando.executeQuery();

	            while (resultado.next()) {
	                lista.add(
	                    new Usuario(
	                        resultado.getInt("id"),
	                        resultado.getString("nome"),
	                        resultado.getFloat("celular"),
	                        resultado.getString("email"),
	                        resultado.getFloat("cpf"),       
	                        resultado.getString("senha")
	                    )
	                );
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return lista;
	    }

	    public Usuario findById(long id) throws SQLException {
	        try {
	            PreparedStatement comando = conexao.prepareStatement("SELECT * FROM tbl_usuario WHERE id = ?");
	            comando.setLong(1, id);
	            ResultSet resultado = comando.executeQuery();

	            if (resultado.next()) {
	                return new Usuario(
	                		resultado.getInt("id"),
	                        resultado.getString("nome"),
	                        resultado.getFloat("celular"),
	                        resultado.getString("email"),
	                        resultado.getFloat("cpf"),       
	                        resultado.getString("senha")
	                );
	            }

	         } catch (Exception e) {
	            
	        	e.printStackTrace();
	            }
			return null;
		
	    }

	    public void create(Usuario usuario) throws SQLException {
	        try {
	            PreparedStatement comando = conexao.prepareStatement("INSERT INTO tbl_usuario (nome, numero_celular, email, cpf, senha) VALUES (?, ?, ?, ?, ?)");
	            comando.setInt(1, usuario.getId());
	            comando.setString(2, usuario.getNome());
	            comando.setFloat(3, usuario.getCelular());
	            comando.setString(4, usuario.getEmail());
	            comando.setFloat(5, usuario.getCpf());
	            comando.setString(2, usuario.getSenha());
	            
	            comando.executeUpdate();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }

	    public void update(Usuario usuario) throws SQLException {
	        try {
	            PreparedStatement comando = conexao.prepareStatement("UPDATE tbl_usuario SET nome = ?, numero_celular = ?, email = ?, cpf = ?, senha = ? WHERE id = ?");
	            comando.setInt(1, usuario.getId());
	            comando.setString(2, usuario.getNome());
	            comando.setFloat(3, usuario.getCelular());
	            comando.setString(4, usuario.getEmail());
	            comando.setFloat(5, usuario.getCpf());
	            comando.setString(2, usuario.getSenha());
	            comando.executeUpdate();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        }
	    
	    public void delete(Usuario usuario) throws SQLException {
	        try {
	            PreparedStatement comando = conexao.prepareStatement("DELETE FROM tbl_usuario WHERE id = ?");
	            comando.setLong(1, usuario.getId());
	            comando.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw e; 
	        }
	    }
}
