package blockcchain;

 /* Classe permettant de tester notre BlockChain notamment
  *             sa validite et coherence
  */

public class MainBlockChain 
{
	public static void main(String[] args)
	{
		BlockChain blockchain = new BlockChain(4); //4 = difficulte de minage pour la preuve de travail
		
		//ajout de blocks au sein de notre blockchain
		blockchain.addBlock(blockchain.newBlock("INFORMATIQUE"));
		blockchain.addBlock(blockchain.newBlock("PROJET"));
		blockchain.addBlock(blockchain.newBlock("GROUPE I"));
		blockchain.addBlock(blockchain.newBlock("IMPLEMENTATION D'UNE CHAINE DE BLOCK "));
		blockchain.addBlock(blockchain.newBlock("MERCI"));
		
		System.out.println(blockchain);
		System.out.println("Notre Blockchain est T'elle valide ? : " + blockchain.isBlockChainValid());
		
	//Essayons de corrompre notre B.C en ajoutant un block sans le miner
		System.out.println("\n\n\nAPRES AVOIR CORROMPU NOTRE B.C");
		blockchain.addBlock(new Block(14, BlockChain.CalculTimestamp(), "0000b203cf41a170bafb496d", "Block InvaliD"));
		System.out.println(blockchain);
		System.out.println("Notre Nouvelle Blockchain est T'elle valide ? : " + blockchain.isBlockChainValid());
	}
}
