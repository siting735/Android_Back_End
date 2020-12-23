package com.kuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Activity {
    private int activityId;
    private String title;
    private String location;
    private int classId;
    private int state;
    private int attendanceNumber;

    public Activity(String title, String location, int classId, int state, int attendanceNumber) {
        this.title = title;
        this.location = location;
        this.classId = classId;
        this.state = state;
        this.attendanceNumber = attendanceNumber;
    }
}
