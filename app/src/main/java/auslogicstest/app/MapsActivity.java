package auslogicstest.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    GoogleMap mMap;
    final String TAG = "myLogs";
    private List<LatLng> places = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        places.add(new LatLng(55.754724, 37.621380));
        places.add(new LatLng(55.760133, 37.618697));
        places.add(new LatLng(55.764753, 37.591313));
        places.add(new LatLng(55.728466, 37.604155));
    }


    public void onClickTest(View view) {
        if (mMap == null) {
            Toast.makeText(getBaseContext(), "mMap==null!", Toast.LENGTH_LONG).show();
            return;
        }
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        LatLng zelenograd = new LatLng(55.975122, 37.166560);
//        LatLng bestPlace = new LatLng(55.723306, 37.539725);
//
//        mMap.addMarker(new MarkerOptions().position(zelenograd).title("zelenogRAD"));
//        mMap.addMarker(new MarkerOptions().position(bestPlace).title("bestPlace"));
//        mMap.addPolyline()



        MarkerOptions[] markers = new MarkerOptions[places.size()];
        for (int i = 0; i < places.size(); i++) {
            markers[i] = new MarkerOptions()
                    .position((places.get(i));
            googleMap.addMarker(markers[i]);
        }

        //Получаем контекст для запросов, mapsApiKey хранит в себе String с ключом для карт
        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey(getResources().getString(R.string.google_maps_key))
                .build();

//Здесь будет наш итоговый путь состоящий из набора точек
        DirectionsResult result = null;
        try {
            result = DirectionsApi.newRequest(geoApiContext)
                    .origin(places.get(0))//Место старта
                    .destination(places.get(places.size() - 1))//Пункт назначения
                    .waypoints(places.get(1), places.get(2)).await();//Промежуточные точки. Да, не очень красиво, можно через цикл, но зато наглядно
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//Преобразование итогового пути в набор точек
        List<com.google.maps.model.LatLng> path = result.routes[0].overviewPolyline.decodePath();

//Линия которую будем рисовать
        PolylineOptions line = new PolylineOptions();

        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();

//Проходимся по всем точкам, добавляем их в Polyline и в LanLngBounds.Builder
        for (int i = 0; i < path.size(); i++) {
            line.add(new com.google.android.gms.maps.model.LatLng(path.get(i).lat, path.get(i).lng));
            latLngBuilder.include(new com.google.android.gms.maps.model.LatLng(path.get(i).lat, path.get(i).lng));
        }

//Делаем линию более менее симпатичное
        line.width(16f).color(R.color.colorPrimary);

//Добавляем линию на карту
        googleMap.addPolyline(line);

//Выставляем камеру на нужную нам позицию
        LatLngBounds latLngBounds = latLngBuilder.build();

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getBaseContext().getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);


        CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, widthInDP, widthInDP, 25);//width это размер нашего экрана
        googleMap.moveCamera(track);
    }
}