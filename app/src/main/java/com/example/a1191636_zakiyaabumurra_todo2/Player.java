package com.example.a1191636_zakiyaabumurra_todo2;


public class Player {
    private int id;
    private String nickname;
    private int score;
    public Player() {}

    public Player(int id, String nickname, int score) {
        this.id = id;
        this.nickname = nickname;
        this.score = score;
    }

    public Player(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", score=" + score +
                '}';
    }
}

