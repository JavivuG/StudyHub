document.addEventListener("DOMContentLoaded", function() {
        var form = document.querySelector(".info-usuario");
        var boton = form.querySelector(".boton-enviar");

        var passwordInput = form.querySelector("input[name='password']");
        var confirmPasswordInput = form.querySelector("input[name='check_password']");
        var emailInput = form.querySelector("input[name='email']");
        var dateOfBirthInput = form.querySelector("input[name='fnacimiento']");
        var nicknameInput = form.querySelector("input[name='nickname']");
        var nameInput = form.querySelector("input[name='nombre']");
        var lastNameInput = form.querySelector("input[name='apellidos']");

        var errorContainerPassword = document.createElement("div");
        errorContainerPassword.classList.add("error-message");
        confirmPasswordInput.parentNode.appendChild(errorContainerPassword);
        var errorContainerPasswordSecure = document.createElement("div");
        errorContainerPasswordSecure.classList.add("error-message");
        passwordInput.parentNode.appendChild(errorContainerPasswordSecure);
        var errorContainerEmail = document.createElement("div");
        errorContainerEmail.classList.add("error-message");
        emailInput.parentNode.appendChild(errorContainerEmail);
        var errorContainerDate = document.createElement("div");
        errorContainerDate.classList.add("error-message");
        dateOfBirthInput.parentNode.appendChild(errorContainerDate);
        var errorContainerNickname = document.createElement("div");
        errorContainerNickname.classList.add("error-message");
        nicknameInput.parentNode.appendChild(errorContainerNickname);
        var errorContainerName = document.createElement("div");
        errorContainerName.classList.add("error-message");
        nameInput.parentNode.appendChild(errorContainerName);
        var errorContainerLastName = document.createElement("div");
        errorContainerLastName.classList.add("error-message");
        lastNameInput.parentNode.appendChild(errorContainerLastName);
        
        function validateName(){
            var name=nameInput.value;
            var isValid=true;

            if (name===''){
                errorContainerName.textContent("Debe introducir su nombre");
                isValid=false;
            }
        
            if(!isValid){
                disableButton();
            }
            else {
                enableButton();
            }
        }
        
        function validateLastName(){
            var apellidos=lastNameInput.value;
            var isValid=true;
            
            if (apellidos===''){
                errorContainerLastName.textContent("Debe introducir sus apellidos");
                isValid=false;
            }
            
            if(!isValid){
                disableButton();
            }
            else {
                enableButton();
            }           
        }

        function validatePasswords() {
            var password = passwordInput.value;
            var confirmPassword = confirmPasswordInput.value;
            var passwordRegex = /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d@$!%*?.&]{8,}$/;
            var isValid = true;
            errorContainerPassword.textContent = "";
            errorContainerPasswordSecure.textContent = "";
            
            if (!passwordRegex.test(password)) {
                errorContainerPasswordSecure.textContent = "La contraseña debe tener al menos 8 carácteres, 1 mayúscula y 1 dígito";

                isValid = false;
            }

            if (password !== confirmPassword) {
                errorContainerPassword.textContent = "Las contraseñas no coinciden";
                isValid = false;
            }


            
            if (!isValid) {
                disableButton();
            }
            else {
                enableButton();
            }

            return isValid;
        }

        function validateEmail() {
            var email = emailInput.value;
            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var respuesta;
            var isValid=true;
            
            if (email!==''){
                isValid=false;
            }

            if (!emailRegex.test(email)) {
                errorContainerEmail.textContent = "El correo electrónico no es válido";
                isValid=false;
            } else {
                errorContainerEmail.textContent = "";
                isValid=true;
            }
            
            if (isValid){
                var http = new XMLHttpRequest();
                http.open("POST","/CheckDuplicateServlet", true);
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                var params="email="+encodeURIComponent(email);
                http.send(params);
                http.onload = function() {
                    respuesta=http.responseText;
                    errorContainerEmail.textContent = respuesta;
                    if(respuesta!=='' || respuesta===null){
                        disableButton();
                    }
                    else {
                        enableButton();
                    }
                };
            }
            
            if (!isValid){
                disableButton();
            }
            else {
                
                enableButton();
            }
        }

        function validateDateOfBirth() {
            var dob = new Date(dateOfBirthInput.value);
            var today = new Date();
            var isValid=true;

            if (dob === '') {
                errorContainerDate.textContent = "Por favor, introduce tu fecha de nacimiento";
                isValid = false;
            }
            
            if (dob >= today) {
                errorContainerDate.textContent = "La fecha de nacimiento no es válida";
                isValid=false;
            } else {
                errorContainerDate.textContent = "";
                isValid=true;
            }
            
            if(!isValid){
                disableButton();
            }
            else {
                enableButton();
            }
        }

        function validateNickname() {
            var nickname = nicknameInput.value.trim();
            var respuesta;
            var isValid = true;
            
            if(nickname!==''){
                isValid=false;
            }

            if (nickname.length < 5 || nickname.length > 15) {
                errorContainerNickname.textContent = "El nickname debe tener entre 5 y 15 carácteres";
                isValid = false;

            } else {
                errorContainerNickname.textContent = "";
                isValid=true;
            }
            
            if (isValid){
                var http = new XMLHttpRequest();
                http.open("POST","/CheckDuplicateServlet", true);
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                var params="nickname="+encodeURIComponent(nickname);
                http.send(params);
                http.onload = function() {
                    respuesta=http.responseText;
                    errorContainerNickname.textContent = respuesta;
                    if(respuesta!=='' || respuesta===null){
                        disableButton();
                    }
                    else {
                        enableButton();
                    }
                };
            }

            if(!isValid){
                disableButton();
            }
            else {
                enableButton();
            }
        }
        
        function disableButton(){
            boton.classList.add("prevent-default");
            boton.disabled=true;
        }
        
        function enableButton(){
            boton.classList.remove("prevent-default");
            boton.disabled=false;
        }


        passwordInput.addEventListener("input", validatePasswords);
        confirmPasswordInput.addEventListener("input", validatePasswords);
        emailInput.addEventListener("input", validateEmail);
        dateOfBirthInput.addEventListener("input", validateDateOfBirth);
        nicknameInput.addEventListener("input", validateNickname);
        nameInput.addEventListener("input", validateName);
        lastNameInput.addEventListener("input", validateLastName);
    });