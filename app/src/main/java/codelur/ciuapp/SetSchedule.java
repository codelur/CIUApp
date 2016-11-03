package codelur.ciuapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SetSchedule extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    //load themfirst will avoid reloading them everytime the program is changed
    private ArrayList<String> bachClasses;
    private ArrayList<String> masterClasses;
    private Spinner  dayMasterSpinner;
    private Spinner  timeMasterSpinner;
    private Spinner  masterClassSpinner;
    private Spinner  dayBachSpinner;
    private Spinner  timeBachSpinner;
    private Spinner  bachClassSpinner;
    private RadioButton rbBachelor;
    private RadioButton rbMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule);

        RelativeLayout classesLayout = (RelativeLayout) findViewById(R.id.classesLayout);

        ShapeDrawable rectShapeDrawable = new ShapeDrawable(); // pre defined class
        // get paint
        Paint paint = rectShapeDrawable.getPaint();

// set border color, stroke and stroke width
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8); // you can change the value of 5
        classesLayout.setBackground(rectShapeDrawable);

        dayBachSpinner = (Spinner) findViewById(R.id.spinnerDayBach);
        timeBachSpinner = (Spinner) findViewById(R.id.spinnerTimeBach);
        bachClassSpinner = (Spinner) findViewById(R.id.spinnerClassBach);

        dayMasterSpinner = (Spinner) findViewById(R.id.spinnerDayMaster);
        timeMasterSpinner = (Spinner) findViewById(R.id.spinnerTimeMaster);
        masterClassSpinner = (Spinner) findViewById(R.id.spinnerClassMasters);

        //we will have a box selected from the beginning
       // bachBox.setSelected(true);

        bachClasses = this.setBachClasses(R.raw.bachelorclasses);
        masterClasses = this.setBachClasses(R.raw.masterclasses);
        ArrayAdapter<String> classesOfBachelors = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,bachClasses);
        ArrayAdapter<String> classesOfMaster = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,masterClasses);
        bachClassSpinner.setAdapter(classesOfBachelors);
        masterClassSpinner.setAdapter(classesOfMaster);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_schedule, menu);
        menu.getItem(0).setVisible(false);
        rbBachelor = (RadioButton) findViewById(R.id.radioButtonBachelor);
        rbBachelor.setOnCheckedChangeListener(this);
        rbMaster = (RadioButton) findViewById(R.id.radioButtonMaster);
        rbMaster.setOnCheckedChangeListener(this);
        return true;

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.radioButtonBachelor) {
                rbMaster.setChecked(false);
                rbBachelor.setChecked(true);
            }
            if (buttonView.getId() == R.id.radioButtonMaster) {
                rbBachelor.setChecked(false);
                rbMaster.setChecked(true);
            }
        }
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

    public void buttonCancelScheduleOnClick(View v){
        //Close the window
        finish();

    }

    public void buttonAcceptScheduleOnClick(View v){

        int day = 0;
        int time = 0;
        String classSelected="";
        rbBachelor = (RadioButton) findViewById(R.id.radioButtonBachelor);

        rbMaster = (RadioButton) findViewById(R.id.radioButtonMaster);
        //bachBox = (CheckBox) findViewById(R.id.checkBoxBach);
        //masterBox = (CheckBox) findViewById(R.id.checkBoxMaster);

    //    if (!bachBox.isSelected() && !masterBox.isSelected()){
      //      Toast.makeText(getApplicationContext(), "You have not selected if the class you want to add belongs to Bachelor's or Master's program", Toast.LENGTH_LONG).show();
        //}
        if (!rbBachelor.isChecked() && !rbMaster.isChecked()){
            Toast.makeText(getApplicationContext(), "You have not selected if the class you want to add belongs to Bachelor's or Master's program", Toast.LENGTH_LONG).show();
        }
        else {//not necessary but added as example
            if (rbBachelor.isChecked() && rbMaster.isChecked()) {
                Toast.makeText(getApplicationContext(), "You have  selected both Bachelor's or Master's program. Please select only one to add a class to your schedule", Toast.LENGTH_LONG).show();
            } else {

                if (rbBachelor.isChecked()) {
                    day = this.dayBachSpinner.getSelectedItemPosition();
                    time = this.timeBachSpinner.getSelectedItemPosition();
                    classSelected = this.bachClassSpinner.getSelectedItem().toString();
                }

                if (rbMaster.isChecked()) {
                    day = this.dayMasterSpinner.getSelectedItemPosition();
                    time = this.timeMasterSpinner.getSelectedItemPosition();
                    classSelected = this.masterClassSpinner.getSelectedItem().toString();
                }


                int lineToWrite = (day * 2) + time;
                //int lineToWrite = (day*3) + time +1;

                try {
                    InputStream inputStream = getResources().openRawResource(R.raw.schedule);

                    InputStreamReader inputreader = new InputStreamReader(inputStream);
                    BufferedReader buffreader = new BufferedReader(inputreader);

                    String output = "";
                    String fromFile = this.readFromFile();
                    String[] finalString = new String[10];
                    String[] lines = fromFile.split(System.getProperty("line.separator"));

                    for (int i = 0; i < 10; i++) {
                        if (i == lineToWrite) {
                            finalString[i] = classSelected;
                            output = output + classSelected + System.getProperty("line.separator");
                        } else {
                            if (i >= lines.length) {
                                finalString[i] = "";
                                output = output + System.getProperty("line.separator");
                            } else {
                                finalString[i] = lines[i];
                                output = output + lines[i] + System.getProperty("line.separator");
                            }
                        }

                    }

                    this.writeToFile(output);

                } catch (Exception e) {
                    String l = e.toString();
                    l = l + "";
                }
                //Close the window
                finish();
            }
        }
    }

    private ArrayList<String> setBachClasses(int id){

        ArrayList<String> bachClasses = new ArrayList<String> ();

        String line;
        try{
            BufferedReader in =new BufferedReader(new InputStreamReader(getResources().openRawResource(id)));
            String textMasterList = new String();
            line = in.readLine();

            while(line != null)
            {
                //textMasterList = textMasterList +"\n"+ line;
                bachClasses.add(line);
                line = in.readLine();

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return bachClasses;
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("yourschedule.txt", Context.MODE_PRIVATE));
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
            InputStream inputStream = openFileInput("yourschedule.txt");

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
