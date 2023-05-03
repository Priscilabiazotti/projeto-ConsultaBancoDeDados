
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class interfaceConsulta {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnConsultar;
	private JButton btnLimpar;
	private JLabel lblTitulo;

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

		lblTitulo = new JLabel("Resultados da consulta:");
		frame.getContentPane().add(lblTitulo, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

}
