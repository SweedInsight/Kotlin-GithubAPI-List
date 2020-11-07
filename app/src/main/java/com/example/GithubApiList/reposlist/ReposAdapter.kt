package com.example.GithubApiList.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.GithubApiList.R
import com.example.GithubApiList.models.Repo

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val name: TextView = view.findViewById(R.id.name)
    private val owner: TextView = view.findViewById(R.id.owner)

    fun bind(repo: Repo) {
        name.text = repo.name
        owner.text = "@${repo.owner.login}"
    }
}
// Update Viewitem or bind a completly new Viewitem
val diffCallback = object : DiffUtil.ItemCallback<Repo>(){
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}

class ReposAdapter(private val repoClickHandler: (Repo) -> Unit) : ListAdapter<Repo, RepoViewHolder>(diffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo,parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
        // holder.itemView get the root view for the click listener
        holder.itemView.setOnClickListener(){
            repoClickHandler(getItem(position))
        }
    }
}