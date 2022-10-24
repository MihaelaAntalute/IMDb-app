public class UserService extends User {

    public UserService(String firstName, String secondName) {
        super(firstName, secondName);
    }

    //Adaugarea unui review la un film
    public void addReviewToAMovie(Movie movie, Review review) {
        movie.getReviewsList().add(review);
    }

    //Adaugarea unui film la o lista de favorite


}
