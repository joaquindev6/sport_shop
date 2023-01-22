
function tableEdit(option) {
    switch (option) {
        case "user":
            //Diseño del template de la tabla
            $(document).ready(function () {
                $('#table').DataTable({
                    columnDefs: [
                        { className: "centered", targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]},
                        { orderable: false, targets: [8, 14] },
                        { width: "50px", targets: [0]},
                        { width: "320px", targets: [1, 4, 6]},
                        { width: "500px", targets: [9]},
                        { width: "200px", targets: [2, 3, 5, 7, 8, 10, 11, 12, 13]}
                    ],
                    scrollX: "2000px",
                    language: {
                        processing: "Traitement en cours...",
                        search: "Buscar&nbsp;:",
                        lengthMenu: "Elementos por paginación _MENU_",
                        info: "Mostrando del _START_ al _END_ de _TOTAL_ filas",
                        infoEmpty: "Mostrando del 0 al _END_ de _TOTAL_ filas",
                        infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                        infoPostFix: "",
                        loadingRecords: "Chargement en cours...",
                        zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
                        emptyTable: "No hay datos que mostrar",
                        paginate: {
                            first: "Inicio",
                            previous: "Anterior",
                            next: "Siguiente",
                            last: "Ultimo"
                        },
                        aria: {
                            sortAscending: ": activer pour trier la colonne par ordre croissant",
                            sortDescending: ": activer pour trier la colonne par ordre décroissant"
                        }
                    }
                });
            });
            break;
        case "client":
            $(document).ready(function () {
                $('#table').DataTable({
                    columnDefs: [
                        { className: "centered", targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]},
                        { orderable: false, targets: [6, 11] },
                        { width: "50px", targets: [0]},
                        { width: "320px", targets: [1, 4]},
                        { width: "500px", targets: [5]},
                        { width: "200px", targets: [2, 3, 6, 7, 9, 8, 10]}
                    ],
                    scrollX: "2000px",
                    language: {
                        processing: "Traitement en cours...",
                        search: "Buscar&nbsp;:",
                        lengthMenu: "Elementos por paginación _MENU_",
                        info: "Mostrando del _START_ al _END_ de _TOTAL_ filas",
                        infoEmpty: "Mostrando del 0 al _END_ de _TOTAL_ filas",
                        infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                        infoPostFix: "",
                        loadingRecords: "Chargement en cours...",
                        zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
                        emptyTable: "No hay datos que mostrar",
                        paginate: {
                            first: "Inicio",
                            previous: "Anterior",
                            next: "Siguiente",
                            last: "Ultimo"
                        },
                        aria: {
                            sortAscending: ": activer pour trier la colonne par ordre croissant",
                            sortDescending: ": activer pour trier la colonne par ordre décroissant"
                        }
                    }
                });
            });
            break;
        case "product":
            $(document).ready(function () {
                $('#table').DataTable({
                    columnDefs: [
                        { className: "centered", targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]},
                        { orderable: false, targets: [7, 12] },
                        { width: "50px", targets: [0] },
                        { width: "400", targets: [1] },
                        { width: "500px", targets: [6] },
                        { width: "200px", targets: [2, 3, 7, 8, 9, 10, 11] },
                        { width: "150px", targets: [4, 5] }
                    ],
                    scrollX: "2000px",
                    language: {
                        processing: "Traitement en cours...",
                        search: "Buscar&nbsp;:",
                        lengthMenu: "Elementos por paginación _MENU_",
                        info: "Mostrando del _START_ al _END_ de _TOTAL_ filas",
                        infoEmpty: "Mostrando del 0 al _END_ de _TOTAL_ filas",
                        infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                        infoPostFix: "",
                        loadingRecords: "Chargement en cours...",
                        zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
                        emptyTable: "No hay datos que mostrar",
                        paginate: {
                            first: "Inicio",
                            previous: "Anterior",
                            next: "Siguiente",
                            last: "Ultimo"
                        },
                        aria: {
                            sortAscending: ": activer pour trier la colonne par ordre croissant",
                            sortDescending: ": activer pour trier la colonne par ordre décroissant"
                        }
                    }
                });
            });
            break;
    }
}