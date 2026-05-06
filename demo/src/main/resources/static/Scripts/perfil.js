window.addEventListener('DOMContentLoaded', function() {
const loadingScreen = document.getElementById('loading-screen');
const loadingVideo = document.getElementById('loading-video');

// Opción 1: Cuando el video termina (recomendado)
loadingVideo.addEventListener('ended', function() {
    loadingScreen.classList.add('fade-out');

    // Remover completamente después de la animación
    setTimeout(() => {
        loadingScreen.style.display = 'none';
    }, 800); // Coincide con la duración de la animación CSS
});

// Opción 2: Tiempo fijo (plan B por si el evento 'ended' falla)
setTimeout(() => {
    if (loadingScreen.style.display !== 'none') {
        loadingScreen.classList.add('fade-out');
        setTimeout(() => {
            loadingScreen.style.display = 'none';
        }, 800);
    }
}, 3500); // Un poco más que la duración del video
});