package treinamento.com.br.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import treinamento.com.br.R;
import treinamento.com.br.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int i) {
        return alunos.get(i);
    }
    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    //PEGA TODA A VIEW QUE ESTA VINDO DO ACTIVITY
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCriada = criarView(viewGroup);
        popularAluno(i, viewCriada);
        return viewCriada;
    }

    //INICIAR
    private View criarView(ViewGroup viewGroup) {
        View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
        return viewCriada;
    }

    //POPULA O ALUNO COM OS DADOS
    private void popularAluno(int i, View viewCriada) {
        Aluno aluno = alunos.get(i); //PEGA O ALUNO PASSADO ATRAVES DO I

        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);

        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
    }

    public void atualiza(List<Aluno>alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
