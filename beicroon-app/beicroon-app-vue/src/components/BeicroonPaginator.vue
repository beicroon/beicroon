<script setup lang="ts">
import {BeicroonModulePaginator} from "@u/module-app.utils";

interface Props {
  paginator: BeicroonModulePaginator,
}

defineProps<Props>();
</script>

<template>
  <div class="beicroon-pagination" ref="pagination">
    <div class="beicroon-pagination-info">
      <div class="beicroon-pagination-size">共{{ paginator.totalSize }}条</div>
      <div class="beicroon-pagination-per">
        <span>{{ paginator.size }}条/页</span>
        <ul>
          <li v-for="size in paginator.sizes" @click="paginator.setSize(size)">{{ size }}条/页</li>
        </ul>
      </div>
      <div class="beicroon-pagination-page">共{{ paginator.totalPage }}页</div>
    </div>
    <div class="beicroon-pagination-button">
      <button @click="paginator.prevPage">上一页</button>
      <template v-for="page in paginator.pages">
        <button @click="paginator.gotoPage(page)" :class="{active: page === paginator.page}">{{ page }}</button>
      </template>
      <button @click="paginator.nextPage">下一页</button>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-pagination {
  width: 100%;
  height: 100%;
  display: flex;
  padding: 0 8rem;
  align-items: center;
  justify-content: space-between;
}

.beicroon-pagination-info {
  height: 100%;
  display: flex;
  width: 380rem;
  align-items: center;
  letter-spacing: 2rem;
  justify-content: space-between;
}

.beicroon-pagination-size {
  width: 130rem;
  height: 34rem;
  display: flex;
  border-radius: 4rem;
  align-items: center;
  justify-content: center;
  border: 1rem dashed var(--color-grey-light);
}

.beicroon-pagination-per {
  width: 80rem;
  height: 34rem;
  display: flex;
  position: relative;
  border-radius: 4rem;
  align-items: center;
  justify-content: center;
  border: 1rem dashed var(--color-grey-light);

  &:hover {
    ul {
      display: flex;
    }
  }

  ul {
    gap: 6rem;
    left: 50%;
    z-index: 8;
    bottom: 32rem;
    display: none;
    padding: 6rem 0;
    position: absolute;
    border-radius: 4rem;
    flex-direction: column;
    transform: translateX(-50%);
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-light);
  }

  li {
    width: 120rem;
    padding: 6rem 0;
    cursor: pointer;
    text-align: center;
    background-color: var(--color-white-light);

    &:hover {
      color: var(--color-white);
      background-color: var(--color-primary);
    }
  }
}

.beicroon-pagination-page {
  width: 80rem;
  height: 34rem;
  display: flex;
  border-radius: 4rem;
  align-items: center;
  justify-content: center;
  border: 1rem dashed var(--color-grey-light);
}

.beicroon-pagination-button {
  flex: 1;
  gap: 3rem;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: flex-end;

  button {
    height: 34rem;
    padding: 0 18rem;
    min-width: 52rem;
    border-radius: 4rem;
    background-color: var(--color-grey-lighter);

    &.active {
      cursor: default;
      color: var(--color-white);
      background-color: var(--color-primary);
    }
  }
}
</style>