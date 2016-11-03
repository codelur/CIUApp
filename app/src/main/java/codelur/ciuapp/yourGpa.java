package codelur.ciuapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class yourGpa extends AppCompatActivity {

    ArrayList<Item> items;
    private ListView classesGPA=null;
    TextView gradeTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_gpa);
        gradeTV = (TextView) findViewById(R.id.textViewGrade);
        classesGPA = (ListView) findViewById(R.id.listViewClasses);

        MyAdapter adapter = new MyAdapter(this, generateData());

        // 2. Get ListView from activity_main.xml
        //classesGPA = (ListView) findViewById(R.id.listViewClasses);
        //Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
        // 3. setListAdapter
        classesGPA.setAdapter(adapter);

    }

    private ArrayList<Item> generateData() {
        items = new ArrayList<Item>();
        // items.add(new Item("Item 1", "First Item on the list", 0));
        //items.add(new Item("Item 2", "Second Item on the list",0));
        //items.add(new Item("Item 3", "Third Item on the list", 2));

        String fromFileBach = this.readFromFileBach("myBachClasses.txt");
        String[] linesBach = fromFileBach.split(System.getProperty("line.separator"));

        //String h = getResources().getResourceName((R.raw.masterclasses));
        //String fromFileBachList = this.readFromFileBach("../../res/raw/masterclasses.txt");
        ArrayList<String> myClasses = new ArrayList<String>();
        String line;
        try{

            BufferedReader in =new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.bachelorclasses)));
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
        //String[] linesBachList = fromFileBachList.split(System.getProperty("line.separator"));

        for(int i=0;i<linesBach.length;i++) {
            if(!linesBach[i].equals(""))
                items.add(new Item("BACHELOR CLASS ", myClasses.get(Integer.parseInt(linesBach[i])), 0));
        }

        String fromFileMast = this.readFromFileBach("myMasterClasses.txt");
        String[] linesMast = fromFileMast.split(System.getProperty("line.separator"));

        ArrayList<String> myMasterClasses = new ArrayList<String>();
        String lineMaster;
        try{

            BufferedReader inMaster =new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.masterclasses)));
            lineMaster = inMaster.readLine();

            while(lineMaster != null)
            {
                myMasterClasses.add(lineMaster);
                lineMaster = inMaster.readLine();

            }
            //TextView al = (TextView) findViewById(R.id.textBClasses);
            //al.setText(textMasterList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<linesMast.length;i++) {
            if(!linesMast[i].equals(""))
                items.add(new Item("MASTER CLASS ", myMasterClasses.get(Integer.parseInt(linesMast[i])) , 0));
        }

        if(items.size()==0) {
            gradeTV.setText("No classes have been added yet. Please go to section \"My Bachelor Classes and My Master Classes\" to add classes ");
            this.classesGPA.setVisibility(View.GONE);
        }

        return items;
    }

    public void buttonSaveGradesOnClick(View v) {
        // do something when the button is clicked
        String message = "There are no classes to calculate your GPA";

        ListView classesGPA2 = (ListView) findViewById(R.id.listViewClasses);
       // Item c = (Item) classesGPA2.getItemAtPosition(1);
       // int pos = c.getGrade();

        if (items.size()>0) {
            float gpa = 0;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getGrade() == 0)
                    gpa+=4;
                if (items.get(i).getGrade() == 1)
                    gpa+=3.7;
                if (items.get(i).getGrade() == 2)
                    gpa+=3.3;
                if (items.get(i).getGrade() == 3)
                    gpa+=3;
                if (items.get(i).getGrade() == 4)
                    gpa+=2.7;
                if (items.get(i).getGrade() == 5)
                    gpa+=2.3;
                if (items.get(i).getGrade() == 6)
                    gpa+=2;
                if (items.get(i).getGrade() == 7)
                    gpa+=1.7;
                if (items.get(i).getGrade() == 8)
                    gpa+=1.3;
                if (items.get(i).getGrade() == 9)
                    gpa+=1;
                else
                    gpa+=0;
            }

            float gpaAvg = gpa / items.size();
            message = "This is your GPA: "+ String.valueOf(new DecimalFormat("##.##").format(gpaAvg));
        }

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        /*builder1.setNegativeButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private String readMyclasses(String fileName){

        String ret = "";

        try {
            InputStream inputStream = openFileInput(fileName);

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

    private String readFromFileBach(String fileName) {

        String ret = this.readMyclasses(fileName);

        return ret;
    }
}
