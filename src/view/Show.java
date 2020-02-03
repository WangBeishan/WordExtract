package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ChooseFile;
import model.SaveFile;
import model.WordExtractImpl;

public class Show {
	
	private File srcFile;
	
	private int status = 0;
	
	JFrame frame;
	JPanel groundBackPanel;
	JPanel choosePanel;
	JPanel startPanel;
	JButton chooseButton;
	JButton startButton;
	JTextField fileText;
	CardLayout cLayout;
	
	ChooseFile chooseModel = new ChooseFile();
	SaveFile saveFile;
	
	public void go() {
		frame = new JFrame("WordsExtruct");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		chooseButton = new JButton("choose file");
		startButton = new JButton("start");
		chooseButton.addActionListener(new ChoosePanel());
		startButton.addActionListener(new StartPanel());
		
		choosePanel = new JPanel(new BorderLayout());
		startPanel = new JPanel();
		fileText = new JTextField();
		fileText.setText("Please choose file!");
	
		choosePanel.add(chooseButton, BorderLayout.NORTH);
		startPanel.add(startButton);
		
		frame.getContentPane().add(choosePanel, BorderLayout.NORTH);
		frame.getContentPane().add(fileText, BorderLayout.CENTER);
		frame.getContentPane().add(startPanel, BorderLayout.SOUTH);
		frame.setSize(500, 600);
		frame.setLocation(600, 400);
		frame.setVisible(true);
	}
	
	
	
	
	
	
	class StartPanel implements ActionListener {
		
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(status == 0) {
				fileText.setText("Please choose file!");
				new ChoosePanel().actionPerformed(arg0);
			}
			saveFile = new SaveFile();
			File tarFile = new File(System.getProperty("user.dir") + "/[Changed]" + srcFile.getName()	);
			saveFile.saveFile(srcFile, tarFile);
		}
		
	}
	
	
	class ChoosePanel implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter nameExten = new FileNameExtensionFilter("TXT file", "txt");
			chooser.setFileFilter(nameExten);	
			int returnVal = chooser.showOpenDialog(chooser);
			if(returnVal == chooser.APPROVE_OPTION) {
				srcFile = chooser.getSelectedFile();
				chooseModel.chooseSrcPath(srcFile);
				fileText.setText("You choiced file is:  <" + srcFile.getName() + ">");
				status = 1;
			}
		}	
	}

	
	
	

//	-------------------------------------------
	public File getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(File srcFile) {
		this.srcFile = srcFile;
	}
//	-------------------------------------------
}
