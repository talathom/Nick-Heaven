
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
	final int SIZE = 17;
	/**
	 * Width of the Launcher
	 */
	final int X = 500;
	/**
	 * Height of the Launcher
	 */
	final int Y = 375;
	
	/**
	 * Directory which the Network drive is mapped to
	 */
	static String root;
	
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
	File DougDir = new File("");
	File CatDogDir = new File(root +"\\Nick Heaven\\CatDog");
	File PeteandPeteDir = new File("");
	File TMNTDir = new File(root +"\\Nick Heaven\\TMNT");
	
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

	static Random r = new Random(); // Random Object
	JPanel panel = new JPanel(); // Panel for the GUI
	JButton launchBUT = new JButton("Launch Nick Heaven!"); // Button that Launches Nick Heaven
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
	static JProgressBar bar = new JProgressBar();
	static JLabel FileDisplay = new JLabel();
	Font displayFont = new Font("Calibri", Font.PLAIN, 12);
	Font stopFont = new Font("Calibri", Font.BOLD, 14);

	public Driver() {
		setTitle("Nick Heaven Control Panel");
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(X, Y);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Closes the Program on X button press
		panel.setLayout(null);
		launchBUT.setToolTipText("Launch Nick Heaven");
		launchBUT.setBounds((int) (X*.35), 10, 143, 75);
		launchBUT.setIcon(NickHeavenIcon);
		launchBUT.addActionListener(new launchBUTListener()); // Adds the Action Listener to the button
		
		//Marathon Buttons
		DannyPhantomBUT.setToolTipText("Starts a Danny Phantom Marathon");
		DannyPhantomBUT.setBounds(15, 95, 50, 50);
		DannyPhantomBUT.addActionListener(new DannyPhantomMarathon());
		DannyPhantomBUT.setIcon(DannyPhantomIcon);
		KorraBUT.setToolTipText("Starts a Legend of Korra Marathon");
		KorraBUT.setBounds(75, 95, 50, 50);
		KorraBUT.addActionListener(new LegendofKorraMarathon());
		KorraBUT.setIcon(KorraIcon);
		RobotBUT.setToolTipText("Starts a My Life as a Teenage Robot Marathon");
		RobotBUT.setBounds(135, 95, 50, 50);
		RobotBUT.addActionListener(new RobotMarathon());
		RobotBUT.setIcon(RobotIcon);
		SpongeBUT.setToolTipText("Starts a Spongebob Marathon");
		SpongeBUT.setBounds(195, 95, 50, 50);
		SpongeBUT.addActionListener(new SpongebobMarathon());
		SpongeBUT.setIcon(SpongebobIcon);
		DrakeandJoshBUT.setToolTipText("Starts a Drake and Josh Marathon");
		DrakeandJoshBUT.setBounds(255, 95, 50, 50);
		DrakeandJoshBUT.addActionListener(new DrakeandJoshMarathon());
		DrakeandJoshBUT.setIcon(DrakeandJoshIcon);
		
		//Holiday Buttons
		HalloweenBUT.setToolTipText("Starts a Halloween Marathon");
		HalloweenBUT.setBounds(15, 155, 50, 50);
		HalloweenBUT.addActionListener(new Halloween());
		HalloweenBUT.setIcon(HalloweenIcon);
		AprilBUT.setToolTipText("Starts the April Fools Day Event");
		AprilBUT.setBounds(75, 155, 50, 50);
		AprilBUT.addActionListener(new AprilFools());
		AprilBUT.setIcon(AprilIcon);
		BlackSunBUT.setToolTipText("Starts an Avatar Marathon");
		BlackSunBUT.setIcon(BlackSunIcon);
		BlackSunBUT.setBounds(135, 155, 50, 50);
		BlackSunBUT.addActionListener(new BlackSun());
		ChristmasBUT.setToolTipText("NYI - Starts a Christmas Special Marathon - NYI");
		ChristmasBUT.setBounds(195, 155, 50, 50);
		ChristmasBUT.addActionListener(new Christmas());
		ChristmasBUT.setIcon(ChristmasIcon);
		ChristmasBUT.setEnabled(false);
		
		//Special Button
		stopBUT.setToolTipText("Reset the Control Panel");
		stopBUT.setBounds((int) (X*.4), 215, 100, 50);
		stopBUT.addActionListener(new restartListener());
		stopBUT.setFont(stopFont);
		stopBUT.setBackground(Color.RED);
		
		//ProgressBar
		bar.setBounds(15, 275, X-40, 25);
		
		//Episode Information
		FileDisplay.setBounds(15, 300, X-40, 25);
		FileDisplay.setFont(displayFont);
		
		//Panel Setup
		panel.add(launchBUT);
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
		panel.add(bar);
		panel.add(FileDisplay);
		panel.setBackground(Color.white);
		add(panel);
		setVisible(true);
		/* Adds the panel and makes the GUI visible */
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
			IsoFile isoFile = new IsoFile(series[episode].getPath());
			duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
			Desktop.getDesktop().open(series[episode]); // Opens the episode
			long elapsed = 0;
			bar.setMaximum((int) duration);
			FileDisplay.setText("Now Playing: "+ series[episode].toString());
			while(elapsed < duration){
					Thread.sleep(TimeUnit.SECONDS.toMillis(1));
					bar.setValue((int) elapsed);
					bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
					elapsed++;
			}
			bar.setValue(0);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	
	private class DrakeandJoshMarathon extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			int choice = JOptionPane.showConfirmDialog(null, "Run Sequential Marathon?");
			if (choice == JOptionPane.YES_OPTION) {
				ArrayList<Episode> episodes = sortEpisodes(DrakeandJosh);
				for (int i = episodes.size() - 1; i >= 0; i--) {
					try {
						IsoFile isoFile = new IsoFile(episodes.get(i).getFile().getPath());
						duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
						Desktop.getDesktop().open(episodes.get(i).getFile());// Opens the episode in VLC
						// Waits for the current episode to end the loop then
						// iterates and a new episode is picked
						long elapsed = 0;
						bar.setMaximum((int) duration);
						FileDisplay.setText("Now Playing: "+ episodes.get(i).toString());
						while(elapsed < duration){
								Thread.sleep(TimeUnit.SECONDS.toMillis(1));
								bar.setValue((int) elapsed);
								bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
								elapsed++;
								System.out.println("Looped"+ elapsed);
						}
						bar.setValue(0);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else if (choice == JOptionPane.NO_OPTION) {
				while (!Thread.currentThread().isInterrupted()) {
					duration = playEpisode(DrakeandJosh);
					// Waits for the current episode to end the loop then
					// iterates and a new episode is picked
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Marathon Choice Invalid");
			}
		}

	}

	private class DannyPhantomMarathon extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			int choice = JOptionPane.showConfirmDialog(null, "Run Sequential Marathon?");
			if (choice == JOptionPane.YES_OPTION) {
				ArrayList<Episode> episodes = sortEpisodes(DannyPhantom);
				for (int i = episodes.size() - 1; i >= 0; i--) {
					try {
						IsoFile isoFile = new IsoFile(episodes.get(i).getFile().getPath());
						duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
						Desktop.getDesktop().open(episodes.get(i).getFile());// Opens the episode in VLC
						// Waits for the current episode to end the loop then
						// iterates and a new episode is picked
						long elapsed = 0;
						bar.setMaximum((int) duration);
						FileDisplay.setText("Now Playing: "+ episodes.get(i).toString());
						while(elapsed < duration){
								Thread.sleep(TimeUnit.SECONDS.toMillis(1));
								bar.setValue((int) elapsed);
								bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
								elapsed++;
								System.out.println("Looped"+ elapsed);
						}
						bar.setValue(0);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else if (choice == JOptionPane.NO_OPTION) {
				while (!Thread.currentThread().isInterrupted()) {
					duration = playEpisode(DannyPhantom);
					// Waits for the current episode to end the loop then
					// iterates and a new episode is picked
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Marathon Choice Invalid");
			}
		}

	}
	
	private class LegendofKorraMarathon extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			int choice = JOptionPane.showConfirmDialog(null, "Run Sequential Marathon?");
			if (choice == JOptionPane.YES_OPTION) {
				ArrayList<Episode> episodes = sortEpisodes(LegendofKorra);
				for (int i = episodes.size() - 1; i >= 0; i--) {
					try {
						IsoFile isoFile = new IsoFile(episodes.get(i).getFile().getPath());
						duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
						Desktop.getDesktop().open(episodes.get(i).getFile());// Opens the episode in VLC
						// Waits for the current episode to end the loop then
						// iterates and a new episode is picked
						long elapsed = 0;
						bar.setMaximum((int) duration);
						FileDisplay.setText("Now Playing: "+ episodes.get(i).toString());
						while(elapsed < duration){
								Thread.sleep(TimeUnit.SECONDS.toMillis(1));
								bar.setValue((int) elapsed);
								bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
								elapsed++;
								System.out.println("Looped"+ elapsed);
						}
						bar.setValue(0);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else if (choice == JOptionPane.NO_OPTION) {
				while (!Thread.currentThread().isInterrupted()) {
					duration = playEpisode(LegendofKorra);
					// Waits for the current episode to end the loop then
					// iterates and a new episode is picked
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Marathon Choice Invalid");
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
		FileDisplay.setText("Now Playing: "+ AprilFoolsSpecial.toString());
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
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		int series = -1;
		DateTimeFormatter day = DateTimeFormatter.ofPattern("EEE");
		DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH");
		LocalDateTime now;
		while (!Thread.currentThread().isInterrupted()) {
			now = LocalDateTime.now();
			System.out.println(day.format(now));
			System.out.println(hour.format(now));
			if(day.format(now).equals("Sun") && (Integer.parseInt(hour.format(now)) >= 16 || Integer.parseInt(hour.format(now)) < 6)){
			series = r.nextInt(SIZE); // Picks a series and calls the playEpisode method by passing an array of episodes
			} else if(!day.format(now).equals("Sun") && (Integer.parseInt(hour.format(now)) >= 20 || Integer.parseInt(hour.format(now)) < 6)){
			series = r.nextInt(SIZE-1);
			} else if(!day.format(now).equals("Sun") && !(Integer.parseInt(hour.format(now)) >= 20 || Integer.parseInt(hour.format(now)) < 6)){
			series = r.nextInt(SIZE-3)+1;
			} else if(day.format(now).equals("Sun") && !(Integer.parseInt(hour.format(now)) >= 20 || Integer.parseInt(hour.format(now)) < 6)){
			series = r.nextInt(SIZE-2)+1;	
			}
			try {
				if (series == 0) {
					duration = playEpisode(AreYouAfraidoftheDark);

				} else if (series == 1) {
					series = r.nextInt(2);
					if(series == 0){
					duration = playEpisode(LastAirbender);
					} else {
						duration = playEpisode(LegendofKorra);
					}
					
				} else if (series == 2) {
					duration = playEpisode(DannyPhantom);

				} else if (series == 3) {
					duration = playEpisode(DrakeandJosh);

				} else if (series == 4) {
					duration = playEpisode(HeyArnold);

				} else if (series == 5) {
					duration = playEpisode(InvaderZim);

				} else if (series == 6) {
					duration = playEpisode(JimmyNeutron);

				} else if (series == 7) {
					duration = playEpisode(Robot);

				} else if (series == 8) {
					duration = playEpisode(Ned);

				} else if (series == 9) {
					duration = playEpisode(RenandStimpy);

				} else if (series == 10) {
					duration = playEpisode(Rocko);

				} else if (series == 11) {
					duration = playEpisode(Rugrats);

				} else if (series == 12) {
					for(int k = 0; k < 2; k++){
					duration = playEpisode(Spongebob);
					}
				} else if (series == 13) {
					duration = playEpisode(FairlyOddParents);
					
				} else if (series == 14){
					duration = playEpisode(CatDog);
			
				} else if(series == 15) {
					duration = playEpisode(TMNT);
				
				} else if(series == 16) {
					duration = playEpisode(LegendsofTheHiddenTemple);
					
				} else {
					duration = 0;
				}

			} catch (RuntimeException e) {
				e.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
			}
	}
	
	private class RobotMarathon extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			int choice = JOptionPane.showConfirmDialog(null, "Run Sequential Marathon?");
			if (choice == JOptionPane.YES_OPTION) {
				ArrayList<Episode> episodes = sortEpisodes(Robot);
				for (int i = episodes.size() - 1; i >= 0; i--) {
					try {
						IsoFile isoFile = new IsoFile(episodes.get(i).getFile().getPath());
						duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
						Desktop.getDesktop().open(episodes.get(i).getFile());// Opens the episode in VLC
						// Waits for the current episode to end the loop then
						// iterates and a new episode is picked
						long elapsed = 0;
						bar.setMaximum((int) duration);
						FileDisplay.setText("Now Playing: "+ episodes.get(i).toString());
						while(elapsed < duration){
								Thread.sleep(TimeUnit.SECONDS.toMillis(1));
								bar.setValue((int) elapsed);
								bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
								elapsed++;
								System.out.println("Looped"+ elapsed);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else if (choice == JOptionPane.NO_OPTION) {
				while (!Thread.currentThread().isInterrupted()) {
					duration = playEpisode(Robot);
					// Waits for the current episode to end the loop then
					// iterates and a new episode is picked
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Marathon Choice Invalid");
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
				FileDisplay.setText("Now Playing: "+ firstTrailer.toString());
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
					FileDisplay.setText("Now Playing: "+ episodes.get(i).toString());
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
						FileDisplay.setText("Now Playing: "+ secondTrailer.toString());
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
				e.printStackTrace();
			}
		}
	}
	
	private class SpongebobMarathon extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			int choice = JOptionPane.showConfirmDialog(null, "Run Sequential Marathon?");
			if (choice == JOptionPane.YES_OPTION) {
				ArrayList<Episode> episodes = sortEpisodes(Spongebob);
				for (int i = episodes.size() - 1; i >= 0; i--) {
					try {
						IsoFile isoFile = new IsoFile(episodes.get(i).getFile().getPath());
						duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
						Desktop.getDesktop().open(episodes.get(i).getFile());// Opens the episode in VLC
						// Waits for the current episode to end the loop then
						// iterates and a new episode is picked
						long elapsed = 0;
						bar.setMaximum((int) duration);
						FileDisplay.setText("Now Playing: "+ episodes.get(i).toString());
						while(elapsed < duration){
								Thread.sleep(TimeUnit.SECONDS.toMillis(1));
								bar.setValue((int) elapsed);
								bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) +" Minutes Elasped out of "+ TimeUnit.SECONDS.toMinutes(duration));
								elapsed++;
								System.out.println("Looped"+ elapsed);
						}
						bar.setValue(0);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e.getMessage());
					}

				}
			} else if (choice == JOptionPane.NO_OPTION) {
				while (!Thread.currentThread().isInterrupted()) {
					duration = playEpisode(Spongebob);
					// Waits for the current episode to end the loop then
					// iterates and a new episode is picked
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Marathon Choice Invalid");
			}
		}

	}
		
	
	public static void main(String[] args) {
		root = JOptionPane.showInputDialog("What drive is the Network Drive mapped to? (I.E. A:)");
		new Driver();
	}

}
