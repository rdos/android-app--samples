package auslogicstest.app;

import android.app.Application;

public class App extends Application {
    public static ProcessMan processMan;

    @Override
    public void onCreate() {
        super.onCreate();
        processMan = new ProcessMan();
        //Parse SDK stuff goes here
    }
}
