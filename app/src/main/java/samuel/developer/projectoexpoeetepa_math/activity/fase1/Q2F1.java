package samuel.developer.projectoexpoeetepa_math.activity.fase1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import samuel.developer.projectoexpoeetepa_math.R;

public class Q2F1 extends AppCompatActivity {

    private Button alternativaA;
    private Button alternativaB;
    private Button alternativaC;
    private Button alternativaD;
    private ProgressBar timeLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2_f1);
        carregarComponentes();

        alternativaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questaoErrada();
            }
        });

        alternativaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questaoErrada();

            }
        });

        alternativaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questaoCerta();

            }
        });

        alternativaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questaoErrada();
            }
        });
    }

    public void carregarComponentes(){
        alternativaA = findViewById(R.id.alternativaA);
        alternativaB = findViewById(R.id.alternativaB);
        alternativaC = findViewById(R.id.alternativaC);
        alternativaD = findViewById(R.id.alternativaD);
        timeLine = findViewById(R.id.progressBar2);
    }

    public void questaoCerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acertouu!");
        builder.setMessage("Parabéns! Resposta Correta!");
        builder.setPositiveButton("Próxima Questão", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Q3F1.class);
                startActivity(intent);
                finish();
            }
        });
        builder.create();
        builder.show();
    }

    public void questaoErrada(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Errou!");
        builder.setMessage("Parabéns! Resposta Correta!");
        builder.setPositiveButton("Tentar Novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }

    public void tempoEsgotado(){}
}
