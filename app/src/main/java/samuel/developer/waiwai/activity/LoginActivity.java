package samuel.developer.waiwai.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import samuel.developer.waiwai.R;
import samuel.developer.waiwai.config.ConfiguracaoFirebase;
import samuel.developer.waiwai.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager mcallbackManager;
    private LoginButton loginButtonFace;
    private FirebaseAuth autenticacao;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button buttonLogar;
    private AccessTokenTracker accessTokenTracker;
    private TextInputEditText campoEmail, campoSenha;
    private static final String TAG = "FacebookAuthetication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        FacebookSdk.sdkInitialize(getApplicationContext());

        loginButtonFace = findViewById(R.id.login_button_face);

        loginButtonFace.setReadPermissions("email", "public_profile");
        mcallbackManager = CallbackManager.Factory.create();
        loginButtonFace.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSucess " + loginResult);
                handlerFacebookToke(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError " + error);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken == null){
                    autenticacao.signOut();
                }


            }
        };
        carregarComponentes();
        buttonLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarAutenticacao(buttonLogar);
            }
        });
    }

    public void handlerFacebookToke(AccessToken token){
        Log.d(TAG, "handleFacebookToken " + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        autenticacao.signInWithCredential(credential).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, " SIGN IN WITH CREDENTIAL: SUCESSFUL");
                            FirebaseUser user = autenticacao.getCurrentUser();
                            abrirTelaPrincipal();
                            finish();
                        }else{

                            Log.d(TAG, " SIGN IN WITH CREDENTIAL: FAILURE " + task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
        Toast.makeText(getApplicationContext(), "Logando...", Toast.LENGTH_SHORT).show();
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
                    }catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuário não cadastrado";
                    }catch (FirebaseNetworkException e){
                        excecao = "Sem conexão com a internet";
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
        fecharLogin();
    }

    public void abrirTelaCadastro(View view){
        Intent i = new Intent(getApplicationContext(), CadastroActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext()
                , R.transition.fade_in, R.transition.fade_out);
        ActivityCompat.startActivity(LoginActivity.this, i, activityOptionsCompat.toBundle());
        finish();
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


    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            autenticacao.removeAuthStateListener(authStateListener);
        }
    }

    public void fecharLogin(){
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            checkExit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void checkExit()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
