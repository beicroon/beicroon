<script setup lang="ts">
import {provide, ref} from 'vue';

declare type Message = {
  content: string,
  error: boolean,
}

const messages = ref<Message[]>([]);

function addMessage(content: string, error = false) {
  messages.value.push({content, error});

  setTimeout(() => messages.value.shift(), 3000);
}

provide('toast', addMessage);
</script>

<template>
  <div class="toast-container" v-if="messages.length">
    <transition-group name="toast" tag="div">
      <div v-for="(message, index) in messages" :key="index" class="toast-message" :class="{error: message.error}">
        {{ message.content }}
      </div>
    </transition-group>
  </div>
</template>

<style>
.toast-container {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
  display: flex;
  flex-direction: column;
}

.toast-message {
  background-color: #333;
  color: white;
  padding: 10px 20px;
  margin-bottom: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  opacity: 0.9;
  transition: all 0.5s ease-in-out;
}

.toast-message.error {
  background-color: #e74c3c;
}

.toast-enter-active, .toast-leave-active {
  transition: opacity 0.5s, transform 0.5s;
}

.toast-enter-from, .toast-leave-to {
  opacity: 0;
  transform: translateY(-30px);
}
</style>