/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazegenerator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import static mazegenerator.MazePanel.flag;

public class MazeGUI extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MazePanel mazePane;
	private JMenuBar menuBar;
	private JMenu computer;

	
	public MazeGUI(int n) {
		setSize(700, 750);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                addKeyListener(this);
		setLocationRelativeTo(null);
		setTitle("Maze Game");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		
		computer = new JMenu("Auto Move");
		menuBar.add(computer);

                
		JMenuItem autoMove = new JMenuItem();
                        Action AM = new AbstractAction("Auto Move") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
                            
				mazePane.autoMove((Graphics2D) mazePane.getGraphics());
                                close();
                                
			}
		};
                        AM.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
                        autoMove.setAction(AM);
                        computer.add(autoMove);

                        mazePane = new MazePanel(n);
                        contentPane.add(mazePane, BorderLayout.CENTER);
                        this.setVisible(false);
                        
	}
        void close(){
                this.dispose();
            
        }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_0) {
            MazePanel.flag++;
		}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	
	class LevelsAction extends AbstractAction{
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			int number = Integer.valueOf(e.getActionCommand()) * 10 + 1;
			mazePane.refresh(number);
			repaint();
		}
		
	}
}