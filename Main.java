import java.security.*;

public class Main {
public static void main (String[]args)
     throws Exception
     {
// Generate a key pair
       KeyPairGenerator keyPairGenerator =
	 KeyPairGenerator.getInstance ("RSA");
         keyPairGenerator.initialize (2048);	// Key size in bits
       KeyPair keyPair = keyPairGenerator.generateKeyPair ();
// Get the private key
       PrivateKey privateKey = keyPair.getPrivate ();
// Generate a signature
       Signature signature = Signature.getInstance ("SHA256withRSA");
         signature.initSign (privateKey);
// Text to be signed
       String message = "Hello, world!";
         System.out.println ("Message: " + message);
         signature.update (message.getBytes ());
         byte[] digitalSignature = signature.sign ();
         System.out.println ("Digital Signature: " +
			     new String (digitalSignature));
// Verify the signature
       PublicKey publicKey = keyPair.getPublic ();
       Signature verifier = Signature.getInstance ("SHA256withRSA");
         verifier.initVerify (publicKey);
         verifier.update (message.getBytes ());
       boolean verified = verifier.verify (digitalSignature);
         System.out.println ("Signature verified: " + verified);
     }
}