package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.model.Usuario;
import application.repository.UsuarioRepository;
 
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
  
    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String url) {
    	
    	Usuario u = new Usuario();
    	
    	u.setIdade(20);
    	u.setNome("Guilherme");
    	usuarioRepository.save(u);
    	
        return "Ola mundo: " + url + "!";
    }
    
    @GetMapping(value = "listausuarios")// API buscar usuarios
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarUsuarios(){
    	
    	List<Usuario> listaUsuario = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK);
    }
    
}
