package com.codev.BackendCodGroup.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codev.BackendCodGroup.models.Pessoa;

@WebServlet("/ListarPessoasServlet")
public class ListarPessoasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pessoa> pessoas = PessoaDAO.obterListaDePessoas();
        
        // Define atributo no request para enviar a lista de pessoas
        request.setAttribute("pessoas", pessoas);
        
        // Encaminha para a página JSP
        request.getRequestDispatcher("listarPessoas.jsp").forward(request, response);
    }
}

