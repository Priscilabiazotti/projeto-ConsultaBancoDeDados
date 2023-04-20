
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
                ConsultaBanco.consultar();
            }
        });
        btnConsulta.setBounds(167, 106, 97, 25);
        frame.getContentPane().add(btnConsulta);
        
        frame.setVisible(true);
    }
}
