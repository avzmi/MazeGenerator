/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazegenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import java.util.Stack;

import javax.swing.JPanel;


class MazePanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private Rectangle2D[][] cells;
	private static final int dimension = 670;
	private int[][] array;
	private int cellsMaze;
	private float cell_size;
	private Position start;
	private Position end;
	private Position current;
	private int delay = 100;
	public Maze maze;
        public static int steps = 0;
        static int status = 0;
        static int flag = 0;
	public MazePanel(int cellsMaze) {
		addKeyListener(this);
		refresh(cellsMaze);
	}

	public int getCellsMaze() {
		return this.cellsMaze;
	}

	public void refresh(int cellsMaze){
		this.cellsMaze = cellsMaze;
		setSize(dimension, dimension);
		cell_size = (float) (1.0 * dimension / cellsMaze);
		
		setFocusable(true);

		maze = new Maze(cellsMaze - 2);
		array = maze.getArray();

		start = current = new Position(1, 0);
		end = new Position(cellsMaze - 2, cellsMaze - 1);

		cells = new Rectangle2D[cellsMaze][cellsMaze];
		for (int i = 0; i < cellsMaze; i++) {
			for (int j = 0; j < cellsMaze; j++) {
				cells[i][j] = new Rectangle2D.Double(j * cell_size, i * cell_size, cell_size, cell_size);
			}
		}
	}
	private void drawMaze(Graphics2D g2d) {
		for (int i = 0; i < cellsMaze; i++) {
			for (int j = 0; j < cellsMaze; j++) {
				if (array[i][j] == 0) {
					g2d.setColor(Color.WHITE);
				} else {
					g2d.setColor(Color.BLACK);
				}
				g2d.fill(cells[i][j]);
			}
		}
		g2d.setColor(Color.red);
                g2d.fill(cells[end.getX()][end.getY()]);
		int x = start.getX();
		int y = start.getY();

		g2d.setColor(Color.GREEN);
		g2d.fill(cells[x][y]);
	}

	public void autoMove(Graphics2D g2d) {
            
		Stack<Position> way = maze.getDirectWay(start, end);
		g2d.setColor(Color.GREEN);
		while (!way.empty()){
                    if(flag == 0){
			Position next = way.pop();
			int x = next.getX();
			int y = next.getY();
			g2d.fill(cells[x][y]);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
                        steps++;
                    }
                    else{
                        System.out.println("asdsda");
                        break;
                    }
		}
                status = 1;
                    POP p = new POP(new javax.swing.JFrame(), true);
                    p.setVisible(true);
                
	}

	
	 boolean passed() {
		return current.equals(end);
	}

	private void move(Graphics2D g2d, Position current){
		int x = current.getX();
		int y = current.getY();

		update((Graphics2D) this.getGraphics());

		g2d.setColor(Color.GREEN);
		g2d.fill(cells[x][y]);
		
		if(passed()) {
                    POP p = new POP(new javax.swing.JFrame(), true);
                    p.setVisible(true);
                    status = 1;
		}
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		drawMaze((Graphics2D) g);
	}
	
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (maze.canMoveUp(current)) {
				current.setX(current.getX() - 1);
				move((Graphics2D) getGraphics(), current);
                                steps++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (maze.canMoveRight(current)) {
				current.setY(current.getY() + 1);
				move((Graphics2D) getGraphics(), current);
                                steps++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (maze.canMoveDown(current)) {
				current.setX(current.getX() + 1);
				move((Graphics2D) getGraphics(), current);
                                steps++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (maze.canMoveLeft(current)) {
				current.setY(current.getY() - 1);
				move((Graphics2D) getGraphics(), current);
                                steps++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (maze.canMoveUp(current)) {
				current.setX(current.getX() - 1);
				move((Graphics2D) getGraphics(), current);
                                steps++;
			}		
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (maze.canMoveRight(current)) {
				current.setY(current.getY() + 1);
				move((Graphics2D) getGraphics(), current);
                                steps++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (maze.canMoveDown(current)) {
				current.setX(current.getX() + 1);
				move((Graphics2D) getGraphics(), current);
                                steps++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (maze.canMoveLeft(current)) {
				current.setY(current.getY() - 1);
				move((Graphics2D) getGraphics(), current);
                                steps++;
			}
		}
//		if (e.getKeyCode() == KeyEvent.VK_0) {
//                    flag++;
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}