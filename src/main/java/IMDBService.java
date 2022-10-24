import java.util.*;
import java.util.stream.Collectors;

public class IMDBService {
    private List<Movie> movieList;

    public IMDBService(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public IMDBService() {
    }
    // IMDb = Internet Movie Database

    //Gruparea tuturor filmelor dupa gen
    public Map<Genre, List<Movie>> getAllMoviesByGenre() {
        Map<Genre, List<Movie>> moviesByGenre = new HashMap<>();
        for (Movie movie : movieList) {
            if (!moviesByGenre.containsKey(movie.getGenre())) {
                List<Movie> list = new ArrayList<>();
                list.add(movie);
                moviesByGenre.put(movie.getGenre(), list);
            } else {
                moviesByGenre.get(movie.getGenre()).add(movie);
            }
        }
        return moviesByGenre;
    }

    //java8
    public Map<Genre, List<Movie>> getAllMoviesByGenre2() {
        Map<Genre, List<Movie>> allMoviesByGenre = movieList.stream()
                .collect(Collectors.groupingBy(movie -> movie.getGenre()));
        return allMoviesByGenre;
    }

    //Gruparea tuturor filmelor dupa tip
    public Map<Type, List<Movie>> getAllMoviesByType() {
        Map<Type, List<Movie>> moviesByGenre = new HashMap<>();
        for (Movie movie : movieList) {
            if (!moviesByGenre.containsKey(movie.getType())) {
                List<Movie> list = new ArrayList<>();
                list.add(movie);
                moviesByGenre.put(movie.getType(), list);
            } else {
                moviesByGenre.get(movie.getType()).add(movie);
            }
        }
        return moviesByGenre;
    }

    //java8
    public Map<Type, List<Movie>> getAllMoviesByType2() {
        Map<Type, List<Movie>> allMoviesByType = movieList.stream()
                .collect(Collectors.groupingBy(movie -> movie.getType()));
        return allMoviesByType;
    }

    //Gruparea tuturor titlutrilor de filme dupa gen
    public Map<Genre, List<String>> getAllMoviesTitlesByGenre() {
        Map<Genre, List<String>> moviesByGenre = new HashMap<>();
        for (Movie movie : movieList) {
            if (!moviesByGenre.containsKey(movie.getGenre())) {
                List<String> list = new ArrayList<>();
                list.add(movie.getTitle());
                moviesByGenre.put(movie.getGenre(), list);
            } else {
                moviesByGenre.get(movie.getGenre()).add(movie.getTitle());
            }
        }
        return moviesByGenre;
    }


    //Gruparea tuturor titlurilor de filme dupa gen java8
    public Map<Genre, List<String>> getAllMoviesTitlesByGenre2() {
        return getMovieList().stream()
                .collect(Collectors.groupingBy(movie -> movie.getGenre(), Collectors.mapping(movie -> movie.getTitle(), Collectors.toList())));
    }

    //Gruparea tuturor titlurilor de film dupa type
    public Map<Type, List<String>> getAllMoviesTitlesByType() {
        Map<Type, List<String>> allMoviesTitlesByType = new HashMap<>();
        for (Movie movie : movieList) {
            if (!allMoviesTitlesByType.containsKey(movie.getType())) {
                List<String> list = new ArrayList<>();
                list.add(movie.getTitle());
                allMoviesTitlesByType.put(movie.getType(), list);
            } else {
                allMoviesTitlesByType.get(movie.getType()).add(movie.getTitle());
            }
        }
        return allMoviesTitlesByType;
    }

    //java8
    public Map<Type, List<String>> getAllMoviesTitlesByType2() {
        return movieList.stream()
                .collect(Collectors.groupingBy(Movie::getType, Collectors.mapping(Movie::getTitle, Collectors.toList())));
    }

    //Gasirea tuturor filmelor in care joaca un anumit actor
    public List<Movie> getAllMoviesByActor(String actor) {
        List<Movie> allMoviesByActor = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getActorsList().contains(actor)) {
                allMoviesByActor.add(movie);
            }
        }
        return allMoviesByActor;
    }

    public Movie findMovieByName(String movieName) {
        Movie movie = null;
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie1 = movieList.get(i);
            if (movie1.getTitle().equals(movieName)) {
                movie = movie1;
            }
        }
        return movie;
    }

    public String findTitleMovieByName(String movieName) {
        String movieTitle = "";
        for (int i = 0; i < movieList.size(); i++) {
            String movieName2 = movieList.get(i).getTitle();
            if (movieName2.equals(movieName)) {
                movieTitle = movieName;
            }
        }
        return movieTitle;
    }
    //java8
    public Map<String, List<String>> getAllMoviesByActor2(String actor) {
        return movieList.stream()
                .flatMap(movie -> movie.getActorsList().stream())
                .filter(list -> list.contains(actor))
                .collect(Collectors.groupingBy(movie -> movie));
    }

    public List<String> getAllTitlesMoviesByActor(String actor) {
        List<String> allMoviesByActor = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getActorsList().contains(actor)) {
                allMoviesByActor.add(movie.getTitle());
            }
        }
        return allMoviesByActor;
    }

    //Ordonarea filmelor dupa anul aparitiei
    public Set<Integer> getOrderMoviesByReleaseYear() {
        Set<Integer> allMovies = new TreeSet<>();
        for (Movie movie : movieList) {
            allMovies.add(movie.getReleaseYear());
        }
        return allMovies;
    }

    //java8
    public Set<Integer> getOrderMoviesByReleaseYear2() {
        return movieList.stream().sorted().map(Movie::getReleaseYear).collect(Collectors.toSet());
    }

    public Set<String> getAllActorsByTypeAndGenre(Type type, Genre genre) {
//        List<String> actorsList = new ArrayList<>();
//        for (int i = 0; i < movieList.size(); i++) {
//            if ((movieList.get(i).getGenre().equals(genre)) && (movieList.get(i).getType().equals(type))) {
//                actorsList.add(String.valueOf(movieList.get(i).getActorsList()));
//            }
//
//        }
        Set<String> actorsSet = new HashSet<>();
        for (Movie movie : movieList) {
            for (String actor : movie.getActorsList()) {
                if (movie.getGenre().equals(genre) && movie.getType().equals(type)) {
                    actorsSet.add(actor);
                }
            }
        }
        return actorsSet;
    }

    //java8
