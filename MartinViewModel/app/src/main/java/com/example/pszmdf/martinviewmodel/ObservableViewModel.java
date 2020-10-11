package com.example.pszmdf.martinviewmodel;

import androidx.lifecycle.ViewModel;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

public class ObservableViewModel extends ViewModel implements Observable {

    PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    public void notifyChange() {
        callbacks.notifyCallbacks(this, 0, null);
    }

    public void notifyPropertyChanged(int fieldId){
        callbacks.notifyCallbacks(this, fieldId, null);
    }
}
