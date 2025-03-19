<script setup lang="ts">
import {computed, ref} from "vue";
import {BeicroonFile} from "@/utils/utils.file";

interface Props {
  modelValue?: BeicroonFile | Array<BeicroonFile> | null,
  label?: string,
  placeholder?: string,
  disabled?: boolean,
  required?: boolean,
  accept?: string,
  multiple?: boolean,
  preview?: boolean,
}

const props = withDefaults(defineProps<Props>(), {
  size: "normal",
  type: "text",
  accept: "*",
  multiple: false,
  preview: true,
});

const emits = defineEmits(["change"]);

const value = computed(() => {
  if (!props.modelValue) {
    return [];
  }

  return Array.isArray(props.modelValue) ? props.modelValue : [props.modelValue];
});

const fileList = ref<File[]>([]);

const inputEl = ref<HTMLInputElement>();

const clazz = computed(() => {
  const res = [];

  if (!props.label) {
    res.push("single");
  }

  if (props.required) {
    res.push("required");
  }

  return res;
});

const handleChange = (e: any) => {
  fileList.value = e.target.files;

  if (props.multiple) {
    emits("change", fileList.value);
  } else {
    emits("change", fileList.value.length > 0 ? fileList.value[0] : null);
  }
};

const getPreview = (file: BeicroonFile) => {
  const index = file.url.lastIndexOf(".");

  if (index > 0) {
    const ext = file.url.slice(index + 1).toLowerCase();

    if (["jpg", "jpeg", "png", "gif", "bmp"].includes(ext)) {
      return `<img src="${file.url}" alt="${file.name}" />`;
    }
  }

  return file.name;
};

const getFilePreview = (file: File) => {
  if (props.preview && file.type.startsWith('image/')) {
    const previewUrl = URL.createObjectURL(file);

    return `<img src="${previewUrl}" alt="${file.name}" />`;
  }

  return file.name;
};
</script>

<template>
  <div class="beicroon-file">
    <div class="beicroon-input input file" :class="clazz" ref="inputEl">
      <label class="label" v-if="label">{{ label }}</label>
      <div class="chooser">
        <span class="placeholder">{{ placeholder }}</span>
        <input
          class="file"
          type="file"
          @change="handleChange"
          :accept="accept"
          :multiple="multiple"
          :disabled="disabled"
        />
      </div>
    </div>
    <ul class="file-list" v-show="fileList.length > 0 || value.length > 0">
      <li v-for="img in value" v-html="getPreview(img)"></li>
      <li v-for="file in fileList" v-html="getFilePreview(file)"></li>
    </ul>
  </div>
</template>

<style lang="less">
.beicroon-file {
  width: 1068rem;

  .beicroon-input.file {
    width: 216rem;

    .chooser {
      width: 108rem;
      height: 32rem;
      position: relative;
    }

    .placeholder {
      z-index: 1;
      width: 100%;
      height: 100%;
      display: flex;
      position: relative;
      align-items: center;
      pointer-events: none;
      justify-content: center;
      color: var(--color-white);
      background-color: var(--color-primary);
    }

    .file {
      top: 50%;
      left: 50%;
      z-index: 0;
      opacity: 0;
      width: 100%;
      height: 100%;
      font-size: 0;
      cursor: default;
      position: absolute;
      transform: translate(-50%, -50%);
    }
  }

  .file-list {
    gap: 2rem;
    width: 100%;
    display: flex;
    padding: 2rem 0;

    li {
      display: flex;
      width: 180rem;
      align-items: center;
      border: 1rem solid var(--color-grey-light);
    }

    img {
      max-width: 100%;
    }
  }
}
</style>