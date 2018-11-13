/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sc.financeiroapi.control;

import br.senac.sc.financeiroapi.dao.CategoriaDao;
import br.senac.sc.financeiroapi.model.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alunos
 */
public class CategoriaControl {

    private JTextField tfNome;
    private JTable tbCategorias;
    private JTextField tfPesquisa;
    

    private Categoria categoria = null;
    private List<Categoria> listCategorias;
    private CategoriaDao categoriaDao;

    public CategoriaControl(JTextField tfNome, JTable tbCategorias, JTextField tfPesquisa) {
        this.tfNome = tfNome;
        this.listCategorias = listCategorias;
        this.categoriaDao = categoriaDao;

        listCategorias = new ArrayList<>();
        categoriaDao = new CategoriaDao();
    }

    public void cadastrarAction() {
        //Montar nova categoria
        categoria = new Categoria();
        categoria.setNome(tfNome.getText());

        //Salvar categoria no banco e verificar o retorno
        boolean res = categoriaDao.salvar(categoria);
        if (res) {
            JOptionPane.showMessageDialog(null, "Cadastrado!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }

    public void excluirAction() {
        categoria = getItemSelecionado();
        if (categoria == null) {
            JOptionPane.showMessageDialog(null, "Escolha uma categoria!");
        } else {
            boolean res = categoriaDao.excluir(categoria);
            if (res) {
                JOptionPane.showMessageDialog(null, "Categoria excluída!");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            }
        }
        categoria = null;
    }

    private Categoria getItemSelecionado() {
        int linha = tbCategorias.getSelectedRow();
        if (linha >= 0) {
            return listCategorias.get(linha);
        } else {
            return null;
        }
    }

    public void listarAction() {
        listCategorias = categoriaDao.listar();
        showItensTable();
    }

    private void showItensTable() {
        //Listando categorias na table
        DefaultTableModel model;
        model = (DefaultTableModel) tbCategorias.getModel();
        model.setNumRows(0);
        for (Categoria c : listCategorias) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome()
            });
        }
    }

    public void popularFormAction() {
        categoria = getItemSelecionado();
        if (categoria != null) {
            tfNome.setText(categoria.getNome());
        } else {
            JOptionPane.showMessageDialog(null, "Escolha uma categoria");
        }
    }

    public void salvarAction() {
        if (categoria == null) {
            cadastrarAction();
        } else {
            alterarAction();
        }

        listarAction();
        tfNome.setText("");
        tfNome.requestFocus();
        categoria = null;
    }

    public void alterarAction() {
        categoria.setNome(tfNome.getText());
        boolean res = categoriaDao.atualizar(categoria);
        if (res) {
            JOptionPane.showMessageDialog(null, "Editado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao editar.");
        }
    }
     public void pesquisarAction(){
        listCategorias = (List<Categoria>) categoriaDao.lerPorId(Integer.valueOf(tfPesquisa.getText()));
        showItensTable();
    }

}
