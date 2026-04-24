const form = document.querySelector(".form");
const email = document.querySelector("#email");
const password = document.querySelector("#password");

// Usuarios de prueba
const initialUsers = [
    { id: 1, name: "Marcos Montenegro", email: "admin@plataforma.com", password: "admin123", role: "admin" },
    { id: 2, name: "Juan Pérez", email: "juan@correo.com", password: "juan1234", role: "estudiante" },
    { id: 3, name: "Ana López", email: "ana@empresa.com", password: "ana1234", role: "empresa" }
];
localStorage.setItem("users", JSON.stringify(initialUsers));

form.addEventListener("submit", function(e) {
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();

    if (!emailValue || !passwordValue) {
        e.preventDefault();
        alert("Completa todos los campos");
        return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(emailValue)) {
        e.preventDefault();
        alert("Correo inválido");
        return;
    }

    if (passwordValue.length < 3) {
        e.preventDefault();
        alert("La contraseña debe tener mínimo 3 caracteres");
        return;
    }
});