
public class Autenticacao {

		public static boolean validar(String usuario, String senha) {
			// Lógica de autenticação aqui
			if (usuario.equals("admin") && senha.equals("123")) {
				return true;
			} else {
				return false;
			}
		}
	}