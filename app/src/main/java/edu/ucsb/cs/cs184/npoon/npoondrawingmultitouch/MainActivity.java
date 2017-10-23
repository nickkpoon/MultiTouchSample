package edu.ucsb.cs.cs184.npoon.npoondrawingmultitouch;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //TODO: handle clicks on the menu items
        if(item.getItemId() == R.id.clear)
        {
            Toast.makeText(MainActivity.this, "CLEAR CLICKED", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout myLayout = (ConstraintLayout) findViewById(R.id.myLayout);

        //Bitmap b = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
        //Canvas c = new Canvas (b);

        myLayout.setOnTouchListener(new ConstraintLayout.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent m)
            {
                handleTouch();
                return true;
            }

        });
    }

    public void handleTouch()
    {
        Toast.makeText(MainActivity.this, "multiTouch clicked", Toast.LENGTH_LONG).show();
    }


}
