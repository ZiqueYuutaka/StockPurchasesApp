package fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.zique_yuutaka.stockpurchasesapp.R;

import java.util.Date;

import dataobject.Stock;

/**
 * Created by Zique Yuutaka on 4/18/2017.
 */

public class BuildListFragment extends Fragment {

    private static String DEBUG="BUILD_LIST_FRAGMENT";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Stock stock;
    private Button btAdd;
    private EditText customerId;
    private EditText customerName;
    private EditText companyName;
    private EditText amountPurchased;
    private EditText purchasePrice;
    private Button btDate;
    private TextView TotalWorth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup fragContainer,
                             Bundle savedInstanceState){
        Log.d(DEBUG,"onCreateView called");
        View v = inflater.inflate(R.layout.build_list_fragment, fragContainer, false);

        setUI(v);

        return v;
    }

    private void setUI(View v){

        stock = new Stock();

        customerId = (EditText)v.findViewById(R.id.et_customer_name);
        customerName=(EditText)v.findViewById(R.id.et_customer_name);
        companyName=(EditText)v.findViewById(R.id.et_customer_name);
        amountPurchased=(EditText)v.findViewById(R.id.et_customer_name);
        purchasePrice=(EditText)v.findViewById(R.id.et_customer_name);
        TotalWorth=(TextView) v.findViewById(R.id.et_customer_name);

        btAdd=(Button) v.findViewById(R.id.bt_add);
        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(DEBUG, "add button clicked");
            }
        });

        btDate=(Button) v.findViewById(R.id.bt_purchase_date);
        btDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(DEBUG, "date picker button clicked");
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(BuildListFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //If there was an error
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_DATE){
            String date = (String) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            stock.setPurchaseDate(date);
            btDate.setText(stock.getPurchaseDate().toString());//aka updateDate()
        }
    }//End onActivityResult
}
