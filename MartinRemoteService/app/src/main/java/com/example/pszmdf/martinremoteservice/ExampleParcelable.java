package com.example.pszmdf.martinremoteservice;

import android.os.Parcel;
import android.os.Parcelable;

public class ExampleParcelable implements Parcelable {

    public int x;
    public int y;
    public String text;

    public ExampleParcelable() {

    }

    public ExampleParcelable(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.x);
        out.writeInt(this.y);
        out.writeString(this.text);
    }

    private void readFromParcel(Parcel in) {
        this.x = in.readInt();
        this.y = in.readInt();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<ExampleParcelable> CREATOR = new Parcelable.Creator<ExampleParcelable>() {

        public ExampleParcelable createFromParcel(Parcel in) {
            return new ExampleParcelable(in);
        }

        public ExampleParcelable[] newArray(int size) {
            return new ExampleParcelable[size];
        }
    };
}
