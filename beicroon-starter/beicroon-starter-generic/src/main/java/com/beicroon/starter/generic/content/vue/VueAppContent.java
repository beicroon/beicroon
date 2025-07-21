package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueAppContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted} from "vue";
            import {dialog, moduleApp, overlay} from "beicroon-app-vue/utils";
            import config, {PageVO, QueryDTO} from "@m/{{pathName}}/script/module.ts";
            import {{className}}Create from "@m/{{pathName}}/{{className}}Create.vue";
            import {{className}}Update from "@m/{{pathName}}/{{className}}Update.vue";
            import {{className}}Detail from "@m/{{pathName}}/{{className}}Detail.vue";
            import {BButton, BInput, BModule, BTableColumn, BTableField, BTableGroup} from "beicroon-app-vue/components";
            
            const module = moduleApp<QueryDTO, PageVO>(config);
            
            const handleCreate = () => {
              overlay({
                title: "新增{{tableComment}}",
                component: {{className}}Create,
                confirm: module.page.request,
              });
            };
            
            const handleUpdate = (item: PageVO) => {
              overlay({
                title: "编辑{{tableComment}}",
                component: {{className}}Update,
                props: {id: item.id},
                confirm: module.page.request,
              });
            };
            
            const handleDetail = (item: PageVO) => {
              overlay({
                title: "{{tableComment}}详情",
                component: {{className}}Detail,
                props: {id: item.id},
              });
            };
            
            const handleRemove = (item: PageVO) => {
              dialog({
                title: "删除确认",
                message: `确定删除该{{tableComment}}吗？`,
                confirm: async () => await module.remove.request(item.id),
                succeed: module.page.request,
              });
            };
            
            onMounted(module.page.request);
            </script>
            
            <template>
              <b-module :module="module">
                <template v-slot:search>{{searchContent}}
                </template>
                <template v-slot:action-left>
                  <b-button level="warning" label="新增" @click="handleCreate"/>
                </template>
                <template v-slot:action-right>
                  <b-button label="重置" @click="module.clear" level="warning"/>
                  <b-button label="查询" :loading="module.page.loading" @click="module.page.request()"/>
                </template>{{tableContent}}
                <b-table-group label="创建/更新" width="320rem">
                  <template v-slot="{item}">
                    <b-table-field label="创建人" :content="item.creatorName"/>
                    <b-table-field label="更新人" :content="item.modifierName"/>
                    <b-table-field label="创建时间" :content="item.createdAt"/>
                    <b-table-field label="更新时间" :content="item.modifiedAt"/>
                  </template>
                </b-table-group>
                <b-table-group label="操作" direction="row" frozen-right>
                  <template v-slot="{item}">
                    <b-button size="small" level="primary" label="详情" @click="handleDetail(item)"/>
                    <b-button size="small" level="warning" label="编辑" @click="handleUpdate(item)"/>
                    <b-button size="small" level="danger" label="删除" @click="handleRemove(item)"/>
                  </template>
                </b-table-group>
              </b-module>
            </template>
            
            <style lang="less">
            
            </style>
            """;

    private VueAppContent() {

    }

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{className}}", table.getClassName())
                .replace("{{pathName}}", table.getPathName())
                .replace("{{urlName}}", table.getUrlName())
                .replace("{{tableComment}}", table.getTableComment())
                .replace("{{searchContent}}", table.getVueSearchContent())
                .replace("{{tableContent}}", table.getVueTableContent())
                ;
    }

}
