package br.senac.telemedicina.model;

public class Consulta {
    private int id;
    private int pacienteId;
    private int medicoId;
    private String dataConsulta;
    private String diagnostico;
    private String prescricao;

    public Consulta(int pacienteId, int medicoId, String dataConsulta, String diagnostico, String prescricao) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataConsulta = dataConsulta;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
    }

    public Consulta(int id, int pacienteId, int medicoId, String dataConsulta, String diagnostico, String prescricao) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataConsulta = dataConsulta;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", pacienteId=" + pacienteId +
                ", medicoId=" + medicoId +
                ", dataConsulta='" + dataConsulta + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", prescricao='" + prescricao + '\'' +
                '}';
    }
}