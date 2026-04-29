const THEMES = [
    "theme-yellow",
    "theme-purple",
    "theme-cyan-dark",
    "theme-green-dark",
    "theme-blue-light"
]

function applyTheme(theme) {
    document.documentElement.classList.remove(...THEMES)
    document.documentElement.classList.add(theme)
}

function setTheme(theme) {
    applyTheme(theme)
    localStorage.setItem("theme", theme)
}

function loadTheme() {
    const savedTheme = localStorage.getItem("theme") || "theme-yellow"
    applyTheme(savedTheme)
}

function initTheme() {
    loadTheme()
    setupThemeButtons()
}

function setupThemeButtons() {
    const buttons = document.querySelectorAll("[data-theme]")
    buttons.forEach(btn => {
        btn.addEventListener("click", () => {
            const theme = btn.getAttribute("data-theme")
            setTheme(theme)
        })
    })
}

document.addEventListener("DOMContentLoaded", initTheme)