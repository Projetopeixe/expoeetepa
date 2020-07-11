package samuel.developer.waiwai.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import samuel.developer.waiwai.R;
import samuel.developer.waiwai.dao.BdDao;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Toast;

import java.util.Locale;

import samuel.developer.waiwai.datamodel.DataModelPalavra;

public class TraducaoPalavraDic extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private ImageView retornar;
    private ImageView imagemPalavra;
    private TextView nomeEmWaiWai;
    private TextView nomeEmPortugues;
    private TextView descricaoPalavra;
    private ImageView falarPalavra;
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traducao_palavra_dic);
        getSupportActionBar().hide();
        carregarComponentes();
        tts = new TextToSpeech(this, this);

        Bundle dados = getIntent().getExtras();
        if(dados!= null){
            if(dados.getByteArray(DataModelPalavra.getImagempalavra() ) == null){
                imagemPalavra.setImageResource(R.drawable.logomarca);
            }else {
                BdDao bdDao = new BdDao(this);
                Bitmap bitmap = bdDao.converteByteArrayToBitmap(dados.getByteArray(DataModelPalavra.getImagempalavra()));
                imagemPalavra.setImageBitmap(bitmap);
            }
            descricaoPalavra.setText(dados.getString(DataModelPalavra.getPergunta()));
            nomeEmWaiWai.setText(dados.getString(DataModelPalavra.getPalavra_waiwai()));
            nomeEmPortugues.setText(dados.getString(DataModelPalavra.getPalavra_portugues()));
        }else {

        }
        retornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityDicionario.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext()
                        , R.transition.fade_in, R.transition.fade_out);
                ActivityCompat.startActivity(TraducaoPalavraDic.this, i, activityOptionsCompat.toBundle());
                finish();
            }
        });
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            falarPalavra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    falarMeuTexto();
                }
            });
        }
    }

    @SuppressLint("NewApi")
    public void falarMeuTexto(){
        Log.i("Script", "Máximo: "+ TextToSpeech.getMaxSpeechInputLength());
        int codigo = tts.isLanguageAvailable(new Locale("pt", "BR"));
        if(codigo == TextToSpeech.LANG_COUNTRY_AVAILABLE){
            tts.setLanguage(new Locale("pt", "Br"));
        }else{
            //TODO...
        }
        tts.speak(nomeEmPortugues.getText().toString(), tts.QUEUE_ADD, null );
        tts.setPitch(1);
        tts.playSilence(1500, tts.QUEUE_ADD, null);
        tts.setSpeechRate(2);
        tts.speak(nomeEmWaiWai.getText().toString(), tts.QUEUE_ADD, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(getApplicationContext(), ActivityDicionario.class);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext()
                    , R.transition.fade_in, R.transition.fade_out);
            ActivityCompat.startActivity(TraducaoPalavraDic.this, i, activityOptionsCompat.toBundle());
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void carregarComponentes(){
        retornar = findViewById(R.id.btn_retornar_dic);
        nomeEmWaiWai = findViewById(R.id.nameEmWaiWai);
        nomeEmPortugues = findViewById(R.id.nameEmPortugues);
        imagemPalavra = findViewById(R.id.imagemPalavra);
        descricaoPalavra = findViewById(R.id.textDescricaoPalavra);
        falarPalavra = findViewById(R.id.executarAudioWord);
    }

}
