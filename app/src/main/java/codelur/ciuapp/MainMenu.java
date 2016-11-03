package codelur.ciuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.net.Uri;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

import static codelur.ciuapp.R.id.action_settings;


public class MainMenu extends AppCompatActivity {

   // Button buttonUniversity;
   private boolean isLightOn = false;
    //private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //buttonUniversity = (Button) findViewById(R.id.buttonUniversity);
        //buttonUniversity.setOnClickListener((View.OnClickListener) this);
        this.isLightOn = false;
        ImageView imgFb = (ImageView) findViewById(R.id.imageViewFb);
        imgFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse(getString(R.string.cuiFacebbokpage));
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        ImageView imgGMaps = (ImageView) findViewById(R.id.imageViewGoogleMapsLogo);
        imgGMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "https://goo.gl/maps/C7oLVCTua1u";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
        if (id == action_settings) {
            //startActivity(new Intent(this, Programs.class));
            turnFlashlight();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public void buttonUniversityOnClick(View v) {
    // do something when the button is clicked

        startActivity(new Intent(this, University.class));
    }

    public void buttonStudentOnClick(View v) {
        // do something when the button is clicked

        startActivity(new Intent(this, Student.class));
    }

    public void buttonContactUsOnClick(View v) {
        // do something when the button is clicked

        startActivity(new Intent(this, ContactUs.class));
    }

    public void buttonProgramsOnClick(View v) {
        // do something when the button is clicked

        startActivity(new Intent(this, Programs.class));
    }


    private void turnFlashlight(){
     //   startActivity(new Intent(this, MasterClassesList.class));
     /*   try {
            camera = Camera.open(); // this line will throw exception if camera is not in use.
        }
        catch (Exception e){
            // if exception is thrown, return your boolean value here...
        }
        final Parameters p = camera.getParameters();
        if (this.isLightOn){
            p.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
            camera.stopPreview();
            camera.startPreview();
            isLightOn=false;

        } else {
            p.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);

            isLightOn=true;
        }*/

    }



}
