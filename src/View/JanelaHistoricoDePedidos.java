package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controll.ControladorCliente;
import Controll.ControladorPedido;
import Model.Cliente;
import Model.Pedido;

public class JanelaHistoricoDePedidos extends JanelaPadrao{
	public JanelaHistoricoDePedidos() {
		this.setTitle("Pedidos do dia");
		adicionarJTable();
		adicionarJButton();
		this.setVisible(true);
	}
	
	DefaultTableModel modelo;
    JTable tabela;
    
	public void adicionarJTable() {
		ControladorPedido control = new ControladorPedido();
		ControladorCliente controlCliente = new ControladorCliente();
		modelo = new DefaultTableModel();
		
		modelo.addColumn("ID");
		modelo.addColumn("CPF");
		modelo.addColumn("Sabor");
		modelo.addColumn("Tamanho");
		modelo.addColumn("Qtd");
		modelo.addColumn("Status");
		modelo.addColumn("Ingredientes");
		modelo.addColumn("Endereço");
		
		Date dataDia = new Date();
		GregorianCalendar dataCalDia= new GregorianCalendar();
		dataCalDia.setTime(dataDia);
		Format format= new SimpleDateFormat("dd");
		int relatorioDia= Integer.parseInt(format.format(dataCalDia.getTime()));
		
		for(Pedido p:control.listarP()) {
			String endereco="";
			for(Cliente cli:controlCliente.listarC()){
				if(cli.getIdentificacao().equals(p.getCpfCliente())){
					endereco=cli.getEndereco();
				}
			}
			GregorianCalendar dataCalPedido= new GregorianCalendar();
			dataCalPedido.setTime(p.getData());
			Format formato= new SimpleDateFormat("dd");
			int relatorioPedido= Integer.parseInt(formato.format(dataCalPedido.getTime()));
			if(relatorioDia==relatorioPedido) {
				Object[] linha = new Object[] {
						p.getID(),p.getCpfCliente(),p.getPizza().getTipo(),p.getPizza().getTamanho()
						,p.getQtdPizzas(),p.getStatus(),p.getPizza().getIgredientes(),endereco
				};
				modelo.addRow(linha);
			}
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
		
		private JanelaHistoricoDePedidos janela;
	
		public OuvinteBtVoltar(JanelaHistoricoDePedidos janela){
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			janela.dispose();
		
		}
	
	}
	
}
