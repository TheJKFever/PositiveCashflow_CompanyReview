package WindowBuilder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class helper extends JFrame {

	private JPanel contentPane, tabbedPanel, importPanel;
	private static Rectangle viewer = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	private JFrame popup;
	private JTable goodTable, badTable, unknownTable;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					helper frame = new helper();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public helper() {
		setTitle("Positive Cashflow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1000, viewer.height-40);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		tabbedPanel = new JPanel();
		contentPane.add(tabbedPanel);
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
				new String[][] {
						{"Date", "Transaction Description", "Amount", "Company", "Good/Bad"}
				},
				new String[] {
						"Date", "Transaction Description", "Amount", "Company", "Good/Bad"
					}
			));
		goodTable.getColumnModel().getColumn(0).setResizable(false);
		goodTable.getColumnModel().getColumn(0).setMinWidth(2);
		goodTable.getColumnModel().getColumn(0).setMaxWidth(100);
		goodTable.getColumnModel().getColumn(1).setResizable(false);
		goodTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		goodTable.getColumnModel().getColumn(1).setMinWidth(2);
		goodTable.getColumnModel().getColumn(1).setMaxWidth(500);
		goodTable.getColumnModel().getColumn(2).setResizable(false);
		goodTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		goodTable.getColumnModel().getColumn(2).setMinWidth(2);
		goodTable.getColumnModel().getColumn(2).setMaxWidth(500);
		goodTable.getColumnModel().getColumn(3).setResizable(false);
		goodTable.getColumnModel().getColumn(3).setMinWidth(1);
		goodTable.getColumnModel().getColumn(3).setMaxWidth(75);
		goodTable.getModel();
        JScrollPane scrollPaneGood = new JScrollPane(goodTable);
        goodTab.add(scrollPaneGood);
		
//CREATE BAD TAB
		JPanel badTab = new JPanel();
		tabbedPane.addTab("Bad", null, badTab, null);
		badTab.setLayout(new BoxLayout(badTab, BoxLayout.Y_AXIS));
		
		JLabel BadLabel = new JLabel("Bad Companies and Transactions");
		BadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BadLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BadLabel.setAlignmentX(0.5f);
		badTab.add(BadLabel);
		
		badTable = new JTable(){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		badTable.setRowSelectionAllowed(false);
		badTable.setFillsViewportHeight(true);
		badTable.setModel(new DefaultTableModel(
			new String[][] {
				{"Date", "Company", "Transaction Description", "Amount"},
			},
			new String[] {
				"Date", "Company", "Transaction Description", "Amount"
			}
		));
		badTable.getColumnModel().getColumn(0).setResizable(false);
		badTable.getColumnModel().getColumn(0).setMinWidth(2);
		badTable.getColumnModel().getColumn(0).setMaxWidth(100);
		badTable.getColumnModel().getColumn(1).setResizable(false);
		badTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		badTable.getColumnModel().getColumn(1).setMinWidth(2);
		badTable.getColumnModel().getColumn(1).setMaxWidth(500);
		badTable.getColumnModel().getColumn(2).setResizable(false);
		badTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		badTable.getColumnModel().getColumn(2).setMinWidth(2);
		badTable.getColumnModel().getColumn(2).setMaxWidth(500);
		badTable.getColumnModel().getColumn(3).setResizable(false);
		badTable.getColumnModel().getColumn(3).setMinWidth(1);
		badTable.getColumnModel().getColumn(3).setMaxWidth(75);
		badTable.getModel();
        JScrollPane scrollPaneBad = new JScrollPane(badTable);
        badTab.add(scrollPaneBad);



//CREATE UNKNOWN TAB
		JPanel unknownTab = new JPanel();
		tabbedPane.addTab("Unknown", null, unknownTab, null);
		unknownTab.setLayout(new BoxLayout(unknownTab, BoxLayout.Y_AXIS));
		
		JLabel unknownLabel = new JLabel("Unknown Transactions");
		unknownLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unknownLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		unknownLabel.setAlignmentX(0.5f);
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
		unknownTable.setModel(new DefaultTableModel(
			new String[][] {
				{"Date", "Transaction Description", "Amount", "Company", "Good/Bad"},
			},
			new String[] {
				"Date", "Transaction Description", "Amount", "Company", "Good/Bad"
			}
		));
		unknownTable.getColumnModel().getColumn(0).setResizable(false);
		unknownTable.getColumnModel().getColumn(0).setMinWidth(2);
		unknownTable.getColumnModel().getColumn(0).setMaxWidth(100);
		unknownTable.getColumnModel().getColumn(1).setResizable(false);
		unknownTable.getColumnModel().getColumn(1).setPreferredWidth(400);
		unknownTable.getColumnModel().getColumn(1).setMinWidth(2);
		unknownTable.getColumnModel().getColumn(1).setMaxWidth(500);
		unknownTable.getColumnModel().getColumn(2).setResizable(false);
		unknownTable.getColumnModel().getColumn(2).setMinWidth(2);
		unknownTable.getColumnModel().getColumn(2).setMaxWidth(75);
		unknownTable.getColumnModel().getColumn(3).setResizable(false);
		unknownTable.getColumnModel().getColumn(3).setPreferredWidth(300);
		unknownTable.getColumnModel().getColumn(3).setMinWidth(1);
		unknownTable.getColumnModel().getColumn(3).setMaxWidth(500);
		unknownTable.getModel();
        JScrollPane scrollPaneUnknown = new JScrollPane(unknownTable);
        unknownTab.add(scrollPaneUnknown);
	}

}
