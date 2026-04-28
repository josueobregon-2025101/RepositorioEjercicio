const form = document.querySelector(".form");

form.addEventListener("submit", function(e) {
    const identificador = document.getElementById("identificador").value.trim();
    const passwordValue = document.getElementById("password").value.trim();

    if (!identificador || !passwordValue) {
        e.preventDefault();
        alert("Completa todos los campos");
    }
});
