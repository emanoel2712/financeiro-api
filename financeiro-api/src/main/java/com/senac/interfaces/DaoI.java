/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.interfaces;

import java.util.List;

/**
 *
 * @author Alunos
 */
public interface DaoI<T> {
    public boolean salvar(T obj);
    public boolean atualizar(T obj);
    public boolean excluir(T obj);
    public List<T> listar();
    public T lerPorId(int id);

}
