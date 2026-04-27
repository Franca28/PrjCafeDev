import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      // Tudo o que começar com /cliente vai para o Java
      '/cliente': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
      // Tudo o que começar com /pedido vai para o Java
      '/pedido': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      }
    }
  }
})
