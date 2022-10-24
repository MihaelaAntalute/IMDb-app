import java.util.List;


public class Client extends User {
    List<String> favoriteMoviesList;

    public Client(String firstName, String secondName, List<String> favoriteMoviesList) {
        super(firstName, secondName);
        this.favoriteMoviesList = favoriteMoviesList;
    }

    public List<String> getFavoriteMoviesList() {
        return favoriteMoviesList;
    }

    public void setFavoriteMoviesList(List<String> favoriteMoviesList) {
        this.favoriteMoviesList = favoriteMoviesList;
    }

    @Override
    public String toString() {
        return "Client{" +
                "favoriteMoviesList=" + favoriteMoviesList +
                '}';
    }

    //adauga filmul la lista de favorite
    public void addMovieToFavoriteList(String movieName){
        favoriteMoviesList.add(movieName);
        System.out.println("The movie was added on list");
    }

    //adauga review in lista de review de la un anumit film
    public void addReviewToMovie(Movie movie,Review review){
        movie.getReviewsList().add(review);
        System.out.println("The review was added on movie");
    }

}


