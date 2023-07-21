package blockcchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.*;


public class Block 
{
	private int index;
	private String timesTamp; // infos date creation
	private String hash;   //assure l'integrite de la B.C.
	private String previousHash;  // lie le block courant au block prec.
	private String data;
	private int nonce;  // servira pendant la phase de minage

    
	         //Constructeur
	public Block(int index, String timesTamp, String previousHash,
                     String data)
	{
		this.index = index;
		this.timesTamp = timesTamp;
		this.previousHash = previousHash;
		this.data = data;
		nonce = 0;
		hash = calculateHash(this); //calcul de la premiere valeur du hash
	}
	
	           //Getters
	public int getIndex()
	{
		return this.index;
	}
	
	public String getTimestamp()
	{
		return timesTamp;
	}
	
	public String getHash()
	{
		return hash;
	}
	
	public String getPreviousHash()
	{
		return previousHash;
	}
	
	public String getData()
	{
		return data;
	}

        
        //public 
	
	/*
         * Ici nous n'avons pas de setters car la blockchaine est apriori             
         *                        non modifiable
	 */
	
	public String str()
	{
		return index + timesTamp + previousHash + data + nonce ;
	}
	
	public String toString()
	{
		//methode permettant de renvoyer les informations
		//d'un block plus proprement
		StringBuilder builder = new StringBuilder();
		builder.append("Block #").append(index).
                        append( "[PreviousHash :").append(previousHash).append(", ").
                        append(" TimesTamp : ").append(timesTamp).append( ", ").
                        append(" Data : ").append(data).append(", ").
			append( " Hash : ").append(hash).append(", ");
		return builder.toString();
	}
	
	public static String calculateHash(Block block)
	{
	    /*methode calculant le hash d'un block passer en entree
	     * grace a l'algo de cryptho SHA-256
		 */
		if(block != null)
		{
			MessageDigest digest = null;
			try 
			{
				 digest = MessageDigest.getInstance("SHA-256");
			}
			catch (NoSuchAlgorithmException e)
			{
				return null;
			}
			
			String txt = block.str();
			final byte bytes[] = digest.digest(txt.getBytes()); //recuperation du hash
			final StringBuilder builder = new StringBuilder();  // sous forme de tableau de byte
			                                                    
			for(final byte b : bytes)
			{
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
				{
					builder.append('0');
				}
				builder.append(hex);
			}
			
			return builder.toString(); 			
		}
		
		return null;
		
		
	}
	
	public void mineBlock(int difficulty)
	{
		
	   /* Methode permettant de miner le block en resolvant 
		* preuve de travail pour le block courant  etant donner une certaine difficulte
		*passer en parametre
		*/ 
		nonce = 0;  // stocke le nombre d'essaie realiser pendant le minage  
		while(!getHash().substring(0, difficulty).equals(Zeros(difficulty)))
		{
			nonce ++;
			hash = Block.calculateHash(this);
		}
	}
        
        public static String Zeros(int length)
	{
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<length; i++)
		{
			builder.append('0');
		}
		
		return builder.toString();
	}
	
	
	
	
	
	
}
