// Version 29 7/5/2019
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

//Referenced Library
import com.coremedia.iso.IsoFile;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.ImageIcon;

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
	File showsDir = new File(root + "\\Nick Heaven\\Shows"); // Directory where show directories are stored
	File[] directories = showsDir.listFiles(); // Array of Show Folders

	File iconDir = new File(root + "\\Logos");
	File[] buttonIcons = iconDir.listFiles(); // Array of Files to be passed to ImageIcon Constructor

	static Random r = new Random(); // Random Object
	JPanel panel = new JPanel(); // Panel for the GUI
	JButton launchBUT = new JButton("Launch Nick Heaven!"); // Button that Launches Nick Heaven
	JButton OnBUT = new JButton("ON");
	JButton OffBUT = new JButton("OFF");
	JButton stopBUT = new JButton("RESET");
	JButton[] controlButtons;
	JButton[] holidayButtons = new JButton[5];

	static JProgressBar bar = new JProgressBar();
	static JLabel FileDisplay = new JLabel();
	static JLabel UpNextDisplay = new JLabel();
	Font displayFont = new Font("Calibri", Font.BOLD, 12);
	Font stopFont = new Font("Calibri", Font.BOLD, 14);
	Font statusFont = new Font("Calibri", Font.BOLD, 9);

	static ArrayList<Series> showsList = new ArrayList<Series>();

	// CLASS CONSTRUCTOR
	public Driver() {

		//Initial Show Declarations
		Series[] shows = { new Series("Avatar: The Last Airbender"), new Series("Legend of Korra"), new Series("Danny Phantom"),
				new Series("Are You Afraid of the Dark"), new Series("Drake and Josh"), new Series("Hey Arnold"),
				new Series("Invader Zim"), new Series("Jimmy Neutron"), new Series("Legends of the Hidden Temple"),
				new Series("Neds Declassified School Survival Guide"), new Series("Ren and Stimpy"), new Series("Rockos Modern Life"),
				new Series("Rugrats"), new Series("Spongebob"), new Series("My Life as a Teenage Robot"),
				new Series("The Fairly Oddparents"), new Series("CatDog"), new Series("TMNT"), new Series("Doug") };

		// Load all show info in
		for (int i = 0; i < shows.length; i++) {
			shows[i].loadFromDatabase(root);

		}
		// Fix the Avatar Edge Case
		File[] AvatarEpisodes = combineArrays(shows[0], shows[1]);
		Series avatar = new Series("Avatar");
		avatar.setEpisodes(AvatarEpisodes);
		
		for (int i = 0; i < shows.length; i++) {
			showsList.add(shows[i]);
		}

		Collections.sort(showsList); // Sort the list for quick lookup later

		setTitle("Nick Heaven Control Panel");
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(X, Y);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Closes the Program on X button press
		panel.setLayout(null);
		launchBUT.setToolTipText("Launch Nick Heaven");
		launchBUT.setBounds((int) (X * .3), 10, 143, 75);
		File pic = new File(root + "\\Logos\\nickheaven.png"); // MAY CRASH
		launchBUT.setIcon(new ImageIcon(pic.getPath()));
		launchBUT.addActionListener(new launchBUTListener()); // Adds the Action Listener to the button

		// Episode Flagger Buttons
		// Row 0
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
		
		controlButtons = new JButton[showsList.size()];

		// Place all butons on the GUI
		int y = 95, x = 15, i = 0;
		while (y < 335) {
			x = 15;
			while (x < 320 && i < controlButtons.length) {
				controlButtons[i] = new JButton();
				controlButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
				controlButtons[i].setToolTipText("Enable " + showsList.get(i).getName());
				controlButtons[i].setBounds(x, y, 50, 50);
				controlButtons[i].addActionListener(new showButtonListener());
				controlButtons[i].setIcon(showsList.get(i).getImage());
				controlButtons[i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				i++;
				x += 60;
			}
			y += 60;
			if (i >= controlButtons.length) {
				break;
			}
		}
		
		//Add combined Avatar Series now that buttons are placed
		for(int j = 0; j < showsList.size(); j++) {
			if(avatar.compareTo(showsList.get(j)) < 0) {
				showsList.add(j, avatar);
				break;
			}
		}
		
		Series[] holidayShows = { new Series("April Fools"), new Series("Halloween"), new Series("Christmas") };
		for (Series show : holidayShows) {
			show.loadFromDatabase(root);
		}

		JButton BlackSunBUT = new JButton();
		
		for(int j = 0; j < holidayButtons.length; j++) {
			holidayButtons[j] = new JButton("Button "+ j);
		}

		i = 0;
		x = 15;
		y = 335;
		while (x < 320 && i < holidayButtons.length) {
			holidayButtons[i].setBounds(x, y, 50, 50);
			x += 60;
		}
		// Holiday Buttons
		// holidayButtons[0].addActionListener(new AprilFools());
		holidayButtons[0].setToolTipText("Starts the April Fools Day Event");
		holidayButtons[0].setIcon(holidayShows[0].getImage());
		holidayButtons[1].setToolTipText("Starts a Halloween Marathon");
		// holidayButtons[1].addActionListener(new Halloween());
		holidayButtons[1].setIcon(holidayShows[1].getImage());
		holidayButtons[2].setToolTipText("NYI - Starts a Christmas Special Marathon - NYI");
		// holidayButtons[2].addActionListener(new Christmas());
		holidayButtons[2].setIcon(holidayShows[2].getImage());
		holidayButtons[2].setEnabled(false);

		

		// Special Button
		stopBUT.setToolTipText("Reset the Control Panel");
		stopBUT.setBounds((int) (X * .35), 450, 100, 50);
		stopBUT.addActionListener(new restartListener());
		stopBUT.setFont(stopFont);
		stopBUT.setForeground(NickOrange);
		stopBUT.setBackground(Color.WHITE);

		// ProgressBar
		bar.setBounds(15, 510, X - 40, 25);

		// Episode Information
		FileDisplay.setBounds(15, 400, X - 40, 25);
		FileDisplay.setFont(displayFont);
		UpNextDisplay.setBounds(15, 425, X - 40, 25);
		UpNextDisplay.setFont(displayFont);

		// Panel Setup
		panel.add(launchBUT);
		panel.add(OnBUT);
		panel.add(OffBUT);
		panel.add(stopBUT);
		panel.add(bar);
		panel.add(FileDisplay);
		panel.add(UpNextDisplay);
		for (JButton button : controlButtons) {
			panel.add(button);
		}
		panel.setBackground(NickOrange);
		add(panel);
		setVisible(true);
		/* Adds the panel and makes the GUI visible */
	}

	/**
	 * Combines the File arrays of two series and returns one array
	 * @param a First Series
	 * @param b Second Series
	 * @return An array of both Series Episode File Arrays Combined
	 */
	public File[] combineArrays(Series a, Series b) {
		File[] episodes = new File[a.getNumEpisodes() + b.getNumEpisodes()];
		File[] aep = a.getEpisodes();
		File[] bep = b.getEpisodes();
		int counter = 0;
		for (File ep : aep) {
			episodes[counter] = ep;
			counter++;
		}
		for (File ep : bep) {
			episodes[counter] = ep;
			counter++;
		}
		return episodes;
	}

	/*
	 * ActionListener for the Launch Button which implements ActionListener and
	 * extends Thread by extending Thread the buttons code is multithreaded and runs
	 * on a separate thread from the rest of the GUI. This allows interactions to
	 * occur with the GUI while the rest of the program is waiting for the current
	 * episode to end.
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
	 *
	 * @param series Array of Episodes
	 * @return Duration of the Episode chosen
	 */
	public static long playEpisode(Series series) {
		long duration = 0;

		try {
			File episode = series.getRandomEpisode();
			IsoFile isoFile = new IsoFile(episode.getPath());
			duration = isoFile.getMovieBox().getMovieHeaderBox().getDuration()
					/ isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
			System.out.println(duration);
			Desktop.getDesktop().open(episode); // Opens the episode
			long elapsed = 0;
			bar.setMaximum((int) duration);
			FileDisplay.setText(episode.toString());
			FileDisplay.setToolTipText("Now Playing: " + episode.toString());
			while (elapsed < duration) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(1));
				bar.setValue((int) elapsed);
				bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) + " Minutes Elasped out of "
						+ TimeUnit.SECONDS.toMinutes(duration));
				elapsed++;
			}
			bar.setValue(0);

		} catch (IOException e) {
			writeError(e.getMessage());

		} catch (InterruptedException e) {
			writeError(e.getMessage());
		}

		return duration;
	}

	/**
	 * Writes Errors to a File
	 * @param error Error Message from exception
	 */
	public static void writeError(String error) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(error);
			pw.println(error);
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Sorts the Episodes in order of Lowest Number Season First then Lowest Number
	 * Episode of Each Season.
	 *
	 * @param series Array of Epsiodes belonging to a show
	 * @return Returns a List of the Episodes sorted from S1E1 to SxEy where x is
	 *         the final season and y is the final epsiode of season y
	 */
	public static ArrayList<Episode> sortEpisodes(File[] series) {
		ArrayList<Episode> episodes = new ArrayList<Episode>();
		String season, episodeNum, name;
		for (int i = 0; i < series.length; i++) {
			name = series[i].getName();
			if (name.charAt(0) != 'S') {
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

	/**
	 * Activates and Deactives Shows from playing when a button is pressed
	 * @author Thomas
	 *
	 */
	private class showButtonListener extends Thread implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			int index = searchShows(button.getText(), 0, showsList.size());
			if (button.isEnabled()) {
				button.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				showsList.get(index).disable();
			} else if(!button.isEnabled()){
				button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				showsList.get(index).enable();
			}
		}
	}

	private class Christmas extends Thread implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			while (!this.currentThread().isInterrupted()) {
				// duration = playEpisode(HalloweenSpecials);
			}
		}
	}

	private class AprilFools extends Thread implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			try {
				long duration;
				boolean zero = false, four = false, eight = false, twelve = false, sixteen = false, twenty = false;
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
				LocalDateTime now = LocalDateTime.now();

				IsoFile isoFile;

				while (!this.isInterrupted()) {
					if (Integer.parseInt(dtf.format(now)) >= 0 && !zero) {
						duration = playAprilFoolsEpisode();
						zero = true;

					} else if (Integer.parseInt(dtf.format(now)) >= 4 && !four) {
						duration = playAprilFoolsEpisode();
						four = true;

					} else if (Integer.parseInt(dtf.format(now)) >= 8 && !eight) {
						duration = playAprilFoolsEpisode();
						eight = true;

					} else if (Integer.parseInt(dtf.format(now)) >= 12 && !twelve) {
						duration = playAprilFoolsEpisode();
						twelve = true;

					} else if (Integer.parseInt(dtf.format(now)) >= 16 && !sixteen) {
						duration = playAprilFoolsEpisode();
						sixteen = true;

					} else if (Integer.parseInt(dtf.format(now)) >= 20 && !twenty) {
						duration = playAprilFoolsEpisode();
						twenty = true;

					} else {
						NormalOperation();
					}
				}
			} catch (Exception e) {
				writeError(e.getMessage());
			}
		}

	}

	public static long playAprilFoolsEpisode() {
		long duration = 1000;
		try {
			File AprilFoolsSpecial = new File(
					root + "\\Holiday Episodes\\April Fool's\\Spongebob S1E38 (April Fool's Special).mp4");
			IsoFile isoFile = isoFile = new IsoFile(AprilFoolsSpecial.getPath());
			duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration()
					/ isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
			Desktop.getDesktop().open(AprilFoolsSpecial);
			long elapsed = 0;
			bar.setMaximum((int) duration);
			FileDisplay.setText(AprilFoolsSpecial.toString());
			FileDisplay.setToolTipText("Now Playing: " + AprilFoolsSpecial.toString());
			while (elapsed < duration) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(1));
				bar.setValue((int) elapsed);
				bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) + " Minutes Elasped out of "
						+ TimeUnit.SECONDS.toMinutes(duration));
				elapsed++;
				System.out.println("Looped" + elapsed);
			}
			bar.setValue(0);
		} catch (IOException e) {
			writeError(e.getMessage());
		} catch (InterruptedException e) {
			writeError(e.getMessage());
		}
		return duration;
	}

	private class restartListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Runtime rt = Runtime.getRuntime();
			try {
				Process pr = rt.exec("java -jar \"" + root + "\\Launcher\\Nick Heaven.jar\"");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

	public void NormalOperation() {
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
			int index = searchShows("Are You Afraid of the Dark", 0, showsList.size());
			if (showsList.get(index).isEnabled()
					&& (Integer.parseInt(hour.format(now)) >= 16 || Integer.parseInt(hour.format(now)) < 6)) {
				shows.add(showsList.get(index));
			}
			int airbender = searchShows("Avatar: The Last Airbender", 0, showsList.size());
			int korra = searchShows("Legend of Korra", 0, showsList.size());
			
			System.out.println("");
			for(Series series : showsList) {
				System.out.println(series.getName());
			}
			System.out.println(korra + " "+ airbender + " "+ index);
			if (showsList.get(korra).isEnabled() && showsList.get(airbender).isEnabled()) {
				index = searchShows("Avatar", 0, showsList.size());
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "AVATAR NOT FOUND");
				}
				shows.add(showsList.get(index));
			} else {
				if (showsList.get(korra).isEnabled()) {
					shows.add(showsList.get(korra));
				} else if (showsList.get(airbender).isEnabled()) {
					shows.add(showsList.get(airbender));
				}
			}

			if (day.format(now).equals("Sun")) {
				index = searchShows("Legends of the Hidden Temple", 0, showsList.size());
				if (showsList.get(index).isEnabled()) {
					shows.add(showsList.get(index));
				}
			}

			for (Series show : showsList) {
				if (show.isEnabled() && !show.getName().equals("Avatar")
						&& !show.getName().equals("Are You Afraid of the Dark")
						&& !show.getName().equals("Avatar: The Last Airbender")
						&& !show.getName().equals("Legend of Korra")
						&& !show.getName().equals("Legends of the Hidden Temple")) {
					shows.add(show);
				}
			}

			if (shows.size() == 0) {
				JOptionPane.showMessageDialog(null, "NO SHOWS CHOSEN!");
			}

			if (roll == -1) {
				current = shows.get(r.nextInt(shows.size())).getName();
			}
			roll = r.nextInt(shows.size());
			upNext = shows.get(roll).getName();
			nextShow = nextShow.concat(shows.get(roll).getName());
			UpNextDisplay.setText(nextShow);
			System.out.println(shows.get(roll).getName());

			playEpisode(shows.get(roll));

			current = upNext;
			shows.clear();
		}
	}

	private class BlackSun extends Thread implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			start();
		}

		public void run() {
			long duration;
			int index = searchShows("Avatar", 0, showsList.size());
			ArrayList<Episode> episodes = sortEpisodes(showsList.get(index).getEpisodes());
			try {
				File firstTrailer = new File(root + "\\Airbender Trailers\\Day of Black Sun.mp4");
				File secondTrailer = new File(root + "\\Airbender Trailers\\Trailer.mp4");
				IsoFile isoFileT = new IsoFile(root + "\\Airbender Trailers\\Day of Black Sun.mp4");
				duration = (long) isoFileT.getMovieBox().getMovieHeaderBox().getDuration()
						/ isoFileT.getMovieBox().getMovieHeaderBox().getTimescale();
				Desktop.getDesktop().open(firstTrailer);
				long elapsed = 0;
				bar.setMaximum((int) duration);
				FileDisplay.setText(firstTrailer.toString());
				FileDisplay.setToolTipText("Now Playing: " + firstTrailer.toString());
				while (elapsed < duration) {
					Thread.sleep(TimeUnit.SECONDS.toMillis(1));
					bar.setValue((int) elapsed);
					bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) + " Minutes Elasped out of "
							+ TimeUnit.SECONDS.toMinutes(duration));
					elapsed++;
					System.out.println("Looped" + elapsed);
				}
				bar.setValue(0);

				for (int i = episodes.size() - 1; i >= 0; i--) {
					IsoFile isoFile = new IsoFile(episodes.get(i).getFile().getPath());
					duration = (long) isoFile.getMovieBox().getMovieHeaderBox().getDuration()
							/ isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
					Desktop.getDesktop().open(episodes.get(i).getFile());// Opens the episode in VLC
					// Waits for the current episode to end the loop then
					// iterates and a new episode is picked
					elapsed = 0;
					bar.setMaximum((int) duration);
					FileDisplay.setText(episodes.get(i).toString());
					FileDisplay.setToolTipText("Now Playing: " + episodes.get(i).toString());
					while (elapsed < duration) {
						Thread.sleep(TimeUnit.SECONDS.toMillis(1));
						bar.setValue((int) elapsed);
						bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) + " Minutes Elasped out of "
								+ TimeUnit.SECONDS.toMinutes(duration));
						elapsed++;
						System.out.println("Looped" + elapsed);
					}
					bar.setValue(0);

					if (i == 21) {
						isoFileT = new IsoFile(root + "Airbender Trailers\\Trailer.mp4");
						duration = (long) isoFileT.getMovieBox().getMovieHeaderBox().getDuration()
								/ isoFileT.getMovieBox().getMovieHeaderBox().getTimescale();
						Desktop.getDesktop().open(secondTrailer);
						elapsed = 0;
						bar.setMaximum((int) duration);
						FileDisplay.setText(secondTrailer.toString());
						FileDisplay.setToolTipText("Now Playing: " + secondTrailer.toString());
						while (elapsed < duration) {
							Thread.sleep(TimeUnit.SECONDS.toMillis(1));
							bar.setValue((int) elapsed);
							bar.setToolTipText(TimeUnit.SECONDS.toMinutes(elapsed) + " Minutes Elasped out of "
									+ TimeUnit.SECONDS.toMinutes(duration));
							elapsed++;
							System.out.println("Looped" + elapsed);
						}
						bar.setValue(0);
					}
				}

			} catch (Exception e) {
				writeError(e.getMessage());
			}
		}
	}

	private class OnButton extends Thread implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			for (JButton button : controlButtons) {
				button.enable();
				button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			}
		}

	}

	private class OffButton extends Thread implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			for (JButton button : controlButtons) {
				button.disable();
				button.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			}

		}

	}

	/**
	 * Binary Search for a show in the showsList
	 * @param showName Name of the show being searched
	 * @param l Start index
	 * @param r End index
	 * @return index where the show is located, -1 if not found
	 */
	int searchShows(String showName, int l, int r) {
		if (r >= l) {
			int mid = l + (r - l) / 2;
			if (showsList.get(mid).getName().equals(showName)) {
				return mid;
			}
			if (showsList.get(mid).getName().compareTo(showName) > 0) {
				return searchShows(showName, l, mid - 1);
			}
			return searchShows(showName, mid + 1, r);
		}
		return -1;
	}


	public static void main(String[] args) {
		File settings = new File("Settings.txt");
		if (settings.exists()) { // If file exists read in the root directory from the settings files
			try {
				Scanner sc = new Scanner(settings);
				int lineNum = 0;
				while (sc.hasNextLine()) {
					switch (lineNum) {
					case 0:
						root = sc.nextLine();
						break;
					default:
						System.out.println("ERROR");
						break;
					}
				}
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
			new Driver();
		} else { // Have user type in path to root and save it
			root = JOptionPane.showInputDialog("What drive is the Network Drive mapped to? (I.E. A:)");
			if (root == null || root.length() <= 0) {
				System.exit(0);
			} else {
				try {
					PrintWriter p = new PrintWriter(settings);
					p.println(root);
					p.close();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
				new Driver();
			}
		}
	}

}
