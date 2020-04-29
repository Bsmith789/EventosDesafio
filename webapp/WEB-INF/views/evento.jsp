<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Evento</title>
	
	<spring:url value="/evento/deleta/" var="deleta"></spring:url>
	<spring:url value="/evento/altera/" var="altera"></spring:url>
	<spring:url value="/evento/salva" var="salva"></spring:url>
	
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
					<h2>Evento</h2>
				</div>
				
				<form:form action="${salva}" modelAttribute="evento" enctype="multipart/form-data" cssClass="mb-2">
					
					<form:hidden path="id"/>
					<form:hidden path="imagemProd" />
					
					<div class="form-group">
						<label>Nome</label>
						<form:input path="nome" cssClass="form-control" />
					</div>
					
					<div class="form-group">
				<label>Casa de Show</label> 
				<select name="casaDeShowId">
					<c:forEach var="casaDeShow" items="${casaDeShows}">
						<option value="${casaDeShow.id}">${casaDeShow.nome}</option>
					</c:forEach>
				</select>
			</div>
					
					<div class="form-group">
						<label>Quantidade de ingressos</label>
						<form:input path="qtdIngressos" cssClass="form-control" />
					</div>
					
					<div class="form-group">
						<label>Descrição</label>
						<textarea rows="3" name="descricao" maxlength="2000" class="form-control">${evento.descricao}</textarea>
					</div>
					
					<div class="form-group">
						<label>Data do Evento</label> 
						<input type="date"
							class="form-control" name="dtEvento"
							value="${evento.dtEvento}" />
					</div>
					
					<div class="form-group">
						<label>Valor Unitario</label>
						<form:input path="valorUnitario" cssClass="form-control" />
					</div>
					
					<div class="form-group">
						<label>Imagem</label>
						<div class="custom-file">
							<input type="file" name="imagem" class="custom-file-input" value="${evento.imagemEncoded }" />
							<label class="custom-file-label">Escolha uma imagem</label> 
						</div>
					</div>
					
					<c:if test="${evento.id ne 0 }"> 
						<div class="mb-4">
							<img class="img-thumbnail" src="data:image/jpge;base64,${evento.imagemEncoded }" alt="imagem não encontrada"/>
						</div>
					</c:if>
					
					<input type="submit" class="btn btn-primary" name="salva" value="${evento.id == 0 ? 'Cadastrar' : 'Alterar'}" />
					
					<c:if test="${evento.id ne 0 }">
						<input type="submit" class="btn btn-warning" name="cancela" value="Cancelar" />
					</c:if>
				</form:form>
				<c:if test="${not empty eventos }">
					<table class="table table-striped">
						<thead class="thead-dark">
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Nome</th>
								<th scope="col">Valor Unitário</th>
								<th scope="col" colspan="2">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="evento" items="${eventos}">
								<tr>
									<td scope="row">${evento.id}</td>
									<td>${evento.nome}</td>
									<td>${evento.valorUnitario}</td>
									<td><a href="${altera}${evento.id}" class="btn btn-warning">Alterar</a></td>
									<td><a href="${deleta}${evento.id}" class="btn btn-danger" onclick="return confirm('Deseja deletar esse evento?');">Deletar</a></td>
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