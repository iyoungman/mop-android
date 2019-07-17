package com.youngman.mop.data;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-06-06.
 */

@Getter
public class Club {

    private Long clubId;
    private String name;
    private String introduce;
    private String createDate;
    private String region;
    private String hobby;
    private String imageUri;
    private String upComingMeetingDate;

    @Builder
    public Club(Long clubId,
                String name,
                String introduce,
                String createDate,
                String region,
                String hobby,
                String imageUri,
                String upComingMeetingDate) {

        this.clubId = clubId;
        this.name = name;
        this.introduce = introduce;
        this.createDate = createDate;
        this.region = region;
        this.hobby = hobby;
        this.imageUri = imageUri;
        this.upComingMeetingDate = upComingMeetingDate;
    }

    public String getSimpleTime() {
        if (upComingMeetingDate == null) {
            return "예정된 모임이 없습니다";
        } else {
            String[] split = upComingMeetingDate.split("T");
            String date = split[0].substring(2);

            String[] split2 = split[1].split(":");
            String time = split2[0] + "시 " + split2[1] + "분";
            return date + " " + time;
        }
    }
}
