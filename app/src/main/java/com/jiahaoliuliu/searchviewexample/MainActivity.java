package com.jiahaoliuliu.searchviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mExpandSearchViewButton;

    private MenuItem mMenuItemSearch;
    private static final int MENU_ITEM_SEARCH_ID = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        // TODO: implement this
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
        return true;
    }
}
