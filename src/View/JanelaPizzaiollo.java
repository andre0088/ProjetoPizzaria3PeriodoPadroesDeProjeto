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
import Controll.ControladorPedido;
import Model.Pedido;

public class JanelaPizzaiollo extends JanelaPadraoGerencia{
	public JanelaPizzaiollo(){
		ControladorFuncionario control = new ControladorFuncionario();
		String[] strings = control.usuarioL().split("-");
		String titulo = "janela de Pizzaiollo"+".   Funcionário: "+strings[1]+"  !";
		this.setTitle(titulo);
		adicionarJMenuBar(this);
		adicionarJButtons();
		adicionarJTablePizzaiollo();
		backGround();
		this.setVisible(true);
	}
	
	public void adicionarJMenuBar(JanelaPizzaiollo janela) {
		JMenuBar menuBar=new JMenuBar();
		janela.setJMenuBar(menuBar);
		JMenu logout= new JMenu("Logout");
		menuBar.add(logout);
		JMenuItem sair= new JMenuItem("Deslogar");
		sair.addActionListener(new OuvinteBtLogout(this));
		logout.add(sair);
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
	
	public void adicionarJTablePizzaiollo() {
		ControladorPedido control = new ControladorPedido();
		modelo = new DefaultTableModel();
		
		modelo.addColumn("ID");
		modelo.addColumn("CPF");
		modelo.addColumn("Sabor");
		modelo.addColumn("Tamanho");
		modelo.addColumn("Qtd");
		modelo.addColumn("Status");
		modelo.addColumn("Ingredientes");

		for(Pedido pedido:control.listarP()){
			if(pedido.getStatus().equals("Pedido feito") ||pedido.getStatus().equals("Fazendo")){
				Object[] linha = new Object[]{
						pedido.getID(),pedido.getCpfCliente(),pedido.getPizza().getTipo(),pedido.getPizza().getTamanho(),
						pedido.getQtdPizzas(),pedido.getStatus(),pedido.getPizza().getIgredientes()
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
		JButton fazendo = new JButtonPersonalizado("Fazendo pizza", 10, 355, 240, 25);
		fazendo.addActionListener(new OuvinteBtFazendo(this));
		add(fazendo);
		JButton pronto = new JButtonPersonalizado("Pizza pronta", 10, 315, 240, 25);
		pronto.addActionListener(new OuvinteBtPronto(this));
		add(pronto);
	}
	
	private class OuvinteBtFazendo implements ActionListener{
		private JanelaPizzaiollo janela;
		
		public OuvinteBtFazendo(JanelaPizzaiollo janela){
			this.janela=janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			ControladorPedido controlador = new ControladorPedido();
			Object codigoCpf = modelo.getValueAt(tabela.getSelectedRow(),1 );
			String cpf= (String)codigoCpf;
			Object codigoId= modelo.getValueAt(tabela.getSelectedRow(),0);
			String id=(String)codigoId;
			Object status=modelo.getValueAt(tabela.getSelectedRow(), 5);
			String stts = (String) status;
			try {
				controlador.editarStatusPizzaiollo(id, cpf,stts,"Fazendo");
				JOptionPane.showMessageDialog(null, "Status editado com sucesso !");
				janela.dispose();
				new JanelaPizzaiollo().setLocationRelativeTo(null);
			} catch (IdInexistenteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		}
		
	}
	
	private class OuvinteBtPronto implements ActionListener{
		private JanelaPizzaiollo jan;
		
		public OuvinteBtPronto(JanelaPizzaiollo jan){
			this.jan=jan;
		}
		
		public void actionPerformed(ActionEvent e) {
			ControladorPedido controlador = new ControladorPedido();
			Object codigoCpf = modelo.getValueAt(tabela.getSelectedRow(),1 );
			String cpf= (String)codigoCpf;
			Object codigoId= modelo.getValueAt(tabela.getSelectedRow(),0);
			String id=(String)codigoId;
			Object status=modelo.getValueAt(tabela.getSelectedRow(), 5);
			String stts = (String) status;
			try {
				controlador.editarStatusPizzaiollo(id, cpf,stts,"Pronto");
				JOptionPane.showMessageDialog(null, "Status editado com sucesso !");
				jan.dispose();
				new JanelaPizzaiollo().setLocationRelativeTo(null);
			} catch (IdInexistenteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		}
			
	}
	
}
