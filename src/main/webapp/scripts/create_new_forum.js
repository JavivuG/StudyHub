var botones = document.querySelectorAll('.new-forum');

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
    modal.innerHTML = '<div id="modal-header"><h3><i class="fa fa-exclamation-circle" aria-hidden="true"></i> Crear nueva asignatura</h3><span data-confirm=0 class="modal-action" id="modal-close"><i class="fa fa-times" aria-hidden="true"></i></span></div><div id="modal-content"><div class="nombre-asig"><label for="asignatura">Asignatura</label><input type="text" id="asignatura" name="asignatura" placeholder="Introduce el nombre de la asignatura" required/></div><div class="curso-asig"><label for="curso">Curso (Número del 1 al 10)</label><input type="number" min="1" max="10" id="curso" name="curso" required></div><div class="carrera-asig"><div class="grado-asig"><label for="grado">Grado</label><input type="text" id="grado" name="grado" placeholder="Introduce el grado en el que se imparte" required/></div></div></div><div id="modal-buttons"><button class="modal-action" data-confirm=0 id="modal-button-no">No</button><button class="modal-action" data-confirm=1 id="modal-button-yes">Crear</button></div>';
    wrapper.appendChild(modal);

    setTimeout(function() {
        wrapper.classList.add('active');
    }, 100);

    var modalActions = wrapper.querySelectorAll('.modal-action');
    modalActions.forEach(function(action) {
        action.addEventListener('click', function() {
            var result = parseInt(this.dataset.confirm);
            if (result === 1) {
                var asignatura = document.getElementById('asignatura').value;
                var curso = document.getElementById('curso').value;
                var grado = document.getElementById('grado').value;
                window.location.href = '/CreateNewForum?asignatura='+asignatura+'&curso='+curso+'&grado='+grado;
            } else {
                wrapper.classList.remove('active');
                setTimeout(function() {
                    wrapper.remove();
                }, 500);
            }
        });
    });
}
