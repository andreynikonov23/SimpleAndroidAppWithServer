package ru.nnov.app.ui.bucket;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BucketViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BucketViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bucket fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}