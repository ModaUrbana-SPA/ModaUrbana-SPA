document.addEventListener("DOMContentLoaded", () => {
    fetch("/prendas")
        .then(res => {
            if (!res.ok) throw new Error("Error al obtener productos");
            return res.json();
        })
        .then(data => {
            productos = data;
            mostrarDestacadosSlider();
            mostrarProductos();
        })
        .catch(err => {
            document.getElementById('slide-image-container').innerHTML = `<p style="color:red;">${err.message}</p>`;
        });
});

let productos = [];
let cart = [];
let indexSlider = 0;

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

function mostrarDestacadosSlider() {
    const contenedor = document.getElementById('slide-image-container');
    contenedor.innerHTML = '';

    const destacados = productos.filter(p => p.destacado);

    destacados.forEach((prod, index) => {
        const div = document.createElement('div');
        div.className = 'slider-item';
        if (index === 0) div.classList.add('active');
        div.innerHTML = `<img src="${prod.imagen}" alt="${prod.nombre}" style="cursor:pointer;" onclick="window.location.href='detalle.html?id=${prod.id}'">`;
        contenedor.appendChild(div);
    });

    iniciarSliderManual();
}

function iniciarSliderManual() {
    const items = document.querySelectorAll('.slider-item');
    const nextBtn = document.getElementById('next-slide');
    const prevBtn = document.getElementById('prev-slide');

    function mostrarSlide(index) {
        items.forEach((item, i) => {
            item.classList.toggle('active', i === index);
        });
    }

    nextBtn.addEventListener('click', () => {
        indexSlider = (indexSlider + 1) % items.length;
        mostrarSlide(indexSlider);
    });

    prevBtn.addEventListener('click', () => {
        indexSlider = (indexSlider - 1 + items.length) % items.length;
        mostrarSlide(indexSlider);
    });

    setInterval(() => {
        indexSlider = (indexSlider + 1) % items.length;
        mostrarSlide(indexSlider);
    }, 5000);
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
