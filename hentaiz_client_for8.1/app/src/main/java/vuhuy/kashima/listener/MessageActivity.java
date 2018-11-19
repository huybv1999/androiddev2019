
package vuhuy.kashima.listener;

import vuhuy.kashima.R;
import vuhuy.kashima.model.Extra;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Activity for single message view
 */
public class MessageActivity extends Activity
{
    /**
     * On create
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.message);

        ((TextView) findViewById(R.id.message)).setText(
            getIntent().getExtras().getString(Extra.MESSAGE)
        );
    }
}
