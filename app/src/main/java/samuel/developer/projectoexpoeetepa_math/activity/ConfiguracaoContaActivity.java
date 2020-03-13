package samuel.developer.projectoexpoeetepa_math.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.config.ConfiguracaoFirebase;
import samuel.developer.projectoexpoeetepa_math.helper.Base64Custom;
import samuel.developer.projectoexpoeetepa_math.helper.Permissao;
import samuel.developer.projectoexpoeetepa_math.helper.UsuarioFirebase;

public class ConfiguracaoContaActivity extends AppCompatActivity {

    private ImageButton imageButtonCamera;
    private CircleImageView fotoPerfil;
    private ImageButton imageButtonGaleria;
    private static final int SELECAO_CAMERA = 100;
    private static final int SELECAO_GALERIA = 200;
    private StorageReference storageReference;
    private String identificadorUsuario;
    private EditText editNome;
    private ImageView imageAtualizarNome;
    private TextView textoAguarde;

    private  String[] permissoesNecessarias = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_conta);
        getSupportActionBar().setTitle("Ajustes");

        //Validar Permissões
        Permissao.validarPermissoes(permissoesNecessarias, this, 1);
        carregarComponentes();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Recuperar dados do Usuario
        FirebaseUser usuarioatual = UsuarioFirebase.getUsuarioAtual();
        Uri url  = usuarioatual.getPhotoUrl();
        if (url != null){
            Glide.with(ConfiguracaoContaActivity.this).load(url).into(fotoPerfil);
        }else{
            fotoPerfil.setImageResource(R.drawable.padrao);
        }

        editNome.setText(usuarioatual.getDisplayName());

        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i, SELECAO_CAMERA);
                }

            }
        });

        imageButtonGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i, SELECAO_GALERIA);
                }
            }
        });

        imageAtualizarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString();
                boolean retorno = UsuarioFirebase.atualizarNomeUsuario(nome);
                if (retorno){
                    Toast.makeText(ConfiguracaoContaActivity.this, "Nome Alterado com Sucesso!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ConfiguracaoContaActivity.this, "Erro ao alterar nome de usuário!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Bitmap imagem = null;

            try{

                switch (requestCode){
                    case SELECAO_CAMERA:
                        imagem = (Bitmap)data.getExtras().get("data");
                        break;
                    case SELECAO_GALERIA:
                        Uri localImagemSelecionada = data.getData();
                        imagem =  MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                        break;
                }

                if (imagem != null){
                    fotoPerfil.setImageBitmap(imagem);

                    //Recuperar
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    byte[] dadosImagem = baos.toByteArray();


                    //Salvar
                    identificadorUsuario = UsuarioFirebase.getIdentificadorUsuario();
                    StorageReference imagemref = storageReference.child("imagens").child("perfil").child(identificadorUsuario + ".jpeg");

                    UploadTask uploadTask = imagemref.putBytes(dadosImagem);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ConfiguracaoContaActivity.this, "Erro ao fazer upload de imagem", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(ConfiguracaoContaActivity.this, "Sucesso ao fazer upload de imagem", Toast.LENGTH_SHORT).show();

                            Uri utl = taskSnapshot.getDownloadUrl();
                            atualizaFotoUsuario(utl);
                        }
                    });
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void atualizaFotoUsuario(Uri url){
        UsuarioFirebase.atualizarFotoUsuario(url);
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
        builder.setMessage("Para realizar configurações no App é necessário aceitar as permissões");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void carregarComponentes(){
        imageButtonCamera = findViewById(R.id.imagemButtonCamera);
        imageButtonGaleria = findViewById(R.id.imageButtonGaleria);
        fotoPerfil = findViewById(R.id.circleImageViewFotoPerfil);
        storageReference = ConfiguracaoFirebase.getFirebaseStorage();
        editNome = findViewById(R.id.editNomeConfi);
        imageAtualizarNome = findViewById(R.id.imageAtualizarNomeUsuario);
        textoAguarde = findViewById(R.id.textoAguarde);
    }

}
