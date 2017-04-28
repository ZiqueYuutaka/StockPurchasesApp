package fragments;

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

    private class StockHolder extends RecyclerView.ViewHolder{
        public TextView mTitleTextView;


        public StockHolder(View itemView){
            super(itemView);

           mTitleTextView = (TextView) itemView;
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
