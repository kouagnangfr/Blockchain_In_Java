
package blockcchain;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class BlockChain 
{
	/* classe permettant de creer notre B.C et 
	 * de garantir son integrite.
	 */
	
	private int difficulty;   //indique la dificulte pour le processus de minage
	private List<Block> blocks; //permettant de stockerles differents block de notre blockChain
	
	//Constructeur
	public BlockChain(int difficulty)
	{
		this.difficulty = difficulty;
		blocks = new ArrayList<>();
		
		//creation du premier block de notre B.C (genesis block)
		Block b = new Block(0, CalculTimestamp(), null, "First Block");
		b.mineBlock(difficulty);
		blocks.add(b);
	}
	
	//getter
	public int getDifficulty()
	{
		return difficulty;
	}
	
	public Block latestBlock() 
	{
		//permet de renvoyer le dernier block de notre B.C
		return blocks.get(blocks.size()-1);
	}
	
	public Block newBlock(String data)
	{
		Block latestBlock = latestBlock();
		return new Block( latestBlock.getIndex() + 1, CalculTimestamp(),
				latestBlock.getHash(), data);
	}
	
	public void addBlock(Block b)
	{
		/*methode permettant d'ajouter un nouveau block
		 * a notre blockchain
		 */
		if(b != null)
		{
			b.mineBlock(difficulty); // neccessitee de minage du block avant son ajout dans la B.C
			blocks.add(b);
		}
	}
	
	public boolean isFirstBlockvalid()
	{
		/*methode permettant de verifier la validite du
		 * premier block
		 */
		Block firstBlock = blocks.get(0);
		
		if(firstBlock.getIndex() != 0)
		{
			return false;
		}
		
		if(firstBlock.getPreviousHash() != null)
		{
			return false;
		}
		
		if(firstBlock.getHash() == null)
		{
			return false;
		}
		
		return true;
		
	}
	
	public boolean isValidNewBlock(Block newBlock, Block previousBlock)
	{
		/*methode permettant de verifier la validite d'un
		 *  nouveau block a l'aide des informations du block precedent
		 */
		boolean rep = true;
		if(newBlock != null && previousBlock != null)
		{
			if((previousBlock.getIndex() + 1) != newBlock.getIndex())
			{
				rep = false;
			}
			
			else if(newBlock.getPreviousHash() == null || !newBlock.getPreviousHash().equals(previousBlock.getHash()))
			{
				rep = false;
			}
			
			else if(newBlock.getHash() == null || !Block.calculateHash(newBlock).equals(newBlock.getHash()))
			{
				rep = false;
			}
			
			else {
			rep = true;
			}
		}
		
		return rep;
	
	}
	
	
	public boolean isBlockChainValid()
	{
		/*methode permettant de verifier la validite de
		 *  toute la B.C. (garantir on integrite)
		 */
		if(!isFirstBlockvalid())
		{
			return false;
		}
		
		for(int i = 1; i < blocks.size(); i++)
		{
			Block currentBlock = blocks.get(i);
			Block previousBlock = blocks.get(i - 1);
			
			if(!isValidNewBlock(currentBlock, previousBlock))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public String toString() 
	{
		/*surcharge de la methode toString de l'objet Blockchain
		 * permettant de retourner le contenu de notre B.C sous
		 * forme de chaine de caracteres;
		 */
		StringBuilder builder = new StringBuilder();
		
		for (Block block : blocks)
		{
			builder.append(block).append("\n");
		}
		
		return builder.toString();
	}
	
	public static String CalculTimestamp()
	{
		//Generation des infos sur la date de creation
		String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
				Calendar.getInstance().getTime());
		return timestamp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
