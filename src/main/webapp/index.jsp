<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.Cliente"%>
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

	<main>
		<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="imagens/pexels-enginakyurt-30823759.jpg" class="d-block w-100 carousel-img-custom" alt="...">
				</div>
				<div class="carousel-item">
					<img src="imagens/pexels-huysuzkadraj-29326470.jpg" class="d-block w-100 carousel-img-custom" alt="...">
				</div>
				<div class="carousel-item">
					<img src="imagens/pexels-enginakyurt-2260569.jpg" class="d-block w-100 carousel-img-custom" alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
			</button>
		</div>
	</main>
	<div class="animacao-visual">
		<div class="conteudo-banner-index">
			<%
			for (int i = 0; i < 40; i++) {
			%>
			<span class="mx-1 py-3 span-animation">Nova Coleção</span>
			<%
			}
			%>

		</div>
	</div>
	<div class="containermth">
		<div class="cardsPai">
			<div class="cardsFilho">
				<a href="loja.html">
					<img src="imagens/Pulseira1.jpeg" alt="">
				</a>
			</div>

			<h2 class="text-center pulseiraClass">PULSEIRA PÉROLAS</h2>
		</div>
		<div class="cardsPai">
			<div class="cardsFilho">
				<a href="loja.html">
					<img src="imagens/Pulseira1.jpeg" alt="">
				</a>
			</div>
			<h2 class="text-center pulseiraClass">PULSEIRA LABRADORITA</h2>
		</div>
		<div class="cardsPai">
			<div class="cardsFilho">
				<a href="loja.html">
					<img src="imagens/Pulseira6.jpeg" alt="">
				</a>
			</div>
			<h2 class="text-center pulseiraClass">PULSEIRA APATITA</h2>
		</div>
		<div class="cardsPai">
			<div class="cardsFilho">
				<a href="loja.html">
					<img src="imagens/Pulseira5.jpeg" alt="">
				</a>
			</div>
			<h2 class="text-center pulseiraClass">PULSEIRA TODA EM PRATA</h2>
		</div>
	</div>
	<footer>
		<p>@Design and Created by Matheus Cavallero</p>
	</footer>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</html>