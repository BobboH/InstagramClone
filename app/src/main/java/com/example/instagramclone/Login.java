package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class Login extends AppCompatActivity {
    protected EditText edtName;
    protected EditText edtKickSpeed;
    protected EditText edtKickPower;
    protected Button btnSaveBoxer;
    protected TextView txtGetData;
    protected TextView txtName;
    protected TextView txtKickSpeed;
    protected TextView txtKickPower;
    private String allKickBoxers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        btnSaveBoxer = findViewById(R.id.btnSaveBoxer);
        txtGetData=findViewById(R.id.txtGetData);
        txtName = findViewById(R.id.txtName);
        txtKickSpeed = findViewById(R.id.txtKickSpeed);
        txtKickPower = findViewById(R.id.txtKickPower);
        txtName.setText("Name");;
        txtKickSpeed.setText("Speed");
        txtKickPower.setText("Power");

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e==null){
                            for(ParseObject kickboxer :objects ){
                                allKickBoxers = allKickBoxers + kickboxer.get("Name") + "\n";
                            }
                            if (objects.size() >0){
                                FancyToast.makeText(Login.this,
                                        allKickBoxers,
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }
                        }
                    }
                });
//                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
//                parseQuery.getInBackground("YD6i1YiY4k", new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                        if(object != null && e==null){
//                         //   txtGetData.setText(object.get("Name").toString());
//                            txtName.setText(object.get("Name").toString());
//                            txtKickSpeed.setText(object.get("kickSpeed").toString());
//                            txtKickPower.setText(object.get("kickPower").toString());
//                        }
//                    }
//                });
            }
        });
    }


    public void updateBoxer(View buttonView) {
        try {

            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name", edtName.getText().toString());
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));

            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(Login.this, "Kickboxer " + kickBoxer.get("Name") + "  Saved!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        //   Toast.makeText(Login.this, "Kickboxer " + kickBoxer.get("Name")+ "  Saved!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch(Exception e){
            FancyToast.makeText(Login.this, "Kickboxer Save Failed " + e.getCause().toString(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
 //   public void helloWorldTapped(View view){
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punchSpeed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null){
//                    Toast.makeText(Login.this, "Save successful", Toast.LENGTH_SHORT).show();
//                }
//            }
////        });
//        ParseObject kickBoxer = new ParseObject("KickBoxer");
//        kickBoxer.put("kickpower", 400);
//        kickBoxer.put("kickSpeed", 100);
//        kickBoxer.put("Name", "Fred");
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null){
//                    Toast.makeText(Login.this, "Kickboxer Saved",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

 //   }
}
