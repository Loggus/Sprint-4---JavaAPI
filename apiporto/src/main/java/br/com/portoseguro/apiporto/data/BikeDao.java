package br.com.portoseguro.apiporto.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portoseguro.apiporto.model.Bicicleta;
import br.com.portoseguro.connectionfactory.ConnectionFactory;

public class BikeDao {

    private Connection conexao;

    public BikeDao() {
        try {
            conexao = ConnectionFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public List<Bicicleta> getAllBikes() throws SQLException {
        List<Bicicleta> lista = new ArrayList<Bicicleta>();

        try {
            PreparedStatement comando = conexao.prepareStatement("SELECT * FROM tbl_bikes");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                lista.add(
                    new Bicicleta(
                        resultado.getLong("id"),
                        resultado.getString("marca"),
                        resultado.getString("modelo"),
                        resultado.getBigDecimal("valor"),
                        resultado.getString("cor")
                    )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Bicicleta findById(long id) throws SQLException {
        try {
            PreparedStatement comando = conexao.prepareStatement("SELECT * FROM tbl_bikes WHERE id = ?");
            comando.setLong(1, id);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                return new Bicicleta(
                    resultado.getLong("id"),
                    resultado.getString("marca"),
                    resultado.getString("modelo"),
                    resultado.getBigDecimal("valor"),
                    resultado.getString("cor")
                );
            }

         } catch (Exception e) {
            
        	e.printStackTrace();
            }
		return null;
	
    }

    public void create(Bicicleta bicicleta) throws SQLException {
        try {
            PreparedStatement comando = conexao.prepareStatement("INSERT INTO tbl_bikes (marca, modelo, valor, cor) VALUES (?, ?, ?, ?)");
            comando.setString(1, bicicleta.getMarca());
            comando.setString(2, bicicleta.getModelo());
            comando.setBigDecimal(3, bicicleta.getValor());
            comando.setString(4, bicicleta.getCor());
            comando.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public void update(Bicicleta bicicleta) throws SQLException {
        try {
            PreparedStatement comando = conexao.prepareStatement("UPDATE tbl_bikes SET marca = ?, modelo = ?, valor = ?, cor = ? WHERE id = ?");
            comando.setString(1, bicicleta.getMarca());
            comando.setString(2, bicicleta.getModelo());
            comando.setBigDecimal(3, bicicleta.getValor());
            comando.setString(4, bicicleta.getCor());
            comando.setLong(5, bicicleta.getId());
            comando.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        }
    
    public void delete(Bicicleta bicicleta) throws SQLException {
        try {
            PreparedStatement comando = conexao.prepareStatement("DELETE FROM tbl_bikes WHERE id = ?");
            comando.setLong(1, bicicleta.getId());
            comando.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
    }
    }

