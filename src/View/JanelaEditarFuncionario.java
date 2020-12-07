package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Controll.ControladorFuncionario;

public class JanelaEditarFuncionario extends JanelaPadraoCadastroEdicaoFun{
	private JanelaGerenciarFuncionarios janelaGerencia;
	
	public JanelaEditarFuncionario(JanelaGerenciarFuncionarios janelaGerencia) {
		this.janelaGerencia=janelaGerencia;
		this.setTitle("Edição de funcionários");
		adicionarComponentes();
		this.setVisible(true);

	}
	
	public void adicionarComponentes() {
		JLabel interrogacao= new JLabelPersonalizado("", 10, 10, 40, 40);
		interrogacao.setIcon(new ImageIcon("Interrogacao.png"));
		interrogacao.setToolTipText("Digite o ID do funcionário que você deseja editar.");
		JButton editar = new JButtonPersonalizado("Editar", 80, 390, 160, 30);
		editar.setIcon(new ImageIcon("Salvar.png"));
		editar.addActionListener(new OuvinteBtEditar(this, janelaGerencia));
		JButton cancelar = new JButtonPersonalizado("Cancelar", 270, 390, 160, 30);
		cancelar.setIcon(new ImageIcon("Cancelar.png"));
		cancelar.addActionListener(new OuvinteCancelar(this));
		add(editar);
		add(cancelar);
		add(interrogacao);
	}
	
	private class OuvinteBtEditar implements ActionListener{
		private JanelaEditarFuncionario janela;
		private JanelaGerenciarFuncionarios janelaGerencia;
		
		public OuvinteBtEditar(JanelaEditarFuncionario j,JanelaGerenciarFuncionarios janelaGerencia) {
			this.janela=j;
			this.janelaGerencia=janelaGerencia;
		}
		public void actionPerformed(ActionEvent e) {
			ControladorFuncionario gerencia= new ControladorFuncionario();
			try {
				String cargo= (String)cargoMultiplo.getSelectedItem();
				String s=new String(senha.getPassword());
				gerencia.editarF(nome.getText(),login.getText(),id.getText(),s,cargo);
				JOptionPane.showMessageDialog(null,"Editado com sucesso !");
				janela.dispose();
				janelaGerencia.dispose();
				new JanelaGerenciarFuncionarios().setLocationRelativeTo(null);
			} catch (UsuarioJaExistenteException | DadosNaoPreenchidosException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		
	}

}
