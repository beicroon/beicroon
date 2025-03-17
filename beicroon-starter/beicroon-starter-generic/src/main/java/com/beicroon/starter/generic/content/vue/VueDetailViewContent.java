package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueDetailViewContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import BeicroonForm from "@c/BeicroonForm.vue";
            import BeicroonInput from "@c/BeicroonInput.vue";
            import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
            import {DetailVO} from "@m/{{pathName}}/script/module.ts";
            
            interface Props {
              data?: DetailVO | null,
            }
            
            defineProps<Props>();
            </script>
            
            <template>
              <b-form :loading="!data" disabled>
                <template v-if="data">
                  <b-form-group label="基础信息">{{detailContent}}
                  </b-form-group>
                </template>
                <template v-slot:button>
                  <slot name="button"></slot>
                </template>
              </b-form>
            </template>
            
            <style lang="less">
            
            </style>
            """;

    private VueDetailViewContent() {

    }

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{pathName}}", table.getPathName())
                .replace("{{urlName}}", table.getUrlName())
                .replace("{{detailContent}}", table.getVueDetailContent())
                ;
    }

}
