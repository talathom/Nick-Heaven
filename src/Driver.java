// Version 28 12/24/2018

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
//Referenced Library
import com.coremedia.iso.IsoFile;

public class Driver extends JFrame {
	
	/**
	 * Number of Series Active on Nick Heaven
	 */
	final int SIZE = 18;
	/**
	 * Width of the Launcher
	 */
	final int X = 400;
	/**
	 * Height of the Launcher
	 */
	final int Y = 575;
	
	/**
	 * Directory which the Network drive is mapped to
	 */
	static String root;
	static String error;
	static File Error = new File("Error.txt");
	

	Color NickOrange = new Color(244, 109, 37);
	
	/* Files which point to the folder of a particular series */
	File AreYouAfraidoftheDarkDir = new File(root +"\\Nick Heaven\\Are You Afraid of the Dark");
	File LastAirbenderDir = new File(root +"\\Nick Heaven\\Avatar The Last Airbender");
	File DannyPhantomDir = new File(root +"\\Nick Heaven\\Danny Phantom");
	File DrakeandJoshDir = new File(root +"\\Nick Heaven\\Drake and Josh");
	File HeyArnoldDir = new File(root +"\\Nick Heaven\\Hey Arnold");
	File InvaderZimDir = new File(root +"\\Nick Heaven\\Invader Zim");
	File JimmyNeutronDir = new File(root +"\\Nick Heaven\\Jimmy Neutron");
	File LegendofKorraDir = new File(root +"\\Nick Heaven\\Legend of Korra");
	File LegendsofTheHiddenTempleDir = new File(root +"\\Nick Heaven\\Legends of the Hidden Temple");
	File NedDir = new File(root +"\\Nick Heaven\\Ned's Declassified School Survival Guide");
	File RenandStimpyDir = new File(root +"\\Nick Heaven\\Ren & Stimpy");
	File RockoDir = new File(root +"\\Nick Heaven\\Rocko's Modern Life");
	File RugratsDir = new File(root +"\\Nick Heaven\\Rugrats");
	File SpongebobDir = new File(root +"\\Nick Heaven\\Spongebob");
	File FairlyOddParentsDir = new File(root +"\\Nick Heaven\\The Fairly OddParents");
	File HalloweenDir = new File(root +"\\Nick Heaven\\Holiday Episodes\\Halloween");
	File RobotDir = new File(root +"\\Nick Heaven\\My Life as a Teenage Robot");
	File DougDir = new File(root +"\\Nick Heaven\\Doug");
	File CatDogDir = new File(root +"\\Nick Heaven\\CatDog");
	File PeteandPeteDir = new File("");
	File TMNTDir = new File(root +"\\Nick Heaven\\TMNT");
	File SecretDir = new File(root +"\\Nick Heaven\\Secret");
	
	/*
	 * Arrays of Files which load all of the files within a shows directory into
	 * the array These arrays contain the actual mp4's while the previous files
	 * simply pointed to a directory
	 */
	File[] AreYouAfraidoftheDark = AreYouAfraidoftheDarkDir.listFiles();
	File[] LastAirbender = LastAirbenderDir.listFiles();
	File[] DannyPhantom = DannyPhantomDir.listFiles();
	File[] DrakeandJosh = DrakeandJoshDir.listFiles();
	File[] HeyArnold = HeyArnoldDir.listFiles();
	File[] InvaderZim = InvaderZimDir.listFiles();
	File[] JimmyNeutron = JimmyNeutronDir.listFiles();
	File[] LegendofKorra = LegendofKorraDir.listFiles();
	File[] LegendsofTheHiddenTemple = LegendsofTheHiddenTempleDir.listFiles();
	File[] Ned = NedDir.listFiles();
	File[] RenandStimpy = RenandStimpyDir.listFiles();
	File[] Rocko = RockoDir.listFiles();
	File[] Rugrats = RugratsDir.listFiles();
	File[] Spongebob = SpongebobDir.listFiles();
	File[] Robot = RobotDir.listFiles();
	File[] FairlyOddParents = FairlyOddParentsDir.listFiles();
	File[] HalloweenSpecials = HalloweenDir.listFiles();
	File[] CatDog = CatDogDir.listFiles();
	File[] TMNT = TMNTDir.listFiles();
	File[] Doug = DougDir.listFiles();
	File[] Secret = SecretDir.listFiles();
	File[] Avatar = new File[LegendofKorra.length + LastAirbender.length];
	
