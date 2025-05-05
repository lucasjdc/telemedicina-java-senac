package br.senac.telemedicina.model;

public class Paciente {
    private String nome;
    private String idade;
    private String pressao;
    private String glicose;
    private String colesterol;

    public Paciente(String nome, String idade, String pressao, String glicose, String colesterol) {
        this.nome = nome;
        this.idade = idade;
        this.pressao = pressao;
        this.glicose = glicose;
        this.colesterol = colesterol;
    }

    public String getNome() {
        return nome;
    }

    public String getIdade() {
        return idade;
    }

    public String getPressao() {
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
}
