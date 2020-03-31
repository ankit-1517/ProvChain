import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

	public class Encrypt{
	private Cipher cipher;

	public Encrypt() throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance("RSA");
	}

	// this method returns the private key of the user. Actually it is the public key but we have given it the wrong name.
	public PrivateKey getPrivate(String s) throws Exception {
		PrivateKey p;
		byte[] keyBytes = Files.readAllBytes(new File(s).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		p=kf.generatePrivate(spec);
		return p;		
	}
	
	//this method writes the encrypted file using the private key.
	public void encryptFile(byte[] input, File output, PrivateKey key) 
		throws IOException, GeneralSecurityException {
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		writeToFile(output, this.cipher.doFinal(input));
	}
	// this method writes the input given as bytes in the file given  
	private void writeToFile(File output, byte[] toWrite)
			throws IllegalBlockSizeException, BadPaddingException, IOException {
		FileOutputStream fos = new FileOutputStream(output);
		fos.write(toWrite);
		fos.flush();
		fos.close();
	}
	
	public String encryptText(String msg, PrivateKey key) 
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, 
			BadPaddingException, InvalidKeyException {
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}
	// this method returns the content of the given files in form of bytes 
	public byte[] getFileInBytes(File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		byte[] fbytes = new byte[(int) f.length()];
		fis.read(fbytes);
		fis.close();
		return fbytes;
	}
	//this method creates a encypted file at the output address using the input file and the private key of the user.
	public void Encrypting(String inputaddress,String outputaddress,String newUserName)throws Exception
	{
		PrivateKey privateKey=getPrivate(address.keyAddress+"KeyPair"+newUserName+"/privateKey");
		Encrypt ac = new Encrypt();
		if (new File(inputaddress).exists()){ 
			ac.encryptFile(ac.getFileInBytes(new File(inputaddress)), new File(outputaddress+"encrypted.txt"),privateKey);
			Decrypt dc=new Decrypt();
			dc.Decrypting(outputaddress+"encrypted.txt",outputaddress+".txt",newUserName);
		}
		else
		System.out.println("Create a file text.txt under folder KeyPair");
	}
	
}
