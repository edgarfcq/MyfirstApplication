package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;



import com.example.myfirstapplication.databinding.ActivityMainBinding;
import com.example.myfirstapplication.databinding.FragmentCheckListBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

                    int id = item.getItemId();
                    if (id == R.id.Home) {
                        replaceFragment(new HomeFragment());
                    } else if (id == R.id.navigation) {
                        replaceFragment(new NavigationFragment());
                    } else if (id == R.id.check_list) {
                        replaceFragment(new CheckListFragment());
                    }
                    return true;
        }
            /*switch (item.getItemId()){
                case R.id.Home:
                    break;
                case R.id.check_list:
                    break;
                case R.id.navigation:
                    break;
            }

             */

                );
    }




    public void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}