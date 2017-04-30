package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zique_yuutaka.stockpurchasesapp.R;
import com.zique_yuutaka.stockpurchasesapp.StockViewActivity;

import java.util.ArrayList;
import java.util.List;

import database.TreeDB;
import dataobject.Stock;

/**
 * Created by Zique Yuutaka on 4/18/2017.
 */

public class StockListFragment extends Fragment {

    private static final String DEBUG ="***STOCK_LIST***";

    private StockAdapter mStockAdapter;
    private RecyclerView stockRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup fragContainer,
                             Bundle savedInstanceState){
        Log.d(DEBUG,"onCreateView called");
        View v = inflater.inflate(R.layout.stock_list_fragment, fragContainer, false);

        stockRecycler = (RecyclerView) v.findViewById(R.id.stock_list_fragment);
        stockRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        setUI();

        return v;
    }

    private class StockHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener{
        public TextView mTitleTextView;

        //hold on to stock here to pass on click
        public Stock stockHolderStock;

        public StockHolder(View itemView){
            super(itemView);

           mTitleTextView = (TextView) itemView;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Log.d(DEBUG,mTitleTextView.getText().toString() +" was clicked");

            //set StockViewFragment with the holder's stock
            StockViewFragment.setStock(stockHolderStock);

            //I could also just search the tree

            //launch StockViewActivity
            Intent i = StockViewActivity.newIntent(getContext());
            startActivity(i);
        }

        //Set text of views
/*        public void bindWord(Word word){

        }*/
    }

    private class StockAdapter extends RecyclerView.Adapter<StockHolder>{
        private List<Stock> mList;

        public StockAdapter(List<Stock> list){

            mList = list;
        }

        @Override
        public StockHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(android.R.layout.simple_list_item_1, parent,false);
            return new StockHolder(view);
        }

        @Override
        public void onBindViewHolder(StockHolder holder, int position){
            Stock temp = mList.get(position);
//            holder.bindWord(word);
            holder.mTitleTextView.setText(temp.getCustomerId());
            holder.stockHolderStock = temp;
        }

        @Override
        public int getItemCount(){
            return mList.size();
        }
    }

    private void setUI(){
        ArrayList<String> tempList = new ArrayList<>();

        TreeDB myTree = new TreeDB();

        mStockAdapter = new StockAdapter(myTree.getInOrderList());
        stockRecycler.setAdapter(mStockAdapter);
    }
}
