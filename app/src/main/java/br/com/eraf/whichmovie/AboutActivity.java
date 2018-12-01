package br.com.eraf.whichmovie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class AboutActivity extends TemplateActivity {

    TextView creator1TextView;
    TextView creator2TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle(R.string.about_app);



        creator1TextView = findViewById(R.id.creator1TextView);
        creator1TextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/aline-silva-888332117/"));
                startActivity(browserIntent);
            }
        });
        creator2TextView = findViewById(R.id.creator2TextView);
        creator2TextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/erik-ferreira-28a56531/"));
                startActivity(browserIntent);
            }
        });




    }
}
