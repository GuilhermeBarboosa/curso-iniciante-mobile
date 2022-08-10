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


    //CRIAR A ACTIVITY
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

    //CONFIGURA OS ALUNOS NO BOTAO DE ADICIONAR
    private void configuraNovoAluno() {
        FloatingActionButton criarAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        criarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormulario();
            }
        });
    }

    //MANDA PRA ACTIVITY DE FORMULARIO
    private void abrirFormulario() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    //MOSTRAR A LISTA DE ALUNOS
    private void configuraLista() {
        listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaAlunos);
        listenerAluno(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    //PEGA TODOS OS ALUNOS E MOSTRA NA LIST VIEW
    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunoDAO.read());

        listaDeAlunos.setAdapter(adapter);
    }

    //CLICK PARA EDITAR O ALUNO
    private void listenerAluno(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = alunoDAO.findPosition(posicao);
                abreFormularioEditaAluno(alunoEscolhido);
            }
        });
    }
    //ABRE A ACTIVITY DE FORMULARIO MAS MANDANDO TODOS OS DADOS DO ALUNO
    private void abreFormularioEditaAluno(Aluno alunoEscolhido) {
        Intent goToFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        goToFormularioActivity.putExtra("aluno", alunoEscolhido);
        startActivity(goToFormularioActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(alunoDAO.read());
    }

    //REMOVE O ALUNO QUANDO CLICADO NO BOTAO DE REMOVER
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

    //INFLADOR DE MENU QUANDO SEGURAR
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_alunos_menu, menu);
    }

    //REMOVE O ALUNO DO DAO E ATUALIZA O ADAPTER
    public void removeAluno(Aluno alunoEscolhido) {
        alunoDAO.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }












}
