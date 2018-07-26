package sg.edu.rp.webservices.firebasetodoapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Declare variable
    private TextView tvMessageTitle, tvMessageDate, tvMessageDays, tvMessageComplete;
    private EditText etMessageTitle, etMessageDate, etMessageDays;
    private CheckBox cbMessageComplete;
    private Button btnAdd;

    // TODO: Task 1 - Declare Firebase variables
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference toDoItemPOJOReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI element
        tvMessageTitle = (TextView) findViewById(R.id.textViewMessageTitle);
        tvMessageDate = (TextView) findViewById(R.id.textViewMessageDate);
        tvMessageDays = (TextView) findViewById(R.id.textViewMessageDays);
        tvMessageComplete = (TextView) findViewById(R.id.textViewMessageComplete);
        etMessageTitle = (EditText) findViewById(R.id.editTextMessageTitle);
        etMessageDate = (EditText) findViewById(R.id.editTextMessageDate);
        etMessageDays = (EditText) findViewById(R.id.editTextMessageDays);
        cbMessageComplete = (CheckBox) findViewById(R.id.checkBoxMessageComplete);
        btnAdd = (Button) findViewById(R.id.buttonAdd);

        // TODO: Task 2 - Get Firebase database instance and reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        toDoItemPOJOReference = firebaseDatabase.getReference("toDoItem");

        // TODO: Task 3 - Add a value event listener to the "message" node
        toDoItemPOJOReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot
                ToDoItem item = dataSnapshot.getValue(ToDoItem.class);
                if (item != null) {
                    tvMessageTitle.setText("Title: " + item.getTitle());
                    tvMessageDate.setText("Date: " + item.getDate());
                    tvMessageDays.setText("NumOfDays: " + item.getDays());
                    tvMessageComplete.setText("Completed? " + item.isComplete());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method will be invoked if there is any error
                Log.e(TAG, "Database error occurred", databaseError.toException());

            }
        });

        // TODO: Task 5 - Update UI elements, and then include OnClickListener for Send Message button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etMessageTitle.getText().toString();
                String date = etMessageDate.getText().toString();
                int days = Integer.parseInt(etMessageDays.getText().toString());
                boolean complete = cbMessageComplete.isChecked();

                if (title.length() > 0) {
                    ToDoItem item = new ToDoItem(title, date, days, complete);
                    toDoItemPOJOReference.setValue(item);

                    etMessageTitle.setText("");
                    etMessageDate.setText("");
                    etMessageDays.setText("");
                    cbMessageComplete.setChecked(false);
                }
            }
        });
    }
}

