package com.beicroon.starter.mysql.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beicroon.starter.mysql.model.GenericModel;

public interface ISuperRepository<T extends GenericModel> extends IService<T> {

}
