import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import 'vitest'

export default defineConfig({
  server: {
    port: 3000
  },
  plugins: [react()],
  test: {
    globals: true,
    environment: 'jsdom',
    setupFiles: './src/setupTests.ts'
  }
})
