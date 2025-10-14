package umbcs681.hw4.color;

public class Color {

	private int redScale;
	private int greenScale;
	private int blueScale;
	
	Color(int r, int g, int b) {
		this.blueScale=b;
		this.greenScale=g;
		this.redScale=r;
	}

	public int getRedScale() {
		return redScale;
	}
	public int getBlueScale() {
		return blueScale;
	}
	public int getGreenScale() {
		return greenScale;
	}


}
