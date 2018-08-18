package xyz.reddvid.thevisaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private ListView listView;
    private CustomAdapter adapter;
    private List<String> spinnerArray;
    private List<Countries> visaArray;
    private final String[] COUNTRY_LIST = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Costa Rica", "Côte d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran, Islamic Republic of", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Lao PDR", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia, Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestinian Territory, Occupied", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Saint Vincent and the Grenadines", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Holy See (Vatican City State)", "Venezuela, Bolivarian Republic of", "Viet Nam", "Yemen", "Zambia", "Zimbabwe"};
    private final String[] COUNTRY_CODES = {"af", "al", "dz", "ad", "ao", "ag", "ar", "am", "au", "at", "az", "bs", "bh", "bd", "bb", "by", "be", "bz", "bj", "bt", "bo", "ba", "bw", "br", "bn", "bg", "bf", "bi", "kh", "cm", "ca", "cv", "cf", "td", "cl", "cn", "co", "km", "cg", "cd", "cr", "ci", "hr", "cu", "cy", "cz", "dk", "dj", "dm", "ic_do", "ec", "eg", "sv", "gq", "er", "ee", "et", "fj", "fi", "fr", "ga", "gm", "ge", "de", "gh", "gr", "gd", "gt", "gn", "gw", "gy", "ht", "hn", "hk", "hu", "is", "in", "id", "ir", "iq", "ie", "il", "it", "jm", "jp", "jo", "kz", "ke", "ki", "xk", "kw", "kg", "la", "lv", "lb", "ls", "lr", "ly", "li", "lt", "lu", "mo", "mk", "mg", "mw", "my", "mv", "ml", "mt", "mh", "mr", "mu", "mx", "fm", "md", "mc", "mn", "me", "ma", "mz", "mm", "na", "nr", "np", "nl", "nz", "ni", "ne", "ng", "kp", "no", "om", "pk", "pw", "ps", "pa", "pg", "py", "pe", "ph", "pl", "pt", "qa", "ro", "ru", "rw", "kn", "lc", "ws", "sm", "st", "sa", "sn", "rs", "sc", "sl", "sg", "sk", "si", "sb", "so", "za", "kr", "ss", "es", "lk", "vc", "sd", "sr", "sz", "se", "ch", "sy", "tw", "tj", "tz", "th", "tl", "tg", "to", "tt", "tn", "tr", "tm", "tv", "ug", "ua", "ae", "gb", "us", "uy", "uz", "vu", "va", "ve", "vn", "ye", "zm", "zw"};
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

//        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
//
//        if(useDarkTheme) {
//            setTheme(R.style.AppTheme_Dark_NoActionBar);
//        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinnerBox);
        spinnerArray = new ArrayList<String>();
        visaArray = new ArrayList<Countries>();
        recyclerView = findViewById(R.id.recyclerView);


        // Read JSON File
        final String jsonString = loadJSONFromAsset(getApplicationContext());

        try {
            JSONArray mJsonArray = new JSONArray(jsonString);

            for (int i = 0; i < mJsonArray.length(); i++) {
                JSONObject mJsonObject = mJsonArray.getJSONObject(i);

                spinnerArray.add(mJsonObject.getString("Passport"));


            }


//            JSONArray mJsonArrayProperty = mJsonObject.getJSONArray("properties");
//                      for (int i = 0; i < mJsonArrayProperty.length(); i++) {
//                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);
//
//                String prop_id = mJsonObjectProperty.getString("prop_id");
//                String prop_name = mJsonObjectProperty.getString("prop_name");
//                String address = mJsonObjectProperty.getString("address");
//                String city = mJsonObjectProperty.getString("city");
//                String state = mJsonObjectProperty.getString("state");
//                String zip = mJsonObjectProperty.getString("zip");
//                String lat = mJsonObjectProperty.getString("lat");
//                String lon = mJsonObjectProperty.getString("long");
//            }


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(adapter.getPosition("Philippines"));
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    // TODO Auto-generated method stub
                    Toast.makeText(parent.getContext(),
                            "Showing visa requirements for " + parent.getItemAtPosition(pos).toString(),
                            Toast.LENGTH_LONG).show();

                    LoadVisa(pos, jsonString);
                }


                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
    }

    private void LoadVisa(int index, String jsonString) {
        visaArray.clear();

        try {
            JSONArray mJsonArray = new JSONArray(jsonString);
            JSONObject mJsonObject = mJsonArray.getJSONObject(index);

            for (int x = 0; x < COUNTRY_LIST.length; x++) {
                String country = COUNTRY_LIST[x];
                String visaStatus = mJsonObject.getString(COUNTRY_LIST[x]);
                String countryCode = COUNTRY_CODES[x];
                int id = getResources().getIdentifier(countryCode, "drawable", getPackageName());

                Log.w("myApp", country + visaStatus + id);

                if (!visaStatus.equals("-1"))
                    visaArray.add(new Countries(country, visaStatus, id));
            }
            Log.w("myApp", String.valueOf(visaArray.size()));
            adapter = new CustomAdapter(visaArray, this);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.custom_menu, menu);

//        Switch toggle = menu.findViewById(R.id.app_bar_switch);
//        toggle.setChecked(useDarkTheme);
//        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
//                toggleTheme(isChecked);
//            }
//        });
        return true;
    }
}
