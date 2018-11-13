/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sc.financeiroapi.dao;

import br.senac.sc.financeiroapi.model.Categoria;
import br.senac.sc.financeiroapi.model.Endereco;
import br.senac.sc.financeiroapi.model.Pessoa;
import com.senac.conexao.Dao;
import com.senac.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emanoel
 */
public class PessoaDao extends Dao implements DaoI<Pessoa>{
   private PreparedStatement stmt;  
   
     @Override
    public boolean salvar(Pessoa obj) {
        try {
            //N SEI SE O INSERT TÁ CERTO
            stmt = conexao.prepareStatement("INSERT pessoa(nome, ativo, endereco) VALUES(?, ?, ?)");
            stmt.setString(1, obj.getNome());
            stmt.setBoolean(2, obj.getAtivo());
            // n sei se é pra pegar o id do endereco
            stmt.setObject(3, obj.getEndereco().getId());
            
            //Res=Resultado de linhas afetadas (Quantidade de linhas afetadas)
            int res = stmt.executeUpdate();
            if(res>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean atualizar(Pessoa obj) {
       try {
            stmt = conexao.prepareStatement("UPDATE pessoa SET nome = ?, ativo = ?, endereco = ?, WHERE id = ? ");                   
            stmt.setString(1, obj.getNome());
            stmt.setBoolean(2, obj.getAtivo());
            stmt.setObject(3, obj.getEndereco().getId());
            
            //Verifica a quantidade de linhas afetadas
            if(stmt.executeUpdate()>0){
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } 
    }
        
        
        
        
        
        
        
        

    @Override
    public boolean excluir(Pessoa obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM pessoa WHERE id = ? ");
            stmt.setLong(1, obj.getId());
            
            //Verifica a quantidade de linhas afetadas
            return (stmt.executeUpdate()>0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
          
        }
       
    }

    @Override
    public List<Pessoa> listar() {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome, ativo, endereco FROM cliente ORDER BY id DESC");
            //result seria o resultado do select ou seja (id, nome) q tá na lista do banco de dados
            ResultSet result = stmt.executeQuery();
            List<Pessoa> lista = new ArrayList<>();
            
            
            //Enquanto tiver mais resultado ou seja linhas q são basicamente (id e nome de clientes)no banco de dados.          
            while(result.next()){
                //Irá ser criado novos clientes setando id e nome e dando get nos mesmos
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getLong("id"));
                pessoa.setNome(result.getString("nome"));
                pessoa.setAtivo(result.getBoolean("ativo"));
                pessoa.setEndereco((Endereco) result.getObject("enderco"));
                //E irá adiciona-los na lista
                lista.add(pessoa);
            }
            
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Pessoa lerPorId(int id) {
        return null;
    }
}