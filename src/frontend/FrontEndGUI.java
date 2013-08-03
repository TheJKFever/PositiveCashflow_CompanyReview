package frontend;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

import backend.DatabaseBE;
import baseClasses.ReadWriteCSV;
/**
 * 
 * @author Jeroen Goossens & Jon Koehmstedt
 * CSC202 Final Project
 */
public class FrontEndGUI extends JFrame {
	
	private static final long serialVersionUID = -6511477525339689224L;
	private JPanel contentPane, tabbedPanel, importPanel;
	private static UserInterfaceFE profileData;
	private static Rectangle viewer = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	private JFrame popup;
	private JTable goodTable, badTable, unknownTable, companyTable;
	private static DatabaseBE myDB;

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
	
	
	@SuppressWarnings("serial")
	private void setToTabbedPanel(){
		System.out.println("Got inside SetToTabbedPanel");
		tabbedPanel = new JPanel();
		tabbedPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanel.add(tabbedPane);
//CREATE OVERVIEW TAB		
		JPanel overviewTab = new JPanel();
		tabbedPane.addTab("Overview", null, overviewTab, null);
		overviewTab.setLayout(null);
		
  		JLabel OverviewLabel = new JLabel("Key: YELLOW - Unknown, GREEN - Good, RED - Bad");
  		OverviewLabel.setBounds((this.getBounds().width/2)-250, 29, 500, 20);
  		OverviewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
  		OverviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		overviewTab.add(OverviewLabel);
		
		//CREATE PIE CHART
		JPanel pieChartPanel = new JPanel();
		pieChartPanel.setLayout(new BoxLayout(pieChartPanel, BoxLayout.Y_AXIS));
		pieChartPanel.setBounds((this.getBounds().width/2)-250, 160, 500, 400);
		PieChart pieChart = new PieChart();
		pieChartPanel.add(pieChart);
		overviewTab.add(pieChartPanel);

//CREATE GOOD TAB
		JPanel goodTab = new JPanel();
		tabbedPane.addTab("Good", null, goodTab, null);
		goodTab.setLayout(new BoxLayout(goodTab, BoxLayout.Y_AXIS));
		
		JLabel goodLabel = new JLabel("Good Companies and Transactions");
		goodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		goodLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		goodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goodTab.add(goodLabel);
		goodTable = new JTable(){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        goodTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		goodTable.setRowSelectionAllowed(false);
		goodTable.setFillsViewportHeight(true);
		goodTable.setModel(new DefaultTableModel(
				profileData.getGoodTransactions(),
				new String[] {
						"Date", "Company", "Transaction Description", "Amount ($)"
					}
			){
			Class[] columnTypes = new Class[] {Calendar.class, String.class, String.class, String.class, String.class};
			boolean[] columnEditables = new boolean[] {false, false, false, false};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		goodTable.getColumnModel().getColumn(0).setResizable(false);
		goodTable.getColumnModel().getColumn(0).setMinWidth(2);
		goodTable.getColumnModel().getColumn(0).setMaxWidth(100);
		goodTable.getColumnModel().getColumn(1).setResizable(true);
		goodTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		goodTable.getColumnModel().getColumn(1).setMinWidth(2);
		goodTable.getColumnModel().getColumn(1).setMaxWidth(500);
		goodTable.getColumnModel().getColumn(2).setResizable(true);
		goodTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		goodTable.getColumnModel().getColumn(2).setMinWidth(2);
		goodTable.getColumnModel().getColumn(2).setMaxWidth(500);
		goodTable.getColumnModel().getColumn(3).setResizable(false);
		goodTable.getColumnModel().getColumn(3).setMinWidth(1);
		goodTable.getColumnModel().getColumn(3).setMaxWidth(100);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(goodTable.getModel());
		goodTable.setRowSorter(sorter);
//		goodTable.setAutoCreateRowSorter(true);

		goodTable.getModel();
        JScrollPane scrollPaneGood = new JScrollPane(goodTable);
        goodTab.add(scrollPaneGood);
		
//CREATE BAD TAB
		JPanel badTab = new JPanel();
		tabbedPane.addTab("bad", null, badTab, null);
		badTab.setLayout(new BoxLayout(badTab, BoxLayout.Y_AXIS));
		
		JLabel badLabel = new JLabel("Bad Companies and Transactions");
		badLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		badLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		badLabel.setHorizontalAlignment(SwingConstants.CENTER);
		badTab.add(badLabel);
		badTable = new JTable(){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        badTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		badTable.setRowSelectionAllowed(false);
		badTable.setFillsViewportHeight(true);
		badTable.setAutoCreateRowSorter(true);
		badTable.setModel(new DefaultTableModel(
				profileData.getBadTransactions(),
				new String[] {
						"Date", "Company", "Transaction Description", "Amount ($)"
					}
			){
			Class[] columnTypes = new Class[] {
					Calendar.class, String.class, String.class, String.class
				};
			public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
			public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		badTable.getColumnModel().getColumn(0).setResizable(false);
		badTable.getColumnModel().getColumn(0).setMinWidth(2);
		badTable.getColumnModel().getColumn(0).setMaxWidth(100);
		badTable.getColumnModel().getColumn(1).setResizable(true);
		badTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		badTable.getColumnModel().getColumn(1).setMinWidth(2);
		badTable.getColumnModel().getColumn(1).setMaxWidth(500);
		badTable.getColumnModel().getColumn(2).setResizable(true);
		badTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		badTable.getColumnModel().getColumn(2).setMinWidth(2);
		badTable.getColumnModel().getColumn(2).setMaxWidth(500);
		badTable.getColumnModel().getColumn(3).setResizable(false);
		badTable.getColumnModel().getColumn(3).setMinWidth(1);
		badTable.getColumnModel().getColumn(3).setMaxWidth(100);
		badTable.getModel();
        JScrollPane scrollPanebad = new JScrollPane(badTable);
        badTab.add(scrollPanebad);

//CREATE UNKNOWN TAB
      		JPanel unknownTab = new JPanel();
      		tabbedPane.addTab("unknown", null, unknownTab, null);
      		unknownTab.setLayout(new BoxLayout(unknownTab, BoxLayout.Y_AXIS));
      		
      		JLabel unknownLabel = new JLabel("Unknown Companies and Transactions");
      		unknownLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      		unknownLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
      		unknownLabel.setHorizontalAlignment(SwingConstants.CENTER);
      		unknownTab.add(unknownLabel);
      		unknownTable = new JTable(){
                  public boolean getScrollableTracksViewportWidth()
                  {
                      return getPreferredSize().width < getParent().getWidth();
                  }
              };
              unknownTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
      		unknownTable.setRowSelectionAllowed(false);
      		unknownTable.setFillsViewportHeight(true);
      		unknownTable.setAutoCreateRowSorter(true);
      		unknownTable.setModel(new DefaultTableModel(
      				profileData.getUnknownTransactions(),
      				new String[] {
      						"Date", "Transaction Description", "Amount ($)", "Company", "Good/Bad"
      					}
      			){
      			Class[] columnTypes = new Class[] {
    					Calendar.class, String.class, String.class, String.class, String.class
    				};
    			public Class getColumnClass(int columnIndex) {
    					return columnTypes[columnIndex];
    				}
    			boolean[] columnEditables = new boolean[] {
    					false, false, false, true, true
    				};
    			public boolean isCellEditable(int row, int column) {
    					return columnEditables[column];
    				}
    			});
      		unknownTable.getColumnModel().getColumn(0).setResizable(false);
      		unknownTable.getColumnModel().getColumn(0).setMinWidth(2);
      		unknownTable.getColumnModel().getColumn(0).setMaxWidth(100);
      		unknownTable.getColumnModel().getColumn(1).setResizable(true);
      		unknownTable.getColumnModel().getColumn(1).setPreferredWidth(200);
      		unknownTable.getColumnModel().getColumn(1).setMinWidth(2);
      		unknownTable.getColumnModel().getColumn(1).setMaxWidth(500);
      		unknownTable.getColumnModel().getColumn(2).setResizable(false);
      		unknownTable.getColumnModel().getColumn(2).setPreferredWidth(100);
      		unknownTable.getColumnModel().getColumn(2).setMinWidth(2);
      		unknownTable.getColumnModel().getColumn(2).setMaxWidth(100);
      		unknownTable.getColumnModel().getColumn(3).setResizable(true);
      		unknownTable.getColumnModel().getColumn(3).setPreferredWidth(200);
      		unknownTable.getColumnModel().getColumn(3).setMinWidth(1);
      		unknownTable.getColumnModel().getColumn(3).setMaxWidth(500);
      		unknownTable.getColumnModel().getColumn(4).setResizable(false);
      		unknownTable.getColumnModel().getColumn(4).setPreferredWidth(100);
      		unknownTable.getColumnModel().getColumn(4).setMinWidth(1);
      		unknownTable.getColumnModel().getColumn(4).setMaxWidth(100);
      		unknownTable.getModel();
      		setUpGood_BadColumn(unknownTable, unknownTable.getColumnModel().getColumn(4));
            JScrollPane scrollPaneUnknown = new JScrollPane(unknownTable);
            unknownTab.add(scrollPaneUnknown);        
        
//CREATE COMPANY TAB
  		JPanel companyTab = new JPanel();
  		tabbedPane.addTab("Company Summary", null, companyTab, null);
  		companyTab.setLayout(new BoxLayout(companyTab, BoxLayout.Y_AXIS));
  		
  		JLabel CompanyLabel = new JLabel("Summary of Companies");
  		CompanyLabel.setHorizontalAlignment(SwingConstants.CENTER);
  		CompanyLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
  		CompanyLabel.setAlignmentX(0.5f);
  		companyTab.add(CompanyLabel);
System.out.println("Before fourth ProfileDate call"); 
  		companyTable = new JTable(){
              public boolean getScrollableTracksViewportWidth()
              {
                  return getPreferredSize().width < getParent().getWidth();
              }
          };
System.out.println("Got through fourth Profile Data Call");			
        companyTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
  		companyTable.setRowSelectionAllowed(false);
  		companyTable.setFillsViewportHeight(true);
  		companyTable.setAutoCreateRowSorter(true);
  		companyTable.setModel(new DefaultTableModel(
  			profileData.getCompanySummary() ,
  			new String[] {
  				"Company", "Good/Bad", "Total"
  			}
  		){
  			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
  			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false
				};
			public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
  		companyTable.getColumnModel().getColumn(0).setResizable(true);
  		companyTable.getColumnModel().getColumn(0).setPreferredWidth(200);
  		companyTable.getColumnModel().getColumn(0).setMinWidth(2);
  		companyTable.getColumnModel().getColumn(0).setMaxWidth(300);
  		companyTable.getColumnModel().getColumn(1).setResizable(false);
  		companyTable.getColumnModel().getColumn(1).setPreferredWidth(75);
  		companyTable.getColumnModel().getColumn(1).setMinWidth(2);
  		companyTable.getColumnModel().getColumn(1).setMaxWidth(75);
  		companyTable.getColumnModel().getColumn(2).setResizable(false);
  		companyTable.getColumnModel().getColumn(2).setPreferredWidth(75);
  		companyTable.getColumnModel().getColumn(2).setMinWidth(2);
  		companyTable.getColumnModel().getColumn(2).setMaxWidth(75);
  		companyTable.getModel();
        JScrollPane scrollPaneCompany = new JScrollPane(companyTable);
        companyTab.add(scrollPaneCompany);             

//CREATE EXPORT TAB
		JPanel exportPanel = new JPanel();
  		tabbedPane.addTab("Export", null, exportPanel, null);
  		exportPanel.setLayout(new BoxLayout(exportPanel, BoxLayout.Y_AXIS));
		
		JPanel headerPanel = new JPanel();
		exportPanel.add(headerPanel);
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
		exportPanel.add(lowerPanel);
		GridBagLayout importBtnPanel = new GridBagLayout();
		importBtnPanel.columnWidths = new int[] {400, 100, 400};
		importBtnPanel.rowHeights = new int[] {180, 45, 180};
		importBtnPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		importBtnPanel.rowWeights = new double[]{0.0, 0.0};
		lowerPanel.setLayout(importBtnPanel);
		GridBagConstraints gbc_exportBtn_1 = new GridBagConstraints();
		gbc_exportBtn_1.insets = new Insets(0, 0, 5, 5);
		gbc_exportBtn_1.gridx = 1;
		gbc_exportBtn_1.gridy = 1;	
		JButton exportBtn = new JButton("Export To File");
		exportBtn.addActionListener(new exportAction());
		lowerPanel.add(exportBtn, gbc_exportBtn_1);
		exportBtn.setFont(new Font("Dialog", Font.PLAIN, 16));
        
        
        
        
//REMOVE IMPORT PANEL AND ADD TABBED PANEL
        System.out.println("Got to change content pane");
        
		contentPane.removeAll();
		contentPane.add(tabbedPanel);
		setContentPane(contentPane);
		revalidate();
	}
	
		
	class Slice {
		   double value;
		   Color color;
		   
		   public Slice(double value, Color color) {  
			   this.value = value;
			   this.color = color;
		   }
	}
	class PieChart extends JComponent {
		Slice[] slices = { new Slice(profileData.getPercentGood(), Color.green),
				new Slice(profileData.getPercentUnknown(), Color.yellow), new Slice(profileData.getPercentBad(), Color.red) };
		PieChart() {}
		public void paint(Graphics g) {
			drawPie((Graphics2D) g, getBounds(), slices);
		}
		void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
			double total = 0.0D;
			for (int i = 0; i < slices.length; i++) {
				total += slices[i].value;
			}
			double curValue = 0.0D;
			int startAngle = 0;
			for (int i = 0; i < slices.length; i++) {
				startAngle = (int) (curValue * 360 / total);
				int arcAngle = (int) (slices[i].value * 360 / total);
				g.setColor(slices[i].color);
				g.fillArc(area.x, area.y, area.width, area.height, 
						startAngle, arcAngle);
				curValue += slices[i].value;
			}
		}
	}

	
	
	public void setUpGood_BadColumn(JTable table, TableColumn good_BadColumn) {
		//Set up the editor for the sport cells.
		JComboBox<String> good_Bad = new JComboBox<String>();
		good_Bad.addItem("");
		good_Bad.addItem("Bad");
		good_Bad.addItem("Good");
		good_BadColumn.setCellEditor(new DefaultCellEditor(good_Bad));

		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer =
				new DefaultTableCellRenderer();
		renderer.setToolTipText("set value");
		good_BadColumn.setCellRenderer(renderer);
	}
	
	
	// display frame.
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myDB = new DatabaseBE();
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
					String inputFile = chooser.getCurrentDirectory().toString()+"\\"+chooser.getSelectedFile().getName();
System.out.println("Got to profile data read in");
						profileData = new UserInterfaceFE(inputFile, myDB);
System.out.println("read in data successfully");
						setToTabbedPanel();
				} else {
					JOptionPane.showMessageDialog(popup, "There was an error uploading the file,  please try again", "ERROR", 0);
				}
			}
			catch (Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(popup, e.getStackTrace(), "EXCEPTION", 0);
			}
		}
	}
	
	private class exportAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
			chooser.setFileFilter(filter);
			try{
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					String outputFile = chooser.getCurrentDirectory().toString()+"\\"+chooser.getSelectedFile().getName()+".csv";
					ReadWriteCSV.writeToCSV(outputFile, profileData.getGoodTransactions(), profileData.getBadTransactions(), profileData.getUnknownTransactions());
					JOptionPane.showMessageDialog(popup, "S U C C E S S ! ! !", "SUCCESS", 0);
				} else {
					JOptionPane.showMessageDialog(popup, "There was an error uploading the file,  please try again", "ERROR", 0);
				}
			}
			catch (Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(popup, e.getStackTrace(), "EXCEPTION", 0);
			}
		}
	}
	
	
	public static void main(String args[]){
		init();
	}	

}
