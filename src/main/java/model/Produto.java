package model;

import java.util.List;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Entity
@DynamicUpdate
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String codigo;
	@Column
	private String nome;
	@Column
	private String descricao;
	@Column
	private String preco;
	@Column
	private String estoque;
	@Transient
	private String msg;

	public Produto() {
		super();
	}

	public Produto(String codigo, String nome, String descricao, String preco, String estoque) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
	}

	public Produto(long id, String codigo, String nome, String descricao, String preco, String estoque) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
	}

	// metódos
	public boolean cadastrar() {
		try {
			if (!verificarCodigo(this.codigo)) {
				return false;
			} else {
				if (!verificarLetras(this.nome, "Nome")) {
					return false;
				} else {
					if (!verificarLetras(this.descricao, "Descrição")) {
						return false;
					} else {
						if (!verificarNumeros(this.preco, "Preço")) {
							return false;
						} else {
							if (!verificarNumeros(this.estoque, "Estoque")) {
								return false;
							}
							this.codigo = formatarCodigo(codigo);
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
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			System.out.println(e);
		}
		return false;
	}

	public boolean verificarLetras(String palavra, String campo) {
		if (palavra == null || palavra.isEmpty()) {
			this.msg = campo + " campo vazio";
			return false;
		} else {
			String digitosPermitidos = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

			for (int i = 0; i < palavra.length(); i++) {
				char c = palavra.charAt(i);

				if (digitosPermitidos.indexOf(c) == -1) {
					this.msg = campo + " só pode conter letras";
					return false;
				}
			}
			return true;
		}
	}

	public boolean verificarCodigo(String codigo) {
		if (codigo == null || codigo.isEmpty()) {
			this.msg = "Código Campo vazio";
			return false;
		} else {
			String digitosPermitidos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

			for (int i = 0; i < codigo.length(); i++) {
				char c = codigo.charAt(i);

				if (digitosPermitidos.indexOf(c) == -1) {
					this.msg = "Código caracter invalido";
					return false;
				}
			}
			return true;
		}
	}

	public boolean verificarNumeros(String valor, String campo) {
		if (valor == null || valor.isEmpty()) {
			this.msg = campo + " campo vazio";
			return false;
		} else {
			String digitosPermitidos = "0123456789";

			for (int i = 0; i < valor.length(); i++) {
				char c = valor.charAt(i);

				if (digitosPermitidos.indexOf(c) == -1) {
					this.msg = campo + " caracter invalido";
					return false;
				}
			}
			return true;
		}
	}

	public String formatarCodigo(String codigo) {
		return codigo.toUpperCase();
	}
	

	public boolean localizar(String codigo) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			String sql = "SELECT id, codigo, nome, descricao, preco, estoque FROM Produto WHERE codigo = :codigo";
			Query query = session.createNativeQuery(sql, Produto.class);
			query.setParameter("codigo", codigo);

			List list = query.list();

			if (list == null || list.size() == 0) { // if (list == null){
				this.msg = "Produto não encontrado";
				return false;
			} else {
				Produto p = (Produto) list.get(0); // ← Cliente já preenchido automaticamente
				// Preenche o this com os dados do cliente encontrado
				this.setId(p.getId());
				this.setCodigo(p.getCodigo());
				this.setNome(p.getNome());
				this.setDescricao(p.getDescricao());
				this.setPreco(p.getPreco());
				this.setEstoque(p.getEstoque());
				this.msg = "Produto Localizado com Sucesso";
				return true;
			}

		} catch (Exception e) {
			e.toString();
			System.out.println(e);
			return false;
		}
	}

	public boolean editarProduto() {
		try {
			if (!verificarCodigo(this.codigo)) {
				return false;
			}
			if (!verificarLetras(this.nome, "Nome")) {
				return false;
			}
			if (!verificarLetras(this.descricao, "Descrição")) {
				return false;
			}
			if (!verificarNumeros( this.preco, "Preço")) {
				return false;
			}
			if (!verificarNumeros(this.estoque, "Estoque")) {
				return false;
			}
			this.codigo = formatarCodigo(codigo);
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			try {
				session.beginTransaction();

				session.update(this);

				session.getTransaction().commit();

				session.close();

				this.msg = "Produto Atualizado com Sucesso";
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				this.msg = "Erro ao Atualizar Produto " + e;
				return false;
			}

		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}
	
	public boolean deletarProduto(long idLocalizado) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			Produto p = session.get(Produto.class, idLocalizado);

			if (p != null) {


				this.msg = "Produto Apagado Com Sucesso";

				session.delete(p);

				session.getTransaction().commit();

				session.close();

				return true;
			} else {
				this.msg = "Erro ao Apagar o Produto";
				return false;
			}
		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}
	
	/*
	public boolean editarNome(long idLocalizado, String novoNome) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			Produto p = session.get(Produto.class, idLocalizado);

			if (p != null) {
				p.setNome(novoNome);

				System.out.println(p.getId());

				System.out.println(p.getNome());

				this.msg = "Nome Editado com Sucesso";

				session.update(p);

				session.getTransaction().commit();

				session.close();

				return true;
			} else {
				this.msg = "Nome não foi Editado";

				return false;
			}

		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}

	public boolean editarCodigo(long idLocalizado, String novoCodigo) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			Produto p = session.get(Produto.class, idLocalizado);

			if (p != null) {
				p.setCodigo(novoCodigo);

				System.out.println(p.getId());

				System.out.println(p.getNome());

				this.msg = "Codigo Editado com Sucesso";

				session.update(p);

				session.getTransaction().commit();

				session.close();

				return true;
			} else {
				this.msg = "Codigo não foi Editado";

				return false;
			}

		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}

	public boolean editarDescricao(long idLocalizado, String novaDescricao) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			Produto p = session.get(Produto.class, idLocalizado);

			if (p != null) {
				p.setDescricao(novaDescricao);

				System.out.println(p.getId());

				System.out.println(p.getNome());

				this.msg = "Descricao Editado com Sucesso";

				session.update(p);

				session.getTransaction().commit();

				session.close();

				return true;
			} else {
				this.msg = "Descricao não foi Editado";

				return false;
			}

		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}

	public boolean editarPreco(long idLocalizado, String novoPreco) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			Produto p = session.get(Produto.class, idLocalizado);

			if (p != null) {
				p.setPreco(novoPreco);

				System.out.println(p.getId());

				System.out.println(p.getNome());

				this.msg = "Preço Editado com Sucesso";

				session.update(p);

				session.getTransaction().commit();

				session.close();

				return true;
			} else {
				this.msg = "Preço não foi Editado";

				return false;
			}

		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}

	public boolean editarEstoque(long idLocalizado, int novoEstoque) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			Produto p = session.get(Produto.class, idLocalizado);

			if (p != null) {
				p.setEstoque(novoEstoque);

				System.out.println(p.getId());

				System.out.println(p.getNome());

				this.msg = "Estoque Editado com Sucesso";

				session.update(p);

				session.getTransaction().commit();

				session.close();

				return true;
			} else {
				this.msg = "Estoque não foi Editado";
				return false;
			}

		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}
*/

	// metódos getters e setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getEstoque() {
		return estoque;
	}

	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
