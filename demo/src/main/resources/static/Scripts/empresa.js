function editarActive(id){
    //ponemos el id en un input para uso posterior
    document.getElementById("id").value = id;
    fetch("/empresas/" + id)
    .then(response => response.json())
    .then(data => {
        //ponemos los datos en inputs para uso posterior
        document.getElementById("nombre").value = data.nombreEmpresa;
        document.getElementById("correo").value = data.correoEmpresa;
        document.getElementById("telefono").value = data.telefonoEmpresa;
        document.getElementById("sector").value = data.tipoEmpresa;
        document.getElementById("tamanio").value = data.tamanoEmpresa;
        document.getElementById("direction").value = data.direccionEmpresa;
        document.getElementById("horario").value = data.horarioEmpresa;
        document.getElementById("descripcion").value = data.descripcion;
        document.getElementById("idLogin").value =data.idLogin;

//cambios de estado para ocultar o mostrar
        let editar = document.getElementById("editarContainer");
        editar.classList.remove("editarInactivo");
        editar.classList.add("editarActivo");
    });
}
async function validarContrasena(){
    const contrasena = document.getElementById("contrasenaLogin").value;

    const response = await fetch("/login/login/validar", {
        method:"POST",
        headers:{
            "Content-Type":
            "application/x-www-form-urlencoded"
        },
        body:
            "contrasena=" +
            encodeURIComponent(contrasena)
    });

    if(response.ok){
        return true;
    }else{
        return false;
    }
}

async function actualizarUsuario(){
    //obtenemos el id en una casilla no editable de el formulario
     let id = document.getElementById("idlogin").value;
    //llenamos el objeto empresa "C0N TODOS LOS DATOS"
    let usuario = {
        usuarioLogin: document.getElementById("usuarioLogin").value,
        correoLogin: document.getElementById("correoLogin").value,
        contrasenaLogin: document.getElementById("newContrasenaLogin").value,
        roles: "Estudiante"
    };
    //fecth con url(empresas/editar/id)
    const response = await fetch("/login/login/edit/" + id,{
        //metodo put
        method:"PUT",
        //advierte que es un JSON
        headers:{
            "Content-Type":"application/json"
        },
        //lo pasamos el objeto JSON nuevo
        body: JSON.stringify(usuario)
    })
    //validaciones de errores posibles
    .catch(error =>{
        console.log(error);
    });
}
async function actualizarEstudiante(){
    //obtenemos el id en una casilla no editable de el formulario
     let id = document.getElementById("id").value;
    //llenamos el objeto empresa "C0N TODOS LOS DATOS"
    let estudiante = {
        nombreInstitucion: document.getElementById("nombreInstitucion").value,
        nombre: document.getElementById("nombre").value,
        apellido: document.getElementById("apellido").value,
        telefono: document.getElementById("telefono").value,
        grado: document.getElementById("grado").value,
        carrera: document.getElementById("carrera").value,
        correo: document.getElementById("correo").value,
        edad: document.getElementById("edad").value,
        tutortel: document.getElementById("tutortel").value,
        //id no visible pero para funcionamiento de editar y no dejar vacio
        idlogin: document.getElementById("idlogin").value

    };
    //fecth con url(empresas/editar/id)
    const response = await fetch("/estudiantes/estudiantes/editar/" + id,{
        //metodo put
        method:"PUT",
        //advierte que es un JSON
        headers:{
            "Content-Type":"application/json"
        },
        //lo pasamos el objeto JSON nuevo
        body: JSON.stringify(estudiante)
    })
    //validaciones de errores posibles
    .then(response => {
        if(!response.ok){
        alert("Error al actualizar: " + response.status);
        }
    })
    .catch(error =>{
        console.log(error);
    });
}
function actualizarEmpresa(){
    //obtenemos el id en una casilla no editable de el formulario
     let id = document.getElementById("id").value;
     console.log(id);
    //llenamos el objeto empresa "C0N TODOS LOS DATOS"
    let empresa = {
        nombreEmpresa: document.getElementById("nombre").value,
        correoEmpresa: document.getElementById("correo").value,
        telefonoEmpresa: document.getElementById("telefono").value,
        tipoEmpresa: document.getElementById("sector").value,
        tamanoEmpresa: document.getElementById("tamanio").value,
        direccionEmpresa: document.getElementById("direction").value,
        horarioEmpresa: document.getElementById("horario").value,
        descripcion: document.getElementById("descripcion").value,
        //id no visible pero para funcionamiento de editar y no dejar vacio
        idLogin: document.getElementById("idlogin").value

    };
    //fecth con url(empresas/editar/id)
    fetch("/empresas/empresas/editar/" + id,{
        //metodo put
        method:"PUT",
        //advierte que es un JSON
        headers:{
            "Content-Type":"application/json"
        },
        //lo pasamos el objeto JSON nuevo
        body: JSON.stringify(empresa)
    })
    //validaciones de errores posibles
    .then(response => {
        console.log("Código:", response.status);

        if(response.ok){
            alert("Empresa actualizada");
            location.reload();
        }else{
            alert("Error al actualizar: " + response.status);
        }
    })
    .catch(error =>{
        console.log(error);
    });
}

 async function actualizarDatosEstudiante() {

     const valida = await validarContrasena();

     if(!valida){
         alert("Contraseña incorrecta");
         location.reload();
         return;
     }
     await actualizarUsuario();
     await actualizarEstudiante();
     alert("Datos actualizados");
     location.reload();
 }

function editarInactive(){
    let editar = document.getElementById("editarContainer");
    editar.classList.remove("editarActivo");
    editar.classList.add("editarInactivo");

}

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