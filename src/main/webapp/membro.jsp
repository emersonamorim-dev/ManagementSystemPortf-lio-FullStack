<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.codev.BackendCodGroup.models.Membro" %>
<%@ page import="com.codev.BackendCodGroup.models.Projeto" %>
<%@ page import="com.codev.BackendCodGroup.servlets.ProjetoDAO" %>
<%@ page import="com.codev.BackendCodGroup.servlets.MembroDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Membros</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <!-- Substitua a versão slim por esta versão completa do jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
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
    
        .text-black {
            color: rgb(15, 175, 52);
        }
    </style>


<script>
  $(document).ready(function() {
      // Adiciona um ouvinte de eventos
      $('#associateModal').on('show.bs.modal', function(event) {
          var button = $(event.relatedTarget); 
          var projetoId = button.data('projetoid'); 
          var modal = $(this);
          modal.find('#projetoId').val(projetoId);

          // Limpa as opções anteriores
          var select = modal.find('#projectSelect');
          select.empty();

          // Chamada AJAX para buscar os projetos
          $.ajax({
              url: 'http://localhost:8085/api/projeto', 
              method: 'GET',
              success: function(projetos) {
                  projetos.forEach(function(projeto) {
                      select.append(new Option(projeto.nome, projeto.id));
                  });
              },
              error: function(xhr, status, error) {
                  console.error('Falha ao buscar projetos: ', error);
              }
          });
          
      });


      $('#associateModal').on('show.bs.modal', function(event) {
          var button = $(event.relatedTarget); 
          var pessoaId = button.data('pessoaid'); 
          var modal = $(this);
          modal.find('#pessoaId').val(pessoaId);

          var select = modal.find('#pessoaSelect');
          select.empty();

          // Chamada AJAX para buscar os projetos
          $.ajax({
              url: 'http://localhost:8085/api/pessoa', 
              method: 'GET',
              success: function(pessoas) {
                  pessoas.forEach(function(pessoa) {
                      select.append(new Option(pessoa.nome, pessoa.id));
                  });
              },
              error: function(xhr, status, error) {
                  console.error('Falha ao buscar projetos: ', error);
              }
          });
          
      });
      
      

      // associa membro a projeto
      window.associarPessoaProjeto = function() {
          var pessoaId = $('#pessoaSelect').val();
          var projetoId = $('#projectSelect').val();

          // Chamada AJAX para associar o membro ao projeto selecionado
          $.ajax({
              url: 'http://localhost:8085/api/projeto/pessoa-projetos', 
              method: 'POST',
              contentType: 'application/json',
              data: JSON.stringify({ pessoaId: pessoaId, projetoId: projetoId }),
              success: function(response) {
                  alert('Membro associado ao projeto com sucesso.');
                  $('#associateModal').modal('hide');
                  location.reload(); 
              },
              error: function(xhr, status, error) {
                  console.error('Erro ao associar Membro: ', error);
                  alert('Falha ao associar Membro ao Projeto.');
              }
          });
      };
  });
</script>


      <script>

        document.addEventListener('DOMContentLoaded', function() {
            // busca pessoas e atualiza a tabela
            function fetchMembros() {
                fetch('http://localhost:8085/api/membro')
                    .then(response => response.json())
                    .then(data => {
                        console.log("Dados recebidos: ", data); 
                        updateTable(data);
                    })
                    .catch(error => {
                        console.error('Falha ao buscar membros:', error);
                        showMessage('Erro ao buscarmembros', false);
                    });
            }
        
        // atualiza a tabela com os dados recebidos
        function updateTable(membros) {
         const tbody = document.querySelector('table tbody');
         let tableHTML = ''; 
     
         membros.forEach(membro => {
             const id = membro.id || '---';
             const nome = membro.nome || '---';
             const atribuicao = membro.atribuicao || '---';
     
     
         });
     
         tbody.innerHTML = tableHTML;
}

            fetchMembros(); 
        
            var form = document.querySelector('form');
            form.onsubmit = function(e) {
                e.preventDefault();
                var data = {
                    nome: document.getElementById('nome').value,
                    atribuicao: document.getElementById('atribuicao').value,
                };
        
                fetch(form.action, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                }).then(response => {
                    if(response.ok) {
                        showMessage('Membro cadastrado com sucesso.');
                        fetchMembros(); 
                        return response.json();
                    }
                    throw new Error('Erro na requisição: ' + response.statusText);
                }).catch(error => {
                    console.error(error);
                    showMessage('Erro ao cadastrar membro.', false);
                });
            };
        
            // mensagens de sucesso ou erro
            function showMessage(message, isSuccess = true) {
                const messageDiv = document.createElement('div');
                messageDiv.textContent = message;
                messageDiv.className = `alert ${isSuccess ? 'alert-success' : 'alert-danger'}`;
                document.body.insertBefore(messageDiv, document.body.firstChild);
                setTimeout(() => messageDiv.remove(), 5000); 
            }
        
        });

 

      </script>
      
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


        <h2>Lista de Membros</h2>
        <table class="table">
            <thead>
                <tr>
                  <th>ID</th>
                  <th>Nome</th>
                  <th>Atribuicões</th>
                  <th>Ações</th>
                </tr>
            </thead>
            <tbody></tbody> 
            <% 
            List<Membro> membros = MembroDAO.obterListaDeMembros(); 
            for (Membro membro : membros) { 
        %>
            <tr>
              <td><%= membro.getId() %></td>
              <td><%= membro.getNome() %></td>
              <td><%= membro.getAtribuicao() %></td>
              <td>
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#associateModal" data-memberid="<%= membro.getId() %>">Associar a Projeto</button>

              </td>
            </tr>
        <% } %>
        </table>
    </div>
  
    <!-- Modal -->
<div class="modal fade" id="associateModal" tabindex="-1" aria-labelledby="associateModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="associateModalLabel">Associar Membros a Projetos</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
              </button>
          </div>
          <div class="modal-body">

              <form id="associateForm">
                  <div class="form-group">
                      <label for="projectSelect">Selecionar Projeto:</label>
                      <select class="form-control" id="projectSelect">

                    </select>

                    <label for="projectSelect">Selecionar Membro:</label>
                    <select class="form-control" id="pessoaSelect">

                  </select>

                  </div>
                  <input type="hidden" id="memberId" value="">
              </form>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
              <button type="button" class="btn btn-primary" onclick="associarPessoaProjeto()">Associar</button>
          </div>
      </div>
  </div>
</div>

    <footer class="footer">
        <div class="container">
            <div class="social-icons">

            </div>
            <p>EmerSoftware &copy;2023</p>
        </div>
    </footer>

</body>
</html>
