
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField; // importação adicionada

public class interfaceConsulta {

    private JFrame frame;

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
                // Criar a nova janela de consulta
                JFrame consultaFrame = new JFrame("Consultar Cliente");
                consultaFrame.setSize(400, 200);
                consultaFrame.setLocationRelativeTo(null);

                // Adicionar componentes Swing à nova janela
                JLabel nomeLabel = new JLabel("Nome:");
                nomeLabel.setBounds(10, 10, 80, 25);
                consultaFrame.add(nomeLabel);
                
                JTextField nomeField = new JTextField(20); // criar o campo de texto
                nomeField.setBounds(100, 10, 200, 25);
                consultaFrame.add(nomeField);

                // Tornar a nova janela visível
                consultaFrame.setVisible(true);
            }
        });
        btnConsulta.setBounds(167, 106, 97, 25);
        frame.getContentPane().add(btnConsulta);
        
        frame.setVisible(true);
    }
}
