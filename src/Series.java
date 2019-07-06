// Version 29 7/5/2019
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This class describes a show on Nick Heaven. It holds an array of all of its
 * episodes, a string holding its name and a boolean regarding whether it is
 * enabled to play.
 *
 * @author Thomas Talasco
 *
 */
public class Series implements Comparable {

	private File[] episodes;
	private boolean isEnabled = true;
	private String name;
	private ImageIcon image;

	/**
	 * Construct a Series object using an array of Episode files and the name of the
	 * show
	 *
	 * @param Episodes
	 * @param name
	 */
	public Series(String name) {
		this.name = name;
	}

	/**
	 * Prevents the series from being played
	 */
	public void disable() {
		isEnabled = false;
	}

	/**
	 * Allows the series to be played
	 */
	public void enable() {
		isEnabled = true;
	}

	/**
	 * Returns the status of whether the series can be played
	 *
	 * @return True if it can be played, False if not.
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * Returns the Name of the Series
	 *
	 * @return The Name of the Series
	 */
	public String getName() {
		return name;
	}

	public ImageIcon getImage() {
		return image;
	}

	/**
	 * Selects an episode from the given series
	 *
	 * @return A random episode from this series
	 */
	public File getNewEpisode() {
		Random r = new Random();
		int selection = r.nextInt(this.episodes.length);
		return this.episodes[selection];
	}

	/**
	 * Override the episodes array;
	 * 
	 * @param episodes
	 */
	public void setEpisodes(File[] episodes) {
		this.episodes = episodes;
	}

	public File[] getEpisodes() {
		return this.episodes;
	}

	public int getNumEpisodes() {
		if(this.episodes == null) {
			System.out.println(this.name);
			return -1;
		}
		return this.episodes.length;
	}

	/**
	 * Loads all files relevant to a show from the database
	 */
	public void loadFromDatabase(String root) {
		Connection con;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://P:/Java Projects/Nick Heaven/data/showDB.accdb";
			con = DriverManager.getConnection(url, "", "");
			Statement stmt = con.createStatement();
			String query = "select Logo, EpisodeDir from shows where Name = '" + this.name + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String path = root + rs.getString("Logo");
				File img = new File(path);
				this.image = new ImageIcon(path);
				path = root + rs.getString("EpisodeDir");
				File folder = new File(path);
				this.episodes = folder.listFiles();
			}
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, this.name +" had a an oops");
			Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public File getRandomEpisode() {
		Random r = new Random();
		int choice = r.nextInt(episodes.length) - 1;
		return episodes[choice];
	}

	@Override
	public int compareTo(Object obj) {
		Series compare = (Series) obj;
		return this.name.compareTo(compare.getName());
	}
}
