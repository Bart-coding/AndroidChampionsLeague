package com.example.androidchampionsleague;

public class Match {
    private int Id;
    private Team HomeTeam; //lub minitablica
    private int[] HomeScores = new int[2];
    private Team AwayTeam;
    private int[] AwayScores = new int[2];
    private String groupName;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Team getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        HomeTeam = homeTeam;
    }

    public int getHomeFullScore() {
        return HomeScores[0];
    }

    public void setHomeFullScore(int homeFullScore) {
        HomeScores[0] = homeFullScore;
    }

    public int getHomeHalfScore() {
        return HomeScores[1];
    }

    public void setHomeHalfScore(int homeHalfScore) {
        HomeScores[1] = homeHalfScore;
    }

    public Team getAwayTeam() {
        return AwayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        AwayTeam = awayTeam;
    }

    public int getAwayFullScore() {
        return AwayScores[0];
    }

    public void setAwayFullScore(int awayFullScore) {
        AwayScores[0] = awayFullScore;
    }

    public int getAwayHalfScore() {
        return AwayScores[1];
    }

    public void setAwayHalfScore(int awayHalfScore) {
        AwayScores[1] = awayHalfScore;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
