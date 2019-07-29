package com.youngman.mop.lib.realtimedb;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.youngman.mop.data.Participant;
import com.youngman.mop.util.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YoungMan on 2019-06-27.
 */

public class MapFirebaseService {

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference databaseReference;
    private MemberLocation myLocation;


    public MapFirebaseService() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    /**
     * 단체지도방 생성
     */
    public void callCreateMapGroup(Long clubId, List<Participant> participants, CreateApiListener createApiListener) {
        DatabaseReference club = databaseReference.child(clubId.toString());
        Map<String, Object> map = new HashMap<>();

        for (Participant participant : participants) {
            LocationInfo locationInfo = LocationInfo.builder()
                    .name(participant.getName())
                    .latLng(new LatLng(0, 0))
                    .updateTime("init")
                    .build();

            map.put(participant.getEmail(), locationInfo);
        }

        club.setValue(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    createApiListener.onSuccess();
                } else {//There was an error
                    LogUtils.logInfo(databaseError.getDetails());
                    createApiListener.onFail(databaseError.getMessage());
                }
            }
        });
    }

    /**
     * 1. 동호회 단체 지도방 존재여부
     * 2. 단체 지도방이 있다면 초대 멤버인지 확인 여부
     */
    public void callIsValidateMapAndMember(Long clubId, String email, ValidateApiListener validateApiListener) {
        DatabaseReference clubReference = databaseReference.child(clubId.toString());
        clubReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    validateApiListener.onFail("생성되어 있는 단체지도방이 없습니다");
                    return;
                }

                if (dataSnapshot.child(email).exists()) {
                    validateApiListener.onSuccess();
                } else {
                    validateApiListener.onFail("단체지도방에 초대되지 않은 멤버입니다.");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                validateApiListener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    /**
     * 1. 자신의 위치 저장
     * 2. 동호회 멤버 위치목록 조회
     */
    public void callMapRefresh(Long clubId, String email, LocationInfo locationInfo, RefreshApiListener refreshApiListener) {
        DatabaseReference clubReference = databaseReference.child(clubId.toString());
        DatabaseReference memberReference = clubReference.child(email);
        if (locationInfo.isWrongLocation()) {
            return;
        }

        memberReference.setValue(locationInfo);

        clubReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<MemberLocation> otherLocations = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    double latitude = snapshot.child("latLng").child("latitude").getValue(Double.class);
                    double longitude = snapshot.child("latLng").child("longitude").getValue(Double.class);
                    String name = snapshot.child("name").getValue(String.class);
                    String updateTime = snapshot.child("updateTime").getValue(String.class);
                    LocationInfo locationInfo = LocationInfo.of(latitude, longitude, name, updateTime);

                    if (email.equals(snapshot.getKey())) {
                        myLocation = new MemberLocation(email, locationInfo);
                        continue;
                    }
                    otherLocations.add(new MemberLocation(snapshot.getKey(), locationInfo));
                }
                refreshApiListener.onSuccess(otherLocations, myLocation);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                refreshApiListener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    /**
     * 지도 단체방 나가기
     */
    public void callMapOut(Long clubId, String email, DeleteApiListener deleteApiListener) {
        DatabaseReference clubReference = databaseReference.child(clubId.toString());
        DatabaseReference memberReference = clubReference.child(email);
        memberReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                deleteApiListener.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                deleteApiListener.onFail(e.getMessage());
            }
        });
    }

//    public void callAddMapMember(Long clubId, String email) {
//        DatabaseReference club = databaseReference.child(clubId.toString());
//        Map<String, Object> map = new HashMap<>();
//        map.put(email, new FirebaseLocation());
//        club.setValue(map);
//    }

    public interface CreateApiListener {
        void onSuccess();
        void onFail(String message);
    }

    public interface ValidateApiListener {
        void onSuccess();
        void onFail(String message);
    }

    public interface RefreshApiListener {
        void onSuccess(List<MemberLocation> otherLocations, MemberLocation myLocation);
        void onFail(String message);
    }

    public interface DeleteApiListener {
        void onSuccess();
        void onFail(String message);
    }
}
