package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FrontEndGUI extends JFrame {
	
	private JPanel contentPane, tabbedPanel, importPanel;
	private static boolean import_Accepted = false;
	private UserInterfaceFE profileData = new UserInterfaceFE();
	private static Rectangle viewer = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	private JFrame popup;
	private JTable goodTable, badTable, unknownTable;

	public FrontEndGUI(){
		setTitle("Positive Cashflow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1000, viewer.height-40);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		importPanel = new JPanel();
		importPanel.setLayout(new BoxLayout(importPanel, BoxLayout.Y_AXIS));
		contentPane.add(importPanel);
		
		JPanel headerPanel = new JPanel();
		importPanel.add(headerPanel);
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		
		JLabel Logo = new JLabel("");
		headerPanel.add(Logo);
		Logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setIcon(new ImageIcon("images\\logo.png"));
		
		JLabel welcomeMessage = new JLabel("Please upload a csv file with the following in the header: \"Date\", \"Original Description\", and \"Amount\"");
		welcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomeMessage.setBorder(new EmptyBorder(10, 5, 5, 5));
		headerPanel.add(welcomeMessage);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBorder(null);
		lowerPanel.setForeground(Color.WHITE);
		importPanel.add(lowerPanel);
		GridBagLayout importBtnPanel = new GridBagLayout();
		importBtnPanel.columnWidths = new int[] {400, 100, 400};
		importBtnPanel.rowHeights = new int[] {180, 45, 180};
		importBtnPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		importBtnPanel.rowWeights = new double[]{0.0, 0.0};
		lowerPanel.setLayout(importBtnPanel);
		GridBagConstraints gbc_importBtn_1 = new GridBagConstraints();
		gbc_importBtn_1.insets = new Insets(0, 0, 5, 5);
		gbc_importBtn_1.gridx = 1;
		gbc_importBtn_1.gridy = 1;	
		JButton importBtn = new JButton("Import File");
		importBtn.addActionListener(new importAction());
		lowerPanel.add(importBtn, gbc_importBtn_1);
		importBtn.setFont(new Font("Dialog", Font.PLAIN, 16));
//		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane}));
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void setToTabbedPanel(){		
		tabbedPanel = new JPanel();
		tabbedPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanel.add(tabbedPane);
//CREATE OVERVIEW TAB		
		JPanel overviewTab = new JPanel();
		tabbedPane.addTab("Overview", null, overviewTab, null);
		overviewTab.setLayout(null);
		
		//CREATE PIE CHART
		Canvas pieChart = new Canvas();
		pieChart.setBounds(208, 184, 433, 298);
		overviewTab.add(pieChart);

//CREATE GOOD TAB
		JPanel goodTab = new JPanel();
		tabbedPane.addTab("Good", null, goodTab, null);
		goodTab.setLayout(new BoxLayout(goodTab, BoxLayout.Y_AXIS));
		
		JLabel goodLabel = new JLabel("Good Companies and Transactions");
		goodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		goodLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		goodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goodTab.add(goodLabel);
		goodTable = new JTable();
		goodTable.setRowSelectionAllowed(false);
		goodTable.setFillsViewportHeight(true);
		goodTable.setModel(new DefaultTableModel(
				new String[][] {{
						"Date", "Company", "Transaction Description", "Amount"
					}}
//				profileData.getGoodTransactions(),
			,new String[] {
				"Date", "Company", "Transaction Description", "Amount"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		goodTable.getColumnModel().getColumn(0).setResizable(false);
		goodTable.getColumnModel().getColumn(0).setMinWidth(2);
		goodTable.getColumnModel().getColumn(0).setMaxWidth(100);
		goodTable.getColumnModel().getColumn(1).setResizable(false);
		goodTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		goodTable.getColumnModel().getColumn(1).setMinWidth(2);
		goodTable.getColumnModel().getColumn(1).setMaxWidth(200);
		goodTable.getColumnModel().getColumn(2).setResizable(false);
		goodTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		goodTable.getColumnModel().getColumn(2).setMinWidth(2);
		goodTable.getColumnModel().getColumn(2).setMaxWidth(500);
		goodTable.getColumnModel().getColumn(3).setResizable(false);
		goodTable.getColumnModel().getColumn(3).setMinWidth(1);
		goodTable.getColumnModel().getColumn(3).setMaxWidth(75);
		goodTable.getModel();
		goodTab.add(goodTable);
		
//CREATE BAD TAB
		JPanel badTab = new JPanel();
		tabbedPane.addTab("Bad", null, badTab, null);
		badTab.setLayout(new BoxLayout(badTab, BoxLayout.Y_AXIS));
		
		JLabel BadLabel = new JLabel("Bad Companies and Transactions");
		BadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BadLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BadLabel.setAlignmentX(0.5f);
		badTab.add(BadLabel);
		
		badTable = new JTable();
		badTable.setRowSelectionAllowed(false);
		badTable.setFillsViewportHeight(true);
		badTable.setModel(new DefaultTableModel(
				profileData.getBadTransactions(),
			new String[] {
				"Date", "Company", "Transaction Description", "Amount"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		badTable.getColumnModel().getColumn(0).setResizable(false);
		badTable.getColumnModel().getColumn(0).setMinWidth(2);
		badTable.getColumnModel().getColumn(0).setMaxWidth(100);
		badTable.getColumnModel().getColumn(1).setResizable(false);
		badTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		badTable.getColumnModel().getColumn(1).setMinWidth(2);
		badTable.getColumnModel().getColumn(1).setMaxWidth(200);
		badTable.getColumnModel().getColumn(2).setResizable(false);
		badTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		badTable.getColumnModel().getColumn(2).setMinWidth(2);
		badTable.getColumnModel().getColumn(2).setMaxWidth(500);
		badTable.getColumnModel().getColumn(3).setResizable(false);
		badTable.getColumnModel().getColumn(3).setMinWidth(1);
		badTable.getColumnModel().getColumn(3).setMaxWidth(75);
		badTab.add(badTable);
		
//CREATE UNKNOWN TAB
		JPanel unknownTab = new JPanel();
		tabbedPane.addTab("Unknown", null, unknownTab, null);
		unknownTab.setLayout(new BoxLayout(unknownTab, BoxLayout.Y_AXIS));
		
		JLabel unknownLabel = new JLabel("Unknown Transactions");
		unknownLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unknownLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		unknownLabel.setAlignmentX(0.5f);
		unknownTab.add(unknownLabel);
		
		unknownTable = new JTable();
		unknownTable.setRowSelectionAllowed(false);
		unknownTable.setCellSelectionEnabled(true);
		unknownTable.setFillsViewportHeight(true);
		unknownTable.setModel(new DefaultTableModel(
			profileData.getUnknownTransactions(),
			new String[] {
				"Date", "Transaction Description", "Amount", "Company", "Good/Bad"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Double.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		unknownTable.getColumnModel().getColumn(0).setResizable(false);
		unknownTable.getColumnModel().getColumn(0).setMinWidth(2);
		unknownTable.getColumnModel().getColumn(0).setMaxWidth(100);
		unknownTable.getColumnModel().getColumn(1).setResizable(false);
		unknownTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		unknownTable.getColumnModel().getColumn(1).setMinWidth(2);
		unknownTable.getColumnModel().getColumn(1).setMaxWidth(500);
		unknownTable.getColumnModel().getColumn(2).setResizable(false);
		unknownTable.getColumnModel().getColumn(2).setMinWidth(2);
		unknownTable.getColumnModel().getColumn(2).setMaxWidth(75);
		unknownTable.getColumnModel().getColumn(3).setResizable(false);
		unknownTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		unknownTable.getColumnModel().getColumn(3).setMinWidth(0);
		unknownTable.getColumnModel().getColumn(3).setMaxWidth(200);
		unknownTable.getColumnModel().getColumn(4).setResizable(false);
		unknownTable.getColumnModel().getColumn(4).setMinWidth(0);
		unknownTable.getColumnModel().getColumn(4).setMaxWidth(75);
		unknownTab.add(unknownTable);					

//REMOVE IMPORT PANEL AND ADD TABBED PANEL
		contentPane.removeAll();
		contentPane.add(tabbedPanel);
		setContentPane(contentPane);
		revalidate();
	}
	
	// display frame.
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontEndGUI frame = new FrontEndGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	

/******************************************
 Get File, call Search Method, and change all user data
 *******************************************/
	private class importAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
			chooser.setFileFilter(filter);
			try{
				if ((chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)&&(chooser.getSelectedFile().getName().endsWith(".csv"))) {
					String inputFile = chooser.getCurrentDirectory().toString()+chooser.getSelectedFile().getName();
					profileData = new UserInterfaceFE(inputFile);
System.out.println("Have not set tabbed");					
					setToTabbedPanel();
System.out.println("Set Tabbed");
					import_Accepted=true;
				} else {
					JOptionPane.showMessageDialog(popup, "There was an error uploading the file,  please try again", "ERROR", 0);
				}
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(popup, e.getMessage(), "EXCEPTION", 0);
			}
		}
	}
	
	
	public static void main(String args[]){
		init();
	}	

}
