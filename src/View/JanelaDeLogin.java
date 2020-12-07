package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controll.ControladorFuncionario;



public class JanelaDeLogin extends JanelaPadrao{
	
	private JTextField login;
	private JPasswordField senha;
	
	public JanelaDeLogin(){
		this.setTitle("Janela de Login");
		adicionarJLabel();
		adicionarJTextFields();
		adicionarJButtons();
		this.setVisible(true);
	}
	
	public void adicionarJLabel(){
		JLabel login= new LabelPequeno("Login: ",220,170,100,50);
		JLabel senha= new LabelPequeno("Senha: ",220,270,100,50);
		add(senha);
		add(login);

	}
	
	public void adicionarJTextFields(){
		login= new JTextFieldPersonalizado(160,230, 170, 30);
		senha= new JPasswordField();
		senha.setBounds(160,330, 170, 30);
		senha.setHorizontalAlignment(JPasswordField.CENTER);
		add(senha);
		add(login);
	}
	
	public void adicionarJButtons() {
		JButton entrar= new JButtonPersonalizado("Entrar",175,400,140,36);
		entrar.addActionListener(new OuvinteBtEntrar(this));
		entrar.setIcon(new ImageIcon("Entrar.png"));
		add(entrar);

	}
	
	private class OuvinteBtEntrar implements ActionListener{
		private JanelaDeLogin janela;
		public OuvinteBtEntrar(JanelaDeLogin j) {
			janela = j;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				ControladorFuncionario controlador = new ControladorFuncionario();
				String s=new String(senha.getPassword());
				if(controlador.fzrLogin(login.getText(), s).getCargo().equals("Gerente") || controlador.fzrLogin(login.getText(), s).getCargo().equals("Admin")) {
					janela.dispose();
					new JanelaGerenciarFuncionarios();
				}else if(controlador.fzrLogin(login.getText(), s).getCargo().equals("Atendente")){
					janela.dispose();
					new JanelaGerenciarClientes();
				}else if(controlador.fzrLogin(login.getText(), s).getCargo().equals("Motoboy")){
					janela.dispose();
					new JanelaMotoboy();
				}else{
					janela.dispose();
					new JanelaPizzaiollo();
				}
			} catch (LoginOuSenhaInvalidosException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
		}
		
	}
	
}
