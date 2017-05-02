package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zique_yuutaka.stockpurchasesapp.R;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import dataobject.Stock;

/**
 * Created by Zique Yuutaka on 4/30/2017.
 */

public class StockViewFragment extends Fragment {

    private static final String DEBUG = "VIEW_FRAG";

    private static Stock stock;

    private TextView customerId;
    private TextView customerName;
    private TextView companyName;
    private TextView amountPurchased;
    private TextView purchasePrice;
    private TextView purchaseDate;
    private TextView totalWorth;

    public static void setStock(Stock s){
        stock = s;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup fragContainer,
                             Bundle savedInstanceState){
        Log.d(DEBUG,"onCreateView called");
        View v = inflater.inflate(R.layout.stock_view_fragment, fragContainer, false);

        setUI(v);

        return v;
    }

    private void setUI(View view){
        customerId = (TextView) view.findViewById(R.id.tv_customer_id);
        customerId.setText(stock.getCustomerId());

        customerName = (TextView) view.findViewById(R.id.tv_customer_name);
        customerName.setText(stock.getCustomerName());

        companyName = (TextView) view.findViewById(R.id.tv_company);
        companyName.setText(stock.getCompanyNameOfShares());

        amountPurchased = (TextView) view.findViewById(R.id.tv_purchased_shares);
        amountPurchased.setText(Integer.toString(stock.getNumSharesPurchased()));

        purchasePrice = (TextView) view.findViewById(R.id.tv_price);
        String price = currencyFormat(stock.getSharePurchasePrice());
        purchasePrice.setText(price);

        purchaseDate = (TextView) view.findViewById(R.id.tv_date);
        purchaseDate.setText(stock.getPurchaseDate());

        totalWorth = (TextView) view.findViewById(R.id.tv_total_stock_worth);
        String total = currencyFormat(stock.getTotalStockWorth());
        totalWorth.setText(total);
    }

    private String currencyFormat(float money){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(money);
    }
}
