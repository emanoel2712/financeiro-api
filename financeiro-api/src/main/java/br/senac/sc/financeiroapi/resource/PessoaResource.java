package br.senac.sc.financeiroapi.resource;

import br.senac.sc.financeiroapi.model.Endereco;
import br.senac.sc.financeiroapi.model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaResource {

    @GetMapping(path = "/pessoas")
    public List<Pessoa> listar() {
        List<Pessoa> pessoas = new ArrayList<>();

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("Joãozinho");
        pessoa1.setAtivo(true);
        pessoa1.setEndereco(criaEndereco());

        pessoas.add(pessoa1);

        return pessoas;
    }

    private Endereco criaEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLogradouro("Rua João XII");
        endereco.setNumero(503);
        endereco.setComplemento("Casa");
        endereco.setBairro("Bairro das Nações");
        endereco.setCep("88132-150");
        endereco.setCidade("Palhoça");
        endereco.setEstado("Santa Catarina");
        return endereco;
    }
}
