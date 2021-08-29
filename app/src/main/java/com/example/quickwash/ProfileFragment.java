package com.example.quickwash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ProfileFragment extends Fragment
{


    EditText uname,umobile,uemail,uadress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment,null);





    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        String name=getArguments().getString("Name");
        String email=getArguments().getString("Email");
        String mobile=getArguments().getString("Mobile");
        String address=getArguments().getString("Address");

        uname = (EditText)view.findViewById(R.id.pname);
        umobile=(EditText)view.findViewById(R.id.pmobile);
        uemail=(EditText)view.findViewById(R.id.pemail);
        uadress=(EditText)view.findViewById(R.id.paddress);

        uname.setText(name);
        umobile.setText(mobile);
        uemail.setText(email);
        uadress.setText(address);


        uname.setEnabled(false);
        umobile.setEnabled(false);
        uemail.setEnabled(false);
        uadress.setEnabled(false);



    }
}
