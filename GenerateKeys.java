import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateKeys {

	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public GenerateKeys(){}
	
	//this method creates with the given length for the RSA algorithm 
	public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength);
	}

	//this method creates public key and private key and write them to a file which is created in the KEYPAIR folder
	public void createKeys(String s) {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
		String temp="Keys/KeyPair"+s+"/publicKey";
		String temp1="Keys/KeyPair"+s+"/privateKey";
		try {
			this.writeToFile(temp, this.getPublicKey().getEncoded());
			this.writeToFile(temp1, this.getPrivateKey().getEncoded());
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	//this method writes the key given as bytes in the file with the path given 
	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

	public void generator(String userName) 
	{
		GenerateKeys gk;
		try
		{
			gk = new GenerateKeys( 4096);// creates keys with the length 4096.
			gk.createKeys(userName);
		}
		catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			System.err.println(e.getMessage());
		}
	}
	//public static void 
}
