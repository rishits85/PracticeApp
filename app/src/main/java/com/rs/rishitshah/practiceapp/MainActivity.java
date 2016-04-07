package com.rs.rishitshah.practiceapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l;
    String[] titles;
    String[] description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //giving the listView A layout
        l = (ListView) findViewById(R.id.listView);
        //get the string arrays set up in Strings.xml
        Resources ListViewResources = getResources();
        titles = ListViewResources.getStringArray(R.array.Title);
        description = ListViewResources.getStringArray(R.array.Description);
        int[] images = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher};
        // How to use inflate method.
//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative);
//        LayoutInflater inflator = getLayoutInflater();
//        inflator.inflate(R.layout.single_row, relativeLayout,false );

        //Calling our custom adapter in this method
        MyAdapter adapter = new MyAdapter(this, titles, images, description);
        l.setAdapter(adapter);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"hi",Toast.LENGTH_LONG);
    }

    class MyAdapter extends ArrayAdapter {
        Context context;
        int[] images;
        String[] Titles;
        String[] Description;

        //        This is the default constructor of the custom adapter
        MyAdapter(Context c, String[] Titles, int imgs[], String[] desc) {
            //Initialize the constructor of the parent adapter ie arrayAdapter where it takes 4 parameters
            super(c, R.layout.single_row, R.id.textView, Titles);
            this.context = c;
            this.images = imgs;
            this.Titles = Titles;
            this.Description = desc;


        }

        //This method is called every time the adapeter initializes
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //check if there is already a view available. Inflating new views every time is expensive operation
            //Thus if row is non null then the previous view will be recycled
            View row = convertView;
            MyViewHolder holder = null;
            if (row == null) {
                // The method getLayoutInflator not used as there is no activity or window associated with the method here
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //the parent element defined below has the the reference to the listview that is the parent of
                // the single row in this case
                //the row object has reference to the main relative layout
                row = inflater.inflate(R.layout.single_row, parent, false);
                //instantitating the holder here for the first time as new row is being created
                holder = new MyViewHolder(row);
                //this saves the holder tag for the row view so that it can be reused when recycling the view
                //tags are basically pieces of information that can be associated with a view
                row.setTag(holder);
            } else {
                //Since the row.gettag returns an object and we need to typecast it to our required view
                //this is recycling the view
                holder = (MyViewHolder) row.getTag();
            }

            //Assign the respective values to the required views

            holder.image.setImageResource(images[position]);
            holder.title.setText(Titles[position]);
            holder.description.setText(Description[position]);

            return row;
        }
    }

    public class MyViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        MyViewHolder(View v) {
            image = (ImageView) v.findViewById(R.id.imageView);
            title = (TextView) v.findViewById(R.id.textView);
            description = (TextView) v.findViewById(R.id.textView2);
        }

    }
}
