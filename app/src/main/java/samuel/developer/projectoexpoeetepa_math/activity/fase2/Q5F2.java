package samuel.developer.projectoexpoeetepa_math.activity.fase2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

// olha direitinho

import samuel.developer.projectoexpoeetepa_math.R;
import samuel.developer.projectoexpoeetepa_math.activity.ActivityPrincipal;
import samuel.developer.projectoexpoeetepa_math.activity.fase1.Q2F1;

public class Q5F2 extends AppCompatActivity {

    private Button alternativaA;
    private Button alternativaB;
    private Button alternativaC;
    private Button alternativaD;
    private ProgressBar timeLine;
    private MediaPlayer mediaPlayerCerta, mediaPlayerErrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q5_f2);
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
        mediaPlayerCerta = MediaPlayer.create(getApplicationContext(), R.raw.acertou);
        mediaPlayerErrada = MediaPlayer.create(getApplicationContext(), R.raw.erou);
    }

    public void questaoCerta(){
        executarSomCerta();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acertouu!");
        builder.setCancelable(false);
        builder.setMessage("Parabéns! Resposta Correta!");
        builder.setPositiveButton("Finalizar Fase", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               desbloquearFase();
               faseConcluida();

            }
        });
        builder.create();
        builder.show();
    }

    public void questaoErrada(){
        executarSomErrada();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Errou!");
        builder.setCancelable(false);
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
                            if(progresso == 120){
                                tempoEsgotado();
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

    public void tempoEsgotado(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acabou o seu tempo!");
        builder.setCancelable(false);
        builder.setMessage("Para cada questão dessa fase há 2 min para ser respondida. Você demorou demais!\n");
        builder.setPositiveButton("Voltar ao menu principal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }

    public void desbloquearFase(){
        SQLiteDatabase banco = openOrCreateDatabase("app", MODE_PRIVATE, null);
        banco.execSQL("UPDATE fases SET fase3 = 1");
    }

    public void faseConcluida(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fase Concluida!");
        builder.setCancelable(false);
        builder.setMessage("Parabéns! Você concluiu a segunda fase e desbloqueou a terceira!");
        builder.setPositiveButton("Voltar ao menu principal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }
}
