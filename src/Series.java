// Version 28 12/24/2018
import java.io.File;
import java.util.Random;

/**
 * This class describes a show on Nick Heaven. It holds an array of all of its episodes, a string holding its name and a boolean regarding whether it is enabled to play.
 * @author Thomas Talasco
 *
 */
public class Series {
	private File[] Episodes;
	private boolean isEnabled = true;
	private String name;
	
	public Series(File[] Episodes, String name) {
		this.Episodes = Episodes;
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
	 * @return True if it can be played, False if not.
	 */
	public boolean isEnabled() {
		return isEnabled;
	}
	
	/**
	 * Returns the Name of the Series
	 * @return The Name of the Series
	 */
	public String getName() {
		return name;
	}

	/**
	 * Selects an episode from the given series
	 * @return A random episode from this series
	 */
	public File getNewEpisode() {
		Random r = new Random();
		int selection = r.nextInt(this.Episodes.length);
		return this.Episodes[selection];
	}
}
