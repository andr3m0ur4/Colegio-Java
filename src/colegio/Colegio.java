package colegio;

import Classes.Aluno;
import Classes.DAO.AlunoDAO;
import Classes.DAO.ProfessorDAO;
import Classes.Professor;
import java.util.List;

public class Colegio {

    public static void main(String[] args) {
        AlunoDAO dao = new AlunoDAO();
        ProfessorDAO dao2 = new ProfessorDAO();
        
        //Aluno aluno = new Aluno("Andre", 32, "Java", 200);
        //dao.inserir(aluno);
        //Professor professor = new Professor(4500, "Cris", 40);
        //dao2.inserir(professor);
        
        /*List<Aluno> alunos = dao.listar();
        
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }*/
        
        //List<Professor> professores = dao2.listar();
        String[] disciplinas = dao2.listarDisciplinas();
        
        for (String disciplina : disciplinas) {
            System.out.println(disciplina);
        }
    }
    
}
