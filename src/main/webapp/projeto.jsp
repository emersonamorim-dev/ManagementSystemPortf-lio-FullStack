<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.codev.BackendCodGroup.models.Projeto" %>
<%@ page import="com.codev.BackendCodGroup.servlets.ProjetoDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Projetos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
<script>
    document.addEventListener('DOMContentLoaded', function() {
        function fetchProjetos() {
            fetch('http://localhost:8085/api/projeto')
                .then(response => response.json())
                .then(data => {
                    updateTable(data);
                })
                .catch(error => {
                    console.error('Falha ao buscar projetos:', error);
                    showMessage('Erro ao buscar projetos.', false);
                });
        }
    
        // Atualiza a tabela com os dados recebidos
        function updateTable(projetos) {
            const tbody = document.querySelector('table tbody');
            let tableHTML = '';
    
            projetos.forEach(projeto => {
                let {id, nome, descricao, status, orcamento, risco, dataInicio, dataPrevisaoFim, dataFim} = projeto;
                dataInicio = dataInicio ? new Date(dataInicio).toLocaleDateString('pt-BR') : '---';
                dataPrevisaoFim = dataPrevisaoFim ? new Date(dataPrevisaoFim).toLocaleDateString('pt-BR') : '---';
                dataFim = dataFim ? new Date(dataFim).toLocaleDateString('pt-BR') : '---';
    
            });
    
            tbody.innerHTML = tableHTML;
        }
    
        function showMessage(message, isSuccess) {
            alert(message); 
        }

        fetchProjetos();
    });
    
    // Formulário
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('projetoForm').addEventListener('submit', function(e) {
            e.preventDefault();
    
            const formData = new FormData(this);
            const jsonData = {};
            formData.forEach((value, key) => jsonData[key] = value);
    
            fetch('http://localhost:8085/api/projeto', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(jsonData),
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                alert('Projeto Adicionado com Sucesso!'); 
                window.location.reload();
                fetchProjetos(); 
            })
            .catch((error) => {
                console.error('Error:', error);
                showMessage('Erro ao criar o projeto.', false);
            });
        });
    });
    </script>


<script>

// preencher o formulário
function preencherFormularioDeEdicao(dados) {
    $('#dataInicio').val(dados.dataInicio);
    $('#dataPrevisaoFim').val(dados.dataPrevisaoFim);
    $('#descricao').val(dados.descricao);
    $('#orcamento').val(dados.orcamento);
    $('#projectSelectStatus').val(dados.status);
    $('#projectSelectRisco').val(dados.risco);
}

// buscar os dados do projeto para edição
function buscarDadosDoProjetoParaEdicao(projetoId) {
    $.ajax({
        url: `http://localhost:8085/api/projeto/${projetoId}`,
        method: 'GET',
        success: function(projeto) {
            preencherFormularioDeEdicao(projeto);
            $('#associateModal').modal('show');
        },
        error: function(xhr, status, error) {
            console.error('Erro ao buscar projeto:', error);
            alert('Não foi possível carregar os dados do projeto para edição.');
        }
    });
}

// editar o projeto
function editarProjeto() {
    var projetoId = $('#projetoId').val();
    var dataInicio = $('#dataInicio').val();
    var dataPrevisaoFim = $('#dataPrevisaoFim').val();
    var descricao = $('#descricao').val();
    var status = $('#projectSelectStatus').val();
    var risco = $('#projectSelectRisco').val();
    var orcamento = $('#orcamento').val();

    var projetoData = {
        dataInicio: dataInicio,
        dataPrevisaoFim: dataPrevisaoFim,
        descricao: descricao,
        status: status,
        risco: risco,
        orcamento: orcamento
    };

    $.ajax({
        url: `http://localhost:8085/api/projeto/${projetoId}`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(projetoData),
        success: function(response) {
            console.log('Projeto atualizado com sucesso!', response);
            alert('Projeto atualizado com sucesso!');
            $('#associateModal').modal('hide');
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error('Erro ao atualizar o projeto:', error);
            alert('Falha ao atualizar o projeto. Verifique os logs para mais detalhes.');
        }
    });
}

// deletar projeto
function deletarProjeto(id) {
    $.ajax({
        url: `http://localhost:8085/api/projeto/${id}`, // Substitua o número fixo pelo ID dinâmico
        method: 'DELETE',
        success: function(response) {
            alert('Projeto apagado com sucesso.');
            $('#associateModaldelete').modal('hide');
            location.reload(); // Recarrega a página
        },
        error: function(xhr, status, error) {
            console.error('Erro ao apagar projeto:', error);
            alert('Falha ao apagar projeto. O projeto não foi encontrado ou outro erro ocorreu.');
        }
    });
}

// clique ao botão de deletar
$('.delete-project-btn').on('click', function() {
    var projetoId = $(this).data('id'); 
    deletarProjeto(projetoId); 
});


