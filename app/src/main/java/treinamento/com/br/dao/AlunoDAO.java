package treinamento.com.br.dao;

import java.util.ArrayList;
import java.util.List;

import treinamento.com.br.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunosList = new ArrayList();
    private static int contId = 1;

    public void create(Aluno alunoCriado) {
        alunoCriado.setId(contId);
        contId++;
        alunosList.add(alunoCriado);
    }

    public void editaAluno(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno alunos : alunosList) {
            if (alunos.getId() == aluno.getId()) {
                alunoEncontrado = alunos;
            }
        }

        if (alunoEncontrado != null) {
            int i = alunosList.indexOf(alunoEncontrado);
            alunosList.set(i, aluno);
        }
    }

    public List<Aluno> read() {
        return new ArrayList<>(alunosList);
    }

    public Aluno findPosition(int posicao) {
        return alunosList.get(posicao);
    }
}
