

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FloorManager implements GameEntities {

	private BufferedReader floorFile;
	private ArrayList<ArrayList<Brick>> loadedBricks;
	private final int Y_START = 465;
	private int startBrick;
	private int endBrick = 30;

	public FloorManager() throws FileNotFoundException {
		URL path = getClass().getResource("/level_files/FloorFile.txt");
		floorFile = new BufferedReader(new FileReader(path.getFile()));
		loadedBricks = new ArrayList<>();
	}

	public void initFloorArray() throws IOException {
		int x = 0;
		int y = Y_START;
		String line;
		int column = 0;
		while ((line = floorFile.readLine()) != null) {
			loadedBricks.add(new ArrayList<Brick>());
			for (int i = 0; i < line.length(); i++) {
				ArrayList<Brick> columnList = loadedBricks.get(column);
				switch (line.charAt(i)) {
				case '3':
					Brick black = new Brick(x, y, ArtImages.getArtImages().getBrickSprites());
					black.setActiveImage(Brick.BLACK_BRICK - 1);
					columnList.add(black);
					break;
				case '2':
					Brick blue = new Brick(x, y, ArtImages.getArtImages().getBrickSprites());
					blue.setActiveImage(Brick.BLUE_BRICK - 1);
					columnList.add(blue);
					break;
				case '4':
					Brick green = new Brick(x, y, ArtImages.getArtImages().getBrickSprites());
					green.setActiveImage(Brick.GREEN_BRICK - 1);
					columnList.add(green);
					break;
				case '1':
					Brick yellow = new Brick(x, y, ArtImages.getArtImages().getBrickSprites());
					yellow.setActiveImage(Brick.YELLOW_BRICK - 1);
					columnList.add(yellow);
					break;
				}
				y = y - Brick.BRICK_HEIGHT;
			}
			y = Y_START;
			x = x + Brick.BRICK_WIDTH;
			column++;
		}

	}

	public int getStartBrick() {
		return startBrick;
	}

	public int getEndBrick() {
		return endBrick;
	}

	public ArrayList<ArrayList<Brick>> getBrickArray() {
		return loadedBricks;
	}

	@Override
	public void moveRight() {
		for (int i = 0; i < loadedBricks.size(); i++) {
			for (int j = 0; j < loadedBricks.get(i).size(); j++) {
				int X = loadedBricks.get(i).get(j).getX();

				if (X <= 0) {
					startBrick = i;
				}

				if (X <= GamePanel.WINDOW_WIDTH) {
					endBrick = i;
				}
/*
				if (X < -30) {
					for (int k = 0; k < loadedBricks.size() - 1; k++) {
						X += Brick.BRICK_WIDTH;
						loadedBricks.get(i).get(j).setX((int) (X));
					}
				}
*/
				loadedBricks.get(i).get(j).setX((int) (X - GameSprites.MOVE_SIDE_LENGTH));
			}
		}
	}

	@Override
	public void moveLeft() {
		for (int i = 0; i < loadedBricks.size(); i++) {
			for (int j = 0; j < loadedBricks.get(i).size(); j++) {
				int X = loadedBricks.get(i).get(j).getX();
				if (X <= 0) {
					startBrick = i;
				}

				if (X <= GamePanel.WINDOW_WIDTH) {
					endBrick = i;
				}
/*
				if (X > Brick.BRICK_WIDTH * (loadedBricks.size() - 3)) {
					for (int k = 0; k < loadedBricks.size() - 1; k++) {
						X -= Brick.BRICK_WIDTH;
						loadedBricks.get(i).get(j).setX((int) (X));
					}
				}
*/
				loadedBricks.get(i).get(j).setX((int) (X + GameSprites.MOVE_SIDE_LENGTH));
			}
		}
	}

	@Override
	public void jump() {
	}

	@Override
	public void turnOnGravity() {
	}

	@Override
	public void moveUp() {
	}

}
