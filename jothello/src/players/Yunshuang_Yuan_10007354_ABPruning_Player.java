package players;

import java.util.ArrayList;
import java.util.List;

import com.sun.crypto.provider.RSACipher;

import game.AbstractPlayer;
import game.BoardSquare;
import game.Move;
import game.OthelloGame;


public class Yunshuang_Yuan_10007354_ABPruning_Player extends AbstractPlayer {

	public ArrayList<Node> tree = new ArrayList<Node>();
	public OthelloGame gamehandler = new OthelloGame();
	public class Result{
		public int best;
		public int[][] board;
	}
	
	public Yunshuang_Yuan_10007354_ABPruning_Player(int depth) {
		super(depth);
	}
	//d:depth of current node, n: index of children
	public Result minimax(Node node, int alpha, int beta) {
		OthelloGame game = new OthelloGame();
		int[][] tab = node.board;
		List<Move> moves = game.getValidMoves(tab, node.selfBoardMark);
		
	    if (node.depth ==7 || moves.size()<0) {
	    	node.setValue();
	    	Result r = new Result();
	    	r.best = node.value;
	    	r.board = node.board;
	    	return r;
	    }
	    
		node.setChildren(moves);
	    // return if leaf node is reached
	 
	    if (node.selfBoardMark==-1) //boardmark of Max is -1
	    {
	    	Result result = new Result();
	        result.best = Node.MIN;
	        for (int i = 0; i < node.children.size(); i++)
	        {
	             
	           Result res = minimax(node.children.get(i), alpha, beta);
	
	           if(result.best<res.best) {
	        	   result.best = res.best;
	        	   result.board = node.children.get(i).board;
	           }
	            node.value = result.best;
	            
	            alpha = max(alpha, result.best);
	 
	            // Alpha Beta Pruning
	            if (beta <= alpha)
	                break;
	        }
	        return result;
	    }
	    else
	    {
	    	Result result = new Result();
	        result.best = Node.MAX;

	        for (int i = 0; i < node.children.size(); i++)
	        {
	            Result res = minimax(node.children.get(i),alpha, beta);
	            
	            if(result.best>res.best) {
	            	result.best = res.best;
	            	result.board = node.children.get(i).board;
	            }

	            node.value = result.best;
	            beta = min(beta, result.best);
	 
	            // Alpha Beta Pruning
	            if (beta <= alpha)
	                break;
	        }
	        return result;
	    }
	}
	
	public ArrayList<Node> getSearchTree(Node root){

		List<Move> moves = gamehandler.getValidMoves(root.board, root.selfBoardMark);
		if(moves.size()>0) {
			root.setChildren(moves);
			tree.add(root);
			if(root.depth==3) {
				root.setValue();
				tree.set(tree.size()-1, root);
				return tree;
			}
			else {
				for(int i=0; i<root.children.size();i++) {
					getSearchTree(root.children.get(i));
				}
				return tree;
			}
		}
		return tree;
	}
	
	
	@Override
	public BoardSquare play(int[][] tab) {
		OthelloGame game = new OthelloGame();
		Node root = new Node(tab,getMyBoardMark());
		root.depth = 0;
		root.value  = Node.MIN;
		List<Move> moves = game.getValidMoves(tab, getMyBoardMark());
		if (moves.size() > 0) {
			Result result = minimax(root, Node.MIN, Node.MAX);
			for(int i=0; i<moves.size();i++) {
				if(equal(moves.get(i).getBoard(),result.board)) {
					return moves.get(i).getBoardPlace();
				}
			}
	
		}		
		return new BoardSquare(-1, -1);
	}
	
	public static int min(int a, int b) {
		return a<b?a:b;
	}
	
	public static int max(int a, int b) {
		return a>b?a:b;
	}

	public static boolean equal(int[][] a, int[][] b) {
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a.length; j++) {
				if(a[i][j]!=b[i][j])
					return false;
			}
		}
		return true;
	}
	
	public static void printBoard(int[][] b) {
		for(int i=0; i<b.length; i++) {
			for(int j=0; j<b.length; j++) {
				System.out.print(b[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
}
