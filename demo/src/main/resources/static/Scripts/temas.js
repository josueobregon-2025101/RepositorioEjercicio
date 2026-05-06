const themes = [
    "theme-yellow",
    "theme-purple",
    "theme-cyan-dark",
    "theme-green-dark",
    "theme-blue-light"
]

const configuracionLoaders = {
    "theme-yellow": "/videos/ConfiguracionLoaderAmarillo.mp4",
    "theme-purple": "/videos/ConfiguracionLoaderMorado.mp4",
    "theme-cyan-dark": "/videos/ConfiguracionLoaderCyan.mp4",
    "theme-green-dark": "/videos/ConfiguracionLoaderVerde.mp4",
    "theme-blue-light": "/videos/ConfiguracionLoaderAzulClaro.mp4"
}

const practicasLoaders = {
    "theme-yellow": "/videos/PracticasLoaderAmarillo.mp4",
    "theme-purple": "/videos/PracticasLoaderMorado.mp4",
    "theme-cyan-dark": "/videos/PracticasLoaderCyan.mp4",
    "theme-green-dark": "/videos/PracticasLoaderVerde.mp4",
    "theme-blue-light": "/videos/PracticasLoaderAzulClaro.mp4"
}

const empresasLoaders = {
    "theme-yellow": "/videos/EmpresasLoaderAmarillo.mp4",
    "theme-purple": "/videos/EmpresasLoaderMorado.mp4",
    "theme-cyan-dark": "/videos/EmpresasLoaderCyan.mp4",
    "theme-green-dark": "/videos/EmpresasLoaderVerde.mp4",
    "theme-blue-light": "/videos/EmpresasLoaderAzulClaro.mp4"
}

const perfilLoaders = {
    "theme-yellow": "/videos/PerfilLoaderAmarillo.mp4",
    "theme-purple": "/videos/PerfilLoaderMorado.mp4",
    "theme-cyan-dark": "/videos/PerfilLoaderCyan.mp4",
    "theme-green-dark": "/videos/PerfilLoaderVerde.mp4",
    "theme-blue-light": "/videos/PerfilLoaderAzulClaro.mp4"
}

const estudiantesLoaders = {
    "theme-yellow": "/videos/EstudiantesLoaderAmarillo.mp4",
    "theme-purple": "/videos/EstudiantesLoaderMorado.mp4",
    "theme-cyan-dark": "/videos/EstudiantesLoaderCyan.mp4",
    "theme-green-dark": "/videos/EstudiantesLoaderVerde.mp4",
    "theme-blue-light": "/videos/EstudiantesLoaderAzulClaro.mp4"
}

const themebackgrounds = {
    "theme-yellow": { type: "video", value: "/Images/fondoamarillo.mp4" },
    "theme-purple": { type: "video", value: "/Images/fondomorado.mp4" },
    "theme-cyan-dark": { type: "video", value: "/Images/fondocyan.mp4" },
    "theme-green-dark": { type: "video", value: "/Images/fondoverde.mp4" },
    "theme-blue-light": { type: "video", value: "/Images/fondoceleste.mp4" }
}

const configuracionIcons = {
    "theme-yellow": "/Images/ConfiguracionIconAmarillo.png",
    "theme-purple": "/Images/ConfiguracionIconMorado.png",
    "theme-cyan-dark": "/Images/ConfiguracionIconCyan.png",
    "theme-green-dark": "/Images/ConfiguracionIconVerde.png",
    "theme-blue-light": "/Images/ConfiguracionIconAzulClaro.png"
}

function applyfavicon(theme) {
    const favicon = document.getElementById("favicon");
    if (!favicon) return;

    favicon.href = configuracionIcons[theme] || "/Images/ConfiguracionIconAmarillo.png";
}

function applytheme(theme) {
    document.documentElement.classList.remove(...themes)
    document.documentElement.classList.add(theme)
    applybackground(theme)
    applylogo(theme)
    applyLoader(theme)
    applyfavicon(theme);
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

function applyLoader(theme) {
    const loaderVideo = document.getElementById("loading-video");
    const loadingScreen = document.getElementById("loading-screen");

    // Si el elemento no existe en el HTML actual, salimos
    if (!loaderVideo) return;

    const pathname = window.location.pathname;
    let loaderSrc = null;

    // Determinar qué loader usar según la página
    if (pathname.includes("configuracion")) {
        loaderSrc = configuracionLoaders[theme] || "/videos/ConfiguracionLoaderAmarillo.mp4";
    } else if (pathname.includes("practicas")) {
        loaderSrc = practicasLoaders[theme] || "/videos/PracticasLoaderAmarillo.mp4";
    } else if (pathname.includes("empresas")) {
        loaderSrc = empresasLoaders[theme] || "/videos/EmpresasLoaderAmarillo.mp4";
    } else if (pathname.includes("perfil")) {
        loaderSrc = perfilLoaders[theme] || "/videos/PerfilLoaderAmarillo.mp4";
    } else if (pathname.includes("estudiantes")) {
        loaderSrc = estudiantesLoaders[theme] || "/videos/EstudiantesLoaderAmarillo.mp4";
    }

    // Si encontramos un loader para esta página, lo mostramos
    if (loaderSrc) {
        loaderVideo.src = loaderSrc;
        loaderVideo.load();

        if (loadingScreen) loadingScreen.style.display = "flex";
    } else {
        // Si no hay loader definido para esta página, ocultamos
        if (loadingScreen) loadingScreen.style.display = "none";
        loaderVideo.pause();
    }
}

document.addEventListener("DOMContentLoaded", inittheme)