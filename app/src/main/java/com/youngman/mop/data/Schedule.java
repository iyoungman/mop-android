package com.youngman.mop.data;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by YoungMan on 2019-06-09.
 */

@Getter
public class Schedule {

    private Long id;
    private String name;
    private String content;
    private String region;
    private String writer;
    private String meetingTime;
    private Long clubId;


    @Builder
    public Schedule(Long id,
                    String name,
                    String content,
                    String region,
                    String writer,
                    String meetingTime,
                    Long clubId) {

        this.id = id;
        this.name = name;
        this.content = content;
        this.region = region;
        this.writer = writer;
        this.meetingTime = meetingTime;
        this.clubId = clubId;
    }

    public boolean isAllNonNull() {
        long count =  Stream.of(name, content, region, writer, meetingTime, String.valueOf(clubId))
                .filter(data -> !data.isEmpty())
                .count();

        Predicate<Long> isAllNonNull = cnt -> cnt == 6;
        return isAllNonNull.test(count);
    }

    public String getOnlyTime() {
        String[] split = meetingTime.split("T");
        String time = split[1];

        String[] split2 = time.split(":");
        return split2[0] + "시 " + split2[1] + "분";
    }

}
