//TODO: Nothing, this class is complete, including JavaDocs.

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Iterator;

import javax.swing.SwingUtilities;

/**
 *  The window for the GUI.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
class WindowManager extends Frame implements MouseListener, WindowListener, ActionListener {
	/**
	 *  The smallest window width or height to use.
	 */
	private static final int MIN_REGION_SIZE = 80;
	
	/**
	 *  The largest window width or height to use.
	 */
	private static final int MAX_REGION_SIZE = 200;
	
	/**
	 *  The default GUI width in pixels.
	 */
	private static final int DEFAULT_WIDTH = 500;
	
	/**
	 *  The default GUI height in pixels.
	 */
	private static final int DEFAULT_HEIGHT = 600;
	
	/**
	 *  The GUI width in pixels.
	 */
	private int myWidth;
	
	/**
	 *  The GUI height in pixels.
	 */
	private int myHeight;
	
	/**
	 *  The stack of windows.
	 */
	private WindowStack myWindows;
	
	/**
	 * Initialize and display a WindowManager object.
	 */
	public WindowManager ( ) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Initialize and display a WindowManager object
	 * with the given width and height.
	 * @param width the window width in pixels (required to be >= 0)
	 * @param height the window width in pixels (required to be >= 0)
	 */
	public WindowManager (int width, int height) {
		myWidth = width;
		myHeight = height;
		this.addMouseListener (this);
		this.addWindowListener (this);
		setSize (myWidth, myHeight);
		myWindows = new WindowStack ( );
		
		setMenuBar(makeMenu());
		
		setResizable(false);
		setVisible (true);
	}
	
	/**
	 *  Makes the menu for the GUI.
	 *  
	 *  @return the created menu
	 */
	private MenuBar makeMenu() {
		MenuBar bar = new MenuBar();
		
		Menu m = new Menu("Windows");
		MenuItem item1 = new MenuItem("Sort by size");
		MenuItem item2 = new MenuItem("Sort by location");
		
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		m.add(item1);
		m.add(item2);
		bar.add(m);
		
		m = new Menu("Squares");
		item1 = new MenuItem("Sort by creation time");
		item2 = new MenuItem("Sort by window location");
		
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		m.add(item1);
		m.add(item2);
		bar.add(m);
		
		return bar;
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Sort by size":
				myWindows.sortSize();
				break;
			case "Sort by location":
				myWindows.sortLoc();
				break;
			case "Sort by creation time":
				myWindows.getHead().data.sortCreation();
				break;
			case "Sort by window location":
				myWindows.getHead().data.sortLoc();
				break;
		}
		repaint ( );
	}

	/**
	 * Add a window on top of the window's stack of windows
	 * and redraw the stack.
	 * @param r a window to add to the window (required to be r != null)
	 */
	public void addWindow (Window r) {
		myWindows.add (r);
		repaint ( );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		((Graphics2D)g).drawString("Number of Windows: "+myWindows.numWindows(),10,70);
		
		Iterator iter = myWindows.windows ( );
		while (iter.hasNext ( )) {
			Window r = (Window) iter.next ( );
			r.paint (g);
		}
	}
	
	/**
	 *  Simulates a mouse click at the point (x,y)
	 *  in the window.
	 *  
	 *  @param x the x position to simulate the click at
	 *  @param y the y position to simulate the click at
	 */
	public void simulateClick (int x, int y) {
		myWindows.handleClick (x, y, true);
		repaint ( );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed (MouseEvent event) {
		int mouseX = event.getX ( );
		int mouseY = event.getY ( );
		
		if(SwingUtilities.isLeftMouseButton(event)) {
			if(!myWindows.handleClick (mouseX, mouseY, true)) {
				int width = (int)(Math.random()*(MAX_REGION_SIZE-MIN_REGION_SIZE))+MIN_REGION_SIZE;
				int height = (int)(Math.random()*(MAX_REGION_SIZE-MIN_REGION_SIZE))+MIN_REGION_SIZE;
				
				width = Math.min(width, DEFAULT_WIDTH-mouseX-2);
				height = Math.min(height, DEFAULT_HEIGHT-mouseY-2);
				
				Color color = new Color((int)(Math.random() * 0x1000000));
				
				addWindow(new Window(mouseX, mouseY, width, height, color));
			}
		}
		else {
			myWindows.handleClick (mouseX, mouseY, false);
		}
	
		repaint ( );
	}
	
	//All these are required to implement the MouseListener interface.
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void mouseClicked (MouseEvent event) { }
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void mouseReleased (MouseEvent event) { }
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void mouseEntered (MouseEvent event) { }
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void mouseExited (MouseEvent event) { }
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void windowClosing (WindowEvent event) {
		System.exit (0);
	}
	
	//All these are required to implement the WindowListener interface.
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void windowIconified (WindowEvent event) { }
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void windowOpened (WindowEvent event) { }
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void windowClosed (WindowEvent event) { }
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void windowDeiconified (WindowEvent event) { }
	
	/**
	 * {@inheritDoc}
	 */
	@Override public void windowActivated (WindowEvent event) { }
		
	/**
	 * {@inheritDoc}
	 */
	@Override public void windowDeactivated (WindowEvent event) { }
	
	/**
	 *  Main method to run the GUI.
	 *  
	 *  @param args not used
	 */
	public static void main (String args [ ]) {
		WindowManager w = new WindowManager ( );
		w.addWindow (new Window (150, 100, 200, 200, Color.red));
		w.addWindow (new Window (200, 150, 125, 175, Color.yellow));
		w.addWindow (new Window (100, 200, 150, 175, Color.green));
		w.addWindow (new Window (125, 225, 150, 200, Color.blue));
		w.addWindow (new Window (250, 250, 150, 150, Color.orange));
	}
}

