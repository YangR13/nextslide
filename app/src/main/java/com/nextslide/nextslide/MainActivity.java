package com.nextslide.nextslide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PresentationList.PresentationListManager {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Presentation> mPresentations;
    private String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Display list of all presentations.
        mPresentations = new ArrayList<Presentation>();
        mAdapter = new PresentationList(this, mPresentations);
        mRecyclerView.setAdapter(mAdapter);

        // TODO: For each presentation, give user option to edit or present.
        // TODO: Create presentation builder activity. Activity allows users to add files from gallery to resource folder (maybe, might be better solutions). Activity also displays existing associations.

        // TEST: Build a test presentation list!
        Presentation p1 = new Presentation("Yang's Presentation", "Karpkarp. And Food. Fish are friends not food.");
        p1.addAction("carp", new Presentation.ImageAction(R.drawable.karpkarp));
        p1.addAction("sushi", new Presentation.ImageAction(R.drawable.sushi));
        p1.addAction("also sushi", new Presentation.ImageAction(R.drawable.sushi2));
        p1.addAction("curry", new Presentation.ImageAction(R.drawable.curry));
        p1.addAction("ramen", new Presentation.ImageAction(R.drawable.ramen));
        mPresentations.add(p1);

        Log.d(TAG,"Creating p2");
        Presentation p2 = new Presentation("Brian's Presentation", "Memes. All the memes.");
        p2.addAction("stand", new Presentation.ImageAction(R.drawable.jojo));
        p2.addAction("alligators", new Presentation.ImageAction(R.drawable.alligators));
        p2.addAction("popping", new Presentation.ImageAction(R.drawable.jimbo));
        p2.addAction("abilities", new Presentation.ImageAction(R.drawable.tommy));
        p2.addAction("six", new Presentation.SoundAction(R.raw.drakesix));
        p2.addAction("drake", new Presentation.ImageAction(R.drawable.drake));
        p2.addAction("good", new Presentation.SoundAction(R.raw.verygood));
        mPresentations.add(p2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Start Activity to create new Presentation.
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Impement PresentationListManager.
    public void startPresentation(Presentation presentation) {
        // Create an PresentationActivity Intent.
        Intent intent = new Intent(this, RecognitionActivity.class);
        Bundle presentationBundle = new Bundle();
        presentationBundle.putParcelable("presentation_id", presentation);
        intent.putExtras(presentationBundle);
        Log.d(TAG,"Starting presentation");
        this.startActivity(intent);
    }
}
