package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.persistence.PDSFactoriaDAO;
import umu.pds.duolingoBaratero.persistence.PDSUsuarioDAO;

public enum UsuarioService {
	INSTANCE;
	
	private PDSUsuarioDAO usuarioDAO;
	
	private UsuarioService() {
		this.usuarioDAO = PDSFactoriaDAO.INSTANCE.getUsuarioDAO();
	}
	
}
