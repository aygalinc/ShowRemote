package aygalinc.fr.showremote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Consumer;

import aygalinc.fr.showremote.util.ConnectivityUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    RecyclerView.Adapter adapter;

    EditText showEditTextDescription;

    TvServerClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new TvServerClient(this);

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        showEditTextDescription = findViewById(R.id.searchText);
        showEditTextDescription.setEnabled(false);

    }

    @Override
    protected void onStart() {
        super.onStart();


        if (!ConnectivityUtil.checkConnectivity(this)) {
            ConnectivityUtil.toast(this, "No internet connection!");
            hideKeyboard(showEditTextDescription);
        } else {

            showEditTextDescription.setEnabled(true);
            showEditTextDescription.setOnEditorActionListener(new TextViewListener());
        }
    }

    private void hideKeyboard(EditText editText)
    {
        InputMethodManager imm= (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private class TextViewListener implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                final Set<Show> showSet = new ConcurrentSkipListSet<>();
                adapter = new RecylerAdapter(showSet);
                String showToSearch = showEditTextDescription.getText().toString();
                if (showToSearch == null){
                    Log.w(TAG,"Empty text is not allowed");
                    return true;
                }
                client.searchShows(showToSearch, new Consumer<Long>() {
                    @Override
                    public void accept(Long showId) {
                        client.getShow(showId, new Consumer<Show>() {
                            @Override
                            public void accept(Show show) {
                                showSet.add(show);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });

                recyclerView.setAdapter(adapter);
                hideKeyboard(showEditTextDescription);
                return true;
            }
            return false;
        }
    }
}
