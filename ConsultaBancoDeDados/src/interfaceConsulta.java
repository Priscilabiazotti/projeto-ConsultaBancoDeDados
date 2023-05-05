
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class interfaceConsulta {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnConsultar;
	private JButton btnLimpar;
	private JButton btnSair;
	private JLabel lblTitulo;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblSenha;
	private JPasswordField txtSenha;


	public class Autenticacao {

		public static boolean validar(String usuario, String senha) {
			// Lógica de autenticação aqui
			if (usuario.equals("admin") && senha.equals("123")) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaceConsulta window = new interfaceConsulta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public interfaceConsulta() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNome = new JLabel("Nome:");
		panel.add(lblNome);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(20);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = textField.getText();
					String resultado = ConsultaBanco.consultar(nome);
					if (resultado.equals("")) {
						textArea.setText("Nome não encontrado no banco de dados.");
					} else {
						textArea.setText(resultado);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();	
				}
			}
		});
		panel.add(btnConsultar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textArea.setText("");
			}
		});
		panel.add(btnLimpar);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnSair);

		lblTitulo = new JLabel("Resultados da consulta:");
		frame.getContentPane().add(lblTitulo, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.getContentPane().add(panelLogin, BorderLayout.SOUTH);

		lblUsuario = new JLabel("Usuário:");
		panelLogin.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		panelLogin.add(txtUsuario);

		lblSenha = new JLabel("Senha:");
		panelLogin.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		panelLogin.add(txtSenha);

		// criação do botão de login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // obtém as credenciais do usuário
		        String usuario = txtUsuario.getText();
		        String senha = new String(txtSenha.getPassword());

		        // verifica se as credenciais são válidas
		        if (Autenticacao.validar(usuario, senha)) {
		            // se forem válidas, habilita a consulta
		            textField.setEnabled(true);
		            btnConsultar.setEnabled(true);
		            btnLimpar.setEnabled(true);
		            textArea.setEnabled(true);
		            JOptionPane.showMessageDialog(frame, "Login realizado com sucesso.");
		        } else {
		            // se não forem válidas, desabilita a consulta e exibe mensagem de erro
		            textField.setEnabled(false);
		            btnConsultar.setEnabled(false);
		            btnLimpar.setEnabled(false);
		            textArea.setEnabled(false);
		            JOptionPane.showMessageDialog(frame, "Credenciais inválidas. Tente novamente.");
		        }
		    }
		});
		panelLogin.add(btnLogin);
     }
}
