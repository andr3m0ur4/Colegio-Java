package Classes;

public class Aluno extends Pessoa {
    private String curso;
    private double mensalidade;

    public Aluno() {
    }

    public Aluno(String nome, int idade, String curso, double mensalidade) {
        super(nome, idade);
        setCurso(curso);
        setMensalidade(mensalidade);
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        if (curso == null || curso.isEmpty()) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.curso = curso;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(double mensalidade) {
        if (mensalidade < 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.mensalidade = mensalidade;
    }

    @Override
    public String toString() {
        return super.toString() + " - Curso: " + this.curso + " - Mensalidade: R$ " + this.mensalidade + "\n";
    }
}
