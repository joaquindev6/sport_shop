$(document).ready(function () {
   $('.btnEditar').on('click',function (evt) {
       evt.preventDefault();

       let url = this.getAttribute('href');
       const xhttp = new XMLHttpRequest();

       xhttp.open('GET', url, true);
       xhttp.send();

       xhttp.onreadystatechange = function () {
           if (this.readyState == 4 && this.status == 200) {
                let role = JSON.parse(this.responseText);

                document.getElementById('id').value = role.id;
                document.getElementById('name').value = role.name;
                document.getElementById('code').value = role.code;
                document.getElementById('description').value = role.description;
           }
       }

       let modal = new bootstrap.Modal(document.getElementById('staticBackdrop'), {});
       modal.show();
   });

    $('.btnClose, .btnNuevo').on('click', function () {
        document.getElementById('id').value = null;
        document.getElementById('name').value = '';
        document.getElementById('code').value = '';
        document.getElementById('description').value = '';
    });
});