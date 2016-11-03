package codelur.ciuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MasterClassesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_classes_list);

        String line;
        try{
           // getResources().openRawResource(R.raw.bachelorclasses);
            BufferedReader in =new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.masterclasses)));
            //getResources().getAssets().open("bachelorclasses.txt");

           // in = new BufferedReader(new FileReader("bachelorclasses.txt"));
            String textMasterList = new String();
            line = in.readLine();

            while(line != null)
            {
                textMasterList = textMasterList +"\n"+ line +"\n";
                line = in.readLine();

            }
            TextView al = (TextView) findViewById(R.id.textMClasses);
            al.setText(textMasterList);
        }
         catch (IOException e) {
        e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_master_classes_list, menu);
        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
