package edu.neu.madcourse.numad20s_rongyichen;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class WebRepo {
    private MutableLiveData<List<Websites>> searchResults = new MutableLiveData<>();
    private LiveData<List<Websites>> allWebs;
    private WebsitesDao websitesDao;

    public WebRepo(Application application) {
        WebRoomDatabase db;
        db = WebRoomDatabase.getDatabase(application);
        websitesDao = db.websiteDao();
        allWebs = websitesDao.getAllWebs();
    }
    public void insertWebsite(Websites newWeb) {
        InsertAsyncTask task = new InsertAsyncTask(websitesDao);
        task.execute(newWeb);
    }

    public LiveData<List<Websites>> getAllWebs() {
        return allWebs;
    }

    public MutableLiveData<List<Websites>> getSearchResults() {
        return searchResults;
    }

    public void deleteWebsite(int id) {
        DeleteAsyncTask task = new DeleteAsyncTask(websitesDao);
        task.execute(id);
    }

    private void asyncFinished(List<Websites> results) {
        searchResults.setValue(results);
    }

    private static class InsertAsyncTask extends AsyncTask<Websites, Void, Void> {
        private WebsitesDao asyncTaskDao;

        InsertAsyncTask(WebsitesDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Websites... params) {
            asyncTaskDao.insertWebsite(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private WebsitesDao asyncTaskDao;

        DeleteAsyncTask(WebsitesDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            asyncTaskDao.deleteProduct(params[0]);
            return null;
        }
    }
}
