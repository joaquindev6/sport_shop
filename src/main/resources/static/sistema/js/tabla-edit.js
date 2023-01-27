
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
                        { className: "centered", targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]},
                        { orderable: false, targets: [7, 1, 13] },
                        { width: "50px", targets: [0] },
                        { width: "400", targets: [2] },
                        { width: "500px", targets: [7] },
                        { width: "200px", targets: [3, 8, 9, 10, 11] },
                        { width: "150px", targets: [4] },
                        { width: "100px", targets: [1, 5, 6] }
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
        case "role":
            $(document).ready(function () {
                $('#table').DataTable({
                    columnDefs: [
                        { className: "centered", targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]},
                        { orderable: false, targets: [4, 9] },
                        { width: "50px", targets: [0] },
                        { width: "250", targets: [1, 2] },
                        { width: "500px", targets: [3] },
                        { width: "200px", targets: [5, 6, 4, 7, 8] },
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
        case "product-category":
            $(document).ready(function () {
                $('#table').DataTable({
                    columnDefs: [
                        { className: "centered", targets: [0, 1, 2, 3, 4, 5, 6, 7, 8]},
                        { orderable: false, targets: [3, 8] },
                        { width: "50px", targets: [0] },
                        { width: "250", targets: [1, 4, 6] },
                        { width: "500px", targets: [2] },
                        { width: "200px", targets: [3, 5, 7] },
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
        case "product-sub-category":
            $(document).ready(function () {
                $('#table').DataTable({
                    columnDefs: [
                        { className: "centered", targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]},
                        { orderable: false, targets: [4, 9] },
                        { width: "50px", targets: [0] },
                        { width: "250", targets: [1] },
                        { width: "500px", targets: [3] },
                        { width: "200px", targets: [2, 5, 6, 7, 8] },
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
        case "product-mark":
            $(document).ready(function () {
                $('#table').DataTable({
                    columnDefs: [
                        { className: "centered", targets: [0, 1, 2, 3, 4, 5, 6, 7, 8]},
                        { orderable: false, targets: [3, 8] },
                        { width: "50px", targets: [0] },
                        { width: "250", targets: [1, 4, 6] },
                        { width: "500px", targets: [2] },
                        { width: "200px", targets: [3, 5, 7] }
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
    }
}