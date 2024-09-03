<script setup lang="ts">
import {List} from "@/list.ts";
import {computed, ref} from "vue";
import BeicroonListRow from "@/components/BeicroonListRow.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";

type Props = {
  list: List<any, any>,
};

defineProps<Props>();

const listTable = ref();

const colspan = computed(() => {
  return listTable.value ? listTable.value.querySelectorAll("thead td").length : 0;
});
</script>

<template>
  <div class="beicroon-list-table">
    <table class="beicroon-list-table" :class="{loading: list.loading}" ref="listTable">
      <thead class="beicroon-list-head">
      <beicroon-list-row>
        <slot name="title"></slot>
      </beicroon-list-row>
      </thead>
      <tbody class="beicroon-list-body" v-if="list.loading">
      <beicroon-list-row>
        <td :colspan="colspan">
          <beicroon-loading fill="#e6e6e6" width="100" height="100"></beicroon-loading>
        </td>
      </beicroon-list-row>
      </tbody>
      <tbody class="beicroon-list-body" v-else>
      <slot name="body"></slot>
      </tbody>
    </table>
  </div>
</template>

<style lang="less">
:root {
  --beicroon-height-list-row: 72rem;
}

.beicroon-list-table {
  z-index: 1;
  width: 100%;
  height: 100%;
  overflow: auto;
  position: relative;
  scrollbar-width: none;

  &::-webkit-scrollbar {
    width: 0;
    height: 0;
  }

  table {
    min-width: 100%;
    empty-cells: show;
    width: fit-content;
    text-align: center;
    table-layout: fixed;
    border-spacing: 2rem;
    border-collapse: separate;

    &.loading {
      height: 100%;

      .beicroon-list-body {
        height: calc(100% - var(--beicroon-height-list-row));
      }
    }
  }

  .beicroon-list-head {
    top: 2rem;
    z-index: 2;
    position: sticky;
    height: var(--beicroon-height-list-row);

    .beicroon-list-row {
      height: inherit;
      background-color: var(--color-grey-lighter);

      td {
        height: inherit;
      }

      .beicroon-list-cell {
        &:after {
          left: 0;
          top: -2rem;
          z-index: 2;
          content: "";
          width: 100%;
          height: 2rem;
          display: block;
          position: absolute;
          background-color: var(--color-white);
        }
      }

      .beicroon-list-button {
        background-color: inherit;
      }
    }
  }

  .beicroon-list-body {
    .beicroon-list-row {
      background-color: var(--color-white);
      height: var(--beicroon-height-list-row);

      td {
        height: inherit;
      }

      .beicroon-list-button {
        background-color: inherit;
      }
    }

    .beicroon-list-row:nth-child(even) {
      background-color: var(--color-grey-lightest);

      .beicroon-list-button {
        background-color: inherit;
      }
    }
  }

  .beicroon-list-cell {
    height: 100%;
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
  }

  .beicroon-list-button {
    z-index: 1;
    right: 2rem;
    width: 180rem;
    position: sticky;

    &:before {
      top: 0;
      z-index: 2;
      left: -2rem;
      content: "";
      width: 2rem;
      height: 100%;
      display: block;
      position: absolute;
      background-color: var(--color-white);
    }

    &:after {
      top: 0;
      z-index: 2;
      content: "";
      width: 2rem;
      height: 100%;
      right: -2rem;
      display: block;
      position: absolute;
      background-color: var(--color-white);
    }

    .beicroon-list-cell {
      flex-direction: row;
    }
  }
}
</style>