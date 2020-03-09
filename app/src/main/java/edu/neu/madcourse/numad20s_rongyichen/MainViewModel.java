package edu.neu.madcourse.numad20s_rongyichen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private WebsitesRepository repository;
    private LiveData<List<Websites>> allWebs;


    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new WebsitesRepository(application);
        allWebs = repository.getAllWebs();

    }

    public LiveData<List<Websites>> getAllWebs() {
        return allWebs;
    }


    public void insertWebsite(Websites website) {
        repository.insertWebsite(website);
    }

    public void deleteWebsite(int id) {
        repository.deleteWebsite(id);
    }
}
