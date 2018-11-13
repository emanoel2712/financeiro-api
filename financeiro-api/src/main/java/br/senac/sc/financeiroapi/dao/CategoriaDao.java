/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sc.financeiroapi.dao;

import br.senac.sc.financeiroapi.model.Categoria;
import com.senac.conexao.Dao;
import com.senac.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class CategoriaDao extends Dao implements DaoI<Categoria> {

    PreparedStatement stmt;
    ResultSet res;

    public CategoriaDao() {
        super();
    }

    @Override
    public boolean salvar(Categoria obj) {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "categoria(nome) VALUES(?)");
            stmt.setString(1, obj.getNome());

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
    public boolean atualizar(Categoria obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE categoria"
                    + " SET nome = ? "
                    + " WHERE id = ? ");
            stmt.setString(1, obj.getNome());
            stmt.setLong(2, obj.getId());

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
    public boolean excluir(Categoria obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM categoria "
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
    public List<Categoria> listar() {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome FROM "
                    + " categoria ORDER BY id DESC");
            res = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();

            //Extraindo os dados da consulta
            while (res.next()) {
                Categoria c = new Categoria();
                c.setId(res.getLong("id"));
                c.setNome(res.getString("nome"));
                lista.add(c);
            }

            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Categoria lerPorId(int id) {
         try {
            stmt = conexao.prepareStatement("SELECT id, nome "
                    + " FROM categoria WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            Categoria c = new Categoria();
            if(res.next()){
                c.setId(res.getLong("id"));
                c.setNome(res.getString("nome"));
            }
            return c;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    
    }

}
