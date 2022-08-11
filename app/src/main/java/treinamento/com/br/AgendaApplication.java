package treinamento.com.br;

import android.app.Application;

import treinamento.com.br.dao.AlunoDAO;
import treinamento.com.br.model.Aluno;

public class AgendaApplication extends Application {
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunoTeste();
    }

    private void criaAlunoTeste() {
        alunoDAO.create(new Aluno("Gui", "gui@gmail.com", "34 9999999"));
        alunoDAO.create(new Aluno("Barbosa", "barbosa@gmail.com", "34 9999999"));
    }
}
