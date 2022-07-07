package treinamento.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import treinamento.com.br.R;
import treinamento.com.br.dao.AlunoDAO;
import treinamento.com.br.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);
        setContentView(R.layout.activity_lista_alunos);

        alunoDAO.create(new Aluno("Gui", "gui@gmail.com", "34 9999999"));
        alunoDAO.create(new Aluno("Barbosa", "barbosa@gmail.com", "34 9999999"));

        configuraNovoAluno();
    }

    private void configuraNovoAluno() {
        FloatingActionButton criarAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        criarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormulario();
            }
        });
    }

    private void abrirFormulario() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunoDAO.read()));

        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = alunoDAO.findPosition(posicao);

                Intent goToFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                goToFormularioActivity.putExtra("aluno", alunoEscolhido);
                startActivity(goToFormularioActivity);
            }
        });
    }
}
