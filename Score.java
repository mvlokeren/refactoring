public class Score {
    private int userScore = 0;
    private int compScore = 0;
    private int totalGames = 0;

    public Score(int userScore, int compScore, int totalGames){
        this.userScore = userScore;
        this.compScore = compScore;
        this.totalGames = totalGames;
    }

    public int getUserScore(){
        return userScore;
    }

    public int getCompScore() {
        return compScore;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setUserScore(int score){
        this.userScore = score;
    }

    public void setCompScore(int score){
        this.compScore = score;
    }

    public void setTotalGames(int total){
        this.totalGames = total;
    }
}
