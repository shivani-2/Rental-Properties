package com.example.rentalproperties_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Property> rentalProperties = new ArrayList<>();

    //Base class to hold information about the property
    public class Property {

        //property basics
        private int streetNumber;
        private String streetName;
        private String suburb;
        private String state;
        private String description;
        private String image;
        private Double price;
        private int bedrooms;
        private int bathrooms;
        private int carspots;

        //constructor
        public Property(int streetNumber, String streetName, String suburb, String state, String description, Double price, String image, int bedrooms, int bathrooms, int carspots){

            this.streetNumber = streetNumber;
            this.streetName = streetName;
            this.suburb = suburb;
            this.state = state;
            this.description = description;
            this.price = price;
            this.image = image;
            this.bedrooms = bedrooms;
            this.bathrooms = bathrooms;
            this.carspots = carspots;
        }

        //getters
        public int getStreetNumber() { return streetNumber; }
        public String getStreetName() {return streetName; }
        public String getSuburb() {return suburb; }
        public String getState() {return state; }
        public String getDescription() {return description; }
        public Double getPrice() {return price; }
        public String getImage() { return image; }
        public int getBedrooms(){ return bedrooms; }
        public int getBathrooms(){ return bathrooms; }
        public int getCarspots(){ return carspots; }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create property elements
        rentalProperties.add(
                new Property(10, "Smith Street", "Sydney", "NSW", "A large 3 bedroom apartment right in the heart of Sydney! A rare find, with 3 bedrooms and a secured car park.", 450.00, "property_image_6", 3, 1, 1));

        rentalProperties.add(
                new Property(66, "King Street", "Sydney", "NSW", "A fully furnished studio apartment overlooking the harbour. Minutes from the CBD and next to transport, this is a perfect set-up for city living.", 320.00, "property_image_6", 1, 1, 1));

        rentalProperties.add(
                new Property(1, "Liverpool Road", "Liverpool", "NSW", "A standard 3 bedroom house in the suburbs. With room for several cars and right next to shops this is perfect for new families.", 360.00, "property_image_6", 3, 2, 2));

        rentalProperties.add(
                new Property(567, "Sunny Street", "Gold Coast", "QLD", "Come and see this amazing studio apartment in the heart of the gold coast, featuring stunning waterfront views.", 360.00, "property_image_6" , 1, 1, 1));

        rentalProperties.add(
                new Property(23, "Wood Street", "East Coast", "QLD", "Come and see this amazing studio apartment in the heart of the gold coast, featuring stunning waterfront views.", 560.00, "property_image_6" , 1, 1, 1));

        rentalProperties.add(
                new Property(567, "Riven Street", "Gold Coast", "QLD", "Come and see this amazing studio apartment in the heart of the gold coast, featuring stunning waterfront views.", 360.00, "property_image_6" , 3, 3, 1));


        //create our new array adapter
        ArrayAdapter<Property> adapter = new propertyArrayAdapter(this, 0, rentalProperties);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);
    }



    //custom ArrayAdapter
    class propertyArrayAdapter extends ArrayAdapter<Property> {

        private Context context;
        private List<Property> rentalProperties;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<Property> objects) {
            super(context, resource, objects);

            this.context = context;
            this.rentalProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            Property property = rentalProperties.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.property_layout, null);

            TextView description = (TextView) view.findViewById(R.id.description);
            TextView address = (TextView) view.findViewById(R.id.address);
            TextView bedroom = (TextView) view.findViewById(R.id.bedroom);
            TextView bathroom = (TextView) view.findViewById(R.id.bathroom);
            TextView carspot = (TextView) view.findViewById(R.id.carspot);
            TextView price = (TextView) view.findViewById(R.id.price);
            ImageView image = (ImageView) view.findViewById(R.id.image);

            //set address and description
            String completeAddress = property.getStreetNumber() + " " + property.getStreetName() + ", " + property.getSuburb() + ", " + property.getState();
            address.setText(completeAddress);

            //display trimmed excerpt for description
            int descriptionLength = property.getDescription().length();
            if(descriptionLength >= 100){
                String descriptionTrim = property.getDescription().substring(0, 100) + "...";
                description.setText(descriptionTrim);
            }else{
                description.setText(property.getDescription());
            }

            //set price and rental attributes
            price.setText("$" + String.valueOf(property.getPrice()));
            bedroom.setText("Bed: " + String.valueOf(property.getBedrooms()));
            bathroom.setText("Bath: " + String.valueOf(property.getBathrooms()));
            carspot.setText("Car: " + String.valueOf(property.getCarspots()));

            //get the image associated with this property
            int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
            image.setImageResource(imageID);

            return view;
        }
    }
}