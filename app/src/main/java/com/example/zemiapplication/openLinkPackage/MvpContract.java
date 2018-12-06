package com.example.zemiapplication.openLinkPackage;

public interface MvpContract {



    interface MainView {


        void showWebView(String link);

        void showGame();

    }

    interface MainPresenter {


        void loadAllow();

        void showError();

        void onDestroy();
    }


}
