package Classes;

public class Pessoa {
    private String nome;
    private int idade;
    
    public Pessoa() {
        
    }
    
    public Pessoa(String nome, int idade) {
        setNome(nome);
        setIdade(idade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 3) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + " - Idade: " + this.idade;
    }
}
