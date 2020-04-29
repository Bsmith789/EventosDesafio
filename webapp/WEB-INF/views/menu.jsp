<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="home"></spring:url>
<spring:url value="/logout" var="logout"></spring:url>
<spring:url value="/login" var="logar"></spring:url>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${home}">Eventos Bacanas</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<a class="nav-link" href="${home}">Home
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">${usuarioLogado.name == 'anonymousUser' ? '' : usuarioLogado.name}</a>
				</li>
				<li class="nav-item">
					<c:if test="${usuarioLogado.name ne 'anonymousUser' }">
						<a class="nav-link" href="${logout}" >Sair</a>
					</c:if>
					<c:if test="${usuarioLogado.name eq 'anonymousUser' }">
						<a class="nav-link" href="${logar}" >Entrar</a>
					</c:if>
					
				</li>
			</ul>
		</div>
	</div>
</nav>
<div class="mb-2"></div>