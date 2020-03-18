package com.useage.jdk8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Customer {
    private Long id;
    private String name; //顾客名称
    private String data;
}
