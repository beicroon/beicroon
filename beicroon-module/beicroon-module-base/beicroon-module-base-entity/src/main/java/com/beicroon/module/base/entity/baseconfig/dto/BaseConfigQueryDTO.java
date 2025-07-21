package com.beicroon.module.base.entity.baseconfig.dto;

import com.beicroon.construct.annotation.KeywordsSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@KeywordsSearch("")
@EqualsAndHashCode(callSuper = true)
public class BaseConfigQueryDTO extends QueryDTO {

    @FieldSearch(value = "`config_key`")
    @Schema(name = "configKey", description = "键")
    private String configKey;

}
