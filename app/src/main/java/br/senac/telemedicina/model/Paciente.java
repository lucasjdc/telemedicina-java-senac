package br.senac.telemedicina.model;

public class Paciente {
    private int id;
    private String nome;
    private int idade;
    private String pressao;
    private String glicose;
    private String colesterol;

    public Paciente(int id, String nome, int idade, String pressao, String glicose, String colesterol) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.pressao = pressao;
        this.glicose = glicose;
        this.colesterol = colesterol;
    }

    public Paciente() {
        // Construtor vazio, usado para criar um objeto sem inicializar com valores.
    }

    public Paciente(String nome, int idade, String pressao, String glicose, String colesterol) {
        this.nome = nome;
        this.idade = idade;
        this.pressao = pressao;
        this.glicose = glicose;
        this.colesterol = colesterol;
    }

    public int getId(){
        return id;
    }
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getPressaoArterial() {
        return pressao;
    }

    public String getGlicose() {
        return glicose;
    }

    public String getColesterol() {
        return colesterol;
    }

    @Override
    public String toString() {
        return nome + "-" + idade + " anos";
    }

    public void setNome(String novoPaciente) {
        this.nome = novoPaciente;
    }

    public void setIdade(int idadePaciente) {
        this.idade = idadePaciente;
    }
}
