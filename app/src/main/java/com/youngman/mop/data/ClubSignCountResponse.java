package com.youngman.mop.data;

import org.eazegraph.lib.models.BarModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-11-02.
 */

@Getter
public class ClubSignCountResponse {

    private List<ClubSignCount> clubSignCounts = new ArrayList<>();

    public ClubSignCountResponse(List<ClubSignCount> clubSignCounts) {
        this.clubSignCounts = clubSignCounts;
    }

    public List<BarModel> toBarModels() {
        return clubSignCounts.stream()
                .map(ClubSignCount::toBarModel)
                .collect(Collectors.toList());
    }

    public int getAllSignCount() {
        return clubSignCounts.stream()
                .mapToInt(c -> (int) c.getCount())
                .sum();
    }

    public String getStartDate() {
        return clubSignCounts.get(0).getStatisticsDate();
    }

    public String getEndDate() {
        return clubSignCounts.get(clubSignCounts.size() - 1).getStatisticsDate();
    }

}
