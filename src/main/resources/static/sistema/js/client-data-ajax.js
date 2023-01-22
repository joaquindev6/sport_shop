$(document).ready(function () {
    $('.btnEditar').on('click', function (evt) {
       evt.preventDefault();

       let url = this.getAttribute('href');
       const xhttp = new XMLHttpRequest();

       xhttp.open('GET', url, true);
       xhttp.send();

       xhttp.onreadystatechange = function () {
           if (this.readyState == 4 && this.status == 200) {
                let client = JSON.parse(this.responseText);

                document.getElementById('id').value = client.id;
                document.getElementById('names').value = client.names;
                document.getElementById('apePat').value = client.apePat;
                document.getElementById('apeMat').value = client.apeMat;
                document.getElementById('email').value = client.email;
                document.getElementById('password').value = client.password;
                document.getElementById('observation').value = client.observation;
           }
       }

           let modal = new bootstrap.Modal(document.getElementById('staticBackdrop'), {});
       modal.show();
    });

    $('.btnClose, .btnNuevo').on('click', function (evt) {
        document.getElementById('id').value = null;
        document.getElementById('names').value = '';
        document.getElementById('apePat').value = '';
        document.getElementById('apeMat').value = '';
        document.getElementById('email').value = '';
        document.getElementById('password').value = '';
        document.getElementById('observation').value = '';
    });
});