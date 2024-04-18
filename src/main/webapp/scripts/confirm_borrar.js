var botones = document.querySelectorAll('.borrar-button');

botones.forEach(function(boton) {
    boton.addEventListener('click', function(e) {
        e.preventDefault();
        toggleModal(boton.dataset.info,boton.dataset.url);
    });
});

function toggleModal(info,url) {
    var text;
    if (info === 'files') {
        text = "¿Está seguro de que desea eliminar el fichero?";
    } else if (info === 'topic') {
        text = "¿Está seguro de que desea eliminar el tema?";
    } else if (info === 'comentario') {
        text = "¿Está seguro de que desea eliminar el comentario?";
    }

    var wrapper = document.createElement('div');
    wrapper.id = "modal-wrapper";
    document.body.appendChild(wrapper);
    

    var modal = document.createElement('div');
    modal.id = "modal-confirmation";
    modal.innerHTML = '<div id="modal-header"><h3><i class="fa fa-exclamation-circle" aria-hidden="true"></i> Confirmar borrado</h3><span data-confirm=0 class="modal-action" id="modal-close"><i class="fa fa-times" aria-hidden="true"></i></span></div><div id="modal-content"><p>' + text + '</p></div><div id="modal-buttons"><button class="modal-action" data-confirm=0 id="modal-button-no">No</button><button class="modal-action" data-confirm=1 id="modal-button-yes">Sí</button></div>';
    wrapper.appendChild(modal);

    setTimeout(function() {
        wrapper.classList.add('active');
    }, 100);

    var modalActions = wrapper.querySelectorAll('.modal-action');
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
