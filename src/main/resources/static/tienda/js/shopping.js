let idProducts = document.getElementsByName('idProducts');
const valores = Array.from(idProducts);

valores.forEach(function (id) {
    document.getElementById('addCar_' + id.value).addEventListener('click', function (evt) {

        let url = '/carrito/cargar-productos/' + id.value;
        const xhttp = new XMLHttpRequest();

        xhttp.open('GET', url, true);
        xhttp.send();

        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                let product = JSON.parse(this.responseText);
                localStorage.setItem('idLocalProduct', product.id);
            }
        }
    });
});