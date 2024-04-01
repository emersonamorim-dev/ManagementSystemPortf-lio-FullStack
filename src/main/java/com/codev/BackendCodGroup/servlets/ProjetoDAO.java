package com.codev.BackendCodGroup.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

import com.codev.BackendCodGroup.exceptions.DataAccessException;
import com.codev.BackendCodGroup.models.Projeto;


public class ProjetoDAO {

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
    // lista os projetos no banco de dados
    public static List<Projeto> obterListaDeProjeto() {
        List<Projeto> projetos = new ArrayList<>();
        String query = "SELECT id, nome, data_inicio as dataInicio, data_previsao_fim as dataPrevisaoFim, data_fim as dataFim, descricao, status, orcamento, risco FROM projeto";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getLong("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDataInicio(rs.getDate("dataInicio")); 
                projeto.setDataPrevisaoFim(rs.getDate("dataPrevisaoFim")); 
                projeto.setDataFim(rs.getDate("dataFim")); 
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setStatus(rs.getString("status"));
                projeto.setOrcamento(rs.getFloat("orcamento"));
                projeto.setRisco(rs.getString("risco"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao obter lista de projetos", e);
        }
        return projetos;
    }

    public List<Projeto> todosProjetos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT nome FROM projeto"; 
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setNome(rs.getString("nome"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }
}