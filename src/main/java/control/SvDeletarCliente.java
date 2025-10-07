package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

import java.io.IOException;

/**
 * Servlet implementation class SvDeletarCliente
 */
@WebServlet("/SvDeletarCliente")
public class SvDeletarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvDeletarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Integer.parseInt(request.getParameter("idLocalizar"));

		Cliente c = new Cliente();
		
		if (c.deletarCliente(id)) {
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
