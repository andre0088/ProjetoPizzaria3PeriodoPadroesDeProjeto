package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controll.ControladorCliente;
import Controll.ControladorPizza;

public class JanelaCadastroCliente extends JanelaPadrao{
	private JanelaGerenciarClientes j;
	
	private JTextField nome;
	private JTextField cpf;
	private JTextField endereco;
	private JTextField telefone;
	private JTextField id;
	private JTextField qtdPizzas;
	private JComboBox<String> saborTamanhos;
	private JComboBox<String>status;
	
	public JanelaCadastroCliente(JanelaGerenciarClientes j) {
		this.j=j;
		this.setTitle("Cadastro de clientes");
		adicionarJLabel();
		adicionarJTextFields();
		adicionarJComboBox();
		adicionarJButtons();
		this.setVisible(true);
	}
	public void adicionarJLabel() {
		JLabel nome=new LabelPequeno("Nome:", 20, 160, 100, 30);
		JLabel cpf=new LabelPequeno("CPF:", 20, 210, 100, 30);
		JLabel endereco=new LabelPequeno("Endereco:",20, 260, 100, 30);
		JLabel telefone=new LabelPequeno("Telefone:", 20, 310, 100, 30);
		JLabel status=new LabelPequeno("Status:", 270, 310, 100, 30);
		JLabel qtdPizzas=new LabelPequeno("Qtd Pizzas:", 270, 260, 95, 30); 
		JLabel id=new LabelPequeno("ID do pedido:", 270, 160, 115, 30);
		JLabel sabor=new LabelPequeno("Sabor:", 270, 210, 60, 30);
		add(nome);
		add(cpf);
		add(endereco);
		add(telefone);
		add(id);
		add(sabor);
		add(status);
		add(qtdPizzas);
	}
	public void adicionarJTextFields() {
		nome= new JTextFieldPersonalizado(90, 160, 160, 30);
		qtdPizzas= new JTextFieldPersonalizado(380, 260, 105, 30);
		cpf= new JTextFieldPersonalizado(90, 210, 160, 30);
		endereco= new JTextFieldPersonalizado(120, 260, 130, 30);
		telefone= new JTextFieldPersonalizado(120, 310, 130, 30);
		id= new JTextFieldPersonalizado(395, 160,90, 30);
		add(nome);
		add(endereco);
		add(cpf);
		add(telefone);
		add(id);
		add(qtdPizzas);
	}
	public void adicionarJComboBox() {
		ControladorPizza control= new ControladorPizza();
		String[] variedades= new String[control.listarP().size()];
		for(int i=0;i<control.listarP().size();i++) {
			variedades[i]= control.listarP().get(i).getTipo()+"-"+control.listarP().get(i).getTamanho();
		}
		
		saborTamanhos=new JComboBox<String>(variedades);
		saborTamanhos.setBounds(330,210,155,30);

		String[]opcoes= new String[]{"Pedido feito"};
		status=new JComboBox<String>(opcoes);
		status.setBounds(340,310,145,30);
		add(status);
		add(saborTamanhos);
	}
	
	public void adicionarJButtons() {
		JButton cadastrar=new JButtonPersonalizado("Cadastrar", 80, 410, 160, 30);
		JButton cancelar=new JButtonPersonalizado("Cancelar", 280, 410, 160, 30);
		cadastrar.setIcon(new ImageIcon("Salvar.png"));
		cadastrar.addActionListener(new OuvinteBtCadastrar(this, j));
		cancelar.setIcon(new ImageIcon("Cancelar.png"));
		cancelar.addActionListener(new OuvinteCancelar(this));
		add(cadastrar);
		add(cancelar);
	}
	
	private class OuvinteBtCadastrar implements ActionListener{
		private JanelaCadastroCliente janela;
		private JanelaGerenciarClientes j;
		
		public OuvinteBtCadastrar(JanelaCadastroCliente janela,JanelaGerenciarClientes j) {
			this.j=j;
			this.janela=janela;
		}
		public void actionPerformed(ActionEvent e) {
			float precoFinal=0;
			ControladorCliente controlador= new ControladorCliente();
			ControladorPizza control = new ControladorPizza();
			String s= (String)status.getSelectedItem();
			Date data = new Date();
			String saborT=(String) saborTamanhos.getSelectedItem();
			String[] st= saborT.split("-");
			String sabor= st[0];
			String tamanho=st[1];
			precoFinal=control.retornarPrecoPizza(sabor, tamanho);
			int qtd=0;
			if(qtdPizzas.getText().equals("")==false) {
				qtd=Integer.parseInt(qtdPizzas.getText());
			}
			try {
				controlador.cadastroEPedidoC(nome.getText(),cpf.getText(),endereco.getText(),telefone.getText(),data,id.getText(),precoFinal,qtd,s,sabor,tamanho);
				JOptionPane.showMessageDialog(null, "Cliente e pedido cadastrados com sucesso !");
				janela.dispose();
				j.dispose();
				new JanelaGerenciarClientes().setLocationRelativeTo(null);
			} catch (DadosNaoPreenchidosException | ClienteJaExistenteException | SaborInexistenteException | PedidoJaExistenteException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		
	}


}
