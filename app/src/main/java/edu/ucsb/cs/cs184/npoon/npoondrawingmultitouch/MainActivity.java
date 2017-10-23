package edu.ucsb.cs.cs184.npoon.npoondrawingmultitouch;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

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

        Button button1 = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);

        button1.setBackgroundColor(randColor());
        button2.setBackgroundColor(randColor());
        button3.setBackgroundColor(randColor());
        button4.setBackgroundColor(randColor());

        int button1Color1 = buttonClick(button1);
        int button1Color2 = buttonClick(button2);
        int button1Color3 = buttonClick(button3);
        int button1Color4 = buttonClick(button4);


        //ConstraintLayout myLayout = (ConstraintLayout) findViewById(R.id.myLayout);
        View surface = findViewById(R.id.surfaceView);
        surface.setBackgroundColor(Color.WHITE);
        surface.setOnTouchListener(new ConstraintLayout.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent m)
            {
                handleTouch();
                return true;
            }

        });
    }

    public void handleTouch()
    {
        Toast.makeText(MainActivity.this, "multiTouch clicked", Toast.LENGTH_LONG).show(); //DEBUG
    }

    public int randColor()
    {
        Random rand = new Random();
        int one = rand.nextInt(256);
        int two = rand.nextInt(256);
        int three = rand.nextInt(256);

        int color = Color.argb(255, one, two, three);

        return color;

    }

    public int buttonClick(Button b)
    {
        final Button click = b;
        int randomColor = randColor();
        click.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                click.setBackgroundColor(randColor());
            }
        });

        return randomColor;

    }


}
