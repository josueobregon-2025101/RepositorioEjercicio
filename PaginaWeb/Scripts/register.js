const form = document.querySelector("form");
const fullname = form.querySelector("input[name='fullname']");
const email = form.querySelector("input[name='email']");
const username = form.querySelector("input[name='username']");
const password = form.querySelector("input[name='password']");
const confirmPassword = form.querySelector("input[name='confirm_password']");
const role = form.querySelector("select[name='role']");

// Inicializar usuarios en localStorage si no existen
if (!localStorage.getItem("users")) {
    localStorage.setItem("users", JSON.stringify([]));
}

// Escuchar envío del formulario
form.addEventListener("submit", function(e){
    e.preventDefault();

    // Capturar valores
    const fullnameValue = fullname.value.trim();
    const emailValue = email.value.trim();
    const usernameValue = username.value.trim();
    const passwordValue = password.value.trim();
    const confirmPasswordValue = confirmPassword.value.trim();
    const roleValue = role.value;

    // Validaciones básicas
    if (!fullnameValue || !emailValue || !usernameValue || !passwordValue || !confirmPasswordValue || !roleValue) {
        alert("Completa todos los campos");
        return;
    }

    // Validar email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(emailValue)) {
        alert("Correo inválido");
        return;
    }

    // Validar contraseñas
    if (passwordValue.length < 6) {
        alert("La contraseña debe tener mínimo 6 caracteres");
        return;
    }

    if (passwordValue !== confirmPasswordValue) {
        alert("Las contraseñas no coinciden");
        return;
    }

    // Recuperar usuarios existentes
    const users = JSON.parse(localStorage.getItem("users"));

    // Verificar duplicados
    const emailExists = users.some(user => user.email === emailValue);
    const usernameExists = users.some(user => user.username === usernameValue);

    if (emailExists) {
        alert("Este correo ya está registrado");
        return;
    }

    if (usernameExists) {
        alert("Este nombre de usuario ya está en uso");
        return;
    }

    // Crear nuevo usuario
    const newUser = {
        id: Date.now(), // id único
        name: fullnameValue,
        email: emailValue,
        username: usernameValue,
        password: passwordValue,
        role: roleValue
    };

    // Guardar usuario en localStorage
    users.push(newUser);
    localStorage.setItem("users", JSON.stringify(users));

    alert("Registro exitoso. Serás redirigido al login.");

    // Redirigir al login
    window.location.href = "login.html";
});