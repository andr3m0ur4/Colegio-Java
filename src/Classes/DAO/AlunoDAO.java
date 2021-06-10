package Classes.DAO;

import Classes.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sqlite.conexao.Conexao;

public class AlunoDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public AlunoDAO() {
        this.con = new Conexao().getConexao();
    }
    
    public void inserir(Aluno aluno, String sala) {
        String sql = "INSERT INTO aluno VALUES(?, ?, ?, ?, ?)";
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setDouble(4, aluno.getMensalidade());
            stmt.setString(5, sala);
            
            stmt.execute();
            
            stmt.close();
        } catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getString("nome"), rs.getInt("idade"), rs.getString("curso"), rs.getDouble("mensalidade"));
                alunos.add(aluno);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        
        return alunos;
    }
}
