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

import Controll.ControladorFuncionario;
import Controll.ControladorPizza;
import Model.Pizza;

public class JanelaGerenciarPizza extends JanelaPadraoGerencia{
	public JanelaGerenciarPizza() {
		ControladorFuncionario control = new ControladorFuncionario();
		String[] strings = control.usuarioL().split("-");
		String titulo = "janela de "+strings[0]+".   Funcionário: "+strings[1]+"  !";
		this.setTitle(titulo);
		adicionarJMenuBar(this);
		adicionarJTablePizza();
		adicionarJButtons();
		backGround();
		this.setVisible(true);
	}
	public void adicionarJMenuBar(JanelaGerenciarPizza janela) {
		JMenuBar menuBar=new JMenuBar();
		janela.setJMenuBar(menuBar);
		JMenu logout= new JMenu("Logout");
		menuBar.add(logout);
		JMenuItem sair= new JMenuItem("Deslogar");
		sair.addActionListener(new OuvinteBtLogout(this));
		logout.add(sair);
		JMenu menuPizza= new JMenu("Pizzas");
		menuBar.add(menuPizza);
		JMenuItem cadastrarPizza= new JMenuItem("Cadastrar pizza");
		cadastrarPizza.addActionListener(new OuvinteBtCadastrar(this));
		menuPizza.add(cadastrarPizza);
		JMenuItem editarPizza= new JMenuItem("Editar pizza");
		editarPizza.addActionListener(new OuvinteBtEditar(this));
		menuPizza.add(editarPizza);
		JMenu menuRelatorios= new JMenu("Relatorios");
		JMenuItem gerarRelatorios= new JMenuItem("Gerar relatórios");
		gerarRelatorios.addActionListener(new OuvinteBtContabilidade(this));
		menuRelatorios.add(gerarRelatorios);
		menuBar.add(menuRelatorios);
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
	
	public void adicionarJTablePizza() {
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
		conteiner.setBounds(260, 160, 805, 460);
		add(conteiner);
	}
	public void adicionarJButtons() {
		JButton gerenciarFuncionarios= new JButtonPersonalizado("Gerenciar funcionarios", 10, 320, 240, 25);
		gerenciarFuncionarios.addActionListener(new OuvinteBtGerenciar(this));
		add(gerenciarFuncionarios);
		JButton excluir = new JButtonPersonalizado("Excluir pizza", 10, 355, 240, 25);
		excluir.addActionListener(new OuvinteBtExcluir(this));
		add(excluir);
	}

	private class OuvinteBtCadastrar implements ActionListener{
		private JanelaGerenciarPizza janela;
		
		public OuvinteBtCadastrar(JanelaGerenciarPizza janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaCadastrarPizza(janela).setLocationRelativeTo(janela);
		}
		
	}
	private class OuvinteBtEditar implements ActionListener{
		private JanelaGerenciarPizza janela;
		
		public OuvinteBtEditar(JanelaGerenciarPizza janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaEditarPizza(janela).setLocationRelativeTo(janela);
		}
		
	}
	private class OuvinteBtExcluir implements ActionListener{
		private JanelaGerenciarPizza janela;
		
		public OuvinteBtExcluir(JanelaGerenciarPizza janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			ControladorPizza controlador = new ControladorPizza();
			Object codigo=modelo.getValueAt(tabela.getSelectedRow(),0);
			String id= (String) codigo;
			controlador.excluirP(id);
			JOptionPane.showMessageDialog(null, "Pizza excluida com sucesso !");
			janela.dispose();
			new JanelaGerenciarPizza().setLocationRelativeTo(null);
		}	
		
	}
	private class OuvinteBtGerenciar implements ActionListener{
		private JanelaGerenciarPizza janela;
		
		public OuvinteBtGerenciar(JanelaGerenciarPizza janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaGerenciarFuncionarios().setLocationRelativeTo(janela);
			janela.dispose();
		}
		
	
	}
	
	
}
