package com.example.dicegame01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.NumberPicker;

public class ContinueGameDialog extends DialogFragment
{
    private int computerResult;
    private int playerResult;
    private String gameResult;

    public ContinueGameDialog()
    {
        this.computerResult = 0;
        this.playerResult = 0;
        this.gameResult = "";
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Do you want to Continue Playing?");
        if(this.gameResult == "Computer")
        {
            dialogBuilder.setMessage("The Computer won the previous round with a score of " + Integer.toString(this.computerResult) + ". While your score was " +
            Integer.toString(this.playerResult) + ". Do you want to continue Playing?");
        }
        else if(this.gameResult == "Player")
        {
            dialogBuilder.setMessage("You won the previous round with a score of " + Integer.toString(this.playerResult) + ". While the computer's score was " +
                    Integer.toString(this.computerResult) + ". Do you want to continue Playing?");
        }
        else
        {
            dialogBuilder.setMessage("You tied the computer in the previous round with a score of " + Integer.toString(this.playerResult) + ". While the computer's score was " +
                    Integer.toString(this.computerResult) + ". Do you want to continue Playing?");
        }

        dialogBuilder.setCancelable(true);

        dialogBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });

        dialogBuilder.setNegativeButton("Continue", null);

        return dialogBuilder.create();
    }

    public void setContinueGameDialogResult(Stats stat)
    {
        this.computerResult = stat.getComputerScore();
        this.playerResult = stat.getPlayerScore();
        this.gameResult = stat.getRoundWinner();
    }

}
