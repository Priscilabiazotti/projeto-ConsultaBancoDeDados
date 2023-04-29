
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
				// Adicione os outros campos da tabela
				resultado += (id + " - " + nomeConsulta + "\n");
			}
			return resultado;
		} finally {
			ConexaoBanco.fecharConexao();
		}
	}

	public static ConsultaBanco getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
