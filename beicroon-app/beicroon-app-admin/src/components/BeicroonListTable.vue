<script setup lang="ts">
import {defineProps, onMounted, provide, ref} from "vue";

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
    <thead>
    <tr>
      <slot></slot>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(row, rowIndex) in props.data" :key="rowIndex">
      <td v-for="(column, colIndex) in columns" :key="colIndex">
        <div class="beicroon-list-field">{{ row[column.field] }}</div>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<style lang="less">
.beicroon-list-table {
  min-width: 100%;

  .beicroon-list-title {
    display: flex;
    padding: 8rem 18rem;
    flex-direction: column;
    box-shadow: 0 0 1rem var(--color-grey);
  }

  .beicroon-list-field {
    display: flex;
    text-align: center;
    padding: 8rem 18rem;
    flex-direction: column;
    box-shadow: 0 0 1rem var(--color-grey);
  }
}
</style>