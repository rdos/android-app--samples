package auslogicstest.app;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;


//ActionBarActivities
abstract class AbstractActivity extends AppCompatActivity {

    // TODO:)
    private static final String LOG_TAG__TTEST = "ttest";

    protected void log(String msg) {
        Log.d(LOG_TAG__TTEST, msg);
    }
}
