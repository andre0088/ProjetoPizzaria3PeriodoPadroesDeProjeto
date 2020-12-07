package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controll.ControladorFuncionario;

public class JanelaCadastroDeFuncionario extends JanelaPadraoCadastroEdicaoFun{
	private JanelaGerenciarFuncionarios janelaGerencia;
	
	public JanelaCadastroDeFuncionario() {
		this.setTitle("Cadastro de funcionários");
		adicionarJButtons();
		this.setVisible(true);
	}
	
	public JanelaCadastroDeFuncionario(JanelaGerenciarFuncionarios janelaGerencia) {
		this.janelaGerencia=janelaGerencia;
		this.setTitle("Cadastro de funcionários");
		adicionarJButtons();
		this.setVisible(true);

	}

	public void adicionarJButtons() {
		JButton cadastrar = new JButtonPersonalizado("Cadastrar", 80, 390, 160, 30);
		cadastrar.setIcon(new ImageIcon("Salvar.png"));
		cadastrar.addActionListener(new OuvinteBtCadastrar(this, janelaGerencia));
		JButton cancelar = new JButtonPersonalizado("Cancelar", 270, 390, 160, 30);
		cancelar.setIcon(new ImageIcon("Cancelar.png"));
		cancelar.addActionListener(new OuvinteCancelar(this));
		add(cadastrar);
		add(cancelar);
	}
	
	private class OuvinteBtCadastrar implements ActionListener{
		private JanelaCadastroDeFuncionario janela;
		private JanelaGerenciarFuncionarios janelaGerencia;
		
		public OuvinteBtCadastrar(JanelaCadastroDeFuncionario janela,JanelaGerenciarFuncionarios janelaGerencia) {
			this.janela=janela;
			this.janelaGerencia=janelaGerencia;
		}
		public void actionPerformed(ActionEvent e) {
			ControladorFuncionario controlador= new ControladorFuncionario();
			String cargo="";
			try {
				cargo=(String)cargoMultiplo.getSelectedItem();
				String password=new String(senha.getPassword());
				controlador.cadastrarF(nome.getText(),login.getText(),id.getText(),password,cargo);
				JOptionPane.showMessageDialog(null,"Funcionário cadastrado com sucesso !");
				janela.dispose();
				janelaGerencia.dispose();
				new JanelaGerenciarFuncionarios();
				
			} catch (UsuarioJaExistenteException | DadosNaoPreenchidosException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		
	}
	
}
