package com.youngman.mop.lib.tamp;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;
import com.youngman.mop.util.LogUtils;

import java.util.ArrayList;

/**
 * Created by YoungMan on 2019-11-14.
 */

public class TMapDirectionService extends AsyncTask<LatLng, Integer, ArrayList<TMapPoint>> {

    private Context context;
    private TMapDirectionServiceCallback callback;
    private TMapView tMapView;

    public TMapDirectionService(Context context, TMapDirectionServiceCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        tMapView = new TMapView(context);
        tMapView.setSKTMapApiKey("6cf83bc0-8517-482b-985f-cc300341c073");
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
    }

    @Override
    protected ArrayList<TMapPoint> doInBackground(LatLng... latLngs) {
        try {
            TMapPoint startPoint = convertLatLngToTMapPint(latLngs[0]);
            TMapPoint endPoint = convertLatLngToTMapPint(latLngs[1]);
//            TMapPoint endPoint = convertLatLngToTMapPint(new LatLng());

            TMapData tmapData = new TMapData();
            TMapPolyLine tMapPolyLine = tmapData.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, startPoint, endPoint);
            return tMapPolyLine.getLinePoint();

        } catch (Exception e) {
            LogUtils.logInfo(e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<TMapPoint> tMapPoints) {
        if (callback != null) {
            callback.onSuccess(convertTMapPointsToLatLng(tMapPoints));
            if (tMapPoints.size() == 0 || tMapPoints == null) {
                callback.onFailure("경로가 없습니다");
            }
        } else {
            callback.onFailure("Callback 실패");
        }
    }

    private TMapPoint convertLatLngToTMapPint(LatLng latLng) {
        return new TMapPoint(latLng.latitude, latLng.longitude);
    }

    private ArrayList<LatLng> convertTMapPointsToLatLng(ArrayList<TMapPoint> tMapPoints) {
        ArrayList<LatLng> latLngs = new ArrayList<>(tMapPoints.size());
        for (TMapPoint tMapPoint : tMapPoints) {
            latLngs.add(new LatLng(tMapPoint.getLatitude(), tMapPoint.getLongitude()));
        }
        return latLngs;
    }
}



