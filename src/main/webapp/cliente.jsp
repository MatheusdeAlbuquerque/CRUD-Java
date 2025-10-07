<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Cliente"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
	if ("admin".equals(usuario)) {
	%>
	<main class="divPaiLocalizar" style="min-height: 100vh; position: relative;">
		<div class="divFilhoLocalizar" style="width: 500px; position: absolute; top: 0; left: 10px; padding:;">
			<form id="form-1" action="SvLocalizarCliente" method="get">
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

		<div class="d-flex justify-content-center align-items-center">
			<div class="card border-0 shadow p-4 mt-5" style="width: 500px;">
				<h4 class="mb-4 text-center">Área do Cliente</h4>
				<form method="get" class="conteudo" action="SvCadastrarCliente">
					<input type="hidden" value="<%=c != null ? c.getId() : ""%>" id="button-3" name="idLocalizar" />
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iNome" placeholder="" name="nome" onblur="verificarLetras($('#iNome').val(), 'idErroNome')"
							value="<%=c != null ? c.getNome() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("nome") : ""%>">
						<label for="floatingInput">Nome</label>
						<div>
							<p id="idErroNome" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iCpf" name="cpf" placeholder="" onkeyup="formatarCpf($('#iCpf'))" onblur="verificarCpf($('#iCpf').val(), 'idErroCpf')"
							value="<%=c != null ? c.getCpf() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("cpf") : ""%>">
						<label for="floatingInput">CPF</label>
						<div>
							<p id="idErroCpf" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iEndereco" name="endereco" placeholder="" onblur="verificarLetras($('#iEndereco').val(), 'idErroEndereco')"
							value="<%=c != null ? c.getEndereco() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("endereco") : ""%>">
						<label for="floatingInput">Endereço</label>
						<p id="idErroEndereco" class="text-danger small mt-1"></p>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iEstado" name="estado" placeholder="" onblur="verificarLetras($('#iEstado').val(), 'idErroEstado')"
							value="<%=c != null ? c.getEstado() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("estado") : ""%>">
						<label for="floatingInput">Estado</label>
						<div>
							<p id="idErroEstado" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" onblur="verificarDataNascimento($('#iDataNascimento').val(), 'idErroNascimento')" onkeyup="formatarNascimento($('#iDataNascimento'))" id="iDataNascimento" name="dataNascimento"
							placeholder="" value="<%=c != null ? c.getDataNascimento() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("dataNascimento") : ""%>">
						<label for="floatingInput">Data Nascimento</label>
						<div>
							<p id="idErroNascimento" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" onblur="verificarTelefone($('#iTelefone').val(), 'idErroTelefone')" onkeyup="formatarTelefone($('#iTelefone'))" id="iTelefone" name="telefone" placeholder=""
							value="<%=c != null ? c.getTelefone() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("telefone") : ""%>">
						<label for="floatingInput">Telefone</label>
						<div>
							<p id="idErroTelefone" class="text-danger small mt-1"></p>
						</div>
					</div>
					<div class="form-floating w-100">
						<input type="password" class="form-control floatingPassword" id="iSenha" name="senha" placeholder="Password" onblur="verificarSenha('iSenha')"
							value="<%=c != null ? c.getSenha() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("senha") : ""%>">
						<label for="floatingInput">Senha</label>
					</div>
					<div>
						<p id="idErroSenha" class="text-danger small mt-1"></p>
					</div>
					<div class="form-floating w-100">
						<input type="text" class="form-control floatingInput" id="iSexo" name="sexo" placeholder="sexo" onblur="verificarLetras($('#iSexo').val(), 'idErroSexo')"
							value="<%=c != null ? c.getSexo() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("sexo") : ""%>">
						<label for="floatingInput">Sexo</label>
						<div>
							<p id="idErroSexo" class="text-danger small mt-1"></p>
						</div>
					</div>

					<%
					if (c != null) {
					%>
					<div class="d-flex flex-row justify-content-evenly w-100">
						<div>
							<input type="submit" class="btn btn-warning" value="Editar" id="button-2" formaction="SvEditarCliente" />
						</div>
						<div>
							<input type="submit" class="btn btn-danger" value="Deletar" id="button-4" formaction="SvDeletarCliente" />
						</div>
						<div>
							<a href="cliente.jsp" class="btn btn-primary">Cadastrar Novamente</a>
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