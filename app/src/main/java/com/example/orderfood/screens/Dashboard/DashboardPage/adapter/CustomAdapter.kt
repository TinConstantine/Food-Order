package com.example.orderfood.screens.Dashboard.DashboardPage.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import android.widget.TextView
import com.example.orderfood.R
import com.example.orderfood.models.LocationModel
import com.example.orderfood.models.PriceModel
import com.example.orderfood.models.TimeModel

class CustomAdapter{
    class LocationAdapter(private val context: Context,private val list: List<LocationModel> ) : ArrayAdapter<LocationModel>(context, 0,list) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView?:LayoutInflater.from(context).inflate(R.layout.sp_item, parent, false)
            val textView = view.findViewById<TextView>(R.id.tvName);
            textView.text = list[position].loc;
            return view;
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            val textView = view.findViewById<CheckedTextView>(android.R.id.text1);
            textView.text = list[position].loc;
            return view;
        }

    }
    class TimeAdapter(private val context: Context,private val list: List<TimeModel> ) : ArrayAdapter<TimeModel>(context, 0,list) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView?:LayoutInflater.from(context).inflate(R.layout.sp_item, parent, false)
            val textView = view.findViewById<TextView>(R.id.tvName);
            textView.text = list[position].Value;
            return view;
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            val textView = view.findViewById<CheckedTextView>(android.R.id.text1);
            textView.text = list[position].Value;
            return view;
        }

    }
    class PriceAdapter(private val context: Context,private val list: List<PriceModel> ) : ArrayAdapter<PriceModel>(context, 0,list) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView?:LayoutInflater.from(context).inflate(R.layout.sp_item, parent, false)
            val textView = view.findViewById<TextView>(R.id.tvName);
            textView.text = list[position].Value;
            return view;
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            val textView = view.findViewById<CheckedTextView>(android.R.id.text1);
            textView.text = list[position].Value;
            return view;
        }

    }
}