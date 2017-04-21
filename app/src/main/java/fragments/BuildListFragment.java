package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zique_yuutaka.stockpurchasesapp.R;

/**
 * Created by Zique Yuutaka on 4/18/2017.
 */

public class BuildListFragment extends Fragment {

    private static String DEBUG="BUILD_LIST_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup fragContainer,
                             Bundle savedInstanceState){
        Log.d(DEBUG,"onCreateView called");
        View v = inflater.inflate(R.layout.build_list_fragment, fragContainer, false);

        return v;
    }
}
