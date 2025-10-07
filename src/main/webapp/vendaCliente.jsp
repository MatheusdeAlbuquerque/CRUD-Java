<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Cliente"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<head>
<link rel="stylesheet" type="text/css" href="base.css">
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@media ( max-width : 1550px) {
	.divPaiLocalizar {
		display: flex;
		justify-content: center;
		padding: 30px;
		flex-direction: column;
		align-items: center;
	}
	.divFilhoLocalizar {
		position: static !important; /* remove o absolute */
		width: 100%;
		max-width: 500px;
	}
}
</style>
</head>
<%
Cliente c = (Cliente) request.getSession().getAttribute("Cliente");
String status = (String) request.getAttribute("status");
String status2 = (String) request.getAttribute("statusLocalizar");
Object usuario = request.getSession().getAttribute("usuarioLogado");
Cliente clienteUsuario = (Cliente) request.getSession().getAttribute("usuarioCliente");
%>

<body>
	<header class="container-fluid py-2 border-bottom">
		<div class="row align-items-center">

			<!-- Coluna esquerda vazia (caso queira algo futuramente) -->
			<div class="col-4"></div>

			<!-- Coluna central com o título centralizado -->
			<div class="col-4 text-center">
				<a class="text-decoration-none" href="index.jsp">
					<h1 class="m-0">Roccas</h1>
				</a>
			</div>

			<!-- Coluna direita com saudação e botão de sair -->
			<div class="col-4 text-end">
				<%
				if (clienteUsuario != null) {
				%>
				<p class="d-inline me-2 m-0">
					Seja Bem-Vindo,
					<%=clienteUsuario.getNome()%></p>
				<a class="btn btn-outline-danger btn-sm" href="SvLogout">Sair</a>
				<%
				} else if ("admin".equals(usuario)) {
				%>
				<p class="d-inline me-2 m-0">Seja Bem-Vindo, Administrador</p>
				<a class="btn btn-outline-danger btn-sm" href="SvLogout">Sair</a>
				<%
				}
				%>
			</div>

		</div>
	</header>
	<nav>
		<%
		if (clienteUsuario != null) {
		%>
		<ul class="nav nav-underline">
			<li class="nav-item"><a class="nav-link text-light" aria-current="page" href="perfil.jsp">PERFIL</a></li>
		</ul>
		<%
		} else if ("admin".equals(usuario)) {
		%>
		<ul class="nav nav-underline">
			<li class="nav-item"><a class="nav-link text-light" aria-current="page" href="cliente.jsp">CLIENTE</a></li>
			<li class="nav-item"><a class="nav-link text-light" href="produto.jsp">PRODUTO</a></li>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle text-light" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> VENDA </a>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="vendaCliente.jsp">Cadastrar Venda</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="visualizarVenda.jsp">Visualizar Venda</a></li>
				</ul></li>
		</ul>
		<%
		} else {
		%>
		<ul class="nav nav-underline">
			<li class="nav-item"><a class="nav-link text-light" aria-current="page" href="login.jsp">Login</a></li>
		</ul>
		<%
		}
		%>
	</nav>

	<%
	if ("admin".equals(usuario)) {
	%>
	<main class="divPaiLocalizar" style="min-height: 100vh; position: relative;">
		<div class="divFilhoLocalizar" style="width: 500px; position: absolute; top: 0; left: 10px; padding:;">
			<form id="form-1" action="SvLocalizarVenda" method="get">
				<div class="card border-0 shadow p-4 mt-5">
				<h4 class="mb-4 text-center">Localize o Cliente</h4>
					<div class="form-floating mb-3 d-flex">
						<input type="text" class="form-control" id="floatingInput" placeholder="Digite o CPF" name="localizarCpf" value="<%=(status2 != null && (status2.equals("ok") || status2.equals("erro"))) ? "" : (c != null ? c.getCpf() : "")%>">
						<label for="floatingInput">Digite o Cpf</label>
						<input type="submit" value="Localizar" class="btn btn-primary ms-2" />
					</div>
					<%
					String msgLocalizar = (String) request.getAttribute("msgLocalizar");

					if (msgLocalizar != null) {
						if ("ok".equals(status2)) {
					%>
					<div class="alert alert-success" role="alert">
						<%=msgLocalizar%>
					</div>
					<%
					} else {
					%>
					<div class="alert alert-danger" role="alert">
						<%=msgLocalizar%>
					</div>
					<%
					}
					}
					%>
				</div>
			</form>
		</div>
		<%
		if (c != null) {
		%>
		<div class="d-flex justify-content-center align-items-center">
			<div class="card border-0 shadow p-4 mt-5" style="width: 500px;">
				<h4 class="mb-4 text-center">Dados do Cliente</h4>
				<form method="get" class="" action="">
					<div class="mb-4">
						<label class="form-label">Nome</label>
						<p class=""><%=c != null ? c.getNome() : ""%></p>
					</div>
					<div class="mb-4">
						<label class="">CPF</label>
						<p class=""><%=c != null ? c.getCpf() : ""%></p>
					</div>
					<div class="mb-4">
						<label class="form-label">Endereço</label>
						<p class=""><%=c != null ? c.getEndereco() : ""%></p>
					</div>
					<div class="mb-4">
						<label class="form-label">Estado</label>
						<p class=""><%=c != null ? c.getEstado() : ""%></p>
					</div>
					<div class="mb-4">
						<label class="form-label">Data de Nascimento</label>
						<p class=""><%=c != null ? c.getDataNascimento() : ""%></p>
					</div>
					<div class="mb-4">
						<label class="form-label">Telefone</label>
						<p class=""><%=c != null ? c.getTelefone() : ""%></p>
					</div>
					<div class="mb-4">
						<label class="form-label">Sexo</label>
						<p class=""><%=c != null ? c.getSexo() : ""%></p>
					</div>
					<input type="submit" class="btn btn-warning" value="Próximo" id="button-2" formaction="vendaProduto.jsp" />
					<%
					}
					%>
				</form>
			</div>
		</div>
	</main>
	<%
	} else {
	%>
	<main class="d-flex justify-content-center align-items-center" style="min-height: 100vh;">
		<div class="alert alert-danger text-center" style="width: 400px;">
			<h4 class="mb-2">Acesso negado</h4>
			<p>Você não tem permissão para acessar esta página.</p>
			<a href="index.jsp" class="btn btn-primary mt-2">Voltar para o início</a>
		</div>
	</main>
	<%
	}
	%>
	<footer class="mt-5">
		<p>@Design and Created by Matheus Cavallero</p>
	</footer>
	<div class="animacao-visual-paginas">
		<div class="conteudo-banner-paginas">
			<%
			for (int i = 0; i < 40; i++) {
			%>
			<span class="mx-1 py-3 span-animation text-light">Cada pulseira de prata é inspirada nas ondas, nas pedras e na sua essência.</span>
			<%
			}
			%>
		</div>
	</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
<script type="text/javascript">
 
</script>
</html>