
window.onload = function () {
    let inputIds = document.getElementsByName('importes');
    const valores = Array.from(inputIds);

    //Al entrar a la pagina automaticamente obtiene todos los ids de los productos y calcula el importe y el total
    valores.forEach(function (id) {

        //Elimina el localStorage correspondiente con el id
        document.getElementById('remove_' + id.value).addEventListener('click', function () {
            localStorage.removeItem('cantidadStorage_' + id.value);
        });

        calcularImporte(id.value);

        //En el caso que no se haya guardado nada en el localstorage no entra a la condicion
        let cantidadSession = 0;
        if (localStorage.getItem('cantidadStorage_' + id.value) != null) {
            cantidadSession = localStorage.getItem('cantidadStorage_' + id.value);
            document.getElementById('cantidad_' + id.value).setAttribute('value', cantidadSession);
        }

        //Verifica si el producto seleccionado y se encuentra en el carrito
        let idLocalProduct = localStorage.getItem('idLocalProduct');
        if (idLocalProduct === id.value) {
            let cantidadProducto = ++cantidadSession;
            document.getElementById('cantidad_' + id.value).setAttribute('value', cantidadProducto);
            localStorage.setItem('cantidadStorage_' + id.value, cantidadProducto);
            localStorage.removeItem('idLocalProduct');
        }
    })
}

function calcularImporte(id) {
    let precio = document.getElementById('precio_' + id).innerHTML;
    precio = parseFloat(precio.substring(10));

    let cantidad = document.getElementById('cantidad_' + id).value;
    cantidad = parseFloat(cantidad);

    if (cantidad > 1) {
        localStorage.setItem('cantidadStorage_' + id, cantidad);
    } else {
        cantidad = localStorage.getItem('cantidadStorage_' + id) == null ? 1 : localStorage.getItem('cantidadStorage_' + id);
    }

    document.getElementById('importe_' + id).innerHTML = precio * cantidad;

    calcularTotal();
}

function calcularTotal() {
    let subTotal = 0;
    let total = 0;

    let inputIds = document.getElementsByName('importes');
    const valores = Array.from(inputIds);

    valores.forEach(function (id) {

        let importe = document.getElementById('importe_' + id.value).innerHTML;
        document.getElementById('importeInput_' + id.value).value = importe; //Pasando datos al imput
        importe = parseFloat(importe);

        subTotal += importe;

        let descuentos = 0;
        total = subTotal - descuentos;

        document.getElementById('precioSubTotal').innerHTML = 'S/ ' + subTotal;
        document.getElementById('precioTotal').innerHTML = 'S/ ' + total;

        document.getElementById('total').value = total; //Pasando datos al input
    });
}

//Al finalizar la compra elimina el localStorage con las cantidades
document.getElementById('saleFinish').addEventListener('click', function () {
    let inputIds = document.getElementsByName('importes');
    const valores = Array.from(inputIds);

    valores.forEach(function (id) {
        localStorage.removeItem('cantidadStorage_' + id.value);
    });
});

