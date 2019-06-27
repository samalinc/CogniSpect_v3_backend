package com.bsuir.cognispect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenerateTestVariantsModel {
    @NotNull
    List<UUID> userIds;
    @NotNull
    private UUID testTemplateId;
}
