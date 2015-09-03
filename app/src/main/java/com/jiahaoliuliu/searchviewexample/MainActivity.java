package com.jiahaoliuliu.searchviewexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG  = "MainActivity";

    private Button mExpandSearchViewButton;
    private SearchView mSearchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;

    private MenuItem mMenuItemSearch;
    private static final int MENU_ITEM_SEARCH_ID = 1000;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mExpandSearchViewButton = (Button) findViewById(R.id.expand_search_view_button);
        mExpandSearchViewButton.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.expand_search_view_button:
                    expandSearchView();
                    break;
            }
        }
    };

    private void expandSearchView() {
        Toast.makeText(this, "Expanding the search view", Toast.LENGTH_SHORT).show();
        mMenuItemSearch.expandActionView();
        mSearchAutoComplete.setText("Search expanded by external button");
    }

    // Action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Update user profile
        mMenuItemSearch = menu.add(Menu.NONE, MENU_ITEM_SEARCH_ID, Menu
                .NONE, R.string.action_bar_search)
                .setIcon(R.drawable.ic_action_search)
                .setActionView(R.layout.search_layout);
        mMenuItemSearch.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        setUpSearchView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_SEARCH_ID:
                mMenuItemSearch.expandActionView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpSearchView() {
        // The precondition is that mMenuItemSearch is not null
        if (mMenuItemSearch == null) {
            Log.e(TAG, "Trying to set up search view when the menu item search is null");
            return;
        }

        mSearchView = (SearchView) MenuItemCompat.getActionView(mMenuItemSearch);
        mSearchAutoComplete = (SearchView.SearchAutoComplete)mSearchView.findViewById(R.id.search_src_text);
        ImageView closeButton = (ImageView) mSearchView.findViewById(R.id.search_close_btn);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String keyword) {
                Log.v(TAG, "Searching the videos with the keyword " + keyword);
                mSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "The search has been cancelled. Requesting the list of all the videos to the module");
                mSearchAutoComplete.setText("");
                mSearchView.setQuery("", false);
                mSearchView.onActionViewCollapsed();
                mMenuItemSearch.collapseActionView();
            }
        });

        MenuItemCompat.setOnActionExpandListener(mMenuItemSearch, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mSearchView.setQuery("", false);
                mSearchView.onActionViewCollapsed();
                return true;
            }
        });
    }
}
