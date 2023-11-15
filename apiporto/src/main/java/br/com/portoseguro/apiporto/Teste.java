package br.com.portoseguro.apiporto;

import java.sql.SQLException;

import br.com.portoseguro.apiporto.data.BikeDao;
import br.com.portoseguro.apiporto.model.Bicicleta;

public class Teste {

	public static void main(String[] args) {
		BikeDao dao= new BikeDao();
		try {
			Bicicleta bi = dao.findById(1L);
			System.out.println(bi.getMarca());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
