package com.jiahaoliuliu.searchviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mExpandSearchViewButton;

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
}
