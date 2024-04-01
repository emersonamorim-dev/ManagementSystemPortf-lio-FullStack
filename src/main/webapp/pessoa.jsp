<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.codev.BackendCodGroup.models.Pessoa" %>
<%@ page import="com.codev.BackendCodGroup.servlets.PessoaDAO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Pessoas</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // busca pessoas e atualizar a tabela
            function fetchPessoas() {
                fetch('http://localhost:8085/api/pessoa')
                    .then(response => response.json())
                    .then(data => {
                        console.log("Dados recebidos: ", data); 
                        updateTable(data);
                    })
                    .catch(error => {
                        console.error('Falha ao buscar pessoas:', error);
                        showMessage('Erro ao buscar pessoas.', false);
                    });
            }
        
        // atualiza a tabela com os dados recebidos
        function updateTable(pessoas) {
         const tbody = document.querySelector('table tbody');
         let tableHTML = '';
     
         pessoas.forEach(pessoa => {
             const id = pessoa.id || '---';
             const nome = pessoa.nome || '---';
             const cpf = pessoa.cpf || '---';
             const funcionarioStatus = pessoa.funcionario ? 'Sim' : 'Não';
             const gerenteStatus = pessoa.gerente ? 'Sim' : 'Não';
             
             const datanascimento = new Date(pessoa.datanascimento).toLocaleDateString('pt-BR', {
                 year: 'numeric', month: '2-digit', day: '2-digit'
             });
     
     
         });
     
         tbody.innerHTML = tableHTML;
}

            fetchPessoas(); 
        
            var form = document.querySelector('form');
            form.onsubmit = function(e) {
                e.preventDefault();
                var data = {
                    nome: document.getElementById('nome').value,
                    datanascimento: document.getElementById('datanascimento').value,
                    cpf: document.getElementById('cpf').value,
                    funcionario: document.getElementById('funcionario').checked,
                    gerente: document.getElementById('gerente').checked
                };
        
                fetch(form.action, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                }).then(response => {
                    if(response.ok) {
                        showMessage('Pessoa cadastrada com sucesso.');
                        fetchPessoas(); 
                        return response.json();
                    }
                    throw new Error('Erro na requisição: ' + response.statusText);
                }).catch(error => {
                    console.error(error);
                    showMessage('Erro ao cadastrar pessoa.', false);
                });
            };
        
            // mostra mensagens de sucesso ou erro
            function showMessage(message, isSuccess = true) {
                const messageDiv = document.createElement('div');
                messageDiv.textContent = message;
                messageDiv.className = `alert ${isSuccess ? 'alert-success' : 'alert-danger'}`;
                document.body.insertBefore(messageDiv, document.body.firstChild);
                setTimeout(() => messageDiv.remove(), 5000); 
            }
        
            // deleta pessoa
            function deletePessoa(id) {
                fetch(`http://localhost:8085/api/pessoa/${id}`, { method: 'DELETE' })
                    .then(response => {
                        if (response.ok) {
                            showMessage('Pessoa apagada com sucesso.');
                            fetchPessoas(); // Recarrega a lista de pessoas ou remove a linha da tabela
                        } else {
                            throw new Error('Erro ao deletar pessoa.');
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        showMessage('Erro ao deletar pessoa.', false);
                    });
            }
        });
        </script>

</head>

<style>
    .footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: #333;
        color: white;
        text-align: center;
        padding: 10px 0;
    }

</style>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">EmerDev</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  
    <div class="collapse navbar-collapse" id="navbarContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="membro.jsp">Membros</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="pessoa.jsp">Pessoas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="projeto.jsp">Projetos</a>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="https://www.linkedin.com/in/emerson-amorim-dev/" target="_blank"><i class="fab fa-linkedin"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="https://github.com/emersonamorim-dev" target="_blank"><i class="fab fa-github"></i></a>
          </li>
      </ul>
    </div>
  </nav>
    <br/>
    <div class="container mt-5">


        <h2>Lista de Pessoas</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Data de Nascimento</th>
                    <th>CPF</th>
                    <th>Funcionário</th>
                    <th>Gerente</th>
                </tr>
            </thead>
            <tbody></tbody> 
            <% 
            List<Pessoa> pessoas = PessoaDAO.obterListaDePessoas(); 
            for (Pessoa pessoa : pessoas) { 
        %>
            <tr>
                <td><%= pessoa.getId() %></td>
                <td><%= pessoa.getNome() %></td>
                <td><%= pessoa.getDatanascimento() %></td>
                <td><%= pessoa.getCpf() %></td>
                <td><%= pessoa.isFuncionario() ? "Sim" : "Não" %></td>
                <td><%= pessoa.isGerente() ? "Sim" : "Não" %></td>
            </tr>
        <% } %>
        </table>


    </div>
    <div class="container mt-5">
        <h2>Adicionar Pessoa</h2>
        <form action="http://localhost:8085/api/pessoa" method="post">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" class="form-control" id="nome" name="nome">
            </div>
            <div class="form-group">
                <label for="datanascimento">Data de Nascimento:</label>
                <input type="date" class="form-control" id="datanascimento" name="datanascimento">
            </div>
            <div class="form-group">
                <label for="cpf">CPF:</label>
                <input type="text" class="form-control" id="cpf" name="cpf">
            </div>
            <div class="form-group">
                <label for="funcionario">Funcionário:</label>
                <input type="checkbox" class="form-check-input" id="funcionario" name="funcionario">
            </div>
            <div class="form-group">
                <label for="gerente">Gerente:</label>
                <input type="checkbox" class="form-check-input" id="gerente" name="gerente">
            </div>
            <button type="submit" class="btn btn-primary">Adicionar</button>
        </form>
        <br/>
        <br/>
        <br/>
    </div>
</div>

    <footer class="footer">
        <div class="container">
            <div class="social-icons">

            </div>
            <p>EmerSoftware &copy;2023</p>
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
