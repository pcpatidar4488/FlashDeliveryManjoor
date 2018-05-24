package com.example.manjooralam.flashdelivery.adapters;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.activities.AboutUsActivity;
import com.example.manjooralam.flashdelivery.activities.AdminPanelActivity;
import com.example.manjooralam.flashdelivery.activities.AdressBookActivity;
import com.example.manjooralam.flashdelivery.activities.Contact_us;
import com.example.manjooralam.flashdelivery.activities.HomeActivity;
import com.example.manjooralam.flashdelivery.activities.MapsActivity;
import com.example.manjooralam.flashdelivery.activities.MyWalletActivity;
import com.example.manjooralam.flashdelivery.activities.NotificationActivity;
import com.example.manjooralam.flashdelivery.activities.OrderHistoryActivity;
import com.example.manjooralam.flashdelivery.activities.OrderOnPhoneActivity;
import com.example.manjooralam.flashdelivery.activities.ShoppingActivity;
import com.example.manjooralam.flashdelivery.utilities.AppUtils;
import com.google.firebase.auth.FirebaseAuth;

public class ExpendableDrawerListAdapter extends BaseExpandableListAdapter {

    private final FirebaseAuth auth;
    private Context _context;
    private ArrayList<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<String>> _listDataChild;
    private ArrayList<Boolean> openStausList = new ArrayList<>();
    int previous = -1;

    public ExpendableDrawerListAdapter(Context context, ArrayList<String> listDataHeader, HashMap<String, ArrayList<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        for (int i = 0; i < _listDataHeader.size(); i++){
            openStausList.add(false);
        }

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_drawer_list_item, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPage(view, groupPosition, childPosition);
            }
        });
        return convertView;
    }

    private void openPage(View view, int groupPosition, int childPosition) {

        switch (groupPosition) {
            case 0:
                break;
            case 1:
                switch (childPosition){
                    case 0:
                        ((HomeActivity)_context).startActivity(new Intent(_context, OrderHistoryActivity.class));
                        break;

                    case 1:
                        ((HomeActivity)_context).startActivity(new Intent(_context, MyWalletActivity.class).putExtra("EXTRA_FROM","MyWallet"));
                        break;

                    case 2:
                        ((HomeActivity)_context).startActivity(new Intent(_context, MyWalletActivity.class).putExtra("EXTRA_FROM","Reward"));
                        break;

                    case 3:
                        ((HomeActivity)_context).startActivity(new Intent(_context, ShoppingActivity.class));
                        break;

                    case 4:
                        Toast.makeText(_context, (String) getChild(groupPosition, childPosition), Toast.LENGTH_SHORT).show();
                        break;

                    case 5:
                        ((HomeActivity)_context).startActivity(new Intent(_context, AdressBookActivity.class));
                        break;

                    case 6:
                        ((HomeActivity)_context).startActivity(new Intent(_context, Contact_us.class).putExtra("EXTRA_FROM","EditProfile"));

                        break;
                }
                break;

            case 2:
                switch (childPosition) {
                    case 0:
                        break;

                    case 1:
                        break;

                    case 2:
                        break;
                }
                break;

            case 7:
                switch (childPosition) {
                    case 0:
                        ((HomeActivity)_context).startActivity(new Intent(_context, AboutUsActivity.class).putExtra("EXTRA_FROM","AboutUs"));

                        break;

                    case 1:
                        ((HomeActivity)_context).startActivity(new Intent(_context, AboutUsActivity.class).putExtra("EXTRA_FROM","PrivacyPolicy"));

                        break;

                    case 2:
                        ((HomeActivity)_context).startActivity(new Intent(_context, AboutUsActivity.class).putExtra("EXTRA_FROM","TermsAndConditions"));

                        break;
                }
                break;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_drawer_list_header, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        lblListHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(openStausList.get(groupPosition)) {
                    ((HomeActivity) _context).expListView.collapseGroup(groupPosition);
                    openStausList.remove(groupPosition);
                    openStausList.add(groupPosition, false);
                }else {
                    for (int i = 0; i < _listDataHeader.size(); i++){
                        if(i!=groupPosition) {
                            ((HomeActivity) _context).expListView.collapseGroup(i);
                            openStausList.remove(i);
                            openStausList.add(i, false);
                        }else {
                            ((HomeActivity) _context).expListView.expandGroup(groupPosition);
                            openStausList.remove(groupPosition);
                            openStausList.add(groupPosition, true);
                        }
                    }

                }
                switch (groupPosition) {
                    case 0:
                        ((HomeActivity)_context).drawerLayout.closeDrawer(Gravity.LEFT);
                        Toast.makeText(_context,"1st", Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        ((HomeActivity)_context).startActivity(new Intent(_context, OrderOnPhoneActivity.class));

                        break;
                    case 4:
                        ((HomeActivity)_context).startActivity(new Intent(_context, NotificationActivity.class));

                        break;
                    case 5:
                        ((HomeActivity)_context).startActivity(new Intent(_context, MapsActivity.class));

                        break;
                    case 6:
                        ((HomeActivity)_context).startActivity(new Intent(_context, Contact_us.class).putExtra("EXTRA_FROM","ContactUs"));

                        break;
                    case 8:
                        ((HomeActivity)_context).startActivity(new Intent(_context, AdminPanelActivity.class));
                        /*try {
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("text/plain");
                            i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                            String sAux = "\nLet me recommend you this application\n\n";
                            sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
                            i.putExtra(Intent.EXTRA_TEXT, sAux);
                            _context.startActivity(Intent.createChooser(i, "choose one"));
                        } catch(Exception e) {
                            //e.toString();
                        }*/
                        break;

                    case 9:
                        if(AppUtils.getInstance().isNetworkAvailable(_context)){
                            AppUtils.getInstance().showProgessDialog(((HomeActivity)_context));
                            ((HomeActivity)_context).logoutApiCall();
                        }else {
                            AppUtils.getInstance().showSnackBar(_context.getResources().getString(R.string.s_no_internet),((HomeActivity)_context).drawerLayout);
                        }
                        break;

                }
            }
        });

        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


   /* @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
        switch(groupPosition) {

            case 0:

                ((HomeActivity)_context).drawerLayout.closeDrawer(Gravity.LEFT);
                         Toast.makeText(_context,"ewfwefgwefe", Toast.LENGTH_SHORT).show();
                break;
        }
    }*/
}