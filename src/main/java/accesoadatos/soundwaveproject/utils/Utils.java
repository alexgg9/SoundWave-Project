package accesoadatos.soundwaveproject.utils;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * Muestra un cuadro de diálogo emergente (pop-up).
     *
     * @param title Título del cuadro de diálogo.
     * @param header Encabezado del cuadro de diálogo.
     * @param text Texto del cuerpo del cuadro de diálogo.
     * @param type Tipo de alerta (por ejemplo, INFORMATION, WARNING, ERROR).
     * @return La instancia del cuadro de diálogo mostrado.
     */
    public static Alert showPopUp(String title, String header, String text, Alert.AlertType type) {
        Alert alertDialog = new Alert(type);
        alertDialog.setTitle(title);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(text);

        alertDialog.show();

        // Asegura que el cuadro de diálogo esté en primer plano.
        Stage s = (Stage) alertDialog.getDialogPane().getScene().getWindow();
        s.toFront();

        return alertDialog;
    }

    /**
     * Convierte la duración de una canción (en segundos) en una cadena con formato "mm:ss".
     *
     * @param duracion Duración en segundos.
     * @return Cadena de duración formateada.
     */
    public static String duracionToString(long duracion) {
        long minutos = duracion / 60;
        long segundos = duracion % 60;
        String segundosStr = segundos < 10 ? "0" + segundos : String.valueOf(segundos);
        return String.format("%d:%s", minutos, segundosStr);
    }

    /**
     * Verifica si una dirección de correo electrónico tiene un formato válido.
     *
     * @param email Dirección de correo electrónico a verificar.
     * @return true si el formato es válido, false en caso contrario.
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Encripta una cadena utilizando el algoritmo SHA-256.
     *
     * @param s Cadena a encriptar.
     * @return Cadena encriptada.
     */
    public static String encryptSHA256(String s) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");

            md.update(s.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte aByte : md.digest()) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Convierte un arreglo de bytes en una imagen.
     *
     * @param byteArray Arreglo de bytes que representa la imagen.
     * @return Objeto Image creado a partir del arreglo de bytes.
     */
    public static Image convertBytesToArray(byte[] byteArray) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray)) {
            return new Image(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

