package br.com.eraf.whichmovie;

import android.os.Bundle;

public class AboutActivity extends TemplateActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle(R.string.about_app);
    }
}
