package br.com.myteams.util;

import br.com.myteams.exception.InfraestruturaException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaUtil
{

    public static String criptografa(String s)
    {
        MessageDigest algorithm;
        try
        {
            algorithm = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = algorithm.digest(s.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest)
            {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new InfraestruturaException("Erro ao criptografar a senha", e);
        }

    }
}