//    public static List<String>getAllActorsList(Genre genre,Type type){
//        return movieList.stream()
//    }


    //Gasirea celui mai apreciat film de un anumit tip si gen
    public String getTheMostFamousMovieTitleByGenreAndType(Genre genre, Type type) {
        String title = "";
        double maxAverageReviewValue = 0;
        for (Movie movie : movieList) {
            if ((movie.getGenre().equals(genre)) && (movie.getType().equals(type)) && (movie.getAverageReviewValueForMovie() > maxAverageReviewValue)) {
                title = movie.getTitle();
                maxAverageReviewValue = movie.getAverageReviewValueForMovie();
            }
        }
        return title;
    }

    //gasirea celui mai apreciat film dupa gen
    public String getTheMostFamousMovieTitleByGenre(Genre genre) {
        String title = "";
        double maxAverageReviewValue = 0;
        for (Movie movie : movieList) {
            if ((movie.getGenre().equals(genre)) && (movie.getAverageReviewValueForMovie() > maxAverageReviewValue)) {
                maxAverageReviewValue = movie.getAverageReviewValueForMovie();
                title = movie.getTitle();
            }
        }
        return title;
    }

    //gasirea celui mai faimos film dupa type
    public String getTheMostFamousMovieTitleByType(Type type) {
        String title = "";
        double maxAverageReviewValue = 0;
        for (Movie movie : movieList) {
            if ((movie.getType().equals(type)) && (movie.getAverageReviewValueForMovie() > maxAverageReviewValue)) {
                maxAverageReviewValue = movie.getAverageReviewValueForMovie();
                title = movie.getTitle();
            }
        }
        return title;
    }
    //todo ordoneaza filmele dupa review descrescator
