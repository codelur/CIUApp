package codelur.ciuapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class yourSchedule extends AppCompatActivity {
    private TextView infoSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_schedule);

        infoSchedule = (TextView) findViewById(R.id.textViewSchedule);
        try {
            String schedule = this.readFromFile();
           InputStream inputStream = getResources().openRawResource(R.raw.schedule);
            InputStreamReader inputreader = new InputStreamReader(inputStream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String output = "";

            for (int i = 0; i < 15; i++) {
                String aux = buffreader.readLine();
                output = output + aux + "/n";
            }

            if (schedule.equals(""))
                infoSchedule.setText("No schedule has been created yet. Please go to section \"Create your schedule\" to add classes ");
            else
                infoSchedule.setText(schedule);

        } catch (IOException e) {
            String l = e.toString();
            l = l + "";
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
                //counter to write the days
                String[] weekdays = {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"};
                int i =0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    if (i % 2 == 0)
                        stringBuilder.append(System.getProperty("line.separator") + weekdays[i/2] + System.getProperty("line.separator") + "Afternoon: " + receiveString + System.getProperty("line.separator"));
                    else
                        stringBuilder.append("Evening: " + receiveString + System.getProperty("line.separator"));
                    i++;
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

    public void buttonDeleteScheduleOnClick(View v){
        this.writeToFile("");
        Toast.makeText(getApplicationContext(), "Your schedule has been deleted", Toast.LENGTH_SHORT).show();
        finish();

    }
}
