<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/evento" var="evento"></spring:url>
<spring:url value="/casaDeShow" var="casaDeShow"></spring:url>
<spring:url value="/cliente" var="cliente"></spring:url>
	
<div class="col-lg-3 mt-5">

	<!-- <h1 class="my-4">Eventos Bacanas</h1> -->
	<c:if test="${usuarioLogado.authorities[0] eq 'ROLE_ADMIN' }">
		<div class="list-group">
			<a href="${evento }" class="list-group-item">Cadastro Eventos</a>
			<a href="${cliente }" class="list-group-item">Cadastro Clientes</a>
			<a href="${casaDeShow }" class="list-group-item">Cadastro Casa de Show</a>
		</div>
	</c:if>
	

 </div>