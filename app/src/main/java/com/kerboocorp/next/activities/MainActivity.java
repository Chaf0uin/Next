package com.kerboocorp.next.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kerboocorp.next.R;
import com.kerboocorp.next.adapters.StuffAdapter;
import com.kerboocorp.next.managers.HintApi;
import com.kerboocorp.next.managers.StuffManager;
import com.melnykov.fab.FloatingActionButton;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.stuffList)
    RecyclerView stuffListView;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private LinearLayoutManager linearLayoutManager;
    private StuffAdapter stuffAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);


        linearLayoutManager = new LinearLayoutManager(this);
        stuffListView.setLayoutManager(linearLayoutManager);
        stuffListView.setItemAnimator(new DefaultItemAnimator());

        stuffAdapter = new StuffAdapter(R.layout.list_item_stuff, this);
        stuffListView.setAdapter(stuffAdapter);

        stuffAdapter.addItemList(StuffManager.getInstance().findStuffList());

        fab.attachToRecyclerView(stuffListView);

//        OAuthService service = new ServiceBuilder()
//                .provider(HintApi.class)
//                .apiKey("31fea7e36a89521e75a4a603d3c4e768d27445bada0b3659538c134eb9dda998")
//                .apiSecret("9ea0143ab6344e19b64a204543cc7e913cb7284409a0379871de6ebaca564d35")
//                .callback("hint://call/back")
//                .build();
//
//        String authorizationUrl = service.getAuthorizationUrl(null);
//        Log.d("TEST", authorizationUrl);
//        Scanner in = new Scanner(System.in);
//        Verifier verifier = new Verifier(in.nextLine());
//        Token accessToken = service.getAccessToken(null, verifier);
//        Log.d("TEST", accessToken.getToken());
//        OAuthRequest request = new OAuthRequest(Verb.GET, "http://projectb.kerboocorp.com/api/v1/hintobjects");
//        service.signRequest(accessToken, request);
//        Response response = request.send();
//        Log.d("TEST", response.getBody());

        // Replace these with your own api key, secret and callback
        String apiKey = "31fea7e36a89521e75a4a603d3c4e768d27445bada0b3659538c134eb9dda998";
        String apiSecret = "9ea0143ab6344e19b64a204543cc7e913cb7284409a0379871de6ebaca564d35";
        String callback = "hint://call/back";


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
