package codelur.ciuapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.RLStudent);
        ShapeDrawable rectShapeDrawable = new ShapeDrawable(); // pre defined class

// get paint
        Paint paint = rectShapeDrawable.getPaint();

// set border color, stroke and stroke width
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8); // you can change the value of 5
        layout.setBackground(rectShapeDrawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
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

    public void buttonMyClassesOnClick(View v) {
        // do something when the button is clicked

        startActivity(new Intent(this, myClasses.class));
    }

    public void buttonMyMasterClassesOnClick(View v) {
        // do something when the button is clicked

        startActivity(new Intent(this, MyMasterClasses.class));
    }

    public void buttonMyGpaOnClick(View v) {
        // do something when the button is clicked

        startActivity(new Intent(this, yourGpa.class));
    }

    public void buttonSetScheduleOnClick(View v) {
        // do something when the button is clicked

        startActivity(new Intent(this, SetSchedule.class));
    }

    public void buttonYourScheduleOnClick(View v) {
        // do something when the button is clicked

        startActivity(new Intent(this, yourSchedule.class));
    }
}
