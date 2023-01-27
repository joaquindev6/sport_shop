$(document).ready(function () {
   $('.btnEditar').on('click',function (evt) {
       evt.preventDefault();

       let url = this.getAttribute('href');
       const xhttp = new XMLHttpRequest();

       xhttp.open('GET', url, true);
       xhttp.send();

       xhttp.onreadystatechange = function () {
           if (this.readyState == 4 && this.status == 200) {
               let product = JSON.parse(this.responseText);

               document.getElementById('id').value = product.id;
               document.getElementById('name').value = product.name;
               document.getElementById('mark').value = product.mark.id;
               document.getElementById('subCategory').value = product.subCategory.id;
               document.getElementById('amount').value = product.amount;
               document.getElementById('price').value = product.price;
               document.getElementById('description').value = product.description;
           }
       }

       let modal = new bootstrap.Modal(document.getElementById('staticBackdrop'), {});
       modal.show();
   });

   $('.btnClose, .btnNuevo').on('click', function (evt) {
       document.getElementById('id').value = null;
       document.getElementById('name').value = '';
       document.getElementById('mark').value = '';
       document.getElementById('subCategory').value = '';
       document.getElementById('amount').value = '';
       document.getElementById('price').value = '';
       document.getElementById('description').value = '';
   })

    //A traves del response body de controlador de producto obtengo los datos del producto por medio del request y con ajax realizo la peticion
    $('.showPhoto').on('click', function (evt) {
        evt.preventDefault();

        let urlDataProduct = this.getAttribute('href');
        const xhttp = new XMLHttpRequest();

        xhttp.open('GET', urlDataProduct, true);
        xhttp.send();

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let product = JSON.parse(this.responseText);

                document.getElementById('imgPhoto').setAttribute('src',
                    '/system-sport-shop/photoproducts/' + product.photo);
                document.getElementById('titleModalPhoto').innerHTML = product.name;
            }
        }

        let modal = new bootstrap.Modal(document.getElementById('showModalPhoto'), {});
        modal.show();
    });
});