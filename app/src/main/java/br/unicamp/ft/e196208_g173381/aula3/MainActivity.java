package br.unicamp.ft.e196208_g173381.aula3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import br.unicamp.ft.e196208_g173381.aula3.internet.InternetFragment;
import br.unicamp.ft.e196208_g173381.aula3.jogo3.Jogo3Fragment;
import br.unicamp.ft.e196208_g173381.aula3.jogo3.StatusJogador;
import br.unicamp.ft.e196208_g173381.aula3.kotlin.KotlinActivity;
import br.unicamp.ft.e196208_g173381.aula3.alunos.*;
import br.unicamp.ft.e196208_g173381.aula3.database.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MyFirstFragment f1 = new MyFirstFragment();
            fragmentTransaction.add(R.id.frame, f1, "f1_tag");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(this, "Você! pressionou settings", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        if (id == R.id.action_contato) {
            Toast toast = Toast.makeText(this, "Você! pressionou contato", Toast.LENGTH_SHORT);
            toast.show();
            Fragment mailFragment = fragmentManager.findFragmentByTag("mail");

            if (mailFragment == null) {
                mailFragment = new MailFragment();
            }


            replaceFragment(mailFragment, "mail");
            return true;
        }
        if (id == R.id.action_desempenho2) {
            Toast toast = Toast.makeText(this, "Você! pressionou desempenho 2", Toast.LENGTH_SHORT);
            toast.show();
            Fragment desempenhoFragment = fragmentManager.findFragmentByTag("desempenho");

            if (desempenhoFragment == null) {
                desempenhoFragment = new DesempenhoFragment();
            }


            replaceFragment(desempenhoFragment, "desempenho");
            return true;
        }

        if (id == R.id.action_desempenho3) {
            Toast toast = Toast.makeText(this, "Você! pressionou desempenho 3", Toast.LENGTH_SHORT);
            toast.show();
            Fragment desempenhoFragment2 = fragmentManager.findFragmentByTag("desempenho2");

            if (desempenhoFragment2 == null) {
                desempenhoFragment2 = new StatusJogador();
            }


            replaceFragment(desempenhoFragment2, "desempenho2");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.autores) {
            // Handle the camera action
            Toast toast = Toast.makeText(this, "Você! pressionou Autores", Toast.LENGTH_SHORT);
            toast.show();
            Fragment autorFragment = new AutoresFragment();
            replaceFragment(autorFragment, "autores");

        } else if (id == R.id.nav_alunos) {

            Toast toast = Toast.makeText(this, "Você! pressionou Alunos", Toast.LENGTH_SHORT);
            toast.show();
            Fragment alunoFragment = new AlunosFragment();
            replaceFragment(alunoFragment,"alunos");

        } else if (id == R.id.nav_biografias) {
            Toast toast = Toast.makeText(this, "Você! pressionou Biografias", Toast.LENGTH_SHORT);
            toast.show();
            Fragment biografiasFragment = new BiografiasFragment();
            replaceFragment(biografiasFragment, "biografias");


        } else if (id == R.id.jogo1) {
            Toast toast = Toast.makeText(this, "Você! pressionou jogo 1", Toast.LENGTH_SHORT);
            toast.show();
            Fragment puzzleFragment = new PuzzleFragment();
            replaceFragment(puzzleFragment, "puzzle");

        } else if (id == R.id.jogo2) {
            Toast toast = Toast.makeText(this, "Você! pressionou jogo 2", Toast.LENGTH_SHORT);
            toast.show();
            Fragment nameFragment = new NameFragment();
            replaceFragment(nameFragment, "name");

        }else if (id == R.id.jogo3) {
            Toast toast = Toast.makeText(this, "Você! pressionou jogo 3", Toast.LENGTH_SHORT);
            toast.show();
            Fragment jogo3Fragment = new Jogo3Fragment();
            replaceFragment(jogo3Fragment, "jogo3");

        }  else if (id == R.id.newActivity) {
            Toast toast = Toast.makeText(this, "Kotlin Activity", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this,KotlinActivity.class);
            startActivity(intent);


        }else if (id == R.id.database) {
            Toast toast = Toast.makeText(this, "Clicou no banco", Toast.LENGTH_SHORT);
            toast.show();
            Fragment databaseFragment = new DatabaseFragment();
            replaceFragment(databaseFragment, "database");


        }else if (id == R.id.internet) {
            Toast toast = Toast.makeText(this, "Clicou no internet", Toast.LENGTH_SHORT);
            toast.show();
            Fragment internetFragment = new InternetFragment();
            replaceFragment(internetFragment, "internet");


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void doSomething(String msg){

        System.out.println(msg);
        AutoresFragment autorFragment = (AutoresFragment) fragmentManager.findFragmentByTag("autores");

        if (autorFragment == null){
            autorFragment =  new AutoresFragment();
        }
        autorFragment.setText(msg);
        replaceFragment(autorFragment, "autores");
    }

    public void showBiografia(int position){

        BiografiasFragment biografiasFragment = (BiografiasFragment) fragmentManager.findFragmentByTag("biografias");

        if(biografiasFragment == null){
            biografiasFragment = new BiografiasFragment();
        }

        biografiasFragment.setPosition(position);
        replaceFragment(biografiasFragment, "biografias");

    }



}
