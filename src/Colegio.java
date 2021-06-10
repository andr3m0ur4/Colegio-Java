
import Classes.Aluno;
import Classes.DAO.AlunoDAO;
import Classes.DAO.ProfessorDAO;
import Classes.DAO.SalaDAO;
import Classes.Professor;
import Classes.Sala;
import java.util.Scanner;

public class Colegio {
    static Scanner entrada = new Scanner(System.in);
    static Scanner texto = new Scanner(System.in);
    static AlunoDAO alunoDAO = new AlunoDAO();
    static ProfessorDAO professorDAO = new ProfessorDAO();
    static SalaDAO salaDAO = new SalaDAO();
    
    public static void main(String[] args) {
        exibirMenu();
    }
    
    static void exibirMenu() {
        boolean op = true;
        
        while (op) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Sala");
            System.out.println("2 - Listar Salas");
            System.out.println("3 - Exibir Sala");
            System.out.println("0 - Sair");
            System.out.print(">>> ");
            
            switch (entrada.nextInt()) {
                case 0 -> {
                    op = false;
                }
                case 1 -> {
                    cadastrarSala();
                }
                case 2 -> {
                    listarSalas();
                }
                case 3 -> {
                    
                }
                
            }
        }
    }
    
    static void cadastrarSala() {
        Sala sala = new Sala();
        
        System.out.print("Digite o nome da sala: ");
        sala.setNome(texto.nextLine());
        
        salaDAO.inserir(sala);
        
        cadastrarProfessor(sala);
        cadastrarAlunos(sala);
    }
    
    static void cadastrarAlunos(Sala sala) {
        for (int i = 0; i < 20; i++) {
            Aluno aluno = new Aluno();

            System.out.print("Digite o nome do aluno: ");
            aluno.setNome(texto.nextLine());

            System.out.print("Digite a idade do aluno: ");
            aluno.setIdade(entrada.nextInt());

            System.out.print("Digite o curso do aluno: ");
            aluno.setCurso(texto.nextLine());

            System.out.print("Digite o valor da mensalidade do aluno: R$ ");
            aluno.setMensalidade(entrada.nextDouble());

            alunoDAO.inserir(aluno, sala.getNome());
            
            System.out.print("Deseja cadastrar outro aluno? [S/N] ");
            if (entrada.next().toUpperCase().charAt(0) == 'N') break;
        }
    }
    
    static void cadastrarProfessor(Sala sala) {
        Professor professor = new Professor();
        
        System.out.print("Digite o nome do professor: ");
        professor.setNome(texto.nextLine());
        
        System.out.print("Digite a idade do professor: ");
        professor.setIdade(entrada.nextInt());
        
        System.out.print("Digite o salário do professor: ");
        professor.setSalario(entrada.nextDouble());
        professorDAO.inserir(professor, sala.getNome());
        
        cadastrarDisciplinas(professor);
    }
    
    static void cadastrarDisciplinas(Professor professor) {
        boolean op = true;
        
        while (op) {
            System.out.println("Escolha uma disciplina: ");
            for (String disciplina : professorDAO.listarDisciplinas()) {
                System.out.println(disciplina);
            }
            System.out.println("0 - Sair");
            System.out.print(">>> ");
            int id = entrada.nextInt();
            
            if (id == 0) break;
            
            professor.setDisciplina(professorDAO.getDisciplina(id));
            professorDAO.setDisciplina(professor, professorDAO.getDisciplina(id));
        }
    }
    
    static void listarSalas() {
        for (Sala sala : salaDAO.listar()) {
            System.out.println("\n\t*** Sala ***");
            System.out.println(sala);
            
            System.out.println("\t*** Professor ***");
            System.out.println(sala.getPessoa(0));
            
            System.out.println("\t*** Alunos ***");
            sala.getPessoas();
        }
    }
}
