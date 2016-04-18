package org.kitdroid.proxyhelper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import org.kitdroid.helper.DialogHelper;
import org.kitdroid.helper.Toaster;
import org.kitdroid.proxyhelper.adapter.ProxyConfigAdatper;
import org.kitdroid.util.IntentUtils;

public class MainActivity extends AppCompatActivity implements OnItemLongClickListener {

    public static final int REQUEST_CODE_EDIT_PROXY = 1;
    private ListView mListView;
    private ProxyConfigAdatper adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.startActivityForResult(getActivity(), ProxyDetailEditActivity.class, REQUEST_CODE_EDIT_PROXY);

            }
        });


        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemLongClickListener(this);

        adapter = new ProxyConfigAdatper(LayoutInflater.from(getActivity()));
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                IntentUtils.startActivity(getActivity(), SettingsActivity.class);
                return true;
            }
            case R.id.action_wificonfig: {
                IntentUtils.startActivity(getActivity(), WiFiConfigListActivity.class);
                return true;
            }
            case R.id.action_about: {
                IntentUtils.startActivity(getActivity(), SettingsAboutActivity.class);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }

        switch (requestCode){
            case REQUEST_CODE_EDIT_PROXY:{
                onProxysDataChanged();
                break;
            }
            default:{

            }
        }
    }

    private void onProxysDataChanged() {
        adapter.notifyDataSetChanged();
    }

    @NonNull
    private MainActivity getActivity() {
        return MainActivity.this;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        showDelDailog();
        return false;
    }

    private void showDelDailog() {
        DialogHelper.showConfirmDialog(getActivity(), "删除代理:", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toaster.showShort(getActivity(), "Del");
            }
        });
    }
}
