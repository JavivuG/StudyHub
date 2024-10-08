<%@page import="studyhub.business.AportacionUsuario"%>
<%@page import="studyhub.business.ContribucionAsignatura"%>
<%@page import="studyhub.controlador.UserInfo"%>
<%@page import="studyhub.business.Asignatura"%>
<%@page import="studyhub.business.Tema"%>
<%@page import="studyhub.business.Comentario"%>
<%@page import="studyhub.business.Comentario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="studyhub.business.Fichero"%>
<%@ page language="java" %> 
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="studyhub.business.User" %>
<jsp:include page="/LoadProfile" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <link rel="shortcut icon" href="images/logo.svg" />
        <title>StudyHub</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="styles/footer.css" />
        <link rel="stylesheet" href="styles/common.css" />
        <link rel="stylesheet" href="styles/header.css" />
        <link rel="stylesheet" href="styles/profile.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&amp;display=swap"
            rel="stylesheet"
        />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
        />
        <link rel="icon" type="image/png" href="images/icono.png" />
        <script src="scripts/logo.js"></script>
    </head>
    <body>
        <div class="main-content">
            <header>
                <div class="container">
                    <nav class="nav-main">
                        <a href="index.jsp">
                            <svg
                                class="logo-header"
                                width="262"
                                height="48"
                                viewBox="0 0 262 48"
                                fill="none"
                                xmlns="http://www.w3.org/2000/svg"
                            >
                                <g id="Encabezado">
                                    <path
                                        id="StudyHub"
                                        d="M68.3097 18.2756C68.196 17.1297 67.7083 16.2396 66.8466 15.6051C65.9848 14.9706 64.8153 14.6534 63.3381 14.6534C62.3343 14.6534 61.4867 14.7955 60.7955 15.0795C60.1042 15.3542 59.5739 15.7377 59.2045 16.2301C58.8447 16.7225 58.6648 17.2812 58.6648 17.9062C58.6458 18.4271 58.7547 18.8816 58.9915 19.2699C59.2377 19.6581 59.5739 19.9943 60 20.2784C60.4261 20.553 60.9186 20.7945 61.4773 21.0028C62.036 21.2017 62.6326 21.3722 63.267 21.5142L65.8807 22.1392C67.1496 22.4233 68.3144 22.8021 69.375 23.2756C70.4356 23.7491 71.3542 24.3314 72.1307 25.0227C72.9072 25.714 73.5085 26.5284 73.9347 27.4659C74.3703 28.4034 74.5928 29.4782 74.6023 30.6903C74.5928 32.4706 74.1383 34.0142 73.2386 35.321C72.3485 36.6184 71.0606 37.6269 69.375 38.3466C67.6989 39.0568 65.6771 39.4119 63.3097 39.4119C60.9612 39.4119 58.9157 39.0521 57.1733 38.3324C55.4403 37.6127 54.0862 36.5473 53.1108 35.1364C52.1449 33.7159 51.6383 31.9593 51.5909 29.8665H57.5426C57.6089 30.8419 57.8883 31.6562 58.3807 32.3097C58.8826 32.9536 59.5502 33.4413 60.3835 33.7727C61.2263 34.0947 62.178 34.2557 63.2386 34.2557C64.2803 34.2557 65.1847 34.1042 65.9517 33.8011C66.7282 33.4981 67.3295 33.0767 67.7557 32.5369C68.1818 31.9972 68.3949 31.3769 68.3949 30.6761C68.3949 30.0227 68.2008 29.4735 67.8125 29.0284C67.4337 28.5833 66.875 28.2045 66.1364 27.892C65.4072 27.5795 64.5123 27.2955 63.4517 27.0398L60.2841 26.2443C57.8314 25.6477 55.8949 24.715 54.4744 23.446C53.054 22.1771 52.3485 20.4678 52.358 18.3182C52.3485 16.5568 52.8172 15.018 53.7642 13.7017C54.7206 12.3854 56.0322 11.358 57.6989 10.6193C59.3655 9.88068 61.2595 9.51136 63.3807 9.51136C65.5398 9.51136 67.4242 9.88068 69.0341 10.6193C70.6534 11.358 71.9129 12.3854 72.8125 13.7017C73.7121 15.018 74.1761 16.5426 74.2045 18.2756H68.3097ZM90.2308 17.1818V21.7273H77.0916V17.1818H90.2308ZM80.0746 11.9545H86.1257V32.2955C86.1257 32.8542 86.2109 33.2898 86.3814 33.6023C86.5518 33.9053 86.7886 34.1184 87.0916 34.2415C87.4041 34.3646 87.764 34.4261 88.1712 34.4261C88.4553 34.4261 88.7393 34.4025 89.0234 34.3551C89.3075 34.2983 89.5253 34.2557 89.6768 34.2273L90.6286 38.7301C90.3255 38.8248 89.8994 38.9337 89.3501 39.0568C88.8009 39.1894 88.1333 39.2699 87.3473 39.2983C85.889 39.3551 84.6106 39.161 83.5121 38.7159C82.4231 38.2708 81.5755 37.5795 80.9695 36.642C80.3634 35.7045 80.0651 34.5208 80.0746 33.0909V11.9545ZM108.164 29.7102V17.1818H114.215V39H108.406V35.0369H108.178C107.686 36.3153 106.867 37.3428 105.721 38.1193C104.585 38.8958 103.197 39.2841 101.559 39.2841C100.101 39.2841 98.8175 38.9527 97.7095 38.2898C96.6016 37.6269 95.7351 36.6847 95.1101 35.4631C94.4946 34.2415 94.1821 32.7784 94.1726 31.0739V17.1818H100.224V29.9943C100.233 31.2822 100.579 32.3002 101.261 33.0483C101.942 33.7964 102.856 34.1705 104.002 34.1705C104.731 34.1705 105.413 34.0047 106.048 33.6733C106.682 33.3324 107.193 32.8305 107.582 32.1676C107.979 31.5047 108.174 30.6856 108.164 29.7102ZM127.095 39.3551C125.438 39.3551 123.937 38.929 122.592 38.0767C121.257 37.215 120.196 35.9508 119.411 34.2841C118.634 32.608 118.246 30.553 118.246 28.1193C118.246 25.6193 118.648 23.5407 119.453 21.8835C120.258 20.2169 121.328 18.9716 122.663 18.1477C124.008 17.3144 125.481 16.8977 127.081 16.8977C128.303 16.8977 129.321 17.1061 130.135 17.5227C130.959 17.9299 131.622 18.4413 132.124 19.0568C132.635 19.6629 133.023 20.2595 133.288 20.8466H133.473V9.90909H139.51V39H133.544V35.5057H133.288C133.004 36.1117 132.602 36.7131 132.081 37.3097C131.57 37.8968 130.902 38.3845 130.078 38.7727C129.264 39.161 128.269 39.3551 127.095 39.3551ZM129.013 34.5398C129.988 34.5398 130.812 34.2746 131.484 33.7443C132.166 33.2045 132.687 32.4517 133.047 31.4858C133.416 30.5199 133.601 29.3883 133.601 28.0909C133.601 26.7936 133.421 25.6667 133.061 24.7102C132.701 23.7538 132.18 23.0152 131.499 22.4943C130.817 21.9735 129.988 21.7131 129.013 21.7131C128.018 21.7131 127.18 21.983 126.499 22.5227C125.817 23.0625 125.301 23.8106 124.95 24.767C124.6 25.7235 124.425 26.8314 124.425 28.0909C124.425 29.3598 124.6 30.482 124.95 31.4574C125.31 32.4233 125.826 33.1809 126.499 33.7301C127.18 34.2699 128.018 34.5398 129.013 34.5398ZM148.221 47.1818C147.454 47.1818 146.734 47.1203 146.062 46.9972C145.399 46.8835 144.85 46.7367 144.414 46.5568L145.778 42.0398C146.488 42.2576 147.127 42.3759 147.695 42.3949C148.273 42.4138 148.77 42.2813 149.187 41.9972C149.613 41.7131 149.959 41.2301 150.224 40.5483L150.579 39.625L142.752 17.1818H149.116L153.633 33.2045H153.86L158.42 17.1818H164.826L156.346 41.358C155.939 42.5322 155.385 43.5549 154.684 44.4261C153.993 45.3068 153.117 45.9839 152.056 46.4574C150.996 46.9403 149.717 47.1818 148.221 47.1818ZM168.036 39V9.90909H174.187V21.9119H186.673V9.90909H192.809V39H186.673V26.983H174.187V39H168.036ZM211.758 29.7102V17.1818H217.809V39H211.999V35.0369H211.772C211.28 36.3153 210.46 37.3428 209.315 38.1193C208.178 38.8958 206.791 39.2841 205.153 39.2841C203.694 39.2841 202.411 38.9527 201.303 38.2898C200.195 37.6269 199.329 36.6847 198.704 35.4631C198.088 34.2415 197.776 32.7784 197.766 31.0739V17.1818H203.817V29.9943C203.827 31.2822 204.173 32.3002 204.854 33.0483C205.536 33.7964 206.45 34.1705 207.596 34.1705C208.325 34.1705 209.007 34.0047 209.641 33.6733C210.276 33.3324 210.787 32.8305 211.175 32.1676C211.573 31.5047 211.767 30.6856 211.758 29.7102ZM222.763 39V9.90909H228.814V20.8466H228.999C229.264 20.2595 229.647 19.6629 230.149 19.0568C230.661 18.4413 231.323 17.9299 232.138 17.5227C232.962 17.1061 233.984 16.8977 235.206 16.8977C236.797 16.8977 238.265 17.3144 239.609 18.1477C240.954 18.9716 242.029 20.2169 242.834 21.8835C243.639 23.5407 244.041 25.6193 244.041 28.1193C244.041 30.553 243.648 32.608 242.862 34.2841C242.086 35.9508 241.025 37.215 239.68 38.0767C238.345 38.929 236.849 39.3551 235.192 39.3551C234.018 39.3551 233.018 39.161 232.195 38.7727C231.38 38.3845 230.713 37.8968 230.192 37.3097C229.671 36.7131 229.273 36.1117 228.999 35.5057H228.729V39H222.763ZM228.686 28.0909C228.686 29.3883 228.866 30.5199 229.226 31.4858C229.586 32.4517 230.107 33.2045 230.788 33.7443C231.47 34.2746 232.299 34.5398 233.274 34.5398C234.259 34.5398 235.092 34.2699 235.774 33.7301C236.456 33.1809 236.972 32.4233 237.322 31.4574C237.682 30.482 237.862 29.3598 237.862 28.0909C237.862 26.8314 237.687 25.7235 237.337 24.767C236.986 23.8106 236.47 23.0625 235.788 22.5227C235.107 21.983 234.268 21.7131 233.274 21.7131C232.289 21.7131 231.456 21.9735 230.774 22.4943C230.102 23.0152 229.586 23.7538 229.226 24.7102C228.866 25.6667 228.686 26.7936 228.686 28.0909Z"
                                        fill="white"
                                    />
                                    <g id="logo">
                                        <g id="base">
                                            <path
                                                id="Abajo"
                                                d="M28.05 29.0667C29.1666 28.3333 30.6333 29.1333 30.6333 30.4667V32.6167C30.6333 34.7333 28.9833 37 27 37.6667L21.6833 39.4333C20.75 39.75 19.2333 39.75 18.3166 39.4333L13 37.6667C11 37 9.36664 34.7333 9.36664 32.6167V30.45C9.36664 29.1333 10.8333 28.3333 11.9333 29.05L15.3666 31.2833C16.6833 32.1667 18.35 32.6 20.0166 32.6C21.6833 32.6 23.35 32.1667 24.6666 31.2833L28.05 29.0667Z"
                                                fill="white"
                                            />
                                        </g>
                                        <g id="arriba">
                                            <path
                                                id="Birrete"
                                                d="M33.3 13.7667L23.3166 7.21666C21.5166 6.03333 18.55 6.03333 16.75 7.21666L6.71664 13.7667C3.49997 15.85 3.49997 20.5667 6.71664 22.6667L9.3833 24.4L16.75 29.2C18.55 30.3833 21.5166 30.3833 23.3166 29.2L30.6333 24.4L32.9166 22.9V28C32.9166 28.6833 33.4833 29.25 34.1666 29.25C34.85 29.25 35.4166 28.6833 35.4166 28V19.8C36.0833 17.65 35.4 15.15 33.3 13.7667Z"
                                            />
                                        </g>
                                    </g>
                                </g>
                            </svg>
                        </a>

                        <ul class="nav-menu">
                            <li>
                                <a href="dashboard.jsp" class="boton-inicio"
                                    >Ir al Dashboard</a
                                >
                            </li>
                            <li>
                                <a href="/LogOut" class="boton-logout"
                                    >Cerrar Sesión</a
                                >
                            </li>
                        </ul>
                    </nav>
                </div>
            </header>

            
            <div class="flex">
                <div class="perfil">
                    <img src="images/profile.svg" alt="user" id="profile" />
                    <div class="datos-perfil">
                        <% User usuario=(User) session.getAttribute("user"); %>
                        <p id="nickname"><%= usuario.getNickname()%></p>
                        <p id="name"><%= usuario.getNombre() %> <%= usuario.getApellidos() %></p>
                        <p id="email"><%= usuario.getEmail()%></p>
                        <p id="birthday"><%= usuario.getFecha_nacimiento().toString() %></p>
                        <p id="role"><%= usuario.getRol().substring(0,1).toUpperCase()+usuario.getRol().substring(1) %></p>
                    </div>
                </div>

                
                <div class="estadisticas">
                    <div class="asignaturas-contribuidas">
                        <h2>Asignaturas más contribuidas</h2>
                        <% ArrayList<ContribucionAsignatura> contribuciones = (ArrayList <ContribucionAsignatura>) session.getAttribute("contribuciones"); %>
                        <div class="asignaturas<%if (contribuciones==null || contribuciones.isEmpty() || contribuciones.size()==1) {%> sin-elementos<% } %>">
                            <% 
                                if (contribuciones.size()>0){ 
                                    for (int i=0; i<contribuciones.size(); i++){
                                        ContribucionAsignatura contribucionActual=contribuciones.get(i);%>
                                        <a class="asignatura-box" href="forum.jsp?idForo=<%=contribucionActual.getForo().getID_asignatura()%>">
                                            <div class="contribuidas">
                                                <%= contribucionActual.getNombreForo() %>
                                            </div>
                                            <div class="opiniones-bar">
                                                <div class="progress progress-moved">
                                                    <% int porcentaje=contribucionActual.getPorcentajeContribucion(); %>
                                                    <div class="progress-bar" style="--progress-width: <%= porcentaje %>%;">
                                                        <div class="porcentaje"><%= porcentaje %> %</div>
                                                    </div>
                                                </div>
                                            </div>
                                          
                                        </a>
                            <%      }
                                } 
                                else { %>
                                    <p class="mensaje-vacio-profile">No has aportado contenido en StudyHub</p>
                                <% } %>
                        </div>
                    </div>
                    <div class="ultimas-subidas">
                        <h2>Últimas subidas</h2>
                        <% Fichero ficheroActual;
                                ArrayList<Fichero> ficherosUsuario=(ArrayList<Fichero>) session.getAttribute("ficheros_usuario"); %>

                            <div class="ultimas-subidas-box<% if (ficherosUsuario == null || ficherosUsuario.isEmpty()) { %> sin-elementos<% } %>">
                                <%
                                if (ficherosUsuario.size()>0){
                                for (int i=0; i<ficherosUsuario.size(); i++){ 
                                    ficheroActual=ficherosUsuario.get(i); %>
                                    <div class="ultimas-subidas-container">
                                        <div class="contribuidas-titulo">
                                            <% if (ficheroActual.getNombre().length()>19){ %>
                                                <%= ficheroActual.getNombre().substring(0, 19   ) %>...
                                            <% }
                                            else { %>
                                                <%= ficheroActual.getNombre() %>
                                            <% } %>
                                        </div>
                                        <div class="descarga">
                                            <a href="/downloadServlet?idFichero=<%= ficheroActual.getId_fichero()%>&idComentario=<%=ficheroActual.getId_comentario()%>&idForo=<%=ficheroActual.getId_foro()%>"
                                                ><img
                                                    src="images/download.svg"
                                                    alt="download"
                                            /></a>
                                        </div>
                                    </div>
                                <% } %>
                                <div class="ultimas-subidas-container">
                                        <div class="contribuidas-titulo">
                                            Ver mis ficheros
                                        </div>
                                        <div class="descarga">
                                            <a href="my_files.jsp"
                                                ><img
                                                        src="images/more.svg"
                                                    alt="download"
                                            /></a>
                                        </div>
                                    </div>
                                <% }
                                else { %>
                                    <p class="mensaje-vacio-profile">No has subido ningun fichero</p>
                                <% } %>
                        
                        </div>
                    </div>
                    <div class="ultimos-mensajes">
                        <h2>Mis últimos mensajes</h2>
                        
                          <%
                        int suma = 0;
                        ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>) session.getAttribute("comentarios");
                        ArrayList<Tema> listaTemas = (ArrayList<Tema>) session.getAttribute("temas");
                        ArrayList<Asignatura> listaAsignaturas = (ArrayList<Asignatura>) session.getAttribute("asignaturas");
                        
                        if (listaComentarios.size()>4){
                        for(int i = listaComentarios.size() -1 ; i > listaComentarios.size() - 5; i--){
                        if((suma+i) == -1) break;
                        while(!listaComentarios.get(i+suma).getNickname().equals(UserInfo.getUserNickname(request))){
                            suma--;
                            if((suma+i) == -1) break;
                         }
                        if((suma+i)== -1) break; 
                        int idTemaActual = listaComentarios.get(i+suma).getId_tema();
                        %>
                        <a href="topic.jsp?idForo=<%=listaTemas.get(idTemaActual-1).getId_foro()%>&idTema=<%= idTemaActual %>">
                        <div class="mensaje-box"> 
                            <div class="contribuidas-titulo">
                                 Foro <%= listaAsignaturas.get(listaTemas.get(idTemaActual-1).getId_foro()-1).getNombre() %>
                            </div>
                            <div class="mensaje">
                                <% String texto=listaComentarios.get(i+suma).getTexto();
                                if (texto.equals("")){ %>
                                    Has compartido un fichero
                                <% }
                                else { %>
                                    Has comentado: <%= texto %>
                                <% } %>
                            </div>
                        </div>
                            
                        </a>
                        <%}
                        } else { %>
                            <p class="mensaje-vacio-profile">No has hecho ningun comentario<p> 
                        <% } %>
                    </div>
                    <div class="podio">
                        <h2>Posición en el podio</h2>
                        <% ArrayList<AportacionUsuario> top_usuario=(ArrayList<AportacionUsuario>) session.getAttribute("top_usuarios");                        
                        if (top_usuario.size()>0){ 
                            for (int i=0; i<top_usuario.size(); i++){ %>
                                <% AportacionUsuario top_usuario_actual=top_usuario.get(i); %>
                                <div class="mensaje-box <% if (top_usuario_actual.getNickname().equals(UserInfo.getUserNickname(request))) { %>top-personal<% } %>">
                                    <div class="contribuidas-titulo"><%= i+1 %> - <%= top_usuario_actual.getNickname()%></div>
                                    <div class="mensaje">
                                        <%= top_usuario_actual.getMensaje(UserInfo.getUserNickname(request)) %>
                                    </div>
                                </div>
                        <%  }
                        } 
                        else { %>
                            <p class="mensaje-vacio-profile">No hay usuarios en el top<p>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pie de pagina -->
        <footer>
            <div class="info">
                <h1>StudyHub</h1>
                <ul>
                    <li><a href="#">Cookies</a></li>
                    <li><a href="#">Términos y Condiciones</a></li>
                    <li><a href="#">Privacidad</a></li>
                </ul>
            </div>

            <div class="otros">
                <ul id="socials">
                    <li><a href="contact.jsp">Contacto</a></li>
                    <li><a href="#">Preguntas frecuentes</a></li>
                </ul>
                <p id="copyright">© StudyHub | 2024</p>
                <ul id="socials">
                    <li>
                        <a href="#"
                            ><img src="images/tiktok.svg" alt="tiktok"
                        /></a>
                    </li>
                    <li>
                        <a href="#"
                            ><img src="images/facebook.svg" alt="facebook"
                        /></a>
                    </li>
                    <li>
                        <a href="#"
                            ><img src="images/instagram.svg" alt="instagram"
                        /></a>
                    </li>
                    <li>
                        <a href="#"
                            ><img src="images/twitter.svg" alt="twitter"
                        /></a>
                    </li>
                </ul>
            </div>
        </footer>
    </body>
</html>
