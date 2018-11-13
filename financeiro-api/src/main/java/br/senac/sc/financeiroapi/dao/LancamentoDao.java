/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sc.financeiroapi.dao;

import br.senac.sc.financeiroapi.model.Categoria;
import br.senac.sc.financeiroapi.model.Lancamento;
import br.senac.sc.financeiroapi.model.Pessoa;
import br.senac.sc.financeiroapi.model.TipoLancamento;
import com.senac.conexao.Dao;
import com.senac.interfaces.DaoI;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emanoel
 */
public class LancamentoDao extends Dao implements DaoI<Lancamento> {

    private PreparedStatement stmt;

    public LancamentoDao() {
    }

    @Override
    public boolean salvar(Lancamento obj) {
        try {
            stmt = conexao.prepareStatement("INSERT lancamento (descricao, dataVencimento, dataPagamento, valor, observacao, tipo, categoria, pessoa) VALUES (?, ?, ?, ?, ?, ?, ? ?)");

            stmt.setString(1, obj.getDescricao());
            stmt.setDate(2, Date.valueOf(obj.getDataVencimento()));
            stmt.setDate(3, Date.valueOf(obj.getDataPagamento()));
            stmt.setDouble(4, obj.getValor());
            stmt.setString(4, obj.getObservacao());
            stmt.setObject(5, obj.getTipo());
            stmt.setObject(5, obj.getCategoria());
            stmt.setObject(5, obj.getPessoa());

            //Quantidade de linhas afetadas
            int res = stmt.executeUpdate();
            if (res > 0) {
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
    public boolean atualizar(Lancamento obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE lancamento"
                    + " SET descricao = ? "
                    + " SET dataVencimento = ? "
                    + " SET dataPagamento = ? "
                    + " SET valor = ? "
                    + " SET observacao = ? "                    
                    + " SET tipo = ? "
                    + " SET categoria = ? "
                    + " SET pessoa = ? "
                    + " WHERE id = ? ");
            stmt.setString(1, obj.getDescricao());
       //     stmt.setString(2, obj.getDataVencimento());
       //     stmt.setString(3, obj.getDataPagamento());
            stmt.setDouble(4, obj.getValor());
            stmt.setString(5, obj.getObservacao());
            stmt.setObject(6, obj.getTipo());
            stmt.setObject(7, obj.getCategoria());
            stmt.setObject(8, obj.getPessoa());

            //Verifica a quantidade de linhas afetadas
            if (stmt.executeUpdate() > 0) {
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
    public boolean excluir(Lancamento obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM lancamento "
                    + " WHERE id = ?");
            stmt.setLong(1, obj.getId());

            //Verifica a quantidade de linhas afetadas
            return (stmt.executeUpdate() > 0);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Lancamento> listar() {
        try {
            stmt = conexao.prepareStatement("SELECT id, descricao, dataVencimento, dataPagamento, valor, observacao, tipo, categoria, pessoa FROM lancamento ORDER BY id DESC)");
              
            res = stmt.executeQuery();
            List<Lancamento> lista = new ArrayList<>();

            //Extraindo os dados da consulta
            while (res.next()) {
                Lancamento l = new Lancamento();
                l.setId(res.getLong("id"));
                l.setDescricao(res.getString("descricao"));
                l.setDataVencimento(res.getString("dataVencimento"));
                l.setDataPagamento(res.getString("dataPagamento"));
                l.setValor(res.getDouble("valor"));
                l.setObservacao(res.getString("observacao"));
                l.setTipo((TipoLancamento) res.getObject("tipo"));
                l.setCategoria((Categoria) res.getObject("categoria"));
                l.setPessoa((Pessoa) res.getObject("pessoa"));
                
                lista.add(l);
            }

            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Lancamento lerPorId(int id) {
        return null;
    }
}
  //  @Override
  //  public Lancamento lerPorId(int id) {
  //     try {
     //       stmt = conexao.prepareStatement("SELECT id, nome "
    //                + " FROM categoria WHERE id = ?");
     //       stmt.setInt(1, id);
      //      ResultSet res = stmt.executeQuery();
        //    Categoria c = new Categoria();
          //  if(res.next()){
      //          c.setId(res.getLong("id"));
       //         c.setNome(res.getString("nome"));
        //    }
      //      return c;
       // } catch (SQLException ex) {
       //     System.out.println(ex.getMessage());
           // return null;
      //  }
 //   }
    
//}