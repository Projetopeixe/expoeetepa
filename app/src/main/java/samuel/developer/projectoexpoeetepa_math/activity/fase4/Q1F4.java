package samuel.developer.projectoexpoeetepa_math.activity.fase4;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import samuel.developer.projectoexpoeetepa_math.R;


public class Q1F4 extends AppCompatActivity {

    private Button alternativaA;
    private Button alternativaB;
    private Button alternativaC;
    private Button alternativaD;
    private ProgressBar timeLine;
    private MediaPlayer mediaPlayerCerta, mediaPlayerErrada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1_f4);
        carregarComponente();
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


    private void carregarComponente () {
        timeLine = findViewById(R.id.progressBar2);
        alternativaA = findViewById(R.id.alternativaA);
        alternativaB = findViewById(R.id.alternativaB);
        alternativaC = findViewById(R.id.alternativaC);
        alternativaD = findViewById(R.id.alternativaD);
        mediaPlayerCerta = MediaPlayer.create(getApplicationContext(),R.raw.acertou);
        mediaPlayerErrada = MediaPlayer.create(getApplicationContext(), R.raw.erou);
    }

    public  void questaoCerta(){
        executarSomCerta();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acertou!");
        builder.setMessage("Parabéns! Você Acertou!");
        builder.setPositiveButton("Próxima Questão!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Q2F4.class);
                startActivity(intent);
                finish();
            }
        });
        builder.create();
        builder.show();

    }
    public void questaoErrada() {
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
                        for (int i = 0; i<= 120; i++){
                            final int progresso = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timeLine.setProgress(progresso);

                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (progresso == 120){
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
        builder.setTitle("Acabou seu tempo!");
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
}
