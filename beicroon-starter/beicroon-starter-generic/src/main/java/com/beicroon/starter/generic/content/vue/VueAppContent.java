package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueAppContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted} from "vue";
            import dialogUtils from "@u/dialog.utils.ts";
            import overlayUtils from "@u/overlay.utils.ts";
            import newModule from "@u/module-app.utils.ts";
            import BeicroonInput from "@c/BeicroonInput.vue";
            import BeicroonModule from "@c/BeicroonModule.vue";
            import BeicroonButton from "@c/BeicroonButton.vue";
            import BeicroonTableField from "@c/BeicroonTableField.vue";
            import BeicroonTableGroup from "@c/BeicroonTableGroup.vue";
            import BeicroonTableColumn from "@c/BeicroonTableColumn.vue";
            import {{className}}Create from "@m/{{pathName}}/{{className}}Create.vue";
            import {{className}}Update from "@m/{{pathName}}/{{className}}Update.vue";
            import {{className}}Detail from "@m/{{pathName}}/{{className}}Detail.vue";
            import config, {PageVO, QueryDTO} from "@m/{{pathName}}/script/module.ts";
            
            const module = newModule<QueryDTO, PageVO>(config);
            
            const handleCreate = () => {
              overlayUtils({
                title: "新增{{tableComment}}",
                component: {{className}}Create,
                confirm: module.page.request,
              });
            };
            
            const handleUpdate = (item: any) => {
              overlayUtils({
                title: "编辑{{tableComment}}",
                component: {{className}}Update,
                props: {id: item.id},
                confirm: module.page.request,
              });
            };
            
            const handleDetail = (item: any) => {
              overlayUtils({
                title: "{{tableComment}}详情",
                component: {{className}}Detail,
                props: {id: item.id},
              });
            };
            
            const handleRemove = (item: any) => {
              dialogUtils({
                title: "删除确认",
                message: `确定删除该{{tableComment}}吗？`,
                confirm: async () => await module.remove.request(item.id),
                succeed: module.page.request,
              });
            };
            
            onMounted(module.page.request);
            </script>
            
            <template>
              <beicroon-module :module="module">
                <template v-slot:search>{{searchContent}}
                </template>
                <template v-slot:action-left>
                  <beicroon-button level="warning" label="新增" @click="handleCreate"/>
                </template>
                <template v-slot:action-right>
                  <beicroon-button label="重置" @click="module.clear" level="warning"/>
                  <beicroon-button label="查询" :loading="module.page.loading" @click="module.page.request()"/>
                </template>{{tableContent}}
                <beicroon-table-group label="创建/更新" width="320rem">
                  <template v-slot="{item}">
                    <beicroon-table-field label="创建人" :content="item.creatorName"/>
                    <beicroon-table-field label="创建时间" :content="item.createdAt"/>
                    <beicroon-table-field label="更新人" :content="item.modifierName"/>
                    <beicroon-table-field label="更新时间" :content="item.modifiedAt"/>
                  </template>
                </beicroon-table-group>
                <beicroon-table-group label="操作" direction="row" frozen-right>
                  <template v-slot="{item}">
                    <beicroon-button size="small" level="primary" label="详情" @click="handleDetail(item)"/>
                    <beicroon-button size="small" level="warning" label="编辑" @click="handleUpdate(item)"/>
                    <beicroon-button size="small" level="danger" label="删除" @click="handleRemove(item)"/>
                  </template>
                </beicroon-table-group>
              </beicroon-module>
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
