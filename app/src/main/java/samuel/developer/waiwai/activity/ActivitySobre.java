package samuel.developer.waiwai.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import samuel.developer.waiwai.R;

public class ActivitySobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String descricao = "Wai Wai é um aplicativo que foi desenvolvido visando auxiliar no aprendizado da Língua Portuguesa por parte dos alunos indígenas da Universidade Federal do Oeste do Pará (UFOPA) que são pertencentes a tribo Wai-Wai.";
        View sobre = new AboutPage(this)
                .setImage(R.drawable.logomarca)
                .setDescription(descricao)
                .addEmail("raimundo.araujo@ufopa.edu.br", "E-mail para contato")
                .addGroup("Desenvolvedor do aplicativo: ")
                .addGroup("Samuel Oliveira de Amorim")
                .addGroup("Coordenador do projeto:")
                .addGroup("Prof. Esp. Raimundo Martins de Araújo Júnior")
                .create();
        setContentView(sobre);


    }
}
