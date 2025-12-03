package com.example.fastlicense;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottonMenu {


    public static boolean switchPage(Activity activity, MenuItem item){
        int id = item.getItemId();

        if(id == R.id.home && !(activity instanceof Home)){
            activity.startActivity(new Intent(activity, Home.class));
            return true;
        } else if (id == R.id.areaUtente && !(activity instanceof AreaUtente)) {

            activity.startActivity(new Intent(activity, AreaUtente.class));
            return true;

        }else {
            return false;
        }

    }


}
