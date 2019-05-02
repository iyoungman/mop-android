package com.youngman.mop.model.dto;

import com.youngman.mop.model.domain.ClubModel;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-05-01.
 */

@Getter
public class ClubDto {

    private String id;
    private String name;
    private String introduce;
    private String createDate;
    private String hobby;
    private String upComingMeeting;
    private String upComingMeetingDate;

    @Builder
    public ClubDto(String id, String name, String introduce, String createDate, String hobby, String upComingMeeting, String upComingMeetingDate) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.createDate = createDate;
        this.hobby = hobby;
        this.upComingMeeting = upComingMeeting;
        this.upComingMeetingDate = upComingMeetingDate;
    }

    public static ClubDto of(ClubModel clubModel) {
        return ClubDto.builder()
                .id(clubModel.getId())
                .name(clubModel.getName())
                .introduce(clubModel.getIntroduce())
                .createDate(clubModel.getCreateDate())
                .hobby(clubModel.getHobby())
                .upComingMeeting(clubModel.getUpComingMeeting())
                .upComingMeetingDate(clubModel.getUpComingMeetingDate())
                .build();
    }
}
