package com.youngman.mop.model.domain;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-05-01.
 */

@Getter
public class ClubModel {

    private String id;
    private String name;
    private String introduce;
    private String createDate;//생성날짜
    private String hobby;
    private String upComingMeeting;//다가오는 모임
    private String upComingMeetingDate;//다가오는 모임 날짜
}
