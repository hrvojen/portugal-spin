package com.otr.www.usda;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.otr.www.usda.adapter.FoodReportAdapter;
import com.otr.www.usda.dummy.DummyContent;
import com.otr.www.usda.http.HttpAsnyTaskReport;


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    private Handler handler;
    private FoodReportAdapter adapter;
    TextView textView;
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        textView = (TextView) rootView.findViewById(R.id.item_detail);

        // Show the dummy content as text in a TextView.
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //  new HttpAsnyTaskReport(getActivity(),getArguments().getString(ARG_ITEM_ID));
            //adapter = new FoodReportAdapter();
            new HttpAsnyTaskReport(handler, textView).execute(getArguments().getString(ARG_ITEM_ID));

//
//            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.content);
//            }
        }

        return rootView;
    }
}
