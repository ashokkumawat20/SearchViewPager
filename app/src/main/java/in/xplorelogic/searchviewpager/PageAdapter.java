package in.xplorelogic.searchviewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    private String mSearchTerm;
    //integer to count number of tabs
    int tabCount;

    public PageAdapter(@NonNull FragmentManager fm, int behavior, String searchTerm) {
        super(fm, behavior);
        //Initializing tab count
        this.tabCount= tabCount;
        this.mSearchTerm= searchTerm;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Tab1 tab1 = Tab1.newInstance(mSearchTerm);
                return tab1;
            case 1:
                Tab2 tab2 = Tab2.newInstance(mSearchTerm);
                return tab2;
            case 2:
                Tab3 tab3 = Tab3.newInstance(mSearchTerm);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
    public void setTextQueryChanged(String newText) {
        mSearchTerm = newText;
    }
}
