package com.hugo83.openapi_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AreaCode {

    //openApiId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long openApiId;

    //rnum
    private Long rNum;

    //code
    private String code;

    private String name;
}
