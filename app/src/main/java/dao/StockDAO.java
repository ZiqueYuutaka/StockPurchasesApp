package dao;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataobject.Stock;

/**
 * Created by Zique Yuutaka on 4/18/2017.
 */

public abstract class StockDAO {

    private static String DEBUG = "****STOCKDAO";
    private static String mFilename = "stocks.txt";

    public static Context mContext;

    //private static list of stocks

    //public list getStocks()

    //public void setStocks(list)

    public static void writeStocks(List<Stock> list) throws IOException{
        Log.d(DEBUG, "in writeStocks()");
        Writer writer = null;

        //REMEMBER TO ADD A BUFFER
        Log.d(DEBUG, "Writing stocks to file");
        OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
        Log.d(DEBUG, "out = null : " + out);
        writer = new OutputStreamWriter(out);
        Log.d(DEBUG, "writer = null: " + writer);
        for(Stock stock : list){
            writer.write(stock.toString());
        }
        writer.close();
    }

    public List<Stock> readStocks() throws IOException{
        ArrayList<Stock> tempList = new ArrayList<>();
        BufferedReader reader = null;

        //REMEMBER TO ADD A BUFFER
        try{
            Log.d(DEBUG, "Reading file....");
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            String temp = reader.readLine();
            Stock stock = parseInfo(temp);
            tempList.add(stock);
            while((temp = reader.readLine()) != null){
                stock = parseInfo(temp);
                tempList.add(stock);
            }
        }catch (Exception ex){
            Log.d(DEBUG, "Error reading file");
            ex.printStackTrace();
        }finally{
            if(reader != null){
                reader.close();
            }
        }

        return tempList;
    }

    private Stock parseInfo(String stockInfo){
        String[] info = stockInfo.split(";");

        return new Stock(info[0],info[1], info[2], Integer.parseInt(info[3]), Float.parseFloat(info[4]),
                info[5], Float.parseFloat(info[6]));
    }
}
