<script setup lang="ts">
import {onMounted, onUnmounted, ref, watch} from "vue";

interface Props {
  text: string,
  width: number,
  height: number,
}

const props = defineProps<Props>();

const canvas = ref<HTMLCanvasElement | null>(null);

let animationFrame: number;

const drawLogo = (ctx: CanvasRenderingContext2D, progress: number) => {
  ctx.clearRect(0, 0, props.width, props.height);

  const fontSize = Math.min(props.height / 2, props.width / (props.text.length * 0.7));

  ctx.font = `${fontSize}rem "Brush Script MT", cursive`;
  ctx.fillStyle = "#ffffff";
  ctx.strokeStyle = "#ffffff";
  ctx.lineWidth = fontSize / 30;

  const textWidth = ctx.measureText(props.text).width;
  const textHeight = fontSize;

  const startX = (props.width - textWidth) / 2;
  const startY = (props.height + textHeight * 0.6) / 2;

  for (let i = 0; i < props.text.length; i++) {
    const letterProgress = Math.max(0, Math.min(1, (progress - i * 0.1) * 5));

    ctx.save();
    ctx.translate(startX + ctx.measureText(props.text.substring(0, i)).width, startY);
    ctx.rotate(Math.sin(progress * 2 + i * 0.5) * 0.05);
    ctx.globalAlpha = letterProgress;
    ctx.fillText(props.text[i], 0, 0);
    ctx.strokeText(props.text[i], 0, 0);
    ctx.restore();
  }

  ctx.shadowColor = "#3498db";
  ctx.shadowBlur = (fontSize / 6) * Math.sin(progress * 2) + fontSize / 12;
  ctx.globalAlpha = 0.5;

  ctx.fillText(props.text, startX, startY);
};

const animate = (ctx: CanvasRenderingContext2D, startTime: number) => {
  const elapsed = (Date.now() - startTime) / 1000;
  const progress = Math.min(1, elapsed / 2);

  drawLogo(ctx, progress);

  if (progress < 1) {
    animationFrame = requestAnimationFrame(() => animate(ctx, startTime));
  }
};

let timer: any = 0;

const initAnimation = () => {
  clearTimeout(timer);

  if (canvas.value) {
    const ctx = canvas.value.getContext("2d");

    if (ctx) {
      cancelAnimationFrame(animationFrame);

      animate(ctx, Date.now());
    }
  }

  timer = setTimeout(initAnimation, 3000);
};

onMounted(initAnimation);

onUnmounted(() => cancelAnimationFrame(animationFrame));

watch([() => props.text, () => props.width, () => props.height], initAnimation);
</script>

<template>
  <div class="logo" :style="{ width: `${width}rem`, height: `${height}rem` }">
    <canvas ref="canvas" :width="width" :height="height"></canvas>
  </div>
</template>

<style lang="less">
.logo {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: transparent;
}
</style>