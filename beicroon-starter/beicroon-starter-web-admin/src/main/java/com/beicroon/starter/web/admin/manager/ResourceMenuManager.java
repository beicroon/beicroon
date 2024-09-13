package com.beicroon.starter.web.admin.manager;

import com.beicroon.starter.web.admin.convertor.ResourceMenuConvertor;
import com.beicroon.starter.web.admin.model.ResourceMenuModel;
import com.beicroon.starter.web.admin.repository.ResourceMenuRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResourceMenuManager {

     @Resource
     private ResourceMenuConvertor resourceMenuConvertor;

     @Resource
     private ResourceMenuRepository resourceMenuRepository;

     public void setParent(ResourceMenuModel menu, Long parentId) {
          ResourceMenuModel parent = this.resourceMenuRepository.getByIdOrError(parentId, "父级菜单不存在或以删除");

          this.setParent(menu, parent);
     }

     public void setParent(ResourceMenuModel menu, ResourceMenuModel parent) {
          menu.setParentId(parent.getId());
          menu.setParentCode(parent.getCode());
          menu.setParentName(parent.getName());
     }

}
