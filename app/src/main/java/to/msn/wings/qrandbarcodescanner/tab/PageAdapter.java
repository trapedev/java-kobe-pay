package to.msn.wings.qrandbarcodescanner.tab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import to.msn.wings.qrandbarcodescanner.tab.tab1;
import to.msn.wings.qrandbarcodescanner.tab.tab2;
import to.msn.wings.qrandbarcodescanner.tab.tab3;

public class PageAdapter extends FragmentPagerAdapter {

    private int numoftab;

    public PageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numoftab = numOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new tab1();

            case 1:
                return new tab2();

            case 2:
                return new tab3();

            default:
                return null;
        }

    }

    @Override
    public int getCount(){
        return numoftab;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
