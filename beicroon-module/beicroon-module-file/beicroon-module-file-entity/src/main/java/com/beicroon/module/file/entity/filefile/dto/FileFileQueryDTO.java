package com.beicroon.module.file.entity.filefile.dto;

import com.beicroon.construct.annotation.KeywordsSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@KeywordsSearch("")
@EqualsAndHashCode(callSuper = true)
public class FileFileQueryDTO extends QueryDTO {

    @FieldSearch(value = "`file_name`", like = true)
    @Schema(name = "fileName", description = "文件名称")
    private String fileName;

}
