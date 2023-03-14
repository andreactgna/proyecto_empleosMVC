package com.rayosoft.service.db;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rayosoft.model.Usuario;
import com.rayosoft.repository.UsuariosRepository;
import com.rayosoft.service.IUsuariosService;

@Service
public class UsuariosServiceJpa implements IUsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepo;
	
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
	}

	public void eliminar(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);
	}

	public List<Usuario> buscarTodos() {
		return usuariosRepo.findAll();
	}

	@Override
	public List<Usuario> buscarRegistrados() { return usuariosRepo.findByFechaRegistroNotNull();}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		Optional<Usuario> optional = usuariosRepo.findById(idUsuario);
		if (optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public Usuario buscarPorUsername(String username) {return usuariosRepo.findByUsername(username);}

	@Transactional
	@Override
	public int bloquear(int id) {return usuariosRepo.lock(id);}

	@Transactional
	@Override
	public int activar(int id) {return usuariosRepo.unlock(id);}

}
