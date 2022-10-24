
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImdbTest {

    private IMDBService imdbService;
    private Admin admin;

    @BeforeEach
    public void setUp() {
        imdbService = new IMDBService();
        List<String> favoriteMovies = new ArrayList<>();
        favoriteMovies.add("Fast and furious 7");
        favoriteMovies.add("Fast and furious 9");
        favoriteMovies.add("Rebelde");
        favoriteMovies.add("Red notice");
        List<String> favoriteMovies2 = new ArrayList<>();
        favoriteMovies2.add("Red notice");
        favoriteMovies2.add("The Adam project");
        Client client = new Client("Andrei", "Aaaa", favoriteMovies);
        Client client2 = new Client("Alin", "Bbbba", favoriteMovies2);
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        clientList.add(client2);
        admin = new Admin("John", "Garry", clientList);
        List<String> actorsList1 = new ArrayList<>();
        actorsList1.add("Paul Walker");
        actorsList1.add("Vin Diesel");
        actorsList1.add("Jordana Brewster ");
        actorsList1.add("Michelle Rodriguez");
        actorsList1.add("Dwayne Johnson");
        List<Review> reviewList1 = new ArrayList<>();
        reviewList1.add(Review.FIVE_STARS);
        reviewList1.add(Review.FIVE_STARS);
        reviewList1.add(Review.FIVE_STARS);
        Movie movie1 = new Movie("Fast and furious 7", 2015, Genre.ACTION, Type.MOVIE, actorsList1, reviewList1);
        List<String> actorsList2 = new ArrayList<>();
        actorsList2.add("Kate Winslet");
        actorsList2.add("Leonardo DiCaprio");
        actorsList2.add("Bill Zane");
        actorsList2.add("Victor Garbert");
        List<Review> reviewList2 = new ArrayList<>();
        reviewList2.add(Review.FOUR_STARS);
        reviewList2.add(Review.FIVE_STARS);
        reviewList2.add(Review.FOUR_STARS);
        Movie movie2 = new Movie("Titanic", 1997, Genre.DRAMA, Type.MOVIE, actorsList2, reviewList2);
        List<String> actorsList3 = new ArrayList<>();
        actorsList3.add("Adam Sandler");
        actorsList3.add("Cole Sprouse");
        actorsList3.add("Dylan Sprouse");
        actorsList3.add("Leslie Mann ");
        List<Review> reviewList3 = new ArrayList<>();
        reviewList3.add(Review.FIVE_STARS);
        reviewList3.add(Review.FOUR_STARS);
        reviewList3.add(Review.THREE_STARS);
        Movie movie3 = new Movie("Un tatic grozav", 1999, Genre.COMEDY, Type.MOVIE, actorsList3, reviewList3);
        List<String> actorsList4 = new ArrayList<>();
        actorsList4.add("Vin Diesel");
        actorsList4.add("Michelle Rodriguez");
        actorsList4.add("Jordana Brewster");
        actorsList4.add("John Cena");
        List<Review> reviewList4 = new ArrayList<>();
        reviewList4.add(Review.FIVE_STARS);
        reviewList4.add(Review.FIVE_STARS);
        reviewList4.add(Review.FOUR_STARS);
        Movie movie4 = new Movie("Fast and furious 9", 2021, Genre.ACTION, Type.MOVIE, actorsList4, reviewList4);

        List<String> actorsList5 = new ArrayList<>();
        actorsList5.add("Gal Gadot");
        actorsList5.add("Dwayne Johnson");
        actorsList5.add("Ryan Reynolds");
        actorsList5.add("Ritu Aria");
        List<Review> reviewList5 = new ArrayList<>();
        reviewList5.add(Review.FIVE_STARS);
        reviewList5.add(Review.FOUR_STARS);
        reviewList5.add(Review.FIVE_STARS);
        Movie movie5 = new Movie("Red notice", 2021, Genre.COMEDY, Type.MOVIE, actorsList5, reviewList5);
        List<String> actorsList6 = new ArrayList<>();
        actorsList6.add("Ryan Reynolds");
        actorsList6.add("Walker Scobell");
        actorsList6.add("Zoe Saldana");
        actorsList6.add("Jennifer Gardner");
        List<Review> reviewList6 = new ArrayList<>();
        reviewList6.add(Review.FOUR_STARS);
        reviewList6.add(Review.THREE_STARS);
        reviewList6.add(Review.FOUR_STARS);
        Movie movie6 = new Movie("The Adam project", 2022, Genre.ACTION, Type.MOVIE, actorsList6, reviewList6);
        List<String> actorsList7 = new ArrayList<>();
        actorsList7.add("Genevieve Gorder");
        actorsList7.add("Peter Lorimer");
        List<Review> reviewList7 = new ArrayList<>();
        reviewList7.add(Review.THREE_STARS);
        reviewList7.add(Review.FOUR_STARS);
        Movie movie7 = new Movie("Stay here(Renovare cu potential)", 2018, Genre.ACTION, Type.TV_SHOW, actorsList7, reviewList7);
        List<String> actorsList8 = new ArrayList<>();
        actorsList8.add("Sofia Carson");
        actorsList8.add("Nicholas Galitzine");
        actorsList8.add("Chosen Jacobs");
        actorsList8.add("Linden Ashby");
        List<Review> reviewList8 = new ArrayList<>();
        reviewList8.add(Review.FIVE_STARS);
        reviewList8.add(Review.FIVE_STARS);
        reviewList8.add(Review.FIVE_STARS);
        Movie movie8 = new Movie("Purple hearts", 2022, Genre.ROMANTIC, Type.MOVIE, actorsList8, reviewList8);
        List<Movie> moviesList = new ArrayList<>();
        moviesList.add(movie1);
        moviesList.add(movie2);
        moviesList.add(movie3);
        moviesList.add(movie4);
        moviesList.add(movie5);
        moviesList.add(movie6);
        moviesList.add(movie7);
        moviesList.add(movie8);
        System.out.println(moviesList);
        imdbService.setMovieList(moviesList);
    }

    @Test
    void testGetMovieByActor_ShouldReturnMovies() {
        List<Movie> result = imdbService.getAllMoviesByActor("Vin Diesel");
        Movie movie1 = imdbService.findMovieByName("Fast and furious 7");
        Movie movie2 = imdbService.findMovieByName("Fast and furious 9");
        List<Movie> expected = Arrays.asList(movie1, movie2);
        assertEquals(expected, result);
    }

    @Test
    void testGetActorWhoActInMoreMovies_ShouldReturnActor() {
        String actorName = imdbService.getTheActorWhoActInMoreMovies();
        String actorNameExpected = "Ryan Reynolds";
        assertEquals(actorName, actorNameExpected);
    }

    @Test
    void testGetAllTitlesMoviesByActor_ShouldReturnMoviesTitleByActor() {
        List<String> result = imdbService.getAllTitlesMoviesByActor("Vin Diesel");
        String movieName1 = "Fast and furious 7";
        String movieName2 = "Fast and furious 9";
        List<String> expected = Arrays.asList(movieName1, movieName2);
        assertEquals(expected, result);
    }

    @Test
    void testGetOrderedOfMoviesByReleaseYear_ShouldReturnOrderByReleaseYear() {
        Set<Integer> result = imdbService.getOrderMoviesByReleaseYear();
        List<Integer> aList = Arrays.asList(2015, 1997, 2018, 1999, 2021, 2021, 2022, 2022);
        Set<Integer> expected = new TreeSet<Integer>(aList);
        assertEquals(expected, result);
    }

    @Test
    void testGetTheMostPopularTitleMovies_ShouldReturnOneTitleMovie() {
        String result = imdbService.getTheMostPopularTitleMovies(admin);
        String expected = "Red notice";
        assertEquals(expected, result);
    }

    @Test
    void testFindTitleMovieByName_ShouldReturnTheMovieName() {
        String result = imdbService.findTitleMovieByName("Purple hearts");
        String expected = "Purple hearts";
        assertEquals(expected, result);
    }

}