package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

import java.io.IOException;

/**
 * Servlet implementation class SvCadastrarCliente
 */
@WebServlet("/SvCadastrarCliente")
public class SvCadastrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvCadastrarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String endereco = request.getParameter("endereco");
		String estado = request.getParameter("estado");
		String dataNascimento = request.getParameter("dataNascimento");
		String telefone = request.getParameter("telefone");
		String senha = request.getParameter("senha");
		String sexo = request.getParameter("sexo");
		
		Cliente c = new Cliente(nome, cpf, endereco, estado, dataNascimento, telefone, senha, sexo);
		
		if (c.cadastrar()) {
			request.setAttribute("status", "ok");
			request.setAttribute("msg", c.getMsg());
		} else {
			request.setAttribute("status", "erro");
			request.setAttribute("msg", c.getMsg());
		}
		request.getRequestDispatcher("cliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
