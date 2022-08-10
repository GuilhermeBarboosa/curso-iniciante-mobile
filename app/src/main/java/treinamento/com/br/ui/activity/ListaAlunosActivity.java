package treinamento.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import treinamento.com.br.R;
import treinamento.com.br.dao.AlunoDAO;
import treinamento.com.br.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ListView listaAlunos;
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);
        setContentView(R.layout.activity_lista_alunos);

        alunoDAO.create(new Aluno("Gui", "gui@gmail.com", "34 9999999"));
        alunoDAO.create(new Aluno("Barbosa", "barbosa@gmail.com", "34 9999999"));

        configuraNovoAluno();
        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(alunoDAO.read());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            removeAluno(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }


    public void removeAluno(Aluno alunoEscolhido) {
        alunoDAO.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }


    private void configuraLista() {
        listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaAlunos);
        listenerAluno(listaAlunos);
        registerForContextMenu(listaAlunos);
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

    private void listenerAluno(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = alunoDAO.findPosition(posicao);
                abreFormularioEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioEditaAluno(Aluno alunoEscolhido) {
        Intent goToFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        goToFormularioActivity.putExtra("aluno", alunoEscolhido);
        startActivity(goToFormularioActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunoDAO.read());

        listaDeAlunos.setAdapter(adapter);
    }
}
