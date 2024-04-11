const dropArea = document.querySelector(".drag-area");
const dragText = document.querySelector(".header");
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
        let fileReader = new FileReader();
        fileReader.onload = () => {
            let fileURL = fileReader.result;
            if (fileType.includes('image')) {
                // Create image element for image preview
                let imgTag = document.createElement('img');
                imgTag.src = fileURL;
                imgTag.alt = "";

                // Create container for preview and upload button
                let previewContainer = document.createElement('div');
                previewContainer.classList.add('preview-container');
                previewContainer.appendChild(imgTag);

                document.getElementById("send").addEventListener('click', () => {
                    submitFile(file);
                });
                // Clear existing content and append the preview container
                dropArea.innerHTML = "";
                dropArea.appendChild(previewContainer);
            } else if (fileType === 'application/pdf') {
                // Create iframe element for PDF preview
                let pdfTag = document.createElement('iframe');
                pdfTag.src = fileURL;
                pdfTag.width = "100%";
                pdfTag.height = "500px";

                // Create container for PDF preview and upload button
                let previewContainer = document.createElement('div');
                previewContainer.classList.add('preview-container');
                previewContainer.appendChild(pdfTag);

                document.getElementById("send").addEventListener('click', () => {
                    submitFile(file);
                });

                // Clear existing content and append the preview container
                dropArea.innerHTML = "";
                dropArea.appendChild(previewContainer);
            }
        };
        fileReader.readAsDataURL(file);
    } else {
        alert("Este tipo de archivo no puede subirse");
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
        if (xhr.status === 200) window.location.href="files.jsp?idForo=" + id_foro;
        else window.location.href="upload.jsp?idForo=" + id_foro;
    };
    xhr.send(formData);
}
