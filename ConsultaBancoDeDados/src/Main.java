import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static Connection conexao = null;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/cadastro?user=Priscila&password=Qç3504Pa#*";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(url);
            System.out.println("Conexão estabelecida com sucesso!");
            conexao.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do MySQL não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao banco de dados!");
            e.printStackTrace();
        }
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão com o banco de dados.");
                e.printStackTrace();
            }
        }
    }
}
