package com.rayosoft.service;

import java.util.List;
import com.rayosoft.model.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface IUsuariosService {

	/** Ejercicio: Implementar método para registrar un usuario nuevo. 
	 * 	1. Usar la plantilla del archivo formRegistro.html
	 * 	2. El método para mostrar el formulario para registrar y el método para guardar el usuario deberá 
	 * 	   estar en el Controlador HomeController.
	 * 	3. Al guardar el usuario se le asignará el perfil USUARIO y la fecha de Registro
	 * 	   sera la fecha actual del sistema.
	 * @param usuario
	 */
	void guardar(Usuario usuario);
	
	// Ejercicio: Método que elimina un usuario de la base de datos.
	void eliminar(Integer idUsuario);
	
	// Ejercicio: Implementar método que recupera todos los usuarios. Usar vista de listUsuarios.html
	List<Usuario> buscarTodos();
	List<Usuario> buscarRegistrados();
	Usuario buscarPorId(Integer idUsuario);
	Usuario buscarPorUsername(String username);
	int bloquear(int id);
	int activar(int id);
}

// Agregar al archivo menu.html el link para acceder al listado de Usuarios y configurar el link del botón Registrarse

