
let productoActual = null;

document.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (!id) {
        alert("Producto no encontrado");
        window.location.href = "index.html";
        return;
    }

    const productos = JSON.parse(localStorage.getItem("productosDisponibles")) || [];
    productoActual = productos.find(p => p.id == id);

    if (productoActual) {
        renderizarProducto(productoActual);
    } else {
        fetch(`/prendas/${id}`)
            .then(res => {
                if (!res.ok) throw new Error("No se pudo cargar el producto");
                return res.json();
            })
            .then(prod => {
                productoActual = prod;
                renderizarProducto(prod);
            })
            .catch(err => {
                console.error(err);
                alert("No se pudo cargar el producto");
            });
    }
});

function renderizarProducto(prod) {
    const container = document.getElementById("detalle-producto");
    container.innerHTML = `
    <div class="product-detail">
        <img src="${prod.imagen}" alt="${prod.nombre}" class="detalle-img" />
        <div class="detalle-info">
            <h2 id="nombre-producto">${prod.nombre}</h2>
            <p id="precio-producto"><strong>Precio:</strong> $${prod.precio}</p>
            <p id="color-producto"><strong>Color:</strong> ${prod.color}</p>
            <p id="talla-producto"><strong>Talla:</strong> ${prod.talla}</p>
            <p><strong>Stock disponible:</strong> ${prod.stock}</p>
            <button class="glow-btn" onclick="agregarAlCarrito()">AGREGAR AL CARRITO</button>
        </div>
    </div>
    `;
}

function agregarAlCarrito() {
    if (!productoActual) {
        alert("Producto no disponible");
        return;
    }

    const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
    const existente = carrito.find(p => p.name === productoActual.nombre);

    if (existente) {
        existente.qty += 1;
    } else {
        carrito.push({
            name: productoActual.nombre,
            qty: 1,
            price: productoActual.precio
        });
    }

    localStorage.setItem("carrito", JSON.stringify(carrito));
    actualizarContadorCarrito();

    if (confirm(`"${productoActual.nombre}" agregado al carrito.\n\n¿Quieres ir al carrito? (Cancelar para seguir comprando)`)) {
        window.location.href = "index.html#cart-btn";
    } else {
        window.location.href = "index.html#shop";
    }
}

function actualizarContadorCarrito() {
    const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
    const total = carrito.reduce((acc, item) => acc + item.qty, 0);
    const contador = document.getElementById("cart-count");
    if (contador) contador.textContent = total;
}

