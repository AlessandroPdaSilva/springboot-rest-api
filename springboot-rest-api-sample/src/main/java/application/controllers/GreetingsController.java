package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.model.Usuario;
import application.repository.UsuarioRepository;
 
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
  
    @RequestMapping(value = "/mostranome/{url}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String url) {
    	
    	Usuario u = new Usuario();
    	
    	u.setIdade(20);
    	u.setNome("Guilherme");
    	usuarioRepository.save(u);
    	
        return "Ola mundo: " + url + "!";
    }
    
    // API buscar usuarios
    @GetMapping(value = "listausuarios")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarUsuarios(){
    	
    	List<Usuario> listaUsuario = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK);
    }
    
    
    // Salvar usuario
    @PostMapping(value = "salvarusuario")
    @ResponseBody
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
    	
    	usuario = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }
    
    // Editar usuario
    @PutMapping(value = "editarusuario")
    @ResponseBody
    public ResponseEntity<?> editarUsuario(@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id do usuario não informado", HttpStatus.OK);
    	}
    	
    	usuario = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    
    // Deletar usuario
    @DeleteMapping(value = "deletarusuario")
    @ResponseBody
    public ResponseEntity<String> deletarUsuario(@RequestParam Long idUsuario){
    	
    	usuarioRepository.deleteById(idUsuario);
    	
    	return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.OK);
    }
    
    // Buscar usuario por id
    @GetMapping(value = "buscarusuariobyid")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUsuarioById(@RequestParam(name = "idUsuario") Long idUsuario){
    	
    	Usuario u = usuarioRepository.findById(idUsuario).get();
    	
    	return new ResponseEntity<Usuario>(u, HttpStatus.OK);
    }
    
    
    // Buscar usuario por nome
    @GetMapping(value = "buscarusuariobynome")
    @ResponseBody
    public ResponseEntity<?> buscarUsuarioByNome(@RequestParam(name = "nome") String nome){
    	
    	List<Usuario> listaUsuario = usuarioRepository.pesquisarByNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK);
    }
    
    
}


