package com.example.bharat.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   int x=0;                     // 0 is Green, 1 is Red and 2 is empty.
   int[] gameState={2,2,2,2,2,2,2,2,2};
   int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
   boolean finished=true;
   int tieCheck=0;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = (int) Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && finished) {
            gameState[tappedCounter] = x;
            counter.setTranslationY(-1000);

            if (x == 0) {

                counter.setImageResource(R.drawable.crystal1);
                x = 1;

            } else {

                counter.setImageResource(R.drawable.crystal2);
                x = 0;
            }

            counter.animate().translationYBy(1000).rotation(360).setDuration(400);

            for (int[] check : winningPositions) {

                if (gameState[check[0]] == gameState[check[1]] && gameState[check[1]] == gameState[check[2]] && gameState[check[0]] != 2) {
                    finished=false;
                    String message="";
                    if (x == 0) {
                        message = "Red Won!";
                    } else if (x == 1) {
                        message = "Green Won!";
                    }

                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    playAgainButton.setVisibility(view.VISIBLE);
                }
            }

            for(int j=0;j<9;j++){
                if(gameState[j]!=2) tieCheck=tieCheck+1;
            }

            if(tieCheck==9) {
                 tieCheck=0;
                 finished=false;
                 Toast.makeText(this, "It's a Tie", Toast.LENGTH_SHORT).show();
                 Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                 playAgainButton.setVisibility(view.VISIBLE);
             }else {
                tieCheck=0;
            }

        }

    }

    public void chalu(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(view.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount();i++){

            ImageView counter2= (ImageView) gridLayout.getChildAt(i);
            counter2.setImageDrawable(null);
        }

        for(int i=0; i<gameState.length; i++) {
            gameState[i]=2;
        }
        x=0;                     // 0 is Green, 1 is Red and 2 is empty.
        finished=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
