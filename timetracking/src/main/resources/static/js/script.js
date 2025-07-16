// Inicializa DataTable
$(document).ready(function () {
    $('#employeesAdmin').DataTable();
});

// Manejador para el botón "Editar"
$(document).on('click', '.edit-btn', function () {
    const id = $(this).data('id');

    $.ajax({
        url: '/admin/edit',
        method: 'GET',
        data: { id: id },
        success: function (html) {
            // Inyectamos el modal en el contenedor
            $('#modalContainer').html(html);

            // Inicializamos y mostramos el modal
            const modal = new bootstrap.Modal(document.getElementById('modalEditAdmin'));
            modal.show();

            // Bindeamos el formulario para AJAX POST
            bindEditForm(modal);
        },
        error: function () {
            alert(' Error al cargar el formulario de edición.');
        }
    });
});

// Función para interceptar el submit del formulario
function bindEditForm(modalInstance) {
    $('#formEdit').on('submit', function (e) {
        e.preventDefault();

        $.ajax({
            url: $(this).attr('action'),
            method: 'POST',
            data: $(this).serialize(),
            success: function (responseHtml) {
                if ($(responseHtml).find(".alert.alert-warning").length > 0) {
                    // Hay errores de validación, volvemos a mostrar el modal con los errores
                    $('#modalContainer').html(responseHtml);
                    const modal = new bootstrap.Modal(document.getElementById('modalEditAdmin'));
                    modal.show();
                    bindEditForm(modal);
                } else {
                    // Actualización exitosa
                    alert(' Trabajador actualizado correctamente');
                    modalInstance.hide();
                    location.reload();
                }
            },
            error: function () {
                alert(' Error al actualizar el trabajador');
            }
        });
    });
}
