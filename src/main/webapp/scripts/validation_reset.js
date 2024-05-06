document.addEventListener("DOMContentLoaded", function() {
    var form = document.querySelector(".info-usuario");
    var boton = form.querySelector(".boton-enviar");

    var passwordInput = form.querySelector("input[name='password']");
    var confirmPasswordInput = form.querySelector("input[name='check_password']");
    var emailInput = form.querySelector("input[name='email']");
    
    var errorContainerPasswordSecure = document.createElement("div");
    errorContainerPasswordSecure.classList.add("error-message");
    passwordInput.parentNode.appendChild(errorContainerPasswordSecure);

    var errorContainerPassword = document.createElement("div");
    errorContainerPassword.classList.add("error-message");
    confirmPasswordInput.parentNode.appendChild(errorContainerPassword);
    
    var errorContainerEmail = document.createElement("div");
    errorContainerEmail.classList.add("error-message");
    emailInput.parentNode.appendChild(errorContainerEmail);

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
            http.open("POST","/CheckRegisteredEmail", true);
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            var params="email="+encodeURIComponent(email);
            http.send(params);
            http.onload = function() {
                if (http.readyState===4){
                    if(http.status===200){
                        respuesta=http.responseText;
                        errorContainerEmail.textContent = respuesta;
                        if(respuesta!=='' || respuesta===null){
                            disableButton();
                        }
                        else {
                            enableButton();
                        }
                    }
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
});