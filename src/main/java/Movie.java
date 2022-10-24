import java.util.List;

public class Movie implements Comparable<Movie> {
    private String title;
    private int releaseYear;
    private Genre genre;
    private Type type;
    private List<String> actorsList;
    private List<Review> reviewsList;

    private double reviewAverage;



    public Movie(String title, int releaseYear, Genre genre, Type type, List<String> actorsList, List<Review> reviewsList) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.type = type;
        this.actorsList = actorsList;
        this.reviewsList = reviewsList;
    }
    public Movie(){

    }

    public double getReviewAverage() {
        return reviewAverage;
    }

    public void setReviewAverage(double reviewAverage) {
        this.reviewAverage = getAverageReviewValueForMovie();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<String> getActorsList() {
        return actorsList;
    }

    public void setActorsList(List<String> actorsList) {
        this.actorsList = actorsList;
    }

    public List<Review> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Review> reviewsList) {
        this.reviewsList = reviewsList;
    }
    public double getAverageReviewValueForMovie() {
        double sum = 0;
        for (Review review : reviewsList) {
            sum += review.reviewValue;
        }
        reviewAverage = sum / reviewsList.size();
        return sum / reviewsList.size();
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                ", type=" + type +
                ", actorsList=" + actorsList +
                ", reviewsList=" + reviewsList +
                ", reviewAverage=" + reviewAverage +
                '}';
    }

    @Override
    public int compareTo(Movie o) {
        return Integer.compare(this.releaseYear, o.releaseYear);

    }
}
