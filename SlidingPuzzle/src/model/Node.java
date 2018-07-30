package model;

import java.util.ArrayList;
import java.util.List;

import model.PuzzleGame.action;

public class Node{
	private Integer[][] selfState;
	private Node nparent;
	private List<Node> nchildren;
	private action LastAct;
	
	public Node(){
		this.selfState = null;
		this.LastAct = null;
		this.nparent = null;
		this.nchildren = null;
	}
	
	public Node(Integer[][] selfState){
		this.selfState = selfState;
		this.LastAct = null;
		this.nparent = null;
		this.nchildren = null;
	}
	
	public Node(Integer[][] selfState,action act,Node par){
		this.selfState = selfState;
		this.LastAct = act;
		this.nparent = par;
		this.nchildren = null;
	}
	
	public void setParent(Node par){
		this.nparent = par;
	}
	
	public void setLastAct(action act){
		this.LastAct = act;
	}
	
	public void addChild(Node child){
		if(nchildren == null)
			nchildren = new ArrayList<Node>();
		this.nchildren.add(child);
	}
	
	//overriding
	public void addChild(List<Node> c){
		if(nchildren == null)
			nchildren = new ArrayList<Node>();
		//System.out.println("Adding children...size = "+c.size());
		for(int i=0; i<c.size();i++){
			//BFSPlayer.printBoards(c.get(i).getSelfState());
			this.nchildren.add(c.get(i));
			//BFSPlayer.printBoards(nchildren);
		}	
	}
	
	public Integer[][] getSelfState(){
		return this.selfState;
	}
	
	public Node getParent(){
		return this.nparent;
	}
	
	public action getLastAct(){
		return this.LastAct;
	}
	
	public List<Node> getChildren(){
		return this.nchildren;
	}
	
	public static boolean isSame(Integer[][] arr1, Integer[][] arr2)
	{
	    for (int x=0; x<arr1.length; x++)
	    {
	        for (int y=0; y<arr1[x].length; y++)
	        {
	            if (arr1[x][y] != arr2[x][y])
	            {
	                return false;
	            }
	        }
	    }

	    return true;
	}
}
