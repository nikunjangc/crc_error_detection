package nyc.c4q.fragments2;



import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.concurrent.TransferQueue;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {
    View rootView;
    TextView text;
    EditText editText;
    TextView fragment_textview1;
    EditText crc_edittext;
    Button nextButton;
    TextView transfercode;
    String code="";

   public NewFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String check(String information, String polynomial){

        int prelength = information.length() + polynomial.length();
        int length=prelength-1;
        int dividend[]=new int[length];
        int divisor[] = new int[polynomial.length()];
        for(int i=0;i<information.length();i++)
            dividend[i] = Integer.parseInt(information.charAt(i)+"");
        for(int i=0;i<polynomial.length();i++)
            divisor[i] = Integer.parseInt(polynomial.charAt(i)+"");
        /*
        Calculating CRC code word ; division from  the above dividend and divisor and find the remainder
        code word = information word + Remainder
         */
        for(int i=0;i<information.length();i++)
        {
            if(dividend[i]==1)
                for(int j=0;j<divisor.length;j++)
                    dividend[i+j] ^= divisor[j];
        }
        System.out.print("Computed CRC code word is: ");

        for(int i=0;i<information.length();i++)
            dividend[i] = Integer.parseInt(information.charAt(i)+"");
        code="";
        for(int i=0;i<dividend.length;i++){
                code+=Integer.toString(dividend[i]);
        }
        return (code) ;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_new,container,false);
        text=(TextView) rootView.findViewById(R.id.fragment_textview);
        editText=(EditText) rootView.findViewById(R.id.fragment_edittext);
        fragment_textview1=(TextView) rootView.findViewById(R.id.fragment_textview1);
        crc_edittext=(EditText) rootView.findViewById(R.id.crc_edittext);
        transfercode=(TextView) rootView.findViewById(R.id.transfer_code);
        nextButton=(Button) rootView.findViewById(R.id.fragment_button);

         /*
        From the given CRC polynomial and information word, We need to find dividend and divisor to compute CRC Code word
        Thus dividend =information word + (length of CRC polynomial -1) 0's at the end
        divisor is same as the CRC polynomial
         */


        nextButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String information=editText.getText().toString();
                String polynomial=crc_edittext.getText().toString();

                String a=  check(information,polynomial);


                transfercode.setText(a);

                SecondFragment secondFragment= new SecondFragment();
                FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                Bundle bundle=new Bundle();
                bundle.putString("text01",transfercode.getText().toString());
                secondFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag_container_framelayout,secondFragment);
                fragmentTransaction.addToBackStack("new_fragment");
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }

}
