package aygalinc.fr.showremote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by colin on 20/01/2018.
 */

public class ShowDetailsActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_layout);

        Toolbar toolbar = findViewById(R.id.view_toolbar);

        TextView textView = findViewById(R.id.view_text_description);

        // To retrieve object in second Activity
        String showName = getIntent().getStringExtra("name");
        String showDescription = getIntent().getStringExtra("description");

        toolbar.setTitle(showName); //Set the title of the toolbar to the series name
        setActionBar(toolbar);

        textView.setText(showDescription);

    }
}

