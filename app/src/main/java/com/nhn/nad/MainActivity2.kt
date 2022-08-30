package com.nhn.nad


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        fun getJsonString() : String {
            return try {
                val inputStream = assets.open("song.json")
                val fileSize: Int = inputStream.available()
                val buffer = ByteArray(fileSize)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charset.forName("UTF-8"))
            } catch (e:Exception) {
                ""
            }
        }

        fun parseJsonSongObject(json:JSONObject) : Song {
            return Song(
                name = json.getString("name"),
                desc = json.getString("desc"),
                poster = resources.getIdentifier(json.getString("poster"),"drawable",packageName)
            )
        }

        fun parseJsonObject(json: String) : List<Genre>{
            return try {
                val genres = mutableListOf<Genre>()
                val jsonObject = JSONObject(json)
                val genreArray: JSONArray = jsonObject.getJSONArray("Genres")
                for (i in 0 until genreArray.length()) {
                    val genreObject = genreArray.getJSONObject(i)
                    val songsArray = genreObject.getJSONArray("songs")
                    val songs = mutableListOf<Song>()
                    for(j in 0 until songsArray.length())
                    {
                        val songObject = songsArray.getJSONObject(j)
                        songs.add(parseJsonSongObject(songObject))
                    }
                    genres.add(Genre(name = genreObject.getString("name"), songs = songs))
                }
                return genres
            } catch (e: JSONException) {
                e.printStackTrace()
                listOf()
            }
        }


        val data = parseJsonObject(getJsonString())
        findViewById<RecyclerView>(R.id.rv).adapter = GenreAdapter(data)
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}
