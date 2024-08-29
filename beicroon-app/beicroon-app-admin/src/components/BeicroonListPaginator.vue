<script setup lang="ts">
import {PageInfo} from "@/http.ts";
import {computed, ref, watch} from "vue";

type Props = {
  modelValue: PageInfo,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:modelValue"]);

const page = computed({
  get: () => props.modelValue.page || 1,
  set: (val: number) => emits("update:modelValue", {...props.modelValue, page: val}),
});

const size = computed({
  get: () => props.modelValue.size || 15,
  set: (val: number) => emits("update:modelValue", {...props.modelValue, size: val}),
});

const total = computed({
  get: () => props.modelValue.total || 0,
  set: (val: number) => emits("update:modelValue", {...props.modelValue, total: val}),
});

watch(
  () => {
    return {page: page.value, size: size.value, total: total.value};
  },
  () => {
    setPages();
  },
);

const pages = ref([1]);

const chooserShow = ref(false);

const choosers = ref([15, 30, 50, 100]);

function setPage(num: number) {
  page.value = num;
}

function choose(num: number) {
  if (size.value == num) {
    return;
  }

  page.value = 1;
  size.value = num;
}

function toggleChoosers() {
  chooserShow.value = !chooserShow.value;
}

function getPages(s: number, e: number) {
  const ps = [];

  for (let i = s; i <= e; i++) {
    ps.push(i);
  }

  return ps;
}

function setPages() {
  const t = total.value > 0 ? Math.ceil(total.value / size.value) : 1;

  if (t <= 11) {
    pages.value = getPages(1, t);
  } else if (page.value <= 6) {
    pages.value = getPages(1, 11);
  } else if (page.value + 5 >= t) {
    pages.value = getPages(t - 10, 11);
  } else {
    pages.value = getPages(page.value - 5, page.value + 5);
  }
}
</script>

<template>
  <div class="beicroon-paginator">
    <div class="size" @click="toggleChoosers">
      <h6 class="current">
        <span>{{ size }}</span>
        <span>条/页</span>
      </h6>
      <ul class="chooser" :class="{show: chooserShow}">
        <template v-for="num in choosers">
          <li @click="choose(num)">
            <span>{{ num }}</span>
            <span>条/页</span>
          </li>
        </template>
      </ul>
    </div>
    <ul class="page">
      <template v-for="p in pages">
        <li @click="setPage(p)" :class="{active: p == page}">{{ p }}</li>
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
    z-index: 1;
    left: -2rem;
    display: none;
    padding: 4rem 0;
    position: absolute;
    border-radius: 6rem;
    width: calc(100% + 4rem);
    bottom: calc(100% + 4rem);
    background-color: var(--color-white);
    border: 1rem solid var(--color-grey-light);

    &.show {
      gap: 4rem;
      display: flex;
      flex-direction: column;
    }

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
        color: var(--color-white);
        background-color: var(--color-primary);
      }
    }
  }
}
</style>