package br.senac.sc.financeiroapi.resource;

import br.senac.sc.financeiroapi.model.Endereco;
import br.senac.sc.financeiroapi.model.Lancamento;
import br.senac.sc.financeiroapi.model.Pessoa;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LancamentoResource {
    
    @GetMapping(path = "lancamentos")
    public List<Lancamento> listar() {
        return null;
    }
    
    
     private Pessoa criaPessoa() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLogradouro("Rua João XII");
        endereco.setNumero(503);
        endereco.setComplemento("Casa");
        endereco.setBairro("Bairro das Nações");
        endereco.setCep("88132-150");
        endereco.setCidade("Palhoça");
        endereco.setEstado("Santa Catarina");
        
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Joãozinho");
        pessoa.setAtivo(true);
        pessoa.setEndereco(endereco);
        
        return pessoa;
    }
    
}
