
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CadastroDAO {

	private Connection conexao;

	public CadastroDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void inserir(Cadastro cadastro) throws SQLException {
		String sql = "INSERT INTO cadastro (nome, email, telefone) VALUES (?, ?, ?)";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, cadastro.getNome());
		statement.setString(2, cadastro.getEmail());
		statement.setString(3, cadastro.getTelefone());
		statement.executeUpdate();
	}

	public void atualizar(Cadastro cadastro) throws SQLException {
		String sql = "UPDATE cadastro SET nome = ?, email = ?, telefone = ? WHERE id = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, cadastro.getNome());
		statement.setString(2, cadastro.getEmail());
		statement.setString(3, cadastro.getTelefone());
		statement.setInt(4, cadastro.getId());
		statement.executeUpdate();
	}

	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM cadastro WHERE id = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
	}

	public Cadastro consultarPorId(int id) throws SQLException {
		String sql = "SELECT * FROM cadastro WHERE id = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			String nome = resultSet.getString("nome");
			String email = resultSet.getString("email");
			String telefone = resultSet.getString("telefone");
			return new Cadastro(id, nome, email, telefone);
		}
		return null;
	}

	public Cadastro consultarPorNome(String nome) throws SQLException {
		String sql = "SELECT * FROM cadastro WHERE nome = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nome);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			String email = resultSet.getString("email");
			String telefone = resultSet.getString("telefone");
			return new Cadastro(id, nome, email, telefone);
		}
		return null;
	}

	public List<Cadastro> consultarTodos() throws SQLException {
		String sql = "SELECT * FROM cadastro";
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		List<Cadastro> cadastros = new ArrayList<>();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String email = resultSet.getString("email");
			String telefone = resultSet.getString("telefone");
			cadastros.add(new Cadastro(id, nome, email, telefone));
		}
		return cadastros;
	}

}
