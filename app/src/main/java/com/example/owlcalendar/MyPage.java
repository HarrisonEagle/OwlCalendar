package com.example.owlcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MyPage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.mypage,container, false);

        Button login = v.findViewById(R.id.login);
        final String userid = MainContents.getDefaults("userid",getContext());
        if(userid==null){
            login.setText("LogIn");
        }else{
            login.setText("LogOut");
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userid==null){
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    getActivity().startActivity(intent);
                }else{
                    MainContents.delDefaults("userid",getContext());
                    Toast.makeText(getContext(),"Log Out",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(),MainContents.class);
                    startActivity(intent);
                }
            }
        });


        super.onCreate(savedInstanceState);

        return v;
    }

}