package model;

import java.util.List;



import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Entity
@DynamicUpdate
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String nome;
	@Column
	private String cpf;
	@Column
	private String endereco;
	@Column
	private String estado;
	@Column
	private String dataNascimento;
	@Column
	private String telefone;
	@Column
	private String senha;
	@Column
	private String sexo;
	@Transient
	private String msg;

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, String endereco, String estado, String dataNascimento, String telefone, String senha, String sexo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.estado = estado;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.senha = senha;
		this.sexo = sexo;
	}

	public Cliente(long id, String nome, String cpf, String endereco, String estado, String dataNascimento, String telefone, String senha, String sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.estado = estado;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.senha = senha;
		this.sexo = sexo;
	}

	// métodos

	public boolean cadastrar() {
		try {
			if (!verificarLetras(this.nome, "Nome")) {
				return false;
			} else {
				if (!verificarCpf(this.cpf)) {
					return false;
				} else {
					if (!verificarLetras(this.endereco, "Endereço")) {
						return false;
					} else {
						if (!verificarLetras(this.estado, "Estado")) {
							return false;
						} else {
							if (!verificarDataNascimento(dataNascimento)) {
								return false;
							} else {
								if (!verificarTelefone(this.telefone)) {
									return false;
								} else {
									if (!verificarLetras(this.sexo, "Sexo")) {
										return false;
									} else {
										if (!verificarSenha(this.senha)) {
											return false;
										} else {
											this.cpf = formatarCpf(cpf);
											this.telefone = formatarTelefone(telefone);
											this.dataNascimento = formatarDataNascimento(dataNascimento);
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
	
	
	public boolean verificarSenha(String senha) {
	    if (senha == null) senha = "";
	    senha = senha.trim();

	    String letrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
	    String letrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String numeros = "1234567890";
	    String caracteresEspeciais = "!#@$";

	    boolean temMinuscula = false;
	    boolean temMaiuscula = false;
	    boolean temNumero = false;
	    boolean temEspecial = false;

	    for (int i = 0; i < senha.length(); i++) {
	        char c = senha.charAt(i);

	        if (letrasMinusculas.indexOf(c) != -1) {
	            temMinuscula = true;
	        } else if (numeros.indexOf(c) != -1) {
	            temNumero = true;
	        } else if (letrasMaiusculas.indexOf(c) != -1) {
	            temMaiuscula = true;
	        } else if (caracteresEspeciais.indexOf(c) != -1) {
	            temEspecial = true;
	        }
	    }

	    if (!temMinuscula || !temMaiuscula || !temNumero || !temEspecial) {
	        this.msg = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula, um número e um caractere especial (!#@$)";
	        return false;
	    }

	    this.senha = senha; // opcional, se existir atributo
	    return true;
	}

	
	
	public boolean verificarCpf(String cpf) {
		if (cpf == null || cpf.isEmpty()) {
			this.msg = "CPF campo vazio";
			return false;
		}

		String cpfLimpo = cpf.replace(".", "");
		cpfLimpo = cpfLimpo.replace("-", "");
		cpfLimpo = cpfLimpo.replace(" ", "");

		if (cpfLimpo.length() != 11) {
			this.msg = "CPF só pode ter 11 caracteres";
			return false;
		}
		String digitosPermitidos = "0123456789";

		for (int i = 0; i < cpfLimpo.length(); i++) {
			char c = cpfLimpo.charAt(i);

			if (digitosPermitidos.indexOf(c) == -1) {
				this.msg = "CPF invalido";
				return false;
			}

		}
		this.cpf = cpfLimpo;
		return true;
	}

	public String formatarCpf(String cpf) {
		String formatarCpf = "";
		formatarCpf += cpf.substring(0, 3) + ".";
		formatarCpf += cpf.substring(3, 6) + ".";
		formatarCpf += cpf.substring(6, 9) + "-";
		formatarCpf += cpf.substring(9, 11);
		return formatarCpf;
	}

	public boolean verificarTelefone(String telefone) {
		if (telefone == null || telefone.isEmpty()) {
			this.msg = "Telefone, campo vazio";
			return false;
		}
		String telefoneLimpo = telefone.replace("(", "");
		telefoneLimpo = telefoneLimpo.replace(")", "");
		telefoneLimpo = telefoneLimpo.replace("-", "");
		telefoneLimpo = telefoneLimpo.replace(" ", "");
		if (telefone.length() != 11) {
			this.msg = "Telefone só pode conter 11 caracteres";
		}
		String digitosPermitidos = "0123456789";

		for (int i = 0; i < telefoneLimpo.length(); i++) {// telefoneLimpo está limpo e por isso eu coloco ele no for.
			char c = telefoneLimpo.charAt(i);

			if (digitosPermitidos.indexOf(c) == -1) {
				this.msg = "Telefone, digito invalido(só pode conter números)";
				return false;
			}
		}
		this.telefone = telefoneLimpo;
		return true;
	}

	public String formatarTelefone(String telefone) {
		String formatarTelefone = "";
		formatarTelefone += "(";
		formatarTelefone += telefone.substring(0, 2) + ")";
		formatarTelefone += telefone.substring(2, 7) + "-";
		formatarTelefone += telefone.substring(7, 11);
		return formatarTelefone;
	}

	public boolean verificarDataNascimento(String dataNascimento) {
		if (dataNascimento == null || dataNascimento.isEmpty()) {
			this.msg = "Data de Nascimento está vazia";
		}
		// aqui para conseguir verificar o tamanho sem dar erro pq o javascript vai
		// colocar sozinho os /.
		String dataNascimentoLimpa = dataNascimento.replace("/", "");
		dataNascimentoLimpa = dataNascimentoLimpa.replace(" ", "");
		if (dataNascimentoLimpa.length() != 8) {
			this.msg = "Data de Nascimento está Incorreta";
		}
		String numerais = "0123456789";

		for (int i = 0; i < dataNascimentoLimpa.length(); i++) {
			char c = dataNascimentoLimpa.charAt(i);

			if (numerais.indexOf(c) == -1) {
				this.msg = "Data de Nascimento só pode conter números";
				return false;
			}
		}
		this.dataNascimento = dataNascimentoLimpa;
		return true;
	}

	public String formatarDataNascimento(String dataNascimento) {
		String formatarDataNascimento = "";
		formatarDataNascimento += dataNascimento.substring(0, 2) + "/";
		formatarDataNascimento += dataNascimento.substring(2, 4) + "/";
		formatarDataNascimento += dataNascimento.substring(4, 8);
		return formatarDataNascimento;
	}

	/*
	 * public boolean verificarSenha(String palavra) { String alfabeto =
	 * " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; String numeros =
	 * "0123456789"; String especiais = "!@#$%"; for (int i = 0; i <
	 * palavra.length(); i++) { char c = palavra.charAt(i);
	 * 
	 * if (alfabeto.indexOf(c) == -1) { this.msg = "só pode conter letras"; return
	 * false; } } return true;
	 * 
	 * }
	 * 
	 * public boolean verificarSenha(String senha) { if (senha == null ||
	 * senha.isEmpty()) { this.msg = "Senha está vazia"; return false; } else { if
	 * (senha.length() < 8) { this.msg = "Senha tem que ter 8 caracteres no mínimo";
	 * return false; } else { String digitosPermitidos =
	 * "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789!@%";
	 * 
	 * if (senha) {
	 * 
	 * } else {
	 * 
	 * } } }
	 * 
	 * }
	 */
	public boolean localizar(String cpf) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			String sql = "SELECT id, nome, cpf, endereco, dataNascimento, estado, senha, telefone, sexo FROM cliente WHERE cpf = :cpf";
			Query query = session.createNativeQuery(sql, Cliente.class);
			query.setParameter("cpf", cpf);

			List list = query.list();

			if (list == null || list.size() == 0) { // if (list == null){
				this.msg = "Cliente não encontrado";
				return false;
			} else {
				Cliente c = (Cliente) list.get(0); // ← Cliente já preenchido automaticamente
				// Preenche o this com os dados do cliente encontrado
				this.setId(c.getId());
				this.setNome(c.getNome());
				this.setCpf(c.getCpf());
				this.setEndereco(c.getEndereco());
				this.setDataNascimento(c.getDataNascimento());
				this.setEstado(c.getEstado());
				this.setSenha(c.getSenha());
				this.setTelefone(c.getTelefone());
				this.setSexo(c.getSexo());

				this.msg = "Cliente Localizado com Sucesso";
				return true;
			}

		} catch (Exception e) {
			e.toString();
			System.out.println(e);
			return false;
		}
	}

	public boolean verficarLogin(String cpf, String senha) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			String sql = "SELECT * FROM cliente WHERE cpf = :cpf AND senha = :senha";
			Query query = session.createNativeQuery(sql, Cliente.class);
			query.setParameter("cpf", cpf);
			query.setParameter("senha", senha);

			List list = query.list();

			if (list == null || list.size() == 0) {
				this.msg = "Cliente não encontrado";
				return false;
			} else {
				Cliente c = (Cliente) list.get(0);
				this.setId(c.getId());
				this.setNome(c.getNome());
				this.setCpf(c.getCpf());
				this.setEndereco(c.getEndereco());
				this.setDataNascimento(c.getDataNascimento());
				this.setEstado(c.getEstado());
				this.setSenha(c.getSenha());
				this.setTelefone(c.getTelefone());
				this.setSexo(c.getSexo());
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			System.out.println(e);
		}
		return false;
	}

	public Cliente localizarPorId(String id) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			String sql = "SELECT id, nome, cpf, endereco, dataNascimento, estado, senha, telefone, sexo FROM cliente WHERE id = :id";
			Query query = session.createNativeQuery(sql, Cliente.class);
			query.setParameter("id", id);

			List list = query.list();

			if (list == null || list.size() == 0) { // if (list == null){
				return null;
			} else {
				Cliente c = (Cliente) list.get(0); // ← Cliente já preenchido automaticamente
				// Preenche o this com os dados do cliente encontrado
				this.setId(c.getId());
				this.setNome(c.getNome());
				this.setCpf(c.getCpf());
				this.setEndereco(c.getEndereco());
				this.setDataNascimento(c.getDataNascimento());
				this.setEstado(c.getEstado());
				this.setSenha(c.getSenha());
				this.setTelefone(c.getTelefone());
				this.setSexo(c.getSexo());

				return c;
			}

		} catch (Exception e) {
			e.toString();
			System.out.println(e);
			return null;
		}
	}

	public boolean editarCliente() {
		try {
			if (!verificarLetras(this.nome, "Nome")) {
				return false;
			}
			if (!verificarCpf(this.cpf)) {
				return false;
			}
			if (!verificarLetras(this.endereco, "Endereço")) {
				return false;
			}
			if (!verificarLetras(this.estado, "Estado")) {
				return false;
			}
			if (!verificarDataNascimento(this.dataNascimento)) {
				return false;
			}
			if (!verificarTelefone(this.telefone)) {
				return false;
			}
			if (!verificarSenha(this.senha)) {
				return false;
			}
			if (!verificarLetras(this.sexo, "Sexo")) {
				return false;
			}
			this.cpf = formatarCpf(cpf);
			this.telefone = formatarTelefone(telefone);
			this.dataNascimento = formatarDataNascimento(dataNascimento);
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			try {
				session.beginTransaction();

				session.update(this);

				session.getTransaction().commit();

				session.close();

				this.msg = "Cliente Atualizado com Sucesso";
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				this.msg = "Erro ao Atualizar Cliente " + e;
				return false;
			}

		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}

	public boolean deletarCliente(long idLocalizado) {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			Cliente c = session.get(Cliente.class, idLocalizado);

			if (c != null) {

				System.out.println(c.getId());

				this.msg = "Cliente Apagado Com Sucesso";

				session.delete(c);

				session.getTransaction().commit();

				session.close();

				return true;
			} else {
				this.msg = "Erro ao Apagar a Conta";
				return false;
			}
		} catch (Exception e) {
			this.msg = e.toString();
			System.out.println(e);
		}
		return false;
	}

	/*
	 * public boolean editarNome(long idLocalizado, String novoNome) { try { if
	 * (!verificarLetras(novoNome, "Nome")) { return false; } SessionFactory
	 * sessionFactory = new Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) { c.setNome(novoNome);
	 * 
	 * this.msg = "Nome Editado com Sucesso";
	 * 
	 * session.update(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Nome não foi Editado";
	 * 
	 * return false; } } catch (Exception e) { this.msg = e.toString();
	 * System.out.println(e); } return false; }
	 * 
	 * public boolean editarCpf(long idLocalizado, String novoCpf) { try { if
	 * (!verificarCpf(novoCpf)) { return false; } SessionFactory sessionFactory =
	 * new Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) { c.setCpf(novoCpf);
	 * 
	 * System.out.println(c.getId());
	 * 
	 * System.out.println(c.getCpf());
	 * 
	 * this.msg = "CPF Editado com Sucesso";
	 * 
	 * session.update(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Nome não foi Editado"; return false; } }
	 * catch (Exception e) { this.msg = e.toString(); System.out.println(e); }
	 * return false; }
	 * 
	 * public boolean editarEndereco(long idLocalizado, String novoEndereco) { try {
	 * SessionFactory sessionFactory = new
	 * Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) { c.setEndereco(novoEndereco);
	 * 
	 * System.out.println(c.getId());
	 * 
	 * System.out.println(c.getCpf());
	 * 
	 * this.msg = "Endereço Editado com Sucesso";
	 * 
	 * session.update(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Endereço não foi Editado"; return false; }
	 * } catch (Exception e) { this.msg = e.toString(); System.out.println(e); }
	 * return false; }
	 * 
	 * public boolean editarEstado(long idLocalizado, String novoEstado) { try {
	 * SessionFactory sessionFactory = new
	 * Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) { c.setEstado(novoEstado);
	 * 
	 * System.out.println(c.getId());
	 * 
	 * System.out.println(c.getCpf());
	 * 
	 * this.msg = "Estado Editado com Sucesso";
	 * 
	 * session.update(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Estado não foi Editado"; return false; } }
	 * catch (Exception e) { this.msg = e.toString(); System.out.println(e); }
	 * return false; }
	 * 
	 * public boolean editarDataNascimento(long idLocalizado, String novaData) { try
	 * { SessionFactory sessionFactory = new
	 * Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) { c.setDataNascimento(novaData);
	 * 
	 * System.out.println(c.getId());
	 * 
	 * System.out.println(c.getCpf());
	 * 
	 * this.msg = "Data Editada com Sucesso";
	 * 
	 * session.update(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Data não foi Editado"; return false; } }
	 * catch (Exception e) { this.msg = e.toString(); System.out.println(e); }
	 * return false; }
	 * 
	 * public boolean editarTelefone(long idLocalizado, String novoTelefone) { try {
	 * SessionFactory sessionFactory = new
	 * Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) { c.setTelefone(novoTelefone);
	 * 
	 * System.out.println(c.getId());
	 * 
	 * System.out.println(c.getCpf());
	 * 
	 * this.msg = "Telefone Editado com Sucesso";
	 * 
	 * session.update(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Telefone não foi Editado"; return false; }
	 * } catch (Exception e) { this.msg = e.toString(); System.out.println(e); }
	 * return false; }
	 * 
	 * public boolean editarSexo(long idLocalizado, String novoSexo) { try {
	 * SessionFactory sessionFactory = new
	 * Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) { c.setSexo(novoSexo);
	 * 
	 * System.out.println(c.getId());
	 * 
	 * System.out.println(c.getCpf());
	 * 
	 * this.msg = "Sexo Editado com Sucesso";
	 * 
	 * session.update(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Sexo não foi Editado"; return false; } }
	 * catch (Exception e) { this.msg = e.toString(); System.out.println(e); }
	 * return false; }
	 * 
	 * public boolean editarSenha(long idLocalizado, String novaSenha) { try {
	 * SessionFactory sessionFactory = new
	 * Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) { c.setSenha(novaSenha);
	 * 
	 * System.out.println(c.getId());
	 * 
	 * System.out.println(c.getCpf());
	 * 
	 * this.msg = "Senha Editado com Sucesso";
	 * 
	 * session.update(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Senha não foi Editado"; return false; } }
	 * catch (Exception e) { this.msg = e.toString(); System.out.println(e); }
	 * return false; }
	 * 
	 * public boolean deletarCliente(long idLocalizado) { try { SessionFactory
	 * sessionFactory = new Configuration().configure().buildSessionFactory();
	 * 
	 * Session session = sessionFactory.openSession();
	 * 
	 * session.beginTransaction();
	 * 
	 * Cliente c = session.get(Cliente.class, idLocalizado);
	 * 
	 * if (c != null) {
	 * 
	 * System.out.println(c.getId());
	 * 
	 * this.msg = "Conta Apagada Com Sucesso";
	 * 
	 * session.delete(c);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * session.close();
	 * 
	 * return true; } else { this.msg = "Erro ao Apagar a Conta"; return false; } }
	 * catch (Exception e) { this.msg = e.toString(); System.out.println(e); }
	 * return false; }
	 */
	// métodos getters e setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