	//Booleans for if a show is selected to run, note that these variables are always lower case, unlike their respecitve File Arrays
	Series dannyphantom = new Series(DannyPhantom, "Danny Phantom");
	Series areyouafraidofthedark = new Series(AreYouAfraidoftheDark, "Are You Afraid of the Dark");
	Series lastairbender = new Series(LastAirbender, "Last Airbender");
	Series drakeandjosh = new Series(DrakeandJosh, "Drake and Josh");
	Series heyarnold = new Series(HeyArnold, "Hey Arnold");
	Series invaderzim = new Series(InvaderZim, "Invader Zim");
	Series jimmyneutron = new Series(JimmyNeutron, "Jimmy Neutron");
	Series legendofkorra = new Series(LegendofKorra, "Legend of Korra");
	Series legendsofthehiddentemple = new Series(LegendsofTheHiddenTemple, "Legends of the Hidden Temple");
	Series ned = new Series(Ned, "Ned");
	Series renandstimpy = new Series(RenandStimpy, "Ren and Stimpy");
	Series rocko = new Series(Rocko, "Rocko");
	Series rugrats = new Series(Rugrats, "Rugrats");
	Series spongebob = new Series(Spongebob, "Spongebob");
	Series robot = new Series(Robot, "Robot");
	Series fairlyoddparent = new Series(FairlyOddParents, "The Fairly Oddparents");
	Series catdog = new Series(CatDog, "Catdog");
	Series tmnt = new Series(TMNT, "TMNT");
	Series doug = new Series(Doug, "Doug");
	Series secret = new Series(Secret, "YTP");
	Series avatar;
	//Series nineties = new Series(Nineties, "Nineties");
	//Series twok = new Series(Twok, "Twok");

	ImageIcon DannyPhantomIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\DannyPhantom.png");
	ImageIcon NickHeavenIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\NickHeaven.png");
	ImageIcon KorraIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\Korra.png");
	ImageIcon HalloweenIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\halloween.png");
	ImageIcon AprilIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\April.png");
	ImageIcon SpongebobIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\spingebill.png");
	ImageIcon RobotIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\robot.png");
	ImageIcon BlackSunIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\BlackSun.png");
	ImageIcon ChristmasIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\Nickmas.png");
	ImageIcon DrakeandJoshIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\DrakeandJosh.png");
	ImageIcon AirbenderIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\airbender.png");
	ImageIcon DarkIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\dark.png");
	ImageIcon ArnoldIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\heyarnold.png");
	ImageIcon ZimIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\zim.png");
	ImageIcon NedIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\ned.png");
	ImageIcon RSIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\renandstimpy.png");
	ImageIcon RockoIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\rocko.png");
	ImageIcon RugratsIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\rugrats.png");
	ImageIcon OddParentsIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\oddparents.png");
	ImageIcon DougIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\doug.png");
	ImageIcon CatDogIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\catdog.png");
	ImageIcon TMNTIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\tmnt.png");
	ImageIcon TempleIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\temple.png");
	ImageIcon NeutronIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\jimmyneutron.png");
	ImageIcon NinetiesIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\nineties.png");
	ImageIcon TwoKIcon = new ImageIcon(root +"\\Nick Heaven\\Logos\\2k.png");

	static Random r = new Random(); // Random Object
	JPanel panel = new JPanel(); // Panel for the GUI
	JButton launchBUT = new JButton("Launch Nick Heaven!"); // Button that Launches Nick Heaven
	JButton OnBUT = new JButton("ON");
	JButton OffBUT = new JButton("OFF");
	JButton DannyPhantomBUT = new JButton();
	JButton KorraBUT = new JButton();
	JButton HalloweenBUT = new JButton();
	JButton AprilBUT = new JButton();
	JButton stopBUT = new JButton("RESET");
	JButton SpongeBUT = new JButton();
	JButton RobotBUT = new JButton();
	JButton BlackSunBUT = new JButton();
	JButton ChristmasBUT = new JButton();
	JButton DrakeandJoshBUT = new JButton();
	JButton AirbenderBUT = new JButton();
	JButton DarkBUT = new JButton();
	JButton ArnoldBUT = new JButton();
	JButton ZimBUT = new JButton();
	JButton NedBUT = new JButton();
	JButton RSBUT = new JButton();
	JButton RockoBUT = new JButton();
	JButton RugratBUT = new JButton();
	JButton OddParentsBUT = new JButton();
	JButton DougBUT = new JButton();
	JButton CatDogBUT = new JButton();
	JButton TMNTBUT = new JButton();
	JButton TempleBUT = new JButton();
	JButton NeutronBUT = new JButton();
	JButton NinetiesBUT = new JButton();
	JButton TwoKBUT = new JButton();
	static JProgressBar bar = new JProgressBar();
	static JLabel FileDisplay = new JLabel();
	static JLabel UpNextDisplay = new JLabel();
	Font displayFont = new Font("Calibri", Font.BOLD, 12);
	Font stopFont = new Font("Calibri", Font.BOLD, 14);
	Font statusFont = new Font("Calibri", Font.BOLD, 9);

