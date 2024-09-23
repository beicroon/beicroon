<script setup lang="ts">
import {ref} from "vue";
import {List} from "@/utils/list.utils.ts";

type Props = {
  list: List<any, any>,
};

defineProps<Props>();

const hidden = ref(true);

function toggleChoosers() {
  hidden.value = !hidden.value;
}
</script>

<template>
  <div class="beicroon-paginator">
    <div class="size" @click="toggleChoosers">
      <h6 class="current">
        <span>{{ list.pageInfo.size }}</span>
        <span>条/页</span>
      </h6>
      <ul class="chooser" :class="{hidden: hidden}">
        <template v-for="size in list.choosers">
          <li @click="list.choose(size)">
            <span>{{ size }}</span>
            <span>条/页</span>
          </li>
        </template>
      </ul>
    </div>
    <ul class="page">
      <template v-for="page in list.pages">
        <li @click="list.setPage(page)" :class="{active: page == list.pageInfo.page}">{{ page }}</li>
      </template>
    </ul>
  </div>
</template>

<style lang="less">
.beicroon-paginator {
  gap: 52rem;
  display: flex;
  padding: 0 52rem;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;

  .size {
    z-index: 1;
    width: 120rem;
    height: 34rem;
    display: flex;
    position: relative;
    border-radius: 6rem;
    align-items: center;
    justify-content: center;
    border: 1rem solid var(--color-grey-light);
  }

  .current {
    gap: 6rem;
    width: 100%;
    height: 100%;
    display: flex;
    cursor: pointer;
    font-size: 14rem;
    font-weight: normal;
    align-items: center;
    justify-content: center;
  }

  .chooser {
    gap: 4rem;
    z-index: 1;
    left: -2rem;
    display: flex;
    padding: 4rem 0;
    position: absolute;
    border-radius: 6rem;
    flex-direction: column;
    width: calc(100% + 4rem);
    bottom: calc(100% + 4rem);
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-light);

    li {
      gap: 6rem;
      height: 28rem;
      display: flex;
      cursor: pointer;
      user-select: none;
      align-items: center;
      justify-content: center;
    }
  }

  .page {
    gap: 6rem;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;

    li {
      width: 52rem;
      height: 34rem;
      cursor: pointer;
      user-select: none;
      border-radius: 4rem;
      align-items: center;
      display: inline-flex;
      justify-content: center;
      background-color: var(--color-grey-lighter);

      &.active {
        cursor: text;
        color: var(--color-white);
        background-color: var(--color-primary);
      }
    }
  }
}
</style>