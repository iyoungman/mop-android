package com.youngman.mop.model.dto;

import android.support.annotation.NonNull;

import com.youngman.mop.model.domain.ClubModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-05-01.
 */

@Getter
public class MyClubDto {

    private List<ClubDto> clubDtos;

    @Builder
    public MyClubDto(@NonNull List<ClubDto> clubDtos) {
        this.clubDtos = clubDtos;
    }

    public static MyClubDto of(@NonNull List<ClubModel> clubModels) {
        return MyClubDto.builder()
                .clubDtos(clubModels.stream()
                        .map(ClubDto::of)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
