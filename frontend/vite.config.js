// vite.config.js
import { defineConfig } from 'vite';

export default defineConfig(({ mode }) => ({
  base: mode === 'production' ? '/train-trip-generator-5000-plus/' : '/'
}));