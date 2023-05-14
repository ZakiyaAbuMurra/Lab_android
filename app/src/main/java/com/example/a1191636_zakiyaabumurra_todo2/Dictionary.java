package com.example.a1191636_zakiyaabumurra_todo2;

public class Dictionary {
    private String englishWord ;
    private String arabicWord_1 ;
    private String arabicWord_2 ;

    public String getArabicWord_2() {
        return arabicWord_2;
    }

    public void setArabicWord_2(String arabicWord_2) {
        this.arabicWord_2 = arabicWord_2;
    }

    private  int correctAnswerIndex ;

    public Dictionary(String englishWord, String arabicWord, String arabicWord_2 ,  int correctAnswerIndex) {
        this.englishWord = englishWord;
        this.arabicWord_1 = arabicWord;
        this.arabicWord_2 = arabicWord_2 ;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getArabicWord() {
        return arabicWord_1;
    }

    public void setArabicWord(String arabicWord) {
        this.arabicWord_1 = arabicWord;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "englishWord='" + englishWord + '\'' +
                ", arabicWord='" + arabicWord_1 + '\'' +
                ", correctAnswerIndex=" + correctAnswerIndex +
                '}';
    }
}

