var botones = document.querySelectorAll('#delete-button');
console.log(botones);
botones.forEach(function(boton) {
    boton.addEventListener('click', function(e) {
        e.preventDefault();
        toggleDeleteModal(boton.dataset.info,boton.dataset.url);
    });
});

function toggleDeleteModal(info,url) {
    var text;
    if (info === 'files') {
        text = "¿Está seguro de que desea eliminar el fichero?";
    } else if (info === 'topic') {
        text = "¿Está seguro de que desea eliminar el tema?";
    } else if (info === 'comentario') {
        text = "¿Está seguro de que desea eliminar el comentario?";
    } else if (info == 'subject') {
        text = "¿Está seguro de que desea eliminar la asignatura?";
    }

    var wrapper = document.createElement('div');
    wrapper.id = "modal-wrapper";
    document.body.appendChild(wrapper);
    

    var modal = document.createElement('div');
    modal.id = "modal-confirmation";
    modal.innerHTML = '<div class="modal-header" id="modal-header"><h3><i class="fa fa-exclamation-circle" aria-hidden="true"></i> Confirmar borrado</h3><span data-confirm=0 class="modal-action-delete" id="modal-close"><i class="fa fa-times" aria-hidden="true"></i></span></div><div id="modal-content"><p>' + text + '</p></div><div id="modal-buttons"><button class="modal-action-delete" data-confirm=0 id="modal-button-no">No</button><button class="modal-action-delete" data-confirm=1 id="modal-button-yes">Sí</button></div>';
    wrapper.appendChild(modal);

    setTimeout(function() {
        wrapper.classList.add('active');
    }, 100);

    var modalActions = wrapper.querySelectorAll('.modal-action-delete');
    modalActions.forEach(function(action) {
        action.addEventListener('click', function() {
            var result = parseInt(this.dataset.confirm);
            if (result === 1) {
                window.location.href = url;
            } else {
                wrapper.classList.remove('active');
                setTimeout(function() {
                    wrapper.remove();
                }, 500);
            }
        });
    });
}
