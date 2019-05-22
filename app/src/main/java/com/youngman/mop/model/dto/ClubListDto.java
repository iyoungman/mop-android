package com.youngman.mop.model.dto;

import android.support.annotation.NonNull;

import com.youngman.mop.model.domain.ClubModel;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-05-03.
 */

@Getter
public class ClubListDto {

    private List<ClubDto> clubDtoList;

    @Builder
    public ClubListDto(@NonNull List<ClubDto> clubDtoList) {
        this.clubDtoList = clubDtoList;
    }

    public static ClubListDto of(@NonNull List<ClubModel> clubModelList) {
        return new ClubListDto(clubModelList.stream()
                .map(ClubDto::of)
                .collect(Collectors.toList()));
    }
}
