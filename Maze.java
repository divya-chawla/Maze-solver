/*
* Name    :  DIVYAPREET CHAWLA
* ID      :  V00862263
* Date    :  26 JUNE 2016
* Filename:  Maze.java
* Details :  CSC115 Assignment #4
* The CellDeque class is a Double ended queue of Cell objects.
* The Maze class is s simple maze representation that has zero or a single solution.
 */
 
public class Maze{
	    //a 2-dimensional array of characters that form a maze
		char[][] mazeData;
		// first cell of the maze from where the maze starts
		Cell start;
		// cell to reach the final destination in the maze
		Cell finish;
		CellDeque path;
		
		//Sets up a simple unsolved maze
		//the maze will be visible as single characters, either an asterisk or a space. The asterisk represents a wall in the maze, 
		//while the space character represents a free cell.
		public Maze(String[] testMaze , Cell start, Cell finish){
			mazeData = new char[testMaze.length][testMaze[0].length()];
			for(int i = 0 ; i < mazeData.length ; i++){
				for(int j = 0 ; j < mazeData[i].length; j++){
					//copy the data from string[] arr into the 2d array of chars mazeData
					mazeData[i][j] = testMaze[i].charAt(j);
				}
			}
			this.start = start;
			this.finish = finish;
			//creating an empty deque to keep the cells of the path used to solve the maze
			path = new CellDeque();
		}
		
		//Finds the solution path, if there is one, for the maze
		public CellDeque solve(){
			// calls resursive method to find a path from start to the finish 
			boolean solved = findPath(this.start, this.finish);
			if(solved){
				System.out.println("found a path");
			}
			else{
				System.out.println("did not find a path");
				path.makeEmpty();
			}
			//returns the solved path in form of a list
			return path;
		}
		
		//recursive method to find the solution to the maze with parameters start and finish cell
		private boolean findPath(Cell cur, Cell finish){
			// inserts the current cell to th last of the list
			path.insertLast(cur);
			//marks the cell "V" if the cell is visited i.e. the current cell
			System.out.println("Current location: "+cur);
			System.out.print(path);
			mazeData[cur.row][cur.col] = 'V';
			// ** BASE CASE **
			//the path will be found only if the current cell is the finish cell that is we have reached the destination
			if(cur.equals(finish)){
				return true;
			}
			// ** RECURSIVE CASE **
			//checking if cell below the current cell is not out of the maze and is blank
			if(cur.row+1 != mazeData.length && mazeData[cur.row+1][cur.col] == ' '){
				Cell down = new Cell(cur.row+1,cur.col);
				//finds a path from the cell below to the finish cell
				if(findPath(down, finish) == true){
					return true;
				}	
			}
			//checking if cell above the current cell is not out of the maz and is blank
			if(cur.row-1 != -1 && mazeData[cur.row-1][cur.col] == ' '){
				Cell up = new Cell(cur.row-1,cur.col);
				//finds a path from the cell above to the finish cell
				if(findPath(up, finish) == true){
					return true;
				}
			}
			//checking if cell to the right of the current cell is not out of the maze and is blank
			if(cur.col+1 != mazeData[0].length && mazeData[cur.row][cur.col+1] == ' '){
				Cell right = new Cell(cur.row,cur.col+1);
				//finds a path from the cell to the right of the current cell to the finish cell
				if(findPath(right, finish) == true){
					return true;
				}
			}
			//checking if cell to the left of the current cell is not out of the maze and is blank
			if(cur.col-1 != -1 && mazeData[cur.row][cur.col-1] == ' '){
				Cell left = new Cell(cur.row,cur.col-1);
				//finds a path from the cell to the right of the current cell to the finish cell
				if(findPath(left, finish) == true){
					return true;
				}
			}
			// ** DEAD END **
			path.removeLast();
			//marks the dead end with X so that it is not visited again
			mazeData[cur.row][cur.col] = 'X';
			return false;
		}	

		//prints the maze
		public String toString(){
			String details = "";
			for(int i = 0 ; i < mazeData.length ; i++){
				for(int j = 0 ; j < mazeData[i].length; j++){
					details = details+mazeData[i][j] ;
				}
				details = details+"\n";
			}
			return details;
		}
    }