let enlaceEliminar = "";

document.addEventListener("DOMContentLoaded", () => {
    const botones = document.querySelectorAll(".js-delete");

    botones.forEach(boton => {
        boton.addEventListener("click", function(e) {
            e.preventDefault();
            enlaceEliminar = this.href;
            document.getElementById("modalEliminar").style.display = "flex";
        });
    });
});

function cerrarModal() {
    document.getElementById("modalEliminar").style.display = "none";
}

function confirmarEliminar() {
    if (enlaceEliminar) {
        window.location.href = enlaceEliminar;
    }
}