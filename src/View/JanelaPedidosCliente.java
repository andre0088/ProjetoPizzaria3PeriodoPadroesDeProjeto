package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controll.ControladorCliente;
import Controll.ControladorPedido;
import Model.Pedido;

public class JanelaPedidosCliente extends JanelaPadrao{
	private String cpf;
	public JanelaPedidosCliente(String cpf) {
		this.cpf=cpf;
		this.setTitle("Pedidos feitos");
		adicionarJTable();
		adicionarJButton();
		this.setVisible(true);
	}
	
	DefaultTableModel modelo;
    JTable tabela;
    
	public void adicionarJTable() {
		ControladorPedido control = new ControladorPedido();
		ControladorCliente controlCli = new ControladorCliente();
		modelo = new DefaultTableModel();
		
		modelo.addColumn("ID");
		modelo.addColumn("Sabor");
		modelo.addColumn("Tamanho");
		modelo.addColumn("Qtd");
		modelo.addColumn("Status");
		modelo.addColumn("Ingredientes");
		modelo.addColumn("Endereço");
		

	
		for(Pedido p:control.pedidosCliente(cpf)) {
			Object[] linha = new Object[] {
					p.getID(),p.getPizza().getTipo(),p.getPizza().getTamanho()
					,p.getQtdPizzas(),p.getStatus(),p.getPizza().getIgredientes(),controlCli.endCliente(cpf)
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
	
		private JanelaPedidosCliente janela;
	
		public OuvinteBtVoltar(JanelaPedidosCliente janela){
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			janela.dispose();
		
		}
	
	}
}
