package mold;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class molds extends JFrame implements MouseListener, Runnable,
		KeyListener {
	private static int size = 100;
	private int dots = 0;
	private boolean[][] moldy = new boolean[size][size];
	private boolean[][] moldybuf = new boolean[size][size];

	public molds() {
		setSize(size, size);
		setVisible(true);
		addMouseListener(this);
		addKeyListener(this);
	}

	public void paint(Graphics g) {
		for (int q = 0; q < size; q++) {
			for (int w = 0; w < size; w++) {
				if (!moldy[q*3][w*3]) {
					g.setColor(Color.white);
					g.drawRect(q, w, 3, 3);
				} else {
					g.setColor(Color.black);
					g.drawRect(q, w, 3, 3);
				}
			}
		}
	}

	public void run() {
		for (int e = 0; e < 100000; e++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int z = 0; z < size; z++) {
				for (int c = 0; c < size; c++) {
					moldybuf[z][c] = moldy[z][c];
				}
			}
			for (int x = 0; x < size; x++) {
				for (int y = 0; y < size; y++) {
					if (moldy[x][y] && x < size - 1 && x > 1 && y < size - 1
							&& y > 1) {
						if (Math.random() < 0.03125) {
							if (Math.random() < 0.25) {
								moldybuf[x + 1][y] = true;
							} else if (Math.random() < 0.5) {
								moldybuf[x][y + 1] = true;
							} else if (Math.random() < 0.75) {
								moldybuf[x - 1][y] = true;
							} else {
								moldybuf[x][y - 1] = true;
							}
						}
					}
				}
			}
			for (int z = 0; z < size; z++) {
				for (int c = 0; c < size; c++) {
					moldy[z][c] = moldybuf[z][c];
				}
			}
			repaint();

		}

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		moldy[e.getX()][e.getY()] = true;
		moldybuf[e.getX()][e.getY()] = true;

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		molds m = new molds();
		new Thread(m).start();
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_EQUALS) {
			for (int g = 0; g < size; g++) {
				for (int h = 0; h < size; h++) {
					if (moldy[g][h]) {
						dots = dots + 1;
					}
				}
			}
		}
		System.out.println(dots);
		dots = 0;

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
