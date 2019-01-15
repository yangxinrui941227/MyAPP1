package yxr.com.myapp.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import yxr.com.myapp.R;
import yxr.com.myapp.util.AlterMessage;
import yxr.com.myapp.util.Logger;

public class MapActivity extends AppCompatActivity {
    private LocationClient locationClient;
    private TextView text_view19;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new MyLocationListener());
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());

        text_view19 = findViewById(R.id.text_view19);
        setContentView(R.layout.activity_map);
        mapView = findViewById(R.id.bmap);

        List<String> permissList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MapActivity.this,Manifest.permission.ACCESS_FINE_LOCATION )!=PackageManager.PERMISSION_GRANTED){
            permissList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this,Manifest.permission.READ_PHONE_STATE )!=PackageManager.PERMISSION_GRANTED){
            permissList.add(Manifest.permission.READ_PHONE_STATE);
        } if (ContextCompat.checkSelfPermission(MapActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE )!=PackageManager.PERMISSION_GRANTED){
            permissList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissList.isEmpty()){
            String[] strings = permissList.toArray(new String[permissList.size()]);
            ActivityCompat.requestPermissions(MapActivity.this,strings,1);
        }else {
            requestLocation();
        }
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
    }
    private void requestLocation() {
        initLocation();
        locationClient.start();
    }

    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setIsNeedAddress(true);
        locationClient.setLocOption(option);

    }
    public void navigateTo(BDLocation location){
        if(this.isFirst){

        }  Logger.log("************"," navigateTo(BDLocation location){");
        LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(ll);
        baiduMap.animateMapStatus(mapStatusUpdate);
        mapStatusUpdate = MapStatusUpdateFactory.zoomTo(16f);
        baiduMap.animateMapStatus(mapStatusUpdate);
        this.isFirst= false;
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        MyLocationData build = builder.build();
        baiduMap.setMyLocationData(build);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            navigateTo(bdLocation);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPostion = new StringBuilder();
                    currentPostion.append("纬度：").append(bdLocation.getLatitude()).append("\n");
                    currentPostion.append("经线：").append(bdLocation.getLongitude()).append("\n");
                    currentPostion.append("国家：").append(bdLocation.getCountry()).append("\n");
                    currentPostion.append("省：").append(bdLocation.getProvince()).append("\n");
                    currentPostion.append("市：").append(bdLocation.getCity()).append("\n");
                    currentPostion.append("区：").append(bdLocation.getDistrict()).append("\n");
                    currentPostion.append("街道：").append(bdLocation.getStreet()).append("\n");
                    currentPostion.append("定位方式：");
                    if (bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                        currentPostion.append("gps");
                    }else if(bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
                        currentPostion.append("network");
                    }

                    text_view19.setText(currentPostion.toString());

                }
            });
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   // call();
                } else {
                    AlterMessage.toast(MapActivity.this, "you permission failed");
                }
                break;
            case 2:
                if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //initMediaPlayer();
                } else {
                    AlterMessage.toast(MapActivity.this, "you permission failed");
                    finish();
                }
                break;
        }
    }
}
