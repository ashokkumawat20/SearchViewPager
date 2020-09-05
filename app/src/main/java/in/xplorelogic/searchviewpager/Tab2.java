package in.xplorelogic.searchviewpager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class Tab2 extends Fragment implements ISearch {

    private static final String ARG_SEARCHTERM = "search_term";
    private String mSearchTerm = null;

    private IFragmentListener mIFragmentListener = null;
    ArrayList<String> strings = null;

    ArrayAdapter<String> arrayAdapter = null;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.tab2, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview1);
        strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(String.valueOf(i)+"sfddfdsf");
        }
        //  strings.add("11");
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(arrayAdapter);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.getDataFromFragment_one(strings);
        if(getArguments()!=null) {
            mSearchTerm = (String) getArguments().get(ARG_SEARCHTERM);
        }
        return view;
    }
    public Tab2() {
    }

    @Override
    public void onTextQuery(String text) {
        arrayAdapter.getFilter().filter(text);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(null!=mSearchTerm) {
            onTextQuery(mSearchTerm);
        }
    }

    public static Tab2 newInstance(String searchTerm){
        Tab2 fragment = new Tab2();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SEARCHTERM, searchTerm);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIFragmentListener = (IFragmentListener) context;
        mIFragmentListener.addiSearch(Tab2.this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(null!=mIFragmentListener)
            mIFragmentListener.removeISearch(Tab2.this);
    }
}
