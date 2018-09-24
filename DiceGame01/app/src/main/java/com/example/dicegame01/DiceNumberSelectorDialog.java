package com.example.dicegame01;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.NumberPicker;

public class DiceNumberSelectorDialog extends DialogFragment {


    private NumberPicker.OnValueChangeListener onValueChangeListener;

    private int firstDieRolled;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final NumberPicker picker = new NumberPicker(getActivity());
        picker.setMinValue(1);
        picker.setMaxValue(6);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Choose the value of the Dice side you want to Play");
        dialogBuilder.setMessage("Your first rolled die was " + getFirstDieRolled() + ". Select your second dice face value :");

        dialogBuilder.setCancelable(false);

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onValueChangeListener.onValueChange(picker, picker.getValue(), picker.getValue());
            }
        });

        dialogBuilder.setView(picker);
        return dialogBuilder.create();
    }


    ////Getters and Setters
    public NumberPicker.OnValueChangeListener getOnValueChangeListener() {
        return onValueChangeListener;
    }

    public void setOnValueChangeListener(NumberPicker.OnValueChangeListener onValueChangeListener) {
        this.onValueChangeListener = onValueChangeListener;
    }

    public int getFirstDieRolled() {
        return firstDieRolled;
    }

    public void setFirstDieRolled(int firstDieRolled) {
        this.firstDieRolled = firstDieRolled;
    }


}
