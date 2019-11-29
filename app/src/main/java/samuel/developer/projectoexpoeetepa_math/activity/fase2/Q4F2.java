package samuel.developer.projectoexpoeetepa_math.activity.fase2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.activity.fase1.Q2F1;

public class Q4F2 extends AppCompatActivity {

    private Button alternativaA;
    private Button alternativaB;
    private Button alternativaC;
    private Button alternativaD;
    private ProgressBar timeLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q4_f2);
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
                questaoErrada();

            }
        });

        alternativaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questaoCerta();
            }
        });
    }
    private void carregarComponentes(){
        timeLine = findViewById(R.id.progressBar2);
        alternativaA = findViewById(R.id.alternativaA);
        alternativaB = findViewById(R.id.alternativaB);
        alternativaC = findViewById(R.id.alternativaC);
        alternativaD = findViewById(R.id.alternativaD);
    }

    public void questaoCerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acertouu!");
        builder.setMessage("Parabéns! Resposta Correta!");
        builder.setPositiveButton("Próxima Questão", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Q5F2.class);
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
}
