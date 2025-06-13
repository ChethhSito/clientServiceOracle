document.addEventListener("DOMContentLoaded", () => {
    const inputID = document.getElementById("clienteRefe");
    const btnBuscar = document.getElementById("btnBuscar");
    const btnEliminar = document.getElementById("btnEliminar");
    const tablaCont = document.getElementById("tablaCont");
    const tbody = document.getElementById("cliente_table");
    const progressBar = document.getElementById("progress-bar");
    const progressFill = document.querySelector(".progress-bar-fill");

    let clienteEncontrado = null;

    btnBuscar.addEventListener("click", async () => {
        const id = inputID.value.trim();
        if (!id) return alert("Por favor, ingrese un ID válido.");

        // Mostrar barra de carga
        progressBar.style.display = "block";
        progressFill.style.width = "0%";
        setTimeout(() => progressFill.style.width = "100%", 10);

        await new Promise(res => setTimeout(res, 1000)); // Espera simulada de 1 segundo

        try {
            const res = await fetch(`/api/clients/get/${id}`);
            if (!res.ok) throw new Error("Cliente no encontrado");

            const data = await res.json();
            clienteEncontrado = data;

            // Mostrar tabla
            tablaCont.style.display = "block";
            tbody.innerHTML = `
                <tr>
                    <td>${data.id}</td>
                    <td>${data.nombreCompleto}</td>
                    <td>${data.dni}</td>
                    <td>${data.correoElectronico}</td>
                    <td>${data.telefono}</td>
                    <td>${new Date(data.fechaRegistro).toLocaleDateString()}</td>
                    <td>${data.estado}</td>
                </tr>
            `;
        } catch (err) {
            alert(err.message);
            tablaCont.style.display = "none";
            tbody.innerHTML = "";
            clienteEncontrado = null;
        } finally {
            progressBar.style.display = "none";
            progressFill.style.width = "0%";
        }
    });

    btnEliminar.addEventListener("click", async () => {
        if (!clienteEncontrado) {
            alert("Debe buscar un cliente primero.");
            return;
        }

        const confirmar = confirm("¿Está seguro de eliminar este cliente?");
        if (!confirmar) return;

        try {
            const resDir = await fetch(`/api/direcciones/cliente/${clienteEncontrado.id}`);
            if (resDir.ok) {
                const direcciones = await resDir.json();
                if (direcciones.length > 0) {
                    alert("No se puede eliminar: el cliente tiene direcciones registradas.");
                    return;
                }
            }

            const res = await fetch(`/api/clients/delete/${clienteEncontrado.id}`, {
                method: "DELETE"
            });

            if (!res.ok) throw new Error("Error al eliminar el cliente");

            alert("Cliente eliminado correctamente.");
            inputID.value = "";
            tablaCont.style.display = "none";
            tbody.innerHTML = "";
            clienteEncontrado = null;

        } catch (err) {
            alert("Error: " + err.message);
        }
    });
});

