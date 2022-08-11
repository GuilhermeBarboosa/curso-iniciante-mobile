package treinamento.com.br.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int id = 0;
    private String nome;
    private String email;
    private String telefone;

    public Aluno(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Aluno() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + telefone;
    }

    public boolean isExist() {
        return id > 0;
    }
}
