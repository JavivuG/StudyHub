var botones = document.querySelectorAll('.new-topic');

botones.forEach(function(boton) {
    boton.addEventListener('click', function(e) {
        e.preventDefault();
        toggleModal();
    });
});

function toggleModal() {
    var wrapper = document.createElement('div');
    wrapper.id = "modal-wrapper";
    document.body.appendChild(wrapper);
    

    var modal = document.createElement('div');
    modal.id = "modal-confirmation";
    modal.innerHTML = '<div class="modal-success-header" id="modal-header"><h3><i class="fa fa-exclamation-circle" aria-hidden="true"></i>  Crear nuevo tema</h3><span data-confirm=0 class="modal-action-create" id="modal-close"><i class="fa fa-times" aria-hidden="true"></i></span></div><div id="modal-content"><div class="titulo-tema"><label for="title">Titulo</label><input type="text" id="title" name="title" placeholder="Introduce el titulo" required/></div><div class="mensaje-tema"><label for="message">Mensaje</label><textarea id="message" name="message" placeholder="Escribe tu mensaje" required/></textarea></div></div><div id="modal-buttons"><button class="modal-action-create" data-confirm=0 id="modal-button-no">No</button><button class="modal-action-create" data-confirm=1 id="modal-button-yes">Crear</button></div>';
    wrapper.appendChild(modal);

    setTimeout(function() {
        wrapper.classList.add('active');
    }, 100);

    var modalActions = wrapper.querySelectorAll('.modal-action-create');
    modalActions.forEach(function(action) {
        action.addEventListener('click', function() {
            var result = parseInt(this.dataset.confirm);
            if (result === 1) {
                var title = encodeURIComponent(document.getElementById('title').value);
                var message = encodeURIComponent(document.getElementById('message').value);
                let queryString = window.location.search; 
                let urlParams = new URLSearchParams(queryString);
                var idForo = urlParams.get('idForo');
                window.location.href = '/CreateTopic?idForo=' + idForo + '&title=' + title + '&message=' + message;
            } else {
                wrapper.classList.remove('active');
                setTimeout(function() {
                    wrapper.remove();
                }, 500);
            }
        });
    });
}
