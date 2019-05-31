package com.youngman.mop.model.dto;

import android.support.annotation.NonNull;

import com.youngman.mop.model.domain.ClubModel;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-05-01.
 */

@Getter
public class MyClubListDto {

    private List<ClubDto> clubDtos;

    @Builder
    public MyClubListDto(@NonNull List<ClubDto> clubDtos) {
        this.clubDtos = clubDtos;
    }

    public static MyClubListDto of(@NonNull List<ClubModel> clubModels) {
        return MyClubListDto.builder()
                .clubDtos(clubModels.stream()
                        .map(ClubDto::of)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
