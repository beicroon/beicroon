<script setup lang="ts">
import {defineProps, onMounted, provide, ref} from "vue";
import BeicroonButton from "@/components/BeicroonButton.vue";

type Column = {
  label: string,
  field: string,
};

type Props = {
  data: Array<any>,
};

const props = defineProps<Props>();

const columns = ref<Array<Column>>([]);

provide("registerListTableColumn", columns);

onMounted(() => {

});
</script>

<template>
  <table class="beicroon-list-table">
    <thead class="beicroon-list-head">
    <tr class="beicroon-list-row">
      <slot></slot>
      <th class="beicroon-list-button">
        <div class="beicroon-list-field">操作</div>
      </th>
    </tr>
    </thead>
    <tbody class="beicroon-list-body">
    <tr class="beicroon-list-row" v-for="(row, rowIndex) in props.data" :key="rowIndex">
      <td v-for="(column, colIndex) in columns" :key="colIndex">
        <div class="beicroon-list-field">{{ row[column.field] }}</div>
      </td>
      <td class="beicroon-list-button">
        <div class="beicroon-list-field">
          <beicroon-button class="primary" label="查看"></beicroon-button>
          <beicroon-button class="warning" label="编辑"></beicroon-button>
          <beicroon-button class="danger" label="删除"></beicroon-button>
        </div>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<style lang="less">
.beicroon-list-table {
  min-width: 100%;
  empty-cells: show;
  width: fit-content;
  text-align: center;
  table-layout: fixed;
  box-shadow: 0 0 1rem var(--color-grey);

  .beicroon-list-head {
    z-index: 2;
    top: -8rem;
    position: sticky;

    .beicroon-list-row {
      background-color: var(--color-grey-lightest);

      .beicroon-list-button {
        background-color: inherit;
      }
    }
  }

  .beicroon-list-body {
    .beicroon-list-row {
      background-color: var(--color-white);

      .beicroon-list-button{
        background-color: inherit;
      }
    }

    .beicroon-list-row:nth-child(even) {
      background-color: var(--color-grey-lightest);

      .beicroon-list-button{
        background-color: inherit;
      }
    }
  }

  .beicroon-list-field {
    padding: 18rem;
    box-shadow: 0 0 1rem var(--color-grey);
  }

  .beicroon-list-button {
    z-index: 1;
    top: -8rem;
    right: -8rem;
    width: 180rem;
    position: sticky;
  }
}
</style>