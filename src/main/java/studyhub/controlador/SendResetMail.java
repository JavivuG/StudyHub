import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.PasswordAuthentication;  
import studyhub.data.TokenDB;
import studyhub.data.UserDB;
import java.util.UUID;

@WebServlet(name = "SendResetMail", urlPatterns = {"/SendResetMail"})
public class SendResetMail extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Datos del remitente
        String remitente = "studyhub.services@gmail.com";
        String clave = "jtfp qpwz lhzo fwie";

        // Datos del destinatario
        String destinatario = request.getParameter("email");

        // Configuración de las propiedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Autenticación del remitente
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            // Crear un objeto MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            
            String token=java.util.UUID.randomUUID().toString();
            String passwordTemporal=request.getParameter("password");
            String nickname=UserDB.selectUser(destinatario).getNickname();
            
            TokenDB.generateToken(nickname, passwordTemporal, token,LocalDateTime.now().plusMinutes(30));
            message.setSubject("Restablecer tu contraseña de Studyhub");

            // Contenido del mensaje en HTML
            String contenidoHTML = "<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Confirmación de Reset de Contraseña</title>\n" +
"    <style>\n" +
"        body {\n" +
"            font-family: Arial, sans-serif;\n" +
"            background-color: #f4f4f4;\n" +
"            margin: 0;\n" +
"            padding: 0;\n" +
"        }\n" +
"        .container {\n" +
"            max-width: 600px;\n" +
"            margin: 20px auto;\n" +
"            background-color: #ffffff;\n" +
"            padding: 20px;\n" +
"            border-radius: 8px;\n" +
"            box-shadow: 0 4px 8px rgba(0,0,0,0.1);\n" +
"        }\n" +
"        h1 {\n" +
"            color: #333333;\n" +
"            margin-bottom: 20px;\n" +
"        }\n" +
"        p {\n" +
"            color: #666666;\n" +
"            margin-bottom: 20px;\n" +
"        }\n" +
"        .button {\n" +
"            display: inline-block;\n" +
"            background-color: #007bff;\n" +
"            padding: 10px 20px;\n" +
"            text-decoration: none;\n" +
"            border-radius: 5px;\n" +
"        }\n" +
"        .button:hover {\n" +
"            background-color: #0056b3;\n" + // Color de fondo más oscuro al pasar el ratón
"        }\n" +
"        .footer {\n" +
"            margin-top: 20px;\n" +
"            text-align: center;\n" +
"            color: #999999;\n" +
"        }\n" +                       
"    </style>\n" +
"</head>\n" +
"<body>\n" +
"    <div class=\"container\">\n" +
"        <h1>Confirmación de Reset de Contraseña</h1>\n" +
"        <p>¡Hola "+nickname+"!</p>\n" +
"        <p>Hemos recibido una solicitud para restablecer la contraseña de tu cuenta. Si has realizado esta solicitud, haz clic en el siguiente botón para continuar con el proceso:</p>\n" +
"        <a href=\"http://localhost:8080/ResetPassword?token="+token+"\" class=\"button\">"+ 
"        <table>\n" +
"        <tr>\n" +
"            <td style=\"color: #FFFFFF\">Restablecer Contraseña</td>\n" +
"        </tr>\n" +
"    </table></a>\n" +
"        <p>Si no has solicitado un restablecimiento de contraseña, puedes ignorar este correo electrónico de forma segura.</p>\n" +
"    </div>\n" +
"    <div class=\"footer\">\n" +
"        Este correo electrónico ha sido enviado automáticamente. Por favor, no respondas a este correo.\n" +
"    </div>\n" +
"</body>\n" +
"</html>";


            // Establecer el contenido del mensaje como HTML
            message.setContent(contenidoHTML, "text/html");

            // Enviar el mensaje
            Transport.send(message);
            response.sendRedirect("dashboard.jsp");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
