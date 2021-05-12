package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

import businessLogic.BlFacade;
import domain.Bet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
public class PastBetsGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));
	private BlFacade businessLogic;
	private String[] columnNames = new String[] {"Question", "Placed Money", "Benefits", "Answer", "Result"};
	private DefaultTableModel model = new DefaultTableModel(null, columnNames);
	private JTable table = new JTable(model);
	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	/**
	 * Create the application.
	 */
	public PastBetsGUI(BlFacade bl) 
	{
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 464, 203);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		this.businessLogic = bl;
		try 
		{
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void jbInit() throws Exception
	{
		frame = new JFrame();
		this.setSize(new Dimension(500, 300));
		this.setTitle("Past Bets");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		model.setDataVector(null, columnNames);
		for(Bet b : businessLogic.getUser().getPastBets())
		{
			Vector<Object> row = new Vector<Object>();
			row.add(b.getQuestion().getQuestion());
			row.add(b.getPlacedBet());
			row.add(b.getBenefits() - b.getPlacedBet());
			row.add(b.getAnswer());
			row.add(b.getQuestion().getResult());
			model.addRow(row);
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton_actionPerformed(e);
			}
		});
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void jButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
