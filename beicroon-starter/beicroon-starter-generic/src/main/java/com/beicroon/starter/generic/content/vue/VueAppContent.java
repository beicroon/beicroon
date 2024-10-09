package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueAppContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted} from "vue";
            import Create from "./{{filename}}Create.vue";
            import Detail from "./{{filename}}Detail.vue";
            import Update from "./{{filename}}Update.vue";
            import createBeicroonList from "@/utils/beicroon/list.utils.ts";
            import BeicroonList from "@/components/beicroon/BeicroonList.vue";
            import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
            import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
            import BeicroonListRow from "@/components/beicroon/BeicroonListRow.vue";
            import BeicroonListCell from "@/components/beicroon/BeicroonListCell.vue";
            import BeicroonDatetime from "@/components/beicroon/BeicroonDatetime.vue";
            import BeicroonListCellButton from "@/components/beicroon/BeicroonListCellButton.vue";
            import {page, remove, {{filename}}PageVO, {{filename}}QueryDTO} from "./{{vueFilename}}.http.utils.ts";

            const list = createBeicroonList<{{filename}}QueryDTO, {{filename}}PageVO>("{{comment}}", page);

            onMounted(list.resetSearch);
            </script>

            <template>
              <beicroon-list :list="list">
                <template #head-search>
                  {{search}}
                </template>
                <template #head-button>
                  <beicroon-button class="block primary" label="重置" @click="list.handleReset"></beicroon-button>
                  <beicroon-button class="block primary" label="查询" :loading="list.loading" @click="list.resetSearch"></beicroon-button>
                  <beicroon-button class="block warning" label="新增" @click="list.handleCreate(Create)"></beicroon-button>
                </template>
                <template #table-title>
                  {{tableHead}}
                  <beicroon-list-cell-button>操作</beicroon-list-cell-button>
                </template>
                <template #table-body>
                  <beicroon-list-row v-for="item in list.data">
                    {{tableBody}}
                    <beicroon-list-cell-button>
                      <beicroon-button class="primary" label="查看" @click="list.handleDetail(item, Detail)"></beicroon-button>
                      <beicroon-button class="warning" label="编辑" @click="list.handleUpdate(item, Update)"></beicroon-button>
                      <beicroon-button class="danger" label="删除" @click="list.handleRemove(item, remove)"></beicroon-button>
                    </beicroon-list-cell-button>
                  </beicroon-list-row>
                </template>
                <template #more-search>
                  <beicroon-datetime class="column" label="创建时间" v-model="list.params.createdAt"></beicroon-datetime>
                  {{moreSearch}}
                </template>
              </beicroon-list>
            </template>

            <style scoped lang="less">
            </style>
            """;

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{filename}}", table.getFilename())
                .replace("{{vueFilename}}", table.getVueFilename())
                .replace("{{comment}}", table.getComment())
                .replace("{{search}}", table.getVueSearchContent())
                .replace("{{moreSearch}}", table.getVueMoreSearchContent())
                .replace("{{tableHead}}", table.getVueTableHeadContent())
                .replace("{{tableBody}}", table.getVueTableBodyContent());
    }

}
