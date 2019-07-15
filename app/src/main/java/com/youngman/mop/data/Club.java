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
}
