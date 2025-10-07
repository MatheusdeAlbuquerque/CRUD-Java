<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Cliente"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="base.css">
<head>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
Cliente c = (Cliente) request.getAttribute("Cliente");
String status = (String) request.getAttribute("status");
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
			</div>

		</div>
	</header>
	<nav>
		<ul class="nav nav-underline">
			<li class="nav-item"><a class="nav-link text-light" aria-current="page" href="login.jsp">Login</a></li>
		</ul>
	</nav>
	<main>
		<div class="d-flex justify-content-center align-items-center" style="min-height: 100vh;">
			<div class="card border-0 shadow p-4 mt-5" style="width: 500px;">
				<h4 class="mb-4 text-center">Login</h4>
				<form method="get" class="conteudo" action="SvLogin" style="margin-top: 50px">
					<input type="hidden" value="<%=c != null ? c.getId() : ""%>" id="button-3" name="idLocalizar" />
					<div class="form-floating w-100">
						<input type="text" class="form-control" id="floatingInput" placeholder="" name="login"
							value="<%=c != null ? c.getCpf() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("login") : ""%>">
						<label for="floatingInput">Digite o Cpf</label>
					</div>
					<div class="form-floating w-100">
						<input type="password" class="form-control" id="floatingInput" placeholder="" name="senha"
							value="<%=c != null ? c.getSenha() : request.getAttribute("status") != null && request.getAttribute("status").equals("erro") ? request.getParameter("senha") : ""%>">
						<label for="floatingInput">Digite a Senha</label>
					</div>
					<div>
						<input class="btn btn-success" type="submit" value="Entrar" id="button-1" />
					</div>
					<%
					String msgLogin = (String) request.getAttribute("msg");

					if (msgLogin != null) {
						if ("erro".equals(status)) {
					%>
					<div class="alert alert-danger" role="alert">
						<%=msgLogin%>
					</div>
					<%
					}
					%>
					<%
					}
					%>
				</form>
			</div>
		</div>

	</main>
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

</html>