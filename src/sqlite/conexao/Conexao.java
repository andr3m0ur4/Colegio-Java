package sqlite.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection getConexao() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:colegio.db");
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
