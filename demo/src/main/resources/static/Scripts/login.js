const form = document.querySelector(".form");

form.addEventListener("submit", function(e) {
    const identificador = document.getElementById("identificador").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!identificador || !password) {
        e.preventDefault();
        alert("Completa todos los campos");
    }
    // Sin más restricciones - deja pasar al servidor
});
