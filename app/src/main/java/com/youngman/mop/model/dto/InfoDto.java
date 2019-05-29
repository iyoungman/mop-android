package com.youngman.mop.model.dto;

import android.support.annotation.NonNull;

import com.youngman.mop.model.domain.ClubModel;
import com.youngman.mop.model.domain.MemberModel;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-05-28.
 */

@Getter
public class InfoDto {

    private ClubDto clubDto;
    private List<MemberDto> memberDtos;

    @Builder
    public InfoDto(@NonNull ClubDto clubDto, @NonNull List<MemberDto> memberDtos) {
        this.clubDto = clubDto;
        this.memberDtos = memberDtos;
    }

    public static InfoDto of(@NonNull ClubModel clubModel, @NonNull List<MemberModel> memberModels) {
        return InfoDto.builder()
                .clubDto(ClubDto.of(clubModel))
                .memberDtos(memberModels.stream()
                        .map(MemberDto::of)
                        .collect(Collectors.toList())
                )
               .build();
    }
}
