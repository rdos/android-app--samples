package auslogicstest.app;

import android.app.Application;
import android.widget.Toast;

public class App extends Application {
    public static ProcessMan processMan;

    @Override
    public void onCreate() {
        try {
            super.onCreate();
            processMan = new ProcessMan();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(),"Oops! :(", Toast.LENGTH_LONG).show();
        }

        //Parse SDK stuff goes here
    }
}
