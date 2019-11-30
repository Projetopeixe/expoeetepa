package samuel.developer.projectoexpoeetepa_math.activity.fase1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.activity.ActivityPrincipal;

public class Q5F1 extends AppCompatActivity {

    private Button alternativaA;
    private Button alternativaB;
    private Button alternativaC;
    private Button alternativaD;
    private ProgressBar timeLine;
    private static final String CONCLUIDO = "Conclusao";
    private MediaPlayer mediaPlayerCerta, mediaPlayerErrada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q5_f1);
        carregarComponentes();
        carregamentoTempo();

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

    public void carregarComponentes(){
        alternativaA = findViewById(R.id.alternativaA);
        alternativaB = findViewById(R.id.alternativaB);
        alternativaC = findViewById(R.id.alternativaC);
        alternativaD = findViewById(R.id.alternativaD);
        timeLine = findViewById(R.id.progressBar2);
        mediaPlayerCerta = MediaPlayer.create(getApplicationContext(), R.raw.acertou);
        mediaPlayerErrada = MediaPlayer.create(getApplicationContext(), R.raw.erou);
    }

    public void questaoCerta(){
        executarSomCerta();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acertouu!");
        builder.setMessage("Parabéns! Resposta Correta!");
        builder.setPositiveButton("Próxima Questão", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), ActivityPrincipal.class);
                finish();
            }
        });
        builder.create();
        builder.show();
    }

    public void questaoErrada(){
        executarSomErrada();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Errou!");
        builder.setMessage("Que pena! Resposta Incorreta!");
        builder.setPositiveButton("Tentar Novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }
    public void carregamentoTempo(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i<= 120; i++) {
                            final int progresso = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timeLine.setProgress(progresso);
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException   e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }

    public void executarSomCerta(){
        if (mediaPlayerCerta != null){
            mediaPlayerCerta.start();
        }
    }
    public void executarSomErrada(){
        if(mediaPlayerErrada != null){
            mediaPlayerErrada.start();
        }
    }
}
