document.addEventListener('DOMContentLoaded', function () {
  const inputId = document.getElementById('clienteRefe');
  const formulario = document.getElementById('clienteForm');
    
  inputId.addEventListener('input', function () {
    if (inputId.value.trim() !== '') {
      formulario.style.display = 'block';
      btnRegistrarId.addEventListener('click', function () {
      fetch('http://localhost:8081/api/clients/get/' + inputId.value)
        .then(response => {
          if (!response.ok) {
            throw new Error('Cliente no encontrado');
          }
          return response.json();
        })
        .then(cliente => {
          document.getElementById("nombreCompleto").value = cliente.nombreCompleto;
          document.getElementById("dni").value = cliente.dni;
          document.getElementById("correo").value = cliente.correoElectronico;
          document.getElementById("telefono").value = cliente.telefono;
          document.getElementById("fechaRegistro").value = cliente.fechaRegistro.split('T')[0];
          document.getElementById("estado").value = cliente.estado;
        })
        .catch(error => {
          console.error('Error al obtener el cliente:', error);
          alert('Error al obtener el cliente: ' + error.message);
        });
    });
    } else {
      formulario.style.display = 'none';
    }
  });
  formulario.addEventListener('submit', function (event) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    var cliente = {
      "id": Number(inputId.value),
      "nombreCompleto": document.getElementById("nombreCompleto").value,
      "dni": document.getElementById("dni").value,
      "correoElectronico": document.getElementById("correo").value,
      "telefono": document.getElementById("telefono").value,
      "fechaRegistro": document.getElementById("fechaRegistro").value,
      "estado": document.getElementById("estado").value
    };
console.log("Cliente a actualizar:", cliente);
    var url = "http://localhost:8081/api/clients/update/" + cliente.id;
    fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(cliente)
    }).then(response => {
      if (response.ok) {
        alert("Cliente actualizado correctamente");
        // Opcional: limpiar formulario o redirigir
      } else {
        alert("Error al actualizar cliente");
      }
    }).catch(error => {
      console.error("Error al actualizar cliente:", error);
      alert("Error del servidor: " + error.message);
    });
    this.reset(); // Limpia el formulario
  });
});