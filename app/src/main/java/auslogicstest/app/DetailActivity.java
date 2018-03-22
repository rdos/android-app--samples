package auslogicstest.app;


import android.os.Bundle;

public class DetailActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        log("onCreate");
//        Bundle extras = getIntent().getExtras();
//        String s = extras.getString("selectedValue");
//        WebView viewer = (WebView) findViewById(R.id.webView1);
//        viewer.loadUrl(s);
    }
}