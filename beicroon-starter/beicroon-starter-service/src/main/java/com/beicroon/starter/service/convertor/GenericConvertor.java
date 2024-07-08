package com.beicroon.starter.service.convertor;

import com.beicroon.construct.entity.CreateDTO;
import com.beicroon.construct.entity.DisableVO;
import com.beicroon.construct.entity.GenericVO;
import com.beicroon.construct.entity.UpdateDTO;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.starter.mysql.model.DisableModel;
import com.beicroon.starter.mysql.model.GenericModel;
import com.beicroon.starter.service.helper.ConvertorHelper;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

public interface GenericConvertor<M extends GenericModel, C extends CreateDTO, U extends UpdateDTO, B extends GenericVO, D extends GenericVO, P extends GenericVO> extends BaseConvertor<M, C, B> {

    P toPageVO(M m);

    D toDetailVO(M m);

    M toEntity(U u);

    @AfterMapping
    default void fillModifier(@MappingTarget M m, U u) {
        ConvertorHelper.fillModifier(m);
    }

    @AfterMapping
    default <V extends DisableVO, T extends DisableModel> void fillDisable(@MappingTarget V vo, T model) {
        BooleanEnums flag = BooleanEnums.boolOf(model.getDisabledAt() != null);

        vo.setDisableFlag(flag.getCode());
        vo.setDisableDesc(BooleanEnums.isTrue(flag.getCode()) ? "已禁用" : "已启用");
    }

}
