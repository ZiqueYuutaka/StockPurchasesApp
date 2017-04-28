package fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.zique_yuutaka.stockpurchasesapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Zique Yuutaka on 4/27/2017.
 */

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "StockPurchaseApp.date";
    private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState){

        //Create a calendar object to get integer values of Date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        mDatePicker = (DatePicker)v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_of_purchase)
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        String date = Integer.toString(year) + "-" + Integer.toString(month)
                                +"-"+Integer.toString(day);
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                .create();
    }

    //Sends the result of the user picking a new date back to BuildListFragment
    private void sendResult(int resultCode, String date){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
