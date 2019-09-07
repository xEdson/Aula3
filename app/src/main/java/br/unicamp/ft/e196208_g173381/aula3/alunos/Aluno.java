package br.unicamp.ft.e196208_g173381.aula3.alunos;


public class Aluno {

    private String nome;
    private int foto;
    private String descricao;

    public Aluno(String nome, int foto, String descricao) {
        this.descricao=descricao;
        this.nome=nome;
        this.foto=foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
