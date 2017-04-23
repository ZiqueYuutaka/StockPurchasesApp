package dataobject;

/**
 * Created by Zique Yuutaka on 4/23/2017.
 */

public class Stock {

    private String customerId;
    private String customerName;
    private String companyNameOfShares;
    private int numSharesPurchased;
    private float sharePurchasePrice;
    private String purchaseDate; //Could be a Date object
    private float totalStockWorth;

    public Stock(String customerId, String customerName, String companyNameOfShares,
                 int numSharesPurchased, float sharePurchasePrice, String purchaseDate,
                 float totalStockWorth) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.companyNameOfShares = companyNameOfShares;
        this.numSharesPurchased = numSharesPurchased;
        this.sharePurchasePrice = sharePurchasePrice;
        this.purchaseDate = purchaseDate;
        this.totalStockWorth = totalStockWorth;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompanyNameOfShares() {
        return companyNameOfShares;
    }

    public void setCompanyNameOfShares(String companyNameOfShares) {
        this.companyNameOfShares = companyNameOfShares;
    }

    public int getNumSharesPurchased() {
        return numSharesPurchased;
    }

    public void setNumSharesPurchased(int numSharesPurchased) {
        this.numSharesPurchased = numSharesPurchased;
    }

    public float getSharePurchasePrice() {
        return sharePurchasePrice;
    }

    public void setSharePurchasePrice(float sharePurchasePrice) {
        this.sharePurchasePrice = sharePurchasePrice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public float getTotalStockWorth() {
        return totalStockWorth;
    }

    public void setTotalStockWorth(float totalStockWorth) {
        this.totalStockWorth = totalStockWorth;
    }

    @Override
    public String toString(){

        return customerId + ";" +
                customerName + ";" +
                companyNameOfShares + ";" +
                Integer.toString(numSharesPurchased) + ";" +
                Float.toString(sharePurchasePrice) + ";" +
                purchaseDate + ";" +
                Float.toString(totalStockWorth) + "\n";
    }
}
