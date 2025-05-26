document.addEventListener('DOMContentLoaded', () => {
    const slides = document.querySelectorAll('.slider img');
    let currentSlide = 0;

    function showSlide(index) {
        slides.forEach((slide, i) => {
            slide.style.display = i === index ? 'block' : 'none'; // Asegura que solo una imagen esté visible
        });
    }

    function nextSlide() {
        currentSlide = (currentSlide + 1) % slides.length;
        showSlide(currentSlide);
    }

    // Inicializa el slider mostrando la primera imagen
    showSlide(currentSlide);

    // Cambia de imagen automáticamente cada 5 segundos
    setInterval(nextSlide, 5000);
});
