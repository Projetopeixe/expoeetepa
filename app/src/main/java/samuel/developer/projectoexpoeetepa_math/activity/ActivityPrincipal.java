package samuel.developer.projectoexpoeetepa_math.activity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import de.hdodenhof.circleimageview.CircleImageView;
import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.activity.fase1.Q1F1;
import samuel.developer.projectoexpoeetepa_math.helper.UsuarioFirebase;

public class ActivityPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private TextView nomedoUsuario, emailUsera;
    private CircleImageView imagemUser;
    private ImageView btnF1, btnF2, btnF3, btnF4, btnF5, btnF6, btnF7, btnF8, btnF9, btnF10;
    public static int pontuacao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        carregarBotoesFases();
        nomedoUsuario = findViewById(R.id.nomeDoUsuarioTela);
        imagemUser = findViewById(R.id.imagedoUsuarioNav);
        emailUsera = findViewById(R.id.emailUserNav);

        btnF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Q1F1.class);
                startActivity(intent);
                finish();
            }
        });

        btnF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnF5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnF6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnF7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnF8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnF9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnF10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void carregarBotoesFases(){
        btnF1 = findViewById(R.id.btnFase1);
        btnF2 = findViewById(R.id.btnFase2);
        btnF3 = findViewById(R.id.btnFase3);
        btnF4 = findViewById(R.id.btnFase4);
        btnF5 = findViewById(R.id.btnFase5);
        btnF6 = findViewById(R.id.btnFase6);
        btnF7 = findViewById(R.id.btnFase7);
        btnF8 = findViewById(R.id.btnFase8);
        btnF9 = findViewById(R.id.btnFase9);
        btnF10 = findViewById(R.id.btnFase10);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

     public void carregarDadosUser(){
         FirebaseUser usuarioatual = UsuarioFirebase.getUsuarioAtual();
         Uri url  = usuarioatual.getPhotoUrl();
         if (url != null){
             Glide.with(ActivityPrincipal.this).load(url).into(imagemUser);
         }else{
             imagemUser.setImageResource(R.drawable.padrao);
         }
         FirebaseUser usuario = UsuarioFirebase.getUsuarioAtual();

         nomedoUsuario.setText(usuario.getDisplayName());
     }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_configuracaoConta) {
            abriConfiguracoes();

        } else if (id == R.id.Deslogar) {
            deslogarUsuario();

            Intent intent = new Intent(ActivityPrincipal.this, LoginActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_sobre) {

        }else if (id == R.id.emailDevelopers){

        }else  if (id == R.id.sairdoApp){

            finish();
            LoginActivity login = new LoginActivity();
            login.fecharLogin();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void abriConfiguracoes(){
        Intent intent = new Intent(ActivityPrincipal.this, ConfiguracaoContaActivity.class);
        startActivity(intent);
    }

    public void deslogarUsuario(){
            auth.signOut();
    }

}
