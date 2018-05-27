package nyc.c4q.fragments2;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //to go to next fragment you need to create an object of next object , create a fragmentManager and fragmentTranxaction and set fragment manager to begin transaction
        // and replace the main activity associate with id to another fragment class object.
        NewFragment newFragment= new NewFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_container_framelayout,newFragment);
        fragmentTransaction.commit();

    }
}
