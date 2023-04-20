
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL_CONEXAO = "jdbc:mysql://localhost:3306/cadastro?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "Priscila";
    private static final String SENHA = "Qç3504Pa#*";
    private static Connection conexao = null;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Erro ao estabelecer conexão com o banco de dados: " + e.getMessage());
            }
        }
        return conexao;
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}
