package com.tedu.sb2206.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
    public class Result implements Serializable {
        private String code;//http状态码
        private String msg;//信息描述
        private Object content;//具体的内容
    }

