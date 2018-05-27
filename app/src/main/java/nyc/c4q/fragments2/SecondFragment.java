package nyc.c4q.fragments2;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    View rootView;
    TextView textView1;
    EditText message_edittext;
    TextView txt_view_message;
    Button btn_check;
    String code="";
    int a;



    public SecondFragment() {
        // Required empty public constructor
    }


    public String errorchecker(String information, String polynomial){
 /*
Checking the Error in data transmission
 */

        int dividend[]= new int[information.length() + polynomial.length()-1];
        int divisor[] = new int[polynomial.length()];
        for(int i=0;i<information.length();i++)
            dividend[i] = Integer.parseInt(information.charAt(i)+""); // charAt(i) returns the character at index i
        for(int i=0;i<polynomial.length();i++)
            divisor[i] = Integer.parseInt(polynomial.charAt(i)+"");

/*
Calculate Remainder to check whether there is error in data transmission or not
our Computed entered CRC code word will be dividend and CRC polynomial of existing data transmission process will be the divisor
Check the Remainder
if there is no remainder, ie 0000 No Error in Data Transmission
if there is remainder, ie 1111,~ any remainder, there is error in data Transmission
 */
        for(int i=0;i<information.length();i++){
            if(dividend[i]==1)
                for(int j=0;j<divisor.length;j++)
                    dividend[i+j] ^= divisor[j];
        }



        boolean check = true;
        for(int i=0;i<dividend.length;i++)
            if (dividend[i] == 1) {
                check = false;
                break;


            }


        if(check==true){
            code="yes";
            return   code;

        }
        else{
            code="no";
            return code;

        }

    }









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        rootView= inflater.inflate(R.layout.fragment_second, container, false);
        textView1=(TextView) rootView.findViewById(R.id.fragment2_textview);
        message_edittext=(EditText) rootView.findViewById(R.id.message_edittext);
        txt_view_message=(TextView) rootView.findViewById(R.id.final_message_txtview);
        btn_check=(Button) rootView.findViewById(R.id.btn_check);
        Bundle bundle= getArguments();

        String textString=bundle.getString("text01");
         final String textpono=bundle.getString("text02");
        textView1.setText(textString);


        btn_check.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                String encryptedmessage = message_edittext.getText().toString();

                String checked = errorchecker(encryptedmessage, textpono);
                if (checked == "yes") {
                    txt_view_message.setText("No Error in Data Transmission ");


                } else if (checked == "no") {
                    txt_view_message.setText(" Error in Data Transmission ");
                } else {
                    txt_view_message.setText(" Error ");
                }

            }

        });











        return rootView;

}
    }

