package Classes;

public class Professor extends Pessoa {
    private String[] disciplina;
    private double salario;

    public Professor() {
        this.disciplina = new String[10];
    }

    public Professor(double salario, String nome, int idade) {
        super(nome, idade);
        setSalario(salario);
        this.disciplina = new String[10];
    }

    public String getDisciplina(int indice) {
        return this.disciplina[indice];
    }

    public void setDisciplina(String disciplina) {
        if (disciplina == null || disciplina.isEmpty()) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        for (int i = 0; i < this.disciplina.length; i++) {
            if (this.disciplina[i] == null) {
                this.disciplina[i] = disciplina;
                return;
            }
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.salario = salario;
    }

    @Override
    public String toString() {
        String disciplinas = "\nDisciplinas: ";
        for (String disciplina : this.disciplina) {
            if (disciplina != null) {
                disciplinas += disciplina + " | ";
            }
        }
        
        return super.toString() + " - Salário: R$ " + this.salario + disciplinas + "\n";
    }
}
