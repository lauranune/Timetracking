// Inicializa DataTable
var app = {
    init: function () {
        app.initDatatable('#employees')
    },
    initDatatable: function (id) {
        $(id).DataTable();
    }
};
$(document).ready(function () {
    app.init();
});

// Manejador para el botón "Editar"


$(document).ready(function () {
    $(document).on('click', '.edit-btn', function () {
        const id = $(this).data('id');

        $.ajax({
            url: `/admin/edit?id=${id}`,
            type: 'GET',
            success: function (html) {
                $('body').append(html);

                const modal = new bootstrap.Modal(document.getElementById('modalEditAdmin'));
                modal.show();

                $(' #modalEditAdmin').on('hidden.bs.modal', function () {
                    $(this).remove();
                });
                $('#formEdit').on('submit', function (e) {
                    e.preventDefault();
                    $.ajax({
                        url: $(this).attr('action'),
                        type: 'POST',
                        data: $(this).serialize(),
                        success: function (data) {
                            alert('Trabajador actualizado correctamente');
                            const modalElement = document.getElementById('modalEditAdmin');
                            const modalInstance = bootstrap.Modal.getInstance(modalElement);
                            modalInstance.hide();
                            location.reload();
                        },
                        error: function (xhr) {
                            alert('Error al guardar trabajador: ' + xhr.responseText);
                        }
                    });
                });
            },
            error: function (xhr) {
                alert('Error al cargar trabajador: ' + xhr.responseText);
            }
        });
    });

// Nuevo user
    $(document).ready(function () {
        $('#newUser').on('click', function () {
            const $formContainer = $('#formContainer');

            $.ajax({
                url: '/admin/formNewUser',
                method: 'GET',
                success: function (data) {
                    $('body').append(data);
                    const modal = new bootstrap.Modal(document.getElementById('modalNewUser'));
                    modal.show();

                    bindNewUserForm();
                },
                error: function () {
                    alert('Error al crear trabajador');
                }
            })
        });
    });

    function bindNewUserForm() {
        const modalElement = document.getElementById('modalNewUser');
        const modal = bootstrap.Modal.getInstance(modalElement);

        $('#formNewUser').on('submit', function (e) {
            e.preventDefault();
            
            $.ajax({
                url: $(this).attr('action'),
                method: 'POST',
                data: $(this).serialize(),
                success: function () {
                    alert('Usuario creado correctamente');
                    modal.hide();
                    location.reload();
                },
                error: function (xhr) {
                    alert('Error al guardar el usuario: ' + xhr.responseText);
                }
            });
        });
    }

    // Borrar trabajador
    $(document).on('click', '.delete-btn', function () {
        const id = $(this).data('id');

        if (confirm("Quieres borrar este trabajador?")) {
            $.ajax({
                type: 'GET',
                url: `/admin/delete/${id}`,
                success: function () {
                    alert("Trabajador borrado correctamente.");
                    location.reload();
                },
                error: function () {
                    alert("Error al borrar el trabajador.");
                }
            });
        }
    });
})