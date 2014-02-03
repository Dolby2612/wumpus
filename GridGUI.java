import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GridGUI extends JFrame implements ActionListener
{
	JButton[][] button;
	int size;
	
	public GridGUI(int size)
	{
		this.size = size;
		setLayout(new GridLayout(size,size));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Wumpus");
		setSize(600,600);
		setLocation(500,500);
		
		button = new JButton[size][size];
		
		for(int y = 0; y < size; y++)
		{
			for(int x = 0; x < size; x++)
			{
				button[y][x] = new JButton("");
				button[y][x].setText(null);
				add(button[y][x]);
				button[y][x].addActionListener(this);
				
				}
				
				
			}
			setVisible(true);
		}
		
	
	
	public void addCaption(int y, int x, String caption)
	{
		button[y][x].setText(caption);
	}
	
	public void removeCaption(int y, int x)
	{
		button[y][x].setText(null);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//searches through the entire array until the clicked button is found
		for(int y = 0; y < size; y++)
		{
			for(int x = 0; x < size; x++)
			{
				if(e.getSource() == button[y][x])
				{
					//prints the coordinates (x,y) of the clicked square
					String caption = x+","+y;
					System.out.println(caption);
					addCaption(y,x,caption);
					
				}
			}
		}
	}
	
}
