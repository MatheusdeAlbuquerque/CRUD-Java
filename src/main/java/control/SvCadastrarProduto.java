package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

import java.io.IOException;

/**
 * Servlet implementation class SvCadastrarProduto
 */
@WebServlet("/SvCadastrarProduto")
public class SvCadastrarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvCadastrarProduto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String preco = request.getParameter("preco");
	    String estoque = request.getParameter("estoque");
	    

		Produto p = new Produto(codigo, nome, descricao, preco, estoque);

		if (p.cadastrar()) {
			request.setAttribute("status", "ok");
			request.setAttribute("msg", p.getMsg());
		} else {
			request.setAttribute("status", "erro");
			request.setAttribute("msg", p.getMsg());
		}
		request.getRequestDispatcher("produto.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			System.out.println(e);
			request.getRequestDispatcher("produto.jsp").forward(request, response);

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
