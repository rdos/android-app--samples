package auslogicstest.app;

import android.os.Bundle;

public class ProcessListActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_list);
        log("onCreate");
//        ProcessListFragment processFragment = new ProcessListFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager()
//        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
//                .add(R.id.container_for_fragments, processFragment)
//                .addToBackStack("myStack")
//                .commit();
    }
}
