package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

import java.io.IOException;

/**
 * Servlet implementation class SvEditarProduto
 */
@WebServlet("/SvEditarProduto")
public class SvEditarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvEditarProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto p = new Produto();
		
		p.setId(Long.parseLong(request.getParameter("idLocalizar")));
		p.setCodigo(request.getParameter("codigo"));
		p.setNome(request.getParameter("nome"));
		p.setDescricao(request.getParameter("descricao"));
		p.setPreco(request.getParameter("preco"));
		p.setEstoque(request.getParameter("estoque"));
		
		
		if (p.editarProduto()) {
			request.setAttribute("status", "ok");
			request.setAttribute("msg", p.getMsg());
			request.setAttribute("Produto", p);
			request.setAttribute("statusLocalizar", "ok");
		} else {
			request.setAttribute("statusLocalizar", "erro");
			request.setAttribute("status", "erro");
			request.setAttribute("msg", p.getMsg());
			request.setAttribute("Produto", p);
		}
		
		request.getRequestDispatcher("produto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
