package samuel.developer.waiwai.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import samuel.developer.waiwai.R;

public class FinishGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
