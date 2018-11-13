package br.senac.sc.financeiroapi.resource;

import br.senac.sc.financeiroapi.model.Categoria;
import com.senac.conexao.Dao;
import com.senac.interfaces.DaoI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/categorias")
@RestController
public class CategoriaResource {

    @GetMapping
    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(1L, "Transporte"));
        categorias.add(new Categoria(2L, "Alimentação"));
        categorias.add(new Categoria(3L, "Educação"));
        return categorias;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long codigo) {
        //Categoria c = categoriaDao.buscarPorId(codigo)
        //return c;
        Categoria c = null;
        if (c == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(c);
        }
    }
        @PutMapping("/{codigo}")
        public Categoria alterar(@PathVariable Long id, @RequestBody Categoria categoria){
            
            //categoriaDao.alterar(codigo, categoria);
            
        return null;
        
    }
          
    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria salvar(@RequestBody Categoria categoria) {
        //Categoria c = categoriaDao.salvar(categoria)
        //return categoria;
        
        // categoriaDao.salvar(categoria);
        
        categoria.setId(1L);

        return categoria;
    }

}
//    @PostMapping(path = "/categorias")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Categoria salvar(@RequestBody Categoria categoria) {
//        categoria.setId(1L);
//        System.out.println(categoria.getNome());
//        return categoria;
//    }

