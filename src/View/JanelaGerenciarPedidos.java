package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controll.ControladorCliente;
import Controll.ControladorFuncionario;
import Controll.ControladorPedido;
import Model.Cliente;
import Model.Pedido;

public class JanelaGerenciarPedidos extends JanelaPadraoGerencia{
	public JanelaGerenciarPedidos() {
		ControladorFuncionario control = new ControladorFuncionario();
		String[] strings = control.usuarioL().split("-");
		String titulo = "janela de Atendente"+".   Funcionário: "+strings[1]+"  !";
		this.setTitle(titulo);
		adicionarJMenuBar(this);
		adicionarJButtons();
		adicionarJTableAtendente();
		backGround();
		this.setVisible(true);
	}
	public void adicionarJMenuBar(JanelaGerenciarPedidos janela) {
		JMenuBar menuBar=new JMenuBar();
		janela.setJMenuBar(menuBar);
		JMenu logout= new JMenu("Logout");
		menuBar.add(logout);
		JMenuItem sair= new JMenuItem("Deslogar");
		sair.addActionListener(new OuvinteBtLogout(this));
		logout.add(sair);
		JMenu menuPedidos= new JMenu("Pedidos");
		menuBar.add(menuPedidos);
		JMenuItem realizarPedido= new JMenuItem("Realizar Pedido");
		realizarPedido.addActionListener(new OuvinteBtRealizar(this));
		menuPedidos.add(realizarPedido);
		JMenu acesso= new JMenu("Acesso");
		JMenuItem gerente= new JMenuItem("Gerente");
		gerente.addActionListener(new OuvinteBtAcessoGerente(this));
		acesso.add(gerente);
		JMenuItem atendente= new JMenuItem("Atendente");
		atendente.addActionListener(new OuvinteBtAcessoAtendente(this));
		acesso.add(atendente);
		JMenuItem motoboy = new JMenuItem("Motoboy");
		motoboy.addActionListener(new OuvinteBtAcessoMotoboy(this));
		acesso.add(motoboy);
		JMenuItem pizzaiollo = new JMenuItem("Pizzaiollo");
		pizzaiollo.addActionListener(new OuvinteBtAcessoPizzaiollo(this));
		acesso.add(pizzaiollo);
		ControladorFuncionario control = new ControladorFuncionario();
		String[]strings=control.usuarioL().split("-");
		if(strings[0].equals("Gerente") || strings[0].equals("Admin")) {
			menuBar.add(acesso);
		}
		
	}
	public void backGround(){
		JLabel fundo = new JLabel(new ImageIcon("telaComp.jpg"));
		fundo.setBounds(0, 0, 1100, 700);
		add(fundo);
	}
    
	DefaultTableModel modelo;
    JTable tabela;
    
    
	public void adicionarJTableAtendente() {
		ControladorPedido control = new ControladorPedido();
		ControladorCliente controlCli = new ControladorCliente();
		modelo = new DefaultTableModel();
		
		modelo.addColumn("ID");
		modelo.addColumn("CPF");
		modelo.addColumn("Sabor");
		modelo.addColumn("Tamanho");
		modelo.addColumn("Qtd");
		modelo.addColumn("Status");
		modelo.addColumn("Ingredientes");
		modelo.addColumn("Endereço");

		for(Pedido pedido:control.listarP()){
			if(pedido.getStatus().equals("Entregue")==false){
				String endereco="";
				for(Cliente cli:controlCli.listarC()){
					if(cli.getIdentificacao().equals(pedido.getCpfCliente())){
						endereco=cli.getEndereco();
					}
				}
				Object[] linha = new Object[]{
						pedido.getID(),pedido.getCpfCliente(),pedido.getPizza().getTipo(),pedido.getPizza().getTamanho(),
						pedido.getQtdPizzas(),pedido.getStatus(),pedido.getPizza().getIgredientes(),endereco
				};
				modelo.addRow(linha);
			}
		}
		tabela = new JTable(modelo);
		JScrollPane conteiner = new JScrollPane(tabela);
		conteiner.setBounds(260, 160, 805, 460);
		add(conteiner);
	}
	
	public void adicionarJButtons() {
		JButton pedidosdoDia = new JButtonPersonalizado("Pedidos do dia", 30, 275,200, 25);
		pedidosdoDia.addActionListener(new OuvinteBtPedidosDia(this));
		add(pedidosdoDia);
		JButton gerenciaCliente= new JButtonPersonalizado("Gerenciar clientes", 30, 320, 200, 25);
		gerenciaCliente.addActionListener(new OuvinteBtGerencia(this));
		add(gerenciaCliente);
		JButton listarPizzas= new JButtonPersonalizado("Listar pizzas", 30, 365, 200, 25);
		listarPizzas.addActionListener(new OuvinteBtListar(this));
		add(listarPizzas);
		JButton excluir = new JButtonPersonalizado("Excluir pedido", 30, 410, 200, 25);
		excluir.addActionListener(new OuvinteBtExcluir(this));
		add(excluir);
	}
	
	private class OuvinteBtRealizar implements ActionListener{
		private JanelaGerenciarPedidos janela;
		
		public OuvinteBtRealizar(JanelaGerenciarPedidos janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaRealizarPedido(janela).setLocationRelativeTo(janela);
			
		}
		
	}

	private class OuvinteBtExcluir implements ActionListener{
		private JanelaGerenciarPedidos janela;
		
		public OuvinteBtExcluir(JanelaGerenciarPedidos janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			ControladorPedido control= new ControladorPedido();
			Object codigo = modelo.getValueAt(tabela.getSelectedRow(),0);
			Object cpf = modelo.getValueAt(tabela.getSelectedRow(),1);
			String id=(String) codigo;
			String CPF=(String) cpf;
			try {
				control.excluirP(id,CPF);
				JOptionPane.showMessageDialog(null, "Pedido excluido com sucesso !");
				janela.dispose();
				new JanelaGerenciarPedidos().setLocationRelativeTo(null);;
			} catch (IdInexistenteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		}
		
	}

	private class OuvinteBtGerencia implements ActionListener{
		private JanelaGerenciarPedidos janela;
		
		public OuvinteBtGerencia(JanelaGerenciarPedidos janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaGerenciarClientes().setLocationRelativeTo(janela);
			janela.dispose();
			
		}
		
	}
	private class OuvinteBtListar implements ActionListener{
		private JanelaGerenciarPedidos janela;
		
		public OuvinteBtListar(JanelaGerenciarPedidos janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaListarPizzas().setLocationRelativeTo(janela);;
			
		}
		
	}
	private class OuvinteBtPedidosDia implements ActionListener{
		private JanelaGerenciarPedidos janela;
		
		public OuvinteBtPedidosDia(JanelaGerenciarPedidos janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaHistoricoDePedidos().setLocationRelativeTo(janela);;
			
		}
		
	}

}
