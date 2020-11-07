package com.example.GithubApiList.repodetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.GithubApiList.R
import com.example.GithubApiList.models.Repo

class RepoDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        val name =intent.getStringExtra(KEY_NAME)
        val owner_name =intent.getStringExtra(KEY_OWNER_NAME)

        val nameText: TextView = findViewById(R.id.repo_name)
        val ownerNameText: TextView = findViewById(R.id.repo_owner_name)

        nameText.text = name
        ownerNameText.text = "@${owner_name}"

    }


    companion object {
        const val KEY_NAME="KEY_NAME"
        const val KEY_OWNER_NAME="KEY_OWNER_NAME"

        fun startActivity(context: Context, repo: Repo){
            val detailsActivityIntent = Intent(context, RepoDetailsActivity::class.java)
            detailsActivityIntent.putExtra(KEY_NAME, repo.name)
            detailsActivityIntent.putExtra(KEY_OWNER_NAME, repo.owner.login)
            context.startActivity(detailsActivityIntent)

        }

    }
}