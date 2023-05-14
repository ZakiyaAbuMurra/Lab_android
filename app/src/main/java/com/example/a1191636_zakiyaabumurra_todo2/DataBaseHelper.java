package com.example.a1191636_zakiyaabumurra_todo2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "scores.db";
        private static final int DATABASE_VERSION = 1;
        private static final  String TABLE_NAME = "scores";
        private static final String ID = "id";
        private static final String NICKNAME = "nickname";
        private static final String SCORE = "score";

        public DataBaseHelper(Context context, String s, Object o, int i) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // EXECUTE THE query
            String createTable = "CREATE TABLE scores ("+
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NICKNAME + " TEXT, " +
                    SCORE + " INTEGER)";
            db.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // implemention when you want to call the onCreate() becuase this function call one time in defualt
        }

        public boolean insertScore(Player player) {
            SQLiteDatabase db = this.getWritableDatabase();

            // Check if the nickname already exists in the database
//            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NICKNAME + "=?", new String[]{player.getNickname()});
//            if (cursor.moveToFirst()) {
//                // The nickname already exists, update the player for the existing record
//                int id = cursor.getInt(cursor.getColumnIndex(ID));
//                int currentScore = cursor.getInt(cursor.getColumnIndex(SCORE));
//                int score_value = player.getScore() ;
//                score_value += currentScore;
//                ContentValues values = new ContentValues();
//                values.put("SCORE", score_value);
//                String arg [] = {String.valueOf(player.getNickname())};
//                // sign '?' for SQL injection
//                // return the number updated raw
//                db.update(TABLE_NAME, values, NICKNAME + "=?", arg);
//                return true;
//            } else {
                // The nickname doesn't exist, insert a new record
                ContentValues contentValues = new ContentValues();
                contentValues.put(NICKNAME, player.getNickname());
                contentValues.put(SCORE, player.getScore());
                long result = db.insert(TABLE_NAME, null, contentValues);
                //check if the insertion operation is succfull or not
                if (result == -1 )
                    return false;
                else
                    return true ;
            }
        //}

        public Cursor getTopScores(int limit) {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("SELECT NICKNAME, SCORE FROM " + TABLE_NAME + " ORDER BY SCORE DESC LIMIT 5", null);

//            //rawquray return cursor
//            Cursor cursor = db.rawQuery(query, null);
//            ArrayList<Player> scores = new ArrayList<>();
//            // if this condition is true that means thers is element in cursor , otherwise it's empty
//            if (cursor != null && cursor.moveToFirst()) {
//                 // use do while to start read from first raw , then if cursor.moveToNext is true (there is another raw in table) continues .
//                do {
//                    int id = cursor.getInt(cursor.getColumnIndex(ID));
//                    String nickname = cursor.getString(cursor.getColumnIndex(NICKNAME));
//                    int score = cursor.getInt(cursor.getColumnIndex(SCORE));
//                    // add the argument to the new object of the scores class
//                    Player s = new Player(nickname ,score);
//                    scores.add(s);
//
//                } while (cursor.moveToNext());
//            }
//            cursor.close();

        }

        public  long getPlayerCount(){
            SQLiteDatabase db = getReadableDatabase() ;
            DatabaseUtils.queryNumEntries(db ,TABLE_NAME);
            return 0 ;
        }

        public boolean deleteAllScores() {
            SQLiteDatabase db = this.getWritableDatabase();
            int rowsDeleted = db.delete("scores", null, null);
            return rowsDeleted > 0;
        }
    }
