package com.example.app01sqlite;

import android.graphics.Bitmap;

public class Item {
    Bitmap animalImage;

    public Item(Bitmap animalImage)
    {
        this.animalImage=animalImage;
    }
    public Bitmap getAnimalImage()
    {
        return animalImage;
    }
}