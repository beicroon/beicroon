<script setup lang="ts">
import BeicroonListRow from "@/components/BeicroonListRow.vue";
</script>

<template>
  <table class="beicroon-list-table">
    <thead class="beicroon-list-head">
    <beicroon-list-row>
      <slot name="title"></slot>
    </beicroon-list-row>
    </thead>
    <tbody class="beicroon-list-body">
    <slot name="body"></slot>
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
  box-shadow: 0 0 1rem var(--color-grey-light) inset;

  .beicroon-list-head {
    z-index: 2;
    top: -8rem;
    position: sticky;

    .beicroon-list-row {
      background-color: var(--color-grey-lighter);

      .beicroon-list-button {
        background-color: inherit;
      }
    }
  }

  .beicroon-list-body {
    .beicroon-list-row {
      background-color: var(--color-white);

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
    padding: 18rem;
    box-shadow: 0 0 1rem var(--color-grey-light) inset;
  }

  .beicroon-list-button {
    z-index: 1;
    top: -8rem;
    right: 2rem;
    width: 180rem;
    position: sticky;

    &:before {
      top: 0;
      z-index: 2;
      left: -3rem;
      content: "";
      width: 3rem;
      height: 100%;
      display: block;
      position: absolute;
      background-color: var(--color-white);
    }

    &:after {
      top: 0;
      z-index: 2;
      content: "";
      width: 10rem;
      height: 100%;
      right: -10rem;
      display: block;
      position: absolute;
      background-color: var(--color-white);
    }
  }
}
</style>