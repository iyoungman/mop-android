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

    private List<ClubDto> clubDtos;

    @Builder
    public ClubListDto(@NonNull List<ClubDto> clubDtos) {
        this.clubDtos = clubDtos;
    }

    public static ClubListDto of(@NonNull List<ClubModel> clubModels) {
        return ClubListDto.builder()
                .clubDtos(clubModels.stream()
                        .map(ClubDto::of)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