	public Driver() {
		combineArrays();
		avatar = new Series(Avatar, "Avatar");
		setTitle("Nick Heaven Control Panel");
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(X, Y);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Closes the Program on X button press
		panel.setLayout(null);
		launchBUT.setToolTipText("Launch Nick Heaven");
		launchBUT.setBounds((int) (X*.3), 10, 143, 75);
		launchBUT.setIcon(NickHeavenIcon);
		launchBUT.addActionListener(new launchBUTListener()); // Adds the Action Listener to the button
		
		//Episode Flagger Buttons
		//Row 0
		OnBUT.setToolTipText("Enable All Shows");
		OnBUT.setBounds(15, 35, 50, 50);
		OnBUT.addActionListener(new OnButton());
		OnBUT.setBackground(Color.GREEN);
		OnBUT.setFont(statusFont);
		OffBUT.setToolTipText("Disable All Shows");
		OffBUT.setBounds(315, 35, 50, 50);
		OffBUT.addActionListener(new OffButton());
		OffBUT.setBackground(Color.RED);
		OffBUT.setFont(statusFont);
		//Row 1
		DannyPhantomBUT.setToolTipText("Enable Danny Phantom");
		DannyPhantomBUT.setBounds(15, 95, 50, 50);
		DannyPhantomBUT.addActionListener(new DannyPhantom());
		DannyPhantomBUT.setIcon(DannyPhantomIcon);
		DannyPhantomBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		KorraBUT.setToolTipText("Enable Legend of Korra");
		KorraBUT.setBounds(75, 95, 50, 50);
		KorraBUT.addActionListener(new LegendofKorra());
		KorraBUT.setIcon(KorraIcon);
		KorraBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		RobotBUT.setToolTipText("Enable My Life as a Teenage Robot");
		RobotBUT.setBounds(135, 95, 50, 50);
		RobotBUT.addActionListener(new Robot());
		RobotBUT.setIcon(RobotIcon);
		RobotBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		SpongeBUT.setToolTipText("Enable Spongebob");
		SpongeBUT.setBounds(195, 95, 50, 50);
		SpongeBUT.addActionListener(new Spongebob());
		SpongeBUT.setIcon(SpongebobIcon);
		SpongeBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		DrakeandJoshBUT.setToolTipText("Enable Drake and Josh");
		DrakeandJoshBUT.setBounds(255, 95, 50, 50);
		DrakeandJoshBUT.addActionListener(new DrakeandJosh());
		DrakeandJoshBUT.setIcon(DrakeandJoshIcon);
		DrakeandJoshBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		AirbenderBUT.setToolTipText("Enable Avatar: The Last");
		AirbenderBUT.setBounds(315, 95, 50, 50);
		AirbenderBUT.addActionListener(new Airbender());
		AirbenderBUT.setIcon(AirbenderIcon);
		AirbenderBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		//ROW 2
		DarkBUT.setToolTipText("Enable Are You Afraid of the Dark");
		DarkBUT.setBounds(15, 155, 50, 50);
		DarkBUT.addActionListener(new Dark());
		DarkBUT.setIcon(DarkIcon);
		DarkBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		ArnoldBUT.setToolTipText("Enable Hey Arnold");
		ArnoldBUT.setBounds(75, 155, 50, 50);
		ArnoldBUT.addActionListener(new Arnold());
		ArnoldBUT.setIcon(ArnoldIcon);
		ArnoldBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		ZimBUT.setToolTipText("Enable Invader Zim");
		ZimBUT.setBounds(135, 155, 50, 50);
		ZimBUT.addActionListener(new Zim());
		ZimBUT.setIcon(ZimIcon);
		ZimBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		NedBUT.setToolTipText("Enable Ned's Declassified School Survival Guide");
		NedBUT.setBounds(195, 155, 50, 50);
		NedBUT.addActionListener(new Ned());
		NedBUT.setIcon(NedIcon);
		NedBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		RSBUT.setToolTipText("Enable Ren and Stimpy");
		RSBUT.setBounds(255, 155, 50, 50);
		RSBUT.addActionListener(new RenandStimpy());
		RSBUT.setIcon(RSIcon);
		RSBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		RockoBUT.setToolTipText("Enable Rocko's Modern Life");
		RockoBUT.setBounds(315, 155, 50, 50);
		RockoBUT.addActionListener(new Rocko());
		RockoBUT.setIcon(RockoIcon);
		RockoBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		
		//Row 3
		RugratBUT.setToolTipText("Enable Rugrats");
		RugratBUT.setBounds(15, 215, 50, 50);
		RugratBUT.addActionListener(new Rugrat());
		RugratBUT.setIcon(RugratsIcon);
		RugratBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		OddParentsBUT.setToolTipText("Enable Fairly OddParents");
		OddParentsBUT.setBounds(75, 215, 50, 50);
		OddParentsBUT.addActionListener(new OddParents());
		OddParentsBUT.setIcon(OddParentsIcon);
		OddParentsBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		DougBUT.setToolTipText("Enable Doug");
		DougBUT.setBounds(135, 215, 50, 50);
		DougBUT.addActionListener(new Doug());
		DougBUT.setIcon(DougIcon);
		DougBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		CatDogBUT.setToolTipText("Enable CatDog");
		CatDogBUT.setBounds(195, 215, 50, 50);
		CatDogBUT.addActionListener(new CatDog());
		CatDogBUT.setIcon(CatDogIcon);
		CatDogBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		TMNTBUT.setToolTipText("Enable Teenage Mutant Ninja Turtles");
		TMNTBUT.setBounds(255, 215, 50, 50);
		TMNTBUT.addActionListener(new TMNT());
		TMNTBUT.setIcon(TMNTIcon);
		TMNTBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		TempleBUT.setToolTipText("Enable Legends of the Hidden Temple");
		TempleBUT.setBounds(315, 215, 50, 50);
		TempleBUT.addActionListener(new Temple());
		TempleBUT.setIcon(TempleIcon);
		TempleBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		
		//Row 4
		NeutronBUT.setToolTipText("Enable Jimmy Neutron");
		NeutronBUT.setBounds(15, 275, 50, 50);
		NeutronBUT.addActionListener(new JimmyNeutron());
		NeutronBUT.setIcon(NeutronIcon);
		NeutronBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		NinetiesBUT.setToolTipText("Enable 90's Commercials");
		NinetiesBUT.setBounds(75, 275, 50, 50);
		NinetiesBUT.setIcon(NinetiesIcon);
		NinetiesBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		NinetiesBUT.setEnabled(false);
		TwoKBUT.setToolTipText("Enable 00's Commercials");
		TwoKBUT.setBounds(135, 275, 50, 50);
		TwoKBUT.setIcon(TwoKIcon);
		TwoKBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		TwoKBUT.setEnabled(false);
		
		//Holiday Buttons
		HalloweenBUT.setToolTipText("Starts a Halloween Marathon");
		HalloweenBUT.setBounds(15, 335, 50, 50);
		HalloweenBUT.addActionListener(new Halloween());
		HalloweenBUT.setIcon(HalloweenIcon);
		AprilBUT.setToolTipText("Starts the April Fools Day Event");
		AprilBUT.setBounds(75, 335, 50, 50);
		AprilBUT.addActionListener(new AprilFools());
		AprilBUT.setIcon(AprilIcon);
		BlackSunBUT.setToolTipText("Starts an Avatar Marathon");
		BlackSunBUT.setIcon(BlackSunIcon);
		BlackSunBUT.setBounds(135, 335, 50, 50);
		BlackSunBUT.addActionListener(new BlackSun());
		ChristmasBUT.setToolTipText("NYI - Starts a Christmas Special Marathon - NYI");
		ChristmasBUT.setBounds(195, 335, 50, 50);
		ChristmasBUT.addActionListener(new Christmas());
		ChristmasBUT.setIcon(ChristmasIcon);
		ChristmasBUT.setEnabled(false);
		
		//Special Button
		stopBUT.setToolTipText("Reset the Control Panel");
		stopBUT.setBounds((int) (X*.35), 450, 100, 50);
		stopBUT.addActionListener(new restartListener());
		stopBUT.setFont(stopFont);
		stopBUT.setForeground(NickOrange);
		stopBUT.setBackground(Color.WHITE);
		
		//ProgressBar
		bar.setBounds(15, 510, X-40, 25);
		
		//Episode Information
		FileDisplay.setBounds(15, 400, X-40, 25);
		FileDisplay.setFont(displayFont);
		UpNextDisplay.setBounds(15, 425, X-40, 25);
		UpNextDisplay.setFont(displayFont);
		
		//Panel Setup
		panel.add(launchBUT);
		panel.add(OnBUT);
		panel.add(OffBUT);
		panel.add(DannyPhantomBUT);
		panel.add(KorraBUT);
		panel.add(HalloweenBUT);
		panel.add(AprilBUT);
		panel.add(stopBUT);
		panel.add(SpongeBUT);
		panel.add(RobotBUT);
		panel.add(BlackSunBUT);
		panel.add(ChristmasBUT);
		panel.add(DrakeandJoshBUT);
		panel.add(AirbenderBUT);
		panel.add(ArnoldBUT);
		panel.add(CatDogBUT);
		panel.add(DarkBUT);
		panel.add(DougBUT);
		panel.add(NedBUT);
		panel.add(OddParentsBUT);
		panel.add(RSBUT);
		panel.add(RockoBUT);
		panel.add(RugratBUT);
		panel.add(TMNTBUT);
		panel.add(ZimBUT);
		panel.add(TempleBUT);
		panel.add(NeutronBUT);
		panel.add(NinetiesBUT);
		panel.add(TwoKBUT);
		panel.add(bar);
		panel.add(FileDisplay);
		panel.add(UpNextDisplay);
		panel.setBackground(NickOrange);
		add(panel);
		setVisible(true);
		/* Adds the panel and makes the GUI visible */
	}
	
