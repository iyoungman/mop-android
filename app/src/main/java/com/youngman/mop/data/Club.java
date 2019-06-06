package com.youngman.mop.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

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
    private String upComingMeetingDate;

    @Builder
    public Club(@NonNull Long clubId, @NonNull String name, @NonNull String introduce,
                @NonNull String createDate, @NonNull String region, @NonNull String hobby,
                @NonNull String upComingMeetingDate) {

        this.clubId = clubId;
        this.name = name;
        this.introduce = introduce;
        this.createDate = createDate;
        this.region = region;
        this.hobby = hobby;
        this.upComingMeetingDate = upComingMeetingDate;
    }
}