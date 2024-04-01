<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deletar Membro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Deletar Membro</h2>
        <p>Você tem certeza que deseja deletar o membro: ${member.nome}?</p>
        <form action="confirmarDelecao" method="post">
            <input type="hidden" name="id" value="${member.id}">
            <button type="submit" class="btn btn-danger">Confirmar</button>
            <a href="membros.jsp" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>
