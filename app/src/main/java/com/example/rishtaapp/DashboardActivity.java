package com.example.rishtaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private DrawerLayout drawerLayout;
    private Button menubtn;
    private TextView textUsername;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        drawerLayout = findViewById(R.id.drawer_layout);
        menubtn = findViewById(R.id.Menubtn);
        menubtn.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(findViewById(R.id.nav_view))) {
                drawerLayout.closeDrawer(findViewById(R.id.nav_view)); // Close the drawer
            } else {
                drawerLayout.openDrawer(findViewById(R.id.nav_view)); // Open the drawer
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        String username = getIntent().getStringExtra("username");

        NavigationView navigationView = findViewById(R.id.nav_view);
        LinearLayout navHeader = (LinearLayout) navigationView.getHeaderView(0);
        textUsername = navHeader.findViewById(R.id.textusername);
        if (username != null) {
            textUsername.setText(username);
        }
         fragmentManager = getSupportFragmentManager();

        String[] actionNames = {"Create Client", "View Clients", "Delete Client", "Search Client", "Update Client Status"};
        int[] actionIcons = {R.drawable.baseline_create_24, R.drawable.baseline_pageview_24, R.drawable.baseline_delete_forever_24, R.drawable.baseline_search_24, R.drawable.baseline_update_24};

        cardAdapter = new CardAdapter(this, actionNames, actionIcons, fragmentManager);
        recyclerView.setAdapter(cardAdapter);


        MenuItem logoutMenuItem = navigationView.getMenu().findItem(R.id.nav_logout);
        MenuItem cardNavigation = navigationView.getMenu().findItem(R.id.card_dashboard);

        logoutMenuItem.setOnMenuItemClickListener(menuItem -> {
            SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear(); // Clear login data
            editor.apply();

            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clears activity stack
            startActivity(intent);
            finish();
            Toast.makeText(DashboardActivity.this, "User Logged out", Toast.LENGTH_SHORT).show();
            return true;
        });
        cardNavigation.setOnMenuItemClickListener(menuItem -> {
            recyclerView.setVisibility(View.VISIBLE);

            getSupportFragmentManager().popBackStack();
            return true;
        });

    }
    public void loadFragment(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new CreateClientFragment();
                break;
            case 1:
                fragment = new ViewClientsFragment();
                break;
            case 2:
                fragment = new DeleteClientFragment();
                break;
            case 3:
                fragment = new SearchClientFragment();
                break;
            case 4:
                fragment = new UpdateClientStatusFragment();
                break;
        }



        // If a fragment is selected, replace the existing content and hide RecyclerView
        if (fragment != null) {
            recyclerView.setVisibility(View.GONE); // Hide the RecyclerView when a fragment is loaded
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment) // Replace content in frame layout with the selected fragment
                    .addToBackStack(null) // Add to back stack so we can go back to RecyclerView later
                    .commit();
        }
    }

}
