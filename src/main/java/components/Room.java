package components;

public class Room {

	private int top, left, width, height;
	
	public Room(int top, int left, int height, int width) {
		this.setTop(top);
		this.setLeft(left);
		this.setWidth(width);
		this.setHeight(height);
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

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

}
