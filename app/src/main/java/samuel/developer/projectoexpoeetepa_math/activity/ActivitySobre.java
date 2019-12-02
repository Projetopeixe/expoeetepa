package samuel.developer.projectoexpoeetepa_math.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import samuel.developer.projectoexpoeetepa_math.R;

public class ActivitySobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sobre);

        View sobre = new AboutPage(this)
                .setImage(R.drawable.logo)
                .setDescription("O app Easy Math")
                .addGroup("Verifique os códigos")
                .addGitHub("Projetopeixe/expoeetepa", "Veja o código fonte")
                .addGroup("Desenvolvedores: ")
                .addGroup("Daniele Vieira Almeida")
                .addGroup("João Victor Santos Júnior")
                .addGroup("Maria Clara Silva dos Passos")
                .addGroup("Rita de Kássia Andrade de Oliveira")
                .addGroup("Samuel Oliveira de Amorim")
                .addGroup("Apoio: ")
                .addGroup("Luiz Victor")
                .create();
        setContentView(sobre);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
