package edu.ucsb.cs.cs184.npoon.npoondrawingmultitouch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends ActionBarActivity{


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
        //setContentView(new drawView(this));

        final Button button1 = (Button)findViewById(R.id.button);
        final Button button2 = (Button)findViewById(R.id.button2);
        final Button button3 = (Button)findViewById(R.id.button3);
        final Button button4 = (Button)findViewById(R.id.button4);

        button1.setBackgroundColor(randColor());
        button2.setBackgroundColor(randColor());
        button3.setBackgroundColor(randColor());
        button4.setBackgroundColor(randColor());

        int button1Color1 = buttonClick(button1);
        int button1Color2 = buttonClick(button2);
        int button1Color3 = buttonClick(button3);
        int button1Color4 = buttonClick(button4);

        LinearLayout surface = (LinearLayout)findViewById(R.id.surface);
        surface.addView(new CanvasView(this));
        /*surface.setBackgroundColor(Color.WHITE);
        surface.setOnTouchListener(new LinearLayout.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent m)
            {
                //Toast.makeText(MainActivity.this, "TOUCHED!", Toast.LENGTH_LONG).show(); //DEBUG
                handleTouch(m, button1, button2, button3, button4);
                return true;
            }

        });*/

    }

    /*public void handleTouch(MotionEvent m, Button one, Button two, Button three, Button four)
    {
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++)
        {
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            //int id = m.getActionMasked();
            int action = m.getActionMasked();
            int id = m.getActionIndex();

            if (id == 0)
            {
                one.setText("ONE");

            }
            else if (id == 1)
            {
                two.setText("ONE");

            }
            else if (id == 2)
            {
                three.setText("ONE");

            }
            else if (id == 3)
                four.setText("THREE");
            else
            {
                Toast.makeText(MainActivity.this, "MORE THAN FOUR CLICKED", Toast.LENGTH_LONG).show();

            }
            /*switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    actionString = "down";
                    break;
                case MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "PNTR DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "PNTR UP";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }


        }
    }*/

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
