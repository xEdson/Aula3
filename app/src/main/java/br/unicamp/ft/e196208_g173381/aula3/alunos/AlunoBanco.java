package br.unicamp.ft.e196208_g173381.aula3.alunos;

public class AlunoBanco {

    private int id;
    private String nome;
    private int tentativaGlobal;
    private int tentativaSelf;
    private int acerto;
    private int erro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTentativaGlobal() {
        return tentativaGlobal;
    }

    public void setTentativaGlobal(int tentativaGlobal) {
        this.tentativaGlobal = tentativaGlobal;
    }

    public int getTentativaSelf() {
        return tentativaSelf;
    }

    public void setTentativaSelf(int tentativaSelf) {
        this.tentativaSelf = tentativaSelf;
    }

    public int getAcerto() {
        return acerto;
    }

    public void setAcerto(int acerto) {
        this.acerto = acerto;
    }

    public int getErro() {
        return erro;
    }

    public void setErro(int erro) {
        this.erro = erro;
    }

    public void incrementAcerto(){
        this.acerto++;
    }
    public void incrementErro(){
        this.erro++;
    }
    public void incrementTentativaGlobal(){
        this.tentativaGlobal++;
    }
    public void incrementTentativaSelf(){
        this.tentativaSelf++;
    }
}
