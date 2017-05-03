package validation;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Zique Yuutaka on 2/5/2017.
 */

public abstract class Validator {

    public static boolean isEmpty(Context context, String str){
        if(str.equals("")){
            Toast.makeText(context, "Please fill all spaces", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean isFloat(Context context, String str){
        try{
            Float.parseFloat(str);
            return true;
        }
        catch(Exception ex){
            Log.d("DebugTag", "Not a floating point number");
            Toast.makeText(context, "Incorrect decimal value exists", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean isInt(Context context, String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch(Exception ex){
            Log.d("DebugTag", "Not an integer");
            Toast.makeText(context, "Incorrect integer value exists", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean isValidId(Context context, String str){
        //id should be two capital letters followed by six digits
        String alphas = str.substring(0,2);
        //id should be eight characters total
        if(str.length() == 8){
            if(!validTwoChars(alphas) || !validNumbers(str.substring(2,str.length()))){
                Toast.makeText(context, "ID is invalid", Toast.LENGTH_SHORT).show();
                return false; // not valid two chars
            }
            return true;
        }else{
            Toast.makeText(context, "ID MUST be 8 alphanumeric characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Get substring of the first two letters
        // check if the are letters

        //Get substring of six digits
        // check if they are numbers
    }

    private static boolean validTwoChars(String str){

       for(int i = 0; i < str.length() -1; i++){
           if(Character.isDigit(str.charAt(i)) || !Character.isLetter(str.charAt(i))){
               return false;
           }
       }
       return true;
    }

    private static boolean validNumbers(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
}
