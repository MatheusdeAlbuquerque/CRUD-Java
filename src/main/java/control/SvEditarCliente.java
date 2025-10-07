package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

import java.io.IOException;

/**
 * Servlet implementation class SvEditarCliente
 */
@WebServlet("/SvEditarCliente")
public class SvEditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvEditarCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente c = new Cliente();

		c.setId(Long.parseLong(request.getParameter("idLocalizar")));
		c.setNome(request.getParameter("nome"));
		c.setCpf(request.getParameter("cpf"));
		c.setEndereco(request.getParameter("endereco"));
		c.setEstado(request.getParameter("estado"));
		c.setDataNascimento(request.getParameter("dataNascimento"));
		c.setSenha(request.getParameter("senha"));
		c.setSexo(request.getParameter("sexo"));
		c.setTelefone(request.getParameter("telefone"));

		
		if (c.editarCliente()) {
			request.setAttribute("status", "ok");
			request.setAttribute("msg", c.getMsg());
			request.setAttribute("Cliente", c);
			request.setAttribute("statusLocalizar", "ok");
		} else {
			request.setAttribute("statusLocalizar", "erro");
			request.setAttribute("status", "erro");
			request.setAttribute("msg", c.getMsg());
			request.setAttribute("Cliente", c);
		}
		request.getRequestDispatcher("cliente.jsp").forward(request, response);

		
		/*
		if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
			String nome = request.getParameter("nome");
			System.out.println("passei aqui");

			if (c.editarNome(id, nome)) {
				request.setAttribute("status", "ok");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			} else {
				request.setAttribute("status", "erro");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			}
		}
		if (request.getParameter("cpf") != null && !request.getParameter("cpf").isEmpty()) {
			String cpf = request.getParameter("cpf");
			if (c.editarCpf(id, cpf)) {
				request.setAttribute("Cliente", c);
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("status", "ok");
			} else {
				request.setAttribute("status", "erro");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			}
		}

		if (request.getParameter("endereco") != null && !request.getParameter("endereco").isEmpty()) {
			String endereco = request.getParameter("endereco");
			if (c.editarEndereco(id, endereco)) {
				request.setAttribute("Cliente", c);
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("status", "ok");
			} else {
				request.setAttribute("status", "erro");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			}
		}
		if (request.getParameter("estado") != null && !request.getParameter("estado").isEmpty()) {
			String estado = request.getParameter("estado");
			if (c.editarEstado(id, estado)) {
				request.setAttribute("Cliente", c);
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("status", "ok");
			} else {
				request.setAttribute("status", "erro");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			}
		}
		if (request.getParameter("dataNascimento") != null && !request.getParameter("dataNascimento").isEmpty()) {
			String dataNascimento = request.getParameter("dataNascimento");
			if (c.editarDataNascimento(id, dataNascimento)) {
				request.setAttribute("Cliente", c);
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("status", "ok");
			} else {
				request.setAttribute("status", "erro");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			}
		}
		if (request.getParameter("telefone") != null && !request.getParameter("telefone").isEmpty()) {
			String telefone = request.getParameter("telefone");
			if (c.editarTelefone(id, telefone)) {
				request.setAttribute("Cliente", c);
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("status", "ok");
			} else {
				request.setAttribute("status", "erro");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			}
		}
		if (request.getParameter("sexo") != null && !request.getParameter("sexo").isEmpty()) {
			String sexo = request.getParameter("sexo");
			if (c.editarSexo(id, sexo)) {
				request.setAttribute("Cliente", c);
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("status", "ok");
			} else {
				request.setAttribute("status", "erro");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			}
		}
		if (request.getParameter("senha") != null && !request.getParameter("senha").isEmpty()) {
			String senha = request.getParameter("senha");
			if (c.editarSenha(id, senha)) {
				request.setAttribute("Cliente", c);
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("status", "ok");
			} else {
				request.setAttribute("status", "erro");
				request.setAttribute("msgEditar", c.getMsg());
				request.setAttribute("Cliente", c);
			}
		}
		request.getRequestDispatcher("cliente.jsp").forward(request, response);
*/		
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
