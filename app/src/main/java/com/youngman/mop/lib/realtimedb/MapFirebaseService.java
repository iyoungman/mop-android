package com.youngman.mop.lib.realtimedb;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.youngman.mop.util.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
     * 1. 자신의 위치 저장
     * 2. 동호회 멤버 위치목록 조회
     */
    public void callMapRefresh(Long clubId, String email, LatLng latLng, RefreshApiListener refreshApiListener) {
        DatabaseReference clubReference = databaseReference.child(clubId.toString());
        DatabaseReference memberReference = clubReference.child(email);
        if(isAllZero(latLng.latitude, latLng.longitude)) {
            return;
        }
        memberReference.setValue(latLng);

        clubReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<MemberLocation> otherLocations = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    double latitude = snapshot.child("latitude").getValue(Double.class);
                    double longitude = snapshot.child("longitude").getValue(Double.class);

                    if(email.equals(snapshot.getKey())) {
                        myLocation = new MemberLocation(snapshot.getKey(), new LatLng(latitude, longitude));
                        continue;
                    }
                    otherLocations.add(new MemberLocation(snapshot.getKey(), new LatLng(latitude, longitude)));
                }

                refreshApiListener.onSuccess(otherLocations, myLocation);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                LogUtils.logDebug(databaseError.getMessage());
                LogUtils.logDebug(databaseError.getDetails());
                refreshApiListener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    private boolean isAllZero(double latitude, double longitude) {
        long count = Stream.of(latitude, longitude)
                .filter(data -> data.equals(0d))
                .count();
        Predicate<Long> isAllZero = cnt -> cnt == 2;
        return isAllZero.test(count);
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

    //동호회 인원들 위치 목록 가져오기


    //DB 변경이 있을때마다 가져온다
//        databaseReference.child(clubId.toString()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    LogUtils.logDebug(snapshot.getKey());
//                    LogUtils.logDebug(snapshot.getValue().toString());
//                    apiListener.onSuccess(null);//TODO 어떻게 받지
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                LogUtils.logDebug(databaseError.getMessage());
//                LogUtils.logDebug(databaseError.getDetails());
//                apiListener.onFail("통신에 실패하였습니다."); }
//        });
//}


//    /**
//     * 단체지도방 생성
//     */
//    public void callCreateMapMemberGroup(Long clubId, List<String> memberEmails) {
//        DatabaseReference club = databaseReference.child(clubId.toString());
//        Map<String, Object> map = new HashMap<>();
//
////        for (String email : memberEmails) {
////            map.put(email, new FirebaseLocation());
////        }
//        club.setValue(map);
//    }
//
//    /**
//     * 초기 한번만 실행하는 용도
//     */
//    public void callIsMapMember(Long clubId, String email) {
//        databaseReference.child(clubId.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.child(email).exists()) {
//                    LogUtils.logDebug("지도 단톡방 멤버가 아닙니다");
//                    return;
//                }
//                LogUtils.logDebug("지도 단톡방 멤버입니다");
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    LogUtils.logDebug(snapshot.getKey());
//                    LogUtils.logDebug(snapshot.getValue().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    /**
//     * 단체지도방의 멤버들 위치 정보 받기
//     */
//    public void callMapMembersLocationByClubId(@NonNull Long clubId,
//                                               @NonNull MapSource.ApiListener listener) {
//
//        databaseReference.child(clubId.toString()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    LogUtils.logDebug(snapshot.getKey());
//                    LogUtils.logDebug(snapshot.getValue().toString());
//                    listener.onSuccess(null);//TODO 어떻게 받지
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                LogUtils.logDebug(databaseError.getMessage());
//                LogUtils.logDebug(databaseError.getDetails());
//                listener.onFail("통신에 실패하였습니다.");
//            }
//        });
//    }
//
//    /**
//     * 단체지도방 멤버 추가
//     */
//    public void callAddMapMember(Long clubId, String email) {
////        DatabaseReference club = databaseReference.child(clubId.toString());
////        Map<String, Object> map = new HashMap<>();
////        map.put(email, new FirebaseLocation());
////        club.setValue(map);
//    }

    public interface RefreshApiListener {
        void onSuccess(List<MemberLocation> otherLocations, MemberLocation myLocation);
        void onFail(String message);
    }

    public interface DeleteApiListener {
        void onSuccess();
        void onFail(String message);
    }
}
