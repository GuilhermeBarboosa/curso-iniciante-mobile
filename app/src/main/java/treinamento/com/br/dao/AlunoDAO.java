package treinamento.com.br.dao;

import java.util.ArrayList;
import java.util.List;

import treinamento.com.br.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunosList = new ArrayList();

    public void create(Aluno alunoCriado) {
        alunosList.add(alunoCriado);
    }

    public List<Aluno> read() {
        return new ArrayList<>(alunosList);
    }

    public Aluno findPosition(int posicao) {
        return alunosList.get(posicao);
    }
}
