const form = document.querySelector(".form");
const email = document.querySelector("#email");
const password = document.querySelector("#password");

// Crear usuarios de prueba siempre
const initialUsers = [
    { id: 1, name: "Marcos Montenegro", email: "admin@plataforma.com", password: "admin123", role: "admin" },
    { id: 2, name: "Juan Pérez", email: "juan@correo.com", password: "juan1234", role: "estudiante" },
    { id: 3, name: "Ana López", email: "ana@empresa.com", password: "ana1234", role: "empresa" }
];
localStorage.setItem("users", JSON.stringify(initialUsers));

// Escuchar envío del formulario
form.addEventListener("submit", function(e){
    e.preventDefault();

    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();

    if(!emailValue || !passwordValue){
        alert("Completa todos los campos");
        return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!emailRegex.test(emailValue)){
        alert("Correo inválido");
        return;
    }

    if(passwordValue.length < 6){
        alert("La contraseña debe tener mínimo 6 caracteres");
        return;
    }

    // Recuperar usuarios
    const users = JSON.parse(localStorage.getItem("users"));

    // Buscar usuario
    const userFound = users.find(
        user => user.email === emailValue && user.password === passwordValue
    );

    if(!userFound){
        alert("Credenciales incorrectas");
        return;
    }

    // Guardar sesión
    localStorage.setItem("session", JSON.stringify(userFound));

    // Redirección según rol
    switch(userFound.role){
        case "admin":
            window.location.href = "dashboard-admin.html";
            break;
        case "estudiante":
            window.location.href = "dashboard-estudiante.html";
            break;
        case "empresa":
            window.location.href = "dashboard-empresa.html";
            break;
        default:
            alert("Rol desconocido");
    }
});