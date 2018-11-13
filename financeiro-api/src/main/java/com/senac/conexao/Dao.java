/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alunos
 */
public class Dao {
    private PreparedStatement stmt;
    public ResultSet res;
    private boolean debug = true;
    protected Connection conexao;
    private static final String DATABASE_NAME = "locadora";

    public Dao() {
        conexao = Conexao.getConexao();
        openOrCreateDatabase();
    }
    
    private void openOrCreateDatabase(){ 
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("CREATE DATABASE IF NOT EXISTS "+DATABASE_NAME);
            stmt.execute();
            
            stmt = conexao.prepareStatement("USE "+DATABASE_NAME);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
