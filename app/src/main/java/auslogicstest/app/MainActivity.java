package auslogicstest.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TTEST = "ttest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TTEST, "onCreate: ");
//        ProcessListFragment processFragment = new ProcessListFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager()
//        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
//                .add(R.id.container_for_fragments, processFragment)
//                .addToBackStack("myStack")
//                .commit();
    }
}
