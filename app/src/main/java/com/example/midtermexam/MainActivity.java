package com.example.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean cells [][];
    public boolean win = false;
    Button returnButton;
    ArrayList <Button> buttons = new ArrayList<>();
    ArrayList <String> colors = new ArrayList<>();

    public MainActivity(){
        cells = new boolean[3][3];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        returnButton = (Button)findViewById(R.id.btnReturn);
        Button winButton = (Button) findViewById(R.id.btnWin);
        buttons.add( (Button) findViewById(R.id.btn1));
        buttons.add( (Button) findViewById(R.id.btn2));
        buttons.add( (Button) findViewById(R.id.btn3));
        buttons.add( (Button) findViewById(R.id.btn4));
        buttons.add( (Button) findViewById(R.id.btn5));
        buttons.add( (Button) findViewById(R.id.btn6));
        buttons.add( (Button) findViewById(R.id.btn7));
        buttons.add( (Button) findViewById(R.id.btn8));
        buttons.add( (Button) findViewById(R.id.btn9));

        randomize(buttons, colors);

        int k = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final int currentK = k;

                buttons.get(k).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(win == true)return;
                        randomizeNeighbors(buttons, currentK);
                        win = isWin(colors);
                    }
                });
                k++;
            }
        }

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                win = false;
                randomize(buttons, colors);
            }
        });

        winButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int k = 0;
                for(Button b : buttons){
                    b.setBackgroundColor(Color.GREEN);
                    colors.set(k, "GREEN");
                    k++;
                }
                win = isWin(colors);
            }
        });
    }

    public boolean isWin(ArrayList<String> colors){
        String color = colors.get(0);
        for(int i = 1; i < 9; i++){
            if(colors.get(i) != color){
                return false;
            }
        }
        Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        return true;
    }


    public void randomize(ArrayList<Button> buttons, ArrayList<String> colors){
        Random random = new Random();
        colors.removeAll(colors);
        for(Button b : buttons){
            int color = random.nextInt(3) + 1;
            if(color == 1){
                b.setBackgroundColor(Color.GREEN);
                colors.add("GREEN");
            }else if(color == 2){
                b.setBackgroundColor(Color.BLUE);
                colors.add("BLUE");
            }else{
                b.setBackgroundColor(Color.RED);
                colors.add("RED");
            }
        }
    }

    public void randomizeNeighbors(ArrayList <Button> buttons, int k){
        switch (k){
            case 0:
                randomCellColor(buttons.get(1), 1);
                randomCellColor(buttons.get(3), 3);
                break;
            case 1:
                randomCellColor(buttons.get(0), 0);
                randomCellColor(buttons.get(2), 2);
                randomCellColor(buttons.get(4), 4);
                break;
            case 2:
                randomCellColor(buttons.get(1), 1);
                randomCellColor(buttons.get(5), 5);
                break;
            case 3:
                randomCellColor(buttons.get(0), 0);
                randomCellColor(buttons.get(4), 4);
                randomCellColor(buttons.get(6), 6);
                break;
            case 4:
                randomCellColor(buttons.get(1), 1);
                randomCellColor(buttons.get(3), 3);
                randomCellColor(buttons.get(5), 5);
                randomCellColor(buttons.get(7), 7);
                break;
            case 5:
                randomCellColor(buttons.get(2), 2);
                randomCellColor(buttons.get(4), 4);
                randomCellColor(buttons.get(8), 8);
                break;
            case 6:
                randomCellColor(buttons.get(3), 3);
                randomCellColor(buttons.get(7), 7);
                break;
            case 7:
                randomCellColor(buttons.get(4), 4);
                randomCellColor(buttons.get(6), 6);
                randomCellColor(buttons.get(8), 8);
                break;
            case 8:
                randomCellColor(buttons.get(5), 5);
                randomCellColor(buttons.get(7), 7);
                break;
        }
    }
    public void randomCellColor(Button b, int k){
        String color = colors.get(k);
        switch (color){
            case "RED":
                b.setBackgroundColor(Color.GREEN);
                colors.set(k, "GREEN");
                break;
            case "GREEN":
                b.setBackgroundColor(Color.BLUE);
                colors.set(k, "BLUE");
                break;
            case "BLUE":
                b.setBackgroundColor(Color.RED);
                colors.set(k, "RED");
                break;
        }
    }
}