package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

import java.io.IOException;

/**
 * Servlet implementation class SvLogin
 */
@WebServlet("/SvLogin")
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate(); // encerra totalmente a sessão, toda vez que eu faço um login novo
		try {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");	
			if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
				request.getSession().setAttribute("usuarioLogado", "admin"); 
				request.setAttribute("status", "ok");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			} else {// Localizar um usuario.
				Cliente clienteUsuario = new Cliente();
				if (clienteUsuario.verficarLogin(login, senha)) {
					request.getSession().setAttribute("usuarioCliente", clienteUsuario); // guarda na sessao
					request.setAttribute("status", "ok");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				} else {
					request.setAttribute("status", "erro");
					request.setAttribute("msg", clienteUsuario.getMsg());
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
