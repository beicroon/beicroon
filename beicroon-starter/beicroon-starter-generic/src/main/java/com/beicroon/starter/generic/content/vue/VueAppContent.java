package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueAppContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {PageInfo} from "@/https";
            import toast from "@/utils/toast";
            import {onMounted, ref} from "vue";
            import dialog from "@/utils/dialog";
            import Create from "./{{filename}}Create.vue";
            import Detail from "./{{filename}}Detail.vue";
            import Update from "./{{filename}}Update.vue";
            import dialogWindow from "@/utils/dialogWindow";
            import ElButton from "@/components/elements/ElButton.vue";
            import ElSearch from "@/components/elements/ElSearch.vue";
            import {{{filename}}PageVO as VO, {{filename}}QueryDTO as DTO, page, remove} from "./{{filename}}.http.ts";
            
            const query = ref<DTO>({} as DTO);
            
            const paginator = ref<PageInfo>();
            
            const data = ref<Array<VO>>();
            
            const loading = ref(false);
            
            async function doLoad() {
              loading.value = true;
            
              data.value = [];
            
              const res = await page(query.value);
            
              data.value = res.data;
            
              paginator.value = res.page;
            
              loading.value = false;
            }
            
            async function doReset() {
              query.value = {} as DTO;
            }
            
            async function showCreate() {
              await dialogWindow("新增{{comment}}", Create, {}, {
                reload: doLoad,
              });
            }
            
            async function showDetail(item: VO) {
              await dialogWindow("{{comment}}详情", Detail, {id: item.id}, {
                reload: doLoad,
              });
            }
            
            async function showUpdate(item: VO) {
              await dialogWindow("编辑{{comment}}", Update, {id: item.id}, {
                reload: doLoad,
              });
            }
            
            async function showRemove(item: VO) {
              await dialog(
                  "是否删除该{{comment}}数据？删除后数据不可恢复，请谨慎操作！",
                  () => remove(item.id),
                  async () => toast("删除成功").then(doLoad)
              );
            }
            
            onMounted(doLoad);
            </script>
            
            <template>
              <div class="list">
                <div class="list-head flex-row">
                  <div class="head-search">
                    {{vueAppSearchContent}}
                  </div>
                  <div class="head-action">
                    <el-button class="head-button" @click="doLoad" :loading="loading">查询</el-button>
                    <el-button class="head-button" @click="doReset">重置</el-button>
                    <el-button class="head-button" @click="showCreate">新增</el-button>
                  </div>
                </div>
                <div class="list-view">
                  <table class="list-table">
                    <thead class="table-head">
                    <tr class="table-row">
                      {{vueAppTableHeadContent}}
                      <th>
                        <div class="table-cell">创建时间</div>
                      </th>
                      <th>
                        <div class="table-cell">创建人昵称</div>
                      </th>
                      <th>
                        <div class="table-cell">更新时间</div>
                      </th>
                      <th>
                        <div class="table-cell">更新人昵称</div>
                      </th>
                      <th class="table-action">
                        <div class="table-cell table-button">操作</div>
                      </th>
                    </tr>
                    </thead>
                    <tbody class="table-body">
                    <tr class="table-row" v-for="item in data">
                      {{vueAppTableBodyContent}}
                      <td>
                        <div class="table-cell">{{ item.createdAt }}</div>
                      </td>
                      <td>
                        <div class="table-cell">{{ item.creatorName }}</div>
                      </td>
                      <td>
                        <div class="table-cell">{{ item.modifiedAt }}</div>
                      </td>
                      <td>
                        <div class="table-cell">{{ item.modifierName }}</div>
                      </td>
                      <td class="table-action">
                        <div class="table-cell table-button">
                          <el-button class="primary" @click="showDetail(item)">查看</el-button>
                          <el-button class="warning" @click="showUpdate(item)">编辑</el-button>
                          <el-button class="dangerous" @click="showRemove(item)">删除</el-button>
                        </div>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <div class="list-foot"></div>
              </div>
            </template>
            
            <style scoped lang="less">
            </style>
            """;

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{filename}}", table.getFilename())
                .replace("{{comment}}", table.getComment())
                .replace("{{vueAppSearchContent}}", table.getVueAppSearchContent().trim())
                .replace("{{vueAppTableHeadContent}}", table.getVueAppTableHeadContent().trim())
                .replace("{{vueAppTableBodyContent}}", table.getVueAppTableBodyContent().trim());
    }

}
