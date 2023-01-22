$(document).ready(function () {
    $('.btnEditar').on('click', function (evt) {
        evt.preventDefault();

        let url = this.getAttribute('href');
        const xhttp = new XMLHttpRequest();

        xhttp.open('GET', url, true);
        xhttp.send();

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let subcate = JSON.parse(xhttp.responseText);

                document.getElementById('id').value = subcate.id;
                document.getElementById('name').value = subcate.name;
                document.getElementById('category').value = subcate.category.id;
                document.getElementById('description').value = subcate.description;
            }
        }

        let modal = new bootstrap.Modal(document.getElementById('staticBackdrop'), {});
        modal.show();
    });

    $('.btnClose, .btnNuevo').on('click', function (evt) {
        document.getElementById('id').value = null;
        document.getElementById('name').value = '';
        document.getElementById('category').value = null;
        document.getElementById('description').value = '';
    });
})