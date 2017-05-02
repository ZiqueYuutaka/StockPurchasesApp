package database;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dataobject.Stock;

/**
 * Created by Zique Yuutaka on 4/23/2017.
 */

public class TreeDB {

    private static final String DEBUG = "TREE_DB";

    //made public to remove during onDestroy Method
    public static StockNode rootStock = null;
    public static int size = 0;

    private class StockNode {
        public Stock stock;

        public StockNode(Stock stock) {
            this.stock = stock;
        }

        public StockNode right = null, left = null;

    }

    public TreeDB() {

    }

    // return a list in order
    public List<Stock> getInOrderList() {
        ArrayList<Stock> list = new ArrayList<>();

        buildListInOrder(rootStock, list);

        return list;
    }

    public void printInOrder(){
        if(rootStock == null){
            Log.d(DEBUG, "Empty tree");
        }else{
            printTraverse(rootStock);
        }
    }

    //print in order
    private void printTraverse(StockNode node){
        if(node == null){
            return;
        }else{
            printTraverse(node.left);
            Log.d(DEBUG, node.stock.getCustomerId());
            printTraverse(node.right);
        }
    }

    //get nodes in order
    //Traverse in order
    private void buildListInOrder(StockNode node, ArrayList<Stock> list){
        if(node == null){
            return;
        }else{
            buildListInOrder(node.left, list);
            list.add(node.stock);
            buildListInOrder(node.right, list);
        }
    }

    public void buildTree(List<Stock> list) {
        for (Stock stock : list) {
            Log.d(DEBUG, "Inserting " + stock.getCustomerId());
            insert(stock);
        }
    }

    public void insert(Stock node){
        if(rootStock == null){
            rootStock = new StockNode(node);
            size++;
        }else{
            StockNode parent = null;
            StockNode child = rootStock;

            //while the child node is not null
            //	if the new node is less than the current
            //	go down the left child
            //	else if the new node is greater than current
            //	go down the right child
            //	else non duplicates allowed
            while(child != null){
                if(node.compareTo(child.stock) < 0){
                    parent = child;
                    child = child.left;
                }else if(node.compareTo(child.stock) > 0){
                    parent = child;
                    child = child.right;
                }else{
                    //No duplicates allowed
                    Log.d("DEBUG","No duplicates allowed");
                    break;
                }
            }

            //A leaf has been found
            //If the new node is less than the parent node
            //	place node on the left
            //Otherwise place on right
            if(node.compareTo(parent.stock) < 0){
                parent.left = new StockNode(node);
                size++;
            }else{
                parent.right = new StockNode(node);
                size++;
            }
        }
    }
}
