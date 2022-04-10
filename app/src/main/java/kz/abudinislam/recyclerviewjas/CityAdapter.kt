package kz.abudinislam.recyclerviewjas

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_city_layout.view.*

class CityAdapter(private val context:Context): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view)

    var onClickDelete: OnClickDelete? = null

    private val diffCallback = object : DiffUtil.ItemCallback<CityModel>() {
        override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_city_layout, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Toast.makeText(context,"${differ.currentList[position].countryName} ${differ.currentList[position].cityName}",Toast.LENGTH_SHORT).show()
        }
        holder.itemView.btn_delete.setOnClickListener {
            onClickDelete?.onDelete(differ.currentList[position])
        }

        holder.itemView.tv_city_name.text = differ.currentList[position].cityName
        holder.itemView.tv_country_name.text = differ.currentList[position].countryName
        if (differ.currentList[position].cityPhoto != null) {
            holder.itemView.im_photo.setImageResource(differ.currentList[position].cityPhoto!!)

        }

    }

    override fun getItemCount(): Int = differ.currentList.size
    // return cityList.size


    fun submitList(list: List<CityModel>) {
        differ.submitList(list)
    }

    interface OnClickDelete {

        fun onDelete(item: CityModel)
    }

    fun deleteItem(index:Int) {

    }

}