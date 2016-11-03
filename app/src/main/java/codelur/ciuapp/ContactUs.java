package codelur.ciuapp;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.RLInfo);
        ShapeDrawable rectShapeDrawable = new ShapeDrawable(); // pre defined class

// get paint
        Paint paint = rectShapeDrawable.getPaint();

// set border color, stroke and stroke width
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8); // you can change the value of 5
        layout.setBackground(rectShapeDrawable);
      //  layout.setBackgroundColor(11270794);
       // Menu item= R.menu.menu_contact_us;
        //item.setVisible(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_us, menu);
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
