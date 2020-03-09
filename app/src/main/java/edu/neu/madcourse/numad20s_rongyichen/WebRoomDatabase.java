package edu.neu.madcourse.numad20s_rongyichen;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Websites.class}, version = 1)
public abstract class WebRoomDatabase extends RoomDatabase {
    public abstract WebsitesDao websiteDao();
    private static WebRoomDatabase INSTANCE;
    static WebRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WebRoomDatabase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WebRoomDatabase.class, "website_database").build();
                }
            }
        }
        return INSTANCE;
    }
}