const themes = [
    "theme-yellow",
    "theme-purple",
    "theme-cyan-dark",
    "theme-green-dark",
    "theme-blue-light"
]

const themebackgrounds = {
    "theme-yellow": { type: "image", value: "/Images/Fondo.jpg" },
    "theme-purple": { type: "image", value: "/Images/Fondo.jpg" },
    "theme-cyan-dark": { type: "video", value: "/Images/bg1.mp4" },
    "theme-green-dark": { type: "video", value: "/Images/fondoverde.mp4" },
    "theme-blue-light": { type: "image", value: "/Images/fondo3.jpg" }
}

function applytheme(theme) {
    document.documentElement.classList.remove(...themes)
    document.documentElement.classList.add(theme)
    applybackground(theme)
    applylogo(theme)
}

function applybackground(theme) {
    const bg = themebackgrounds[theme]
    const body = document.body
    let video = document.getElementById("bgvideo")

    if (!video) {
        video = document.createElement("video")
        video.id = "bgvideo"
        video.autoplay = true
        video.loop = true
        video.muted = true
        video.setAttribute("muted", "")
        video.playsinline = true
        body.appendChild(video)
    }

    if (bg.type === "video") {

        video.src = bg.value
        video.loop = true
        video.load()
        video.play().catch(() => {})

        video.onended = () => {
            video.currentTime = 0
            video.play()
        }

        video.style.display = "block"

        body.classList.add("video-bg")
        body.classList.remove("image-bg")

    } else {
        video.pause()
        video.removeAttribute("src")
        video.load()
        video.style.display = "none"

        body.classList.remove("video-bg")
        body.classList.add("image-bg")
    }
}

function settheme(theme) {
    applytheme(theme)
    localStorage.setItem("theme", theme)
}

function loadtheme() {
    const savedtheme = localStorage.getItem("theme") || "theme-yellow"
    applytheme(savedtheme)
}

function inittheme() {
    loadtheme()
    setupthemebuttons()
}

function setupthemebuttons() {
    const buttons = document.querySelectorAll("[data-theme]")
    buttons.forEach(btn => {
        btn.addEventListener("click", () => {
            const theme = btn.getAttribute("data-theme")
            settheme(theme)
        })
    })
}

const themelogos = {
    "theme-yellow": "/Images/INPRAX.png",
    "theme-purple": "/Images/INPRAXThemeMorado.png",
    "theme-cyan-dark": "/Images/INPRAXThemeCyan.png",
    "theme-green-dark": "/Images/INPRAXThemeVerde.png",
    "theme-blue-light": "/Images/INPRAXThemeAzulClaro.png"
}

function applylogo(theme) {
    const logo = document.getElementById("logo")
    if (!logo) return
    logo.src = themelogos[theme] || "/Images/INPRAX.png"
}

document.addEventListener("DOMContentLoaded", inittheme)