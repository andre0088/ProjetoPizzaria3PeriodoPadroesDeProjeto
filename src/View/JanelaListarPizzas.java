package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controll.ControladorPizza;
import Model.Pizza;

public class JanelaListarPizzas extends JanelaPadrao{
	
	public JanelaListarPizzas() {
		this.setTitle("Pizzas cadastradas");
		adicionarJTable();
		adicionarJButton();
		this.setVisible(true);
	}
	
	JTable tabela;
	DefaultTableModel modelo;
	
	public void adicionarJTable() {
		ControladorPizza control = new ControladorPizza();
		modelo = new DefaultTableModel();
		
		modelo.addColumn("ID");
		modelo.addColumn("Sabor");
		modelo.addColumn("Tamanho");
		modelo.addColumn("Preço");
		modelo.addColumn("Ingredientes");
		modelo.addColumn("Preparo");
		
		for(Pizza piz:control.listarP()){
			Object[] linha = new Object[]{
					piz.getCodigoId(),piz.getTipo(),piz.getTamanho(),piz.getPrecoCompleto()
					,piz.getIgredientes(),piz.getFormaPreparo()
			};
			modelo.addRow(linha);
		}
		
		tabela = new JTable(modelo);
		
		JScrollPane conteiner = new JScrollPane(tabela);
		conteiner.setBounds(0, 146, 520, 230);
		add(conteiner);
	}
	public void adicionarJButton(){
		JButton voltar= new JButtonPersonalizado("Voltar", 200, 400, 100, 30);
		voltar.addActionListener(new OuvinteBtVoltar(this));
		add(voltar);
	}
	private class OuvinteBtVoltar implements ActionListener{
	private JanelaListarPizzas janela;
	
	public OuvinteBtVoltar(JanelaListarPizzas janela){
		this.janela=janela;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		janela.dispose();
		
	}
	
}

}
