package samuel.developer.projectoexpoeetepa_math.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.config.ConfiguracaoFirebase;
import samuel.developer.projectoexpoeetepa_math.helper.Base64Custom;
import samuel.developer.projectoexpoeetepa_math.helper.Permissao;
import samuel.developer.projectoexpoeetepa_math.helper.UsuarioFirebase;
import samuel.developer.projectoexpoeetepa_math.model.Usuario;

public class CadastroActivity extends AppCompatActivity {




    private String[] permissoesNecessarias = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private TextInputEditText campoNome, campoEmail, campoSenha, campoConfirSenha;
    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        Permissao.validarPermissoes(permissoesNecessarias, this, 1);
        carregarComponentes();


    }



    public void carregarComponentes(){
        campoNome = findViewById(R.id.campoNomeCadastro);
        campoEmail = findViewById(R.id.campoEmailCadastro);
        campoSenha = findViewById(R.id.campoSenhaCadastro);
        campoConfirSenha = findViewById(R.id.campoConfirmacaoSenha);


    }


    public void cadastrarUsuario(final Usuario usuario){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                    UsuarioFirebase.atualizarNomeUsuario(usuario.getNome());
                    finish();

                    try{

                        String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                        usuario.setId(identificadorUsuario);
                        usuario.salvar();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    String excecao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Insira uma senha mais forte";
                    }catch (FirebaseNetworkException e) {
                        excecao = "Sem conexão com a internet";
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Por favor, insira um e-mail válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Usuário já existe";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void validarCadastro(View view){
        String textnome = campoNome.getText().toString();
        String textemail = campoEmail.getText().toString();
        String textsenha = campoSenha.getText().toString();
        String textconfirmSenha = campoConfirSenha.getText().toString();

        if(!textnome.isEmpty()){
            if(!textemail.isEmpty()){
                if(!textsenha.isEmpty()){
                    if(!textconfirmSenha.isEmpty()){
                        if(textconfirmSenha.equals(textsenha)){

                            Usuario usuario = new Usuario();
                            usuario.setNome(textnome);
                            usuario.setSenha(textsenha);
                            usuario.setEmail(textemail);


                            cadastrarUsuario(usuario);
                        }else{
                            Toast.makeText(CadastroActivity.this, "Senhas não conferem", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CadastroActivity.this, "Preencha o campo de Confirmação", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CadastroActivity.this, "Preencha o campo Senha", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(CadastroActivity.this, "Preencha o campo E-mail", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CadastroActivity.this, "Preencha o campo Nome", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado: grantResults){

            if(permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas!");
        builder.setCancelable(false);
        builder.setMessage("Para realizar configurações de imagem e etc. no App é necessário aceitar as permissões");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
