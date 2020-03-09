package edu.neu.madcourse.numad20s_rongyichen;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WebsitesDao {
    @Insert
    void insertWebsite(Websites websites);

    @Query("DELETE FROM Websites WHERE websiteId = :webId")
    void deleteProduct(int webId);

    @Query("SELECT * FROM Websites")
    LiveData<List<Websites>> getAllWebs();

}
