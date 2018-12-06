package com.example.zemiapplication.openLinkPackage;

import android.support.annotation.NonNull;

import com.example.zemiapplication.openLinkPackage.POJO.LinkResponse;
import com.example.zemiapplication.openLinkPackage.network.NetworkService;
import com.example.zemiapplication.openLinkPackage.network.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WebViewPresenter implements MvpContract.MainPresenter {
    private NetworkService mNetworkService;
    private MvpContract.MainView mView;

    WebViewPresenter(MvpContract.MainView mView) {
        this.mView = mView;
    }


    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void loadAllow() {
        mNetworkService = new RetrofitHelper().getAllowFromApi();
        mCompositeDisposable.add(mNetworkService.getAllowResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LinkResponse>() {

                    @Override
                    public void accept(
                            @io.reactivex.annotations.NonNull final LinkResponse results)
                            throws Exception {
                        processResult(results);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        showError();

                    }
                })

        );

    }


    public void onDestroy() {

        mCompositeDisposable.clear();
    }

    private void processResult(LinkResponse results) {
        if (results.getOpen().equals("link")) {
            mView.showWebView(results.getOpenlink());
        } else {
            mView.showGame();
        }
    }

    @Override
    public void showError() {
        mView.showGame();

    }
}
