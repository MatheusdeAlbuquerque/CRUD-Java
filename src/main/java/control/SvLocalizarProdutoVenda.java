package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

import java.io.IOException;

/**
 * Servlet implementation class SvLocalizarProdutoVenda
 */
@WebServlet("/SvLocalizarProdutoVenda")
public class SvLocalizarProdutoVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvLocalizarProdutoVenda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Produto p = new Produto();
        String codigo = request.getParameter("localizarCodigo");
        
        if (p.localizar(codigo)) {
        	request.setAttribute("statusLocalizar", "ok");
			request.getSession().setAttribute("Produto", p);
			request.setAttribute("msgLocalizar", p.getMsg());
		} else {
			request.setAttribute("statusLocalizar", "erro");
			request.setAttribute("Produto", null);
			request.setAttribute("msgLocalizar", p.getMsg());
		}
		request.getRequestDispatcher("vendaProduto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
