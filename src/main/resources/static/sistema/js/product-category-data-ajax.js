$(document).ready(function () {
   $('.btnEditar').on('click', function (evt) {
       evt.preventDefault();

       let url = this.getAttribute('href');
       const xhttp = new XMLHttpRequest();

       xhttp.open('GET', url, true);
       xhttp.send();

       xhttp.onreadystatechange = function () {
           if (this.readyState == 4 && this.status == 200) {
                let category = JSON.parse(this.responseText);

                document.getElementById('id').value = category.id;
                document.getElementById('name').value = category.name;
                document.getElementById('description').value = category.description;
           }
       }

       let modal = new bootstrap.Modal(document.getElementById('staticBackdrop'), {});
       modal.show();
   });

   $('.btnClose, .btnNuevo').on('click', function (evt) {
       document.getElementById('id').value = null;
       document.getElementById('name').value = '';
       document.getElementById('description').value = '';
   });
});