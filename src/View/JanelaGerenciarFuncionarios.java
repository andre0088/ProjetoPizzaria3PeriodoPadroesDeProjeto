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
import Model.Funcionario;

public class JanelaGerenciarFuncionarios extends JanelaPadraoGerencia{
	public JanelaGerenciarFuncionarios() {
		ControladorFuncionario control = new ControladorFuncionario();
		String[] strings = control.usuarioL().split("-");
		String titulo = "janela de "+strings[0]+".   Funcionário: "+strings[1]+"  !";
		this.setTitle(titulo);
		adicionarJMenuBar(this);
		adicionarJTableFuncionarios();
		adicionarJButtons();
		backGround();
		this.setVisible(true);
	}
	public void adicionarJMenuBar(JanelaGerenciarFuncionarios janela) {
		JMenuBar menuBar=new JMenuBar();
		janela.setJMenuBar(menuBar);
		JMenu logout= new JMenu("Logout");
		menuBar.add(logout);
		JMenuItem sair= new JMenuItem("Deslogar");
		sair.addActionListener(new OuvinteBtLogout(this));
		logout.add(sair);
		JMenu menuFuncionario= new JMenu("Funcionarios");
		menuBar.add(menuFuncionario);
		JMenuItem cadastrarFuncionario= new JMenuItem("Cadastrar Funcionario");
		cadastrarFuncionario.addActionListener(new OuvinteBtCadastrar(this));
		menuFuncionario.add(cadastrarFuncionario);
		JMenuItem editarFuncionario= new JMenuItem("Editar Funcionario");
		editarFuncionario.addActionListener(new OuvinteBtEditar(this));
		menuFuncionario.add(editarFuncionario);
		JMenu menurelatorios= new JMenu("Relatorios");
		JMenuItem gerarRelatorios= new JMenuItem("Gerar relatórios");
		gerarRelatorios.addActionListener(new OuvinteBtContabilidade(this));
		menurelatorios.add(gerarRelatorios);
		menuBar.add(menurelatorios);
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
	
	JTable tabela;
	DefaultTableModel modelo;
	
	public void adicionarJTableFuncionarios() {
		ControladorFuncionario control= new ControladorFuncionario();
		modelo = new DefaultTableModel();
		
		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("Cargo");
		
		for(Funcionario func:control.listarF()){
			if(func.getCargo().equals("Admin")==false) {
				Object[] linha = new Object[]{
					func.getIdentificacao(),func.getNome(),func.getCargo()
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
		JButton gerenciarPizzas= new JButtonPersonalizado("Gerenciar pizzas", 30, 320, 200, 25);
		gerenciarPizzas.addActionListener(new OuvinteBtGerencia(this));
		add(gerenciarPizzas);
		JButton excluir= new JButtonPersonalizado("Excluir Funcionario", 30, 355, 200, 25);
		excluir.addActionListener(new OuvinteBtExcluir(this));
		add(excluir);
		
	}
	
	private class OuvinteBtCadastrar implements ActionListener{
		private JanelaGerenciarFuncionarios janela;
		
		public OuvinteBtCadastrar(JanelaGerenciarFuncionarios janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaCadastroDeFuncionario(janela).setLocationRelativeTo(janela);
			
		}
		
	}
	private class OuvinteBtEditar implements ActionListener{
		private JanelaGerenciarFuncionarios janela;
		
		public OuvinteBtEditar(JanelaGerenciarFuncionarios janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaEditarFuncionario(janela).setLocationRelativeTo(janela);
			
		}
		
	}
	private class OuvinteBtExcluir implements ActionListener{
		private JanelaGerenciarFuncionarios janela;
		
		public OuvinteBtExcluir(JanelaGerenciarFuncionarios janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			Object codigo= modelo.getValueAt(tabela.getSelectedRow(), 0);
			String id=(String) codigo;
			ControladorFuncionario controlador= new ControladorFuncionario();
			controlador.excluirF(id);
			JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso !");
			janela.dispose();
			new JanelaGerenciarFuncionarios().setLocationRelativeTo(null);
		}
		
	}
	private class OuvinteBtGerencia implements ActionListener{
		private JanelaGerenciarFuncionarios janela;
		
		public OuvinteBtGerencia(JanelaGerenciarFuncionarios janela) {
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			new JanelaGerenciarPizza().setLocationRelativeTo(janela);
			janela.dispose();
			
		}
		
	}

	}
