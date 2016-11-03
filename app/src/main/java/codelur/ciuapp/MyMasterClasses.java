package codelur.ciuapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MyMasterClasses extends AppCompatActivity {

    private ListView lView;
    boolean[] selectedClasses = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_master_classes);
        ArrayList<String> myClasses = new ArrayList<String>();

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("SELECT YOUR CLASSES");

        String line;
        try{

            BufferedReader in =new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.masterclasses)));
            line = in.readLine();

            while(line != null)
            {
                myClasses.add(line);
                line = in.readLine();

            }
            //TextView al = (TextView) findViewById(R.id.textBClasses);
            //al.setText(textMasterList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //      this.writeToFile("");
        //default as boolean?
        selectedClasses = new boolean[myClasses.size()];

        lView = (ListView) findViewById(R.id.listViewMasterClasses);
        //	Set option as Multiple Choice. So that user can able to select more the one option from list

        lView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, myClasses));
        lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //WORKS
        String fromFile = this.readFromFile();
        if (!fromFile.equals("")) {
            String[] lines = fromFile.split(System.getProperty("line.separator"));

            for (int i = 0; i < lines.length; i++) {
                lView.setItemChecked(Integer.parseInt(lines[i]), true);
                selectedClasses[Integer.parseInt(lines[i])] = true;
            }
        }

        // lView.setItemChecked(1,true);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // change the checkbox state

                if (selectedClasses[position])
                    selectedClasses[position] = false;
                else
                    selectedClasses[position] = true;
            }
        });

        //  android.R.layout.simple_spinner_dropdown_item
        lView.getCheckedItemIds();
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ArrayList<String> myClasses = new ArrayList<String>();
        String line;
        try{

            BufferedReader in =new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.masterclasses)));
            line = in.readLine();

            while(line != null)
            {
                myClasses.add(line);
                line = in.readLine();

            }
            //TextView al = (TextView) findViewById(R.id.textBClasses);
            //al.setText(textMasterList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        lView = (ListView) findViewById(R.id.listViewMasterClasses);
        //	Set option as Multiple Choice. So that user can able to select more the one option from list

        lView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, myClasses));
        lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //WORKS
       // lView.setItemChecked(1, true);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // change the checkbox state
                //CheckedTextView checkedTextView = ((CheckedTextView) view);
                //selectedClasses = new int[myClasses.size()];
                //checkedTextView.setChecked(!checkedTextView.isChecked());
                // Toast.makeText(getBaseContext(), position, Toast.LENGTH_SHORT).show();
            }
        });

        //  android.R.layout.simple_spinner_dropdown_item
        lView.getCheckedItemIds();*/

    }

    //It will save the numbers,not the name of the classes
    //The numbers will correspond to the names in the file of classes and grades in the other file
    public void updateMasterClassesOnClick(View v) {
       /* lView = (ListView) findViewById(R.id.listViewMyClasses);
        long[] selected = lView.getCheckedItemIds();
        String classes = "";
        for (int i=0;i<selected.length;i++){
            classes = classes + selected[i] + System.getProperty("line.separator");
        }
        this.writeToFile(classes);*/
                String classes = "";
        for (int i=0;i<selectedClasses.length;i++){
            if (selectedClasses[i])
                classes = classes + i + System.getProperty("line.separator");
        }
        this.writeToFile(classes);
        Toast.makeText(getApplicationContext(), "The list of your classes have been updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("myMasterClasses.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("myMasterClasses.txt");

            if ( inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString + System.getProperty("line.separator"));
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());

        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

}
