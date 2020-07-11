package samuel.developer.waiwai.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.firebase.auth.FirebaseAuth;
import samuel.developer.waiwai.R;
import samuel.developer.waiwai.activity.fases.Fase1;

public class ActivityPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    Button fase1, fase2, fase3;
    private AccessTokenTracker accessTokenTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Wai Wai");
        carregarBotoesFases();

        fase1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPrincipal.this, Fase1.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext()
                        , R.transition.fade_in, R.transition.fade_out);
                ActivityCompat.startActivity(ActivityPrincipal.this, intent, activityOptionsCompat.toBundle());
            }
        });
        fase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fase 2", Toast.LENGTH_SHORT).show();
            }
        });
        fase3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fase 3", Toast.LENGTH_SHORT).show();
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
        fase1 = findViewById(R.id.btnFase1);
        fase2 = findViewById(R.id.btnFase2);
        fase3 = findViewById(R.id.btnFase3);
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
            accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    if(currentAccessToken == null){
                        auth.signOut();
                    }


                }
            };
            Intent intent = new Intent(ActivityPrincipal.this, LoginActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_sobre) {
            Intent intent = new Intent(ActivityPrincipal.this, ActivitySobre.class);
            startActivity(intent);

        }else if (id == R.id.menuDicionario){
            Intent intent = new Intent(getApplicationContext(), ActivityDicionario.class);
            startActivity(intent);
            finish();

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
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext()
                , R.transition.fade_in, R.transition.fade_out);
        ActivityCompat.startActivity(ActivityPrincipal.this, intent, activityOptionsCompat.toBundle());

    }

    public void deslogarUsuario(){

        auth.signOut();
    }

    public void enviarEmail(){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"raimundo.araujo@ufopa.edu.br"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App");
        email.putExtra(Intent.EXTRA_TEMPLATE, "Mensagem automática");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Escolha o App de e-mail:"));
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
