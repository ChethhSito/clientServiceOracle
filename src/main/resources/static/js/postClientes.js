
document.getElementById('clienteForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto
    
    var cliente = {
        "nombreCompleto": document.getElementById("nombreCompleto").value,
        "dni": document.getElementById("dni").value,
        "correoElectronico": document.getElementById("correo").value,
        "telefono": document.getElementById("telefono").value,
        "fechaRegistro": document.getElementById("fechaRegistro").value,
        "estado" : document.getElementById("estado").value
    };
    var url= "http://localhost:8081/api/clients/add";
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cliente)
    }).then(response => {
        if (response.ok) {
            
            alert("Cliente agregado correctamente");
            // Opcional: limpiar formulario o redirigir
        } else {
            alert("Error al agregar cliente");
        }
    }).catch(error => {
    console.error("Error al agregar cliente:", error);
    alert("Error del servidor: " + error.message);});
    this.reset(); // Limpia el formulario
}
);
