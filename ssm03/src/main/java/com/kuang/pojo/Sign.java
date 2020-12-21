package com.kuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sign {
    private Integer id;
    private Integer activityId;
    private Integer studentId;
    private String device;

    public Sign(Integer activityId, Integer studentId, String device) {
        this.activityId = activityId;
        this.studentId = studentId;
        this.device = device;
    }
}
