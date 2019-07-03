package com.bsuir.cognispect.model.test;

import com.bsuir.cognispect.entity.enums.TestSessionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestSessionSimpleModel {
    @NotNull
    private UUID id;
    @NotNull
    private TestSessionStatusEnum testSessionStatus;
    @NotBlank
    private String name;
    @NotNull
    private String[] routers;
}
