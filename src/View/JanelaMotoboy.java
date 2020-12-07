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

public class JanelaMotoboy  extends JanelaPadraoGerencia{
	public JanelaMotoboy(){
		ControladorFuncionario control = new ControladorFuncionario();
		String[] strings = control.usuarioL().split("-");
		String titulo = "janela de Motoboy"+".   Funcionário: "+strings[1]+"  !";
		this.setTitle(titulo);
		adicionarJMenuBar(this);
		adicionarJButtons();
		adicionarJTableMotoboy();
		backGround();
		this.setVisible(true);
	}
	
	public void adicionarJMenuBar(JanelaMotoboy janela) {
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
	
	public void adicionarJTableMotoboy() {
		ControladorPedido control = new ControladorPedido();
		ControladorCliente controladorCli= new ControladorCliente();

		modelo = new DefaultTableModel();
		
		modelo.addColumn("ID");
		modelo.addColumn("CPF");
		modelo.addColumn("Sabor");
		modelo.addColumn("Tamanho");
		modelo.addColumn("Qtd");
		modelo.addColumn("Status");
		modelo.addColumn("Endereço");

		
		for(Cliente cli: controladorCli.listarC()) {
			boolean bandeira = true;
			for(Pedido pedido:control.listarP()) {
				if(cli.getIdentificacao().equals(pedido.getCpfCliente()) && (pedido.getStatus().equals("Fazendo") || pedido.getStatus().equals("Pedido feito"))) {
					bandeira= false;
					break;
				}
			}
			if(bandeira==true) {
				for(Pedido pedido:control.listarP()) {
					if(cli.getIdentificacao().equals(pedido.getCpfCliente()) && (pedido.getStatus().equals("Pronto"))) {
						String endereco = cli.getEndereco();
						Object[] linha = new Object[] {
								pedido.getID(),pedido.getCpfCliente(),pedido.getPizza().getTipo()
								,pedido.getPizza().getTamanho(),pedido.getQtdPizzas(),pedido.getStatus(),endereco
						};
						modelo.addRow(linha);
					}
				}
			}
		}
		tabela = new JTable(modelo);
	
		JScrollPane conteiner = new JScrollPane(tabela);
		conteiner.setBounds(260, 160, 805, 460);
		add(conteiner);
	}
	
	public void adicionarJButtons() {
		JButton entregue = new JButtonPersonalizado("Finalizar Pedido", 10, 315, 240, 25);
		entregue.addActionListener(new OuvinteBtEntregue(this));
		add(entregue);
	}
	
	
	private class OuvinteBtEntregue implements ActionListener{
		private JanelaMotoboy jan;
		
		public OuvinteBtEntregue(JanelaMotoboy jan){
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
				controlador.editarStatusMotoboy(cpf,"Entregue");
				JOptionPane.showMessageDialog(null, "Status editado com sucesso !");
				jan.dispose();
				new JanelaMotoboy().setLocationRelativeTo(null);
			} catch (IdInexistenteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		}
			
	}
	
}
