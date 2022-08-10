package treinamento.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import treinamento.com.br.R;
import treinamento.com.br.dao.AlunoDAO;
import treinamento.com.br.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText nomeAluno;
    private EditText emailAluno;
    private EditText telefoneAluno;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno alunoRecebido;

    //CRIA A ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        setCampos();
        getDados();
    }

    //CRIA AS REFERENCIAS DO VIEW
    private void setCampos() {
        nomeAluno = findViewById(R.id.acitivity_formulario_aluno_nome);
        emailAluno = findViewById(R.id.acitivity_formulario_aluno_email);
        telefoneAluno = findViewById(R.id.acitivity_formulario_aluno_telefone);
    }

    //SE FOR MODIFICACAO E O ALUNO VIER POPULADO, PREENCHE OS DADOS
    //CASO SEJA NULL VAI APENAS CRIAR UM NOVO ALUNO
    private void getDados() {
        Intent dados = getIntent();
        if (dados.hasExtra("aluno")) {
            alunoRecebido = (Aluno) dados.getSerializableExtra("aluno");
            nomeAluno.setText(alunoRecebido.getNome());
            emailAluno.setText(alunoRecebido.getEmail());
            telefoneAluno.setText(alunoRecebido.getTelefone());
        } else {
            alunoRecebido = new Aluno();
        }
    }

    //INFLADOR DE MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //ESPERANDO CLICAR NO BOTAO SALVAR
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_salvar){
            finishFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    //FINALIZA O FORMULARIO E SE O ALUNO EXISTIR ELE SO FAZ O EDIT CASO NAO ELE INSERE NO DAO
    private void finishFormulario() {
        createAluno();
        if (alunoRecebido.isExist()) {
            alunoDAO.editaAluno(alunoRecebido);
        } else {
            alunoDAO.create(alunoRecebido);
        }
        finish();
    }

    //POPULA O ALUNO
    private void createAluno() {
        String nome = nomeAluno.getText().toString();
        String email = emailAluno.getText().toString();
        String telefone = telefoneAluno.getText().toString();

        alunoRecebido.setNome(nome);
        alunoRecebido.setTelefone(telefone);
        alunoRecebido.setEmail(email);
    }
}