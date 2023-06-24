package gov.iti.jets.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Utility {

    public static String ACCEPTED_STATUS = "accepted";
    public static String REJECTED_STATUS = "rejected";
    public static String WAITING_STATUS = "waiting";
    public static final Integer INVALID_USER = -1;
    public static final Integer INVALID_CATEGORY = -2;
    public static final String ADMIN_ROLE = "admin";
    public static final String USER_ROLE = "user";

    public static String hashPassword(String input) {
        String sha256hex = Hashing.sha256()
                .hashString(input, StandardCharsets.UTF_8)
                .toString();
        return  sha256hex;
    }
}
