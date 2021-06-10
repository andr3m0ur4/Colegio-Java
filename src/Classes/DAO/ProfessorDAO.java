package Classes.DAO;

import Classes.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sqlite.conexao.Conexao;

public class ProfessorDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public ProfessorDAO() {
        this.con = new Conexao().getConexao();
    }
    
    public void inserir(Professor professor, String sala) {
        String sql = "INSERT INTO professor VALUES(?, ?, ?, ?)";
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setDouble(3, professor.getSalario());
            stmt.setString(4, sala);
            
            stmt.execute();
            stmt.close();
        } catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    public List<Professor> listar() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Professor professor = new Professor(rs.getDouble("salario"), rs.getString("nome"), rs.getInt("idade"));
                
                for (String disciplina : getDisciplinas(professor)) {
                    professor.setDisciplina(disciplina);
                }
                
                professores.add(professor);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        
        return professores;
    }
    
    public String[] listarDisciplinas() {
        String[] disciplinas = new String[10];
        int contador = 0;
        String sql = "SELECT rowid, * FROM disciplina";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                disciplinas[contador] = rs.getInt("rowid") + " - " + rs.getString("descricao");
                contador++;
            }
            
           rs.close();
           stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        
        return disciplinas;
    }
    
    public String[] getDisciplinas(Professor professor) {
        String[] disciplinas = new String[10];
        int contador = 0;
        String sql = "SELECT disciplina FROM professor_disciplina WHERE professor LIKE ?";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                disciplinas[contador] = rs.getString("disciplina");
                contador++;
            }
            
            rs.close();
            stmt.close();
        } catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
        
        return disciplinas;
    }
    
    public String getDisciplina(int id) {
        String disciplina = "";
        String sql = "SELECT descricao FROM disciplina WHERE rowid = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            
            disciplina = rs.getString("descricao");
            rs.close();
            stmt.close();
        } catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
        
        return disciplina;
    }
    
    public void setDisciplina(Professor professor, String disciplina) {
        String sql = "INSERT INTO professor_disciplina VALUES(?, ?)";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, disciplina);
            stmt.execute();
            
            stmt.close();
        } catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
