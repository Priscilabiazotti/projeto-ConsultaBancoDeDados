
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaBanco {

	public static void consultar() {
		Connection conexao = ConexaoBanco.getConexao();
		String sql = "SELECT * FROM cadastro";
		try {
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				// Adicione os outros campos da tabela
				System.out.println(id + " - " + nome);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a consulta.");
			e.printStackTrace();
		} finally {
			ConexaoBanco.fecharConexao();
		}
	}

	public static ConsultaBanco getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
