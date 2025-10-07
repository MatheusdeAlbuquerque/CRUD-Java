<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Produto"%>
<%@ page import="model.Cliente"%>

<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<head>
<link rel="stylesheet" type="text/css" href="base.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
<body>
	<%
	Produto p = (Produto) request.getAttribute("Produto");
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
			<form id="form-1" action="SvLocalizarProduto" method="get">
				<div class="card border-0 shadow p-4 mt-5">
					<h4 class="mb-4 text-center">Localize o Produto</h4>
					<div class="form-floating mb-3 d-flex">
						<input type="text" class="form-control" id="floatingInput" placeholder="Digite o CPF" name="localizarCodigo" value="<%=(status2 != null && (status2.equals("ok") || status2.equals("erro"))) ? "" : (p != null ? p.getCodigo() : "")%>">
						<label for="floatingInput">Digite o Código do Produto</label>
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

		<div class="d-flex justify-content-center align-items-center">
			<div class="card border-0 shadow p-4 mt-5" style="width: 500px;">
				<h4 class="mb-4 text-center">Área do Produto</h4>
				<form method="get" class="conteudo" action="SvCadastrarProduto">
					<input type="hidden" value="<%=p != null ? p.getId() : ""%>" id="button-3" name="idLocalizar" />
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iCodigo" placeholder="" name="codigo" onblur="verificarCodigo($('#iCodigo').val(), 'idErroCodigo')"
							value="<%=p != null ? p.getCodigo() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("codigo") : ""%>">
						<label for="floatingInput">Código</label>
						<div>
							<p id="idErroCodigo" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iNome" name="nome" placeholder="" onblur="verificarLetras($('#iNome').val(), 'idErroNome')"
							value="<%=p != null ? p.getNome() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("nome") : ""%>">
						<label for="floatingInput">Nome</label>
						<div>
							<p id="idErroNome" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iDescricao" name="descricao" placeholder="" onblur="verificarLetras($('#iDescricao').val(), 'idErroDescricao')"
							value="<%=p != null ? p.getDescricao() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("descricao") : ""%>">
						<label for="floatingInput">Descrição</label>
						<div>
							<p id="idErroDescricao" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iPreco" name="preco" placeholder="" onblur="verificarNumeros($('#iPreco').val(), 'idErroPreco')"
							value="<%=p != null ? p.getPreco() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("preco") : ""%>">
						<label for="floatingInput">Preço</label>
						<div>
							<p id="idErroPreco" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iEstoque" name="estoque" placeholder="" onblur="verificarNumeros($('#iEstoque').val(), 'idErroEstoque')"
							value="<%=p != null ? p.getEstoque() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("estoque") : ""%>">
						<label for="floatingInput">Estoque</label>
						<div>
							<p id="idErroEstoque" class="text-danger small mt-1"></p>
						</div>
					</div>
					<%
					if (p != null) {
					%>
					<div class="d-flex flex-row justify-content-evenly w-100">
						<div>
							<input type="submit" class="btn btn-warning" value="Editar" id="button-2" formaction="SvEditarProduto" />
						</div>
						<div>
							<input type="submit" class="btn btn-danger" value="Deletar" id="button-4" formaction="SvDeletarProduto" />
						</div>
						<div>
							<a href="produto.jsp" class="btn btn-primary">Cadastrar Novamente</a>
						</div>
					</div>
					<%
					} else {
					%>
					<input type="submit" value="Cadastrar" id="button-1" class="btn btn-success" />
					<%
					}
					%>

					<%
					String msg = (String) request.getAttribute("msg");

					if (msg != null) {
						if ("ok".equals(status)) {
					%>
					<div class="alert alert-success" role="alert">
						<%=msg%>
					</div>
					<%
					} else {
					%>
					<div class="alert alert-danger" role="alert">
						<%=msg%>
					</div>
					<%
					}
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
<script type="text/javascript" src="formulario.js">
</script>
</html>