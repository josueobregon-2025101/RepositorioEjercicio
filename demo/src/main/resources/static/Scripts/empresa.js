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
        idLogin: document.getElementById("idLogin").value

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

function editarInactive(){
    let editar = document.getElementById("editarContainer");
    editar.classList.remove("editarActivo");
    editar.classList.add("editarInactivo");

}