package components;

import java.util.Random;

public class Rectangle {
	private int MIN_SIZE = 7;
	private static Random rnd = new Random(); 

	private int top, left, width, height;
	private Rectangle leftChild;
	private Rectangle rightChild;
	private Room room;

	public Rectangle(int top, int left, int height, int width) {
		this.top = top;
		this.left = left;
		this.width = width;
		this.height = height;
	}

	public boolean split() {
		//if already split, bail out
		if( getLeftChild() != null ) 
			return false;

		//direction of split
		boolean horizontal = rnd.nextBoolean(); 
		//maximum height/width we can split off
		int max = (horizontal ? getHeight() : getWidth()) - MIN_SIZE;
		// area too small to split, bail out
		if( max <= MIN_SIZE )
			return false;

		// generate split point 
		int split = rnd.nextInt(max);
		// adjust split point so there's at least MIN_SIZE in both partitions
		if( split < MIN_SIZE )
			split = MIN_SIZE;

		//populate child areas
		if(horizontal) { 
			setLeftChild(new Rectangle(getTop(), getLeft(), split, getWidth())); 
			setRightChild(new Rectangle(getTop()+split, getLeft(), getHeight()-split, getWidth()));
		} else {
			setLeftChild(new Rectangle(getTop(), getLeft(), getHeight(), split));
			setRightChild(new Rectangle(getTop(), getLeft()+split, getHeight(), getWidth()-split));
		}
		//split successful
		return true; 
	}

	public void generateDungeon() {
		//if current are has child areas, propagate the call
		if( getLeftChild() != null ) {
			getLeftChild().generateDungeon();
			getRightChild().generateDungeon();
		} 
		// if leaf node, create a dungeon within the minimum size constraints
		else { 
			int dungeonTop = (getHeight() - MIN_SIZE <= 0) ? 0 : rnd.nextInt( getHeight() - MIN_SIZE);
			int dungeonLeft =  (getWidth() - MIN_SIZE <= 0) ? 0 : rnd.nextInt( getWidth() - MIN_SIZE);
			int dungeonHeight = Math.max(rnd.nextInt( getHeight() - dungeonTop ), MIN_SIZE );;
			int dungeonWidth = Math.max(rnd.nextInt( getWidth() - dungeonLeft ), MIN_SIZE );;
			setRoom(new Room( getTop() + dungeonTop, getLeft()+dungeonLeft, dungeonHeight, dungeonWidth));
		}
	}
	
	
	public String toString() {
		String s = " top=" + top	
				+ " left=" + left
				+ " width=" + width
				+ " height=" + height;
		return s;
	}
	////////////Getters And Setters////////////
	public Rectangle getRightChild() {
		return rightChild;
	}

	public void setRightChild(Rectangle rightChild) {
		this.rightChild = rightChild;
	}

	public Rectangle getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Rectangle leftChild) {
		this.leftChild = leftChild;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}
}
