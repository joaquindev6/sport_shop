
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
                        infoEmpty: "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
                        infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                        infoPostFix: "",
                        loadingRecords: "Chargement en cours...",
                        zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
                        emptyTable: "Aucune donnée disponible dans le tableau",
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