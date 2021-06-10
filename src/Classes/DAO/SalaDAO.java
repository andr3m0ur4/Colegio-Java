package Classes.DAO;

import Classes.Aluno;
import Classes.Professor;
import Classes.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sqlite.conexao.Conexao;

public class SalaDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public SalaDAO() {
        this.con = new Conexao().getConexao();
    }
    
    public void inserir(Sala sala) {
        String sql = "INSERT INTO sala VALUES(?)";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, sala.getNome());
            stmt.execute();
            
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    public void cadastrarAluno(Sala sala, Aluno aluno) {
        String sql = "INSERT INTO sala_aluno VALUES(?, ?)";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, sala.getNome());
            stmt.setString(2, aluno.getNome());
            stmt.execute();
            
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    public void cadastrarProfessor(Sala sala, Professor professor) {
        String sql = "INSERT INTO sala_professor VALUES(?, ?)";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, sala.getNome());
            stmt.setString(2, professor.getNome());
            stmt.execute();
            
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    public List<Sala> listar() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM sala";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sala sala = new Sala(21);
                sala.setNome(rs.getString("nome"));
                sala.setPessoa(getProfessor(sala));
                
                for (Aluno aluno : getAlunos(sala)) {
                    sala.setPessoa(aluno);
                }
                
                salas.add(sala);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        
        return salas;
    }
    
    public Professor getProfessor(Sala sala) {
        String sql = "SELECT * FROM professor WHERE sala LIKE ?";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, sala.getNome());
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            Professor professor = new Professor(rs.getDouble("salario"), rs.getString("nome"), rs.getInt("idade"));
            ProfessorDAO professorDAO = new ProfessorDAO();
            
            for (String disciplina : professorDAO.getDisciplinas(professor)) {
                if (disciplina != null) professor.setDisciplina(disciplina);
            }
            
            rs.close();
            stmt.close();
            return professor;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    public List<Aluno> getAlunos(Sala sala) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE sala LIKE ?";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, sala.getNome());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getString("nome"), rs.getInt("idade"), rs.getString("curso"), rs.getDouble("mensalidade"));
                alunos.add(aluno);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        
        return alunos;
    }
}
