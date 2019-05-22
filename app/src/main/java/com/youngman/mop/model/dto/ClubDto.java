package com.youngman.mop.model.dto;

import com.youngman.mop.model.domain.ClubModel;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Created by YoungMan on 2019-05-01.
 */

@Getter
public class ClubDto {

    private String id;
    private String name;
    private String introduce;
    private String createDate;
    private String region;
    private String hobby;
    private String upComingMeeting;
    private String upComingMeetingDate;

    @Builder
    public ClubDto(@NonNull String id,
                   @NonNull String name,
                   @NonNull String introduce,
                   @NonNull String createDate,
                   @NonNull String region,
                   @NonNull String hobby,
                   @NonNull String upComingMeeting,
                   @NonNull String upComingMeetingDate) {

        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.createDate = createDate;
        this.region = region;
        this.hobby = hobby;
        this.upComingMeeting = upComingMeeting;
        this.upComingMeetingDate = upComingMeetingDate;
    }

    public static ClubDto of(@NonNull ClubModel clubModel) {
        return ClubDto.builder()
                .id(clubModel.getId())
                .name(clubModel.getName())
                .introduce(clubModel.getIntroduce())
                .createDate(clubModel.getCreateDate())
                .region(clubModel.getRegion())
                .hobby(clubModel.getHobby())
                .upComingMeeting(clubModel.getUpComingMeeting())
                .upComingMeetingDate(clubModel.getUpComingMeetingDate())
                .build();
    }
}
