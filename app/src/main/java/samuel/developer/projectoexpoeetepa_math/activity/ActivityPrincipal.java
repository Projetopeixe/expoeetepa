package samuel.developer.projectoexpoeetepa_math.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.UnicodeSetSpanner;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;
import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.activity.fase1.Q1F1;
import samuel.developer.projectoexpoeetepa_math.activity.fase10.Q1F10;
import samuel.developer.projectoexpoeetepa_math.activity.fase2.Q1F2;
import samuel.developer.projectoexpoeetepa_math.activity.fase3.Q1F3;
import samuel.developer.projectoexpoeetepa_math.activity.fase4.Q1F4;
import samuel.developer.projectoexpoeetepa_math.activity.fase5.Q1F5;
import samuel.developer.projectoexpoeetepa_math.activity.fase6.Q1F6;
import samuel.developer.projectoexpoeetepa_math.activity.fase7.Q1F7;
import samuel.developer.projectoexpoeetepa_math.activity.fase8.Q1F8;
import samuel.developer.projectoexpoeetepa_math.activity.fase9.Q1F9;
import samuel.developer.projectoexpoeetepa_math.helper.ControllerFases;
import samuel.developer.projectoexpoeetepa_math.helper.UsuarioFirebase;

public class ActivityPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private ImageView btnF1, btnF2, btnF3, btnF4, btnF5, btnF6, btnF7, btnF8, btnF9, btnF10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Easy Math");

        carregarBotoesFases();


        //Creating local database (SQLite)
        try{
            SQLiteDatabase banco = openOrCreateDatabase("app", MODE_PRIVATE, null);
            //Create Table fases
            banco.execSQL("CREATE TABLE IF NOT EXISTS fases(" +
                    "fase1 INT(2), fase2 INT(2), fase3 INT(2), fase4 INT(2), fase5 INT(2), fase6 INT(2), fase7 INT(2), fase8 INT(2), fase9 INT(2), fase10 INT(2))");
            banco.execSQL("INSERT INTO fases(fase1, fase2, fase3, fase4, fase5, fase6, fase7, fase8, fase9, fase10) VALUES(1, 0, 0, 0, 0, 0, 0, 0, 0, 0)");
            
        }catch (Exception e){
            e.printStackTrace();
        }

        final ControllerFases controll = new ControllerFases();
        btnF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(controll.getFase1() == 1) {
                    Intent intent = new Intent(getApplicationContext(), Q1F1.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Fase Indisponível!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controll.getFase2() == 1) {
                    Intent intent = new Intent(getApplicationContext(), Q1F2.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 1!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controll.getFase3() == 1){
                    Intent intent = new Intent(getApplicationContext(), Q1F3.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 2!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controll.getFase4() == 1){
                    Intent intent = new Intent(getApplicationContext(), Q1F4.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 3!", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnF5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controll.getFase5() == 1){
                    Intent intent = new Intent(getApplicationContext(), Q1F5.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 4!", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnF6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controll.getFase6() == 1){
                    Intent intent = new Intent(getApplicationContext(), Q1F6.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 5!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnF7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controll.getFase7() == 1){
                    Intent intent = new Intent(getApplicationContext(), Q1F7.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 6!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnF8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controll.getFase8() == 1){
                    Intent intent = new Intent(getApplicationContext(), Q1F8.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 7!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnF9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controll.getFase9() == 1){
                    Intent intent = new Intent(getApplicationContext(), Q1F9.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 8!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnF10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controll.getFase10() == 1){
                    Intent intent = new Intent(getApplicationContext(), Q1F10.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Fase Indisponível! Vença a Fase 9!", Toast.LENGTH_LONG).show();
                }
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
            Intent intent = new Intent(ActivityPrincipal.this, ActivitySobre.class);
            startActivity(intent);


        }else if (id == R.id.emailDevelopers){
            enviarEmail();

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

    public void enviarEmail(){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"easymathoficial19@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App");
        email.putExtra(Intent.EXTRA_TEMPLATE, "Mensagem automática");

        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Escolha o App de e-mail:"));
    }
}
