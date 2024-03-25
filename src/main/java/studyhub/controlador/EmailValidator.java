package studyhub.controlador;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    // Expresión regular para validar un correo electrónico según RFC 5322
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * Método para validar un correo electrónico con la expresión regular.
     *
     * @param email correo electrónico a validar
     * @return true si el correo electrónico es válido, false de lo contrario
     */
    public static boolean validate(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
