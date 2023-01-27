$(document).ready(function() { //Permite la activacion del codigo
    $('.btnEditar').on('click', function(event){
        // var href = $(this).attr('href');
        //
        // $.get(href, function(user, status){
        //     $('#names').val(user.names);
        //     $('#apePat').val(user.apePat);
        //     $('#apeMat').val(user.apeMat);
        //     $('#documentType').val(user.documentType.id);
        //     $('#nroDocu').val(user.nroDocu);
        //     $('#email').val(user.email);
        //     $('#username').val(user.username);
        //     $('#password').val(user.password);
        //     $('#observation').val(user.observation);
        // });

        event.preventDefault();
        let url = this.getAttribute('href');
        const xhttp = new XMLHttpRequest(); //Para utilizar ajax con javascript

        xhttp.open('GET', url, true); //El true indica si es asincrono o no por ajax
        xhttp.send();

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let user = JSON.parse(this.responseText); //Convertimos el json a un objeto

                //Pasando los datos a los input del modal
                document.getElementById('id').value = user.id;
                document.getElementById('names').value = user.names;
                document.getElementById('apePat').value = user.apePat;
                document.getElementById('apeMat').value = user.apeMat;
                document.getElementById('documentType').value = user.documentType.id;
                document.getElementById('nroDocu').value = user.nroDocu;
                document.getElementById('email').value = user.email;
                document.getElementById('username').value = user.username;
                document.getElementById('password').value = user.password;
                document.getElementById('observation').value = user.observation;

                let roles = document.getElementsByName("roles");
                for(let rol of roles) { //Busca los id de los roles de los inputs de la base de datos
                    for (let rolUser of user.roles) { //Recorre los roles del usuario seleccioando
                        if (rol.value == rolUser.id) { //Valida si concuerda y lo marca
                            rol.checked = true;
                        }
                    }
                }
            }
        }

        var myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'), {})
        myModal.show();
    });

    $('.btnClose, .btnNuevo').on('click', function (evt) {
        document.getElementById('id').value = null;
        document.getElementById('names').value = '';
        document.getElementById('apePat').value = '';
        document.getElementById('apeMat').value = '';
        document.getElementById('documentType').value = '';
        document.getElementById('nroDocu').value = '';
        document.getElementById('email').value = '';
        document.getElementById('username').value = '';
        document.getElementById('password').value = '';
        document.getElementById('observation').value = '';

        let roles = document.getElementsByName('roles');
        for (let rol of roles) {
            rol.checked = false;
        }
        // setTimeout(function () {
        //     window.location.href = '/system-sport-shop/control/usuarios';
        // }, 1000)
    });

    $('.showPhoto').on('click', function (evt) {
        evt.preventDefault();

        let url = this.getAttribute('href');
        const xhttp = new XMLHttpRequest();

        xhttp.open('GET', url, true);
        xhttp.send();

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let user = JSON.parse(this.responseText);

                document.getElementById('imgPhoto').setAttribute('src', '/system-sport-shop/photousers/' + user.photo);
                document.getElementById('titleModalPhoto').innerHTML = user.names + ' ' + user.apePat + ' ' + user.apeMat;
            }
        }

        let modal = new bootstrap.Modal(document.getElementById('showModalPhoto'), {});
        modal.show();
    });
});