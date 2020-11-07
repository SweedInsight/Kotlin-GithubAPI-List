package com.example.GithubApiList

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.GithubApiList.api.SearchResult
import com.example.GithubApiList.api.createGithubApiService
import com.example.GithubApiList.repodetails.RepoDetailsActivity
import com.example.GithubApiList.reposlist.ReposAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //    private val adapter = ReposAdapter()
    private lateinit var adapter: ReposAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val button: Button = findViewById(R.id.button)
//        button.setOnClickListener {
//            Toast.makeText(this, "Clicked Button", Toast.LENGTH_SHORT).show();
//        }

//      repo -> /// more readable then "it.name"
//        adapter = ReposAdapter {
////            Toast.makeText(this,it.name, Toast.LENGTH_SHORT)
////            Toast.makeText(this, it.name, Toast.LENGTH_SHORT)
//            Activity.start
//        }

        adapter = ReposAdapter() { repo ->
//            Toast.makeText(this, repo.name, Toast.LENGTH_SHORT)
            RepoDetailsActivity.startActivity(this, repo)
        }

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter


        var service = createGithubApiService()
        service.searchRepositories("android").enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val repos = response.body()?.items.orEmpty();
                adapter.submitList(repos)
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                // handle failure
            }


        })


    }


}