//    public static List<String> getOrderMovieByReview(List<Movie> movieList) {
//        List<String> movies = new ArrayList<>();
//        for (Movie movie : movieList) {
//            for (Review review : movie) {
//
//            }
//        }
//
//        return movies;
//    }

    //medie reviews
    public List<String> getMoviesOrderedByAverageReview() {
//        List<Movie> moviesOrderedByAverageReview = new ArrayList<>();
//        moviesOrderedByAverageReview.addAll(movieList);
//        moviesOrderedByAverageReview.sort(Comparator.comparingDouble(Movie::getAverageReviewValueForMovie));
        //return moviesOrderedByAverageReview;
        return movieList.stream().sorted(Comparator.comparingDouble(Movie::getAverageReviewValueForMovie).reversed()).map(movie -> movie.getTitle() + ", reviews:" + movie.getReviewAverage()).collect(Collectors.toList());
    }


    //gaseste actorul care joaca in cele mai multe filme
    //facem o mapa unde punem key actori si valori de cate ori apar in filme
    //returnam actorul cu cele mai multe valori (aparitii in filme)
    public Map<String, Integer> generateMap() {
        Map<String, Integer> mapActors = new HashMap<>();
        for (int i = 0; i < movieList.size(); i++) {
            for (int j = 0; j < movieList.get(i).getActorsList().size(); j++) {
                String actor = movieList.get(i).getActorsList().get(j);
                if (!mapActors.containsKey(actor)) {
                    mapActors.put(actor, 1);
                } else {
                    mapActors.put(actor, mapActors.get(actor) + 1);
                }
            }
        }
        return mapActors;
    }

    public String getActorWithMaxValuesFromMap(Map<String, Integer> mapActors) {
        int max = 0;
        String actor = "";
        Set<String> list = mapActors.keySet();
        for (String actorName : list) {
            if (mapActors.get(actorName) > max) {
                max = mapActors.get(actorName);
                actor = actorName;
            }
        }
        return actor;
    }


    public String getTheActorWhoActInMoreMovies() {
        generateMap();
        return getActorWithMaxValuesFromMap(generateMap());
    }


    //toti actorii dupa gen
    public Set<String> getAllActorsByGenre(Genre genre) {
        Set<String> actorsList = new HashSet<>();
        for (int i = 0; i < movieList.size(); i++) {
            for (int j = 0; j < movieList.get(i).getActorsList().size(); j++) {
                if (movieList.get(i).getGenre().equals(genre)) {
                    String actor = movieList.get(i).getActorsList().get(j);
                    actorsList.add(actor);
                }
            }
        }
        return actorsList;
    }

    public Set<String> getAllActorsByType(Type type) {
        Set<String> actorsList = new HashSet<>();
        for (int i = 0; i < movieList.size(); i++) {
            for (int j = 0; j < movieList.get(i).getActorsList().size(); j++) {
                if (movieList.get(i).getType().equals(type)) {
                    String actor = movieList.get(i).getActorsList().get(j);
                    actorsList.add(actor);
                }
            }
        }
        return actorsList;
    }

    //Cele mai populare n filme (bazat pe cat de des apar acele filme in listele de filme favorite ale utilizatorilor)
    //1.parcurg listele de clienti,apoi lista cu favoriteMovies de la fiecare client si le pun in mapa
    //2.Iau din mapa fimult valoarea cea mai mare (care a aparut de ma imulte ori)si o returnez
    public String getTheMostPopularTitleMovies(Admin admin) {
        String movieName = "";
        int maxNumber = 0;
        Map<String, Integer> movieAppearsOnList = generateMapForPopularMovies(admin);
        for (String key : movieAppearsOnList.keySet()) {
            if (movieAppearsOnList.get(key) > maxNumber) {
                maxNumber = movieAppearsOnList.get(key);
                movieName = key;
            }
        }
        return movieName;
    }

    public Map<String, Integer> generateMapForPopularMovies(Admin admin) {
        Map<String, Integer> movieAppearsOnList = new HashMap<>();
        for (int i = 0; i < admin.getClientsList().size(); i++) {
            for (int j = 0; j < admin.getClientsList().get(i).favoriteMoviesList.size(); j++) {
                String movie = admin.getClientsList().get(i).getFavoriteMoviesList().get(j);
                if (!movieAppearsOnList.containsKey(movie)) {
                    movieAppearsOnList.put(movie, 1);
                } else {
                    movieAppearsOnList.put(movie, movieAppearsOnList.get(movie) + 1);
                }
            }
        }
        return movieAppearsOnList;
    }


}
