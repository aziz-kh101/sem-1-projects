package com.example.project.lifecycle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> mutableLiveData=new MutableLiveData<>();

    public void setValue(Integer i)
    {
        mutableLiveData.setValue(i);
    }

    public MutableLiveData<Integer> getValue()
    {
        return mutableLiveData;
    }
}
