package br.com.portoseguro.apiporto.service;
import br.com.portoseguro.apiporto.data.BikeDao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import br.com.portoseguro.apiporto.model.Bicicleta;

public class BicicletaService {
	
	BikeDao dao = new BikeDao();
	
	
	public List<Bicicleta> getAllBikes () throws SQLException {
		return dao.getAllBikes();
	}
	
	public Bicicleta findById(Long id) throws SQLException {
		return dao.findById(id);
	}
	
	public boolean create(Bicicleta bike) throws SQLException {
		if (!validar(bike)) return false;
		dao.create(bike); 
		return true;
	}
	
	private boolean validar(Bicicleta bike) {
	    if (bike.getModelo() == null || bike.getModelo().isEmpty()) return false;
	    if (bike.getValor() == null || bike.getValor().compareTo(BigDecimal.ZERO) <= 0) return false;
	    if (bike.getCor() == null || bike.getCor().isEmpty()) return false;
	    
	    return true;
	}


	public boolean update(Bicicleta bike) throws SQLException {
		if (!validar(bike)) return false;
		dao.update(bike);		
		return true;
		
	}
	
	public boolean delete(Bicicleta bike) throws SQLException {
		if (!validar(bike)) return false;
		dao.delete(bike);		
		return true;
	}


	
	

	
}
