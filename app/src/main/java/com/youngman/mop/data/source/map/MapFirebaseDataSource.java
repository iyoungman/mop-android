package com.youngman.mop.data.source.map;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.youngman.mop.util.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YoungMan on 2019-06-12.
 */

public class MapFirebaseDataSource {

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference databaseReference;


    public MapFirebaseDataSource() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    /**
     * 단체지도방 생성
     */
    public void callCreateMapMemberGroup(Long clubId, List<String> memberEmails) {
        DatabaseReference club = databaseReference.child(clubId.toString());
        Map<String, Object> map = new HashMap<>();

        for(String email : memberEmails) {
            map.put(email, new FirebaseLocation());
        }
        club.setValue(map);
    }

    /**
     * 초기 한번만 실행하는 용도
     */
    public void callIsMapMember(Long clubId, String email) {
        databaseReference.child(clubId.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child(email).exists()) {
                    LogUtils.logDebug("지도 단톡방 멤버가 아닙니다");
                    return;
                }
                LogUtils.logDebug("지도 단톡방 멤버입니다");

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LogUtils.logDebug(snapshot.getKey());
                    LogUtils.logDebug(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * 단체지도방의 멤버들 위치 정보 받기
     */
    public void callMapMembersLocationByClubId(@NonNull Long clubId,
                                               @NonNull MapSource.ApiListener listener) {

        databaseReference.child(clubId.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LogUtils.logDebug(snapshot.getKey());
                    LogUtils.logDebug(snapshot.getValue().toString());
                    listener.onSuccess(null);//TODO 어떻게 받지
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                LogUtils.logDebug(databaseError.getMessage());
                LogUtils.logDebug(databaseError.getDetails());
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }

    /**
     * 단체지도방 멤버 추가
     */
    public void callAddMapMember(Long clubId, String email) {
        DatabaseReference club = databaseReference.child(clubId.toString());
        Map<String, Object> map = new HashMap<>();
        map.put(email, new FirebaseLocation());
        club.setValue(map);
    }

}
