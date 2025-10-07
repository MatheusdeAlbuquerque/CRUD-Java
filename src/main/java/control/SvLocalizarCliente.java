package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

import java.io.IOException;

/**
 * Servlet implementation class SvLocalizarCliente
 */
@WebServlet("/SvLocalizarCliente")
public class SvLocalizarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvLocalizarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String localizarCpf = request.getParameter("localizarCpf");
		Cliente c = new Cliente();
		
		if (c.localizar(localizarCpf)) {
			request.setAttribute("statusLocalizar", "ok");
			request.setAttribute("Cliente", c);
			request.setAttribute("msgLocalizar", c.getMsg());
		} else {
			request.setAttribute("statusLocalizar", "erro");
			request.setAttribute("Cliente", null);
			request.setAttribute("msgLocalizar", c.getMsg());
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