	public void combineArrays() {
		for(int i = 0; i < LastAirbender.length; i++) {
			Avatar[i] = LastAirbender[i];
		}
		int j = LastAirbender.length;
		for(int i = 0; i < LegendofKorra.length; i++) {
			Avatar[j] = LegendofKorra[i];
			j++;
		}
	}

	/*
	 * ActionListener for the Launch Button which implements ActionListener and
	 * extends Thread by extending Thread the buttons code is multithreaded and
	 * runs on a separate thread from the rest of the GUI. This allows
	 * interactions to occur with the GUI while the rest of the program is
	 * waiting for the current episode to end.
	 */
	private class launchBUTListener extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			start(); // Starts the thread
		}

		// When the thread runs (buttons is pressed)
		public void run() {
			NormalOperation();
			}
		}

	/**
	 * Picks and new episode from the array of given episodes and plays it, then
	 * returns the length of the episode so that the loop can wait for it to finish
	 * @param series Array of Episodes
	 * @return Duration of the Episode chosen
	 */
	public static long playEpisode(File[] series) {
		long duration = 0;

		try {
			int episode;
			episode = r.nextInt(series.length) - 1; // Picks and episode
			System.out.println(series[episode].toString());
			IsoFile isoFile = new IsoFile(series[episode].getPath());
			duration = isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
			System.out.println(duration);
			Desktop.getDesktop().open(series[episode]); // Opens the episode
			long elapsed = 0;
			bar.setMaximum((int) duration);
			FileDisplay.setText(series[episode].toString());
			FileDisplay.setToolTipText("Now Playing: "+ series[episode].toString());
			while(elapsed < duration){
					Thread.sleep(TimeUnit.SECONDS.toMillis(1));
					bar.setValue((int) elapsed);
					bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
					elapsed++;
			}
			bar.setValue(0);

		} catch (IOException e) {
			error = e.getMessage();
			FileWriter fw;
			try {
				fw = new FileWriter(Error);
				fw.write(error);
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (InterruptedException e) {
			error = e.getMessage();
			FileWriter fw;
			try {
				fw = new FileWriter(Error);
				fw.write(error);
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return duration;
	}
	
	
	/**
	 * Sorts the Episodes in order of Lowest Number Season First then Lowest Number Episode of Each Season.
	 * @param series Array of Epsiodes belonging to a show
	 * @return Returns a List of the Episodes sorted from S1E1 to SxEy where x is the final season and y is the final epsiode of season y 
	 */
	public static ArrayList<Episode> sortEpisodes(File[] series){
		ArrayList<Episode> episodes = new ArrayList<Episode>();
		String season, episodeNum, name;
		for (int i = 0; i < series.length; i++) {
			name = series[i].getName();
			if(name.charAt(0) != 'S'){
			season = name.substring(name.indexOf("S") + 1, name.indexOf("E"));
			} else {
				name = name.substring(1, name.length());
				System.out.println(name);
				season = name.substring(name.indexOf("S") + 1, name.indexOf("E"));
			}

			if (!name.contains("&")) {
				episodeNum = name.substring(name.indexOf("E") + 1, name.indexOf("."));
			} else {
				episodeNum = name.substring(name.indexOf("E") + 1, name.indexOf("&"));
			}
			episodes.add(new Episode(season, episodeNum, series[i]));
		}
		Episode temp;
		for (int i = 0; i < episodes.size(); i++) {
			for (int j = 0; j < episodes.size(); j++) {
				if (episodes.get(i).isGreater(episodes.get(j))) {
					temp = episodes.get(j);
					episodes.set(j, episodes.get(i));
					episodes.set(i, temp);
				}
			}
		}
		return episodes;
	}
	
	private class DrakeandJosh extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(drakeandjosh.isEnabled()) {
				DrakeandJoshBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				drakeandjosh.disable();
			} else {
				DrakeandJoshBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				drakeandjosh.enable();
			}
		}

	}

	private class DannyPhantom implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(dannyphantom.isEnabled()) {
				DannyPhantomBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				dannyphantom.disable();
			} else {
				DannyPhantomBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				dannyphantom.enable();
			}
		}

	}
	
	private class LegendofKorra extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(legendofkorra.isEnabled()) {
				KorraBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				legendofkorra.disable();
			} else {
				KorraBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				legendofkorra.enable();
			}
		}

	}
	
	private class Christmas extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			while(!this.currentThread().isInterrupted()){
			//duration = playEpisode(HalloweenSpecials);
			}
		}
	}

	private class Halloween extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			while(!this.currentThread().isInterrupted()){
			duration = playEpisode(HalloweenSpecials);
			}
		}
	}
	
	private class AprilFools extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			try{
			long duration;
			boolean zero = false, four = false, eight = false, twelve = false, sixteen = false, twenty = false;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
			LocalDateTime now = LocalDateTime.now();
			
			
			IsoFile isoFile; 
			
			while(!this.isInterrupted()){
			if(Integer.parseInt(dtf.format(now)) >= 0 && !zero){
				duration = playAprilFoolsEpisode();
				zero = true;
				
			} else if(Integer.parseInt(dtf.format(now)) >= 4 && !four){
				duration = playAprilFoolsEpisode();
				four = true;
				
			} else if(Integer.parseInt(dtf.format(now)) >= 8 && !eight){
				duration = playAprilFoolsEpisode();
				eight = true;
				
			} else if(Integer.parseInt(dtf.format(now)) >= 12 && !twelve){
				duration = playAprilFoolsEpisode();
				twelve = true;
				
			} else if(Integer.parseInt(dtf.format(now)) >= 16 && !sixteen){
				duration = playAprilFoolsEpisode();
				sixteen = true;
				
			} else if(Integer.parseInt(dtf.format(now)) >= 20 && !twenty){
				duration = playAprilFoolsEpisode();
				twenty = true;
				
			} else {
				NormalOperation();
		}
			}
			} catch(Exception e){
				e.printStackTrace();
				FileWriter fw;
				try {
					fw = new FileWriter(Error);
					fw.write(error);
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
			
	}
	
	public static long playAprilFoolsEpisode(){
		long duration = 1000;
		try{
		File AprilFoolsSpecial = new File(root +"\\Nick Heaven\\Holiday Episodes\\April Fool's\\Spongebob S1E38 (April Fool's Special).mp4");
		IsoFile isoFile = isoFile = new IsoFile(AprilFoolsSpecial.getPath());
		duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
		Desktop.getDesktop().open(AprilFoolsSpecial);
		long elapsed = 0;
		bar.setMaximum((int) duration);
		FileDisplay.setText(AprilFoolsSpecial.toString());
		FileDisplay.setToolTipText("Now Playing: "+ AprilFoolsSpecial.toString());
		while(elapsed < duration){
				Thread.sleep(TimeUnit.SECONDS.toMillis(1));
				bar.setValue((int) elapsed);
				bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
				elapsed++;
				System.out.println("Looped"+ elapsed);
		}
		bar.setValue(0);
		} catch(IOException e){
			e.printStackTrace();
			FileWriter fw;
			try {
				fw = new FileWriter(Error);
				fw.write(error);
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			FileWriter fw;
			try {
				fw = new FileWriter(Error);
				fw.write(error);
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return duration;
	}
	
	private class restartListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Runtime rt = Runtime.getRuntime();
			try {
				Process pr = rt.exec("java -jar \""+ root +"\\Nick Heaven\\Launcher\\Nick Heaven.jar\"");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}
	
	public void NormalOperation(){
		long duration;
		int roll = -1;
		String current = "";
		String upNext;
		String nextShow;
		LocalDateTime now;
		DateTimeFormatter day = DateTimeFormatter.ofPattern("EEE");
		DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH");
		now = LocalDateTime.now();
		
		ArrayList<Series> shows = new ArrayList<Series>();
		
		while (!Thread.currentThread().isInterrupted()) {
			nextShow = "Up Next: ";
			if (areyouafraidofthedark.isEnabled() && (Integer.parseInt(hour.format(now)) >= 16 || Integer.parseInt(hour.format(now)) < 6)) {
				shows.add(areyouafraidofthedark);
			}
			
			if(lastairbender.isEnabled() && legendofkorra.isEnabled()) {
				shows.add(avatar);
			} else {
				if(lastairbender.isEnabled()){
					shows.add(lastairbender);
				}
				if(legendofkorra.isEnabled()){
					shows.add(legendofkorra);
				}
			}
			if(dannyphantom.isEnabled()) {
				shows.add(dannyphantom);
			}
			if(drakeandjosh.isEnabled()) {
				shows.add(drakeandjosh);
			}
			if(heyarnold.isEnabled()) {
				shows.add(heyarnold);
			}
			if(invaderzim.isEnabled()) {
				shows.add(invaderzim);
			}
			if(jimmyneutron.isEnabled()) {
				shows.add(jimmyneutron);
			}
			if(robot.isEnabled()) {
				shows.add(robot);
			}
			if(ned.isEnabled()) {
				shows.add(ned);
			}
			if(renandstimpy.isEnabled()) {
				shows.add(renandstimpy);
			} 
			if(rocko.isEnabled()) {
				shows.add(rocko);
			}
			if(rugrats.isEnabled()) {
				shows.add(rugrats);
			}
			if(spongebob.isEnabled()) {
				shows.add(spongebob);
			}
			if(fairlyoddparent.isEnabled()) {
				shows.add(fairlyoddparent);
			}
			if(catdog.isEnabled()){
				shows.add(catdog);
			}
			if(tmnt.isEnabled()) {
				shows.add(tmnt);
			}
			if(doug.isEnabled()) {
				shows.add(doug);
			} 
			if(legendsofthehiddentemple.isEnabled() && day.format(now).equals("Sun")) {
				shows.add(legendsofthehiddentemple);
			}
			if(!areyouafraidofthedark.isEnabled() && !lastairbender.isEnabled() && !legendofkorra.isEnabled() && !dannyphantom.isEnabled() && !drakeandjosh.isEnabled() && !heyarnold.isEnabled() && !invaderzim.isEnabled() && !jimmyneutron.isEnabled() && !robot.isEnabled() && !ned.isEnabled() && !renandstimpy.isEnabled() && !rocko.isEnabled() && !rugrats.isEnabled() && !spongebob.isEnabled() && !fairlyoddparent.isEnabled() && !catdog.isEnabled() && !tmnt.isEnabled() && !doug.isEnabled() && !legendsofthehiddentemple.isEnabled()) { 
				shows.add(secret);
			}
			
			if(roll == -1) {
				current = shows.get(r.nextInt(shows.size())).getName();
			}
			roll = r.nextInt(shows.size());
			upNext = shows.get(roll).getName();
			nextShow = nextShow.concat(shows.get(roll).getName());
			UpNextDisplay.setText(nextShow);
			System.out.println(shows.get(roll).getName());
			
			//NEXT SHOW
			if (current.equals("Are You Afraid of the Dark")) {
				duration = playEpisode(AreYouAfraidoftheDark);
				
			} else if (current.equals("Avatar")) {
				duration = playEpisode(Avatar);
				
			} else if(current.equals("Last Airbender")){
				duration = playEpisode(LastAirbender);
				
			} else if(current.equals("Legend of Korra")){
				duration = playEpisode(LegendofKorra);
				
			} else if (current.equals("Danny Phantom")) {
				duration = playEpisode(DannyPhantom);

			} else if (current.equals("Drake and Josh")) {
				duration = playEpisode(DrakeandJosh);

			} else if (current.equals("Hey Arnold")) {
				duration = playEpisode(HeyArnold);

			} else if (current.equals("Invader Zim")) {
				duration = playEpisode(InvaderZim);

			} else if (current.equals("Jimmy Neutron")) {
				duration = playEpisode(JimmyNeutron);

			} else if (current.equals("Robot")) {
				duration = playEpisode(Robot);

			} else if (current.equals("Ned")) {
				duration = playEpisode(Ned);

			} else if (current.equals("Ren and Stimpy")) {
				duration = playEpisode(RenandStimpy);

			} else if (current.equals("Rocko")) {
				duration = playEpisode(Rocko);

			} else if (current.equals("Rugrats")) {
				duration = playEpisode(Rugrats);

			} else if (current.equals("Spongebob")) {
				for(int k = 0; k < 2; k++){
				duration = playEpisode(Spongebob);
				}
			} else if (current.equals("The Fairly Oddparents")) {
				duration = playEpisode(FairlyOddParents);
				
			} else if (current.equals("Catdog")){
				duration = playEpisode(CatDog);
		
			} else if(current.equals("TMNT")) {
				duration = playEpisode(TMNT);
			
			} else if(current.equals("Doug")) {
				duration = playEpisode(Doug);
				
			} else if(current.equals("Legends of the Hidden Temple")) {
				duration = playEpisode(LegendsofTheHiddenTemple);
				
			} else if(current.equals("YTP")) { 
				duration = playEpisode(Secret);
				
			} else {
				duration = 0;
			}
			
			current = upNext;
			shows.clear();
	}
	}
	
	private class Robot extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(robot.isEnabled()) {
				RobotBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				robot.disable();
			} else {
				RobotBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				robot.enable();
			}
		}

	}
	
	private class Airbender extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(lastairbender.isEnabled()) {
				AirbenderBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				lastairbender.disable();
			} else {
				AirbenderBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				lastairbender.enable();
			}
		}

	}
	
	private class Dark extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(areyouafraidofthedark.isEnabled()) {
				DarkBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				areyouafraidofthedark.disable();
			} else {
				DarkBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				areyouafraidofthedark.enable();
			}
		}

	}
	
	private class Arnold extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(heyarnold.isEnabled()) {
				ArnoldBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				heyarnold.disable();
			} else {
				ArnoldBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				heyarnold.enable();
			}
		}

	}
	
	private class Zim extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(invaderzim.isEnabled()) {
				ZimBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				invaderzim.disable();
			} else {
				ZimBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				invaderzim.enable();
			}
		}

	}
	
	private class Ned extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(ned.isEnabled()) {
				NedBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				ned.disable();
			} else {
				NedBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				ned.enable();
			}
		}

	}
	
	private class RenandStimpy extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(renandstimpy.isEnabled()) {
				RSBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				renandstimpy.disable();
			} else {
				RSBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				renandstimpy.enable();
			}
		}

	}
	
	private class Rocko extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(rocko.isEnabled()) {
				RockoBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				rocko.disable();
			} else {
				RockoBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				rocko.enable();
			}
		}

	}
	
	private class Rugrat extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(rugrats.isEnabled()) {
				RugratBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				rugrats.disable();
			} else {
				RugratBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				rugrats.enable();
			}
		}

	}
	
	private class OddParents extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(fairlyoddparent.isEnabled()) {
				OddParentsBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				fairlyoddparent.disable();
			} else {
				OddParentsBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				fairlyoddparent.enable();
			}
		}

	}
	
	private class Doug extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(doug.isEnabled()) {
				DougBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				doug.disable();
			} else {
				DougBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				doug.enable();
			}
		}

	}
	
	private class CatDog extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(catdog.isEnabled()) {
				CatDogBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				catdog.disable();
			} else {
				CatDogBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				catdog.enable();
			}
		}

	}
	
	private class TMNT extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(tmnt.isEnabled()) {
				TMNTBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				tmnt.disable();
			} else {
				TMNTBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				tmnt.enable();
			}
		}

	}
	
	private class Temple extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(legendsofthehiddentemple.isEnabled()) {
				TempleBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				legendsofthehiddentemple.disable();
			} else {
				TempleBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				legendsofthehiddentemple.enable();
			}
		}

	}
	
	private class JimmyNeutron extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(jimmyneutron.isEnabled()) {
				NeutronBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				jimmyneutron.disable();
			} else {
				NeutronBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				jimmyneutron.enable();
			}
		}

	}
	
	private class Spongebob extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(spongebob.isEnabled()) {
				SpongeBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				spongebob.disable();
			} else {
				SpongeBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				spongebob.disable();
			}
		}

	}
	

	
	private class BlackSun extends Thread implements ActionListener{
		public void actionPerformed(ActionEvent e){
			start();
		}
		
		public void run(){
			long duration;
			ArrayList<Episode> episodes = sortEpisodes(LastAirbender);
			try{
				File firstTrailer = new File(root +"\\Nick Heaven\\Airbender Trailers\\Day of Black Sun.mp4");
				File secondTrailer = new File(root +"\\Nick Heaven\\Airbender Trailers\\Trailer.mp4");
				IsoFile isoFileT = new IsoFile(root +"\\Nick Heaven\\Airbender Trailers\\Day of Black Sun.mp4");
				duration = (long) isoFileT.getMovieBox().getMovieHeaderBox().getDuration() / isoFileT.getMovieBox().getMovieHeaderBox().getTimescale();
				Desktop.getDesktop().open(firstTrailer);
				long elapsed = 0;
				bar.setMaximum((int) duration);
				FileDisplay.setText(firstTrailer.toString());
				FileDisplay.setToolTipText("Now Playing: "+ firstTrailer.toString());
				while(elapsed < duration){
						Thread.sleep(TimeUnit.SECONDS.toMillis(1));
						bar.setValue((int) elapsed);
						bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
						elapsed++;
						System.out.println("Looped"+ elapsed);
				}
				bar.setValue(0);
				
				for(int i = episodes.size()-1; i >= 0; i--){
					IsoFile isoFile = new IsoFile(episodes.get(i).getFile().getPath());
					duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
					Desktop.getDesktop().open(episodes.get(i).getFile());// Opens the episode in VLC
					// Waits for the current episode to end the loop then
					// iterates and a new episode is picked
					elapsed = 0;
					bar.setMaximum((int) duration);
					FileDisplay.setText(episodes.get(i).toString());
					FileDisplay.setToolTipText("Now Playing: "+ episodes.get(i).toString());
					while(elapsed < duration){
							Thread.sleep(TimeUnit.SECONDS.toMillis(1));
							bar.setValue((int) elapsed);
							bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
							elapsed++;
							System.out.println("Looped"+ elapsed);
					}
					bar.setValue(0);
					
					if(i == 21){
						isoFileT = new IsoFile(root +"\\Nick Heaven\\Airbender Trailers\\Trailer.mp4");
						duration = (long) isoFileT.getMovieBox().getMovieHeaderBox().getDuration() / isoFileT.getMovieBox().getMovieHeaderBox().getTimescale();
						Desktop.getDesktop().open(secondTrailer);
						elapsed = 0;
						bar.setMaximum((int) duration);
						FileDisplay.setText(secondTrailer.toString());
						FileDisplay.setToolTipText("Now Playing: "+ secondTrailer.toString());
						while(elapsed < duration){
								Thread.sleep(TimeUnit.SECONDS.toMillis(1));
								bar.setValue((int) elapsed);
								bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
								elapsed++;
								System.out.println("Looped"+ elapsed);
						}
						bar.setValue(0);
					}
				}
				
			} catch(Exception e) {
				error = e.getMessage();
				FileWriter fw;
				try {
					fw = new FileWriter(Error);
					fw.write(error);
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private class OnButton extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dannyphantom.enable();
			areyouafraidofthedark.enable();
			lastairbender.enable();
			drakeandjosh.enable();
			heyarnold.enable();
			invaderzim.enable();
			jimmyneutron.enable();
			legendofkorra.enable();
			legendsofthehiddentemple.enable();
			ned.enable();
			renandstimpy.enable();
			rocko.enable();
			rugrats.enable();
			spongebob.enable();
			robot.enable();
			fairlyoddparent.enable();
			catdog.enable();
			tmnt.enable();
			doug.enable();
			DannyPhantomBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			KorraBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			SpongeBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			RobotBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			DrakeandJoshBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			AirbenderBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			ArnoldBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			CatDogBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			DarkBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			DougBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			NedBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			OddParentsBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			RSBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			RockoBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			RugratBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			TMNTBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			ZimBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			TempleBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			NeutronBUT.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		}

	}
	
	private class OffButton extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dannyphantom.disable();
			areyouafraidofthedark.disable();
			lastairbender.disable();
			drakeandjosh.disable();
			heyarnold.disable();
			invaderzim.disable();
			jimmyneutron.disable();
			legendofkorra.disable();
			legendsofthehiddentemple.disable();
			ned.disable();
			renandstimpy.disable();
			rocko.disable();
			rugrats.disable();
			spongebob.disable();
			robot.disable();
			fairlyoddparent.disable();
			catdog.disable();
			tmnt.disable();
			doug.disable();
			DannyPhantomBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			KorraBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			SpongeBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			RobotBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			DrakeandJoshBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			AirbenderBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			ArnoldBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			CatDogBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			DarkBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			DougBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			NedBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			OddParentsBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			RSBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			RockoBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			RugratBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			TMNTBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			ZimBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			TempleBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			NeutronBUT.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			
		}

	}
		
	
	public static void main(String[] args) {
		root = JOptionPane.showInputDialog("What drive is the Network Drive mapped to? (I.E. A:)");
		if(root == null || root.length() <= 0) {
			System.exit(0);
		}
		new Driver();
	}

}
