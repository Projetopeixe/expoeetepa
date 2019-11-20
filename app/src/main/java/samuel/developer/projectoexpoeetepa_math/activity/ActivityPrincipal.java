package samuel.developer.projectoexpoeetepa_math.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.security.keystore.UserPresenceUnavailableException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;
import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.helper.UsuarioFirebase;
import samuel.developer.projectoexpoeetepa_math.model.Usuario;

public class ActivityPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private TextView nomedoUsuario, emailUsera;
    private CircleImageView imagemUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");



        nomedoUsuario = findViewById(R.id.nomeDoUsuarioTela);
        imagemUser = findViewById(R.id.imagedoUsuarioNav);
        emailUsera = findViewById(R.id.emailUserNav);


        //carregarDadosUser();



        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


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

     /*@Override
   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

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
