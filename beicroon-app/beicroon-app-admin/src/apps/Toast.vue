<script setup lang="ts">
import {ref} from 'vue';

declare type Message = {
  content: string,
  error: boolean,
}

const messages = ref<Message[]>([]);
</script>

<template>
  <div class="toast">
    <template v-for="message in messages">
      <p :class="{error: message.error}">{{ message.content }}</p>
    </template>
  </div>
</template>