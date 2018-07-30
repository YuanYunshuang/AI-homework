package players;

import java.util.ArrayList;
import java.util.List;

import game.Move;

public class Yunshuang_Yuan_10007354_ABPrunings_Node {
	public int value;
	public Yunshuang_Yuan_10007354_ABPruning_Node father;
	public ArrayList<Yunshuang_Yuan_10007354_ABPruning_Node> children;
	public int[][] board;
	public int depth;
	
	public static final int MAX = 1000;
	public static final int MIN = -1000;
	public int selfBoardMark;
	
	public Yunshuang_Yuan_10007354_ABPruning_Node() {
		
	}
	public Yunshuang_Yuan_10007354_ABPruning_Node(int[][] tab, int bm) {
		this.board = tab;
		this.selfBoardMark = bm;
	}
	
	public void setBoard(int[][] b) {
		this.board = b;
	}
	
	public void setChildren(List<Move> mvs) {
		this.children = new ArrayList<Yunshuang_Yuan_10007354_ABPruning_Node>();
		for(int i=0; i<mvs.size(); i++) {
			Yunshuang_Yuan_10007354_ABPruning_Node n = new Yunshuang_Yuan_10007354_ABPruning_Node(mvs.get(i).getBoard(), -this.selfBoardMark);
			n.father = this;
			n.depth = n.father.depth +1;
			this.children.add(n);
		}
	}
	
	public void setValue() {
		int[][] b = this.board;
		for(int i=0; i<b.length; i++) {
			for(int j=0; j<b.length; j++) {
				if(b[i][j]==this.selfBoardMark) {
					this.value++;
				}
			}
		}
	}
	
}
