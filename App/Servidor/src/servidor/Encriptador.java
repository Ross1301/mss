package servidor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador 
{
    private static MessageDigest md;

    public String cryptWithMD5(String pass)
    {
        String respuesta = null;
        try 
        {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++)
            {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            respuesta = sb.toString();
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            //Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "Error";
        }
        return respuesta;
   }
}
