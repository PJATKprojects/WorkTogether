import type { Config } from 'tailwindcss'


const config: Config = {
darkMode: 'class',
content: [
'./src/pages/**/*.{js,ts,jsx,tsx,mdx}',
'./src/components/**/*.{js,ts,jsx,tsx,mdx}',
'./src/app/**/*.{js,ts,jsx,tsx,mdx}',
],
theme: {
extend: {
colors: {
bg: {
DEFAULT: '#0b0e13',
soft: '#0f141b',
card: '#121823',
},
fg: {
DEFAULT: '#d6e3ff',
muted: '#92a1b8',
},
brand: {
DEFAULT: '#5b8cff',
soft: '#2a3f78',
},
},
boxShadow: {
soft: '0 10px 30px rgba(0,0,0,.35)',
},
borderRadius: {
xl2: '1.25rem',
}
},
},
plugins: [],
}
export default config