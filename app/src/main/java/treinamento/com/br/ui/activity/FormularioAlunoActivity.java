package treinamento.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        setCampos();
        configBotaoSalvar();

        Intent dados = getIntent();
        Aluno alunoRecebido = (Aluno) dados.getSerializableExtra("aluno");
            nomeAluno.setText(alunoRecebido.getNome());
            emailAluno.setText(alunoRecebido.getEmail());
            telefoneAluno.setText(alunoRecebido.getTelefone());
    }

    private void setCampos() {
        nomeAluno = findViewById(R.id.acitivity_formulario_aluno_nome);
        emailAluno = findViewById(R.id.acitivity_formulario_aluno_email);
        telefoneAluno = findViewById(R.id.acitivity_formulario_aluno_telefone);
    }

    private void configBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.acitivity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno alunoCriado = createAluno();
                saveAluno(alunoCriado);
            }
        });
    }

    @NonNull
    private Aluno createAluno() {
        String nome = nomeAluno.getText().toString();
        String email = emailAluno.getText().toString();
        String telefone = telefoneAluno.getText().toString();
        Aluno alunoCriado = new Aluno(nome, email, telefone);
        return alunoCriado;
    }

    private void saveAluno(Aluno alunoCriado) {
        alunoDAO.create(alunoCriado);
        finish();
    }

}