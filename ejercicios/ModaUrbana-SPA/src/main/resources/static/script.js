document.addEventListener("DOMContentLoaded", () => {
    fetch("/prendas")
        .then(res => {
            if (!res.ok) throw new Error("Error al obtener productos");
            return res.json();
        })
        .then(data => {
            productos = data;
            mostrarDestacados();
            iniciarSliderDestacados(); // 👉 Se agregó aquí
            mostrarProductos();
        })
        .catch(err => {
            document.getElementById('productos-lista').innerHTML = `<p style="color:red;">${err.message}</p>`;
        });
});

let productos = [];
let cart = [];

function mostrarProductos() {
    const contenedor = document.getElementById('productos-lista');
    contenedor.innerHTML = '';
    productos.forEach(prod => {
        const div = document.createElement('div');
        div.className = 'product';
        div.innerHTML = `
        <img src="${prod.imagen}" alt="${prod.nombre}">
        <p>${prod.nombre}</p>
        <p><strong>$${prod.precio}</strong></p>
        <p>Talla: ${prod.talla}</p>
        <p>Color: ${prod.color}</p>
        <button class="add-to-cart" data-product="${prod.nombre}">Agregar al carrito</button>
      `;
        contenedor.appendChild(div);
    });

    document.querySelectorAll('.add-to-cart').forEach(btn => {
        btn.addEventListener('click', () => {
            const product = btn.getAttribute('data-product');
            addToCart(product);
        });
    });
}

function mostrarDestacados() {
    const contenedor = document.getElementById('destacados-lista');
    contenedor.innerHTML = '';
    productos
        .filter(p => p.destacado)
        .forEach(prod => {
            const div = document.createElement('div');
            div.className = 'product';
            div.innerHTML = `
          <img src="${prod.imagen}" alt="${prod.nombre}">
          <p>${prod.nombre}</p>
          <p><strong>$${prod.precio}</strong></p>
          <p>Talla: ${prod.talla}</p>
          <p>Color: ${prod.color}</p>
          <button class="add-to-cart" data-product="${prod.nombre}">Agregar al carrito</button>
        `;
            contenedor.appendChild(div);
        });

    document.querySelectorAll('.add-to-cart').forEach(btn => {
        btn.addEventListener('click', () => {
            const product = btn.getAttribute('data-product');
            addToCart(product);
        });
    });
}

function iniciarSliderDestacados() {
    const contenedor = document.getElementById('destacados-lista');
    const items = contenedor.children;
    let index = 0;

    function ocultarTodos() {
        for (let item of items) {
            item.style.display = 'none';
        }
    }

    function mostrarActual() {
        ocultarTodos();
        if (items.length > 0) {
            items[index].style.display = 'block';
        }
    }

    function siguiente() {
        index = (index + 1) % items.length;
        mostrarActual();
    }

    mostrarActual();
    setInterval(siguiente, 3000);
}

function addToCart(productName) {
    const prod = productos.find(p => p.nombre === productName);
    if (!prod) return;

    const found = cart.find(item => item.name === productName);
    if (found) {
        found.qty += 1;
    } else {
        cart.push({ name: productName, qty: 1, price: prod.precio });
    }
    document.getElementById('cart-count').textContent = cart.reduce((acc, item) => acc + item.qty, 0);
    updateCartModal();
}

document.getElementById('cart-btn').addEventListener('click', e => {
    e.preventDefault();
    document.getElementById('cart-modal').style.display = 'block';
    updateCartModal();
});

document.getElementById('close-cart').addEventListener('click', () => {
    document.getElementById('cart-modal').style.display = 'none';
});

function updateCartModal() {
    const cartItems = document.getElementById('cart-items');
    cartItems.innerHTML = '';
    let total = 0;

    cart.forEach((item, idx) => {
        const subtotal = item.qty * item.price;
        total += subtotal;

        const li = document.createElement('li');
        li.innerHTML = `
        ${item.name} (x${item.qty}) - $${item.price} c/u = <b>$${subtotal}</b>
        <button onclick="removeFromCart(${idx})" style="margin-left:10px;">Eliminar</button>
      `;
        cartItems.appendChild(li);
    });

    if (cart.length > 0) {
        const totalLi = document.createElement('li');
        totalLi.innerHTML = `<b>Total: $${total}</b>`;
        cartItems.appendChild(totalLi);
    }
}

window.removeFromCart = function (idx) {
    cart.splice(idx, 1);
    document.getElementById('cart-count').textContent = cart.reduce((acc, item) => acc + item.qty, 0);
    updateCartModal();
};
