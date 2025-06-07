document.addEventListener('DOMContentLoaded', function() {
fetch('api/clients')
.then(response => response.json())
.then(data => {
    const tbody = document.getElementById('cliente_table');
    data.forEach(cliente => {
        let fecha = cliente.fechaRegistro ? cliente.fechaRegistro.split('T')[0] : '';
        console.log(cliente.fechaRegistro, fecha); // <-- agrega esto
        const fila = document.createElement('tr');
        fila.innerHTML = `
            <td>${cliente.id}</td>
            <td>${cliente.nombreCompleto}</td>
            <td>${cliente.dni}</td>
            <td>${cliente.correoElectronico}</td>
            <td>${cliente.telefono}</td>
            <td>${fecha}</td>
            <td>${cliente.estado}</td>`;
            tbody.appendChild(fila);
    });}).catch(error => {
    console.error('Error fetching clientes:', error);
    });});
    