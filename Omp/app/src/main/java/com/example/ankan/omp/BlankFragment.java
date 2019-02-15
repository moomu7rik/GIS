package com.example.ankan.omp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.kml.KmlDocument;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
 public class BlankFragment extends Fragment implements MapEventsReceiver {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnimvi, btnTEXTedit, btnswap, btnCache,btnkml;
    String timeStamp ="";
    String file_name ="";
    String pathSave = "";
    String mediai="";
    String mediav="";
    String txt="";
    int a=0;
    int m=0;
    int t=0;

    public String kmlstorage="";
    private static final String IMAGE_DIRECTORY_NAME = "OMP_STORAGE";
    private static final String KML_DIRECTORY_NAME = "OMP_KML_STORAGE";

    MapView map;
    private IMapController mapController;
    Polygon polygon = new Polygon();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        file_name = "_kmlFile_" + timeStamp + ".kml";
        btnswap = (Button) view.findViewById(R.id.swap);
        btnswap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnimvi.setEnabled(true);
                btnswap.setEnabled(true);
                btnTEXTedit.setEnabled(true);
                btnkml.setEnabled(true);
                btnCache.setEnabled(false);
                a=1;

                Intent intent = new Intent(getActivity(), Audio.class);
                startActivity(intent);
            }
        });
        btnimvi = (Button) view.findViewById(R.id.imvi);
        btnimvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnimvi.setEnabled(true);
                btnswap.setEnabled(true);
                btnTEXTedit.setEnabled(true);
                btnkml.setEnabled(true);
                btnCache.setEnabled(false);
                m=1;
                Intent intent = new Intent(getActivity(), imvi.class);
                startActivity(intent);


            }
        });
        btnCache = (Button) view.findViewById(R.id.btnCache);
        btnCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        btnTEXTedit = (Button) view.findViewById(R.id.TEXTedit);
        btnTEXTedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnimvi.setEnabled(true);
                btnswap.setEnabled(true);
                btnTEXTedit.setEnabled(true);
                btnkml.setEnabled(true);
                btnCache.setEnabled(false);
                t=1;
                Intent intent = new Intent(getActivity(), TEXTedit.class);
                startActivity(intent);
            }
        });
        btnkml=(Button) view.findViewById(R.id.btnKml);
        btnkml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a==1) {
                    SharedPreferences sharedPreferencesa = getActivity().getSharedPreferences(Audio.gprefa,Context.MODE_PRIVATE);
                    pathSave = sharedPreferencesa.getString("aname", "NO AUDIO");
                }
                else
                {
                    pathSave="NO AUDIO";

                }

                if(m==1) {

                    SharedPreferences sharedPreferencesm = getActivity().getSharedPreferences(imvi.gprefm,Context.MODE_PRIVATE);
                    mediai = sharedPreferencesm.getString("pname", "NO IMAGE");
                    mediav = sharedPreferencesm.getString("vname", "NO VIDEO");
                }
                else{
                    mediai="NO IMAGE";
                }
                if(t==1) {

                    SharedPreferences sharedPreferencest = getActivity().getSharedPreferences(TEXTedit.gpreft,Context.MODE_PRIVATE);
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
                a=m=t=0;

            }
        });

        map = (MapView) view.findViewById(R.id.map);
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
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this.getActivity(), this);
        map.getOverlays().add(0, mapEventsOverlay);
        btnimvi.setEnabled(false);
        btnswap.setEnabled(false);
        btnTEXTedit.setEnabled(false);
        btnkml.setEnabled(false);
        btnCache.setEnabled(true);
     return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    List<GeoPoint> pts = new ArrayList<>();
    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
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

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    /*
    public void Anext(View view){
        btnimvi.setEnabled(true);
        btnswap.setEnabled(true);
        btnTEXTedit.setEnabled(true);
        btnkml.setEnabled(true);
        btnCache.setEnabled(false);
        a=1;

        Intent intent = new Intent(this.getActivity(), Audio.class);
        startActivity(intent);


    }
    public void Tnext(View view) {
        btnimvi.setEnabled(true);
        btnswap.setEnabled(true);
        btnTEXTedit.setEnabled(true);
        btnkml.setEnabled(true);
        btnCache.setEnabled(false);
        t=1;
        Intent intent = new Intent(this.getActivity(), TEXTedit.class);
        startActivity(intent);
    }
    public void Aimvi(View view) {
        btnimvi.setEnabled(true);
        btnswap.setEnabled(true);
        btnTEXTedit.setEnabled(true);
        btnkml.setEnabled(true);
        btnCache.setEnabled(false);
        m=1;
        Intent intent = new Intent(this.getActivity(), imvi.class);
        startActivity(intent);

    }
    */
   public void onResume(){
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

  /*
    public void polygon(View view)
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
    */
   /* public void kml(View view)
    {
        if(a==1) {
            SharedPreferences sharedPreferencesa = this.getActivity().getSharedPreferences(Audio.gprefa,Context.MODE_PRIVATE);
            pathSave = sharedPreferencesa.getString("aname", "NO AUDIO");
        }
        else
        {
            pathSave="NO AUDIO";

        }

        if(m==1) {

            SharedPreferences sharedPreferencesm = this.getActivity().getSharedPreferences(imvi.gprefm,Context.MODE_PRIVATE);
            mediai = sharedPreferencesm.getString("pname", "NO IMAGE");
            mediav = sharedPreferencesm.getString("vname", "NO VIDEO");
        }
        else{
            mediai="NO IMAGE";
        }
        if(t==1) {

            SharedPreferences sharedPreferencest = this.getActivity().getSharedPreferences(TEXTedit.gpreft,Context.MODE_PRIVATE);
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
        a=m=t=0;


    }
    */

}
