package edu.ucsb.cs.cs184.npoon.npoondrawingmultitouch;

import android.content.Intent;
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

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends ActionBarActivity{

    static String[] COLOR0 = {"#1b85b8", "#5a5255", "#559e83", "#ae5a41", "#c3cb71"};
    static String[] COLOR1 = {"#586f75", "#aedce7", "#2d2f35", "#cc6649", "#468499"};
    static String[] COLOR2 = {"#6bc4a7", "#4fa48c", "#de3242", "#b0394e", "#98191b"};
    static String[] COLOR3 = {"#ffc707", "#ff7f23", "#fe652b", "#fe4936", "#fe0e4c"};



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
            restartActivity();

        }
        return super.onOptionsItemSelected(item);
    }

    public void restartActivity()
    {
        // do your work Here
        Intent intent= new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(new drawView(this));

        String bColor0 = retrieveColor(0);
        String bColor1 = retrieveColor(1);
        String bColor2 = retrieveColor(2);
        String bColor3 = retrieveColor(3);

        final Button button0 = (Button)findViewById(R.id.button);
        final Button button1 = (Button)findViewById(R.id.button2);
        final Button button2 = (Button)findViewById(R.id.button3);
        final Button button3 = (Button)findViewById(R.id.button4);

        button0.setBackgroundColor(Color.parseColor(bColor0));
        button1.setBackgroundColor(Color.parseColor(bColor1));
        button2.setBackgroundColor(Color.parseColor(bColor2));
        button3.setBackgroundColor(Color.parseColor(bColor3));

        /*int button1Color1 = setButtonColor(button1);
        int button1Color2 = setButtonColor(button2);
        int button1Color3 = setButtonColor(button3);
        int button1Color4 = setButtonColor(button4);*/

        LinearLayout surface = (LinearLayout)findViewById(R.id.surface);
        surface.addView(new CanvasView(this, bColor0, bColor1, bColor2, bColor3));






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

    public String retrieveColor(int buttonID)
    {
        Random rand = new Random();

        int randomNumber = rand.nextInt(4);
        String color;

        switch (buttonID)
        {
            case 0:
                color = COLOR0[randomNumber];
                break;
            case 1:
                color = COLOR1[randomNumber];
                break;
            case 2:
                color = COLOR2[randomNumber];
                break;
            case 3:
                color = COLOR3[randomNumber];
                break;
            default:
                color = "#000000";
        }
        
        return color;

    }

    public void setButtonColor(Button b, String Color)
    {
        final Button click = b;
        click.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                click.setBackgroundColor(0x1b85b8);
            }
        });


    }


}
