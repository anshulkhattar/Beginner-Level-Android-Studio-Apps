package com.codingwithmitch.mvvmrecyclerview.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codingwithmitch.mvvmrecyclerview.models.CountryModel;
import com.codingwithmitch.mvvmrecyclerview.repositories.CountryRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    /**
     *       mutable data mean can be change
     *       live data cant be directly change
     */
    private MutableLiveData<List<CountryModel>> mCountryMutableData;
    private CountryRepository mRepo;
//    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init() {
        if (mCountryMutableData != null) {
            return;
        }
        mRepo = CountryRepository.getInstance();
        mCountryMutableData = mRepo.getCountry1();  //retrieve mutable live data from repository
    }


    public LiveData<List<CountryModel>> getCountry() {
        return mCountryMutableData;
    }


}
