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
Cliente c = (Cliente) request.getAttribute("Cliente");
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
	if (clienteUsuario != null) {
	%>
	<main class="divPaiLocalizar" style="min-height: 100vh; position: relative;">
		<div class="d-flex justify-content-center align-items-center">
			<div class="card border-0 shadow p-4 mt-5" style="width: 500px;">
				<h4 class="mb-4 text-center">Perfil</h4>
				<form method="get" class="conteudo" action="SvLogin">
					<div class="form-floating w-100">
						<input type="text" disabled="disabled" class="form-control floatingInput" id="iNome" placeholder="" name="nome" value="<%=clienteUsuario != null ? clienteUsuario.getNome() : ""%>">
						<label for="floatingInput">Nome</label>
					</div>
					<div class="form-floating w-100">
						<input type="text" disabled="disabled" class="form-control floatingInput" id="iCpf" name="cpf" placeholder="" value="<%=clienteUsuario != null ? clienteUsuario.getCpf() : ""%>">
						<label for="floatingInput">CPF</label>
					</div>
					<div class="form-floating w-100">
						<input type="text" disabled="disabled" class="form-control floatingInput" id="iEndereco" name="endereco" placeholder="" value="<%=clienteUsuario != null ? clienteUsuario.getEndereco() : ""%>">
						<label for="floatingInput">Endereço</label>
						<p id="idErroEndereco" class="text-danger small mt-1"></p>
					</div>
					<div class="form-floating w-100">
						<input type="text" disabled="disabled" class="form-control floatingInput" id="iEstado" name="estado" placeholder="" value="<%=clienteUsuario != null ? clienteUsuario.getEstado() : ""%>">
						<label for="floatingInput">Estado</label>
					</div>
					<div class="form-floating w-100">
						<input type="text" disabled="disabled" class="form-control floatingInput" id="iDataNascimento" name="dataNascimento" placeholder="" value="<%=clienteUsuario != null ? clienteUsuario.getDataNascimento() : ""%>">
						<label for="floatingInput">Data Nascimento</label>
					</div>
					<div class="form-floating w-100">
						<input type="text" disabled="disabled" class="form-control floatingInput" id="iTelefone" name="telefone" placeholder="" value="<%=clienteUsuario != null ? clienteUsuario.getTelefone() : ""%>">
						<label for="floatingInput">Telefone</label>
					</div>
					<div class="form-floating w-100">
						<input type="text" disabled="disabled" class="form-control floatingInput" id="iSexo" name="sexo" placeholder="sexo" value="<%=clienteUsuario != null ? clienteUsuario.getSexo() : ""%>">
						<label for="floatingInput">Sexo</label>
						<div>
							<p id="idErroSexo" class="text-danger small mt-1"></p>
						</div>
					</div>
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
</script>


</html>