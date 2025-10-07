package control;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SvAdicionarProduto
 */
@WebServlet("/SvAdicionarProduto")
public class SvAdicionarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvAdicionarProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Produto p = new Produto();
		String codigo = request.getParameter("codigo");
		  if (p.localizar(codigo)) {

	            // Recupera a lista de alunos da sessão (se não existir, cria uma nova lista)
	            @SuppressWarnings("unchecked")
	            List<Produto> lista = (List<Produto>) request.getSession().getAttribute("produtosAdicionados");

	            double total =  request.getSession().getAttribute("valorTotal") == null ? 0.0 : (double) request.getSession().getAttribute("valorTotal");
	            
	            if (lista == null) {
	                lista = new ArrayList<>();
	            }

	            // Verifica se o aluno já foi adicionado à lista
	            if (!lista.contains(p)) {
	                lista.add(p); // Adiciona o aluno à lista
	                total += Double.parseDouble(p.getPreco());
	                request.getSession().setAttribute("valorTotal", total);
	                request.getSession().setAttribute("produtosAdicionados", lista); // Atualiza a lista na sessão
	                request.setAttribute("msg", "PULSEIRA ADICIONADO À LISTA.");
	                request.setAttribute("status", "ok");
	            } else {
	                request.setAttribute("status", "erro");
	            }

	        } else {
	            // Caso não encontre o aluno, exibe a mensagem de erro
	            request.setAttribute("msg", p.getMsg());
	            request.setAttribute("status", "erro");
	        }

	        // Redireciona para a página de feedback de adição de aluno
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
