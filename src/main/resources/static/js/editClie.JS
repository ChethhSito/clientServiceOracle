document.addEventListener('DOMContentLoaded', function () {
  const inputId = document.getElementById('clienteRefe');
  const formulario = document.getElementById('clienteForm');
  const btnRegistrarId = document.getElementById('btnRegistrarId');
  const barraCarga = document.getElementById('progress-bar');
  const barraRelleno = barraCarga.querySelector('.progress-bar-fill');

  // Función que muestra barra por 1 segundo
  function mostrarBarraCarga() {
    barraCarga.style.display = 'block';
    barraRelleno.style.width = '0%';

    setTimeout(() => {
      barraRelleno.style.width = '100%';
    }, 10);

    return new Promise((resolve) => {
      setTimeout(() => {
        barraCarga.style.display = 'none';
        barraRelleno.style.width = '0%';
        resolve();
      }, 1000);
    });
  }

  btnRegistrarId.addEventListener('click', async function () {
    if (inputId.value.trim() === '') {
      Swal.fire('Error', 'Ingrese un ID de cliente válido', 'error');
      return;
    }

    await mostrarBarraCarga(); // Mostramos barra antes de la búsqueda

    fetch('http://localhost:8081/api/clients/get/' + inputId.value)
      .then(response => {
        if (!response.ok) {
          throw new Error('Cliente no encontrado');
        }
        return response.json();
      })
      .then(cliente => {
        formulario.style.display = 'block';

        document.getElementById("nombreCompleto").value = cliente.nombreCompleto;
        document.getElementById("dni").value = cliente.dni;
        document.getElementById("correo").value = cliente.correoElectronico;
        document.getElementById("telefono").value = cliente.telefono;
        document.getElementById("fechaRegistro").value = cliente.fechaRegistro.split('T')[0];
        document.getElementById("estado").value = cliente.estado;
      })
      .catch(error => {
        formulario.style.display = 'none';
        Swal.fire('No existe ese Id', error.message, 'error');
      });
  });

  formulario.addEventListener('submit', function (event) {
    event.preventDefault(); // Evita el envío por defecto

    const cliente = {
      id: Number(inputId.value),
      nombreCompleto: document.getElementById("nombreCompleto").value,
      dni: document.getElementById("dni").value,
      correoElectronico: document.getElementById("correo").value,
      telefono: document.getElementById("telefono").value,
      fechaRegistro: document.getElementById("fechaRegistro").value,
      estado: document.getElementById("estado").value
    };

    const url = "http://localhost:8081/api/clients/update/" + cliente.id;

    fetch(url, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(cliente)
    })
    .then(response => {
      if (response.ok) {
        Swal.fire('Cliente agregado correctamente', '', 'success');
        formulario.reset();
        formulario.style.display = 'none';
        inputId.value = "";
      } else {
        alert("Error al actualizar cliente");
      }
    })
    .catch(error => {
      Swal.fire('Error al actualizar', error.message, 'error');
    });
  });
});
