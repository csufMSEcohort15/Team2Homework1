package com.example.dicegame01;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

public class StatsDialog extends DialogFragment
{
    private ArrayList<Stats> roundResults;

    public StatsDialog()
    {
        roundResults = new ArrayList<Stats>();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        Context dialogContext = dialogBuilder.getContext();
        dialogBuilder.setTitle("Stats Table");
        ArrayList<String> results = new ArrayList<String>();
        String temp = "";
        int i = 1;
        for(Stats stat : this.roundResults)
        {
            temp = "Game " +Integer.toString(i)+": Computer Score: "+stat.getComputerScore()+ " | Player Score: " + Integer.toString(stat.getPlayerScore()) + " | Round Winner: " +  stat.getRoundWinner();
            results.add(temp);
            i++;
        }
        dialogBuilder.setItems(results.toArray(new String[results.size()]), null);

        dialogBuilder.setCancelable(true);
        dialogBuilder.setNegativeButton("Exit", null);
        return dialogBuilder.create();
    }

    public void setRoundResults(ArrayList<Stats> list)
    {
        this.roundResults.clear();
        for(Stats stat : list)
        {
            this.roundResults.add(stat);
        }
    }

}
