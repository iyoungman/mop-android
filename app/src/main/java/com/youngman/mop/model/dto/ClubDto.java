package com.youngman.mop.model.dto;

import com.youngman.mop.model.domain.ClubModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Created by YoungMan on 2019-05-01.
 */

@Getter
public class ClubDto {

    private Long clubId;
    private String name;
    private String introduce;
    private String createDate;
    private String region;
    private String hobby;
    private String upComingMeetingDate;

    @Builder
    public ClubDto(@NonNull Long clubId,
                   @NonNull String name,
                   @NonNull String introduce,
                   @NonNull String createDate,
                   @NonNull String region,
                   @NonNull String hobby,
                   @NonNull String upComingMeetingDate) {

        this.clubId = clubId;
        this.name = name;
        this.introduce = introduce;
        this.createDate = createDate;
        this.region = region;
        this.hobby = hobby;
        this.upComingMeetingDate = upComingMeetingDate;
    }

    public static ClubDto of(@NonNull ClubModel clubModel) {
        return ClubDto.builder()
                .clubId(clubModel.getClubId())
                .name(clubModel.getName())
                .introduce(clubModel.getIntroduce())
                .createDate(clubModel.getCreateDate())
                .region(clubModel.getRegion())
                .hobby(clubModel.getHobby())
                .upComingMeetingDate(clubModel.getUpComingMeetingDate())
                .build();
    }
}
