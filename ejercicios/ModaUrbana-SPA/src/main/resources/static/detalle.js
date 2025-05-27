const params = new URLSearchParams(window.location.search);
const id = params.get("id");

if (!id) {
    alert("Producto no encontrado");
    window.location.href = "index.html";
}

fetch(`/prendas/${id}`)
    .then(res => res.json())
    .then(data => {
        document.getElementById("producto-nombre").textContent = data.nombre;
        document.getElementById("producto-imagen").src = data.imagen;
        document.getElementById("producto-precio").textContent = data.precio;
        document.getElementById("producto-talla").textContent = data.talla;
        document.getElementById("producto-color").textContent = data.color;
        document.getElementById("producto-stock").textContent = data.stock;

        document.getElementById("agregar-carrito").addEventListener("click", () => {
            alert(`${data.nombre} agregado al carrito`);
            // Aquí podrías usar localStorage o comunicarte con el carrito global
        });
    })
    .catch(err => {
        console.error(err);
        alert("No se pudo cargar el producto");
    });
