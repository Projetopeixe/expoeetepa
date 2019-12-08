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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String descricao = "O app 'Easy Math' foi desenvolvido pela equipe de desenvolvedores abaixo da turma Técnico em Informática 2018, turno matutino, da Escola de Educação Profissional e Tecnológica do Estado do Pará (EETEPA) do municipio de Oriximiná- PA, com intuito de propor a interação entre a informática e o processo de ensino e aprendizagem da Matemática, visando a contribuição de meios tecnológicos nesse processo, de forma que seja visto resultados nítidos na aprendizagem dos usuários. O app conta com dez fases, contendo cinco perguntas em cada.";
        View sobre = new AboutPage(this)
                .setImage(R.drawable.logo)
                .setDescription(descricao)
                .addGroup("Verifique os códigos")
                .addGitHub("Projetopeixe/expoeetepa", "Veja o código fonte")
                .addGroup("Desenvolvedores: ")
                .addGroup("Daniele Vieira Almeida")
                .addGroup("João Victor Santos Júnior")
                .addGroup("Maria Clara Silva dos Passos")
                .addGroup("Rita de Kássia Andrade de Oliveira")
                .addGroup("Samuel Oliveira de Amorim")
                .addGroup("Apoio: ")
                .addGroup("Luiz Victor Alves Veras")
                .addGroup("Luiz Eduardo Trindade da Silva")
                .create();
        setContentView(sobre);


    }
}
