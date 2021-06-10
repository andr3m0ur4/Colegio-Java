package Classes;

public class Sala {
    private String nome;
    private Pessoa[] pessoa;

    public Sala() {
        pessoa = new Pessoa[21];
    }
    
    public Sala(int qtdePessoas) {
        pessoa = new Pessoa[qtdePessoas];
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
    
    public void setPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        for (int i = 0; i < this.pessoa.length; i++) {
            if (this.pessoa[i] == null) {
                this.pessoa[i] = pessoa;
                return;
            }
        }
    }
    
    public Pessoa getPessoa(int indice) {
        return this.pessoa[indice];
    }
    
    public void getPessoas() {
        for (int i = 1; i < pessoa.length; i++) {
            if (pessoa[i] != null) {
                System.out.println(pessoa[i]);
            }
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n";
    }
}
