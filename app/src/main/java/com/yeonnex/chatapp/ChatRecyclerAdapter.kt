package com.yeonnex.chatapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ChatItem(val other: String = "", val my: String = "") {}

class ChatRecyclerAdapter(val context: Context?, val itmes: List<ChatItem>)
    : RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var otherMessage: TextView
        var myMessage: TextView
        init {
            otherMessage = itemView.findViewById(R.id.tvOtherMsg)
            myMessage = itemView.findViewById(R.id.tvMyMsg)

            itemView.setOnClickListener { v: View ->
                // 클릭 이벤트를 구현
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ChatRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_chat, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.otherMessage.text = itmes[position].other
        holder.myMessage.text = itmes[position].my
    }

    override fun getItemCount(): Int {
        return itmes.size
    }
}
