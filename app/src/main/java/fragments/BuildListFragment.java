package fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zique_yuutaka.stockpurchasesapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private TextView totalWorth;

    private List<Stock> mStockList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup fragContainer,
                             Bundle savedInstanceState){
        Log.d(DEBUG,"onCreateView called");
        View v = inflater.inflate(R.layout.build_list_fragment, fragContainer, false);

        mStockList = new ArrayList<>();

        setUI(v);

        return v;
    }

    private void setUI(View v){

        stock = null;

        customerId = (EditText)v.findViewById(R.id.et_customer_name);
        customerName=(EditText)v.findViewById(R.id.et_customer_name);
        companyName=(EditText)v.findViewById(R.id.et_customer_name);
        amountPurchased=(EditText)v.findViewById(R.id.et_customer_name);

        purchasePrice=(EditText)v.findViewById(R.id.et_customer_name);

        totalWorth=(TextView) v.findViewById(R.id.et_customer_name);

        btAdd=(Button) v.findViewById(R.id.bt_add);
        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(DEBUG, "add button clicked");
                stock = new Stock();
                try{

                    stock.setCustomerId(customerId.getText().toString());
                    stock.setCompanyNameOfShares(companyName.getText().toString());
                    stock.setCustomerName(customerName.getText().toString());
                    stock.setNumSharesPurchased(Integer.parseInt(amountPurchased.getText().toString()));
                    stock.setSharePurchasePrice(Float.parseFloat(purchasePrice.getText().toString()));
                    stock.setPurchaseDate(btDate.getText().toString());

                    Log.d(DEBUG, "customerId: " + customerId.getText().toString());
                    Log.d(DEBUG, "customerName: " + customerName.getText().toString());
                    Log.d(DEBUG, "companyName: " + companyName.getText().toString());
                    Log.d(DEBUG, "numSharesPurchased: " + amountPurchased.getText().toString());
                    Log.d(DEBUG, "purchasePrice: " + purchasePrice.getText().toString());
                    Log.d(DEBUG, "purchaseDate: " + btDate.getText().toString());

                    if(stock.getCustomerId().equals("")){
                        Toast.makeText(getContext(), "Please Enter information", Toast.LENGTH_SHORT).show();
                        stock = null;
                    }else{
                        Log.d(DEBUG, "adding stock to list...");
                        mStockList.add(stock);
                        clearFields();
                    }

                }catch(NumberFormatException ex){
                    ex.printStackTrace();
                    Toast.makeText(getContext(), "Please Enter valid numerical information", Toast.LENGTH_SHORT).show();
                    amountPurchased.setText("");
                    purchasePrice.setText("");
                }
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
            btDate.setText(stock.getPurchaseDate().toString());//aka updateDate()
        }
    }//End onActivityResult

    private void clearFields(){
        customerId.setText("");
        customerName.setText("");
        companyName.setText("");
        amountPurchased.setText("");

        purchasePrice.setText("");
        btDate.setText(R.string.date_of_purchase);
    }

    @Override
    public void onDetach(){
        Log.d(DEBUG, "BuildListFragment onDetach()...");
        //build tree

        //save file

        super.onDetach();
    }
}
