package model;

public class Aluno {

    private int id;
    private String nome;
    private int idade;
    private String objetivo;

    public Aluno(int id, String nome, int idade, String objetivo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.objetivo = objetivo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void exibirAluno() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Objetivo: " + objetivo);
        System.out.println("----------------------");
    }
}