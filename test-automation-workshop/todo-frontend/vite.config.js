import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  test: {
    globals: true,
    environment: 'jsdom', // simulates a browser environment for Vue components
    setupFiles: './test/setup.js', // optional setup file for global configurations
    coverage: {
      reporter: ['text', 'html'],
      exclude: ['node_modules/', 'test/'], // coverage settings
    },
  }
})
