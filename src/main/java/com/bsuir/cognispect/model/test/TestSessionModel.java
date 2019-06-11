package com.bsuir.cognispect.model.test;

import com.bsuir.cognispect.entity.enums.TestSessionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestSessionModel {
    private UUID id;
    private TestSessionStatusEnum testSessionStatus;
    private String name;
    private String[] routers;
}
