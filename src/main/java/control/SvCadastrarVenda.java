package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Produto;
import model.Venda;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class SvCadastrarVenda
 */
@WebServlet("/SvCadastrarVenda")
public class SvCadastrarVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvCadastrarVenda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String formaPagamento = request.getParameter("formaPagamento");
		String valorTotal = request.getParameter("valorTotal");
		String status = "Ativo";
		
        Cliente cliente = (Cliente) request.getSession().getAttribute("Cliente");
        @SuppressWarnings("unchecked")
        List<Produto> produtos = (List<Produto>) request.getSession().getAttribute("produtosAdicionados");
        
        Venda venda = new Venda(formaPagamento, valorTotal, status, cliente, produtos);
        
        if (venda.cadastrar()) {
        	request.getSession().removeAttribute("valorTotal");
        	request.getSession().removeAttribute("produtosAdicionados");
        	request.getSession().removeAttribute("Cliente");
    		request.getSession().removeAttribute("Produto");

        	request.setAttribute("status", "ok");
			request.setAttribute("msg", venda.getMsg());
		} else {
			request.setAttribute("status", "ok");
			request.setAttribute("msg", venda.getMsg());
		}
		request.getRequestDispatcher("visualizarVenda.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
