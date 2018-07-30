package model;

import java.util.ArrayList;
import java.util.List;

import model.PuzzleGame.action;

public class BFSPlayer extends Player
{
//********************************************************************************
	public List<action> solve(PuzzleGame game){
		System.out.println("Searching for solution.......");
		List<action> PathToSolution = new ArrayList<action>();
		List<Node> OpenList = new ArrayList<Node>(); 
		List<Node> ClosedList = new ArrayList<Node>(); 

		OpenList.add(new Node(game.getGameBoard()));
			
		
		while(OpenList.size()>0 && !game.isSolution(game.getGameBoard())){
			if(OpenList.size()==0){
				System.out.println("Failed to find solution!");
				return PathToSolution;
			}
			//printBoards(OpenList);
			Node currentState = OpenList.get(0);
			ClosedList.add(currentState);
			//printBoards(ClosedList);
			OpenList.remove(0);
			List<Node> children = getChildrenStates(currentState);
			//printBoards(children);
			currentState.addChild(children);
			
			
			for(int i=0;i<children.size();i++){	
				if(!contains(OpenList, children.get(i)) && 
						!contains(ClosedList, children.get(i))){
					if(game.isSolution(children.get(i).getSelfState())){
						System.out.println("Solution found!");
						PathToSolution = getPath(ClosedList, children.get(i),game);
						System.out.println(PathToSolution.size());
						
						return PathToSolution;
					}
					System.out.println("Add node "+(OpenList.size()+1)+" to OpenList...");
					OpenList.add(children.get(i));
				}
			}
			
			
			
		}
		
		return PathToSolution;
		
	}
	
	public static void printBoards(List<Node> bl){
		System.out.println("BoardList:");
		for(int i=0;i<bl.size();i++){
			Integer[][] tmp = bl.get(i).getSelfState();
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					System.out.print(tmp[j][k]+" ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	public static void printBoards(Integer[][] b){
		//System.out.println("Board:");
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					System.out.print(b[j][k]+" ");
				}
				System.out.print("\n");
		}
	}
	
//************************************************************************************
	public List<action> getPath(List<Node> list, Node n, PuzzleGame game){
		List<action> path = new ArrayList<action>();
		Node current = n;
		printBoards(current.getSelfState());
		//path.add(current.getLastAct());
		System.out.println("step "+path.size()+" "+current.getLastAct().name());
		while(!Node.isSame(current.getSelfState(), game.getGameBoard())){
			path.add(0,current.getLastAct());
			System.out.println("step "+path.size()+" "+current.getLastAct().name());
			current = current.getParent();
			printBoards(current.getSelfState());
			System.out.println(!Node.isSame(current.getSelfState(), game.getGameBoard()));
		}
		for(int m=0;m<path.size();m++)
			System.out.print(path.get(m).name()+"-->");
		System.out.println("END...\n");
		return path;
	}

//************************************************************************************
	public boolean contains(List<Node> list, Node n){
		boolean contain = false;
		for(int i=0; i<list.size(); i++){
			if(Node.isSame(list.get(i).getSelfState(), n.getSelfState())){;
				contain = true;
			}
		}
		return contain;
	}

//*************************************************************************************
	public List<Node> getChildrenStates(Node parentState){
		List<Node> childrenStates = new ArrayList<Node>();
		PuzzleGame game = new PuzzleGame();
		action[] possibleActs = game.getPossibleActions(parentState.getSelfState());
		for(int i=0; i<possibleActs.length;i++){
			childrenStates.add(
					new  Node(game.computeAction(possibleActs[i],
					parentState.getSelfState()), possibleActs[i], parentState));
		}
		
		return childrenStates;
	}
}

