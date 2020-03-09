package edu.neu.madcourse.numad20s_rongyichen;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This is for website which user input
 */
@Entity(tableName = "websites")
public class Websites {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "websiteId")
    private int id;


    @ColumnInfo(name = "websiteName")
    private String web_name;

    @NonNull
    @ColumnInfo(name = "websiteUrl")
    private String web_url;


    public Websites(String web_name, String web_url) {
        this.web_name = web_name;
        this.web_url = web_url;
    }

    public int getId() {
        return id;
    }

    public String getWeb_name() {
        return web_name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setId(int id) {
        this.id = id;
    }


}
