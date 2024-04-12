const dropArea = document.querySelector(".drag-area");
const dragText = document.querySelector(".header");
const preview = document.querySelector(".preview");

let input = dropArea.querySelector("input");
let file;

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id_foro = urlParams.get('idForo');

// Trigger file selection when browse button is clicked
dropArea.querySelector(".button").onclick = () => {
    input.click();
};

// Handle file selection
input.addEventListener("change", function () {
    file = this.files[0];
    displayFile(file);
});

// Handle file drag over
dropArea.addEventListener("dragover", (event) => {
    event.preventDefault();
    dropArea.classList.add("active");
    dragText.textContent = "Suelta para subir";
});

// Handle file drag leave
dropArea.addEventListener("dragleave", () => {
    dropArea.classList.remove("active");
    dragText.textContent = "Arrastra un fichero hasta aquí";
});

// Handle file drop
dropArea.addEventListener("drop", (event) => {
    event.preventDefault();
    dropArea.classList.remove("active");
    file = event.dataTransfer.files[0];
    displayFile(file);
});

// Function to display file preview
function displayFile(file) {
    let fileType = file.type;

    let validExtensions = ["image/jpeg", "image/jpg", "image/png", "application/pdf"];

    if (validExtensions.includes(fileType)) {
        document.getElementById("preview").style.display = 'flex';
        document.getElementById("send").disabled = false;
        document.getElementById("send").classList.add("sendBtn");
        document.getElementById("send").classList.remove("sendBtnDisabled");

        dragText.textContent = "Has subido el fichero: " + file.name + ". Arrastra otro para reemplazar";

        let fileReader = new FileReader();
        fileReader.onload = () => {
            let fileURL = fileReader.result;
            let tag;

            if (fileType.includes('image')) {
                // Create image element for image preview
                tag = document.createElement('img');
            } else if (fileType === 'application/pdf') {
                // Create iframe element for PDF preview
                tag = document.createElement('iframe');
                tag.width = "100%";
                tag.height = "500px";
            }

            tag.src = fileURL;

            preview.innerHTML = "";
            preview.appendChild(tag);

            document.getElementById("send").addEventListener('click', () => {
                submitFile(file);
            });
        };
        fileReader.readAsDataURL(file);
    } else {
        Swal.fire({
            title: '¡Error!',
            text: 'Tipo de archivo no soportado.',
            icon: 'error',
            timer: 4000,
            timerProgressBar: true,
            didOpen: () => {
                Swal.showLoading();
            }
        });
        dropArea.classList.remove("active");
    }
}

// Function to submit file to servlet
function submitFile(file) {
    let formData = new FormData();
    formData.append('file', file);
    formData.append('idForo', id_foro);

    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'uploadServlet', true);
    xhr.onload = function () {
        if (xhr.status === 200)
            window.location.href = "files.jsp?idForo=" + id_foro;
        else
            window.location.href = "upload.jsp?idForo=" + id_foro;
    };
    xhr.send(formData);
}
