package Base.CardLayout;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame{
	JComboBox<String> options=new JComboBox<>();
	CardLayout cardLayout=new CardLayout();
	public  MyFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		this.setContentPane(root);
		
		options.addItem("面板1");
		options.addItem("面板2");
		
		JPanel cards = new JPanel();
		JPanel jPanel1 = new JPanel();
		JPanel jPanel2 = new JPanel();
		jPanel1.add(new JButton("这是面板1"));
		jPanel2.add(new JButton("这是面板2"));
		
		cards.setLayout(cardLayout);
		cards.add(jPanel1, "1");
		cards.add(jPanel2, "2");
		
		root.add(options,BorderLayout.NORTH);
		root.add(cards,BorderLayout.CENTER);	
		
		//切换选项，从而切换界面
		options.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				int Index = options.getSelectedIndex();
				if(Index==0) {
					cardLayout.show(cards, "1");
				}else if(Index==1) {
					cardLayout.show(cards, "2");
				}
				
			}
		});
	}

}
