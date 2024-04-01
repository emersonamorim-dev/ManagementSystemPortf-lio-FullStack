package com.codev.BackendCodGroup.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codev.BackendCodGroup.models.Membro;

import io.github.cdimascio.dotenv.Dotenv;

public class MembroDAO {

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

    // lista os membros no banco de dados
    public static List<Membro> obterListaDeMembros() {
        List<Membro> membros = new ArrayList<>();
        String query = "SELECT id, nome, atribuicao, idpessoa, idprojeto FROM membro";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Membro membro = new Membro();
                membro.setId(rs.getLong("id"));
                membro.setNome(rs.getString("nome"));
                membro.setAtribuicao(rs.getString("atribuicao"));

                membro.setIdpessoa(rs.getLong("idpessoa"));
                membro.setIdprojeto(rs.getLong("idprojeto"));

                membros.add(membro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membros;
    }

}
    
