package br.com.eraf.whichmovie;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class TemplateActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting_language:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;
            case R.id.about_app:
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.now_genre:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
