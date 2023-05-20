
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaBanco {

	public static String consultar(String nome) throws SQLException {
		Connection conexao = ConexaoBanco.getConexao();
		String sql = "SELECT * FROM cadastro WHERE nome = ?";
		try { 
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, nome);
			ResultSet resultSet = statement.executeQuery();
			String resultado = "";
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nomeConsulta = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String telefone = resultSet.getString("telefone");
				resultado += (id + " - " + nomeConsulta + " - " + email + " - " + telefone + "\n");
			}
			return resultado;
		} finally {
			ConexaoBanco.fecharConexao();
		}
	}

	public static void adicionarCadastro(String nome, String email, String telefone) throws SQLException {
		Connection conexao = ConexaoBanco.getConexao();
		String sql = "INSERT INTO cadastro (nome, email, telefone) VALUES (?, ?, ?)";
		try {
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, nome);
			statement.setString(2, email);
			statement.setString(3, telefone);
			statement.executeUpdate();
		} finally {
			ConexaoBanco.fecharConexao();
		}
	}

	public static ConsultaBanco getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
