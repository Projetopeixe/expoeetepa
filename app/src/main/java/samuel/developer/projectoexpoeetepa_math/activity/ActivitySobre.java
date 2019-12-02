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
        View sobre = new AboutPage(this)
                .setImage(R.drawable.logo)
                .create();

    }
}
