package com.codingwithmitch.mvvmrecyclerview.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.codingwithmitch.mvvmrecyclerview.models.CountryModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class CountryRepository {

    private static CountryRepository instance;
    private ArrayList<CountryModel> dataSet = new ArrayList<>();


    public static CountryRepository getInstance(){
        if(instance == null){
            instance = new CountryRepository();
        }
        return instance;
    }


    /**
     *    Pretend to get data from a webservice or online source
     */
    public MutableLiveData<List<CountryModel>> getCountry1(){
        setCountry();
        MutableLiveData<List<CountryModel>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }


    private void setCountry(){

        dataSet.add(
                new CountryModel("SRI LANKA", "https://live.staticflickr.com/2052/2294634746_838b570b51_c.jpg",
                        "G Rajapaksha", "2M")
        );

        dataSet.add(
                new CountryModel("AUSTRALIA", "data:image/png;base64,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",
                        "S Morrison", "25.36M")
        );

        dataSet.add(
                new CountryModel("INDIA", "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png",
                        "R N Kovind", "1.3B")
        );

        dataSet.add(
                new CountryModel("AMERICA", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a4/Flag_of_the_United_States.svg/1200px-Flag_of_the_United_States.svg.png",
                        "Joe Biden", "328.2M")
        );

        dataSet.add(
                new CountryModel("CANADA", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/1200px-Flag_of_Canada_%28Pantone%29.svg.png",
                        "J Trudeau", "37.2M")
        );

        dataSet.add(
                new CountryModel("JAPAN", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Flag_of_Japan_%28bordered%29.svg/2560px-Flag_of_Japan_%28bordered%29.svg.png",
                        "Joe Biden", "328.2M")
        );

        dataSet.add(
                new CountryModel("CHINA", "https://cdn.britannica.com/90/7490-004-BAD4AA72/Flag-China.jpg",
                        "Xi Jinping", "1.39B")
        );

        dataSet.add(
                new CountryModel("NEW ZEALAND", "https://cdn.britannica.com/17/3017-004-DCC13F9D/Flag-New-Zealand.jpg",
                        "J Ardern", "328.2M")
        );

    }
}











