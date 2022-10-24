import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainUserInterface {
    public static void main(String[] args) {
        List<Client> clientsList = new ArrayList<>();
        Admin admin = new Admin("", "", clientsList);
        List<String> favoriteMoviesList = new ArrayList<>();
        Client client = new Client("", "", favoriteMoviesList);
        IMDBService imdbService = new IMDBService();
        Scanner console = new Scanner(System.in);
        int option;
        System.out.println("Insert 1 if you are admin and 2 if you are client");
        int userOption = Integer.parseInt(console.nextLine());

        List<Movie> initialMovies = new ArrayList<>();
        try {
            initialMovies = readMoviesFromFileText();
            imdbService.setMovieList(initialMovies);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            readTheActorsListFromFileText(imdbService);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            readReviewFromText(imdbService);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (userOption == 1) {
            do {
                printMenuAdmin();
                option = Integer.parseInt(console.nextLine());
                doAdministratorAction(option, console, imdbService, admin);
            }
            while (option != 18);
        } else {
            do {
                printMenuClient();
                option = Integer.parseInt(console.nextLine());
                doClientActions(option, console, imdbService, client,admin);
            }
            while (option != 19);
        }
    }
    public static void printMenuAdmin() {
        System.out.println("Menu administrator: ");
        System.out.println("1.Add movie");
        System.out.println("2.All movies by genre");
        System.out.println("3.All movies titles by genre");
        System.out.println("4.All movies by type");
        System.out.println("5.All movies titles by type");
        System.out.println("6.All movies by actor");
        System.out.println("7.All movies titles by actor");
        System.out.println("8.The order of movies according to the year of release");
        System.out.println("9.All actors who act in movies by type and genre");
        System.out.println("10.All actors by type");
        System.out.println("11.All actors by genre");
        System.out.println("12.The most appreciated movie title by genre and type");
        System.out.println("13.The most appreciated movie title by type");
        System.out.println("14.The most appreciated movie title by genre");
        System.out.println("15.The order of movies from the most appreciated");
        System.out.println("16.Found the actor who act in more movies");
        System.out.println("17.The most populars movies titles");
        System.out.println("18.Exit");
    }

    public static void printMenuClient() {
        System.out.println("Menu client:");
        System.out.println("1.Add review to a movie");
        System.out.println("2.Add movie to favorite list");
        System.out.println("3.All movies by genre");
        System.out.println("4.All movie titles by genre");
        System.out.println("5.All movies by type");
        System.out.println("6.All movies titles by type");
        System.out.println("7.All movies by actor");
        System.out.println("8.All movies titles by actor");
        System.out.println("9.The order of movies according to the year of release ");
        System.out.println("10.All actors who act in movies by type and genre");
        System.out.println("11.All actors by type");
        System.out.println("12.All actors by genre");
        System.out.println("13.The most appreciated movie title by genre and type");
        System.out.println("14.The most appreciated movie title by type");
        System.out.println("15.The most appreciated movie title by genre");
        System.out.println("16.The order of movies from the most appreciated");
        System.out.println("17.Found the actor who act in more movies");
        System.out.println("18.The most populars movies titles");
        System.out.println("19.Exit");

    }

    public static void doAdministratorAction(int option, Scanner console, IMDBService imdbService, Admin admin) {
        switch (option) {
            case 1:
                System.out.println("Add movie");
                System.out.println("Insert movie title: ");
                String title = console.nextLine();
                System.out.println("Insert release year: ");
                int releaseYear = Integer.parseInt(console.nextLine());
                System.out.println("Insert genre: ");
                String genre = console.nextLine();
                Genre genre1 = convertFromStringToGenre(genre);
                System.out.println("Insert type: ");
                String type = console.nextLine();
                Type type1 = convertFromStringToType(type);
                System.out.println("Insert actor list: ");
                System.out.println("How many actors have the movie?");
                Integer numberOfActors = Integer.valueOf(console.nextLine());
                List<String> actorList = new ArrayList<>();
                System.out.println("Insert actor: ");
                for (int i = 0; i < numberOfActors; i++) {
                    String actorName = console.nextLine();
                    actorList.add(actorName);
                }
                System.out.println("Insert review list");
                System.out.println("How many reviews have the movie?");
                Integer numberOfReviews = Integer.valueOf(console.nextLine());
                List<Review> reviewList = new ArrayList<>();
                for (int i = 0; i < numberOfReviews; i++) {
                    String reviewString = console.nextLine();
                    Review review = convertFromStringToReview(reviewString);
                    reviewList.add(review);
                }
                Movie movieToBeAdded = new Movie(title, releaseYear, genre1, type1, actorList, reviewList);
                admin.addMovieToMovieList(imdbService.getMovieList(), movieToBeAdded);
                System.out.println();
                break;
            case 2:
                System.out.println("All movies by genre");
                System.out.println(imdbService.getAllMoviesByGenre());
                break;
            case 3:
                System.out.println("All movies titles by genre");
                System.out.println(imdbService.getAllMoviesTitlesByGenre());
                break;
            case 4:
                System.out.println("All movies by type");
                System.out.println(imdbService.getAllMoviesByType());
                break;
            case 5:
                System.out.println("All movies titles by type");
                System.out.println(imdbService.getAllMoviesTitlesByType());
                break;
            case 6:
                System.out.println("All movies by actor");
                System.out.println("Insert actor: ");
                String actor1 = console.nextLine();
                System.out.println(imdbService.getAllMoviesByActor(actor1));
                break;
            case 7:
                System.out.println("All movies titles by actor");
                System.out.println("Insert actor: ");
                String actor2 = console.nextLine();
                System.out.println(imdbService.getAllTitlesMoviesByActor(actor2));
                break;
            case 8:
                System.out.println("The order of movies according to the year of release");
                System.out.println(imdbService.getOrderMoviesByReleaseYear());
                break;
            case 9:
                System.out.println("All actors who act in movies by type and genre");
                System.out.println("Insert type: ");
                String type2 = console.nextLine();
                Type type3 = convertFromStringToType(type2);
                System.out.println("Insert genre: ");
                String genre2 = console.nextLine();
                Genre genre3 = convertFromStringToGenre(genre2);
                System.out.println(imdbService.getAllActorsByTypeAndGenre(type3, genre3));
                break;
            case 10:
                System.out.println("All actors by type");
                System.out.println("Insert type: ");
                String typeString = console.nextLine();
                Type type4 = convertFromStringToType(typeString);
                System.out.println(imdbService.getAllActorsByType(type4));
                break;
            case 11:
                System.out.println("All actors by genre");
                System.out.println("Insert genre: ");
                String genreString = console.nextLine();
                Genre genre4 = convertFromStringToGenre(genreString);
                System.out.println(imdbService.getAllActorsByGenre(genre4));
                break;
            case 12:
                System.out.println("The most appreciated movie title by genre and type");
                System.out.println("Insert genre: ");
                String genre5 = console.nextLine();
                Genre genre6 = convertFromStringToGenre(genre5);
                System.out.println("Insert type: ");
                String type5 = console.nextLine();
                Type type6 = convertFromStringToType(type5);
                System.out.println(imdbService.getTheMostFamousMovieTitleByGenreAndType(genre6,type6));
                break;
            case 13:
                System.out.println("The most appreciated movie title by type");
                System.out.println("Insert type : ");
                String type7 = console.nextLine();
                Type type8 = convertFromStringToType(type7);
                System.out.println(imdbService.getTheMostFamousMovieTitleByType(type8));
                break;
            case 14:
                System.out.println("The most appreciated movie title by genre");
                System.out.println("Insert genre: ");
                String genre7 = console.nextLine();
                Genre genre8 = convertFromStringToGenre(genre7);
                System.out.println(imdbService.getTheMostFamousMovieTitleByGenre(genre8));
                break;
            case 15:
                System.out.println("The order of movies from the most appreciated");
                System.out.println(imdbService.getMoviesOrderedByAverageReview());
                break;
            case 16:
                System.out.println("Found the actor who act in more movies");
                System.out.println(imdbService.getTheActorWhoActInMoreMovies());
                break;
            case 17:
                System.out.println("The most populars movies titles");
                System.out.println(imdbService.getTheMostPopularTitleMovies(admin));
                break;
            case 18:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Insert your option!");
        }
    }

    public static void doClientActions(int option, Scanner console, IMDBService imdbService, Client client,Admin admin) {
        switch (option) {
            case 1:
                System.out.println("Add review to a movie");
                System.out.println("Insert movie: ");
                String movieName = console.nextLine();
                Movie movie = imdbService.findMovieByName( movieName);
                System.out.println("Insert review: ");
                String review = console.nextLine();
                Review review1 = convertFromStringToReview(review);
                client.addReviewToMovie(movie, review1);

                break;
            case 2:
                System.out.println("Add movie to favorite list");
                System.out.println("Insert movie: ");
                String movieName2 = console.nextLine();
                client.addMovieToFavoriteList(movieName2);

                break;
            case 3:
                System.out.println("All movies by genre");
                System.out.println(imdbService.getAllMoviesByGenre());
                break;
            case 4:
                System.out.println("All movie titles by genre");
                System.out.println(imdbService.getAllMoviesTitlesByGenre());
                break;
            case 5:
                System.out.println("All movies by type");
                System.out.println(imdbService.getAllMoviesByType());
                break;
            case 6:
                System.out.println("All movies titles by type");
                System.out.println(imdbService.getAllMoviesTitlesByType2());
                break;
            case 7:
                System.out.println("All movies by actor");
                System.out.println("Insert actor: ");
                String actor = console.nextLine();
                System.out.println(imdbService.getAllMoviesByActor(actor));
                break;
            case 8:
                System.out.println("All movies titles by actor");
                System.out.println("Insert actor: ");
                String actor1 = console.nextLine();
                System.out.println(imdbService.getAllTitlesMoviesByActor(actor1));
                break;
            case 9:
                System.out.println("The order of movies according to the year of release ");
                System.out.println(imdbService.getOrderMoviesByReleaseYear());
                break;
            case 10:
                System.out.println("All actors who act in movies by type and genre");
                System.out.println("Insert type: ");
                String type = console.nextLine();
                Type type1 = convertFromStringToType(type);
                System.out.println("Insert genre: ");
                String genre = console.nextLine();
                Genre genre1 = convertFromStringToGenre(genre);
                System.out.println(imdbService.getAllActorsByTypeAndGenre(type1, genre1));
                break;
            case 11:
                System.out.println("All actors by type");
                System.out.println("Insert type: ");
                String type2 = console.nextLine();
                Type type3 = convertFromStringToType(type2);
                System.out.println(imdbService.getAllActorsByType(type3));
                break;
            case 12:
                System.out.println("All actors by genre");
                System.out.println("Insert genre: ");
                String genre2 = console.nextLine();
                Genre genre3 = convertFromStringToGenre(genre2);
                System.out.println(imdbService.getAllActorsByGenre(genre3));
                break;
            case 13:
                System.out.println("The most appreciated movie title by genre and type");
                System.out.println("Insert genre: ");
                String genre4 = console.nextLine();
                Genre genre5 = convertFromStringToGenre(genre4);
                System.out.println("Insert type: ");
                String type4 = console.nextLine();
                Type type5 = convertFromStringToType(type4);
                System.out.println(imdbService.getTheMostFamousMovieTitleByGenreAndType(genre5, type5));
                break;
            case 14:
                System.out.println("The most appreciated movie title by type");
                System.out.println("Insert type: ");
                String type6 = console.nextLine();
                Type type7 = convertFromStringToType(type6);
                System.out.println(imdbService.getTheMostFamousMovieTitleByType(type7));
                break;
            case 15:
                System.out.println("The most appreciated movie title by genre");
                System.out.println("Insert genre: ");
                String genre6 = console.nextLine();
                Genre genre7 = convertFromStringToGenre(genre6);
                System.out.println(imdbService.getTheMostFamousMovieTitleByGenre(genre7));
                break;
            case 16:
                System.out.println("The order of movies from the most appreciated");
                System.out.println(imdbService.getMoviesOrderedByAverageReview());
                break;
            case 17:
                System.out.println("Found the actor who act in more movies");
                System.out.println(imdbService.getTheActorWhoActInMoreMovies());
                break;
            case 18:
                System.out.println("The most populars movies titles");
                System.out.println(imdbService.getTheMostPopularTitleMovies(admin));
                break;
            case 19:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Insert your option!");
        }

    }

    public static Genre convertFromStringToGenre(String genreString) {
        Genre genreEnum = Genre.ACTION;
        if (genreString.equals("comedy")) {
            genreEnum = Genre.COMEDY;
        }
        if (genreString == ("action")) {
            genreEnum = Genre.ACTION;
        }
        if (genreString.equals("drama")) {
            genreEnum = Genre.DRAMA;
        }
        if (genreString.equals("romantic")) {
            genreEnum = Genre.ROMANTIC;
        }
        if (genreString.equals("adventure")) {
            genreEnum = Genre.ADVENTURE;
        }
        if (genreString.equals("horror")) {
            genreEnum = Genre.HORROR;
        }
        if (genreString.equals("telenovela")) {
            genreEnum = Genre.TELENOVELA;
        }
        return genreEnum;
    }

    public static Type convertFromStringToType(String typeString) {
        Type typeEnum = Type.MOVIE;
        if (typeString.equals("movie")) {
            typeEnum = Type.MOVIE;
        }
        if (typeString.equals("serial")) {
            typeEnum = Type.SERIAL;
        }
        if (typeString.equals("tv show")) {
            typeEnum = Type.TV_SHOW;
        }
        if (typeString.equals("animation")) {
            typeEnum = Type.ANIMATION;
        }
        return typeEnum;
    }

    public static Review convertFromStringToReview(String reviewString) {
        Review reviewEnum = Review.FIVE_STARS;
        if (reviewString.equals("1")) {
            reviewEnum = Review.ONE_STAR;
        }
        if (reviewString.equals("2")) {
            reviewEnum = Review.TWO_STARS;
        }
        if (reviewString.equals("3")) {
            reviewEnum = Review.THREE_STARS;
        }
        if (reviewString.equals("4")) {
            reviewEnum = Review.FOUR_STARS;
        }
        if (reviewString.equals("5")) {
            reviewEnum = Review.FIVE_STARS;
        }
        return reviewEnum;
    }

    //citesc din fisierul movies.txt linie cu linie
    //impart dupa virgula fiecare linie citita (care este un string) intr-un array de string-uri (folosind split)
    //convertesc tipul si genul citite la enum
    //imi fac un nou obiect de tip film (cu actorsList si reviewList 2 liste goale) cu infromatiile din array-ul de string-uri

    public static List<Movie> readMoviesFromFileText() throws IOException {
        List<Movie> movieList = new ArrayList<>();
        BufferedReader reader = null;
        // StringBuilder result = new StringBuilder();
        reader = new BufferedReader(
                new FileReader("src/main/java/IMDbFinalProject/movies.txt"));
        String aLine;
        while ((aLine = reader.readLine()) != null) {
            String[] lineArray = new String[5];
            lineArray = aLine.split(",");
            String title = lineArray[0];
            int releaseYear = Integer.parseInt(lineArray[1]);
            Genre genre = convertFromStringToGenre(lineArray[2]);
            Type type = convertFromStringToType(lineArray[3]);
            Movie movie = new Movie(title, releaseYear, genre, type, new ArrayList<>(), new ArrayList<>());
            movieList.add(movie);
        }
        reader.close();
        return movieList;

    }

    //citesc din fisierul actors.txt linie cu linie
    //impart dupa virgula fiecare linie si rezulta array-ul a
    //caut in lista filmul cu numele a[0]
    //merg de la 1 pana la lugimea lui a
    //bag toate elementele din a (toti actorii) in lista de actori a filmului gasit
    public static void readTheActorsListFromFileText(IMDBService imdbService) throws IOException {
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        reader = new BufferedReader(
                new FileReader("src/main/java/IMDbFinalProject/actors.txt"));
        String aLine;
        while ((aLine = reader.readLine()) != null) {
            result.append(aLine);
            String[] lineArray = aLine.split(",");
            String movieName = lineArray[0];
           // String actorName = lineArray[1]; //todo
            List<String> actorsFromCurrentLine = new ArrayList<>();
            for (int i = 1; i < lineArray.length; i++) {
                actorsFromCurrentLine.add(lineArray[i]);
            }
            //caut filmul dupa nume
            //adaug la film lista de actori
            Movie movie = imdbService.findMovieByName(movieName);
            movie.setActorsList(actorsFromCurrentLine);
        }
        reader.close();
    }



    //citesc din fisierul reviews.txt linie cu linie
    //impart dupa virgula fiecare linie si rezulta array-ul a
    //caut in lista filmul cu numele a[0]
    //merg de la 1 pana la lugimea lui a
    //convertesc de la string la enum-ul de review
    //bag fiecare review din a(tate review-urile) in lista de reviewuri a filmului gasit
    public static void readReviewFromText(IMDBService imdbService) throws IOException {
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        reader = new BufferedReader(
                new FileReader("src/main/java/IMDbFinalProject/reviews.txt"));
        String aLine;
        while ((aLine = reader.readLine()) != null) {
            result.append(aLine);
            String[] lineArray = aLine.split(",");
            String movieName = lineArray[0];
            List<Review> reviewsFromCurrentLine = new ArrayList<>();
            for (int i = 1; i < lineArray.length; i++) {
                reviewsFromCurrentLine.add(convertFromStringToReview(lineArray[i]));
            }
            //caut filmul dupa nume si adaug la film lista de review-uri
            Movie movie = imdbService.findMovieByName(movieName);
            movie.setReviewsList(reviewsFromCurrentLine);
        }
        reader.close();
    }

}