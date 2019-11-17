package samuel.developer.projectoexpoeetepa_math.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.config.ConfiguracaoFirebase;
import samuel.developer.projectoexpoeetepa_math.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private Button buttonLogar;
    private TextInputEditText campoEmail, campoSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        carregarComponentes();
        buttonLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarAutenticacao(buttonLogar);
            }
        });
    }

    public void carregarComponentes(){
        campoEmail = findViewById(R.id.editCampoEmailLogin);
        campoSenha = findViewById(R.id.editCampoSenhaLogin);
        buttonLogar = findViewById(R.id.buttonLogar);
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    }

    public void validarAutenticacao(View view){

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty()){
            if(!senha.isEmpty()){

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);

                logarUsuario(usuario);


            }else{
                Toast.makeText(LoginActivity.this, "Preencha o campo de Senha", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(LoginActivity.this, "Preencha o campo de E-mail", Toast.LENGTH_SHORT).show();
        }
    }

    public void logarUsuario(final Usuario usuario){

        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    abrirTelaPrincipal();
                    finish();
                }else {
                    String excecao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Usuário e senha não correspondem";
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não cadastrado";

                    }catch (Exception e){
                        excecao = "Erro ao logar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, ActivityPrincipal.class);
        startActivity(intent);
    }

    public void abrirTelaCadastro(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if(usuarioAtual != null){
            abrirTelaPrincipal();
            fecharLogin();
        }
    }

    public void fecharLogin(){
        finish();
    }

    /*public void deslogarUsuario(){
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if(usuarioAtual != null){
            autenticacao.signOut();
        }


    }*/

}
