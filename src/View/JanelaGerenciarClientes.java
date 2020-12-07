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
import Model.Cliente;

public class JanelaGerenciarClientes extends JanelaPadraoGerencia{
	public JanelaGerenciarClientes() {
		ControladorFuncionario control = new ControladorFuncionario();
		String[] strings = control.usuarioL().split("-");
		String titulo = "janela de Atendente"+".   Funcionário: "+strings[1]+"  !";
		this.setTitle(titulo);
		adicionarJMenuBar(this);
		adicionarJTable();
		adicionarJButtons();
		backGround();
		this.setVisible(true);
	}
	public void adicionarJMenuBar(JanelaGerenciarClientes janela) {
		JMenuBar menuBar=new JMenuBar();
		janela.setJMenuBar(menuBar);
		JMenu logout= new JMenu("Logout");
		menuBar.add(logout);
		JMenuItem sair= new JMenuItem("Deslogar");
		sair.addActionListener(new OuvinteBtLogout(this));
		logout.add(sair);
		JMenu menuClientes= new JMenu("Clientes");
		menuBar.add(menuClientes);
		JMenuItem cadastrarCliente= new JMenuItem("Cadastrar cliente");
		cadastrarCliente.addActionListener(new OuvinteBtCadastrar(this));
		menuClientes.add(cadastrarCliente);
		JMenuItem editarCliente= new JMenuItem("Editar Cliente");
		editarCliente.addActionListener(new OuvinteBtEditar(this));
		menuClientes.add(editarCliente);
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

	public void adicionarJTable() {
		ControladorCliente control = new ControladorCliente();
		modelo = new DefaultTableModel();
		
		modelo.addColumn("CPF");
		modelo.addColumn("Nome");
		modelo.addColumn("Endereço");
		modelo.addColumn("Telefone");
		
		for(Cliente clien:control.listarC()){
			Object[] linha = new Object[]{
					clien.getIdentificacao(),clien.getNome(),clien.getEndereco(),clien.getTelefone()
			};
			modelo.addRow(linha);
		}
		tabela= new JTable(modelo);
		
		JScrollPane conteiner = new JScrollPane(tabela);
		conteiner.setBounds(260, 160, 805, 460);
		add(conteiner);
	}
	public void adicionarJButtons() {
		JButton pedidosCliente = new JButtonPersonalizado("Pedidos cliente", 30, 275,200, 25);
		pedidosCliente.addActionListener(new OuvinteBtpedidosCliente(this));
		add(pedidosCliente);
		JButton gerenciaPedido= new JButtonPersonalizado("Gerenciar pedidos", 30, 320, 200, 25);
		gerenciaPedido.addActionListener(new OuvinteBtGerenciar(this));
		add(gerenciaPedido);
		JButton listarPizzas= new JButtonPersonalizado("Listar Pizzas", 30, 365, 200, 25);
		listarPizzas.addActionListener(new OuvinteBtListar(this));
		add(listarPizzas);
		JButton excluir= new JButtonPersonalizado("Excluir cliente", 30, 410, 200, 25);
		excluir.addActionListener(new OuvinteBtExcluir(this));
		add(excluir);

	}
	
	private class OuvinteBtCadastrar implements ActionListener{
		private JanelaGerenciarClientes janela;
	
		public OuvinteBtCadastrar(JanelaGerenciarClientes janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaCadastroCliente(janela).setLocationRelativeTo(janela);
				
		}
		
	}
	private class OuvinteBtEditar implements ActionListener{
		private JanelaGerenciarClientes janela;
	
		public OuvinteBtEditar(JanelaGerenciarClientes janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaEditarCliente(janela).setLocationRelativeTo(janela);	
		}
		
	}
	private class OuvinteBtExcluir implements ActionListener{
		private JanelaGerenciarClientes janela;
	
		public OuvinteBtExcluir(JanelaGerenciarClientes janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			ControladorCliente controlador= new ControladorCliente();
			Object codigo=modelo.getValueAt(tabela.getSelectedRow(),0);
			String id = (String) codigo;
			try {
				controlador.excluirC(id);
				JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso !");
				janela.dispose();
				new JanelaGerenciarClientes().setLocationRelativeTo(null);
			} catch (IdInexistenteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		}
		
	}
	private class OuvinteBtGerenciar implements ActionListener{
		private JanelaGerenciarClientes janela;
	
		public OuvinteBtGerenciar(JanelaGerenciarClientes janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaGerenciarPedidos().setLocationRelativeTo(janela);
			janela.dispose();	
		}
		
	}
	private class OuvinteBtListar implements ActionListener{
		private JanelaGerenciarClientes janela;
		
		public OuvinteBtListar(JanelaGerenciarClientes janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaListarPizzas().setLocationRelativeTo(janela);;
			
		}
		
	}
	private class OuvinteBtpedidosCliente implements ActionListener{
		private JanelaGerenciarClientes janela;
		
		public OuvinteBtpedidosCliente(JanelaGerenciarClientes janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			Object codigo = modelo.getValueAt(tabela.getSelectedRow(), 0);
			String cpf= (String) codigo;
			new JanelaPedidosCliente(cpf).setLocationRelativeTo(janela);
			
		}
		
	}
}
