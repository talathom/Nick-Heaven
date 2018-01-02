import java.io.File;

public class Episode{
	private String season, episodeNum;
	private File file;
	
	public Episode(String season, String episodeNum, File file){
		this.season = season;
		this.episodeNum = episodeNum;
		this.file = file;
	}

	public String getSeason() {
		return season;
	}

	public String getEpisodeNum() {
		return episodeNum;
	}

	public File getFile() {
		return file;
	}
	
	/**
	 * Compares two Episodes and returns whether or not an episode comes after the episode passed to this method in the shows sequential ordering.
	 * @param compare Episode to which this episode is being compared
	 * @return True if this Episode comes after the param, False if it comes before.
	 */
	public boolean isGreater(Episode compare){
		boolean isGreater = false;
		if(Integer.parseInt(this.season) > Integer.parseInt(compare.getSeason())){
			isGreater = true;
		} else if(Integer.parseInt(this.season) < Integer.parseInt(compare.getSeason())){
			isGreater = false;
		} else if(Integer.parseInt(this.season) == Integer.parseInt(compare.getSeason())){
			if(Integer.parseInt(this.episodeNum) > Integer.parseInt(compare.getEpisodeNum())){
				isGreater = true;
			} else if(Integer.parseInt(this.episodeNum) < Integer.parseInt(compare.getEpisodeNum())){
				isGreater = false;
			}
		}
		return isGreater;
	}
	
	@Override
	public String toString(){
		return this.file.getName();
	}
}
