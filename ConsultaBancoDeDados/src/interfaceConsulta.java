
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class interfaceConsulta {

	private JFrame frame;
	private JTextField nomeField;
	private JTextArea resultadoArea;

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
		frame.getContentPane().setLayout(null);

		JButton btnConsulta = new JButton("Consultar");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// obter o texto do campo de texto de nome
				String nome = nomeField.getText();

				// executar a consulta no banco de dados
				try {
					String resultado = ConsultaBanco.consultar(nome);

					// exibir o resultado na área de texto
					resultadoArea.setText(resultado);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frame, "Erro ao executar a consulta: " + e.getMessage(),
							"Erro de consulta", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		btnConsulta.setBounds(167, 106, 97, 25);
		frame.getContentPane().add(btnConsulta);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeField.setText("");
				resultadoArea.setText("");
			}
		});
		btnLimpar.setBounds(276, 106, 97, 25);
		frame.getContentPane().add(btnLimpar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 29, 56, 16);
		frame.getContentPane().add(lblNome);

		nomeField = new JTextField();
		nomeField.setBounds(67, 26, 250, 22);
		frame.getContentPane().add(nomeField);
		nomeField.setColumns(10);

		resultadoArea = new JTextArea();
		resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		resultadoArea.setBounds(12, 72, 410, 168);
		frame.getContentPane().add(resultadoArea);

		JLabel lblResultado = new JLabel("Resultado:");
		lblResultado.setBounds(12, 53, 73, 16);
		frame.getContentPane().add(lblResultado);

		resultadoArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = resultadoArea.getSelectedIndex();
				if (index >= 0) {
					String registro = resultadoArea.getText().split("\n")[index];
					String[] campos = registro.split("-");
					String email = campos[2].trim();
					String telefone = campos[3].trim();
					JOptionPane.showMessageDialog(frame, "E-mail: " + email + "\nTelefone: " + telefone,
							"Informações adicionais", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		frame.setVisible(true);
	}
}
