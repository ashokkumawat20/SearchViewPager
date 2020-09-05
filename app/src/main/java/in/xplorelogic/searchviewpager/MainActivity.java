package in.xplorelogic.searchviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.ViewPager;


import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, IFragmentListener {

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    ArrayList<ISearch> iSearch = new ArrayList<>();
    private MenuItem searchMenuItem;
    private String newText;
    private PageAdapter adapter;
    ArrayList<String> listData = null;

    IDataCallback iDataCallback = null;
    private FragmentTabHost mTabHost;
    TextView title;

    public void setiDataCallback(IDataCallback iDataCallback) {
        this.iDataCallback = iDataCallback;
        iDataCallback.onFragmentCreated(listData);
    }

    @Override
    public void addiSearch(ISearch iSearch) {
        this.iSearch.add(iSearch);
    }

    @Override
    public void removeISearch(ISearch iSearch) {
        this.iSearch.remove(iSearch);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listData = new ArrayList<>();
       // title = (TextView) findViewById(R.id.title);
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Active Ads", null),
                Tab1.class, null);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Inactive Ads", null),
                Tab2.class, null);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("New Ad", null),
                Tab3.class, null);
        mTabHost.setCurrentTab(0);
        mTabHost.setSelected(true);

        String tabs = mTabHost.getCurrentTabTag();
        if (tabs.equals("tab1")) {
          //  title.setText(R.string.homePosActAds);
            for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
                mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
            }
            mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#D9D9D9")); // selected

        }
        if (tabs.equals("tab2")) {
          //  title.setText(R.string.homePosInActAds);
            for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
                mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
            }
            mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#D9D9D9")); // selected

        }
        if (tabs.equals("tab3")) {
          //  title.setText(R.string.homePosAddAds);
            for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
                mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
            }
            mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#D9D9D9")); // selected

        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                String tabs = tabId;
                if (tabs.equals("tab1")) {
                    //title.setText(R.string.homePosActAds);
                }
                if (tabs.equals("tab2")) {
                   // title.setText(R.string.homePosInActAds);

                }
                if (tabs.equals("tab3")) {
                    //title.setText(R.string.homePosAddAds);

                }
                for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
                    mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
                }
                mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#D9D9D9")); // selected
            }
        });

//        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
//
//        //Creating our pager adapter
        adapter = new PageAdapter(getSupportFragmentManager(), mTabHost.getChildCount(), newText);
//
//        //Adding adapter to pager
        viewPager.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }


    public void getDataFromFragment_one(ArrayList<String> listData) {
        this.listData = listData;
        Log.e("-->", "" + listData.toString());
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        this.newText = newText;
        adapter.setTextQueryChanged(newText);
        for (ISearch iSearchLocal : this.iSearch)
            iSearchLocal.onTextQuery(newText);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
