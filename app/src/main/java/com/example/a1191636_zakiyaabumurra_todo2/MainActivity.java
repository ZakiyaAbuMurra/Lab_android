package com.example.a1191636_zakiyaabumurra_todo2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout secondLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout firstLinearLayout = new LinearLayout(this);
        Button addButton = new Button(this);
        TextView text = new TextView(this);
        secondLinearLayout = new LinearLayout(this);


        ScrollView scrollView = new ScrollView(this);
        firstLinearLayout.setOrientation(LinearLayout.VERTICAL);
        secondLinearLayout.setOrientation(LinearLayout.VERTICAL);

        addButton.setText("Add pLAYER");
        addButton.setLayoutParams(new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                .LayoutParams.WRAP_CONTENT));



        firstLinearLayout.addView(addButton);
        scrollView.addView(secondLinearLayout);
        firstLinearLayout.addView(scrollView);

        setContentView(firstLinearLayout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPlayerActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });


        LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        secondLinearLayout.addView(linearLayout1);

        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        secondLinearLayout.addView(linearLayout2);

        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setOrientation(LinearLayout.VERTICAL);
        secondLinearLayout.addView(linearLayout3);




//        for(Customer objCustomer : Customer.customersArrayList) {
//            TextView txtCustomerInfo = new TextView(this);
//            txtCustomerInfo.setTextAppearance(R.style.TextAppearance_AppCompat_Display2);
//            txtCustomerInfo.setText(String.valueOf(objCustomer.getmCustomerId()) + "    ");
//            linearLayout1.addView(txtCustomerInfo);
//        }
//
//        for(Customer objCustomer : Customer.customersArrayList) {
//            TextView txtCustomerInfo = new TextView(this);
//            txtCustomerInfo.setTextAppearance(R.style.TextAppearance_AppCompat_Display2);
//            txtCustomerInfo.setText(objCustomer.getmName()+ "    ");
//            linearLayout2.addView(txtCustomerInfo);
//        }
//
//        for(Customer objCustomer : Customer.customersArrayList) {
//            TextView txtCustomerInfo = new TextView(this);
//            txtCustomerInfo.setTextAppearance(R.style.TextAppearance_AppCompat_Display2);
//            txtCustomerInfo.setText(objCustomer.getmGender()+"    ");
//            linearLayout3.addView(txtCustomerInfo);
//        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper =new DataBaseHelper(MainActivity.this," 1191636_ZakiyaAbuMurra_TODO2 ",  null, 1);
        Player player = new Player();
        Cursor cursor = dataBaseHelper.getTopScores(5);

        dataBaseHelper.insertScore(player);

        while (cursor.moveToNext()) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText("Name= " + cursor.getString(0) + "\nScore= " + cursor.getString(1) + "\n\n");

            secondLinearLayout.addView(textView);
        }


    }

}