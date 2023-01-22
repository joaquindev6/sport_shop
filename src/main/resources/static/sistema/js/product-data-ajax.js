$(document).ready(function () {
   $('.btnEditar').on(function (evt) {
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
});