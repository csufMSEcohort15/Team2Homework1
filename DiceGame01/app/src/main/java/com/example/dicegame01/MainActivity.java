package com.example.dicegame01;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    // roll to get the result vaule
    TextView rollResult1;

    // roll to get the result vaule
    TextView rollResult2;

    // btn of the game
    Button rollButton;
    Button statsButton;

    // random number generator
    Random rand;

    // die1 and die2 are the computer generator
    int die1;
    int die2;
    int computerScore;

    // die3 is be long to player, and computer will genrated
    int die3;
    // player's number will create here
    int die4;

    int playerScore;
    DiceNumberSelectorDialog diceValueSelector;
    StatsDialog statisticsViewer;
    ContinueGameDialog continueGameQuery;


    //ArrayList to hold all dice values
    ArrayList<Integer> dice;

    ArrayList<Stats> statsList;

    //ArrayList to hold all the dice images
    ArrayList<ImageView> diceImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // the two results
        rollResult1 = (TextView) findViewById(R.id.rollResult1);
        rollResult2 = (TextView) findViewById(R.id.rollResult2);

        // play button
        rollButton = (Button) findViewById(R.id.rollButton);
        statsButton = (Button) findViewById(R.id.statsButton);

        // Init the random number
        rand = new Random();

        // dice array
        dice = new ArrayList<Integer>();
        statsList = new ArrayList<Stats>();

        diceValueSelector = new DiceNumberSelectorDialog();
        statisticsViewer = new StatsDialog();
        continueGameQuery = new ContinueGameDialog();

        // image array
        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);
        ImageView die4Image = (ImageView) findViewById(R.id.die4Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);
        diceImageViews.add(die4Image);
    }

    @Override
    public void onValueChange(NumberPicker picker, int val1, int val2) {
        die4 = picker.getValue();
        dice.add(die4);
        try
        {
            String imageName = "die_" + dice.get(3) + ".jpg";
            InputStream stream = getAssets().open(imageName);
            Drawable d = Drawable.createFromStream(stream,null);
            diceImageViews.get(3).setImageDrawable(d);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String msg3 = "The player dice is " + die3 + "." + " Player dice 2 is " + die4;
        rollResult2.setText(msg3);
        computerScore = CalcModSum(die1, die2);
        playerScore = CalcModSum(die3, die4);
        Stats temp = new Stats();
        temp.setComputerScore(computerScore);
        temp.setPlayerScore(playerScore);
        if(computerScore > playerScore) {
            temp.setRoundWinner("Computer");
            Toast.makeText(this, "Computer Score: " + computerScore + " Player Score: " + playerScore + " Computer Wins.", Toast.LENGTH_SHORT).show();
        }
        else if(playerScore > computerScore) {
            temp.setRoundWinner("Player");
            Toast.makeText(this, "Computer Score: " + computerScore + " Player Score: " + playerScore + " Player Wins.", Toast.LENGTH_SHORT).show();
        }
        else {
            temp.setRoundWinner("Tie");
            Toast.makeText(this, "Computer Score: " + computerScore + " Player Score: " + playerScore + " Game Tie.", Toast.LENGTH_SHORT).show();
        }
        statsList.add(temp);
        continueGameQuery.setContinueGameDialogResult(temp);
        continueGameQuery.show(getSupportFragmentManager(), "Continue Game?");
    }

    public void getStats(View v)
    {
        statisticsViewer.setRoundResults(statsList);
        statisticsViewer.show(getSupportFragmentManager(), "Stats Viewer");
    }


    public void rollDice(View v) {



        // computer roll dices
        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;

        //dice values into a ArrayList
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        // link the images from assets folder
        for (int dieofSet = 0; dieofSet < 3; dieofSet++){
            String imageName = "die_" + dice.get(dieofSet) + ".jpg";

            try{
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(dieofSet).setImageDrawable(d);

            } catch (IOException e){
                e.printStackTrace();
            }
        }

        //result the message
        String msg1 = "The computer first dice is " + die1 + ", the second dice is " + die2 + ".";

        String msg2 = "The player dice is " + die3 + "." + " Player need to select number...";
        diceValueSelector.setFirstDieRolled(die3);
        //player need to select number.................

        //display the result message
        rollResult1.setText(msg1);
        rollResult2.setText(msg2);
        diceValueSelector.setOnValueChangeListener(this);
        diceValueSelector.show(getSupportFragmentManager(), "Dice Number Picker");
    }

    public int CalcModSum(int dice1, int dice2) {
        int moddedSum = (dice1 + dice2) % 6;
        return moddedSum;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

