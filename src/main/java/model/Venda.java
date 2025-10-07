package model;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Entity
public class Venda {
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String formaPagamento;
	@Column
	private String valorTotal;
	@Column
	private String status;
	@Transient
	private String msg;

	// variavel do tipo cliente, 1 Cliente tem 1 Venda
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_Venda_Cliente"))
	Cliente cliente;

	// Arraylist de Produto, 1 Venda tem N Produtos
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produto", foreignKey = @ForeignKey(name = "fk_Venda_Produto"))
	List<Produto> listaProdutos;

	public Venda(String formaPagamento, String valorTotal, String status, Cliente cliente, List<Produto> listaProdutos) {
		super();
		this.formaPagamento = formaPagamento;
		this.valorTotal = valorTotal;
		this.status = status;
		this.cliente = cliente;
		this.listaProdutos = listaProdutos;
	}

	public Venda(long id, String formaPagamento, String valorTotal, String status, Cliente cliente, List<Produto> listaProdutos) {
		super();
		this.id = id;
		this.formaPagamento = formaPagamento;
		this.valorTotal = valorTotal;
		this.status = status;
		this.cliente = cliente;
		this.listaProdutos = listaProdutos;
	}

	public boolean cadastrar() {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.getTransaction().begin();
			// inicia a conexão

			// salvar no banco de dados e ele recebe um objeto. O objeto que eu vou salvar é
			// a própria classe carro que seria
			// o this pq salve a mim mesmo.
			session.save(this);

			session.getTransaction().commit();
			// confirma as alterações
			session.close();

			this.msg = "Cadastro Efetuado com Sucesso";
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			System.out.println(e);
		}
		return false;
	}
	
	public ArrayList<Venda> listar() {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			String sql = "SELECT * FROM Venda";
			Query query = session.createNativeQuery(sql, Venda.class);

			List list = query.list();

			if (list == null || list.size() == 0) { // if (list == null){
				this.msg = "Cliente não encontrado";
				return null;
			} else { // ← Cliente já preenchido automaticamente
				// Preenche o this com os dados do cliente encontrado
				ArrayList<Venda> vendas = new ArrayList<Venda>();
				for(int i = 0; i < list.size(); i++) {
					Venda venda = (Venda) list.get(i);
					
					vendas.add(venda);
				}
				return vendas;
			}

		} catch (Exception e) {
			e.toString();
			System.out.println(e);
			return null;
		}
	}
	

	public Venda() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

}
