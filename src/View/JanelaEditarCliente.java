package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Controll.ControladorCliente;

public class JanelaEditarCliente extends JanelaPadrao{
	private JTextField nome;
	private JTextField cpf;
	private JTextField endereco;
	private JTextField telefone;
	
	private JanelaGerenciarClientes j;
	
	public JanelaEditarCliente(JanelaGerenciarClientes j) {
		this.j=j;
		this.setTitle("Editar Cliente");
		adicionarJLabel();
		adicionarJTextFields();
		adicionarJButtons();
		this.setVisible(true);
	}
	public void adicionarJLabel() {
		JLabel interrogacao= new JLabelPersonalizado("", 10, 10, 40, 40);
		interrogacao.setIcon(new ImageIcon("Interrogacao.png"));
		interrogacao.setToolTipText("Digite o cpf do cliente que você deseja editar.");
		JLabel nome=new LabelPequeno("Nome:", 120, 160, 100, 30);
		JLabel cpf=new LabelPequeno("CPF:", 120, 210, 100, 30);
		JLabel endereco=new LabelPequeno("Endereco:", 120, 260, 100, 30);
		JLabel telefone=new LabelPequeno("Telefone:", 120, 310, 100, 30);
		add(nome);
		add(cpf);
		add(endereco);
		add(telefone);
		add(interrogacao);
	}
	
	public void adicionarJTextFields() {
		nome= new JTextFieldPersonalizado(200, 160, 180, 30);
		cpf= new JTextFieldPersonalizado(200, 210, 180, 30);
		endereco= new JTextFieldPersonalizado(230, 260, 150, 30);
		telefone= new JTextFieldPersonalizado(230, 310, 150, 30);
		add(nome);
		add(endereco);
		add(cpf);
		add(telefone);
	}
	public void adicionarJButtons() {
		JButton editar=new JButtonPersonalizado("Editar", 90, 380, 150, 30);
		JButton cancelar=new JButtonPersonalizado("Cancelar", 280, 380, 150, 30);
		editar.setIcon(new ImageIcon("Salvar.png"));
		editar.addActionListener(new OuvinteBtEditar(this, j));
		cancelar.setIcon(new ImageIcon("Cancelar.png"));
		cancelar.addActionListener(new OuvinteCancelar(this));
		add(editar);
		add(cancelar);
	}
	private class OuvinteBtEditar implements ActionListener{
		private JanelaEditarCliente janela;
		private JanelaGerenciarClientes j;
		
		public OuvinteBtEditar(JanelaEditarCliente janela,JanelaGerenciarClientes j) {
			this.janela=janela;
			this.j=j;
		}
		public void actionPerformed(ActionEvent e) {
			ControladorCliente controlador= new ControladorCliente();
			try {
				controlador.editarC(nome.getText(),endereco.getText(),cpf.getText(),telefone.getText());
				JOptionPane.showMessageDialog(null, "Cliente editado com sucesso");
				janela.dispose();
				j.dispose();
				new JanelaGerenciarClientes().setLocationRelativeTo(null);
			} catch (DadosNaoPreenchidosException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
		}
		
	}

}
