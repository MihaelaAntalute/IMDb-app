import java.util.List;

public class Admin extends User {


    private List<Client> clientsList;
    public Admin(String firstName, String secondName, List<Client> clientsList) {
        super(firstName, secondName);
        this.clientsList = clientsList;
    }

    public List<Client> getClientsList() {
        return clientsList;
    }

    public void setClientsList(List<Client> clientsList) {
        this.clientsList = clientsList;
    }

    //adauga film in lista de filme
   public void addMovieToMovieList(List<Movie> movieList,Movie movie){
        movieList.add(movie);
       System.out.println("The movie was added on list"+ movie);
   }

}
