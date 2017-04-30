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

    //Broken
    public static boolean isFloat(Context context, String str){
        try{
            Float temp = Float.parseFloat(str);
            return true;
        }
        catch(Exception ex){
            Log.d("DebugTag", "Not a floating point number");
            Toast.makeText(context, "Value is not a number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean inRange(Context context, String val, int min, int max){
        float temp = Float.parseFloat(val);

        if (temp >= min && temp <= max){
            return true;
        }else{
            Toast.makeText(context, "Value is not in range(" + min + " - " + max + ")", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
