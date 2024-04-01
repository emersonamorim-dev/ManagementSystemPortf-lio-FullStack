<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Desafio Técnico - CodGroup</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">

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
      

    <div class="jumbotron">
        <div class="container">
          <h1 class="display-3">Desafio Técnico - CodGroup</h1>
          <p></p>
          <!-- Botão e mais conteúdo -->
        </div>
    </div>

    <div class="container">
            <!-- Example row of columns -->
            <div class="row">
              <div class="col-md-4">
                <h2>Membros</h2>
                <p></p>
                <p><a class="btn btn-secondary" href="membro.jsp" role="button">Veja Mais &raquo;</a></p>
              </div>
              <div class="col-md-4">
                <h2>Pessoas</h2>
                <p> </p>
                <p><a class="btn btn-secondary" href="pessoa.jsp" role="button">Veja Mais &raquo;</a></p>
              </div>
              <div class="col-md-4">
                <h2>Projetos</h2>
                <p></p>
                <p><a class="btn btn-secondary" href="projeto.jsp" role="button">Veja Mais &raquo;</a></p>
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
