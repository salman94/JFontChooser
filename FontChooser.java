package font;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;


import java.awt.event.*;
import javax.swing.border.*;

public class FontChooser implements ActionListener{
	//JFrame f;
	JTextArea ta;
	JList font, fontStyle, fontSize;
	JButton ok, cancel;
	JLabel lbl;
	JPanel textPanel, centerPanel, southPanel, topPanel, panel;
	JPanel centerTextPanel;
	JDialog dialog = null;
	public FontChooser(JTextArea thisTa) {
		ta = thisTa;
		String fontString[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		String fontStyleString[] = {"Regular", "Italic", "Bold", "Bold Italic"};
		String fontSizeNumber [] = {"10","12","14","16","18","20","22","24","26","28","30","32","34","36","38","40"};
		font = new JList(fontString);
		font.setSelectedIndex(0);
		fontStyle = new JList(fontStyleString);
		fontStyle.setSelectedIndex(0);
		
		fontSize = new JList(fontSizeNumber);
		fontSize.setSelectedIndex(0);
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,3));
		topPanel.add(new JLabel("Font"));
		topPanel.add(new JLabel("Font Size"));
		topPanel.add(new JLabel("Font Style"));
		
		
		JScrollPane jfont = new JScrollPane(font);
		JScrollPane jfontStyle = new JScrollPane(fontStyle);
		JScrollPane jfontSize = new JScrollPane(fontSize);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 3));
		centerPanel.add(jfont);
		centerPanel.add(jfontStyle);
		centerPanel.add(jfontSize);
		centerPanel.add(new JLabel(" "));
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched,"Sample");
		JPanel direction = new JPanel();
		direction.setBorder(titled);
		direction.setLayout(new GridLayout(1,1));
		
		JScrollPane jTextPanel = new JScrollPane();
		direction.add(jTextPanel);
		centerPanel.add(direction);
		
		lbl = new JLabel("AbCdXyZ",SwingConstants.CENTER);
		jTextPanel.setViewportView(lbl);
		//lbl.setLineWrap(true);
		//lbl.setText("AbCdXyZ",);
		centerPanel.add(new JLabel(" "));

		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		southPanel = new JPanel(new FlowLayout());
		southPanel.add(new JLabel(" "));
		southPanel.add(new JLabel(" "));
		southPanel.add(new JLabel(" "));
		southPanel.add(ok);
		southPanel.add(cancel);
		cancel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		panel = new JPanel(new BorderLayout());
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(southPanel, BorderLayout.SOUTH);
		
		
		font.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Font newfont = valueFontChange();
				lbl.setFont(newfont);
			}
		});
		fontStyle.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Font newfont = valueFontChange();
				lbl.setFont(newfont);
			}
		});
		fontSize.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Font newfont = valueFontChange();
				lbl.setFont(newfont);
			}
		});
		
	}
	
	Font valueFontChange() {
		int setFontSize = Integer.parseInt((String) fontSize.getSelectedValue());
		int setFontStyle = fontStyle.getSelectedIndex();
		String setFont = (String)font.getSelectedValue();
		Font newfont = new Font(setFont, Font.PLAIN, setFontSize); 
		switch(setFontStyle) {
		case 0: newfont = new Font(setFont, Font.PLAIN, setFontSize);
			break;
		case 1: newfont = new Font(setFont, Font.ITALIC, setFontSize);
			break;
		case 2: newfont = new Font(setFont, Font.BOLD, setFontSize);
			break;
		case 3: newfont = new Font(setFont, Font.BOLD + Font.ITALIC, setFontSize);
			break;
		}
		return newfont;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok) {
			Font newfont = valueFontChange();
	        ta.setFont(newfont);
	        dialog.setVisible(false);
		}
		if(e.getSource() == cancel) {
			dialog.setVisible(false);
		}
	}
	
	public void showDialog(Component parent) {
		Frame owner=null;
		if(parent instanceof Frame) 
			owner=(Frame)parent;
		else
			owner=(Frame)SwingUtilities.getAncestorOfClass(Frame.class,parent);
		if(dialog==null || dialog.getOwner()!=owner)
			{
			dialog=new JDialog(owner,false);
			dialog.getContentPane().add(panel);
			dialog.getRootPane().setDefaultButton(cancel);
			}
		dialog.setSize(450,350);
		dialog.setTitle("Font");
		dialog.setVisible(true);
		
	}
	
	
	public static void main(String []args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new FontChooser(new JTextArea());
	
				
			}
		});
	}
}
