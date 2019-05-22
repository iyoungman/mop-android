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
public class MyClubDto {

    private List<ClubDto> clubDtoList;

    @Builder
    public MyClubDto(@NonNull List<ClubDto> clubDtoList) {
        this.clubDtoList = clubDtoList;
    }

    public static MyClubDto of(@NonNull List<ClubModel> clubModelList) {
        return new MyClubDto(clubModelList.stream()
                .map(ClubDto::of)
                .collect(Collectors.toList()));
    }
}
