package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Functions {
    public static List<Long> getIdsFromString(String string) {
        List<String> idList = Arrays.asList(string.split(";"));
        List<Long> idLongList = new ArrayList<>();
        idList.stream().forEach(x -> idLongList.add(Long.parseLong(x)));
        return idLongList;
    }

    public static String getStringFromIdLong(List<Long> ids) {
        String idString = "";
        for (Long id : ids) {
            idString += String.valueOf(id);
            idString += ";";
        }
        return idString;
    }

    public static String encryptPassword(String password) {
        String encryptedpassword = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedpassword;
    }

}
