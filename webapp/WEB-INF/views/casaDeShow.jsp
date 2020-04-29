<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Casa de Shows</title>

	<spring:url value="/casaDeShow/salva" var="salva"></spring:url>
	<spring:url value="/casaDeShow/deleta/" var="deleta"></spring:url>
	<spring:url value="/casaDeShow/altera/" var="altera"></spring:url>

	<link href='<spring:url value="/resources/css/bootstrap.css" />' rel="stylesheet" />
    <script src='<spring:url value="/resources/js/jquery-3.4.1.min.js" />' ></script> 
    <script src='<spring:url value="/resources/js/bootstrap.js" />' ></script>
</head>
<body>
	<jsp:include page="${request.contextPath}/menu"></jsp:include>
	
		<div class="container mt-5"> 
			<div class="row">
			<jsp:include page="${request.contextPath}/menuLateral"></jsp:include>
			
			<div class="col-lg-9">
				<c:if test="${not empty mensagemErro }">
					<div id="divMensagemErro" class="alert alert-danger" role="alert">
						${mensagemErro }
					</div>
				</c:if>
				
				<c:if test="${not empty mensagemSucesso }">
					<div id="divMensagemSucesso" class="alert alert-success" role="alert">
						${mensagemSucesso }
					</div>
				</c:if>
				
				<div class="pb-2 mt-4 mb-2 border-bottom"> <!-- page header -->
					<h2>Casa de Shows</h2>
				</div>
		
			<form:form action="${salva}" modelAttribute="casaDeShow" enctype="multipart/form-data" cssClass="mb-2">
		
				<form:hidden path="id"/>
			
			<div class="form-group">
				<label>Nome</label>
				<form:input path="nome" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<label>Endereço</label>
				<form:input path="endereco" cssClass="form-control"/>
			</div>
			
			<div class="form-group">
				<label>Capacidade</label>
				<form:input path="capacidade" cssClass="form-control"/>
			</div>
			
			<input type="submit" class="btn btn-primary" name="salva" value="${casaDeShow.id == 0 ? 'Cadastrar' : 'Alterar'}"/>
			
			<c:if test="${casaDeShow.id ne 0}">
				<input type="submit" class="btn btn-warning" name="cancela" value="Cancelar" /> 
			</c:if>
		</form:form>
		<c:if test="${not empty casaDeShows}">
			<hr />
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Nome</th>
						<th scope="col">Endereço</th>
						<th scope="col">Capacidade</th>
						<th scope="col" colspan="2">Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="casaDeShow" items="${casaDeShows}">
						<tr>
							<td scope="row">${casaDeShow.id}</td>
							<td>${casaDeShow.nome}</td>
							<td>${casaDeShow.endereco}</td>
							<td>${casaDeShow.capacidade}</td>
							<td><a href="${altera}${casaDeShow.id}" class="btn btn-warning">Alterar</a></td>
							<td><a href="${deleta}${casaDeShow.id}" class="btn btn-danger" onclick="return confirm('Deseja realmente deletar esta Casa de Show?')">Deletar</a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>	
</div>

<script type="text/javascript">
		$(document).ready(function() {
			$('#divMensagemErro').delay(5000).fadeOut('slow');
			$('#divMensagemSucesso').delay(5000).fadeOut('slow');
		});
	</script>
</body>
</html>