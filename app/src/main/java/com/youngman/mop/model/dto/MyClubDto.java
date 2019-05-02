package com.youngman.mop.model.dto;

import com.youngman.mop.model.domain.ClubModel;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-05-01.
 */

@Getter
public class MyClubDto {

    private List<ClubDto> clubDtos;

    @Builder
    public MyClubDto(List<ClubDto> clubDtos) {
        this.clubDtos = clubDtos;
    }

    public static MyClubDto of(List<ClubModel> clubModels) {
        return new MyClubDto(clubModels
                .stream()
                .map(ClubDto::of)
                .collect(Collectors.toList()));
    }
}
