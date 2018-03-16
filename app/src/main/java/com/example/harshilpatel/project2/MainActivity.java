/*
  MainActivity.java
  Project-2
  Created by Harshil Patel on 02/25/18.
  Copyright © 2018 UIC. All rights reserved.

  <<Harshil Patel>>
   U. of Illinois, Chicago
   CS 478: Spring 2018
   Project 02
 */

package com.example.harshilpatel.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    //enter the intial songs List
    String[] songs = { "Choote Choote peg", "Selfie Le Le Re", "RockStar",
                        "Centuries", "Notice Me"};

    //enter the intial artist List
    String[] artist = { "Honey Singh", "Vishal Dadlani", "21 Savage",
            "Fall Out Boys", "Migos"};

    //enter the intial URLS
    String [] urls = {"https://www.youtube.com/watch?v=xvYBg6MWPbM",
            "https://www.youtube.com/watch?v=TITGBTGJZS8",
            "https://www.youtube.com/watch?v=UceaB4D0jpo",
            "https://www.youtube.com/watch?v=dZEnQogAd8U",
            "https://www.youtube.com/watch?v=EtANOeM_tYQ"};

    //enter the intial wikipedia List
    String[] wiki_artist = {"https://en.wikipedia.org/wiki/Yo_Yo_Honey_Singh",
                            "https://en.wikipedia.org/wiki/Vishal_Dadlani",
                            "https://en.wikipedia.org/wiki/21_Savage",
                            "https://en.wikipedia.org/wiki/Fall_Out_Boy",
                            "https://en.wikipedia.org/wiki/Fall_Out_Boy"};

    //enter the intial wikipedeis List
    String[] wiki_song = {"https://en.wikipedia.org/wiki/Choote_Choote_peg",
                            "https://en.wikipedia.org/wiki/Bajrangi_Bhaijaan",
                            "https://en.wikipedia.org/wiki/RockStar-21_savage",
                            "https://en.wikipedia.org/wiki/Centuries_(song)",
                            "https://en.wikipedia.org/wiki/Culture_II"};

    //create the list of type Mao<String, String> pair
    //represents song anf artist name
    private List<Map<String, String>> list;

    //crate a map to store the songs and artist
    private Map<String, String> songsAndList;
    private SimpleAdapter adapter;


    //make different arraylist for adding the information
    private ArrayList<String> youtube = new ArrayList<>();
    private ArrayList<String> addSongs = new ArrayList<>();
    private ArrayList<String> wiki_s = new ArrayList<>();
    private ArrayList<String> wiki_a = new ArrayList<>();
    private ArrayList<String> singer = new ArrayList<>();
    private ArrayList<String> toRemove = new ArrayList<>();


    //make the EditText options for add the inofrmation
    //when user inputs new song
    EditText songName;
    EditText artistName;
    EditText video_url;
    EditText wikiperdia_page;
    EditText wikipedia_artist;

    //submenu object for adding the submenu for the remove
    SubMenu subMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add the songs to arraylist
        youtube.addAll(Arrays.asList(urls));

        //add the songs to arraylist
        singer.addAll(Arrays.asList(artist));

        //add songs to the array list
        addSongs.addAll(Arrays.asList(songs));

        //add artist wikipedia page to the array list
        wiki_a.addAll(Arrays.asList(wiki_artist));

        //add wikipedi for song to the array list
        wiki_s.addAll(Arrays.asList(wiki_song));

        //Create a list of songs
        ListView finalList = findViewById(R.id.finalList);
        //Create a array list of map to store song and artist

        //create a newArraylist of type Map<String, String>
        list = new ArrayList<>();
        for (int i=0; i<songs.length; i++) {

            //create a HashMap to add song and the artist name
            songsAndList = new HashMap<>(2);

            //put the artist name and the song name in the
            songsAndList.put("songs", songs[i]);
            songsAndList.put("artist", artist[i]);

            //add the song and the artist to the list
            list.add(songsAndList);
        }

        //Adapter which will acts as a bridge between the two xml files
        //and we use simple_list_item_2 because we have
        adapter = new SimpleAdapter(this, list, R.layout.song_artist,
                new String[] {"songs", "artist"},
                new int[] {R.id.songID,
                        R.id.artistID});
        //set the adapter
        finalList.setAdapter(adapter);

        //register for the context menu for the list we ,ade
        registerForContextMenu(finalList);

        //now we add the click method where we click the text and open
        //the directed to a webpage which open up a web page where it plays the
        //song.
        finalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String url = youtube.get(position);

                Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();

                //make and intent to start the second activity
                Intent sendURL = new Intent(MainActivity.this, Main2Activity.class);

                //send the URL
                sendURL.putExtra("url", url);

                //start the activity
                startActivity(sendURL);

            }
        }); //end of on click listner

    }
    /*-----------------------------------------------------------------------------------------------*/
    /*for context menu we have three options so we
    can view artists songs, you can view his her
    wikipediapage or can aslo view the songs wikipedia page
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();      //get the menu inflater
        inflater.inflate(R.menu.context_menu,menu);      //create the context menu

    }
    /*-----------------------------------------------------------------------------------------------*/
    /*This selects the item on the context menu
    feteched the id for and goes to the new activity
    and opens up the pace in new activity:
    The function can do three things:
    1.) To view the Video on the given URL
    2.) To view the wikipedia page og the Song
    3.) To view the wikipedia page og the artist
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //get the adapter view
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //get the id of the itme in the context menu
        int id = item.getItemId();

        //if the id is for watch the video open up the video for the song
        if(id == R.id.view_video){
            String url = youtube.get(info.position);
            //Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();

            //make and intent to start the second activity
            Intent sendURL = new Intent(MainActivity.this, Main2Activity.class);

            //send the url to be open
            sendURL.putExtra("url", url);

            //start the activity
            startActivity(sendURL);
            return true;

        }

        //if the id is for viewing the information of the wikipedia artist
        // open that URl
        if(id == R.id.view_artist_wiki){
            String url = wiki_a.get(info.position);
            //Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();

            //make and intent to start the second activity
            Intent sendURL = new Intent(MainActivity.this, Main2Activity.class);

            //send the url to be open
            sendURL.putExtra("url", url);

            //start the activity
            startActivity(sendURL);
            return true;

        }

        //if the id is for viewing the information of the wikipedia song
        // open that URl
        if(id == R.id.view_song_wiki){

            String url = wiki_s.get(info.position);
            //Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();

            //make and intent to start the second activity
            Intent sendURL = new Intent(MainActivity.this, Main2Activity.class);

            //send the url to be open
            sendURL.putExtra("url", url);

            //start the activity
            startActivity(sendURL);
            return true;

        }

        return true;
    }


    /*------------------------------------------------------------------------------------------*/
    /*
    Create a options menu and add the listeners to it
    the options menu has add, delete and exit the app
    Add: adds a song, artist name, youtubepage, wikipedia page
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        MenuItem remove = menu.findItem(R.id.id_remove);
        subMenu = remove.getSubMenu();

        for(int i =0; i< addSongs.size(); i++){
            subMenu.add(addSongs.get(i));
        }



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.id_add){

            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add, null);
            songName = v.findViewById(R.id.song);
            artistName = v.findViewById(R.id.artist);
            video_url = v.findViewById(R.id.video);
            wikiperdia_page = v.findViewById(R.id.song_info);
            wikipedia_artist = v.findViewById(R.id.artist_info);



            AlertDialog.Builder b1 = new AlertDialog.Builder(MainActivity.this);
            b1.setTitle("Song Name");
            b1.setMessage("Plese enter the following info: ");

            b1.setView(v);


            //add the submit button
            b1.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String song = songName.getText().toString();
                    String artist = artistName.getText().toString();
                    String url = video_url.getText().toString();
                    String s = wikiperdia_page.getText().toString();
                    String a = wikipedia_artist.getText().toString();


                    //add it to the list//songsAndList.put("artist", artist);
                    songsAndList = new HashMap<>(2);

                    songsAndList.put("songs", song);
                    songsAndList.put("artist", artist);

                    list.add(songsAndList);

                    //notify the change to the list view
                    adapter.notifyDataSetChanged();

                    addSongs.add(song);
                    youtube.add(url);
                    singer.add(artist);
                    wiki_s.add(s);
                    wiki_a.add(a);

                    subMenu.add(song);

                    //Toast.makeText(getApplicationContext(), song, Toast.LENGTH_LONG).show();

                }
            });

            //add the cancel button
            b1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            //create the dialog object
            AlertDialog a1 = b1.create();
            a1.show();

            return true;
        }//end of add song

        for(int i= 0; i<list.size(); i++){

            Map<String, String> listItem = list.get(i);
            String song = listItem.get("songs");

            if(list.size() == 1){
                Toast.makeText(getApplicationContext(), "You cannot delete this Song.", Toast.LENGTH_LONG).show();
                break;

            }
            if(item.getTitle().toString() == song){

                Toast.makeText(getApplicationContext(), song + "removed from playlist", Toast.LENGTH_SHORT).show();

                subMenu.removeItem(i);
                list.remove(i);

                adapter.notifyDataSetChanged();
            }
        }
        if(id == R.id.id_exit){

            finish();
        }
        return true;
    }
}
