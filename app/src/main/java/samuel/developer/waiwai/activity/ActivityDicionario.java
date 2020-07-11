package samuel.developer.waiwai.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import samuel.developer.waiwai.R;
import samuel.developer.waiwai.dao.DataSource;
import samuel.developer.waiwai.datamodel.DataModelPalavra;

public class ActivityDicionario extends AppCompatActivity {


    ArrayList<String> mPalavras = new ArrayList<>();
    ImageView btnVoltar;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicionario);
        getSupportActionBar().hide();
        carregarComponentes();


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityPrincipal.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext()
                        , R.transition.fade_in, R.transition.fade_out);
                ActivityCompat.startActivity(ActivityDicionario.this, i, activityOptionsCompat.toBundle());
                finish();
            }
        });

        final DataSource ds = new DataSource(this);
        mPalavras = ds.getPalavras(DataModelPalavra.getTabelaPalavras());
        String[] palavrasRec = new String[mPalavras.size()];
        mPalavras.toArray(palavrasRec);

        MyAdapter adapter = new MyAdapter(this, palavrasRec);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(), TraducaoPalavraDic.class);
                i.putExtra(DataModelPalavra.getPergunta(), ds.getDescricaoPalavraById(DataModelPalavra.getTabelaPalavras(), position+1));
                i.putExtra(DataModelPalavra.getPalavra_portugues(), ds.getPalavraPortuguesById(DataModelPalavra.getTabelaPalavras(), position+1));
                i.putExtra(DataModelPalavra.getPalavra_waiwai(), ds.getPalavraWaiById(DataModelPalavra.getTabelaPalavras(), position+1));
                i.putExtra(DataModelPalavra.getImagempalavra(), ds.getImagemPalavra(DataModelPalavra.getTabelaPalavras(), position+1));
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext()
                        , R.transition.fade_in, R.transition.fade_out);
                ActivityCompat.startActivity(ActivityDicionario.this, i, activityOptionsCompat.toBundle());
                finish();
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rPalavras[];

        MyAdapter(Context c, String Palavras[] ) {
            super(c, R.layout.activity_dicionario, R.id.namePalavra, Palavras);
            this.context = c;
            this.rPalavras = Palavras;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_palavras_dicionario, parent, false);
            TextView palavra = row.findViewById(R.id.namePalavra);

            palavra.setText(rPalavras[position]);
            return row;
        }
    }
    public void carregarComponentes(){
        btnVoltar = findViewById(R.id.btn_retornar_main);
        listView = findViewById(R.id.listViewPalavras);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(getApplicationContext(), ActivityPrincipal.class);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext()
                    , R.transition.fade_in, R.transition.fade_out);
            ActivityCompat.startActivity(ActivityDicionario.this, i, activityOptionsCompat.toBundle());
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
