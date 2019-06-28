package com.bsuir.cognispect.model.create;

import com.bsuir.cognispect.entity.enums.TestSessionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTestSessionModel {
    @NotNull
    private String[] routers;
    @NotBlank
    private String name;
    @NotNull
    private UUID creatorId;
    @NotNull
    private TestSessionStatusEnum status;
    @NotNull
    private List<UUID> studentIds;
    @NotNull
    private UUID testTemplateId;
}
