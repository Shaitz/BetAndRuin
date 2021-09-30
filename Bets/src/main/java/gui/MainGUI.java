package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.BlFacade;
import domain.Event;
import domain.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mainPane;
	protected JLabel selectOptionLbl;
	private JButton browseQuestionsBtn;
	private JButton adminOptionsBtn;
	protected JButton loginRegisterBtn;
	private JButton buttonProfile;
	private JPanel localePane;
	private JRadioButton euskaraRbtn;
	private JRadioButton castellanoRbtn;
	private JRadioButton englishRbtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel userLabel;
	
	private User loggedUser = null;
	//private boolean admin = false;

	private BlFacade businessLogic;

	public BlFacade getBusinessLogic(){
		return businessLogic;
	}

	public void setBussinessLogic (BlFacade afi){
		businessLogic = afi;
	}


	public MainGUI() {
		super();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				}
				catch (Exception e1) {
					System.out.println("Error: " + e1.toString() + " , likely problems "
							+ "with Business Logic or Data Accesse");
				}
				System.exit(1);
			}
		});

		
		this.setBounds(100, 100, 572, 420);

		this.initializeMainPane();
		this.setContentPane(mainPane);
		
		
		GroupLayout gl_localePane = new GroupLayout(localePane);
		gl_localePane.setHorizontalGroup(
			gl_localePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_localePane.createSequentialGroup()
					.addGap(104)
					.addComponent(euskaraRbtn)
					.addGap(46)
					.addComponent(castellanoRbtn)
					.addGap(47)
					.addComponent(englishRbtn)
					.addContainerGap(154, Short.MAX_VALUE))
		);
		gl_localePane.setVerticalGroup(
			gl_localePane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_localePane.createSequentialGroup()
					.addContainerGap(57, Short.MAX_VALUE)
					.addGroup(gl_localePane.createParallelGroup(Alignment.BASELINE)
						.addComponent(euskaraRbtn)
						.addComponent(castellanoRbtn)
						.addComponent(englishRbtn))
					.addContainerGap())
		);
		localePane.setLayout(gl_localePane);
		userLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		GroupLayout gl_mainPane = new GroupLayout(mainPane);
		gl_mainPane.setHorizontalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
						.addComponent(localePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(loginRegisterBtn, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)
						.addComponent(adminOptionsBtn, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseQuestionsBtn, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)
						.addComponent(selectOptionLbl, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE))
					.addGap(62))
				.addGroup(gl_mainPane.createSequentialGroup()
					.addComponent(userLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
					.addComponent(buttonProfile)
					.addGap(136))
		);
		gl_mainPane.setVerticalGroup(
			gl_mainPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_mainPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userLabel)
						.addComponent(buttonProfile))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(selectOptionLbl, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(browseQuestionsBtn, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(adminOptionsBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(loginRegisterBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(localePane, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		mainPane.setLayout(gl_mainPane);

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
//		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeMainPane() 
	{
		mainPane = new JPanel();

		selectOptionLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectUseCase"));
		selectOptionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		initializeBrowseQuestionsBtn();
		initializeCreateQuestionBtn();
		initializeLoginRegisterBtn();
		initializeProfileButton();
		
		initializeLocalePane();
	}

	private void initializeBrowseQuestionsBtn() {
		browseQuestionsBtn = new JButton();
		browseQuestionsBtn.setText(ResourceBundle.getBundle("Etiquetas").
				getString("BrowseQuestions"));
		browseQuestionsBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BrowseQuestionsGUI findQuestionsWindow = new BrowseQuestionsGUI(businessLogic);
				findQuestionsWindow.setVisible(true);
			}
		});
	}

	private void initializeCreateQuestionBtn() {
		adminOptionsBtn = new JButton();
		adminOptionsBtn.setEnabled(false);
		adminOptionsBtn.setText(ResourceBundle.getBundle("Etiquetas").
				getString("AdminOptions"));
		adminOptionsBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				CreateQuestionGUI createQuestionWindow = new CreateQuestionGUI(businessLogic,
						new Vector<Event>());
				createQuestionWindow.setBusinessLogic(businessLogic);
				createQuestionWindow.setVisible(true);
			}
		});
	}

	private void initializeLoginRegisterBtn()
	{
		loginRegisterBtn = new JButton();
		loginRegisterBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("Login/Register"));
		loginRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(loggedUser == null) {
					LoginGUI loginRegisterWindow = new LoginGUI(getThis(), businessLogic);
					loginRegisterWindow.setVisible(true);
				}
				else
				{
					setUser(null);
				}
			}
		});
	}
	
	private void initializeProfileButton()
	{
		buttonProfile = new JButton("Profile");
		buttonProfile.setEnabled(false);
		buttonProfile.addActionListener(new java.awt.event.ActionListener() 
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) 
			{
				ProfileGUI profileGUI = new ProfileGUI(getThis(), businessLogic);
				profileGUI.setVisible(true);
			}
		});
	}
	
	private void initializeLocalePane() {
		localePane = new JPanel();

		initializeEuskaraRbtn();

		initializeCastellanoRbtn();

		initializeEnglishRbtn();
	}

	private void initializeEuskaraRbtn() {
		euskaraRbtn = new JRadioButton("Euskara");
		euskaraRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();
			}
		});
		buttonGroup.add(euskaraRbtn);
	}

	private void initializeCastellanoRbtn() {
		castellanoRbtn = new JRadioButton("Castellano");
		castellanoRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();
			}
		});
		buttonGroup.add(castellanoRbtn);
	}

	private void initializeEnglishRbtn() {
		englishRbtn = new JRadioButton("English");
		englishRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();				}
		});
		buttonGroup.add(englishRbtn);
	}

	private void redraw() {
		selectOptionLbl.setText(ResourceBundle.getBundle("Etiquetas").
				getString("SelectUseCase"));
		browseQuestionsBtn.setText(ResourceBundle.getBundle("Etiquetas").
				getString("BrowseQuestions"));
		adminOptionsBtn.setText(ResourceBundle.getBundle("Etiquetas").
				getString("AdminOptions"));
		if (loggedUser == null)
		{
			loginRegisterBtn.setText(ResourceBundle.getBundle("Etiquetas").
					getString("Login/Register"));
			userLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NotLogged"));
		}
		else
		{
			loginRegisterBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("LogOut"));
			userLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("LoggedUser") + loggedUser.getUsername());
		}
		buttonProfile.setText(ResourceBundle.getBundle("Etiquetas").
				getString("Profile"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	private void setMode() {
		if(loggedUser != null && loggedUser.isAdmin()) {
			//admin = true;
			adminOptionsBtn.setEnabled(true);
		}
		else {
			//admin = false;
			adminOptionsBtn.setEnabled(false);
		}
	}
	
	public void setUser(User nUser) {
		if(nUser == null) {
			loggedUser = null;
			redraw();
			buttonProfile.setEnabled(false);
			setMode();
		}
		else {
			loggedUser = businessLogic.getUser();
			redraw();
			buttonProfile.setEnabled(true);
			setMode();
		}
	}
	
	private MainGUI getThis() {
		return this;
	}
}