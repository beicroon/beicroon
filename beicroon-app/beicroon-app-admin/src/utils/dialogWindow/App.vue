<script setup lang="ts">

const props = defineProps({
  is: {
    required: true,
  },
  title: {
    type: String,
    required: true,
  },
  binds: {
    type: Object,
    required: true,
  },
  events: {
    type: Object,
    required: true,
  },
  complete: {
    type: Function,
    required: true,
  },
});

async function doHide() {
  await props.complete();
}
</script>

<template>
  <div class="dialog-window" @click.stop>
    <div class="title">{{ title }}</div>
    <div class="content">
      <component :is="is" @hide="doHide" v-bind="binds" v-on="events"></component>
    </div>
  </div>
</template>

<style scoped lang="less">
@import "@/less/index";

.dialog-window {
  display: flex;
  flex-direction: column;
}

.title {
  color: @white;
  display: flex;
  height: 60rem;
  padding: 0 72rem;
  font-size: 16rem;
  user-select: none;
  font-weight: bold;
  width: fit-content;
  align-items: center;
  background: linear-gradient(90deg, #287bf1 0%, #65a5ff 100%);
}

.content {
  flex: 1;
  border-top: 1rem solid @light-grey;
}
</style>