package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Không thể sử dụng thuật toán SHA-256", e);
        }
    }
    public static void main(String[] args) {
        String password = "123456";
        String hashedPassword = hashPassword(password);
        System.out.println("Mật khẩu đã được hash: " + hashedPassword);
    }
}
