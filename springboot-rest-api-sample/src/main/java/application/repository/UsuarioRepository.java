package application.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import application.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
	public List<Usuario> pesquisarByNome(String nome);
}
