package com.example.ankan.omp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.kml.KmlDocument;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener,BlankFragment2.OnFragmentInteractionListener{



/*    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    String file_name = "_kmlFile_" + timeStamp + ".kml";
    String pathSave = "";
    String mediai="";
    String mediav="";
    String txt="";
    public String kmlstorage="";
    private static final String IMAGE_DIRECTORY_NAME = "OMP_STORAGE";
    private static final String KML_DIRECTORY_NAME = "OMP_KML_STORAGE";
*/



   /* MapView map;
    private IMapController mapController;
    Polygon polygon = new Polygon();
    */

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
    /*    Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));*/
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's tile servers will get you banned based on this string

        //inflate and create the map
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("COLLECT"));
        tabLayout.addTab(tabLayout.newTab().setText("VIEW"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new pageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


       /*  btnswap = (Button) findViewById(R.id.swap);
        btnimvi = (Button) findViewById(R.id.imvi);
        btnCache = (Button) findViewById(R.id.btnCache);
        btnTEXTedit = (Button) findViewById(R.id.TEXTedit);
        btnkml=(Button) findViewById(R.id.btnKml);
        */



    /*    map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(15.8);
        GeoPoint startPoint = new GeoPoint(23.5748702,87.2937521, 2.2944);
        mapController.setCenter(startPoint);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);
        startMarker.setTitle("NIT Durgapur");
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        map.getOverlays().add(0, mapEventsOverlay);
        btnimvi.setEnabled(false);
        btnswap.setEnabled(false);
        btnTEXTedit.setEnabled(false);
        btnkml.setEnabled(false);
        btnCache.setEnabled(false);
*/
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

/*
    public void Anext(View view){
        btnimvi.setEnabled(true);
        btnswap.setEnabled(true);
        btnTEXTedit.setEnabled(true);
        btnkml.setEnabled(true);
        btnCache.setEnabled(false);
        a=1;
        Intent intent = new Intent(this, Audio.class);
        startActivity(intent);


    }
    public void Tnext(View view) {
        btnimvi.setEnabled(true);
        btnswap.setEnabled(true);
        btnTEXTedit.setEnabled(true);
        btnkml.setEnabled(true);
        btnCache.setEnabled(false);
        t=1;
        Intent intent = new Intent(this, TEXTedit.class);
        startActivity(intent);
    }
    public void Aimvi(View view) {
        btnimvi.setEnabled(true);
        btnswap.setEnabled(true);
        btnTEXTedit.setEnabled(true);
        btnkml.setEnabled(true);
        btnCache.setEnabled(false);
        m=1;
        Intent intent = new Intent(this, imvi.class);
        startActivity(intent);

    }
    */

  /*  public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }
    */


       /* List<GeoPoint> pts = new ArrayList<>();

        public boolean singleTapConfirmedHelper (GeoPoint p){

                Polygon circle = new Polygon();
                circle.setPoints(Polygon.pointsAsCircle(p, 17.0));
                circle.setFillColor(0x12121212);
                circle.setStrokeColor(Color.RED);
                circle.setStrokeWidth(17);
                map.getOverlays().add(circle);
                circle.setInfoWindow(new
                        BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble,
                        map));
                circle.setTitle("Centered on " + p.getLatitude() + "," +
                        p.getLongitude());


                pts.add(new GeoPoint(p.getLatitude(), p.getLongitude()));
                map.invalidate();

            return true;
        }
        */

/*
        @Override
        public boolean longPressHelper (GeoPoint p){
            return false;
        }
*/
   /* public void polygon(View view)
    {

        polygon.setTitle("This is a polygon");
        polygon.setSubDescription(Polygon.class.getCanonicalName());
        polygon.setFillColor(0x12121212);
        polygon.setVisible(true);
        polygon.setStrokeColor(Color.BLACK);
        polygon.setStrokeWidth(4);
        polygon.setInfoWindow(new
                BasicInfoWindow(R.layout.bonuspack_bubble, map));
        polygon.setPoints(pts);
        map.getOverlays().add(polygon);
        map.invalidate();
        pts.clear();


        btnimvi.setEnabled(true);
        btnswap.setEnabled(true);
        btnTEXTedit.setEnabled(true);
        btnkml.setEnabled(false);
        btnCache.setEnabled(false);



    }
    public void kml(View view)
    {
        if(a==1) {
            SharedPreferences sharedPreferencesa = getSharedPreferences(Audio.gprefa, MODE_PRIVATE);
            pathSave = sharedPreferencesa.getString("aname", "NO AUDIO");
        }
        else
        {
            pathSave="NO AUDIO";

        }

        if(m==1) {
            SharedPreferences sharedPreferencesm = getSharedPreferences(imvi.gprefm, MODE_PRIVATE);
            mediai = sharedPreferencesm.getString("pname", "NO IMAGE");
            mediav = sharedPreferencesm.getString("vname", "NO VIDEO");
        }
        else{
            mediai="NO IMAGE";
        }
        if(t==1) {
            SharedPreferences sharedPreferencest = getSharedPreferences(TEXTedit.gpreft, MODE_PRIVATE);
            txt = sharedPreferencest.getString("tname", "NO TEXT");
        }
        else
        {
            txt="NO TEXT";
        }
        KmlDocument kml = new KmlDocument();
        kml.mKmlRoot.setExtendedData("kml type","polygon");
        kml.mKmlRoot.setExtendedData("audio type",pathSave);
        kml.mKmlRoot.setExtendedData("Group ID", "1");
        kml.mKmlRoot.setExtendedData("Priority", "50");
        kml.mKmlRoot.setExtendedData("time type", timeStamp);
        kml.mKmlRoot.setExtendedData("photo type", mediai);
        kml.mKmlRoot.setExtendedData("video type", mediav);
        kml.mKmlRoot.setExtendedData("text type", txt);

        kml.mKmlRoot.addOverlay(polygon, kml);
        File mediaStorageDir = new File(
                Environment
                        .getExternalStorageDirectory().getAbsoluteFile()+"/"+IMAGE_DIRECTORY_NAME+"/"+
                        KML_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");

            }
        }
        kmlstorage = mediaStorageDir.getPath() + File.separator
                + file_name;

        File file = new File(kmlstorage);

        kml.saveAsKML(file);
        btnimvi.setEnabled(false);
        btnswap.setEnabled(false);
        btnTEXTedit.setEnabled(false);
        btnkml.setEnabled(false);
        btnCache.setEnabled(true);


    }
    */




}