$(document).ready(function() {
    // Event listener
    $('.edit-project-btn').on('click', function() {
        var projetoId = $(this).data('id');
        $('#projetoId').val(projetoId); 
        buscarDadosDoProjetoParaEdicao(projetoId);
    });

    $('#confirmDeleteBtn').on('click', function() {
        var projetoId = $('#projetoId').val(); 
        deletarProjeto(projetoId);
    });

    $('#editarForm').on('submit', function(e) {
        e.preventDefault();
        editarProjeto();
    });
});

        
        // associa membro a projeto
        window.editarProjeto = function() {
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
<body>
    <br/>
    <div class="container mt-5">
        <h2>Lista de Projetos</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Data Início</th>
                    <th>Data Previsão Fim</th>
                    <th>Data Fim</th>
                    <th>Descrição</th>
                    <th>Status</th>
                    <th>Orçamento</th>
                    <th>Risco</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody></tbody>
            <% 
            List<Projeto> projetos = ProjetoDAO.obterListaDeProjeto(); 
            for (Projeto projeto : projetos) { 
                String dataInicioStr = (projeto.getDataInicio() != null) ? new SimpleDateFormat("dd/MM/yyyy").format(projeto.getDataInicio()) : "---";
                String dataPrevisaoFimStr = (projeto.getDataPrevisaoFim() != null) ? new SimpleDateFormat("dd/MM/yyyy").format(projeto.getDataPrevisaoFim()) : "---";
                String dataFimStr = (projeto.getDataFim() != null) ? new SimpleDateFormat("dd/MM/yyyy").format(projeto.getDataFim()) : "---";
            %>
            <tr>
                <td><%= projeto.getId() %></td>
                <td><%= projeto.getNome() %></td>
                <td><%= dataInicioStr %></td>
                <td><%= dataPrevisaoFimStr %></td>
                <td><%= dataFimStr %></td>
                <td><%= projeto.getDescricao() %></td>
                <td><%= projeto.getStatus() %></td>
                <td><%= projeto.getOrcamento() %></td>
                <td><%= projeto.getRisco() %></td>
                <td>
                <button type="button" class="btn btn-success edit-project-btn" data-toggle="modal" data-target="#associateModal" data-id="<%= projeto.getId() %>">Editar</button>
            </td>
            <td>
                <button type="button" class="btn btn-danger delete-project-btn" data-toggle="modal" data-target="#associateModaldelete" data-id="<%= projeto.getId() %>">Apagar</button>

            </td>
            </tr>
            <% } %>
            
        </table>
        <div class="container mt-5">
            <!-- Tabela de projetos existente -->
        
            <h2>Adicionar Novo Projeto</h2>
            <form id="projetoForm">
    
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" id="nome" name="nome" required>
                </div>
                <div class="form-group">
                    <label for="dataInicio">Data de Início:</label>
                    <input type="date" class="form-control" id="dataInicio" name="dataInicio" required>
                </div>
                <div class="form-group">
                    <label for="dataPrevisaoFim">Data de Previsão de Fim:</label>
                    <input type="date" class="form-control" id="dataPrevisaoFim" name="dataPrevisaoFim">
                </div>
                <div class="form-group">
                    <label for="descricao">Descrição:</label>
                    <textarea class="form-control" id="descricao" name="descricao" required></textarea>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status" required>
                        <option value="Planejado">Planejado</option>
                        <option value="Em Andamento">Em Andamento</option>
                        <option value="Concluído">Concluído</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="orcamento">Orçamento:</label>
                    <input type="number" step="0.01" class="form-control" id="orcamento" name="orcamento" required>
                </div>
                <div class="form-group">
                    <label for="risco">Risco:</label>
                    <select class="form-control" id="risco" name="risco" required>
                        <option value="Baixo">Baixo</option>
                        <option value="Médio">Médio</option>
                        <option value="Alto">Alto</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Adicionar Projeto</button>
            </form>
            <br/>
            <br/>
            <br/>
        </div>       
    </div>

<!-- Modal -->
<div class="modal fade" id="associateModal" tabindex="-1" aria-labelledby="associateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="associateModalLabel">Editar Projetos</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
  
                <form id="editarForm">
                    
                    <div class="form-group">
                        <label for="dataInicio">Data de Início:</label>
                        <input type="date" class="form-control" id="dataInicio" name="dataInicio" required>
                    </div>
                    <div class="form-group">
                        <label for="dataPrevisaoFim">Data de Previsão de Fim:</label>
                        <input type="date" class="form-control" id="dataPrevisaoFim" name="dataPrevisaoFim">
                    </div>
                    <div class="form-group">
                        <label for="descricao">Descrição:</label>
                        <textarea class="form-control" id="descricao" name="descricao" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="projectSelectStatus">Status:</label>
                        <select class="form-control" id="projectSelectStatus">
                        </select>
  
                        <label for="projectSelectRisco">Risco:</label>
                        <select class="form-control" id="projectSelectRisco">
                        </select>
  
                    </select>
                    <div class="form-group">
                        <label for="orcamento">Orçamento:</label>
                        <input type="number" step="0.01" class="form-control" id="orcamento" name="orcamento" required>
                    </div>
  
                    </div>
                    <input type="hidden" id="membroId" value="">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" onclick="editarProjeto()">Editar</button>
            </div>
        </div>
    </div>
  </div>


  <div class="modal fade" id="associateModaldelete" tabindex="-1" aria-labelledby="associateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="associateModalLabel">Deletar Projetos</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Tem certeza de que deseja deletar este projeto?
                <input type="hidden" id="projetoId">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-danger" onclick="deletarProjeto()">Apagar</button>
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
