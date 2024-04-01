package com.codev.BackendCodGroup.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codev.BackendCodGroup.models.Pessoa;

import io.github.cdimascio.dotenv.Dotenv;

public class PessoaDAO {
    
    // Carrega as variáveis de .env
    private static final Dotenv dotenv = Dotenv.load();

    // conexão com o banco de dados
    private static Connection getConnection() throws SQLException {
        // Usa dotenv para variáveis de ambiente
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        return DriverManager.getConnection(url, username, password);
    }

    // lista as pessoas no banco de dados
    public static List<Pessoa> obterListaDePessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        String query = "SELECT id, nome, datanascimento, cpf, funcionario, gerente FROM pessoa";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId((long) rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setDatanascimento(rs.getDate("datanascimento"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setFuncionario(rs.getBoolean("funcionario"));
                pessoa.setGerente(rs.getBoolean("gerente"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }
}
