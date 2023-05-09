
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private boolean autenticado = false;

	public boolean estaAutenticado() {
		return autenticado;
	}

	public String getUsuario() {
		return textField.getText();
	}

	public String getSenha() {
		return new String(passwordField.getPassword());
	}

	/**
	 * Create the dialog.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setTitle("Login");
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setBounds(10, 11, 46, 14);
		contentPanel.add(lblUsurio);

		textField = new JTextField();
		textField.setBounds(66, 8, 250, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 42, 46, 14);
		contentPanel.add(lblSenha);

		passwordField = new JPasswordField();
		passwordField.setBounds(66, 39, 250, 20);
		contentPanel.add(passwordField);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // verifica se as credenciais são válidas
		        if (Autenticacao.validar(getUsuario(), getSenha())) {
		            autenticado = true;
		            dispose();
		        } else {
		            autenticado = false;
		            textField.setText("");
		            passwordField.setText("");
		            textField.requestFocus();
		        }
		    }
		});
		buttonPane.add(loginButton);
		getRootPane().setDefaultButton(loginButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticado = false;
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}
}

